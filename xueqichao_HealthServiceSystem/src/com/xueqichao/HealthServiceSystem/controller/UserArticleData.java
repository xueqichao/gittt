package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.eneity.Cang;
import com.xueqichao.HealthServiceSystem.eneity.Support;
import com.xueqichao.HealthServiceSystem.service.ArticleService;
import com.xueqichao.HealthServiceSystem.service.CangService;
import com.xueqichao.HealthServiceSystem.service.SupportService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userArticleData")
public class UserArticleData extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        int page = 1;
        int count = 5;
        int[] arr = {0,1};

        String cp = request.getParameter("cp");
        String keyword = request.getParameter("keyword");

        if(cp != null){
            page = Integer.parseInt(cp);
        }

        ArticleService articleService = new ArticleService();

        if(keyword == "" || keyword == null){
            arr = articleService.totalPage(count);

            List<Article> list = articleService.findByPage(page,count);

            request.setAttribute("ArticleData",list);


        }else{
            arr = articleService.userKeyTotalPage(count,keyword);

            List<Article> list = articleService.findByPage(page,count,keyword);

            request.setAttribute("ArticleData",list);

            request.setAttribute("keywords","&keyword="+keyword);



        }

        request.setAttribute("currentpage",page);
        request.setAttribute("tsum",arr[0]);
        request.setAttribute("tpage",arr[1]);

        SupportService supportService = new SupportService();

        List<Support> supportList = supportService.find();

        request.setAttribute("supportList",supportList);

        CangService cangService = new CangService();

        List<Cang> cangList = cangService.find();

        request.setAttribute("cangList",cangList);

        request.getRequestDispatcher("userArticle.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}