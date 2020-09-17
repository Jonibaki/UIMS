INSERT INTO `user_inventory_system`.`customers` (`first_name`, `surname`) VALUES ('Joni', 'Baki');
INSERT INTO `user_inventory_system`.`customers` (`first_name`, `surname`) VALUES ('Baki', 'Joni');

INSERT INTO `user_inventory_system`.`products` (`product_name`, `category`,`price`) VALUES ('Pepsi', 'Drink', 2.0);
INSERT INTO `user_inventory_system`.`products` (`product_name`, `category`,`price`) VALUES ('Coke', 'Drink', 2.0);

INSERT INTO `user_inventory_system`.`orders` (`customerId`) VALUES (1);
INSERT INTO `user_inventory_system`.`orders` (`customerId`) VALUES (2);

INSERT INTO `user_inventory_system`.`orderItems` (`orderId`, `pId`, `quantity`) VALUES (1,1,5);
INSERT INTO `user_inventory_system`.`orderItems` (`orderId`, `pId`, `quantity`) VALUES (2,2,5);