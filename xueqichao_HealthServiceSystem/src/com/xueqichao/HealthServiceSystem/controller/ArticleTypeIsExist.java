package com.xueqichao.HealthServiceSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xueqichao.HealthServiceSystem.service.ArticleTypeService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/articleTypeIsExist")
public class ArticleTypeIsExist extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        String typename = request.getParameter("typename");

        ArticleTypeService articleTypeService = new ArticleTypeService();

        boolean flag = articleTypeService.typeIsExist(typename);

        Map<String,Object> map = new HashMap<>();

        if(flag){
            map.put("typeExist",true);
        }else {
            map.put("typeExist",false);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),map);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}
