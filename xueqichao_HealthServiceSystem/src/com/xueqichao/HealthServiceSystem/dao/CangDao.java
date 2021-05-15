package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Cang;

import java.util.List;

public interface CangDao
{
    /**
     * 查询表中所有信息
     * @return
     */
    List<Cang> find();

    /**
     * 收藏
     */
    void AddCang(int aid,int uid);

    /**
     * 取消收藏
     */
    void cancelCang(int aid,int uid);
}
