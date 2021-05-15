package com.xueqichao.HealthServiceSystem.service;

import com.xueqichao.HealthServiceSystem.dao.CangDao;
import com.xueqichao.HealthServiceSystem.dao.CangImpl;
import com.xueqichao.HealthServiceSystem.eneity.Cang;

import java.util.List;

public class CangService
{
    CangDao cangDao = new CangImpl();

    public List<Cang> find(){
        return cangDao.find();
    }

    public void AddCang(int aid, int uid){
        cangDao.AddCang(aid,uid);
    }

    public void cancelCang(int aid, int uid){
        cangDao.cancelCang(aid,uid);
    }


}
