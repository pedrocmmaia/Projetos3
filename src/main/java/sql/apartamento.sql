CREATE TABLE IF NOT EXISTS apartamento (
    id SERIAL PRIMARY KEY,
    numero INT NOT NULL,
    andar INT NOT NULL,
    bloco_id INT NOT NULL,
    CONSTRAINT fk_bloco FOREIGN KEY (bloco_id) REFERENCES bloco(id) ON DELETE CASCADE
);