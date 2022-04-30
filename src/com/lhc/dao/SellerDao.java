package com.lhc.dao;

import com.lhc.pojo.Seller;

import java.util.List;

public interface SellerDao {

    public Seller login(String name, String password);
    public int register(Seller seller);
    public Seller querySellerByName(String name);
    public int update(Seller seller);
    public int delete(Integer sellerId);
    public Seller querySellerById(Integer id);
    List<Seller> queryForAll();
    public int updateSalesVolumeById(Seller seller);

    Integer queryForTotalCount();
    List<Seller> queryForPageItems(int begin, int pageSize);

    Integer queryForTotalCountByName(String sellerName);
    List<Seller> queryForPageItemsByName(int begin, int pageSize, String sellerName);
}
