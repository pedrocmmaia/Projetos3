ALTER TABLE apartamento
ADD COLUMN morador_responsavel_id INTEGER,
ADD FOREIGN KEY (morador_responsavel_id) REFERENCES morador(id) ON DELETE SET NULL;
