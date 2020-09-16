INSERT INTO `user_inventory_system`.`customers` (`first_name`, `surname`) VALUES ('Joni', 'Baki');
INSERT INTO `user_inventory_system`.`customers` (`first_name`, `surname`) VALUES ('James', 'Bond');
INSERT INTO `user_inventory_system`.`customers` (`first_name`, `surname`) VALUES ('Jason', 'Stratum');
INSERT INTO `user_inventory_system`.`customers` (`first_name`, `surname`) VALUES ('Dwayne', 'Johnson');



INSERT INTO `user_inventory_system`.`products` (`product_name`, `category`,`price`) VALUES ('Pepsi', 'Drinks', 2.0);
INSERT INTO `user_inventory_system`.`products` (`product_name`, `category`,`price`) VALUES ('Coke', 'Drinks', 5.0);
INSERT INTO `user_inventory_system`.`products` (`product_name`, `category`,`price`) VALUES ('Laptop', 'Tech', 1000.0);

INSERT INTO `user_inventory_system`.`orders` (`customerId`) VALUES (1);
INSERT INTO `user_inventory_system`.`orders` (`customerId`) VALUES (2);
INSERT INTO `user_inventory_system`.`orders` (`customerId`) VALUES (3);


INSERT INTO `user_inventory_system`.`orderItems` (`orderId`, `pId`, `quantity`) VALUES (1,2,10);
INSERT INTO `user_inventory_system`.`orderItems` (`orderId`, `pId`, `quantity`) VALUES (1,3,5);
INSERT INTO `user_inventory_system`.`orderItems` (`orderId`, `pId`, `quantity`) VALUES (2,3,1);
INSERT INTO `user_inventory_system`.`orderItems` (`orderId`, `pId`, `quantity`) VALUES (3,3,2);
INSERT INTO `user_inventory_system`.`orderItems` (`orderId`, `pId`, `quantity`) VALUES (3,2,2);




