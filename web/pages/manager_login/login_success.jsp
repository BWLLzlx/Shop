<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员登录成功界面</title>
    <%@include file="/pages/common/head.jsp"%>
    <c:if test="${not empty sessionScope.manager}">
        <style>
            #header{
                background-color: #fba;
            }
        </style>
    </c:if>

</head>
<body>

    <div id="header">
        <div id="header_title">
            <div id="header_title_blank"></div>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </div>
        <div id="blank"></div>
        <div id="jump_message">
            <span>欢迎您，${sessionScope.manager.name}</span>
            <a href="manager/goodsServlet?action=page">商品管理</a>
            <a href="orderServlet?action=page">订单管理</a>
            <a href="sellerServlet?action=page">商家管理</a>
            <a href="loginInfoServlet?action=page">登录记录</a>
            <a href="operationServlet?action=page">操作记录</a>
            <a href="managerServlet?action=logout">退出登录</a>
            <div id="jump_message_blank"></div>
        </div>
    </div>

    <div id="a"></div>

    <div id="main" >
        <div style="width: 400px;height: 50px;line-height: 50px;text-align: center;font-size: 50px;margin: 50px auto auto auto">
            登录成功
        </div>

    </div>

    <div id="b"></div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>