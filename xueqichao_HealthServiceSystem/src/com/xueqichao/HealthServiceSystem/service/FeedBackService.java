package com.xueqichao.HealthServiceSystem.service;

import com.xueqichao.HealthServiceSystem.dao.FeedBackImpl;
import com.xueqichao.HealthServiceSystem.eneity.Feedback;

import java.util.List;

public class FeedBackService
{
    FeedBackImpl feedBack = new FeedBackImpl();

    public List<Feedback> findAll(){
        return feedBack.findAll();
    }

    public int[] totalPage(int count){
        return feedBack.totalPage(count);
    }

    public List<Feedback> findByPage(int page, int count){
        return feedBack.findByPage(page,count);
    }

    public List<Feedback> FindByPage(int page, int count, String username){
        return feedBack.FindByPage(page,count,username);
    }

    public int[] totalPage(int count, String username){
        return feedBack.totalPage(count,username);
    }

    public Feedback find(int fid){return feedBack.find(fid);}

    public void upDataAfterDeal(String content, int fid){feedBack.upDataAfterDeal(content,fid);}

    public void addFeed(Feedback feedback){feedBack.addFeed(feedback);}
}
