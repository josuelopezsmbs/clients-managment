# 📦 Microservicio de Gestión de Clientes

Este proyecto implementa un microservicio backend en Java con Spring Boot y MySQL que permite el registro, consulta y
análisis de datos de clientes. Está diseñado siguiendo principios de arquitectura limpia, seguridad, escalabilidad y
buenas prácticas de desarrollo de software.

---

## 🔗 Demo en linea

- Swagger: [Swagger](http://seek-api-env.eba-78ixjbis.us-east-2.elasticbeanstalk.com/api/swagger-ui/index.html)

## 🚀 Funcionalidades

- Crear nuevos clientes con nombre, apellido, edad y fecha de nacimiento.
- Listar todos los clientes con cálculos derivados (ej. fecha estimada de fallecimiento, jubilación, días vividos,
  etc.).
- Consultar métricas agregadas (promedio de edad, desviación estándar).
- Seguridad basada en JWT.
- Documentación de API con Swagger.
- Validaciones y manejo de excepciones con códigos HTTP claros.
- Preparado para desplegar en la nube (AWS, GCP, Azure).
- Estructura modular siguiendo buenas prácticas (DTO, Service, Repository, Controller).

---

## 🧱 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security + JWT
- Flyway (migraciones de DB)
- MySQL
- Lombok
- Swagger (OpenAPI)
- Maven
- JUnit 5 / Mockito

---

## 📁 Estructura del proyecto

```
src/
├── config/ # Configuración de seguridad, JWT, CORS, etc.
├── controller/ # Endpoints REST
├── dto/ # Objetos de transferencia
├── model/ # Entidades JPA
├── mapper/ # mappers con mapstruct
├── repository/ # Interfaces Spring Data
├── service/ # Lógica de negocio
├── exception/ # Manejo global de errores
├── util/ # Clases de utilidades
└── ClientManagementApplication.java
```

---

## 🔐 Autenticación

El servicio implementa autenticación con JWT. Los endpoints protegidos requieren un token válido.

- Endpoint para login y obtención de token:  
  `POST /api/auth/login`

---

## 🧪 Pruebas

Incluye pruebas unitarias y de integración para los servicios y controladores principales.

```
mvn test
```

## 🧮 Base de Datos

La base de datos es MySQL. Las tablas se crean con migraciones Flyway (src/main/resources/db/migration).

Ejemplo de configuración (application.yml):

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/clients_db
    username: root
    password: your_password
  jpa:
    hibernate.ddl-auto: validate
    show-sql: true
  flyway.enabled: true
```

## ▶️ Ejecución local

Clona el repositorio:

```
git clone https://github.com/josuelopezsmbs/clients-managment.git
cd clients-managment
```

📓 Documentación API
Una vez iniciada la app, accede a Swagger UI:

```
http://localhost:5000/api/swagger-ui.html
```

## 🛠 Endpoints principales

```
POST /api/v1/clients → Crear cliente
GET /api/v1/clients → Listar clientes con estimaciones
GET /api/v1/clients/metrics → Obtener métricas (edad promedio, desviación estándar)
```

## 📡 Monitoreo y métricas

Incluye Spring Boot Actuator con endpoints como:

```
/actuator/health
/actuator/metrics
```

Puedes conectar con Prometheus y Grafana para visualización.
