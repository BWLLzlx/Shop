<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商家登录成功界面</title>
    <%@include file="/pages/common/head.jsp"%>

    <c:if test="${not empty sessionScope.seller}">
        <style>
            #header{
                background-color: #baf;
            }
        </style>
    </c:if>

</head>
<body>

    <div id="header">
        <div id="header_title">
            <div id="header_title_blank"></div>
            <a href="pages/seller_login/seller.jsp">商家首页</a>
        </div>
        <div id="blank"></div>
        <div id="jump_message">
            <span>欢迎您，${sessionScope.seller.name}</span>
            <a href="manager/goodsServlet?action=page&sellerId=${sessionScope.seller.id}">商品管理</a>
            <a href="orderServlet?action=queryBySellerId">销售记录</a>
            <a href="sellerServlet?action=logout">退出登录</a>
            <div id="jump_message_blank"></div>
        </div>
    </div>

    <div id="a"></div>

    <div id="main">
        <div style="margin-top: 100px;font-size: 50px;text-align: center">商家首页</div>
    </div>

    <div id="b"></div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>