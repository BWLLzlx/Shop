package com.lhc.dao;

import com.lhc.pojo.Goods;

import java.util.List;

public interface GoodsDao {
    //实际要做的数据库操作
    public int addGoods(Goods goods);

    public int deleteGoods(Integer id);

    public int updateGoods(Goods goods);

    public Goods queryGoodsById(Integer id );

    //对标这个
    public List<Goods> queryGoods();

    Integer queryForPageTotalCount();

    List<Goods> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Goods> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

    Integer queryForPageTotalCountByName(String goods_name);

    List<Goods> queryForPageItemsByName(int begin, int pageSize, String goods_name);

    String queryForImgPath(Integer id);

    Integer queryForPageTotalCountBySellerId(int sellerId);

    List<Goods> queryForPageItemsBySellerId(int begin, int pageSize, int sellerId);

    Integer queryForPageTotalCountBySellerIdAndGoodsName(int sellerId, String goodsName);

    List<Goods> queryForPageItemsBySellerIdAndGoodsName(int begin, int pageSize, int sellerId, String goodsName);

    List<Goods> queryGoodsByNumberAndCategory(Integer max, int i);
}
