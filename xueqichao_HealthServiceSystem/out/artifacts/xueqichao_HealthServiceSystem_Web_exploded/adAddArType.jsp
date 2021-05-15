<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script>
        function checkTypename(){
            var typeName = $("#typeName").val();
            var flag = (typeName != "");
            if(flag){
                $.post("articleTypeIsExist",{typename:typeName},function (data) {
                    if(data.typeExist){
                        $("#typeName").css("border","1px solid red");
                        $("#errorMsg").html("该类型已存在").css("color","red");
                    }else{
                        $("#typeName").css("border","1px solid green");
                        $("#errorMsg").html("");
                    }
                },"json");
            }else{
                $("#errorMsg").html("类型名不能为空").css("color","red");
                $("#typeName").css("border","1px solid red" );
            }
        }


        $(function (){
            $("#myform").submit(function (){
                return $("#errorMsg").html() =="";
            });

            $("#typeName").blur(checkTypename);

        });

    </script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a href="#" target="_blank">首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">管理员</a></li>
                <li><a href="login.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="articleTypeData"><i class="icon-font">&#xe008;</i>文章类型管理</a></li>
                        <li><a href="userDataServlet"><i class="icon-font">&#xe005;</i>用户管理</a></li>
                        <li><a href="adminArticleData"><i class="icon-font">&#xe006;</i>文章管理</a></li>
                        <li><a href="adminFeedData"><i class="icon-font">&#xe004;</i>投诉管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">文章类型管理</a><span class="crumb-step">&gt;</span><span>新增文章类型</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="addArticleType" method="post" id="myform" name="myform">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>类型名称：</th>
                            <td>
                                <input class="common-text required" name="typeName" size="50" type="text" id="typeName">
                                <span id="errorMsg">&nbsp</span>
                            </td>

                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                <a href="articleTypeData"><input class="btn btn6" value="返回" type="button"></a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>