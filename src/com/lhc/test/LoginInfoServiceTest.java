package com.lhc.test;

import com.lhc.pojo.LoginInfo;
import com.lhc.service.LoginInfoService;
import com.lhc.service.impl.LoginInfoServiceImpl;
import org.junit.Test;

public class LoginInfoServiceTest {

    LoginInfoService loginInfoService = new LoginInfoServiceImpl();

    @Test
    public void addRecord() {
        loginInfoService.addRecord(new LoginInfo(null,"125.216.249.28","test时间","test地址","登录","用户",1));
    }

    @Test
    public void queryForLoginInfo() {
        for (LoginInfo loginInfo : loginInfoService.queryForLoginInfo()) {
            System.out.println(loginInfo);
        }
    }

    @Test
    public void page(){
        for (LoginInfo item : loginInfoService.page(1, 10).getItems()) {
            System.out.println(item);
        }
    }
}