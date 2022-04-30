package com.lhc.test;

import com.lhc.pojo.Page;
import com.lhc.pojo.Seller;
import com.lhc.service.SellerService;
import com.lhc.service.impl.SellerServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class SellerServiceTest {

    SellerService sellerService = new SellerServiceImpl();

    @Test
    public void register() {
        sellerService.register(new Seller(null,"4","4",0));
    }

    @Test
    public void login() {
        System.out.println(sellerService.login(new Seller(null,"4","4",123)));
    }

    @Test
    public void isNameUsed() {
        if (sellerService.isNameUsed("123")){
            System.out.println("已经被使用");
        }else{
            System.out.println("还没被使用");
        }
    }

    @Test
    public void queryForAll(){
        for (Seller seller : sellerService.queryForAll()) {
            System.out.println(seller);
        }
    }

    @Test
    public void delete(){
        sellerService.delete(7);
    }

    @Test
    public void page(){
        System.out.println(sellerService.page(1, 2));
    }

}