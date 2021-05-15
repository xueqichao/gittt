package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.service.ArticleService;
import com.xueqichao.HealthServiceSystem.service.UserService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/workerAddArticle")
public class WorkerAddArticle extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        String author = (String) request.getSession().getAttribute("currentUser");
        String head = request.getParameter("head");
        String content = request.getParameter("content");
        String type = request.getParameter("type");

        ArticleService articleService = new ArticleService();

        Article article = new Article(head,content,type,author);

        articleService.addArticle(article);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}
