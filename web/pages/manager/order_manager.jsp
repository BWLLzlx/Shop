<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/12
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单管理界面</title>
    <%@include file="/pages/common/head.jsp"%>

    <c:if test="${not empty sessionScope.seller}">
        <style>
            #header{
                background-color: #baf;
            }
        </style>
    </c:if>

    <c:if test="${not empty sessionScope.manager}">
        <style>
            #header{
                background-color: #fba;
            }
        </style>
    </c:if>

    <style>
        td{
            width: 300px;
        }
    </style>

</head>
<body>
    <div id="header">
        <div id="header_title">
            <div id="header_title_blank"></div>
            <c:if test="${not empty sessionScope.manager}">
                <a href="pages/manager/manager.jsp">后台管理</a>
            </c:if>
            <c:if test="${not empty sessionScope.seller}">
                <a href="pages/seller_login/seller.jsp">商家首页</a>
            </c:if>
        </div>
        <div id="blank"></div>
        <div id="jump_message">
            <c:if test="${not empty sessionScope.manager}">
                <span>欢迎您，${sessionScope.manager.name}</span>
                <a href="manager/goodsServlet?action=page">商品管理</a>
                <a href="orderServlet?action=page">订单管理</a>
                <a href="sellerServlet?action=page">商家管理</a>
                <a href="loginInfoServlet?action=page">登录记录</a>
                <a href="operationServlet?action=page">操作记录</a>
                <a href="managerServlet?action=logout">退出登录</a>
            </c:if>
            <c:if test="${not empty sessionScope.seller}">
                <span>欢迎您，${sessionScope.seller.name}</span>
                <a href="manager/goodsServlet?action=page&sellerId=${sessionScope.seller.id}">商品管理</a>
                <a href="orderServlet?action=queryBySellerId">销售记录</a>
                <a href="sellerServlet?action=logout">退出登录</a>
            </c:if>
            <div id="jump_message_blank"></div>
        </div>
    </div>
    <div id="a"></div>

    <div id="main">
        <div id="main_inner" style="overflow-y: hidden">
            <table >
                <tr>
                    <td>订单号</td>
                    <td>时间</td>
                    <td>金额</td>
                    <td>详情</td>
                </tr>
                <c:forEach items="${requestScope.page.items}" var="order">
                    <tr><td><br></td></tr>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.createTime}</td>
                        <td>${order.price}</td>
                        <td><a href="orderServlet?action=queryForOrderItemByOrderId&orderId=${order.id}">查看详情</a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

    <%@include file="/pages/common/page_nav.jsp"%>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
