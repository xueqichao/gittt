<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>

    <script>
        function checkHead(){
            var head = $("#head").val();
            var flag = head != "";
            if(flag){
                $("#head").css("border","1px solid green");
            }else{
                $("#head").css("border","1px solid red" );
            }
            return flag;
        }
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
                if(checkHead() && checkContent()){
                    var flag = confirm("确定投诉吗?");
                    if(flag){

                        $.post("userAddFeed",$(this).serialize(),function () {
                            alert("投诉成功!");
                            location.href = "userFeedData";
                        });

                    }

                }
                return false;
            });

            $("#head").blur(checkHead);
            $("textarea[name='content']").blur(checkContent);

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
            <div class="crumb-list"><i class="icon-font"></i><a>首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">我的投诉</a><span class="crumb-step">&gt;</span><span>进行投诉</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form method="post" id="myform" name="myform">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>主题：</th>
                            <td>
                                <input class="common-text required" id="head" name="head" size="50" value="" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th>内容：</th>
                            <td><textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10"></textarea></td>
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

<script type="text/javascript">
    window.UEDITOR_HOME_URL = "/xueqichao_HealthServiceSystem/web/ueditor";
    var ue = UE.getEditor('content');
</script>
</body>
</html>
