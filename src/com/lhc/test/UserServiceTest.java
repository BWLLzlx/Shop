package com.lhc.test;

import com.lhc.pojo.User;
import com.lhc.service.UserService;
import com.lhc.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void register() {
        userService.register(new User(null, "lhc", "lhc", "lhc@lhc.com",1));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "lhc", "lhc", "lhc@lhc.com",1)));
    }

    @Test
    public void existUsername() {
        if(userService.existUsername("abcde")){
            System.out.println("用户名不可用");
        }else{
            System.out.println("用户名可用");
        }
    }
}