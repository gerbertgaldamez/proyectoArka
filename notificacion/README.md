# Notificación Service (Spring Boot WebFlux + R2DBC SQL Server)

Microservicio para enviar notificaciones de **Email/SMS** sobre estados de pedido
(1=CONFIRMAR, 2=DESPACHAR, 4=ENTREGADO, 3=CANCELADO) y carritos abandonados.

## Ejecutar
1. Configura `spring.r2dbc.url` para tu SQL Server (usa `r2dbc:sqlserver://host:1433;database=notificaciones`).
2. Ajusta las credenciales de correo SMTP en `application.yml`.
3. `./gradlew bootRun`

## Endpoints
- `POST /api/notificaciones/pedido/{pedidoId}?estado=1&email=...&telefono=...`
- `POST /api/notificaciones/carrito/abandonado`

## SQL (DDL)
Crea la tabla (ejecuta en SQL Server):

```sql
CREATE TABLE notificacion (
  id BIGINT IDENTITY PRIMARY KEY,
  pedido_id VARCHAR(64) NOT NULL,
  canal VARCHAR(16) NOT NULL,          -- EMAIL | SMS
  evento VARCHAR(32) NOT NULL,         -- CONFIRMAR | DESPACHAR | ENTREGADO | CANCELADO | CARRITO_ABANDONADO
  destinatario VARCHAR(128) NOT NULL,  -- email o teléfono
  asunto VARCHAR(200) NULL,
  mensaje NVARCHAR(2000) NULL,
  estado_envio VARCHAR(16) NOT NULL,   -- PENDIENTE | ENVIADO | FALLIDO
  error NVARCHAR(1000) NULL,
  enviado_en DATETIMEOFFSET NULL,
  creado_en DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET()
);
```

> Nota: Spring Boot no ejecuta `schema.sql` con R2DBC automáticamente; crea la tabla manualmente o integra Flyway/JDBC para migraciones.
