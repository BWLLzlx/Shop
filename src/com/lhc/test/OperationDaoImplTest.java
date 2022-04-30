package com.lhc.test;

import com.lhc.dao.OperationDao;
import com.lhc.dao.impl.OperationDaoImpl;
import com.lhc.pojo.Operation;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class OperationDaoImplTest {

    OperationDao operationDao = new OperationDaoImpl();

    @Test
    public void addOperation() {
        operationDao.addOperation(new Operation(null,"ip","date","role",1,"action","target"));
    }

    @Test
    public void queryForTotalCount(){
        System.out.println(operationDao.queryForTotalCount());
    }

    @Test
    public void queryForPageItems(){
        for (Operation queryForPageItem : operationDao.queryForPageItems(0, 2)) {
            System.out.println(queryForPageItem);
        }
    }
}