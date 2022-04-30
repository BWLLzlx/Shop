package com.lhc.web;

import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Page;
import com.lhc.pojo.Seller;
import com.lhc.service.LoginInfoService;
import com.lhc.service.SellerService;
import com.lhc.service.impl.LoginInfoServiceImpl;
import com.lhc.service.impl.SellerServiceImpl;
import com.lhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInfoServlet extends BaseServlet{

    private LoginInfoService loginInfoService = new LoginInfoServiceImpl();


    //没用上
    protected void addRecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginInfo loginInfo = WebUtils.copyParamToBean(req.getParameterMap(),new LoginInfo());
        loginInfoService.addRecord(loginInfo);
        req.getRequestDispatcher("/pages/test/ipTestEnd.jsp").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 10);
        Page<LoginInfo> page = loginInfoService.page(pageNo,pageSize);
        page.setUrl("loginInfoServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/login_manager.jsp").forward(req,resp);
    }

    protected void pageByRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 10);
        String role = req.getParameter("role");
        Page<LoginInfo> page = loginInfoService.pageByRole(pageNo,pageSize,role);

        StringBuilder stringBuilder = new StringBuilder("loginInfoServlet?action=pageByRole");
        if (role != null){
            stringBuilder.append("&role=").append(role);
        }
        page.setUrl(stringBuilder.toString());
        req.setAttribute("role",role);

        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/login_manager.jsp").forward(req,resp);
    }
}
