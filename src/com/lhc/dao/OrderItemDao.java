package com.lhc.dao;

import com.lhc.pojo.Order;
import com.lhc.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int addOrderItem(OrderItem orderItem);
    List<OrderItem> queryForOrderItemByOrderId(String orderId);
    List<OrderItem> queryBySellerId(Integer sellerId);


}
