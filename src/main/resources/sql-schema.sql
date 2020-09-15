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
    `price` DOUBLE NULL DEFAULT NULL,
    PRIMARY KEY (`pId`)
);
CREATE TABLE IF NOT EXISTS `user_inventory_system`.`orders` (
    `orderId` INT(11) NOT NULL AUTO_INCREMENT,
    `customerId` INT(11) NOT NULL,
    `pId` INT(11) NOT NULL,
    PRIMARY KEY (`orderId`),
    foreign key (`customerId`) references customers (`id`),
    foreign key (`pId`) references products (`pId`)
);
CREATE TABLE IF NOT EXISTS `user_inventory_system`.`orderItems` (
    `orderId` INT(11) NOT NULL,
    `pId` INT(11),
    `quantity` INT(11),
    foreign key (`orderId`) references orders (`orderId`),
    foreign key (`pId`) references products (`pId`)
);