-- Création de la base et de l'utilisateur --

DROP DATABASE IF EXISTS Training_sales;
CREATE DATABASE Training_sales;
USE Training_sales;

CREATE USER IF NOT EXISTS 'admin'@'localhost'
IDENTIFIED BY 'unbreakable_password';

GRANT ALL PRIVILEGES
ON training_sales.*
TO 'admin'@'localhost';

START TRANSACTION;

-- Création des tables --

DROP TABLE IF EXISTS
    `ts_training`,
    `ts_order`,
    `ts_training_order`,
    `ts_customer`,
    `ts_user`;

CREATE TABLE IF NOT EXISTS `ts_training` (
    `tr_id_training` INT NOT NULL AUTO_INCREMENT,
    `tr_name` VARCHAR(50) NOT NULL,
    `tr_description` TEXT NOT NULL,
    `tr_duration` INT NOT NULL,
    `tr_remote` BOOLEAN NOT NULL,
    `tr_price` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`tr_id_training`)
);

CREATE TABLE IF NOT EXISTS `ts_order` (
    `or_id_order` INT NOT NULL AUTO_INCREMENT,
    `or_date` DATE NOT NULL,
    `or_id_customer` INT NOT NULL,
    `or_id_user` INT NOT NULL,
    PRIMARY KEY (`or_id_order`)
);

CREATE TABLE IF NOT EXISTS `ts_training_order` (
    `to_id_training` INT NOT NULL,
    `to_id_order` INT NOT NULL,
    PRIMARY KEY (`to_id_training`, `to_id_order`)
);

CREATE TABLE IF NOT EXISTS `ts_customer` (
    `cu_id_customer` INT NOT NULL AUTO_INCREMENT,
    `cu_first_name` VARCHAR(50) NOT NULL,
    `cu_last_name` VARCHAR(50) NOT NULL,
    `cu_email` VARCHAR(100),
    `cu_address` VARCHAR(100),
    `cu_phone_number` VARCHAR(50),
    PRIMARY KEY (`cu_id_customer`)
);

CREATE TABLE IF NOT EXISTS `ts_user` (
    `us_id_user` INT NOT NULL AUTO_INCREMENT,
    `us_mail` VARCHAR(100) NOT NULL,
    `us_company` VARCHAR(100),
    `us_login` VARCHAR(50) NOT NULL,
    `us_password` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`us_id_user`)
);


-- Ajout des contraintes des clés étrangères --

ALTER TABLE `ts_order`
    ADD CONSTRAINT `ts_order_fk_1` FOREIGN KEY (`or_id_customer`) REFERENCES `ts_customer` (`cu_id_customer`) ON DELETE CASCADE,
    ADD CONSTRAINT `ts_order_fk_2` FOREIGN KEY (`or_id_user`) REFERENCES `ts_user` (`us_id_user`) ON DELETE CASCADE;


-- Insertion des données --

INSERT INTO `ts_training` (`tr_name`, `tr_description`, `tr_duration`, `tr_remote`, `tr_price`) VALUES
    ("Java", "Java SE 8 : Syntaxe & POO", 20, false, 899.99),
    ("Java avancé", "Exception, fichiers, Jdbc, thread...", 20, false, 1499.99),
    ("Spring", "Spring Core / MVC / Security", 20, false, 1299.99),
    ("PHP frameworks", "Une formation qui n'intéresse personne...", 15, true, 9.42),
    ("C#", "DotNet Core", 20, false, 1799.99),
    ("Blockchain", "Apprenez à écrire des smart contract avec solidity et devenez millionaire !", 3, true, 29999.99);

COMMIT;