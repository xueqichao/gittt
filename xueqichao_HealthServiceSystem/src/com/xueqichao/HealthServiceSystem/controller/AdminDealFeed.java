package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.service.FeedBackService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminDealFeed")
public class AdminDealFeed extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        FeedBackService feedBackService = new FeedBackService();

        int fid = Integer.parseInt(request.getParameter("fid"));

        String feedContent = request.getParameter("content");

        feedBackService.upDataAfterDeal(feedContent,fid);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}