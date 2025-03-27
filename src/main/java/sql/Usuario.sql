CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NULL,
    tipo_usuario VARCHAR(20) CHECK (tipo_usuario IN ('Síndico', 'Morador')) NOT NULL
);
