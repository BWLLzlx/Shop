package com.lhc.dao.impl;

import com.lhc.dao.ManagerDao;
import com.lhc.pojo.Manager;

import java.util.List;

public class ManagerDaoImpl extends BaseDao implements ManagerDao {
    @Override
    public Manager login(String name, String password) {
        String sql = "select `id`,`name`,`password` from manager where name = ? and password = ?";
        return queryForOne(Manager.class, sql, name, password);
    }
}
