package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.User;
import com.xueqichao.HealthServiceSystem.service.UserService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getToName")
public class GetToName extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        UserService userService = new UserService();

        int uid = Integer.parseInt(request.getParameter("uid"));

        User user = userService.find(uid);

        request.setAttribute("ToName",user.getUsername());

        request.getRequestDispatcher("").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}