package com.lhc.service.impl;

import com.lhc.dao.GoodsDao;
import com.lhc.dao.OrderDao;
import com.lhc.dao.OrderItemDao;
import com.lhc.dao.SellerDao;
import com.lhc.dao.impl.GoodsDaoImpl;
import com.lhc.dao.impl.OrderDaoImpl;
import com.lhc.dao.impl.OrderItemDaoImpl;
import com.lhc.dao.impl.SellerDaoImpl;
import com.lhc.pojo.*;
import com.lhc.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();
    SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public String addOrder(Cart cart, Integer userId) {
        //先创建订单
        //创建订单号orderId
        String orderId = System.currentTimeMillis()+""+userId;
        //创建订单对象，需要用到用户id和购物车
        Order order = new Order(orderId, new Date(),cart.getTotalPrice(),0, userId);
        //把订单对象添加到数据库
        orderDao.addOrder(order);

        //遍历购物车里面的商品项，创建订单项
        for (Map.Entry<Integer, CartItem>entry :cart.getItems().entrySet()){
            //创建CartItem购物车商品项
            CartItem cartItem = entry.getValue();
            //创建OrderItem订单项，根据购物车商品项的内容来创建,其中购物车商品项的商家id依靠点击添加时获取session
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),
                    cartItem.getPrice(),cartItem.getTotalPrice(),orderId,cartItem.getSellerId(),order.getCreateTime(),cartItem.getId(),userId);
            //把订单项加到数据库
            orderItemDao.addOrderItem(orderItem);

            //提交了订单之后，需要获取商品id找到数据库的商品，修改库存和销量，因为库存可能会有限，所以购物车的属性里面新增了一个stock
            Goods goods = goodsDao.queryGoodsById(cartItem.getId());
            goods.setSales(goods.getSales()+cartItem.getCount());
            goods.setStock(goods.getStock()-cartItem.getCount());
            goodsDao.updateGoods(goods);
            //额外：还需要把seller的salesVolume改了
            Seller seller = sellerDao.querySellerById(cartItem.getSellerId());
            Integer salesVolume = seller.getSalesVolume();
            Integer sellerId = seller.getId();
            System.out.println("原来的销售额："+salesVolume);
            salesVolume += cartItem.getTotalPrice();
            System.out.println("新的销售额："+salesVolume);
            sellerDao.updateSalesVolumeById(new Seller(sellerId,null,null,salesVolume));
        }

        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> queryForAll() {
        return orderDao.queryForAll();
    }

    @Override
    public List<Order> queryByUserId(Integer userId) {
        return orderDao.queryByUserId(userId);
    }

    @Override
    public List<OrderItem> queryForOrderItemByOrderId(String orderId) {
        return orderItemDao.queryForOrderItemByOrderId(orderId);
    }

    @Override
    public List<OrderItem> queryBySellerId(Integer sellerId) {
        return orderItemDao.queryBySellerId(sellerId);
    }

    @Override
    public Page<Order> page(int pageNo, int pageSize) {
        Page<Order> page = new Page<Order>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount = orderDao.queryForTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<Order> items = orderDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }
}
