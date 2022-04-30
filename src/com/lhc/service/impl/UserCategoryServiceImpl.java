package com.lhc.service.impl;

import com.lhc.dao.UserCategoryDao;
import com.lhc.dao.impl.UserCategoryDaoImpl;
import com.lhc.pojo.UserCategory;
import com.lhc.service.UserCategoryService;

import java.util.List;

public class UserCategoryServiceImpl implements UserCategoryService {

    UserCategoryDao userCategoryDao = new UserCategoryDaoImpl();

    @Override
    public void addCategory(UserCategory userCategory) {
        userCategoryDao.addCategory(userCategory);
    }

    @Override
    public Integer[] getMax(UserCategory userCategory) {
        return userCategoryDao.getMax(userCategory);
    }

    @Override
    public UserCategory queryById(Integer categoryId) {
        return userCategoryDao.queryById(categoryId);
    }

    @Override
    public UserCategory queryByAll(UserCategory userCategory) {
        return userCategoryDao.queryByAll(userCategory);
    }

    @Override
    public void setCategoryId(Integer categoryId,Integer userId) {
        userCategoryDao.setCategoryId(categoryId,userId);
    }

    @Override
    public List<UserCategory> queryForAll() {
        return userCategoryDao.queryForAll();
    }
}
