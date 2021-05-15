package com.xueqichao.HealthServiceSystem.dao;


import com.xueqichao.HealthServiceSystem.eneity.Support;

import java.util.List;

public interface SupportDao
{
    /**
     * 查询表中所有信息
     * @return
     */
    List<Support> find();

    /**
     * 点赞
     */
    void AddSupport(int aid,int uid);

    /**
     * 取消点赞
     */
    void cancelSupport(int aid,int uid);
}

