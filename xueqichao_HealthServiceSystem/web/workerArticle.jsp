<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章管理</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script>
        $(function (){
            $(".link-del").click(function (){
                var flag = confirm("你确认删除吗？");
                if(flag){
                    var aid = $(this).prop("id");
                    $.post("delArticle",{"aid":aid});
                    $("tr[id="+aid+"]").remove();
                    location.href="workerArticleData";
                }
            });

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
                <li><a href="#">工作者</a></li>
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
                        <li><a href="workerArticleData"><i class="icon-font">&#xe008;</i>我的文章</a></li>
                        <li><a href="workerStatementData"><i class="icon-font">&#xe004;</i>评论管理</a></li>
                        <li><a href="workerConsult"><i class="icon-font">&#xe012;</i>咨询管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">我的文章</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="getArticleTypeData"><i class="icon-font"></i>发布文章</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>序号</th>
                            <th>文章标题</th>
                            <th>点赞数</th>
                            <th>浏览量</th>
                            <th>收藏数</th>
                            <th>类型</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${requestScope.articleData}" var="data" varStatus="s">
                            <tr id="${data.aid}">
                                <td>${s.count}</td>
                                <td>${data.head}</td>
                                <td>${data.supports}</td>
                                <td>${data.views}</td>
                                <td>${data.collections}</td>
                                <td>${data.type}</td>
                                <td>${data.date}</td>
                                <td>
                                    <a class="link-del" id="${data.aid}" href="javascript:void(0);">删除</a>
                                    <a class="link-look" id="${data.aid}" href="lookArticle?aid=${data.aid}">查看</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="list-page">
                        共${requestScope.tsum}条记录,当前${requestScope.currentpage}/${requestScope.tpage}页
                        <a href="workerArticleData?cp=1">首页</a>
                        <a href="workerArticleData?cp=${requestScope.currentpage-1<1?1:requestScope.currentpage-1}">上一页</a>
                        <a href="workerArticleData?cp=${requestScope.currentpage+1>requestScope.tpage?requestScope.tpage:requestScope.currentpage+1}">下一页</a>
                        <a href="workerArticleData?cp=${requestScope.tpage}">尾页</a>

                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>

</body>
</html>
