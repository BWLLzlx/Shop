package com.lhc.service.impl;

import com.lhc.dao.UserDao;
import com.lhc.dao.impl.UserDaoImpl;
import com.lhc.pojo.User;
import com.lhc.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public void register(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){//用户名不存在
            return false;
        }else{
            return true;
        }
    }

    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    @Override
    public void setCategoryId(Integer categoryId, Integer userId) {
        userDao.setCategoryId(categoryId ,userId);
    }
}
