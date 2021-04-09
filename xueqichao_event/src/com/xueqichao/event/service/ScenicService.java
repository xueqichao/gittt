package com.xueqichao.event.service;

import com.xueqichao.event.dao.ScenicDao;
import com.xueqichao.event.entity.Scenic;

import java.util.Vector;

public class ScenicService
{
    ScenicDao scenicDao = new ScenicDao();

    public Vector<Vector> lookScenic(){
       return scenicDao.lookScenicMysql();
    }

    public void keepScenic(String scenicName,String scenicDesc){
        scenicDao.keepScenicMysql(scenicName,scenicDesc);
    }

    public int deleteScenic(String scenicName,String scenicDesc){
        return scenicDao.deleteScenicMysql(scenicName,scenicDesc);
    }

    public int updateScenicMysql(String scenicName,String scenicDesc,int sid){
        return scenicDao.updateScenicMysql(scenicName,scenicDesc,sid);
    }

    public Scenic lookExistMysql(int sid){
        return scenicDao.lookExistMysql(sid);
    }

    public Scenic isExist(String scenicName){
        return scenicDao.isExistMysql(scenicName);
    }














}
