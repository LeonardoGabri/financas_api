CREATE TABLE compra (
    id VARCHAR(255) PRIMARY KEY,
    banco VARCHAR(255),
    banco_nome VARCHAR(255),
    fornecedor VARCHAR(255),
    fornecedor_nome VARCHAR(255),
    descricao VARCHAR(255),
    data_compra DATE DEFAULT CURRENT_DATE,
    subgrupo VARCHAR(255),
    subgrupo_nome VARCHAR(255),
    mes_inicio_cobranca INT,
    ano_inicio_cobranca INT,
    parcelas INT,
    valor DECIMAL NOT NULL DEFAULT 0.0,
    aliquota_imposta DECIMAL NOT NULL DEFAULT 0.0,
    tipo_compra VARCHAR(255)
);

CREATE TABLE responsavel_compra (
    compra_id varchar(255) not null,
    id varchar(255) not null,
    responsavel varchar(255) not null,
    percentual decimal,
    PRIMARY KEY (compra_id, id)
);