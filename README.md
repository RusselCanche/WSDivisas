# WS Conversion - Spring Boot

Web Service para gestionar divisas y conversiones, con historial y consulta de tasas de cambio usando MySQL y la API externa [Exchangerate](https://www.exchangerate-api.com/).

## Tecnologías

- Java 17+
- Spring Boot 3
- MySQL
- JPA / Hibernate
- H2 (solo para pruebas, opcional)
- RestTemplate
- Postman (para probar endpoints)

## Instalación

1. Clona el repositorio:
```
git clone https://github.com/RusselCanche/WSDivisas.git
cd ws-conversion
```
2. Configura la base de datos MySQL en "application.properties":
```
spring.datasource.url=jdbc:mysql://localhost:3306/ws_conversion?useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
3. Crea la base de datos en MySQL:
```
CREATE DATABASE ws_conversion CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
4. Construye y ejecuta la aplicación:
```
mvn clean install
mvn spring-boot:run
```
## Endpoints

### Divisas

| Acción          | Método | URL                 | Body / Parámetros                              |
| --------------- | ------ | ------------------- | ---------------------------------------------- |
| Listar todas    | GET    | `/api/divisas`      | —                                              |
| Listar por id   | GET    | `/api/divisas/{id}` | —                                              |
| Crear           | POST   | `/api/divisas`      | `{ "code": "MXN", "name": "Peso Mexicano" }`   |
| Actualizar      | PUT    | `/api/divisas/{id}` | `{ "code": "MXN", "name": "Pesos Mexicanos" }` |
| Eliminar por id | DELETE | `/api/divisas/{id}` | —                                              |

### Conversiones

| Acción                                 | Método | URL                             | Parámetros           |
| -------------------------------------- | ------ | ------------------------------- | -------------------- |
| Buscar conversión de hoy               | GET    | `/conversion`                   | `from`, `to`         |
| Historial completo                     | GET    | `/conversion/historial`         | —                    |
| Buscar por divisas y fecha (histórico) | GET    | `/conversion/historial/buscar`  | `from`, `to`, `date` |
| Historial por divisas                  | GET    | `/conversion/historial/divisas` | `from`, `to`         |
| Historial por fecha                    | GET    | `/conversion/historial/fecha`   | `date`               |

Nota: Las conversiones se guardan solo si no existe un registro para el mismo par de divisas y fecha, de lo contrario se toma de la base de datos.

## Uso con Postman

Importa la colección "WS Conversion.postman_collection.json" para probar todos los endpoints.

## Consideraciones

- La API externa utilizada es Exchangerate, requiere API key (en la pagina puede conseguir una gratuita).
- MySQL debe estar corriendo y la base creada antes de arrancar la aplicación.
- La tabla Conversion tiene un índice único sobre (fromCurrency, toCurrency, date) para evitar duplicados.
- Para pruebas rápidas puedes usar H2 cambiando el spring.datasource en application.properties.
