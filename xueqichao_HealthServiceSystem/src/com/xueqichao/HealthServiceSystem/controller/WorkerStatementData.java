package com.xueqichao.HealthServiceSystem.controller;

import com.xueqichao.HealthServiceSystem.eneity.Feedback;
import com.xueqichao.HealthServiceSystem.eneity.Statement;
import com.xueqichao.HealthServiceSystem.service.FeedBackService;
import com.xueqichao.HealthServiceSystem.service.StatementService;
import com.xueqichao.HealthServiceSystem.util.ChangeEncodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/workerStatementData")
public class WorkerStatementData extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChangeEncodeUtil.changeEncode(request,response);

        int page = 1;
        int count = 5;

        String cp = request.getParameter("cp");
        String author = (String) request.getSession().getAttribute("currentUser");

        if(cp != null){
            page = Integer.parseInt(cp);
        }
        if(page == 0){
            page = 1;
        }

        StatementService statementService = new StatementService();

        int arr[] = statementService.totalPage(count,author);

        request.setAttribute("currentpage",page);
        request.setAttribute("tsum",arr[0]);
        request.setAttribute("tpage",arr[1]);

        List<Statement> list = statementService.FindByPage(page,count,author);

        request.setAttribute("statementList",list);

        request.getRequestDispatcher("workerStatement.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
}