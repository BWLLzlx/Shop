<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 12423
  Date: 2022/4/21
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <%@include file="/pages/common/head.jsp"%>

    <style>
        #main td{
            width: 200px;
        }
    </style>

    <c:if test="${not empty sessionScope.username}">
        <style>
            #header{
                background-color: #bfa;
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

            $("#submit_order").click(function (){
                if ($(".updateCount").val()==0){
                    alert("购买数量不能为0！");
                    return false;
                }
                date = new Date();
                $("#date").val(date.toLocaleString());
            })

            $("a.delete").click(function (){
                //this表示当前响应事件的dom对象
                return confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text() +"】吗？")
            })
            $("#clearCart").click(function (){
                //this表示当前响应事件的dom对象
                return confirm("你确定要清空购物车吗？")
            })

            $(".updateCount").change(function (){
                var stock = $(this).attr("stock");
                if(this.value == 0){
                    alert("购买数量不能为0！");
                    this.value = this.defaultValue;
                }else{
                    if(Number(this.value) > Number(stock)){// 如果输入的值比库存还多
                        alert("本货物库存只有"+stock);
                        this.value = this.defaultValue;
                    }else{
                        if(confirm("你确定要把【"+ $(this).parent().parent().find("td:first").text() +"】的数量改为【"+ this.value +"】吗？")){
                            var id = $(this).attr("itemId");
                            location.href = ("${basePath}cartServlet?action=updateCount&id="+id+"&count="+this.value);
                        }else{
                            this.value = this.defaultValue;
                        }
                    }
                }

            })

            $("#recommend").click(function (){
                if (${sessionScope.username!=null}){//如果登录了
                    if(${sessionScope.user.categoryId==1} ){//如果登录了还是1的类型（全0）说明没选喜欢的类型是什么
                        if(confirm("您还没有选择喜欢的商品类型，是否要前往选择？")== true){//如果选择前往选择就跳转到选择，然后不去推荐了
                            location.href = "${basePath}pages/client/like.jsp";
                            return false;
                        }else {//已经选了，就直接继续跳转到推荐
                        }
                    }
                }else {//如果没登陆
                    alert("登录后推荐将会更加个性化！快去登陆吧！")
                }
            })
        })
    </script>
</head>
<body>
    <div id="header">
        <div id="header_title">
            <div id="header_title_blank"></div>
            <a href="client/goodsServlet?action=page">电子商城</a>
            <a href="recommendServlet?action=recommend" id="recommend">个性推荐</a>
        </div>
        <div id="blank">
            <form action="client/goodsServlet" method="get">
                <input type="hidden" name="action" value="pageByName">
                商品名称：
                <input type="text" id="goods_name" name="goods_name" value="${requestScope.goods_name}" class="input_content">

                <input type="hidden" name="ip" id="ip">
                <input type="hidden" name="date" id="date">
                <input type="hidden" name="role" value="用户">
                <input type="hidden" name="roleId" value="${sessionScope.user.id}">
                <input type="hidden" name="operate" value="查询">
                <input type="hidden" name="target" id="target">

                <input type="submit" value="查询" id="goods_search">
            </form>
        </div>
        <div id="jump_message">
            <span>欢迎您，${sessionScope.username}</span>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="orderServlet?action=queryByUserId">我的订单</a>
            <a href="userServlet?action=logout">退出登录</a>
            <div id="jump_message_blank"></div>
        </div>
    </div>
    <div id="a"></div>

    <div id="main">
        <c:if test="${not empty sessionScope.cart.items}">
            <div style="margin:50px auto auto auto;width: 600px;">
                <table>
                    <tr>
                        <td>商品名称</td>
                        <td>数量</td>
                        <td>价格</td>
                        <td>总价格</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${sessionScope.cart.items}" var="entry">
                        <tr><td><br></td></tr>
                        <tr>
                            <td>${entry.value.name}</td>
                            <td>
                                <input itemId="${entry.value.id}" stock="${entry.value.stock}" type="text" class="updateCount" value="${entry.value.count}">
                            </td>
                            <td>${entry.value.price}</td>
                            <td>${entry.value.totalPrice}</td>
                            <td><a href="cartServlet?action=deleteItem&id=${entry.value.id}" class="delete">删除</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </c:if>
        <c:if test="${empty sessionScope.cart.items}">
            <div style="margin:50px auto auto auto;width: 600px;height: 50px;text-align: center">
                当前购物车为空！
                <a href="index.jsp">浏览商品</a>
            </div>
        </c:if>
    </div>

    <div id="b">
        <c:if test="${not empty sessionScope.cart.items}">
            <table style="margin: 0 auto">
                <tr>
                    <td>购物车中共有${sessionScope.cart.totalCount}件商品，</td>
                    <td>总金额${sessionScope.cart.totalPrice}元，</td>
                    <td><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></td>
                    <td>
                        <form action="orderServlet" method="get">
                            <input type="hidden" name="action" value="addOrder">

                            <input type="hidden" name="ip" class="submit_ip" id="ip">
                            <input type="hidden" name="date" class="submit_date" id="date">
                            <input type="hidden" name="role" value="用户">
                            <input type="hidden" name="roleId" value="${sessionScope.user.id}">
                            <input type="hidden" name="operate" value="提交">
                            <input type="hidden" name="target" value="订单">

                            <input type="submit" id="submit_order" value="去结账" style="background-color:transparent;border:0px;font-size:16px;font-weight: bold;">
                        </form>
                    </td>
                </tr>
            </table>
        </c:if>
    </div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
