package com.lhc.test;

import com.lhc.dao.ManagerDao;
import com.lhc.dao.impl.ManagerDaoImpl;
import com.lhc.pojo.Manager;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerDaoTest {

    ManagerDao managerDao = new ManagerDaoImpl();

    @Test
    public void login(){
        System.out.println(managerDao.login("admin","admin"));
    }
}