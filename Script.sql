CREATE USER 'sa'@'localhost' IDENTIFIED BY '*****';
GRANT CREATE, INSERT, UPDATE, DELETE, SELECT, FILE, EXECUTE ON *.* TO 'sa'@'localhost' WITH GRANT OPTION;

CREATE DATABASE db_store;
USE db_store;

CREATE TABLE `db_store`.`audits` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `action` VARCHAR(150) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `db_store`.`types` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `db_store`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(150) NOT NULL,
  `price` VARCHAR(150) NOT NULL,
  `expire` DATE NOT NULL,
  `type` INT NOT NULL,
  `active` BIT NOT NULL,
  `image` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_products__types` FOREIGN KEY (`type`) REFERENCES `db_store`.`types`(`id`)
);

INSERT INTO `db_store`.`types` (`name`)
VALUES ('Instruments');
INSERT INTO `db_store`.`products` (`name`,`price`,`expire`,`type`,`active`)
VALUES ('Guitar Ibanez', 3400000.00, NOW(), 1, 1);

SELECT * FROM `db_store`.`audits`;
SELECT * FROM `db_store`.`types`;
SELECT * FROM `db_store`.`products`;