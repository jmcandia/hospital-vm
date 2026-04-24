# Microservicio `pacientes-microservice`

- [Microservicio `pacientes-microservice`](#microservicio-pacientes-microservice)
  - [Descripción](#descripción)
  - [Dependencias principales](#dependencias-principales)
  - [Componentes clave](#componentes-clave)
  - [Autor](#autor)
  - [Licencia](#licencia)

## Descripción

Este microservicio es responsable de la gestión de los pacientes del sistema. Permite realizar operaciones CRUD sobre la entidad `Paciente`, incluyendo:

- Registro de nuevos pacientes
- Consulta de pacientes (individual y listado)
- Actualización de datos
- Eliminación de registros

Además, define la estructura base del modelo de datos de pacientes, incluyendo atributos como RUN, nombres, apellidos, fecha de nacimiento y correo.

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

- `PacienteRepository`: Extiende `JpaRepository` para operaciones CRUD automáticas.
- `PacienteService`: Contiene la lógica de negocio y manejo transaccional.
- `PacienteController`: Expone endpoints REST para interactuar con el sistema.

## Autor

- José Miguel Candia - [Correo](mailto:jo.candiah@profesor.duoc.cl) | [GitHub](https://www.github.com/jmcandia)

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)
