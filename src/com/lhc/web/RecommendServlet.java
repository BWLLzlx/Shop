package com.lhc.web;

import com.lhc.pojo.Goods;
import com.lhc.pojo.Like;
import com.lhc.pojo.User;
import com.lhc.pojo.UserCategory;
import com.lhc.service.GoodsService;
import com.lhc.service.LikeService;
import com.lhc.service.UserCategoryService;
import com.lhc.service.UserService;
import com.lhc.service.impl.GoodsServiceImpl;
import com.lhc.service.impl.LikeServiceImpl;
import com.lhc.service.impl.UserCategoryServiceImpl;
import com.lhc.service.impl.UserServiceImpl;
import com.lhc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecommendServlet extends BaseServlet{

    UserService userService = new UserServiceImpl();
    UserCategoryService userCategoryService = new UserCategoryServiceImpl();
    GoodsService goodsService = new GoodsServiceImpl();
    LikeService likeService = new LikeServiceImpl();

    protected void recommend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer max[] = new Integer[6];
        UserCategory userCategory = new UserCategory();
        //获取登录用户
        User user = (User) req.getSession().getAttribute("user");
        if(user != null){
            //获取用户类别categoryId
            Integer categoryId = user.getCategoryId();
            //根据categoryId查询用户类别表指定的表单项
            userCategory = userCategoryService.queryById(categoryId);
        }else {
            userCategory = new UserCategory(null,5,5,5,5,5,5);
        }
        //用户类别表userCategory记录了对于各类商品的喜欢程度，喜欢程度是固定的，则权重也是固定的，推荐数量上限固定，所以推荐的每个类型的商品数量上限也是固定的，调用方法获取全部数量上限值（数组）
        max = userCategoryService.getMax(userCategory);
        Integer sum = 0;
        for (int i = 0; i < max.length; i++) {
            sum += max[i];
        }
        System.out.println("原数量");
        for (int i = 0; i < max.length; i++) {
            System.out.println(max[i]);
        }
        while (sum<UserCategory.PAGE_SIZE){
            double random =  Math.random();
            int r = (int)(random*5+1);
            max[r]+=1;
            sum+=1;
        }
        System.out.println("新数量");
        for (int i = 0; i < max.length; i++) {
            System.out.println(max[i]);
        }
        //查询数据库获取商品List
        List<Goods> food = goodsService.queryGoodsByNumberAndCategory(max[0],1);
        if (max[0]>food.size()){//如果商品是food类型的数量，比需要的数量还少，就让下一个数量变多
            max[1]+=max[0]-food.size();
        }
        List<Goods> clothes = goodsService.queryGoodsByNumberAndCategory(max[1],2);
        if (max[1]>clothes.size()){
            max[2]+=max[1]-clothes.size();
        }
        List<Goods> daily = goodsService.queryGoodsByNumberAndCategory(max[2],3);
        if(max[2]>daily.size()){
            max[3]+=max[2]-daily.size();
        }
        List<Goods> furniture = goodsService.queryGoodsByNumberAndCategory(max[3],4);
        if (max[3]>furniture.size()){
            max[4]+=max[3]-furniture.size();
        }
        List<Goods> electric = goodsService.queryGoodsByNumberAndCategory(max[4],5);
        if (max[4]>electric.size()){
            max[5]+=max[4]-electric.size();
        }
        List<Goods> fun = goodsService.queryGoodsByNumberAndCategory(max[5],6);
        System.out.println("最终数量");
        for (int i = 0; i < max.length; i++) {
            System.out.println(max[i]);
        }
        //把List拼接在一起，合成一个大List
        List<Goods> list = Stream.of(food,clothes,daily,furniture,electric,fun).flatMap(Collection::stream).collect(Collectors.toList());
        //保存到request域
        req.setAttribute("list",list);
        //请求重定向
        req.getRequestDispatcher("/pages/client/recommend.jsp").forward(req,resp);
    }

    protected void setCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCategory userCategory = new UserCategory(null,0,0,0,0,0,0);
        String food = req.getParameter("food");
        User user = (User) req.getSession().getAttribute("user");
        System.out.println("登录的用户为:"+user);
        if ("on".equals(food)){
            userCategory.setFood(5);
        }
        String clothes = req.getParameter("clothes");
        if ("on".equals(clothes)){
            userCategory.setClothes(5);
        }
        String daily = req.getParameter("daily");
        if ("on".equals(daily)){
            userCategory.setDaily(5);
        }
        String furniture = req.getParameter("furniture");
        if ("on".equals(furniture)){
            userCategory.setFurniture(5);
        }
        String electric = req.getParameter("electric");
        if ("on".equals(electric)){
            userCategory.setElectric(5);
        }
        String fun = req.getParameter("fun");
        if ("on".equals(fun)){
            userCategory.setFun(5);
        }

        System.out.println("用户选择了"+userCategory);

        //根据用户的选择修改这个用户的like表
        likeService.setLikeById(user.getId(),new Like(0,userCategory.getFood(),userCategory.getClothes(),
                userCategory.getDaily(),userCategory.getFurniture(),userCategory.getElectric(),userCategory.getFun()));

        //检查数据库里面有没有这个用户类别
        //如果是null就说明没有这个用户类别，就把这个类型添加到user_category表
        //并且把user表的categoryId改成对应的编号
        if(userCategoryService.queryByAll(userCategory)==null){
            System.out.println("数据库里面没有选择的版本，新增");
            userCategoryService.addCategory(userCategory);
            UserCategory get = userCategoryService.queryByAll(userCategory);
            System.out.println("新增的这个版本："+get);

            //把用户归为某个类型
            user.setCategoryId(get.getId());
            userCategoryService.setCategoryId(get.getId(),user.getId());
        }else {
            UserCategory get = userCategoryService.queryByAll(userCategory);
            System.out.println("已经存在的版本"+get);


            //把用户归为某个类型
            user.setCategoryId(get.getId());
            userCategoryService.setCategoryId(get.getId(),user.getId());
        }
        //把user对象保存到session域中，请求重定向到推荐页面的时候，会从session域获取到新的user
        req.getSession().setAttribute("user",user);
        resp.sendRedirect(req.getContextPath()+"/recommendServlet?action=recommend");
    }
}
