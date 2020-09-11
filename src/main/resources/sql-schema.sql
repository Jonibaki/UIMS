drop schema user_inventory_system;
CREATE SCHEMA IF NOT EXISTS `user_inventory_system`;
USE `user_inventory_system` ;
CREATE TABLE IF NOT EXISTS `user_inventory_system`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
