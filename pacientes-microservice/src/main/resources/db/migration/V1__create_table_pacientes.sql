CREATE TABLE pacientes (
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    run              VARCHAR(15)  NOT NULL,
    nombres          VARCHAR(100) NOT NULL,
    apellidos        VARCHAR(100) NOT NULL,
    fecha_nacimiento DATETIME     NOT NULL,
    correo           VARCHAR(150) NOT NULL,
    CONSTRAINT pk_pacientes        PRIMARY KEY (id),
    CONSTRAINT uq_pacientes_run    UNIQUE (run)
);