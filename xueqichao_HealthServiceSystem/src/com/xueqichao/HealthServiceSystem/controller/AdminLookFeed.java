package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Feedback;
import com.xueqichao.HealthServiceSystem.service.FeedBackService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminLookFeed")
public class AdminLookFeed extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        int fid = Integer.parseInt(request.getParameter("fid"));

        FeedBackService feedBackService = new FeedBackService();

        Feedback feedback = feedBackService.find(fid);

        request.setAttribute("feedback",feedback);

        request.getRequestDispatcher("adLookFeedback.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}