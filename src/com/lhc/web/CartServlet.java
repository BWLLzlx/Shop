package com.lhc.web;

import com.lhc.pojo.Cart;
import com.lhc.pojo.CartItem;
import com.lhc.pojo.Goods;
import com.lhc.service.GoodsService;
import com.lhc.service.impl.GoodsServiceImpl;
import com.lhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{

    private int number = -10;

    private GoodsService goodsService = new GoodsServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //获取原库存,一起存到cart里面方便等会在购物车的地方再用上这个stock
        int stock = WebUtils.parseInt(req.getParameter("stock"), 0);

        //根据商品id找到对应的Goods对象
        Goods goods = goodsService.queryGoodsById(id);
        //创建一个cartItem对象，cartItem的id就是商品的id
        CartItem cartItem = new CartItem(id,goods.getName(),1,goods.getPrice(),goods.getPrice(),stock,goods.getSellerId());

        //定义number表示还有多少次可以用
        if(number == -10){
            number = stock-1;
        }else {
            number -=1;
        }

        //把number存到session域中
        req.getSession().setAttribute("number",number);

        //调用addItem方法添加到购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);

    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.deleteItem(id);
            String referer = req.getHeader("Referer");
            resp.sendRedirect(referer);
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
            String referer = req.getHeader("Referer");
            resp.sendRedirect(referer);
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateItem(id,count);
            String referer = req.getHeader("Referer");
            resp.sendRedirect(referer);
        }
    }
}
