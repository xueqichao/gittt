package com.xueqichao.HealthServiceSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xueqichao.HealthServiceSystem.eneity.Feedback;
import com.xueqichao.HealthServiceSystem.service.FeedBackService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/feedServlet")
public class FeedServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        int fid = Integer.parseInt(request.getParameter("fid"));

        FeedBackService feedBackService = new FeedBackService();

        Feedback feedback = feedBackService.find(fid);

        Map<String,Object> map = new HashMap<>();

        map.put("state",feedback.getState());

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}