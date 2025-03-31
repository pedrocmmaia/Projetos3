CREATE TABLE Sindico (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    apartamento_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (apartamento_id) REFERENCES Apartment(id) ON DELETE CASCADE
);
