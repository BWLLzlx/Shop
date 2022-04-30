package com.lhc.test;

import com.lhc.dao.SellerDao;
import com.lhc.dao.impl.SellerDaoImpl;
import com.lhc.pojo.Seller;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SellerDaoTest {

    SellerDao sellerDao = new SellerDaoImpl();

    @Test
    public void login() {
        System.out.println(sellerDao.login("1","1"));
    }

    @Test
    public void register() {
        System.out.println(sellerDao.register(new Seller(null,"3","3",0)));
    }

    @Test
    public void querySellerByName() {
        System.out.println(sellerDao.querySellerByName("123"));
    }

    @Test
    public void update() {
        sellerDao.update(new Seller(4,"444","444",0));
    }

    @Test
    public void delete() {
        sellerDao.delete(2);
    }

    @Test
    public void querySellerById() {
        System.out.println(sellerDao.querySellerById(1));
    }

    @Test
    public void queryForAll() {
        for (Seller seller : sellerDao.queryForAll()) {
            System.out.println(seller);
        }
    }

    @Test
    public void updateSalesVolumeById() {
        sellerDao.updateSalesVolumeById(new Seller(4,null,null,123));
    }

    @Test
    public void queryForTotalCount() {
        System.out.println(sellerDao.queryForTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Seller seller : sellerDao.queryForPageItems(2, 2)) {
            System.out.println(seller);
        }
    }
}