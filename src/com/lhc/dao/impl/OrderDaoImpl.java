package com.lhc.dao.impl;

import com.lhc.dao.OrderDao;
import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int addOrder(Order order) {
        String sql = "insert into `order`(`id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryForAll() {
        String sql = "select `id`, `create_time` createTime, `price`, `status`, `user_id` userId from `order`";
        return queryForList(Order.class, sql);
    }

    @Override
    public List<Order> queryByUserId(Integer userId) {
        String sql = "select `id`, `create_time` createTime, `price`, `status`, `user_id` userId from `order` where user_id = ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public Integer queryForTotalCount() {
        String sql = "select count(*) from `order`";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Order> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`, `create_time` createTime, `price`, `status`, `user_id` userId from `order` limit ?,?;";
        return queryForList(Order.class, sql, begin, pageSize);
    }
}
