# Microservicio de Inventario — Tienda PC

## Descripción
Microservicio desarrollado para gestionar el inventario de una tienda en línea de componentes de computadores.

Permite registrar, consultar, actualizar y eliminar inventarios, además de identificar productos con stock bajo.

## Tecnologías utilizadas
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman
- Git y GitHub

## Configuración
- **Puerto:** 8083
- **Base de datos:** `tienda_inventario_db`
- **URL base:** `http://localhost:8083/api/inventarios`

## Funcionalidades
- Registrar inventarios.
- Consultar todos los inventarios.
- Buscar inventario por ID.
- Buscar inventario por ID de producto.
- Actualizar cantidades y stock mínimo.
- Eliminar registros.
- Detectar stock bajo.

## Endpoints REST

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/inventarios` | Crear inventario |
| GET | `/api/inventarios` | Listar inventarios |
| GET | `/api/inventarios/{id}` | Buscar por ID |
| GET | `/api/inventarios/producto/{productoId}` | Buscar por producto |
| PUT | `/api/inventarios/{id}` | Actualizar inventario |
| DELETE | `/api/inventarios/{id}` | Eliminar inventario |
| GET | `/api/inventarios/{id}/stock-bajo` | Consultar stock bajo |

## Ejemplo de JSON

```json
{
  "productoId": 2,
  "cantidad": 10,
  "stockMinimo": 3,
  "ubicacion": "Bodega A"
}
```

## Base de datos

```sql
CREATE DATABASE IF NOT EXISTS tienda_inventario_db;
```

La aplicación utiliza JPA para gestionar la tabla `inventarios`.

## Ejecución

Configurar la variable de entorno `DB_PASSWORD` con la contraseña de MySQL y ejecutar:

```powershell
.\mvnw.cmd spring-boot:run
```

## Compilación

```powershell
.\mvnw.cmd clean package
```

## Estructura del proyecto

- `model`: entidades de la base de datos.
- `repository`: acceso a datos mediante JPA.
- `service`: lógica de negocio.
- `controller`: endpoints REST.

## Proyecto académico

Este microservicio forma parte de una arquitectura de ocho microservicios para una tienda de componentes de PC.