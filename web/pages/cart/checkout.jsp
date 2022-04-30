<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/22
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结算界面</title>
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
        <div id="blank">
            <form action="client/goodsServlet" method="get">
                <input type="hidden" name="action" value="pageByName">
                商品名称：
                <input type="text" id="goods_name" name="goods_name" value="${requestScope.goods_name}" class="input_content">

                <input type="hidden" name="ip" id="ip">
                <input type="hidden" name="date" id="date">
                <input type="hidden" name="role" value="用户">
                <input type="hidden" name="roleId" value="${sessionScope.user.id}">
                <input type="hidden" name="operate" value="查询">
                <input type="hidden" name="target" id="target">

                <input type="submit" value="查询" id="goods_search">
            </form>
        </div>
        <div id="jump_message">
            <span>欢迎您，${sessionScope.username}</span>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="orderServlet?action=queryByUserId">我的订单</a>
            <a href="userServlet?action=logout">退出登录</a>
            <div id="jump_message_blank"></div>
        </div>
    </div>
    <div id="a"></div>
    <div id="main">
        <div style="margin: 50px auto auto auto;width: 400px;height: 100px;text-align: center">你的订单已结算，订单号为：${sessionScope.orderId}</div>
    </div>
    <div id="b"></div>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
