package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.eneity.Statement;
import com.xueqichao.HealthServiceSystem.service.ArticleService;
import com.xueqichao.HealthServiceSystem.service.ArticleTypeService;
import com.xueqichao.HealthServiceSystem.service.StatementService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userLookArticle")
public class UserLookArticle extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        int aid = Integer.parseInt(request.getParameter("aid"));

        ArticleService articleService = new ArticleService();

        ArticleTypeService articleTypeService = new ArticleTypeService();

        Article article = articleService.find(aid);

        articleService.upDataLookNum(article);

        int lookNum = articleService.findSumLookNum(article.getType());

        articleTypeService.upDataLookNum(lookNum,article.getType());

        request.setAttribute("article",article);

        StatementService statementService = new StatementService();

        List<Statement> list = statementService.FindByAid(aid);

        request.setAttribute("statementList",list);

        request.getRequestDispatcher("userLookArticle.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}