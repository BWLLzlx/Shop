package com.lhc.test;

import com.lhc.dao.GoodsDao;
import com.lhc.dao.impl.GoodsDaoImpl;
import com.lhc.pojo.Goods;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class GoodsDaoTest {

    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Test
    public void addGoods() {
        goodsDao.addGoods(new Goods(null, "13", 13, 13, 13,null,1,1));
    }

    @Test
    public void deleteGoods() {
        goodsDao.deleteGoods(111);
    }

    @Test
    public void updateGoods() {
        goodsDao.updateGoods(new Goods(100, "111",11, 11, 11,null,1,6));
    }

    @Test
    public void queryGoodsById() {
        System.out.println(goodsDao.queryGoodsById(102));
    }

    @Test
    public void queryGoods() {
        for (Goods queryGoods : goodsDao.queryGoods()) {
            System.out.println(queryGoods);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(goodsDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Goods goods : goodsDao.queryForPageItems(0, 4)) {
            System.out.println(goods);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(goodsDao.queryForPageTotalCountByPrice(300,400));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Goods goods : goodsDao.queryForPageItemsByPrice(0, 4, 0, 150)) {
            System.out.println(goods);
        }
    }
    @Test
    public void queryForPageTotalCountByName() {
        System.out.println(goodsDao.queryForPageTotalCountByName("12"));
    }

    @Test
    public void queryForPageItemsByName() {
        for (Goods goods : goodsDao.queryForPageItemsByName(0, 20, "12")) {
            System.out.println(goods);
        }
    }

    @Test
    public void queryForImgPath() {
        System.out.println(goodsDao.queryForImgPath(55));
    }

    @Test
    public void queryForPageTotalCountBySellerId() {
        System.out.println(goodsDao.queryForPageTotalCountBySellerId(2));
    }

    @Test
    public void queryForPageItemsBySellerId() {
        for (Goods goods : goodsDao.queryForPageItemsBySellerId(0, 8, 1)) {
            System.out.println(goods);
        }
    }

    @Test
    public void queryGoodsByNumberAndCategory() {
        for (Goods goods : goodsDao.queryGoodsByNumberAndCategory(2, 2)) {
            System.out.println(goods);
        }
    }
}