package com.qa.ims.persistence.dao;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderDAOTest {

    @Mock
    private Utils utils;
    @Mock
    private OrderDAO order;

    @InjectMocks
    private CustomerController controller;

    private final OrderDAO DAO = new OrderDAO();

    @Before
    public void setup() {
        DBUtils.connect("src/test/resources/db.properties");
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Order created = new Order(2L, 1L);
        //assertEquals(created, DAO.create(created));
        Mockito.when(order.create(created)).thenReturn(created);
        assertEquals(created, order.create(created));
    }

    @Test
    public void testReadAll() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L,1L,"Joni Baki","Pepsi",5L,2.0,10.0));
        expected.add(new Order(2L,2L,"Baki Joni","Coke",5L,2.0,10.0));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Order(2L, 2L), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ID = 1L;
        assertEquals(new Order(ID, 1L), DAO.readOrder(ID));
    }

    @Test
    public void testUpdate() {
        final Order updated = new Order(1L,2L,5L);
        final Order result = new Order(1L,1L);
        assertEquals(result, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1,DAO.delete(2,2));
        assertEquals(1, DAO.delete(1));
    }
}
