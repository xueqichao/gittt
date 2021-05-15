package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.service.ArticleService;
import com.xueqichao.HealthServiceSystem.service.ArticleTypeService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lookArticle")
public class LookArticle extends HttpServlet
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

        request.getRequestDispatcher("lookArticle.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}