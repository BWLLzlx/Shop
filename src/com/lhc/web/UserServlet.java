package com.lhc.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.lhc.pojo.Like;
import com.lhc.pojo.LoginInfo;
import com.lhc.pojo.Operation;
import com.lhc.pojo.User;
import com.lhc.service.LikeService;
import com.lhc.service.LoginInfoService;
import com.lhc.service.OperationService;
import com.lhc.service.UserService;
import com.lhc.service.impl.LikeServiceImpl;
import com.lhc.service.impl.LoginInfoServiceImpl;
import com.lhc.service.impl.OperationServiceImpl;
import com.lhc.service.impl.UserServiceImpl;
import com.lhc.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    private LoginInfoService loginInfoService = new LoginInfoServiceImpl();

    private OperationService operationService = new OperationServiceImpl();

    private LikeService likeService = new LikeServiceImpl();

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //删除session
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
//        System.out.println("111");
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("manager");
        req.getSession().removeAttribute("seller");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null,1));
        System.out.println(loginUser);
        if(loginUser==null){
            req.setAttribute("msg","账号或密码错误！");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            Integer id = loginUser.getId();
            LoginInfo loginInfo = WebUtils.copyParamToBean(req.getParameterMap(),new LoginInfo());
            loginInfo.setRoleId(id);
            loginInfoService.addRecord(loginInfo);

            req.setAttribute("username",username);
            req.setAttribute("user",loginUser);
            req.getSession().setAttribute("username",username);
            req.getSession().setAttribute("user",loginUser);
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60*24);
            resp.addCookie(cookie);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取谷歌浏览器的验证码servlet保存到session的验证码字符串，然后删掉session的这个属性
        String codeFromGoogle  = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Integer categoryId = 1;
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //检查验证码
        if (codeFromGoogle != null && codeFromGoogle.equalsIgnoreCase(code)){
            //检查用户名是否存在
            if(userService.existUsername(username)){
//                System.out.println("用户名已存在");
                req.setAttribute("msg","用户名已存在！");
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);
            }else{
                userService.register(new User(null, username, password, email, categoryId));
                //往like表里面设置初始值
                //login方法就是查找账号密码符合的用户，通过用户对象获取到userId
                Integer userId = userService.login(new User(null,username, password,null,1)).getId();
                //根据userId添加Like表，默认全是0
                likeService.addLikeById(userId,new Like(0,0,0,0,0,0,0));
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req,resp);
            }
        }else{
//            System.out.println("验证码错误");
            req.setAttribute("msg","验证码错误！");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req,resp);
        }
    }


}
