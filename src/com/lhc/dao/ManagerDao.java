package com.lhc.dao;

import com.lhc.pojo.Manager;

import java.util.List;

public interface ManagerDao {
    public Manager login(String name,String password);
}
