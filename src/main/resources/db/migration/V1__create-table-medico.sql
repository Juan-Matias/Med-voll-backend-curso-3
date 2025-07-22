CREATE TABLE medicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento VARCHAR(20) NOT NULL UNIQUE,
    especialidad VARCHAR(50) NOT NULL,
    calle VARCHAR(100),
    barrio VARCHAR(100),
    complemento VARCHAR(100),
    numero VARCHAR(10),
    codigo_postal VARCHAR(20),
    estado VARCHAR(50),
    ciudad VARCHAR(100),
    PRIMARY KEY (id)
);
