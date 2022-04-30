package com.lhc.service;

import com.lhc.pojo.Operation;
import com.lhc.pojo.Page;

public interface OperationService {

    public void addOperation(Operation operation);

    Page<Operation> page(int pageNo, int pageSize);
}
