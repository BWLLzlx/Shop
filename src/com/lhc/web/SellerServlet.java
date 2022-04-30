package com.lhc.web;

import com.lhc.pojo.*;
import com.lhc.service.LoginInfoService;
import com.lhc.service.OperationService;
import com.lhc.service.SellerService;
import com.lhc.service.impl.LoginInfoServiceImpl;
import com.lhc.service.impl.OperationServiceImpl;
import com.lhc.service.impl.SellerServiceImpl;
import com.lhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SellerServlet extends BaseServlet{

    SellerService sellerService = new SellerServiceImpl();

    LoginInfoService loginInfoService = new LoginInfoServiceImpl();

    OperationService operationService = new OperationServiceImpl();

    protected void queryForAllSeller(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("11111");
        List<Seller> sellers = sellerService.queryForAll();
        req.setAttribute("sellers",sellers);
        req.getRequestDispatcher("/pages/manager/seller_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seller seller = WebUtils.copyParamToBean(req.getParameterMap(), new Seller());
        req.setAttribute("seller",seller);
        if(sellerService.isNameUsed(seller.getName()) == false){

            sellerService.register(seller);

            Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
            operationService.addOperation(operation);

            resp.sendRedirect(req.getContextPath()+"/sellerServlet?action=page");
        }else {
            req.setAttribute("msg","商家名已存在！");
            req.getRequestDispatcher("/pages/manager/seller_edit.jsp").forward(req,resp);
        }
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sellerId = WebUtils.parseInt(req.getParameter("sellerId"),0);
        sellerService.delete(sellerId);

        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        operationService.addOperation(operation);

        resp.sendRedirect(req.getContextPath()+"/sellerServlet?action=page");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seller seller = WebUtils.copyParamToBean(req.getParameterMap(), new Seller());
        sellerService.update(seller);

        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        operationService.addOperation(operation);

        resp.sendRedirect(req.getContextPath()+"/sellerServlet?action=page");
    }

    protected void getSeller(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sellerId = WebUtils.parseInt(req.getParameter("sellerId"), 0);
        Seller seller = sellerService.querySellerById(sellerId);
        req.setAttribute("seller",seller);
        req.getRequestDispatcher("/pages/manager/seller_edit.jsp").forward(req,resp);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("username");
        req.getSession().removeAttribute("manager");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Seller seller = sellerService.login(new Seller(null, name, password, 0));
        if (seller == null){
            req.setAttribute("msg","账号或密码错误！");
            req.getRequestDispatcher("/pages/seller_login/login.jsp").forward(req,resp);
        }else {
            Integer sellerId = seller.getId();
            LoginInfo loginInfo = WebUtils.copyParamToBean(req.getParameterMap(),new LoginInfo());
            loginInfo.setRoleId(sellerId);
            loginInfoService.addRecord(loginInfo);

            req.setAttribute("seller",seller);
            req.getSession().setAttribute("seller",seller);
            Cookie cookie = new Cookie("sellerName",seller.getName());
            cookie.setMaxAge(60*60*24);
            resp.addCookie(cookie);
            req.getRequestDispatcher("/pages/seller_login/seller.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //删除session
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 10);
        Page<Seller> page = sellerService.page(pageNo,pageSize);
        page.setUrl("sellerServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/seller_manager.jsp").forward(req,resp);
    }

    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 10);

        //记录操作信息
        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        System.out.println(operation);
        operationService.addOperation(operation);

        String sellerName = req.getParameter("sellerName");
        Page<Seller> page = sellerService.pageByName(pageNo,pageSize,sellerName);
        StringBuilder stringBuilder = new StringBuilder("sellerServlet?action=pageByName");
        if (sellerName != null){
            stringBuilder.append("&sellerName=").append(sellerName);
        }
        page.setUrl(stringBuilder.toString());
        //用来显示主要的结果
        req.setAttribute("page",page);
        //用于在搜索栏显示
        req.setAttribute("sellerName",sellerName);
        req.getRequestDispatcher("/pages/manager/seller_manager.jsp").forward(req,resp);
    }
}
