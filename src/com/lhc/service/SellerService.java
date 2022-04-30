package com.lhc.service;

import com.lhc.pojo.Page;
import com.lhc.pojo.Seller;

import java.util.List;

public interface SellerService {

    public void register(Seller seller);
    public Seller login(Seller seller);
    public boolean isNameUsed(String name);

    List<Seller> queryForAll();

    Seller querySellerById(int sellerId);

    public void update(Seller seller);

    void delete(int sellerId);

    Page<Seller> page(int pageNo, int pageSize);

    Page<Seller> pageByName(int pageNo, int pageSize, String sellerName);
}
