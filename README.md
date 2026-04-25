# Hospital V&M

- [Hospital V\&M](#hospital-vm)
  - [Descripción](#descripción)
  - [Aprendizajes esperados](#aprendizajes-esperados)
    - [Desafíos](#desafíos)
  - [Prerrequisitos](#prerrequisitos)
    - [Servicios de infraestructura](#servicios-de-infraestructura)
    - [Configuración de Loki en Grafana](#configuración-de-loki-en-grafana)
    - [Microservicios](#microservicios)
  - [Autor](#autor)
  - [Licencia](#licencia)

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

## Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **Java 17** o superior — [Descargar](https://www.oracle.com/java/technologies/downloads/)
- **Docker Desktop** — [Descargar](https://www.docker.com/products/docker-desktop/)

### Servicios de infraestructura

Los servicios de base de datos y observabilidad se levantan con `Docker Compose`. Desde la raíz del proyecto ejecuta:

```bash
docker compose up -d
```

Esto levanta los siguientes contenedores:

| Contenedor | Descripción            | Puerto |
|:-----------|:-----------------------|:-------|
| `mysql`    | Base de datos MySQL 8  | `3306` |
| `loki`     | Centralización de logs | `3100` |
| `grafana`  | Visualización de logs  | `3000` |

Grafana está disponible en [http://localhost:3000](http://localhost:3000) con las credenciales `admin / admin`.

Para detener los contenedores de Docker manteniendo los datos:

```bash
docker compose down
```

Si además deseas eliminar los volúmenes (esto borrará todos los datos de la base de datos y los logs almacenados):

```bash
docker compose down -v
```

### Configuración de Loki en Grafana

Para visualizar los logs en Grafana, conecta Loki como fuente de datos:

1. Accede a [http://localhost:3000](http://localhost:3000) con `admin / admin`
2. Ve a **Connections → Data sources → Add data source**
3. Selecciona **Loki**
4. En la URL ingresa `http://loki:3100`
5. Haz clic en **Save & test**

Una vez configurado, ve a **Explore**, selecciona **Loki** como fuente y usa la siguiente consulta para ver los logs:

```logql
{app="pacientes"}
{app="atenciones"}
```

### Microservicios

Cada microservicio debe ejecutarse de forma independiente. Desde la carpeta de cada uno:

```bash
./mvnw spring-boot:run
```

| Microservicio | Puerto |
|:--------------|:-------|
| `pacientes`   | `8081` |
| `atenciones`  | `8082` |

Los microservicios se detienen con `Ctrl + C` en la terminal donde están ejecutándose.

## Autor

- José Miguel Candia - [Correo](mailto:jo.candiah@profesor.duoc.cl) | [GitHub](https://www.github.com/jmcandia)

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)
