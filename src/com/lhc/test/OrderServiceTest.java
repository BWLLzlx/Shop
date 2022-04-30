package com.lhc.test;

import com.lhc.pojo.Cart;
import com.lhc.pojo.CartItem;
import com.lhc.pojo.Order;
import com.lhc.pojo.OrderItem;
import com.lhc.service.OrderService;
import com.lhc.service.impl.OrderServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderServiceTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void addOrder() {
        //创建购物车
        Cart cart = new Cart();
        //添加购物车商品项,id表示商品编号，name表示商品名称，count表示买多少个
        cart.addItem(new CartItem(97,"15",1,15,15,0,1));
        cart.addItem(new CartItem(98,"33",1,33,33,0,3));

        //现在购物车里面有两个商品项
        //需要传递的参数有购物车和userId

       System.out.println(orderService.addOrder(cart,2));
    }

    @Test
    public void queryForAll() {
        for (Order order : orderService.queryForAll()) {
            System.out.println(order);
        }
    }

    @Test
    public void queryByUserId() {
        for (Order order : orderService.queryByUserId(2)) {
            System.out.println(order);
        }
    }

    @Test
    public void queryForOrderItemByOrderId() {
        for (OrderItem orderItem : orderService.queryForOrderItemByOrderId("123456789")) {
            System.out.println(orderItem);
        }
    }
}