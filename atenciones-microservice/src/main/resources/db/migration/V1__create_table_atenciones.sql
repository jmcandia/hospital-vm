CREATE TABLE atenciones (
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    fecha_atencion DATETIME       NOT NULL,
    costo          DECIMAL(10, 2) NOT NULL,
    comentario     VARCHAR(255)   NOT NULL,
    paciente_id    BIGINT         NOT NULL,
    CONSTRAINT pk_atenciones      PRIMARY KEY (id),
    CONSTRAINT uq_atenciones_fecha_atencion_paciente_id UNIQUE (fecha_atencion, paciente_id)
);