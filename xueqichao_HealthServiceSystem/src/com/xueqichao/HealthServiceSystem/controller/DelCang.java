package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.eneity.User;
import com.xueqichao.HealthServiceSystem.service.ArticleService;
import com.xueqichao.HealthServiceSystem.service.CangService;
import com.xueqichao.HealthServiceSystem.service.SupportService;
import com.xueqichao.HealthServiceSystem.service.UserService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delCang")
public class DelCang extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        UserService userService = new UserService();

        String username = (String) request.getSession().getAttribute("currentUser");

        User user = userService.find(username);

        int aid = Integer.parseInt(request.getParameter("aid"));

        CangService cangService = new CangService();

        cangService.cancelCang(aid,user.getUid());

        ArticleService articleService = new ArticleService();

        Article article = articleService.find(aid);

        articleService.delCang(article);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}