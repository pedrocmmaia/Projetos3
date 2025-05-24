CREATE TABLE IF NOT EXISTS pagamento (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    data_vencimento DATE NOT NULL
);
