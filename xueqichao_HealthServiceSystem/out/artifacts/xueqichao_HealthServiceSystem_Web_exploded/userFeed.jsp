<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>投诉管理</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
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
            <div class="crumb-list"><i class="icon-font"></i><a>首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">我的投诉</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="userAddFeed.jsp"><i class="icon-font"></i>进行投诉</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>序号</th>
                            <th>反馈用户</th>
                            <th>反馈标题</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${requestScope.feedbackList}" var="data" varStatus="s">
                            <tr id="${data.fid}">
                                <td>${s.count}</td>
                                <td>${data.username}</td>
                                <td>${data.head}</td>
                                <c:if test="${data.state==1}"><td id="${data.fid}">已回复</td></c:if>
                                <c:if test="${data.state==0}"><td id="${data.fid}">未回复</td></c:if>
                                <td>
                                    <a class="link-look" id="${data.fid}" href="userLookFeed?fid=${data.fid}">查看回复</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="list-page">
                        共${requestScope.tsum}条记录,当前${requestScope.currentpage}/${requestScope.tpage}页
                        <a href="userFeedData?cp=1">首页</a>
                        <a href="userFeedData?cp=${requestScope.currentpage-1<1?1:requestScope.currentpage-1}">上一页</a>
                        <a href="userFeedData?cp=${requestScope.currentpage+1>requestScope.tpage?requestScope.tpage:requestScope.currentpage+1}">下一页</a>
                        <a href="userFeedData?cp=${requestScope.tpage}">尾页</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>

</body>
</html>


