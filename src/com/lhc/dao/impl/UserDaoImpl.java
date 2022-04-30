package com.lhc.dao.impl;

import com.lhc.dao.UserDao;
import com.lhc.pojo.User;

import java.sql.Connection;

public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     *
     * 根据用户输入判断用户名是否已用
     * @param username 用户输入
     * @return 返回null说明没有用过，用户名可用。否则用户名已存在
     */
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email`,`category_id`categoryId from user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    /**
     * 检查用户账户和密码是否正确
     * @param username 用户输入
     * @param password 用户输入
     * @return 返回null说明账户或密码错误。否则存在该用户（账号密码正确）
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email`,`category_id`categoryId from user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    /**
     * 创建用户，保存数据到数据库
     * @param user 用户对象
     * @return 如果返回-1就说明更新失败
     */
    @Override
    public int saveUser(User user) {
        String sql = "insert into user(`username`,`password`,`email`,`category_id`) values (?,?,?,?);";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getCategoryId());
    }

    @Override
    public User queryUserById(Integer id) {
        String sql = "select `id`,`username`,`password`,`email`,`category_id`categoryId from user where id = ?";
        return queryForOne(User.class, sql, id);
    }

    @Override
    public int setCategoryId(Integer categoryId, Integer userId) {
        String sql = "update user set `category_id` = ? where id = ?";
        return update(sql,categoryId,userId);
    }
}
