<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/19
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试获取ip</title>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>

    <script type="text/javascript">
        var ip = "";
        var address = "";
        var date = new Date();
        $(function (){
            //ip
            ip = returnCitySN.cip;
            $("#ip").val(ip);
            //地址
            address = returnCitySN.cname;
            $("#address").val(address);
            //时间
            $("#submit").click(function (){
                date = new Date();
                // alert(date.toLocaleString());
                $("#date").val(date.toLocaleString());
            })
        })
    </script>

</head>
<body>
    <form action="loginInfoServlet" method="post">
<%--        通过js获取下面三行--%>
        <input type="hidden" name="ip" id="ip">
        <input type="hidden" name="address" id="address">
        <input type="hidden" name="date" id="date">
        <input type="hidden" name="operation" value="登录">
        <input type="hidden" name="role" value="用户">
        <input type="hidden" name="roleId" value="1">
        <input type="hidden" name="action" value="addRecord">
        <input type="submit" value="提交" id="submit">
    </form>
</body>
</html>