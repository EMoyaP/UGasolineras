# Checklist de Publicación - UGasolineras

## 1. Antes de subir

- Versión a publicar: `Gratis` (con botón de apoyo).
- Revisar `targetSdk` y compatibilidad.
- Verificar que permisos sean solo `while in use`.

## 2. Firma (obligatorio para Play)

Sí, es necesario tener un `keystore` de release.

- Crear `upload keystore` (recomendado).
- Guardar de forma segura:
  - archivo `.jks`,
  - alias,
  - contraseña de keystore,
  - contraseña de alias.
- Activar `Play App Signing` al publicar.

## 3. Contenido legal y consola

- Política de privacidad actual: local.
- Para publicar en Play: convertirla a URL pública (web simple/GitHub Pages) y pegarla en Play Console.
- Completar formulario `Data safety`.
- Completar sección `Contenido de la app`:
  - audiencia objetivo,
  - anuncios (No),
  - acceso a datos (ubicación).

## 4. Android Auto

- Probar flujo de búsqueda y detalle en Android Auto.
- Probar botón `Navegar` desde coche.
- Revisar validación de hosts para release.

## 5. QA mínimo antes de release

- Instalar APK release en:
  - móvil Android,
  - tablet Android.
- Verificar:
  - permiso ubicación al primer uso,
  - actualización de ubicación cada 30s en primer plano,
  - funcionamiento offline parcial con caché,
  - carga de mapa sin errores de bloqueo de teselas,
  - modal de resultados y modal de detalle.

## 6. Assets recomendados para Play

- Icono 512x512.
- Capturas:
  - teléfono (mínimo 2),
  - tablet de 7"/10" (mínimo 1 por tipo).
- Texto de ficha (`store-listing-es.md`).
