CREATE TABLE pagamento (
    id SERIAL PRIMARY KEY,
    apartamento_id INT NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('Condominal', 'IPTU', 'Extra')),
    valor NUMERIC(10, 2) NOT NULL,
    data_vencimento TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('Pago', 'Pendente', 'Atrasado'))
);
