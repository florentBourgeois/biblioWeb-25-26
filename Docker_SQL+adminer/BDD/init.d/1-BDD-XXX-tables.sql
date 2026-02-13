DROP DATABASE IF EXISTS XXX;
CREATE DATABASE IF NOT EXISTS XXX;
USE LPDAOO;

CREATE USER IF NOT EXISTS 'jackSQL'@'%' IDENTIFIED BY 'jack';
GRANT ALL PRIVILEGES ON LPDAOO . * TO 'jackSQL'@'%';


-- -----------------------------------------------------
-- create tables.  
-- -----------------------------------------------------
CREATE TABLE data (
	id SMALLINT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
)
Engine = INNODB;

