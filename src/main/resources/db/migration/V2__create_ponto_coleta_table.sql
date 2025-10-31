CREATE TABLE ponto_coleta (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome NVARCHAR(255) NOT NULL,
    endereco NVARCHAR(500) NOT NULL,
    horario_funcionamento NVARCHAR(255) NULL
);