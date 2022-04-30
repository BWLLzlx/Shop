package com.lhc.test;

import com.lhc.dao.OrderDao;
import com.lhc.dao.impl.OrderDaoImpl;
import com.lhc.pojo.Order;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void addOrder() {
        orderDao.addOrder(new Order("123456789",new Date(),1,1,1));
    }

    @Test
    public void queryForAll() {
        for (Order order : orderDao.queryForAll()) {
            System.out.println(order);
        }
    }

    @Test
    public void queryByUserId() {
        for (Order order : orderDao.queryByUserId(2)) {
            System.out.println(order);
        }
    }
}