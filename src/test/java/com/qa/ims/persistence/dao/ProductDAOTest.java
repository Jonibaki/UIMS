package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;

public class ProductDAOTest {

    private final ProductDAO DAO = new ProductDAO();

    @Before
    public void setup() {
        DBUtils.connect("src/test/resources/db.properties");
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Product created = new Product(2L, "Pepsi", "Drinks");
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product(1L, "Pepsi", "Drink"));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Product(1L, "Pepsi", "Drink"), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long ID = 1L;
        assertEquals(new Product(ID, "Pepsi", "Drink"), DAO.readProduct(ID));
    }

    @Test
    public void testUpdate() {
        final Product updated = new Product(1L, "Pepsi", "Drink");
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }
}
