CREATE TABLE empresa (
    cuit VARCHAR(20) PRIMARY KEY,
    razon_social VARCHAR(255) NOT NULL,
    fecha_adhesion DATE NOT NULL
);

CREATE TABLE transferencia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cuit_empresa VARCHAR(20) NOT NULL,
    importe DECIMAL(15,2) NOT NULL,
    cuenta_debito VARCHAR(50) NOT NULL,
    cuenta_credito VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL,
    FOREIGN KEY (cuit_empresa) REFERENCES empresa(cuit)
);