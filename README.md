# UGasolineras (Android)

Aplicacion Android de UGasolineras para localizar gasolineras y puntos de recarga cercanos, con mapa interactivo, cache inteligente y soporte base para Android Auto.

## Estado
- Version: `1.1.2` (`versionCode 4`)
- Estado: Probada en Android (version estable de referencia)
- Plataforma incluida en este repositorio: **Android**

## Funcionalidades clave
- Geolocalizacion en tiempo real (permiso while-in-use).
- Modo fosil / electrico con cambio suave.
- Cache local de radio 50 km para acelerar apertura.
- Actualizacion de datos:
  - Cada 30 segundos en primer plano.
  - Cada hora en segundo plano (precios).
- Modal de resultados y detalle de estaciones.
- Tema claro/oscuro/sistema.
- Mitigaciones de seguridad en el HTML integrado (bloques EV / saneado / control de peticiones).
- Integracion base con Android Auto (categoria POI).

## Estructura del proyecto
```text
app/
  src/main/
    assets/index.html
    java/com/ug/gasolineralowcost/
    res/
playstore/
  privacy-policy-es.md
  data-safety-es.md
  publish-checklist-es.md
  store-listing-es.md
```

## Requisitos
- Android Studio Hedgehog o superior (recomendado).
- JDK 17.
- SDK Android con `compileSdk 35`.
- Conexi?n a internet para dependencias Gradle.

## Compilacion (Android Studio)
1. Abre la carpeta del proyecto en Android Studio.
2. Espera sincronizacion de Gradle.
3. Ejecuta `Run` para debug en dispositivo/emulador.
4. Para release, usa `Build > Generate Signed Bundle / APK`.

## Compilacion (CLI, opcional)
Si tienes Gradle instalado en PATH:
```bash
gradle :app:assembleDebug
gradle :app:assembleRelease
```

## Publicacion y cumplimiento
En la carpeta `playstore/` tienes material inicial para publicacion:
- Politica de privacidad.
- Data Safety.
- Checklist de revision.
- Texto de ficha de Play Store.

## Seguridad y privacidad
- Ubicacion usada solo para funcionalidad local de la app.
- No se comparte ubicacion con terceros (segun configuracion actual del proyecto).
- Revisa credenciales/API keys antes de publicar en produccion.

## Uso Comercial
- No se permite uso comercial sin autorizacion expresa del titular del proyecto.

## Android Auto
- Se incluye base tecnica para Android Auto.
- Antes de publicacion final, revisar:
  - hosts permitidos,
  - validacion de templates,
  - cumplimiento de politicas de Google para Car App.

## Hoja de ruta recomendada
- A?adir tests instrumentados para flujos criticos (location/cache/modal).
- Preparar CI de release y firma segura.
- Revisar accesibilidad (contraste, tama?o de fuente, TalkBack).

## Licencia
Este repositorio se publica con licencia **CC BY-NC 4.0** (no permite uso comercial).
Revisa `LICENSE` para detalle completo.

## Autor
- Eugenio Moya Perez
- Contacto: eugenio.moya.perez@gmail.com
