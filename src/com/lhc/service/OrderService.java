package com.lhc.service;

import com.lhc.pojo.Cart;
import com.lhc.pojo.Order;
import com.lhc.pojo.OrderItem;
import com.lhc.pojo.Page;

import java.util.List;

public interface OrderService {

    public String addOrder(Cart cart, Integer userId);

    public List<Order> queryForAll();

    public List<Order> queryByUserId(Integer userId);

    public List<OrderItem> queryForOrderItemByOrderId(String orderId);

    List<OrderItem> queryBySellerId(Integer sellerId);

    Page<Order> page(int pageNo, int pageSize);
}
