package com.lhc.dao.impl;

import com.lhc.dao.SellerDao;
import com.lhc.pojo.Seller;

import java.util.List;

public class SellerDaoImpl extends BaseDao implements SellerDao {
    @Override
    public Seller login(String name, String password) {
        String sql = "select `id`,`name`,`password`,`sales_volume` salesVolume from seller where name = ? and password = ?;";
        return queryForOne(Seller.class,sql,name,password);
    }

    @Override
    public int register(Seller seller) {
        String sql = "insert into seller(`name`,`password`,`sales_volume`) values(?,?,?);";
        return update(sql,seller.getName(),seller.getPassword(),seller.getSalesVolume());
    }

    @Override
    public Seller querySellerByName(String name) {
        String sql = "select `id`,`name`,`password`,`sales_volume` salesVolume from seller where name = ?;";
        return queryForOne(Seller.class,sql,name);
    }

    @Override
    public int update(Seller seller) {
        String sql = "update seller set `name`=?,`password`=?,`sales_volume`=? where id = ?;";
        return update(sql,seller.getName(),seller.getPassword(),seller.getSalesVolume(),seller.getId());
    }

    @Override
    public int delete(Integer sellerId) {
        String sql = "delete from seller where id=?;";
        return update(sql,sellerId);
    }

    @Override
    public Seller querySellerById(Integer id) {
        String sql = "select `id`,`name`,`password`,`sales_volume` salesVolume from seller where id = ?;";
        return queryForOne(Seller.class,sql,id);
    }

    @Override
    public List<Seller> queryForAll() {
        String sql = "select `id`,`name`,`password`,`sales_volume` salesVolume from seller;";
        return queryForList(Seller.class,sql);
    }

    @Override
    public int updateSalesVolumeById(Seller seller) {
        String sql = "update seller set `sales_volume`=? where id = ?;";
        return update(sql,seller.getSalesVolume(),seller.getId());
    }

    @Override
    public Integer queryForTotalCount() {
        String sql = "select count(*) from seller";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Seller> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`name`,`password`,`sales_volume` salesVolume from seller limit ?,?;";
//        for (Seller seller : queryForList(Seller.class, sql, begin, pageSize)) {
//            System.out.println(seller);
//        }
        return queryForList(Seller.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForTotalCountByName(String sellerName) {
        String sql = "select count(*) from seller where name = ?";
        Number count = (Number) queryForSingleValue(sql,sellerName);
        return count.intValue();
    }

    @Override
    public List<Seller> queryForPageItemsByName(int begin, int pageSize, String sellerName) {
        String sql = "select `id`,`name`,`password`,`sales_volume` salesVolume from seller where name = ? limit ?,?;";
        return queryForList(Seller.class, sql, sellerName, begin, pageSize);
    }


}
