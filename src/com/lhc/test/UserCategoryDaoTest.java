package com.lhc.test;

import com.lhc.dao.UserCategoryDao;
import com.lhc.dao.impl.UserCategoryDaoImpl;
import com.lhc.pojo.UserCategory;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCategoryDaoTest {

    UserCategoryDao userCategoryDao = new UserCategoryDaoImpl();

    @Test
    public void addCategory() {
        userCategoryDao.addCategory(new UserCategory(null,5,5,5,5,5,5));
    }

    @Test
    public void getMax() {
        for (Integer max : userCategoryDao.getMax(new UserCategory(null, 1, 2, 3, 4, 5, 5))) {
            System.out.println(max);
        }
    }

    @Test
    public void queryById() {
        System.out.println(userCategoryDao.queryById(3));
    }

    @Test
    public void queryByAll() {
        System.out.println(userCategoryDao.queryByAll(new UserCategory(null,0,5,5,5,5,5)));
    }

    @Test
    public void setCategoryId(){
        userCategoryDao.setCategoryId(3,5);
    }

    @Test
    public void queryForAll() {
        for (UserCategory userCategory : userCategoryDao.queryForAll()) {
            System.out.println(userCategory);
        }
    }
}