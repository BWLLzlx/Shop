package com.lhc.service;


import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Page;

import java.util.List;

public interface LoginInfoService {

    public void addRecord(LoginInfo loginInfo);
    public List<LoginInfo> queryForLoginInfo();

    Page<LoginInfo> page(int pageNo, int pageSize);

    Page<LoginInfo> pageByRole(int pageNo, int pageSize, String role);
}
