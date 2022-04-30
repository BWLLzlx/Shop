package com.lhc.dao.impl;

import com.lhc.dao.LoginInfoDao;
import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Seller;

import java.util.List;

public class LoginInfoDaoImpl extends BaseDao implements LoginInfoDao {

    @Override
    public int addRecord(LoginInfo loginInfo) {
        String sql = "insert into loginInfo(`ip`,`address`,`date`,`operation`,`role`,`role_id`) values(?,?,?,?,?,?)";
        return update(sql,loginInfo.getIp(),loginInfo.getAddress(),loginInfo.getDate(),loginInfo.getOperation(),loginInfo.getRole(),loginInfo.getRoleId());
    }

    @Override
    public List<LoginInfo> queryForLoginInfo() {
        String sql = "select `id`,`ip`,`address`,`date`,`operation`,`role`,`role_id` roleId from loginInfo";
        return queryForList(LoginInfo.class,sql);
    }

    @Override
    public Integer queryForTotalCount() {
        String sql = "select count(*) from loginInfo";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<LoginInfo> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`ip`,`address`,`date`,`operation`,`role`,`role_id` roleId from loginInfo limit ?,?;";
        return queryForList(LoginInfo.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForTotalCountByRole(String role) {
        String sql = "select count(*) from loginInfo where role = ?";
        Number count = (Number) queryForSingleValue(sql,role);
        return count.intValue();
    }

    @Override
    public List<LoginInfo> queryForPageItemsByRole(int begin, int pageSize, String role) {
        String sql = "select `id`,`ip`,`address`,`date`,`operation`,`role`,`role_id` roleId from loginInfo where role = ? limit ?,?;";
        return queryForList(LoginInfo.class, sql, role, begin, pageSize);
    }

    @Override
    public List<LoginInfo> queryForPageItemsLike(int begin, int pageSize, String sth) {
        String sql = "select `id`,`ip`,`address`,`date`,`operation`,`role`,`role_id` roleId from loginInfo where role like \"%\" ?"+"\"%\" limit ?,?;";
        return queryForList(LoginInfo.class, sql, sth, begin, pageSize);
    }
}
