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

@WebServlet("/userLookFeed")
public class UserLookFeed extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        int fid = Integer.parseInt(request.getParameter("fid"));

        FeedBackService feedBackService = new FeedBackService();

        Feedback feedback = feedBackService.find(fid);

        if(feedback.getFeedbackContent()=="" || feedback.getFeedbackContent()==null){
            feedback.setFeedbackContent("还未做出回复，请稍等");
        }

        request.setAttribute("currentFeed",feedback);

        request.getRequestDispatcher("userLookFeed.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}