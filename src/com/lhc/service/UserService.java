package com.lhc.service;

import com.lhc.pojo.User;

public interface UserService {


    /**
     * 注册用户
     * @param user 用户对象
     */
    public void register(User user);

    /**
     * 登录
     * @param user 返回用户对象说明成功，如果是null就登陆失败
     */
    public User login(User user);


    /**
     * 检查用户名是否可用
     * @param username 用户名
     * @return 返回true表示存在这个用户名，不可用
     */
    public boolean existUsername(String username);

    public User queryUserById(Integer id);

    void setCategoryId(Integer categoryId, Integer userId);
}
