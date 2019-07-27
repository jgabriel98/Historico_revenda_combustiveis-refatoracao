--CREATE DATABASE "banco_precos";

CREATE TABLE precos (
	id SERIAL,
	regiao TEXT NOT NULL,
	sigla_estado TEXT NOT NULL,
	sigla_municipio TEXT NOT NULL,
	revenda_instalacao TEXT NOT NULL,
	codigo_produto TEXT NOT NULL,
	nome_produto TEXT NOT NULL,
	unidade_de_medida TEXT NOT NULL,
	bandeira TEXT NOT NULL,
	valor_de_compra NUMERIC,
	valor_de_venda NUMERIC NOT NULL,
	data_da_coleta DATE NOT NULL,

    constraint valor_de_venda_nonnegative check (valor_de_venda >= 0)
);
