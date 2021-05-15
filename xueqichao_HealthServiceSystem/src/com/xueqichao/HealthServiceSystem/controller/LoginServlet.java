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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        UserService userService = new UserService();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");

        User user2 = userService.find(new User(username,password,identity,1));

        Map<String,Object> map = new HashMap<>();
        if(identity.equals("")){
            map.put("userExist",true);
        }
        else if(identity.equals("游客")){
            map.put("userExist",true);
            map.put("identity","游客");
        }else{
            if(user2 != null){
                map.put("userExist",true);
                map.put("identity",user2.getPosition());
                request.getSession().setAttribute("currentUser",user2.getUsername());
                request.getSession().setAttribute("User",user2);
            }else{
                map.put("userExist",false);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}
