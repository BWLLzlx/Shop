package com.lhc.test;

import com.lhc.pojo.Operation;
import com.lhc.service.OperationService;
import com.lhc.service.impl.OperationServiceImpl;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class OperationServiceTest {

    OperationService operationService = new OperationServiceImpl();

    @Test
    public void addOperation() {
        operationService.addOperation(new Operation(null,"ip2","date","role2",2,"action2","target2"));
    }
}