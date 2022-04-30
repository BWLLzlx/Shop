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
    <title>商品管理界面</title>
    <%@include file="/pages/common/head.jsp"%>

    <style>
        a:link , a:hover , a:visited, a:active{
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            color: black;
        }
        td{
            width: 100px;
        }
    </style>

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
            $(".delete_ip").val(ip);

            $(".submit").click(function (){
                date = new Date();
                $(".delete_date").val(date.toLocaleString());
                // alert($(".delete_date").val());
                return  confirm("你确定要删除【"+ $(this).parent().parent().parent().find("td:first").text() +"】吗？");
            })

            $(".input_content").change(function (){
                //获取输入框的value属性（也就是输入内容
                var value = $(".input_content").attr("value");
                //赋值给表单项target的value属性
                $("#target").attr("value",value)
                // alert("target的值为"+$("#target").attr("value"));
            })

            $("#goodsSearch").click(function (){
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
            <c:if test="${not empty sessionScope.manager}">
                <a href="pages/manager/manager.jsp">后台管理</a>
            </c:if>
            <c:if test="${not empty sessionScope.seller}">
                <a href="pages/seller_login/seller.jsp">商家首页</a>
            </c:if>
        </div>
        <div id="blank">
            <form action="manager/goodsServlet" method="post">
                <input type="hidden" name="action" value="pageByName">
                <input type="hidden" name="sellerId" value="${param.sellerId}">
                商品名称：
                <input type="text" id="goodsName" name="goodsName" value="${requestScope.goodsName}" class="input_content">

                <%--        靠js获取--%>
                <input type="hidden" name="ip" id="ip">
                <input type="hidden" name="date" id="date">

                <c:if test="${not empty sessionScope.manager}">
                    <input type="hidden" name="role" value="管理员">
                    <input type="hidden" name="roleId" value="1">
                </c:if>
                <c:if test="${not empty sessionScope.seller}">
                    <input type="hidden" name="role" value="商家">
                    <input type="hidden" name="roleId" value="${param.sellerId}">
                </c:if>
                <input type="hidden" name="operate" value="查询">
                <%--            目标值就用goodsName就行，利用js给target赋值--%>
                <input type="hidden" name="target" id="target">


                <input type="submit" value="查询" id="goodsSearch">
            </form>
        </div>
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
<%--        表格区域--%>
        <div style="width: 600px;margin: 0 auto">
            <table>
                <c:if test="${requestScope.page.items.size()==0}">
                    <div>
                        <span>查不到对应的商品</span>
                    </div>
                </c:if>
                <c:if test="${requestScope.page.items.size()!=0}">
                    <tr>
                        <td>名称</td>
                        <td>价格</td>
                        <td>销量</td>
                        <td>库存</td>
                        <td>类型</td>
                        <c:if test="${not empty sessionScope.seller}">
                            <td>操作</td>
                        </c:if>
                    </tr>

                    <c:forEach items="${requestScope.page.items}" var="goods">
                        <tr>
                            <td>${goods.name}</td>
                            <td>${goods.price}</td>
                            <td>${goods.sales}</td>
                            <td>${goods.stock}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${goods.categoryId==1}">食品</c:when>
                                    <c:when test="${goods.categoryId==2}">服装</c:when>
                                    <c:when test="${goods.categoryId==3}">日用品</c:when>
                                    <c:when test="${goods.categoryId==4}">家具</c:when>
                                    <c:when test="${goods.categoryId==5}">电器</c:when>
                                    <c:when test="${goods.categoryId==6}">娱乐</c:when>
                                </c:choose>
                            </td>
                            <c:if test="${not empty sessionScope.seller}">
                                <td><a href="manager/goodsServlet?action=getGoods&id=${goods.id}&method=updateWithPhoto&sellerId=${sessionScope.seller.id}">修改</a></td>
                                <%--<a class="delete" id="delete" href="manager/goodsServlet?action=delete&id=${goods.id}&sellerId=${sessionScope.seller.id}">删除</a>--%>
                                <td>
                                    <form action="manager/goodsServlet" method="get">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="${goods.id}">
                                        <input type="hidden" name="sellerId" value="${sessionScope.seller.id}">

                                        <input type="hidden" name="ip" class="delete_ip" >
                                        <input type="hidden" name="date" class="delete_date">
                                        <input type="hidden" name="role" value="商家">
                                        <input type="hidden" name="roleId" value="${sessionScope.seller.id}">
                                        <input type="hidden" name="operate" value="删除">
                                        <input type="hidden" name="target" value="商品${goods.id}">
                                        <input type="submit" name="submit" class="submit" value="删除" style="background-color:transparent;border:0px;font-size:16px;font-weight: bold;">
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>

                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <c:if test="${not empty sessionScope.seller}">
                        <td><a href="pages/manager/goods_edit.jsp?method=addWithPhoto&sellerId=${sessionScope.seller.id}">添加商品</a></td>
                    </c:if>

                </tr>

            </table>
        </div>
    </div>

    <%--        分页条区域--%>
    <%@include file="/pages/common/page_nav.jsp"%>

    <%@include file="/pages/common/footer.jsp"%>

</body>
</html>
