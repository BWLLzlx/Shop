package com.lhc.service.impl;

import com.lhc.dao.ManagerDao;
import com.lhc.dao.impl.ManagerDaoImpl;
import com.lhc.pojo.Manager;
import com.lhc.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

    ManagerDao managerDao = new ManagerDaoImpl();

    @Override
    public Manager login(Manager manager) {
        return managerDao.login(manager.getName(), manager.getPassword());
    }
}
