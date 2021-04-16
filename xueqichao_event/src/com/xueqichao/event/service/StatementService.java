package com.xueqichao.event.service;

import com.xueqichao.event.dao.StatementDao;
import com.xueqichao.event.entity.ScenicStatement;

import java.util.Vector;

public class StatementService
{
    StatementDao statementDao = new StatementDao();

    public ScenicStatement getInstance(int staid){
        return statementDao.getInstanceMysql(staid);
    }

    public Vector<Vector> lookStatementMysql(){
        return statementDao.lookStatementMysql();
    }

    public int deleteStatement(int staid){
        return statementDao.deleteStatementMysql(staid);
    }

    public Vector<Vector> lookStatementMysql(int uid){
        return statementDao.lookStatementMysql(uid);
    }

    public Vector<Vector> lookStatementMysql(String information){
        return statementDao.lookStatementMysql(information);
    }

    public int keepStatementMysql(String statement,int sid,int uid){
        return statementDao.keepStatementMysql(statement,sid,uid);
    }









}
