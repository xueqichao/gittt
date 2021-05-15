<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
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
            $("#statementForm").submit(function (){
                if(checkContent()){
                    var flag = confirm("确定发布评论吗?");
                    if(flag){
                        var content = $("textarea[name='content']").val();
                        var aid = ${param.aid};
                        $.post("addStatement",{"content":content,"aid":aid},function () {
                            alert("评论成功!");
                            location.reload();
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
                <li><a href="#">用户</a></li>
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
                        <li><a href="userArticleData"><i class="icon-font">&#xe008;</i>文章列表</a></li>
                        <li><a href="userFeedData"><i class="icon-font">&#xe004;</i>投诉管理</a></li>
                        <li><a href="userConsult"><i class="icon-font">&#xe012;</i>咨询管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a>首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">投诉查看</span></div>
        </div>
        <div class="result-wrap">

            <div class="result-content">
                <h1 align="center">回复内容:</h1>
                <form id="statementForm">
                    <textarea rows="7" cols="50" disabled name="content" id="content">${requestScope.currentFeed.feedbackContent}</textarea>
                </form>

            </div>

        </div>
    </div>
    <!--/main-->
</div>

</body>
</html>

