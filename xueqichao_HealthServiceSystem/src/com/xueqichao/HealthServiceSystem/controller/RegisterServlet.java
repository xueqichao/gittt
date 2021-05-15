package com.xueqichao.HealthServiceSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xueqichao.HealthServiceSystem.eneity.User;
import com.xueqichao.HealthServiceSystem.service.UserService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);
        UserService userService = new UserService();

        String registerName = request.getParameter("registerUsername");
        String registerPassword = request.getParameter("registerPassword");
        String registerIdentity = request.getParameter("registerIdentity");

        User user = new User(registerName,registerPassword,registerIdentity,1);

        User u = userService.findRepeat(user);

        Map<String,Object> map = new HashMap<>();

        if(u != null){
            map.put("userExist",true);
            map.put("msg","此用户名已被注册");
        }else{
            map.put("userExist",false);
            map.put("msg","注册成功！即将返回登录界面！");
            userService.keep(user);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}