USE mysql;

CREATE DATABASE audiovisual;

USE audiovisual;

CREATE TABLE teste (
   id BIGINT (20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(100)
);

CREATE TABLE usuario (
   id BIGINT (20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(100) NOT NULL,
   email VARCHAR(100) NOT NULL,
   telefone VARCHAR(100),
   celular VARCHAR(20) NOT NULL,
   tipo int(20) NOT NULL
);