package com.lhc.dao.impl;

import com.lhc.dao.OperationDao;
import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Operation;

import java.util.List;

public class OperationDaoImpl extends BaseDao implements OperationDao {

    @Override
    public int addOperation(Operation operation) {
        String sql = "insert into operation (`ip`,`date`,`role`,`role_id`,`operate`,`target`) values (?,?,?,?,?,?)";
        return update(sql,operation.getIp(),operation.getDate(),operation.getRole(),operation.getRoleId(),operation.getOperate(),operation.getTarget());
    }

    @Override
    public Integer queryForTotalCount() {
        String sql = "select count(*) from operation";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Operation> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`ip`,`date`,`role`,`role_id` roleId,`operate`,`target` from operation limit ?,?;";
        return queryForList(Operation.class, sql, begin, pageSize);
    }
}
