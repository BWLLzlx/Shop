package com.lhc.dao;


import com.lhc.pojo.LoginInfo;

import java.util.List;

public interface LoginInfoDao {

    int addRecord(LoginInfo loginInfo);
    List<LoginInfo> queryForLoginInfo();

    Integer queryForTotalCount();
    List<LoginInfo> queryForPageItems(int begin, int pageSize);

    Integer queryForTotalCountByRole(String role);

    List<LoginInfo> queryForPageItemsByRole(int begin, int pageSize, String role);

    List<LoginInfo> queryForPageItemsLike(int begin, int pageSize, String sth);
}
