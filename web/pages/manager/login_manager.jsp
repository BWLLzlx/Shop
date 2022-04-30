<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/25
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录记录页面</title>
    <%@include file="/pages/common/head.jsp"%>
    <c:if test="${not empty sessionScope.manager}">
        <style>
            #header{
                background-color: #fba;
            }
        </style>
    </c:if>
    <style>
        td{
            width: 200px;
        }
    </style>

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

    <div id="main">
        <div id="main_inner" style="overflow-y: hidden">
            <table >
                <tr>
                    <td>ip</td>
                    <td>地址</td>
                    <td>时间</td>
                    <td>操作</td>
                    <td>对象</td>
                    <td>编号</td>
                </tr>
                <c:forEach items="${requestScope.page.items}" var="item">
                    <tr><td><br></td></tr>
                    <tr>
                        <td>${item.ip}</td>
                        <td>${item.address}</td>
                        <td>${item.date}</td>
                        <td>${item.operation}</td>
                        <td>${item.role}</td>
                        <td>${item.roleId}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <%@include file="/pages/common/page_nav.jsp"%>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
