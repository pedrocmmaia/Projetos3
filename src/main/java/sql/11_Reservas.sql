CREATE TABLE IF NOT EXISTS reservas (
    id SERIAL PRIMARY KEY,
    morador_id INT NOT NULL,
    area_id INT NOT NULL,
    data_reserva DATE NOT NULL,
    status VARCHAR(20) NOT NULL,

    FOREIGN KEY (morador_id) REFERENCES morador(id) ON DELETE CASCADE,
    FOREIGN KEY (area_id) REFERENCES area_comum(id) ON DELETE CASCADE
);
