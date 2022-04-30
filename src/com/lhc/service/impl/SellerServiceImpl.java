package com.lhc.service.impl;

import com.lhc.dao.SellerDao;
import com.lhc.dao.impl.SellerDaoImpl;
import com.lhc.pojo.Page;
import com.lhc.pojo.Seller;
import com.lhc.service.SellerService;

import java.util.List;

public class SellerServiceImpl implements SellerService {

    SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public void register(Seller seller) {
        sellerDao.register(seller);
    }

    @Override
    public Seller login(Seller seller) {
        return sellerDao.login(seller.getName(),seller.getPassword());
    }

    @Override
    public boolean isNameUsed(String name) {
        if(sellerDao.querySellerByName(name)==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Seller> queryForAll() {
        return sellerDao.queryForAll();
    }

    @Override
    public Seller querySellerById(int sellerId) {
        return sellerDao.querySellerById(sellerId);
    }

    @Override
    public void update(Seller seller) {
        sellerDao.update(seller);
    }

    @Override
    public void delete(int sellerId) {
        sellerDao.delete(sellerId);
    }

    @Override
    public Page<Seller> page(int pageNo, int pageSize) {
        Page<Seller> page = new Page<Seller>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount = sellerDao.queryForTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<Seller> items = sellerDao.queryForPageItems(begin,pageSize);
        page.setItems(items);


        return page;
    }

    @Override
    public Page<Seller> pageByName(int pageNo, int pageSize, String sellerName) {
        Page<Seller> page = new Page<Seller>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount = sellerDao.queryForTotalCountByName(sellerName);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<Seller> items = sellerDao.queryForPageItemsByName(begin,pageSize,sellerName);
        page.setItems(items);


        return page;
    }
}
