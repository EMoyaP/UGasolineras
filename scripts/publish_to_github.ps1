param(
  [Parameter(Mandatory=$false)]
  [string]$RepoName = "UGasolineras",

  [Parameter(Mandatory=$false)]
  [string]$GithubUser = "EMoyaP"
)

$ErrorActionPreference = "Stop"

if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
  Write-Error "Git no esta instalado en este equipo. Instala Git y vuelve a ejecutar este script."
}

$remote = "https://github.com/$GithubUser/$RepoName.git"

if (-not (Test-Path ".git")) {
  git init
}

git add .
git commit -m "chore: initial professional Android release for GitHub" --allow-empty

git branch -M main

$existingRemote = git remote 2>$null
if ($existingRemote -notcontains "origin") {
  git remote add origin $remote
} else {
  git remote set-url origin $remote
}

git push -u origin main

Write-Host "Publicado en: $remote"
