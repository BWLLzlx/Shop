<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/12
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath =   request.getScheme()
                    +   "://"
//            根据测试，需要把服务器地址写死，本地就用获取服务name
//                    +   "101.35.255.239"
                    +   request.getServerName()
                    +   ":"
                    +   request.getServerPort()
                    +   request.getContextPath()
                    +   "/";
    pageContext.setAttribute("basePath",basePath);
%>
<base href="<%=basePath%>">
<link rel="stylesheet" href="static/css/reset.css">
<link rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

