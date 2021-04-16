package com.xueqichao.event.service;

import com.xueqichao.event.dao.DealFeedbackDao;

import java.util.Vector;

public class DealFeedbackService
{
    DealFeedbackDao dealFeedbackDao = new DealFeedbackDao();

    public void keepDealFeedbackMysql(String username, String content, int fid){
        dealFeedbackDao.keepDealfeedbackMysql(username,content,fid);
    }

    public Vector<Vector> lookDealFeedbackMysql(String username){
        return dealFeedbackDao.lookDealFeedbackMysql(username);
    }

    public Vector<Vector> lookDealFeedbackMysql(){
        return dealFeedbackDao.lookDealFeedbackMysql();
    }


}
