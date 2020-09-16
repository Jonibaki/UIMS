package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDAO orderDAO;
    private Utils utils;

    public OrderController(OrderDAO orderDAO, Utils utils) {
        super();
        this.orderDAO = orderDAO;
        this.utils = utils;
    }

    /**
     * Reads all orders to the logger
     */
    @Override
    public List<Order> readAll() {
        List<Order> orders = orderDAO.readAll();
        for (Order order : orders) {
            LOGGER.info(order.toString());
        }
        return orders;
    }

    /**
     * Creates a order by taking in user input
     */
    @Override
    public Order create() {
        LOGGER.info("Please enter a customer ID");
        Long customerId = utils.getLong();
        Order order = orderDAO.create(new Order(customerId));
        LOGGER.info("Order created");
        return order;
    }
    /**
     * Updates an existing order by taking in user input
     */
    @Override
    public Order update() {
        //TODO: require to update this section
        LOGGER.info("Please enter the id of the order you would like to update/add");
        Long id = utils.getLong();
        LOGGER.info("Please enter a product ID");
        Long pId = utils.getLong();
        LOGGER.info("Please enter a quantity");
        Long quantity = utils.getLong();

        return orderDAO.update(new Order(id,pId,quantity));
    }

    /**
     * Deletes an existing order by the id of the order
     *
     * @return
     */
    @Override
    public int delete() {
        LOGGER.info("Please type \"item\" to delete an item or \"order\" to delete an order");
        String decision =utils.getString();

        switch(decision){
            case "order":
                LOGGER.info("Please enter the ID of the order you would like to delete");
                Long id = utils.getLong();
                return orderDAO.delete(id);
            case "item":
                LOGGER.info("Please enter the ID of the order you would like to delete");
                Long oid = utils.getLong();
                LOGGER.info("Please enter the ID of the product you would like to delete");
                Long pid = utils.getLong();
                return orderDAO.delete(oid,pid);
            default:
                LOGGER.info("Invalid choice");
                break;
        }
        return  0;
    }

}
