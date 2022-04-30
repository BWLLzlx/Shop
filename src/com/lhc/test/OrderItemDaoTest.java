package com.lhc.test;

import com.lhc.dao.OrderItemDao;
import com.lhc.dao.impl.OrderItemDaoImpl;
import com.lhc.pojo.OrderItem;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void addOrderItem() {
        orderItemDao.addOrderItem(new OrderItem(null,"1",1,1,1,"123456789",1, new Date(),1,1));
    }

    @Test
    public void queryForOrderItemByOrderId() {
        for (OrderItem orderItem : orderItemDao.queryForOrderItemByOrderId("123456789")) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void queryBySellerId(){
        for (OrderItem orderItem : orderItemDao.queryBySellerId(3)) {
            System.out.println(orderItem);
        }
    }

}