package com.lhc.service;

import com.lhc.pojo.UserCategory;

import java.util.List;

public interface UserCategoryService {

    public void addCategory(UserCategory userCategory);
    public Integer[] getMax(UserCategory userCategory);

    UserCategory queryById(Integer categoryId);

    UserCategory queryByAll(UserCategory userCategory);

    void setCategoryId(Integer categoryId,Integer userId);

    List<UserCategory> queryForAll();
}
