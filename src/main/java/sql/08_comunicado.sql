CREATE TABLE IF NOT EXISTS comunicados (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    data_envio TIMESTAMP NOT NULL
);
