<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员登录界面</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
    <script type="text/javascript">
        var ip = "";
        var address = "";
        var date = new Date();
        $(function() {
            //获取ip
            ip = returnCitySN.cip;
            $("#ip").val(ip);

            //获取地址
            address = returnCitySN.cname;
            $("#address").val(address);

            $("#submit").click(function () {
                var nameText = $("#name").val();
                var namePatt = /^\w{3,8}$/;
                if(!namePatt.test(nameText)){
                    $("#errorMessage").text("用户名不合法！")
                    return false;
                }else {
                    date = new Date();
                    $("#date").val(date.toLocaleString());
                }
            })
        })
    </script>

</head>
<body>
    <div id="header">
        <div id="header_title">
            <div id="header_title_blank"></div>
            <a href="index.jsp">回到首页</a>
        </div>
        <div id="blank"></div>
        <div id="jump_message">
            <div id="jump_message_blank"></div>
        </div>
    </div>

    <div id="a"></div>

    <div id="main">
        <form action="managerServlet" method="post" id="login_form" style="width: 400px;height: 250px;margin: 100px auto auto auto">
            <input type="hidden" name="action" value="login">
            <div style="width: 400px;height: 100px;">
                <div style="height: 50px;line-height: 50px;font-size: 50px;text-align: center">管理员登录</div>
                <div id="errorMessage" style="color: red;height: 50px;line-height: 50px;text-align: center">${requestScope.msg}</div>
            </div>
            <div style="width: 400px;height: 100px; display: flex;flex-direction: column;justify-content: center">
                <table id="login_table" style="margin: 0 auto;">
                    <tr>
                        <td>名称：</td>
                        <td><input type="text" name="name" id="name" placeholder="请输入名称" value="${cookie.managerName.value}"></td>
                    </tr>
                    <tr><td><br></td></tr>
                    <tr>
                        <td>密码：</td>
                        <td><input type="password" name="password" id="password" placeholder="请输入密码"></td>
                    </tr>
                </table>
            </div>

            <div style="width: 400px;height: 50px;line-height: 50px;text-align: center">
                <input type="submit" value="提交" id="submit">
            </div>

            <%--            需要获取ip、地址、时间，通过js获取--%>
            <input type="hidden" name="ip" id="ip">
            <input type="hidden" name="address" id="address">
            <input type="hidden" name="date" id="date">

            <%--用户登录--%>
            <input type="hidden" name="operation" value="登录">
            <input type="hidden" name="role" value="管理员">

            <%--        数据库里面还有一个参数，登录的用户id，没法直接传--%>
        </form>
    </div>

    <div id="b"></div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>