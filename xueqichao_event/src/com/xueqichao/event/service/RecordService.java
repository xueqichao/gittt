package com.xueqichao.event.service;

import com.xueqichao.event.dao.RecordDao;

import java.util.Vector;

public class RecordService
{
    RecordDao recordDao = new RecordDao();

    public void keepRecord(int r_num,int uid,int tid){
        recordDao.keepRecordMysql(r_num,uid,tid);
    }

    public Vector<Vector> lookRecord(String userName){
        return recordDao.lookRecordMysql(userName);
    }





}
