package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.eneity.Statement;
import com.xueqichao.HealthServiceSystem.service.ArticleService;
import com.xueqichao.HealthServiceSystem.service.StatementService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addStatement")
public class AddStatement extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        String content = request.getParameter("content");

        String username = (String) request.getSession().getAttribute("currentUser");

        int aid = Integer.parseInt(request.getParameter("aid"));

        ArticleService articleService = new ArticleService();

        Article article = articleService.find(aid);

        StatementService statementService = new StatementService();

        Statement statement = new Statement(username,content,aid,article.getAuthor());

        statementService.addStatement(statement);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}