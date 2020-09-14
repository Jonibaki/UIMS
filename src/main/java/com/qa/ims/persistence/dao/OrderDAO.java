package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("orderId");
        Long pId = resultSet.getLong("pId");
        Long customerId = resultSet.getLong("customerId");
        return new Order(id, pId, customerId);
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
             ResultSet resultSet = statement.executeQuery("select * from orders");) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
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
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
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
            statement.executeUpdate("INSERT INTO orders(pId, customerId) values('" + order.getProductId()
                    + "','" + order.getCustomerId() + "')");
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
            return modelFromResultSet(resultSet);
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
            statement.executeUpdate("update orders set pId ='" + order.getProductId() + "', customerId ='"
                    + order.getCustomerId() + "' where orderId =" + order.getId());
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
            return statement.executeUpdate("delete from orders where orderId = " + id);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}
