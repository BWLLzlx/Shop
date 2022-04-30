package com.lhc.dao;

import com.lhc.pojo.Order;

import java.util.List;

public interface OrderDao {

    public int addOrder(Order order);
    List<Order> queryForAll();
    List<Order> queryByUserId(Integer userId);

    Integer queryForTotalCount();

    List<Order> queryForPageItems(int begin, int pageSize);
}
