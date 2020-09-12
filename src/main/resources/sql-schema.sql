drop schema user_inventory_system;
CREATE SCHEMA IF NOT EXISTS `user_inventory_system`;
USE `user_inventory_system` ;

CREATE TABLE IF NOT EXISTS `user_inventory_system`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `user_inventory_system`.`products` (
    `pId` INT(11) NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(40) NULL DEFAULT NULL,
    `category` VARCHAR(40) NULL DEFAULT NULL,
    `price` INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`pId`)
);
CREATE TABLE IF NOT EXISTS `user_inventory_system`.`orders` (
    `orderId` INT(11) NOT NULL AUTO_INCREMENT,
    `customerID` INT (11),
    PRIMARY KEY (`orderId`)
);
