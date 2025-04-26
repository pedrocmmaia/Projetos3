CREATE TYPE status_ocorrencia AS ENUM ('Aberto', 'Em Andamento', 'Resolvido');

CREATE TABLE ocorrencia (
    id SERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status status_ocorrencia NOT NULL DEFAULT 'Aberto',
    morador_id INT NOT NULL,
    CONSTRAINT fk_morador FOREIGN KEY (morador_id) REFERENCES morador(id)
);
