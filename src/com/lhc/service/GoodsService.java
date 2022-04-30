package com.lhc.service;

import com.lhc.pojo.Goods;
import com.lhc.pojo.Page;

import java.util.List;

public interface GoodsService {
    public void addGoods(Goods goods);
    public void deleteGoods(Integer id);
    public void updateGoods(Goods goods);
    public Goods queryGoodsById(Integer id);
    public List<Goods> queryGoods();

    Page<Goods> page(int pageNo, int pageSize);

    Page<Goods> pageByPrice(int pageNo, int pageSize, int min, int max);

    Page<Goods> pageByName(int pageNo, int pageSize, String goods_name);

    Page<Goods> pageBySellerId(int pageNo, int pageSize, int sellerId);

    Page<Goods> pageBySellerIdAndGoodsName(int pageNo, int pageSize, int sellerId, String goodsName);

    List<Goods> queryGoodsByNumberAndCategory(Integer max, int i);
}
