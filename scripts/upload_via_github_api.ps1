param(
  [Parameter(Mandatory=$false)]
  [string]$GithubUser = "EMoyaP",

  [Parameter(Mandatory=$false)]
  [string]$RepoName = "UGasolineras",

  [Parameter(Mandatory=$false)]
  [string]$SourceDir = ".",

  [Parameter(Mandatory=$false)]
  [string]$Token = ""
)

$ErrorActionPreference = "Stop"

if ([string]::IsNullOrWhiteSpace($Token)) {
  $Token = Read-Host "Pega tu token de GitHub (PAT)"
}

if ([string]::IsNullOrWhiteSpace($Token)) {
  throw "Token no proporcionado."
}

$SourceDir = (Resolve-Path $SourceDir).Path
$apiBase = "https://api.github.com"
$repoApi = "$apiBase/repos/$GithubUser/$RepoName"

$headers = @{
  Authorization = "Bearer $Token"
  Accept = "application/vnd.github+json"
  "X-GitHub-Api-Version" = "2022-11-28"
  "User-Agent" = "UGasolinerasUploader"
}

function Invoke-GH {
  param(
    [string]$Method,
    [string]$Uri,
    $Body = $null,
    [switch]$AllowNotFound
  )
  try {
    if ($null -eq $Body) {
      return Invoke-RestMethod -Method $Method -Uri $Uri -Headers $headers
    }
    return Invoke-RestMethod -Method $Method -Uri $Uri -Headers $headers -Body ($Body | ConvertTo-Json -Depth 20)
  }
  catch {
    if ($AllowNotFound -and $_.Exception.Response.StatusCode.value__ -eq 404) {
      return $null
    }
    throw
  }
}

Write-Host "Comprobando repo $GithubUser/$RepoName..."
$repo = Invoke-GH -Method GET -Uri $repoApi -AllowNotFound
if ($null -eq $repo) {
  Write-Host "Creando repo publico..."
  $createBody = @{
    name = $RepoName
    private = $false
    description = "UGasolineras Android app"
    auto_init = $false
    has_issues = $true
    has_wiki = $false
    has_projects = $false
  }
  Invoke-GH -Method POST -Uri "$apiBase/user/repos" -Body $createBody | Out-Null
}

$allFiles = Get-ChildItem -Path $SourceDir -Recurse -File | Where-Object {
  $full = $_.FullName
  $full -notmatch "\\.git(\\|$)" -and
  $full -notmatch "\\app\\build(\\|$)" -and
  $full -notmatch "\\.gradle(\\|$)"
}

Write-Host "Subiendo $($allFiles.Count) ficheros..."

foreach ($file in $allFiles) {
  $relative = $file.FullName.Substring($SourceDir.Length).TrimStart('\\')
  $pathGit = ($relative -replace "\\", "/")

  $contentBytes = [System.IO.File]::ReadAllBytes($file.FullName)
  $contentB64 = [System.Convert]::ToBase64String($contentBytes)

  $existing = Invoke-GH -Method GET -Uri "$repoApi/contents/$pathGit" -AllowNotFound

  $body = @{
    message = "chore: add/update $pathGit"
    content = $contentB64
  }
  if ($null -ne $existing -and $existing.sha) {
    $body.sha = $existing.sha
  }

  Invoke-GH -Method PUT -Uri "$repoApi/contents/$pathGit" -Body $body | Out-Null
  Write-Host "OK  $pathGit"
}

Write-Host "Completado. Repo: https://github.com/$GithubUser/$RepoName"
