package com.lhc.web;

import com.lhc.pojo.*;
import com.lhc.service.*;
import com.lhc.service.impl.*;
import com.lhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();

    private OperationService operationService = new OperationServiceImpl();

    GoodsService goodsService = new GoodsServiceImpl();

    LikeService likeService = new LikeServiceImpl();

    UserCategoryService userCategoryService = new UserCategoryServiceImpl();

    UserService userService = new UserServiceImpl();

    protected void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session中获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //从session中获取用户对象，然后获取用户的id
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();

        //添加订单，获得订单编号
        String orderId = orderService.addOrder(cart, userId);

        //记录操作
        Operation operation = WebUtils.copyParamToBean(req.getParameterMap(), new Operation());
        operationService.addOperation(operation);

        //根据订单编号获取订单项准备遍历
        List<OrderItem> orderItems = orderService.queryForOrderItemByOrderId(orderId);

        //通过userId查找当前用户喜欢的值，准备用来修改
        Like like = likeService.getLikeById(userId);
        Integer food = like.getFood();
        Integer clothes = like.getClothes();
        Integer daily = like.getDaily();
        Integer furniture = like.getFurniture();
        Integer electric = like.getElectric();
        Integer fun = like.getFun();
        Integer[] categories = new Integer[6];
        categories[0] = food;
        categories[1] = clothes;
        categories[2] = daily;
        categories[3] = furniture;
        categories[4] = electric;
        categories[5] = fun;

        //创建数组用于保存新的like值
        Integer[] newLike= new Integer[6];

        //根据订单项修改like表，需要用户编号userId，数量count，商品类型，而商品类型靠商品编号
        for (OrderItem orderItem : orderItems) {
            //获取购买数量count
            Integer count = orderItem.getCount();
            //获取商品id
            Integer goodsId = orderItem.getGoodsId();
            //通过商品id查找商品
            Goods goods = goodsService.queryGoodsById(goodsId);
            //获取这个商品的类型
            Integer categoryId = goods.getCategoryId();
            //根据商品类型对like表进行修改
            for (int i = 0; i < 6; i++) {
                if (categoryId == i+1){//假如定死categoryId是1，也就是i是0，也就是食品
                    for (int j = 0; j < 6; j++) {
                        if (i==j){//当j=0时，也就是categories[0]也就是food，加上count
                            categories[j]+=count;
                        }else {//当j不等于0时，表示不是food，就减去count
                            categories[j]-=count;
                        }
                    }
                    //循环完后就把新的值存在categories数组里面了，然后检查categories数组的每个数据有没有溢出
                    for (int j = 0; j < 6; j++) {
                        if (categories[j]>5){
                            categories[j]=5;
                        }else if(categories[j]<0){
                            categories[j]=0;
                        }
                    }
                    //然后给like表赋值
                    likeService.setLikeById(userId,new Like(0,categories[0],categories[1],categories[2],categories[3],categories[4],categories[5]));
                    System.out.println("购买后的喜欢值为"+likeService.getLikeById(userId));
                    //存储like值
                    for (int j = 0; j < 6; j++) {
                        newLike[j] = categories[j];
                    }
                }
            }
        }
        double loss = 100;

        //更改完用户喜欢的具体情况后，遍历user_category表，计算误差，看看哪个情况和用户当前的最接近（误差最小），不能和（0，0，0，0，0，0）的情况比较
        List<UserCategory> userCategoryList = userCategoryService.queryForAll();
        for (UserCategory userCategory : userCategoryList) {
            if (userCategory.getId()==1){
                continue;
            }else {
                Integer[] categoryLike = new Integer[6];
                categoryLike[0] = userCategory.getFood();
                categoryLike[1] = userCategory.getClothes();
                categoryLike[2] = userCategory.getDaily();
                categoryLike[3] = userCategory.getFurniture();
                categoryLike[4] = userCategory.getElectric();
                categoryLike[5] = userCategory.getFun();

                System.out.println("对比的类别为：");
                for (int i = 0; i < categoryLike.length; i++) {
                    System.out.println(categoryLike[i]);
                }

                //如果计算出来的新误差更小，就把误差设置为这个新的值
                if(WebUtils.getLoss(categoryLike,newLike)<loss){
                    //以后面的做基准，也就是newLike
                    loss = WebUtils.getLoss(categoryLike,newLike);

                }
                System.out.println("误差为"+loss);

            }
        }
        System.out.println("最终误差为"+loss);
        //这时候已经有loss了，只需要判断loss是否超过上限，如果超过设定的上限就说明应该创建一个新的类别了，利用之前保存下来的新的like
        double max = 1;
        if (loss>max){
            //在数据库添加新的类别
            userCategoryService.addCategory(new UserCategory(null,newLike[0],newLike[1],newLike[2],newLike[3],newLike[4],newLike[5]));
            //获得新的类别的id
            UserCategory userCategory = userCategoryService.queryByAll(new UserCategory(null, newLike[0], newLike[1], newLike[2], newLike[3], newLike[4], newLike[5]));
            Integer categoryId = userCategory.getId();
            //把当前用户的喜欢类别设置为那个类别
            userService.setCategoryId(categoryId,userId);
        }

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void queryForAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //自己修改，如果检查到了manager对象才允许继续
        Manager manager = (Manager) req.getSession().getAttribute("manager");
        if (manager==null){
            resp.sendRedirect(req.getContextPath()+"/pages/tips/manager.jsp");
        }else{
            List<Order> orders = orderService.queryForAll();
            req.setAttribute("orders",orders);
            req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
        }
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), 10);
        Page<Order> page = orderService.page(pageNo,pageSize);
        page.setUrl("orderServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    protected void queryByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        List<Order> orders = orderService.queryByUserId(userId);
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void queryBySellerId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seller seller = (Seller) req.getSession().getAttribute("seller");
        Integer sellerId = seller.getId();
        System.out.println("登录商家编号："+sellerId);
        List<OrderItem> orderItems  = orderService.queryBySellerId(sellerId);
        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/order_item.jsp").forward(req,resp);
    }

    protected void queryForOrderItemByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.queryForOrderItemByOrderId(orderId);
        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("/pages/order/order_item.jsp").forward(req,resp);
    }
}
