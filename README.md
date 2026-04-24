# Hospital V&M

- [Hospital V\&M](#hospital-vm)
  - [Descripción](#descripción)
  - [Aprendizajes esperados](#aprendizajes-esperados)
    - [Desafíos](#desafíos)
  - [Autor](#autor)
  - [License](#license)

## Descripción

Este proyecto corresponde al desarrollo de una solución basada en microservicios para la gestión de pacientes y sus atenciones en el Hospital Vida y Meditación. La aplicación busca optimizar el seguimiento del estado de salud de los pacientes, mejorar la comunicación entre el personal médico y los pacientes, y sentar las bases para futuras integraciones (como una app móvil).

## Aprendizajes esperados

Al desarrollar este proyecto, se adquieren conocimientos clave en el diseño de arquitecturas basadas en microservicios utilizando Spring Boot, junto con buenas prácticas en persistencia de datos y desarrollo de APIs REST.

Entre los principales aprendizajes destacan:

- Uso de **Spring Boot** para la creación de servicios REST.
- Implementación de **Spring Data JPA** para operaciones CRUD sin necesidad de SQL manual.
- Configuración de bases de datos **MySQL** y su integración con aplicaciones Java.
- Diseño de modelos de datos utilizando anotaciones JPA como `@Entity`, `@Table`, `@Id`, y relaciones (`@OneToMany`, `@ManyToOne`, entre otras).
- Separación de responsabilidades mediante capas:
  - Repository (acceso a datos)
  - Service (lógica de negocio)
  - Controller (exposición de endpoints)
- Manejo de transacciones con `@Transactional` para garantizar consistencia de datos.
- Uso de `ResponseEntity` para respuestas HTTP más robustas y controladas.
- Diseño y prueba de endpoints REST (GET, POST, PUT, DELETE) usando herramientas como Postman.

### Desafíos

- **Modelado correcto de entidades y relaciones**, utilizando anotaciones JPA adecuadas.
- **Persistencia y consistencia de datos**, aplicando `@Transactional` para evitar inconsistencias.
- **Configuración de base de datos**, usando migraciones de SQL para controlar las versiones del esquema.
- **Separación de responsabilidades**, aplicación de arquitectura en capas para mantener código limpio y escalable.

## Autor

- José Miguel Candia - [Correo](mailto:jo.candiah@profesor.duoc.cl) | [GitHub](https://www.github.com/jmcandia)

## License

[MIT](https://choosealicense.com/licenses/mit/)
