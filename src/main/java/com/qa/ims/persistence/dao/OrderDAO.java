package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();
    private Utils utils = new Utils();
    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("orderId");
        Long customerId = resultSet.getLong("customerId");
        return new Order(orderId, customerId);
    }
    public Order modelOne(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("orderId");
        Long pId = resultSet.getLong("pId");
        String customer = resultSet.getString("customer");
        String productName = resultSet.getString("product_name");
        Long quantity = resultSet.getLong("quantity");
        Double price = resultSet.getDouble("price");
        Double total = resultSet.getDouble("total");
        return new Order(orderId, pId, customer, productName, quantity,price,total);
    }

    /**
     * Reads all orders from the database
     *
     * @return A list of orders
     */
    @Override
    public List<Order> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             //ResultSet resultSet = statement.executeQuery("select * from orders");
             //ResultSet resultSet = statement.executeQuery("select orders.orderId,products.pId, product_name, price, quantity, quantity*price as total from orders, orderItems, products, customers where orders.orderId = orderItems.orderId and products.pId = orderItems.pId order by orders.orderId");
             ResultSet resultSet = statement.executeQuery("select orders.orderId, orderItems.pId, concat(customers.first_name,\" \", customers.surname) as customer, products.product_name, products.price, quantity,quantity*price as total from orders, orderItems, products, customers where orders.orderId = " +
                     "orderItems.orderId and products.pId = orderItems.pId and orders.customerId= customers.id order by orders.orderId;");
//             ResultSet resultSet = statement.executeQuery("select orders.orderId, products.pId, products.product_name, " +
//                     " orders.quantity, products.price from orders JOIN products ON orders.orderId= products.pId");
        )
       {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelOne(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orderId DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates a order in the database
     *
     * @param order - takes in a order object. id will be ignored
     */
    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
//            statement.executeUpdate("INSERT INTO orders(pId, quantity, customerId) values('" + order.getProductId()
//                    + "','" + order.getQuantity()+ "','"+ order.getCustomerId() + "')");
            statement.executeUpdate("INSERT INTO orders(customerId) values('"+order.getCustomerId() + "')");
            Order temp =readLatest();

            LOGGER.info(temp.getId());
            LOGGER.info("Please enter a product ID");
            Long pId = utils.getLong();
            LOGGER.info("Please enter a quantity ID");
            Long quantity = utils.getLong();

            statement.executeUpdate("INSERT INTO orderItems(orderId, pId, quantity) values('" + temp.getId()
                    + "','" + pId + "','" +quantity+"')");
//            statement.executeUpdate("INSERT INTO orderItems(orderId, pId, quantity, customerId) values('" + temp.getId()
//                    + "','" + temp.getProductId() + "','" +temp.getQuantity()+ "','" +temp.getCustomerId()+ "')");

            return temp;
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }
    public Order addItem(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("INSERT INTO orderItems(orderId, pId) values('" + order.getId()
                    + "','" + order.getProductId() + "')");
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order readOrder(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where orderId = " + id);) {
            resultSet.next();
            return modelOne(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Updates a order in the database
     *
     * @param order - takes in a order object, the id field will be used to
     *                 update that order in the database
     * @return
     */
    @Override
    public Order update(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("INSERT INTO orderItems(orderId, pId, quantity) values('" + order.getId()
                    + "','" + order.getProductId() + "','" +order.getQuantity()+"')");
            return readOrder(order.getId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a order in the database
     *
     * @param id - id of the order
     */
    @Override
    public int delete(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            return statement.executeUpdate("delete from orders where orderId in (select orderId from orderItems where orderId = "+ id+")");
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    //delete a specific item from the orderItem table
    public int delete(long oid, long pid) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            return statement.executeUpdate("delete from orderItems where orderId in (select orderId from orders where orderId = "+oid +
                    " and pId = "+pid+ ")");
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}
