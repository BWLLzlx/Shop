<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <%@include file="/pages/common/head.jsp"%>
<%--    引入--%>
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
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{3,8}$/;
                if(!usernamePatt.test(usernameText)){
                    $("#errorMessage").text("用户名不合法！")
                    return false;
                }else {
                    //获取时间
                    date = new Date();
                    // alert(date.toLocaleString());
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
        <div id="main_inner">
            <form action="userServlet" method="post" id="login_form" style="width: 400px; height: 250px;margin: 100px auto auto auto;">
                <input type="hidden" name="action" value="login">
                <div style="width: 400px;height: 100px;margin: 0 auto;">
                    <div style="width: 400px;height: 50px;text-align: center;"><span style="font-size: 50px">用户登录</span></div>
                    <div id="errorMessage" style="color: red;width:400px;height: 50px; text-align: center; line-height: 50px;">${requestScope.msg}</div>
                </div>
                <div style="width: 400px; height: 100px;display: flex;flex-direction: column;justify-content: center;">
                    <table id="login_table" style="margin: 0 auto">
                        <tr>
                            <td>用户名：</td>
                            <td><input type="text" name="username" id="username" placeholder="请输入用户名" value="${cookie.username.value}"></td>
                        </tr>
                        <tr><td><br></td></tr>
                        <tr>
                            <td>密码：</td>
                            <td><input type="password" name="password" id="password" placeholder="请输入密码"></td>
                        </tr>
                    </table>
                </div>
                <div style="margin: 0 auto;width: 400px; height: 50px;display: flex;flex-direction: column;justify-content: center;">
                    <input type="submit" value="提交" id="submit" style="margin: 0 auto;display: block;">
                </div>

                <%--            需要获取ip、地址、时间，通过js获取--%>
                <input type="hidden" name="ip" id="ip">
                <input type="hidden" name="address" id="address">
                <input type="hidden" name="date" id="date">

                <%--用户登录--%>
                <input type="hidden" name="operation" value="登录">
                <input type="hidden" name="role" value="用户">

                <%--        数据库里面还有一个参数，登录的用户id，没法直接传--%>


            </form>
        </div>
    </div>

    <div id="b"></div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>