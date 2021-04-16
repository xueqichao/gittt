package com.xueqichao.event.service;

import com.xueqichao.event.dao.FeedbackDao;
import com.xueqichao.event.entity.Feedback;

import java.util.Vector;

public class FeedbackService
{
    FeedbackDao feedbackDao = new FeedbackDao();

    public Vector<Vector> lookFeedbackMysql(){
        return feedbackDao.lookFeedbackMysql();
    }

    public Vector<Vector> lookFeedbackMysql(String username){
        return feedbackDao.lookFeedbackMysql(username);
    }

    public void keepFeedbackMysql(String username,String content){
        feedbackDao.keepFeedbackMysql(username,content);
    }

    public int backMysql(int fid){
        return feedbackDao.backMysql(fid);
    }

    public Feedback getInstanceMysql(int fid){
        return feedbackDao.getInstanceMysql(fid);
    }




}
