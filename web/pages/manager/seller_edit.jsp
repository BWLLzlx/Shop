<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/12
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商家编辑界面</title>
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



    <script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
    <script type="text/javascript">
        var ip = "";
        var date = new Date();
        $(function (){
            //获取ip
            ip = returnCitySN.cip;
            $("#ip").val(ip);

            $("#submit_btn").click(function (){
                var nameText = $("#name").val();
                var namePatt = /^\w{3,8}$/;
                if(!namePatt.test(nameText)){
                    $("#errorMessage").text("名称不合法！只能输入长度在3-8之间的数字、字母、下划线")
                    return false;
                }
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{3,8}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("#errorMessage").text("密码不合法！只能输入长度在3-8之间的数字、字母、下划线");
                    return false;
                }
                var name = $("#name").val();
                var password = $("#password").val();
                name = $.trim(name);
                password = $.trim(password);
                if(name == null || name==""){
                    $("#errorMessage").text("名称不能为空！");
                    return false;
                }
                if(password == null || password ==""){
                    $("#errorMessage").text("密码不能为空！");
                    return false;
                }
                //获取时间
                date = new Date();
                $("#date").val(date.toLocaleString());
            })
        })
    </script>
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

    <div id="main">
        <div id="main_inner">
            <form action="sellerServlet?action=${param.method}" method="post">
                <div style="width: 400px;height: 100px;text-align: center">
                    <div style="font-size: 50px">编辑商家信息</div>
                    <div id="errorMessage" style="color: red;height: 50px;line-height: 50px;">${requestScope.msg}</div>
                </div>

                <input type="hidden" name="id" value="${requestScope.seller.id}" id="id">
                <div style="width: 400px;height: 100px;margin: 0 auto;text-align: center">
                    名称：<input type="text" name="name" value="${requestScope.seller.name}" id="name"> <br><br>
                    密码：<input type="text" name="password" value="${requestScope.seller.password}" id="password"> <br>
                </div>
                <input type="hidden" name="salesVolume" value="${requestScope.seller.salesVolume}" id="salesVolume">

                <input type="hidden" name="ip" id="ip">
                <input type="hidden" name="date" id="date">
                <input type="hidden" name="role" value="管理员">
                <input type="hidden" name="roleId" value="1">
                <input type="hidden" name="operate" value="${param.method=="add"?"添加":"修改"}">
                <input type="hidden" name="target" value="商家${requestScope.seller.id}">

                <div style="width: 400px;height: 50px;line-height: 50px;text-align: center">
                    <input type="submit" name="submit" value="提交" id="submit_btn">
                </div>
            </form>
        </div>
    </div>

    <div id="b"></div>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
