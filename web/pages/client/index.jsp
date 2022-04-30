<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
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
            $(".addToCart").click(function (){
                if(${sessionScope.username != null} ){
                    var stock = $(this).attr("stock");
                    if (stock>0){
                        var goodsId = $(this).attr("goodsId");
                        location.href = ("${basePath}cartServlet?action=addItem&id="+goodsId+"&stock="+stock);
                        alert("成功加入购物车");
                    }else if (stock == 0){
                        alert("商品暂时缺货！")
                    }
                    //点击加入购物车之后，发送表单添加cartItem到购物车列表
                    alert("成功加入购物车！")
                    // alert($(this).prev().prev().val())
                }else{
                    alert("请先登录！");
                }
            })

            $(".input_content").change(function (){
                //获取输入框的value属性（也就是输入内容
                var value = $(".input_content").attr("value");
                //赋值给表单项target的value属性
                $("#target").attr("value",value)
                // alert("target的值为"+$("#target").attr("value"));
            })

            $("#goods_search").click(function (){
                if(${sessionScope.username == null} ){
                    alert("请先登录！");
                    return false;
                }else {
                    date = new Date();
                    $("#date").val(date.toLocaleString());
                }
            })

            $("#recommend").click(function (){
                if (${sessionScope.username!=null}){//如果登录了
                    if(${sessionScope.user.categoryId==1} ){//如果登录了还是1的类型（全0）说明没选喜欢的类型是什么
                        if(confirm("没有选择喜欢的商品类型！是否要前往选择")== true){//如果选择前往选择就跳转到选择，然后不去推荐了
                            location.href = "${basePath}pages/client/like.jsp";
                            return false;
                        }else {//已经选了，就直接继续跳转到推荐
                        }
                    }
                }else {//如果没登陆
                    if( confirm("登录后推荐将会更加个性化！是否要前往登录") ==true ){
                        location.href = "${basePath}pages/user/login.jsp";
                        return false;
                    }else{//继续
                    }
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
        <%--头部区域--%>
        <div id="header">
            <div id="header_title">
                <div id="header_title_blank"></div>
                <a href="client/goodsServlet?action=page">电子商城</a>
                <a href="recommendServlet?action=recommend" id="recommend">个性推荐</a>
            </div>
            <div id="blank">
<%--                原本是get，改成post看看有没有问题--%>
<%--                从结果来看，没问题--%>
<%--                <form action="client/goodsServlet" method="post">--%>
<%--                    <input type="hidden" name="action" value="pageByName">--%>
<%--                    商品名称：--%>
<%--                    <input type="text" id="goods_name" name="goods_name" value="${requestScope.goods_name}" class="input_content">--%>

<%--                    <input type="hidden" name="ip" id="ip">--%>
<%--                    <input type="hidden" name="date" id="date">--%>
<%--                    <input type="hidden" name="role" value="用户">--%>
<%--                    <input type="hidden" name="roleId" value="${sessionScope.user.id}">--%>
<%--                    <input type="hidden" name="operate" value="查询">--%>
<%--                    <input type="hidden" name="target" id="target">--%>

<%--                    <input type="submit" value="查询" id="goods_search">--%>
<%--                </form>--%>
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
                <form action="client/goodsServlet" method="post">
                    <input type="hidden" name="action" value="pageByName">
                    <input type="text" id="goods_name" name="goods_name" value="${requestScope.goods_name}" class="input_content" >

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

            <%--主体区域--%>
        <div id="main" >
                <%--商品区域--%>
                <div id="main_inner">
                    <c:if test="${requestScope.page.items.size() == 0}">
                        <div>
                            <span>查不到对应的商品</span>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.page.items.size() != 0}">
                            <c:forEach items="${requestScope.page.items}" var="goods">
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
                                                <%--                                <div class="goods_add">--%>
                                                <%--                                    <button goodsId="${goods.id}"  stock="${goods.stock}" class="addToCart">加入购物车</button>--%>
                                                <%--                                </div>--%>
                                            <div class="goods_test">
                                                <form action="cartServlet">
                                                    <input type="hidden" name="action" value="addItem">
                                                    <input type="hidden" name="id" value="${goods.id}" class="id">
                                                    <input type="hidden" name="number" value="${empty sessionScope.number?goods.stock:sessionScope.number}" class="number">
                                                    <input type="hidden" name="stock" value="${goods.stock}" class="stock">
                                                    <input type="submit" name="submit" value="加入购物车" class="addToCart">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                    </c:if>
                </div>
        </div>

        <%--分页区域--%>
        <%@include file="/pages/common/page_nav.jsp"%>


        <%--底部区域--%>
        <%@include file="/pages/common/footer.jsp"%>
</body>
</html>