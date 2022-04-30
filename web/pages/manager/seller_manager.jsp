<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商家管理界面</title>
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
            width: 100px;
        }
    </style>

    <script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
    <script type="text/javascript">
        var ip = "";
        var date = new Date();
        $(function (){
            //获取ip
            ip = returnCitySN.cip;
            $("#ip").val(ip);
            $(".delete_ip").val(ip);

            $(".submit").click(function (){
                date = new Date();
                $(".delete_date").val(date.toLocaleString());
                return confirm("你确定要删除编号为【"+ $(this).parent().parent().parent().find("td:first").text() +"】的商家吗？")
            })

            $(".input_content").change(function (){
                //获取输入框的value属性（也就是输入内容
                var value = $(".input_content").attr("value");
                //赋值给表单项target的value属性
                $("#target").attr("value",value)
                // alert("target的值为"+$("#target").attr("value"));
            })

            $("#sellerSearch").click(function (){
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
        <div id="blank">
            <form action="sellerServlet" method="post">
                <input type="hidden" name="action" value="pageByName">
                商家名称：
                <input type="text" id="sellerName" name="sellerName" value="${requestScope.sellerName}" class="input_content">

                <input type="hidden" name="ip" id="ip">
                <input type="hidden" name="date" id="date">
                <input type="hidden" name="role" value="管理员">
                <input type="hidden" name="roleId" value="1">
                <input type="hidden" name="operate" value="查询">
                <input type="hidden" name="target" id="target">

                <input type="submit" value="查询" id="sellerSearch">
            </form>
        </div>
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
        <div id="main_inner">
            <div  style="margin: 0 auto ; width: 600px;">
                <table >
                    <c:if test="${requestScope.page.items.size()==0}">
                        <div style="height: 100px;line-height: 100px;text-align: center">
                            找不到搜索的商家
                        </div>
                    </c:if>
                    <c:if test="${requestScope.page.items.size()!=0}">
                        <tr>
                            <td>商家编号</td>
                            <td>商家名称</td>
                            <td>商家密码</td>
                            <td>销售额</td>
                            <td>操作</td>
                        </tr>
                        <c:forEach items="${requestScope.page.items}" var="seller">
                            <tr>
                                <td>${seller.id}</td>
                                <td>${seller.name}</td>
                                <td>${seller.password}</td>
                                <td>${seller.salesVolume}</td>
                                <td><a href="sellerServlet?action=getSeller&method=update&sellerId=${seller.id}">编辑</a></td>
                                <td>
                                        <%--                        <a href="sellerServlet?action=delete&sellerId=${seller.id}" class="delete">删除</a>--%>
                                    <form action="sellerServlet" method="get">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="sellerId" value="${seller.id}">

                                        <input type="hidden" name="ip" class="delete_ip" >
                                        <input type="hidden" name="date" class="delete_date">
                                        <input type="hidden" name="role" value="管理员">
                                        <input type="hidden" name="roleId" value="1">
                                        <input type="hidden" name="operate" value="删除">
                                        <input type="hidden" name="target" value="商家${goods.id}">
                                        <input type="submit" name="submit" class="submit" value="删除" style="background-color:transparent;border:0px;font-size:16px;font-weight: bold;">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><a href="pages/manager/seller_edit.jsp?method=add">添加商家</a></td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>

    </div>

    <%@include file="/pages/common/page_nav.jsp"%>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>