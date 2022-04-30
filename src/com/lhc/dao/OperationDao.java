package com.lhc.dao;

import com.lhc.pojo.Operation;

import java.util.List;

public interface OperationDao {
    int addOperation(Operation operation);

    Integer queryForTotalCount();

    List<Operation> queryForPageItems(int begin, int pageSize);
}
