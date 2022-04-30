package com.lhc.dao;

import com.lhc.pojo.UserCategory;

import java.util.List;

public interface UserCategoryDao {

    public int addCategory(UserCategory userCategory);
    public Integer[] getMax(UserCategory userCategory);

    UserCategory queryById(Integer categoryId);

    UserCategory queryByAll(UserCategory userCategory);

    public void setCategoryId(Integer categoryId,Integer userId);

    List<UserCategory> queryForAll();


}
