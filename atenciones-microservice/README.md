# Microservicio `atenciones-microservice`

- [Microservicio `atenciones-microservice`](#microservicio-atenciones-microservice)
  - [Descripción](#descripción)
  - [Dependencias principales](#dependencias-principales)
  - [Componentes clave](#componentes-clave)
  - [Autor](#autor)
  - [Licencia](#licencia)

## Descripción

Este microservicio gestiona las atenciones médicas asociadas a los pacientes. Permite registrar y consultar las atenciones realizadas, incluyendo información como:

- Fecha y hora de atención
- Costo
- Médico responsable
- Comentarios clínicos

Este servicio está diseñado para relacionarse con el microservicio de pacientes mediante `WebClient`.

## Dependencias principales

- Spring Web
- Spring Security
- Spring Data JPA
- MySQL Driver
- Lombok
- Validation
- Flyway Migration
- Reactive HTTP Client

## Componentes clave

- `AtencionRepository`: Manejo de persistencia de atenciones.
- `AtencionService`: Lógica de negocio asociada a las atenciones.
- `AtencionController`: Endpoints REST para gestión de atenciones.

## Autor

- José Miguel Candia - [Correo](mailto:jo.candiah@profesor.duoc.cl) | [GitHub](https://www.github.com/jmcandia)

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)
