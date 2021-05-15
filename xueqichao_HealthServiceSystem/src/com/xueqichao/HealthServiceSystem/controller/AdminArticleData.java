package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.service.ArticleService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminArticleData")
public class AdminArticleData extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        int page = 1;
        int count = 5;

        String cp = request.getParameter("cp");

        if(cp != null){
            page = Integer.parseInt(cp);
        }

        ArticleService articleService = new ArticleService();

        int arr[] = articleService.totalPage(count);

        request.setAttribute("currentpage",page);
        request.setAttribute("tsum",arr[0]);
        request.setAttribute("tpage",arr[1]);

        List<Article> list = articleService.findByPage(page,count);

        request.setAttribute("ArticleData",list);

        request.getRequestDispatcher("adCoArticle.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}