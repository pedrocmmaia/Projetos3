CREATE TABLE IF NOT EXISTS ocorrencia (
    id SERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    morador_id INT NOT NULL,
    status_ocorrencia VARCHAR(20) CHECK (status_ocorrencia IN ('ABERTO', 'EM_ANDAMENTO', 'RESOLVIDO')) NOT NULL DEFAULT 'ABERTO',
    tipo_ocorrencia VARCHAR(20) CHECK (tipo_ocorrencia IN ('MANUTENÇÃO', 'RECLAMAÇÃO', 'OUTRO')) NOT NULL,
    CONSTRAINT fk_morador FOREIGN KEY (morador_id) REFERENCES morador(id)
);
