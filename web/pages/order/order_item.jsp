<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情页面</title>
	<%@ include file="/pages/common/head.jsp"%>
	<c:if test="${not empty sessionScope.username}">
		<style>
			#header{
				background-color: #bfa;
			}
		</style>
	</c:if>

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
			width: 200px;
		}
	</style>

</head>
<body>
	<div id="header">
			<div id="header_title">
				<div id="header_title_blank"></div>
				<c:if test="${not empty sessionScope.user}">
					<a href="client/goodsServlet?action=page">电子商城</a>
					<a href="recommendServlet?action=recommend" id="recommend">个性推荐</a>
				</c:if>
				<c:if test="${not empty sessionScope.manager}">
					<a href="pages/manager/manager.jsp">后台管理</a>
				</c:if>
				<c:if test="${not empty sessionScope.seller}">
					<a href="pages/seller_login/seller.jsp">商家首页</a>
				</c:if>
			</div>

			<div id="blank">
				<c:if test="${not empty sessionScope.user}">
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
				</c:if>
			</div>

			<div id="jump_message">
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎您，${sessionScope.username}</span>
					<a href="pages/cart/cart.jsp">购物车</a>
					<a href="orderServlet?action=queryByUserId">我的订单</a>
					<a href="userServlet?action=logout">退出登录</a>
				</c:if>
				<c:if test="${not empty sessionScope.manager}">
					<span>欢迎您，${sessionScope.manager.name}</span>
					<a href="manager/goodsServlet?action=page">商品管理</a>
					<a href="orderServlet?action=page">订单管理</a>
					<a href="sellerServlet?action=queryForAllSeller">商家管理</a>
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
		<div id="main_inner" style="overflow-y: hidden">
			<table >

				<tr>
					<td>商品编号</td>
					<td>商品名称</td>
					<td>用户编号</td>
					<td>卖家编号</td>
					<td>数量</td>
					<td>单价</td>
					<td>总价</td>
					<td>下单时间</td>
				</tr>

				<c:forEach items="${requestScope.orderItems}" var="orderItem">
					<tr><td><br></td></tr>
					<tr>
						<td>${orderItem.goodsId}</td>
						<td>${orderItem.name}</td>
						<td>${orderItem.userId}</td>
						<td>${orderItem.sellerId}</td>
						<td>${orderItem.count}</td>
						<td>${orderItem.price}</td>
						<td>${orderItem.totalPrice}</td>
						<td>${orderItem.createTime}</td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
	<div id="b"></div>
	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>