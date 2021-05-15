package com.xueqichao.HealthServiceSystem.service;

import com.xueqichao.HealthServiceSystem.dao.StatementDao;
import com.xueqichao.HealthServiceSystem.dao.StatementImpl;
import com.xueqichao.HealthServiceSystem.eneity.Statement;

import java.util.List;

public class StatementService
{
    StatementDao statementDao = new StatementImpl();

    public List<Statement> findAll(){
        return statementDao.findAll();
    }

    public List<Statement> FindByPage(int page, int count, String author){
        return statementDao.FindByPage(page, count, author);
    }

    public int[] totalPage(int count, String author){
        return statementDao.totalPage(count, author);
    }

    public Statement find(int sid){
        return statementDao.find(sid);
    }

    public void addStatement(Statement statement){
        statementDao.addStatement(statement);
    }

    public List<Statement> FindByAid(int aid){
        return statementDao.FindByAid(aid);
    }

    public void delStatement(int sid){
        statementDao.delStatement(sid);
    }
}
