CREATE TABLE usuarios (
    id              BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(20)  NOT NULL UNIQUE,
    password        VARCHAR(100) NOT NULL,
    nombre_completo VARCHAR(150) NOT NULL,
    correo          VARCHAR(75)  NOT NULL UNIQUE,
    telefono        VARCHAR(15)  NULL
);