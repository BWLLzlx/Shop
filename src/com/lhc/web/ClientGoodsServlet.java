package com.lhc.web;

import com.lhc.pojo.Goods;
import com.lhc.pojo.Operation;
import com.lhc.pojo.Page;
import com.lhc.service.GoodsService;
import com.lhc.service.OperationService;
import com.lhc.service.impl.GoodsServiceImpl;
import com.lhc.service.impl.OperationServiceImpl;
import com.lhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientGoodsServlet extends BaseServlet{


    private GoodsService goodsService = new GoodsServiceImpl();

    private OperationService operationService = new OperationServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 16);
        Page<Goods> page = goodsService.page(pageNo,pageSize);
        page.setUrl("client/goodsServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 16);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        Page<Goods> page = goodsService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder stringBuilder = new StringBuilder("client/goodsServlet?action=pageByPrice");
        if (req.getParameter("min") != null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(stringBuilder.toString());

        req.setAttribute("min",min);
        req.setAttribute("max",max);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 16);
        String goods_name = req.getParameter("goods_name");
        Page<Goods> page = goodsService.pageByName(pageNo,pageSize,goods_name);

        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        operationService.addOperation(operation);

        StringBuilder stringBuilder = new StringBuilder("client/goodsServlet?action=pageByName");
        if(req.getParameter("goods_name")!=null){
            stringBuilder.append("&goods_name=").append(req.getParameter("goods_name"));
        }
        page.setUrl(stringBuilder.toString());



        req.setAttribute("page",page);
        req.setAttribute("goods_name",goods_name);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
