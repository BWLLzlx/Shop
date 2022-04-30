package com.lhc.service.impl;

import com.lhc.dao.OperationDao;
import com.lhc.dao.impl.OperationDaoImpl;
import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Operation;
import com.lhc.pojo.Page;
import com.lhc.service.OperationService;

import java.util.List;

public class OperationServiceImpl implements OperationService {

    OperationDao operationDao = new OperationDaoImpl();

    @Override
    public void addOperation(Operation operation) {
        operationDao.addOperation(operation);
    }

    @Override
    public Page<Operation> page(int pageNo, int pageSize) {
        Page<Operation> page = new Page<Operation>();

        page.setPageNo(pageNo);

        page.setPageSize(pageSize);

        Integer pageTotalCount = operationDao.queryForTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo-1)*pageSize;
        List<Operation> items = operationDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }
}
