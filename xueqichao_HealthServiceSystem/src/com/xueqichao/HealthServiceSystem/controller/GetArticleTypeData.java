package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.ArticleType;
import com.xueqichao.HealthServiceSystem.eneity.User;
import com.xueqichao.HealthServiceSystem.service.ArticleTypeService;
import com.xueqichao.HealthServiceSystem.service.UserService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getArticleTypeData")
public class GetArticleTypeData extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        String username = (String) request.getSession().getAttribute("currentUser");

        ArticleTypeService articleTypeService = new ArticleTypeService();

        UserService userService = new UserService();

        User user = userService.find(username);

        if(user.getState() == 0){
            response.getWriter().write("<script>alert('您的账号被封禁!请联系管理员');window.location='workerArticleData'</script>");
        }else {
            List<ArticleType> list = articleTypeService.findAll();
            request.setAttribute("TypeData", list);
            request.getRequestDispatcher("workerAddArticle.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}