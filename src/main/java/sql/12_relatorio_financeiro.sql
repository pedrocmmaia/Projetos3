CREATE TABLE relatorio_financeiro (
    id SERIAL PRIMARY KEY,
    periodo_inicio TIMESTAMP NOT NULL,
    periodo_fim TIMESTAMP NOT NULL,
    total_arrecadado NUMERIC(10, 2) NOT NULL,
    inadimplencia NUMERIC(10, 2) NOT NULL
);
