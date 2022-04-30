<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function() {
            $("#submit").click(function () {
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{3,8}$/;
                if(!usernamePatt.test(usernameText)){
                    $("#errorMessage").text("用户名不合法！只能输入长度在3-8之间的数字、字母、下划线")
                    return false;
                }
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{3,8}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("#errorMessage").text("密码不合法！只能输入长度在3-8之间的数字、字母、下划线");
                    return false;
                }

                var checkPasswordText = $("#checkPassword").val();
                //2 和密码相比较
                if (checkPasswordText != passwordText) {
                    $("#errorMessage").text("确认密码和密码不一致！");
                    return false;
                }

                var emailText = $("#email").val();
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)) {
                    $("#errorMessage").text("邮箱格式不合法！");
                    return false;
                }

                var codeText = $("#code").val();
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $("#errorMessage").text("验证码不能为空！");
                    return false;
                }
                $("#errorMessage").text("");

            })

            $("#codeImg").click(function (){
                this.src="${basePath}kaptcha.jpg?x="+new Date();
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
        <form action="userServlet" method="post" style="width: 400px;height: 400px;margin: 100px auto auto auto">
            <input type="hidden" name="action" value="register">
            <div style="width: 400px ;height: 100px;text-align: center">
                <div style="width: 400px;height: 50px;font-size: 50px">注册</div>
                <div id="errorMessage" style="color: red;width: 400px;height: 50px;line-height: 50px;">${requestScope.msg}</div>
            </div>

            <div style="width: 400px;height: 250px;text-align: center">
                <table style="margin: 0 auto">
                    <tr>
                        <td>用户名：</td>
                        <td><input type="text" name="username" id="username"></td>
                    </tr>
                    <tr><td><br></td></tr>
                    <tr>
                        <td>密码：</td>
                        <td><input type="password" name="password" id="password"></td>
                    </tr>
                    <tr><td><br></td></tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input type="password" name="checkPassword" id="checkPassword"></td>
                    </tr>
                    <tr><td><br></td></tr>
                    <tr>
                        <td>邮箱：</td>
                        <td><input type="text" name="email" id="email"></td>
                    </tr>
                    <tr><td><br></td></tr>
                    <tr>
                        <td>验证码：</td>
                        <td><input type="text" name="code" id="code"></td>
                    </tr>
                    <tr><td><br></td></tr>
                    <tr>
                        <td></td>
                        <td><img src="kaptcha.jpg" style=" width: 177px" alt="验证码" id="codeImg"></td>
                    </tr>
                </table>
            </div>
            <div style="width: 400px;height: 50px;text-align: center;line-height: 50px">
                <input type="submit" value="提交" id="submit">
            </div>
        </form>
    </div>

    <div id="b"></div>

    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>