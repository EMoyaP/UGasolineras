# Arquitectura (resumen)

## Capas
- `WebView` nativa en `MainActivity`.
- Logica principal de UI y datos en `app/src/main/assets/index.html`.
- Bridge nativo (`WebAppBridge.kt`) para cache local persistente.
- Worker de refresco para actualizacion periodica de precios.
- Modulo Android Auto con servicio dedicado.

## Caches
- Cache estatica de entidades dentro de 50 km.
- Cache volatil de precios y metadatos recientes.
- Poda de datos fuera de radio para ahorrar espacio.

## Frecuencia de refresco
- Primer plano: 30s (si app visible).
- Segundo plano: 1 hora (precios).
