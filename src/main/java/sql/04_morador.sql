CREATE TABLE IF NOT EXISTS morador (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    apartamento_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (apartamento_id) REFERENCES apartamento(id) ON DELETE CASCADE
);
