<%@ page import="com.xueqichao.HealthServiceSystem.eneity.Support" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xueqichao.HealthServiceSystem.eneity.User" %>
<%@ page import="com.xueqichao.HealthServiceSystem.eneity.Cang" %>
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
            $(".link-zan").click(function (){
                var action = $(this).html();
                var aid = $(this).prop("id");
                var support = parseInt($("td[id="+aid+"]").html());
                if(action == "点赞"){
                    var upsupport = support + 1;
                    $("td[id="+aid+"]").html(upsupport);
                    $(this).html("取消点赞");
                    $.post("addSupport",{"aid":aid});
                }else{
                    var upsupport = support - 1;
                    $("td[id="+aid+"]").html(upsupport);
                    $(this).html("点赞");
                    $.post("cancelSupport",{"aid":aid});
                }
            });


            $(".link-cang").click(function (){
                var action = $(this).html();
                var aid = $(this).prop("id");
                var cang = parseInt($("td[class="+aid+"]").html());
                if(action == "收藏"){
                    var upcang = cang + 1;
                    $("td[class="+aid+"]").html(upcang);
                    $(this).html("取消收藏");
                    $.post("addCang",{"aid":aid});
                }else{
                    var upcang = cang - 1;
                    $("td[class="+aid+"]").html(upcang);
                    $(this).html("收藏");
                    $.post("delCang",{"aid":aid});
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
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">文章查看</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="70">关键字:</th>
                            <form action="userArticleData">
                            <td><input class="common-text" placeholder="关键字" name="keyword" value="${param.keyword}" id="2" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                            </form>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>序号</th>
                            <th>文章标题</th>
                            <th>点赞数</th>
                            <th>浏览量</th>
                            <th>收藏数</th>
                            <th>类型</th>
                            <th>作者</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${requestScope.ArticleData}" var="data" varStatus="s">
                            <tr id="${data.aid}">
                                <c:set var="currentAid" scope="request" value="${data.aid}"></c:set>
                                <td>${s.count}</td>
                                <td>${data.head}</td>
                                <td id="${data.aid}">${data.supports}</td>
                                <td>${data.views}</td>
                                <td class="${data.aid}">${data.collections}</td>
                                <td>${data.type}</td>
                                <td>${data.author}</td>
                                <td>${data.date}</td>
                                <td>
                                    <% boolean flag = false;
                                        List<Support> supportList = (List<Support>) request.getAttribute("supportList");
                                        User user = (User) request.getSession().getAttribute("User");
                                        int uid = user.getUid();
                                        int aid = (int) request.getAttribute("currentAid");
                                        for(Support support : supportList){
                                            if(support.getUid() == uid && support.getAid() == aid){
                                                flag = true;
                                            }
                                        }
                                        request.setAttribute("flag",flag);
                                    %>
                                    <c:if test="${requestScope.flag}"><a class="link-zan" id="${data.aid}" href="javascript:void(0);">取消点赞</a></c:if>
                                    <c:if test="${!requestScope.flag}"><a class="link-zan" id="${data.aid}" href="javascript:void(0);">点赞</a></c:if>

                                    <% boolean flagg = false;
                                        List<Cang> cangList = (List<Cang>) request.getAttribute("cangList");
                                        user = (User) request.getSession().getAttribute("User");
                                        uid = user.getUid();
                                        aid = (int) request.getAttribute("currentAid");
                                        for(Cang cang : cangList){
                                            if(cang.getUid() == uid && cang.getAid() == aid){
                                                flagg = true;
                                            }
                                        }
                                        request.setAttribute("flagg",flagg);
                                    %>
                                    <c:if test="${requestScope.flagg}"><a class="link-cang" id="${data.aid}" href="javascript:void(0);">取消收藏</a></c:if>
                                    <c:if test="${!requestScope.flagg}"><a class="link-cang" id="${data.aid}" href="javascript:void(0);">收藏</a></c:if>

                                    <a class="link-look" id="${data.aid}" href="userLookArticle?aid=${data.aid}">查看</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="list-page">
                        共${requestScope.tsum}条记录,当前${requestScope.currentpage}/${requestScope.tpage}页
                        <a href="userArticleData?cp=1${keywords}">首页</a>
                        <a href="userArticleData?cp=${requestScope.currentpage-1<1?1:requestScope.currentpage-1}${keywords}">上一页</a>
                        <a href="userArticleData?cp=${requestScope.currentpage+1>requestScope.tpage?requestScope.tpage:requestScope.currentpage+1}${keywords}">下一页</a>
                        <a href="userArticleData?cp=${requestScope.tpage}${keywords}">尾页</a>

                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>

</body>
</html>
