# Data Safety (Google Play) - Borrador para UGasolineras

Última actualización: 3 de abril de 2026

## Resumen de respuestas recomendadas

- ¿La app recopila datos? Sí.
- ¿La app comparte datos con terceros? No (no con fines comerciales).
- ¿Se recopilan datos de ubicación? Sí.
- ¿La recopilación es opcional? Sí, depende del permiso.
- ¿Se requiere para funcionalidad principal? Sí.
- ¿Los datos están cifrados en tránsito? Sí (HTTPS).
- ¿El usuario puede solicitar eliminación? Los datos locales se eliminan borrando datos de app/desinstalando.

## Tipo de datos

- `Location`:
  - `Approximate location`: Sí (si solo se concede aproximada).
  - `Precise location`: Sí (si se concede precisa).

## Propósitos declarables en formulario

- `App functionality` (principal).
- `Analytics`: No (si no añades SDK de analítica).
- `Advertising or marketing`: No.

## Compartición

- No se comparten datos personales con terceros con fines de monetización o publicidad.
- Se realizan llamadas técnicas a APIs externas para funcionamiento de mapas/datos, como parte del servicio.

## Notas para Play Console

- Mantener coherencia exacta entre:
  - Permisos declarados en `AndroidManifest.xml`.
  - Texto de Política de Privacidad.
  - Declaración Data Safety.

- Si en el futuro añades:
  - analítica,
  - crash reporting con identificadores,
  - publicidad,
  deberás actualizar Data Safety y la política.
