<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script>
        function checkContent(){
            var content = $("textarea[name='content']").val();
            var flag = content != "";
            if(flag){
                $("#content").css("border","1px solid green");
            }else{
                $("#content").css("border","1px solid red" );
            }
            return flag;
        }

        $(function (){
            $("#myform").submit(function (){
                if(checkContent()){
                    var flag = confirm("确定回复吗?");
                    if(flag){
                        var fid = ${param.fid};
                        var content = $("#content").val();
                        $.post("adminDealFeed",{"content":content,"fid":fid},function () {
                            alert("回复成功!");
                            location.href = "adminFeedData";
                        });

                    }
                }
                return false;
            });

            $("#content").blur(checkContent);

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
            <div class="crumb-list"><i class="icon-font"></i><a>首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">投诉处理</a><span class="crumb-step"></span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th>回复内容：</th>
                            <td><textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="20"></textarea></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>

