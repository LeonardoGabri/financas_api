CREATE TABLE parcelas (
    id varchar(255) primary key,
    mes_referencia int,
    ano_referencia int,
    compra_id varchar(255),
    responsavel varchar(255),
    responsavel_nome varchar(255),
    valor_parcelado decimal not null default 0.0,
    parcela_atual int
);