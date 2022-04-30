package com.lhc.service.impl;

import com.lhc.dao.GoodsDao;
import com.lhc.dao.impl.GoodsDaoImpl;
import com.lhc.pojo.Goods;
import com.lhc.pojo.Page;
import com.lhc.service.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public void addGoods(Goods goods) {
        goodsDao.addGoods(goods);
    }

    @Override
    public void deleteGoods(Integer id) {
        goodsDao.deleteGoods(id);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsDao.updateGoods(goods);
    }

    @Override
    public Goods queryGoodsById(Integer id) {
        return goodsDao.queryGoodsById(id);
    }

    @Override
    public List<Goods> queryGoods() {
        return goodsDao.queryGoods();
    }

    @Override
    public Page<Goods> page(int pageNo, int pageSize) {
        Page<Goods> page = new Page<Goods>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount = goodsDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<Goods> items = goodsDao.queryForPageItems(begin, pageSize);
        for (Goods item : items) {
//            System.out.println("图片路径原为"+item.getImgPath());
            String path = goodsDao.queryForImgPath(item.getId());
            item.setImgPath(path);
//            System.out.println("图片路径为:"+path);
        }
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Goods> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Goods> page = new Page<Goods>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount =  goodsDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<Goods> items = goodsDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        for (Goods item : items) {
//            System.out.println("图片路径原为"+item.getImgPath());
            String path = goodsDao.queryForImgPath(item.getId());
            item.setImgPath(path);
//            System.out.println("图片路径为:"+path);
        }
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Goods> pageByName(int pageNo, int pageSize, String goods_name) {
        Page<Goods> page = new Page<Goods>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Integer pageTotalCount =  goodsDao.queryForPageTotalCountByName(goods_name);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        int begin = (pageNo-1)*pageSize;
        List<Goods> items = goodsDao.queryForPageItemsByName(begin, pageSize, goods_name);
        for (Goods item : items) {
//            System.out.println("图片路径原为"+item.getImgPath());
            String path = goodsDao.queryForImgPath(item.getId());
            if(path!=null && !"".equals(path)){
                item.setImgPath(path);
            }else {
                item.setImgPath("static/img/1.png");
            }
//            System.out.println("图片路径为:"+path);
        }
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Goods> pageBySellerId(int pageNo, int pageSize, int sellerId) {
        Page<Goods> page = new Page<Goods>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Integer pageTotalCount =  goodsDao.queryForPageTotalCountBySellerId(sellerId);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        int begin = (pageNo-1)*pageSize;
        List<Goods> items = goodsDao.queryForPageItemsBySellerId(begin, pageSize, sellerId);
        for (Goods item : items) {
//            System.out.println("图片路径原为"+item.getImgPath());
            String path = goodsDao.queryForImgPath(item.getId());
            if(path!=null && !"".equals(path)){
                item.setImgPath(path);
            }else {
//                item.setImgPath("static/img/1.png");
            }
//            System.out.println("图片路径为:"+path);
        }
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Goods> pageBySellerIdAndGoodsName(int pageNo, int pageSize, int sellerId, String goodsName) {
        Page<Goods> page = new Page<Goods>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Integer pageTotalCount =  goodsDao.queryForPageTotalCountBySellerIdAndGoodsName(sellerId,goodsName);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);
        int begin = (pageNo-1)*pageSize;
        List<Goods> items = goodsDao.queryForPageItemsBySellerIdAndGoodsName(begin, pageSize, sellerId,goodsName);
        for (Goods item : items) {
//            System.out.println("图片路径原为"+item.getImgPath());
            String path = goodsDao.queryForImgPath(item.getId());
            if(path!=null && !"".equals(path)){
                item.setImgPath(path);
            }else {
//                item.setImgPath("static/img/1.png");
            }
//            System.out.println("图片路径为:"+path);
        }
        page.setItems(items);
        return page;
    }

    @Override
    public List<Goods> queryGoodsByNumberAndCategory(Integer max, int i) {
        return goodsDao.queryGoodsByNumberAndCategory(max,i);
    }

}
