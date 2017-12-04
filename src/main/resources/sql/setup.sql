-- ##########################
-- # autor: thiago-amm
-- # data: 04/12/2017
-- # versao: v1.0.0
-- ##########################

USE mysql;

DROP DATABASE IF EXISTS dao;
DROP USER IF EXISTS dao;

CREATE DATABASE IF NOT EXISTS dao DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
CREATE USER IF NOT EXISTS dao;

GRANT ALL PRIVILEGES ON dao.* TO 'dao'@'%' IDENTIFIED BY 'dao';

FLUSH PRIVILEGES;

USE dao;

CREATE TABLE pessoa (
   id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   sobrenome VARCHAR(50) NOT NULL,
   data_nascimento DATE NOT NULL
);