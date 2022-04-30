<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>推荐页面</title>
    <%@include file="/pages/common/head.jsp"%>

    <style>
        .addToCart{
             background-color:transparent;
             border:0px;
             font-size:16px;
             font-weight: bold;
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

            $("#goods_search").click(function (){
                if(${sessionScope.username == null} ){
                    alert("请先登录！");
                    return false;
                }else {
                    date = new Date();
                    $("#date").val(date.toLocaleString());
                }
            })

            $(".input_content").change(function (){
                //获取输入框的value属性（也就是输入内容
                var value = $(".input_content").attr("value");
                //赋值给表单项target的value属性
                $("#target").attr("value",value)
                // alert("target的值为"+$("#target").attr("value"));
            })

            $("button.addToCart").click(function (){
                if(${sessionScope.username != null} ){
                    var stock = $(this).attr("stock");
                    if (stock>0){
                        var goodsId = $(this).attr("goodsId");
                        location.href = ("${basePath}cartServlet?action=addItem&id="+goodsId+"&stock="+stock);
                        alert("成功加入购物车");
                    }else if (stock == 0){
                        alert("商品暂时缺货！")
                    }
                }else{
                    alert("请先登录！");
                }
            })

            $(".goods_img")
                .mouseover(function(event){
                    $(this).parent().next()
                        .show()
                        .css("left",event.pageX+10)
                        .css("top",event.pageY+10);
                })
                .mousemove(function(event){
                    $(this).parent().next()
                        .css("left",event.pageX+10)
                        .css("top",event.pageY+10);
                })
                .mouseout(function(){
                    $(this).parent().next()
                        .hide();
                });
        })
    </script>
</head>
<body>
    <div id="header">
        <div id="header_title">
            <div id="header_title_blank"></div>
            <a href="client/goodsServlet?action=page">电子商城</a>
<%--            在本页面点击这个反而是不用做js，因为点击就直接刷新，其他地方就检测一下--%>
            <a href="recommendServlet?action=recommend" id="recommend">刷新商品</a>
        </div>
        <div id="blank">

        </div>
        <div id="jump_message">
            <c:if test="${empty sessionScope.username}">
                <span>你好，请登录</span>
                <a href="pages/user/register.jsp">用户注册</a>
                <a href="pages/user/login.jsp">用户登录</a>
                <a href="pages/seller_login/login.jsp">商家登录</a>
                <a href="pages/manager_login/login.jsp">管理员登录</a>
            </c:if>
            <c:if test="${not empty sessionScope.username}">
                <span>欢迎您，${sessionScope.username}</span>
                <a href="pages/cart/cart.jsp">购物车</a>
                <a href="orderServlet?action=queryByUserId">我的订单</a>
                <a href="userServlet?action=logout">退出登录</a>
            </c:if>

            <div id="jump_message_blank"></div>
        </div>
    </div>

    <div id="a">
        <div  style="width: 400px;margin: 0 auto;text-align: center">
            <form action="client/goodsServlet" method="get">
                <input type="hidden" name="action" value="pageByName">
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
    </div>

    <main id="main">
        <div id="main_inner">
            <c:forEach items="${requestScope.list}" var="goods">
                <div class="goods_list">
                    <div class="goods_list_info">
                        <div class="img_div">
                            <img src="${goods.imgPath}" class="goods_img">
                        </div>
                        <div class="showBig" style="position: absolute;display: none">
                            <img src="${goods.imgPath}" style="max-width: 300px;">
                        </div>
                        <br>
                        <div class="goods_info">
                            <div class="goods_name">
                                <span>名称：</span>
                                <span>${goods.name}</span>
                            </div>
                            <div class="goods_category">
                                <span>类型：</span>
                                <span>
                                <c:if test="${goods.categoryId==1}">食品</c:if>
                                <c:if test="${goods.categoryId==2}">服装</c:if>
                                <c:if test="${goods.categoryId==3}">日用品</c:if>
                                <c:if test="${goods.categoryId==4}">家具</c:if>
                                <c:if test="${goods.categoryId==5}">电器</c:if>
                                <c:if test="${goods.categoryId==6}">娱乐</c:if>
                            </span>
                            </div>
                            <div class="goods_price">
                                <span>价格：</span>
                                <span>${goods.price}</span>
                            </div>
                            <div class="goods_sales">
                                <span>销量：</span>
                                <span>${goods.sales}</span>
                            </div>
                            <div class="goods_stock">
                                <span>库存：</span>
                                <span>${goods.stock}</span>
                            </div>
                            <div class="goods_add">
                                <button goodsId="${goods.id}" stock="${goods.stock}" class="addToCart">加入购物车</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </main>

    <div id="b"></div>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
