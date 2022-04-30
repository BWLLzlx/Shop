<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录成功界面</title>
    <%@include file="/pages/common/head.jsp"%>

    <c:if test="${not empty sessionScope.username}">
        <style>
            #header{
                background-color: #bfa;
            }
        </style>
    </c:if>

    <script>
        $(function (){
            $("#recommend").click(function (){
                if (${sessionScope.username!=null}){//如果登录了
                    if(${sessionScope.user.categoryId==1} ){//如果登录了还是1的类型（全0）说明没选喜欢的类型是什么
                        if(confirm("您还没有选择喜欢的商品类型，是否要前往选择？")== true){//如果选择前往选择就跳转到选择，然后不去推荐了
                            location.href = "${basePath}pages/client/like.jsp";
                            return false;
                        }else {//已经选了，就直接继续跳转到推荐
                        }
                    }
                }else {//如果没登陆
                    alert("登录后推荐将会更加个性化！快去登陆吧！")
                }
            })
        })
    </script>
</head>
<body>

    <div id="header">
        <div id="header_title">
            <div id="header_title_blank"></div>
            <a href="client/goodsServlet?action=page">电子商城</a>
            <a href="recommendServlet?action=recommend" id="recommend">个性推荐</a>
        </div>
        <div id="blank"></div>
        <div id="jump_message">
            <span>欢迎您，${sessionScope.username}</span>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="orderServlet?action=queryByUserId">我的订单</a>
            <a href="userServlet?action=logout" id="logout">退出登录</a>
            <div id="jump_message_blank"></div>
        </div>
    </div>

    <div id="a"></div>

    <div id="main">
        <div style="width: 400px;height: 50px;margin: 100px auto auto auto;text-align: center;font-size: 50px">登录成功</div>
    </div>

    <div id="b"></div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>