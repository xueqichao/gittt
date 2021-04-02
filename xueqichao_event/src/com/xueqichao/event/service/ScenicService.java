package com.xueqichao.event.service;

import com.xueqichao.event.dao.ScenicDao;

import java.util.Vector;

public class ScenicService
{
    ScenicDao scenicDao = new ScenicDao();

    public Vector lookScenic(){
       return scenicDao.lookScenicMysql();
    }














}
