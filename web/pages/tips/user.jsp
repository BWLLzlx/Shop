<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/23
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>需要用户权限</title>
    <%@include file="/pages/common/head.jsp"%>
    <meta http-equiv="refresh" content="2;URL=${basePath}index.jsp">
</head>
<body>
    <div style="display: flex;flex-direction: column;justify-content: center; width: 100%; height: 100%">
        <div style="width: 200px;height: 200px;margin: 0 auto">请先登录用户账号！ <br><br> 2秒后将回到首页</div>
    </div>

</body>
</html>
