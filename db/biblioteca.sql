create database biblioteca;

use biblioteca;

create table filmes (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255),
	categoria VARCHAR(255),
	sinopse VARCHAR(255),
    duracao VARCHAR(255),
	PRIMARY KEY (id)
);

create table series (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255),
	categoria VARCHAR(255),
	sinopse VARCHAR(255),
    quantidade_temporadas int,
	PRIMARY KEY (id)
);
