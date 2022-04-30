package com.lhc.web;

import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Operation;
import com.lhc.pojo.Page;
import com.lhc.service.OperationService;
import com.lhc.service.impl.OperationServiceImpl;
import com.lhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OperationServlet extends BaseServlet{

    OperationService operationService = new OperationServiceImpl();

    //只是个样例，实际不会调用
    protected void addOperation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        //ip、date、role、roleId、action、target
        //ip和date是肯定传的，role也在静态页面写死，roleid就是session的id如果没登陆就是1（管理员只有一个），action是定死的，target靠js获取输入框的值，也能传过来
        operationService.addOperation(operation);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 10);
        Page<Operation> page = operationService.page(pageNo,pageSize);
        page.setUrl("operationServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/operation_manager.jsp").forward(req,resp);
    }
}
