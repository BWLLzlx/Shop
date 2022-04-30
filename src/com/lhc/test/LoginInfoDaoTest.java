package com.lhc.test;

import com.lhc.dao.LoginInfoDao;
import com.lhc.dao.impl.LoginInfoDaoImpl;
import com.lhc.pojo.LoginInfo;
import org.junit.Test;

public class LoginInfoDaoTest {

    LoginInfoDao loginInfoDao = new LoginInfoDaoImpl();

    @Test
    public void addRecord() {
        loginInfoDao.addRecord(new LoginInfo(null,"125.216.249.28","测试时间","测试地址","登录","用户",1));
    }

    @Test
    public void queryForLoginInfo() {
        for (LoginInfo loginInfo : loginInfoDao.queryForLoginInfo()) {
            System.out.println(loginInfo);
        }
    }

    @Test
    public void queryForTotalCount(){
        System.out.println(loginInfoDao.queryForTotalCount());
    }

    @Test
    public void queryForPageItems(){
        for (LoginInfo loginInfo : loginInfoDao.queryForPageItems(0, 8)) {
            System.out.println(loginInfo);
        }
    }

    @Test
    public void queryForPageItemsLike(){
        for (LoginInfo loginInfo : loginInfoDao.queryForPageItemsLike(0, 8,"")) {
            System.out.println(loginInfo);
        }
    }
}