package com.lhc.service.impl;

import com.lhc.dao.LoginInfoDao;
import com.lhc.dao.impl.LoginInfoDaoImpl;
import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Page;
import com.lhc.pojo.Seller;
import com.lhc.service.LoginInfoService;

import java.util.List;

public class LoginInfoServiceImpl implements LoginInfoService {

    private LoginInfoDao loginInfoDao = new LoginInfoDaoImpl();

    @Override
    public void addRecord(LoginInfo loginInfo) {
        loginInfoDao.addRecord(loginInfo);
    }

    @Override
    public List<LoginInfo> queryForLoginInfo() {
        return loginInfoDao.queryForLoginInfo();
    }

    @Override
    public Page<LoginInfo> page(int pageNo, int pageSize) {
        Page<LoginInfo> page = new Page<LoginInfo>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount = loginInfoDao.queryForTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<LoginInfo> items = loginInfoDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<LoginInfo> pageByRole(int pageNo, int pageSize, String role) {
        Page<LoginInfo> page = new Page<LoginInfo>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount = loginInfoDao.queryForTotalCountByRole(role);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<LoginInfo> items = loginInfoDao.queryForPageItemsByRole(begin,pageSize,role);
        page.setItems(items);

        return page;
    }
}
