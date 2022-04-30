package com.lhc.test;

import com.lhc.dao.UserDao;
import com.lhc.dao.impl.UserDaoImpl;
import com.lhc.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("777")==null){//输入123判断123是否存在
            System.out.println("用户名不存在！");
        }else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {//输入admin和admin判断是否正确
        if(userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("账号或密码错误");
        }else{
            System.out.println("账号密码正确");
        }
    }

    @Test
    public void saveUser() {
        if(userDao.saveUser(new User(null, "777", "777", "777@777.com", 1))!=-1){
            System.out.println("用户创建成功");
        }else{
            System.out.println("用户创建失败");
        }
    }

    @Test
    public void setCategoryId() {
        userDao.setCategoryId(2,1);
    }
}