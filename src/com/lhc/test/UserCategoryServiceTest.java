package com.lhc.test;

import com.lhc.pojo.UserCategory;
import com.lhc.service.UserCategoryService;
import com.lhc.service.impl.UserCategoryServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCategoryServiceTest {

    UserCategoryService userCategoryService = new UserCategoryServiceImpl();

    @Test
    public void addCategory() {
        userCategoryService.addCategory(new UserCategory(null,5,5,5,0,0,0));
    }

    @Test
    public void getMax() {
        Integer[] max = userCategoryService.getMax(new UserCategory(null, 5, 5, 5, 0, 0, 0));
        for (int i = 0; i < max.length; i++) {
            System.out.println(max[i]);
        }
    }

    @Test
    public void queryById() {
        System.out.println(userCategoryService.queryById(2));
    }

    @Test
    public void queryByAll() {
        System.out.println(userCategoryService.queryByAll(new UserCategory(null,0,4,4,4,5,0)));
    }
}