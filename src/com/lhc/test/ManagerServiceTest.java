package com.lhc.test;

import com.lhc.pojo.Manager;
import com.lhc.service.ManagerService;
import com.lhc.service.impl.ManagerServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerServiceTest {

    ManagerService managerService = new ManagerServiceImpl();

    @Test
    public void login() {
        if (managerService.login(new Manager(null,"admin", "admin"))==null){
            System.out.println("密码错误");
        }else{
            System.out.println("登陆成功");
        }
    }
}