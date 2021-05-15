<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/admin_login.css"/>
  <script>
    $(function (){
      $("#submit").click(function () {
        $.post("loginServlet",$("#loginForm").serialize(),function (data) {
          if(data.userExist){
            if(data.identity == "游客"){
              location.href = "success.jsp";
            }else if(data.identity == "普通用户"){
              location.href = "userMenu.jsp";
            }else if(data.identity == "工作者"){
              location.href = "workerMenu.jsp";
            }else if(data.identity == "管理员"){
              location.href = "adminMenu.jsp";
            }else{
              alert("请选择您的身份");
            }
          }else{
            alert("账号密码错误！");
          }
        },"json");
      });
    });

  </script>
</head>
<body>

<div class="admin_login_wrap">
  <h1>登录页面</h1>
  <div class="adming_login_border">
    <div class="admin_input">
      <form action="loginServlet" method="post" id="loginForm">
        <ul class="admin_items">
          <li>
            <label for="username">用户名：</label>
            <input type="text" name="username" id="username" size="40" class="admin_input_style" />
          </li>
          <li>
            <label for="password">密码：</label>
            <input type="password" name="password" id="password" size="40" class="admin_input_style" />
          </li>
          <li>
            <label for="identity">身份：</label>
            <select name="identity" id="identity" class="admin_input_style">
              <option value="">--请选择--</option>
              <option value="普通用户">普通用户</option>
              <option value="工作者">工作者</option>
              <option value="管理员">管理员</option>
              <option value="游客">游客</option>
            </select>
          </li>
          <li>
            <input type="button" tabindex="3" value="提交" class="btn btn-primary" id="submit" />
          </li>
          <li>
            <a href="register.jsp"><input type="button" tabindex="3" value="注册" class="btn btn-primary" /></a>
          </li>
        </ul>
      </form>
    </div>
  </div>
</div>
</body>
</html>