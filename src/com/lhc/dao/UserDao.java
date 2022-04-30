package com.lhc.dao;

import com.lhc.pojo.User;

public interface UserDao {

    //实际要做的数据库操作
    /**
     * 根据用户输入判断用户名是否已用
     * @param username 用户输入
     * @return 返回null说明没有用过，用户名可用。否则用户名已存在
     */
    public User queryUserByUsername(String username);

    /**
     * 检查用户账户和密码是否正确
     * @param username 用户输入
     * @param password 用户输入
     * @return 返回null说明账户或密码错误。否则存在该用户（账号密码正确）
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 创建用户，保存数据到数据库
     * @param user 用户对象
     * @return 如果返回-1就说明更新失败
     */
    public int saveUser(User user);

    public User queryUserById(Integer id);

    public int setCategoryId(Integer categoryId ,Integer userId);
}
