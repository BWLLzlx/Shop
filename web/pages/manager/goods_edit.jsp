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
    <title>商品编辑界面</title>
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

            $("#submit_btn").click(function (){
                var name = $("#name").val();
                var price = $("#price").val();
                var sales = $("#sales").val();
                var stock = $("#stock").val();
                name = $.trim(name);
                price = $.trim(price);
                sales = $.trim(sales);
                stock = $.trim(stock);
                if(name == null || name==""){
                    $("#errorMessage").text("名称不能为空！");
                    return false;
                }
                if(price == null || price==""){
                    $("#errorMessage").text("价格不能为空！");
                    return false;
                }
                if(sales == null || sales==""){
                    $("#errorMessage").text("销量不能为空！");
                    return false;
                }
                if(stock == null || stock==""){
                    $("#errorMessage").text("库存不能为空！");
                    return false;
                }
                if($("#select_category").val()=="0"){
                    $("#errorMessage").text("商品类型不能为空！");
                    return false;
                }
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
        <div id="blank"></div>
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

    <div id="main">
        <div style="width: 400px;height: 700px;margin: 0 auto">
            <form action="manager/goodsServlet?action=${param.method}" method="post" enctype="multipart/form-data">

                <input type="hidden" name="id" value="${param.id}" id="id">
                <input type="hidden" name="sellerId" value="${param.sellerId}" id="sellerId">

                <div style="width: 400px;height: 100px;">
                    <div style="font-size: 50px;text-align: center">编辑商品信息</div>
                    <div id="errorMessage" style="color: red;height: 50px;line-height: 50px;text-align: center">${requestScope.msg}</div>
                </div>
                <div style="width: 400px;height: 250px;">
                    <img src="${empty requestScope.goods.imgPath?"static/img/1.png":requestScope.goods.imgPath}" style="max-height: 250px;margin: 0 auto;display: block" id="photo">
                </div>
                <br>
                <div style="width: 300px;height: 320px;text-align: center;margin: 0 auto">
                    <table style="text-align: center;">
                        <tr>
                            <td>名称：</td>
                            <td><input type="text" name="name" value="${requestScope.goods.name}" id="name"></td>
                        </tr>
                        <tr><td><br></td></tr>
                        <tr>
                            <td>价格：</td>
                            <td><input type="text" name="price" value="${requestScope.goods.price}" id="price"></td>
                        </tr>
                        <tr><td><br></td></tr>
                        <tr>
                            <td>销量：</td>
                            <td><input type="text" name="sales" value="${requestScope.goods.sales}" id="sales"></td>
                        </tr>
                        <tr><td><br></td></tr>
                        <tr>
                            <td>库存：</td>
                            <td><input type="text" name="stock" value="${requestScope.goods.stock}" id="stock"></td>
                        </tr>
                        <tr><td><br></td></tr>
                        <tr>
                            <td>类型：</td>
                            <td>
                                <select name="categoryId" id="select_category" >
                                    <option value="0">无选择</option>
                                    <option value="1">食品</option>
                                    <option value="2">服装</option>
                                    <option value="3">日用品</option>
                                    <option value="4">家具</option>
                                    <option value="5">电器</option>
                                    <option value="6">娱乐</option>
                                </select>
                            </td>
                        </tr>
                        <tr><td><br></td></tr>
                        <tr>
                            <td>图片：</td>
                            <td><input type="file" name="photo" style="width: 177px" id="choose_photo"></td>
                            <script type="text/javascript">

                                function ProcessFile(e) {
                                    //创建文件对象
                                    var file = document.getElementById('choose_photo').files[0];
                                    var photo =  document.getElementById("photo");
                                    //如果有获取到东西
                                    if (file) {
                                        //创建文件读取对象
                                        var reader = new FileReader();
                                        //加载完成后
                                        reader.onload = function (event) {
                                            var txt = event.target.result;
                                            photo.src = txt;
                                        };
                                    }
                                    reader.readAsDataURL(file);
                                }

                                function contentLoaded() {
                                    document.getElementById('choose_photo').addEventListener('change',
                                        ProcessFile, false);
                                }

                                window.addEventListener("DOMContentLoaded", contentLoaded, false);

                            </script>
                        </tr>
                    </table>
                    <br>

                    <input type="submit" name="submit" value="提交" id="submit_btn" style="margin: 0 auto">

                </div>




                <input type="hidden" name="ip" id="ip">
                <input type="hidden" name="date" id="date">
                <input type="hidden" name="role" value="商家">
                <input type="hidden" name="roleId" value="${param.sellerId}">
                <input type="hidden" name="operate" value="${param.method=="addWithPhoto"?"添加":"修改"}">
                <input type="hidden" name="target" id="target" value="商品${requestScope.goods.id}">


            </form>
        </div>
    </div>

    <div id="b"></div>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
