package com.xueqichao.HealthServiceSystem.service;

import com.xueqichao.HealthServiceSystem.dao.SupportDao;
import com.xueqichao.HealthServiceSystem.dao.SupportDaoImpl;
import com.xueqichao.HealthServiceSystem.eneity.Support;

import java.util.List;

public class SupportService
{
    SupportDao supportDao = new SupportDaoImpl();

    public List<Support> find(){
        return supportDao.find();
    }

    public void AddSupport(int aid, int uid){
        supportDao.AddSupport(aid,uid);
    }

    public void cancelSupport(int aid, int uid){
        supportDao.cancelSupport(aid,uid);
    }











}
