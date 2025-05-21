CREATE TABLE IF NOT EXISTS pagamento_condominio (
    id INT PRIMARY KEY,
    valor DECIMAL(10, 2) NOT NULL,
    data_pagamento TIMESTAMP NOT NULL,
    tipo_pagamento VARCHAR(20) NOT NULL,
    status_pagamento VARCHAR(20) NOT NULL,
    morador_id INT NOT NULL,
    FOREIGN KEY (morador_id) REFERENCES morador(id)
);
