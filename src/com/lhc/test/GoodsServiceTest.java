package com.lhc.test;

import com.lhc.pojo.Goods;
import com.lhc.pojo.Page;
import com.lhc.service.GoodsService;
import com.lhc.service.impl.GoodsServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GoodsServiceTest {

    private GoodsService goodsService = new GoodsServiceImpl();

    @Test
    public void addGoods() {
        goodsService.addGoods(new Goods(null, "99", 99, 99, 99,null,1,6));
    }

    @Test
    public void deleteGoods() {
        goodsService.deleteGoods(92);
    }

    @Test
    public void updateGoods() {
        goodsService.updateGoods(new Goods(89, "11", 11, 11, 11,null,1,6));
    }

    @Test
    public void queryGoodsById() {
        System.out.println(goodsService.queryGoodsById(1));
    }

    @Test
    public void queryGoods() {
        for (Goods queryGoods : goodsService.queryGoods()) {
            System.out.println(queryGoods);
        }
    }

    @Test
    public void page(){
        System.out.println(goodsService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice(){
        System.out.println(goodsService.pageByPrice(1, Page.PAGE_SIZE , 0, 10000));
    }

    @Test
    public void pageByName(){
        System.out.println(goodsService.pageByName(1, Page.PAGE_SIZE , "123"));
    }

    @Test
    public void pageBySellerId(){
        System.out.println(goodsService.pageBySellerId(1, Page.PAGE_SIZE , 2));
    }
}