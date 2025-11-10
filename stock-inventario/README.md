stock-inventario - microservicio de inventario (Spring WebFlux + Reactive MongoDB)

Puertos y conexión:
- Servicio: http://localhost:8085
- MongoDB: mongodb://localhost:27017/stockInventarioDB

Endpoints principales:
- POST /api/inventario
- GET  /api/inventario
- GET  /api/inventario/{id}
- GET  /api/inventario/nombre/{nombre}
- GET  /api/inventario/categoria/{categoria}
- PUT  /api/inventario/{id}/aumentar/{cantidad}  (opcional body JSON para crear si no existe)
- PUT  /api/inventario/{id}/disminuir/{cantidad}
