package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.service.ArticleTypeService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addArticleType")
public class AddArticleType extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        ArticleTypeService articleTypeService = new ArticleTypeService();

        String typename = request.getParameter("typeName");

        articleTypeService.addType(typename);

        response.sendRedirect("articleTypeData");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}