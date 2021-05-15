<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/admin_login.css"/>
    <script>
        function checkUsername(){
            var username = $("#registerUsername").val();
            var reg_username = /^\w{8,20}$/;
            var flag = reg_username.test(username);
            if(flag){
                $("#registerUsername").css("border","1px solid green");
            }else{
                $("#registerUsername").css("border","1px solid red" );
            }
            return flag;
        }

        function checkPassword(){
            var password = $("#registerPassword").val();
            var reg_password = /^\w{8,20}$/;
            var flag = reg_password.test(password);
            if(flag){
                $("#registerPassword").css("border","1px solid green");
            }else{
                $("#registerPassword").css("border","1px solid red" );
            }
            return flag;
        }

        function checkIdentity(){
            var identity = $("#registerIdentity").val();
            var flag = (identity != "");
            if(flag){
                $("#registerIdentity").css("border","1px solid green");
            }else{
                $("#registerIdentity").css("border","1px solid red" );
            }
            return flag;
        }

        $(function (){
            $("#registerForm").submit(function (){
                if(checkPassword() && checkUsername() && checkIdentity()){
                    $.post("registerServlet",$(this).serialize(),function (data) {
                        if(data.userExist){
                            alert(data.msg);
                        }else{
                            alert(data.msg);
                            window.location = "login.jsp";
                        }
                    },"json");
                    }
                return false;
            });

            $("#registerUsername").blur(checkUsername);
            $("#registerPassword").blur(checkPassword);
            $("#registerIdentity").blur(checkIdentity);

        });

    </script>

</head>
<body>
<div class="admin_login_wrap">
    <h1>注册页面</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form method="post" id="registerForm">
                <ul class="admin_items">
                    <li>
                        <label for="registerUsername">用户名：</label>
                        <input type="text" name="registerUsername" id="registerUsername" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="registerPassword">密码：</label>
                        <input type="password" name="registerPassword" id="registerPassword" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="registerIdentity">身份：</label>
                        <select name="registerIdentity" id="registerIdentity" class="admin_input_style">
                            <option value="">--请选择--</option>
                            <option value="普通用户">普通用户</option>
                            <option value="工作者">工作者</option>
                        </select>
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="注册" class="btn btn-primary" id="registerBtn" />
                    </li>
                    <li>
                        <a href="login.jsp"><input type="button" tabindex="3" value="返回" class="btn btn-primary" /></a>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
</body>
</html>