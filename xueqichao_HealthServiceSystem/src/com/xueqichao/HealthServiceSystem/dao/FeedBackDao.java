package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.eneity.Feedback;

import java.util.List;

public interface FeedBackDao
{
    /**
     * 获得所有的数据所构成的集合
     * @return
     */
    List<Feedback> findAll();

    /**
     *
     * @param count 分页查询中每一页的条数
     * @return a[0]表示总记录数,a[1]表示总页数
     */
    int[] totalPage(int count);

    /**
     *
     * @param page 获得的是第几页的数据
     * @param count 每一页数据个数
     * @return 该页面的数据的list集合
     */
    List<Feedback> findByPage(int page, int count);

    /**
     * 根据用户进行分页查询
     * @param page
     * @param count
     * @param username
     * @return
     */
    List<Feedback> FindByPage(int page, int count, String username);

    /**
     * 查询每一位用户的反馈总数和页数
     * @param count 分页查询中每一页的条数
     * @return a[0]表示总记录数,a[1]表示总页数
     */
    int[] totalPage(int count,String username);

    /**
     * 根据主键id来获取对应的反馈信息
     * @param fid
     * @return
     */
    Feedback find(int fid);

    /**
     * 管理员反馈成功后对应数据的修改
     * @param content
     * @param fid
     */
    void upDataAfterDeal(String content,int fid);

    /**
     * 用户进行投诉
     * @param feedback
     */
    void addFeed(Feedback feedback);
}
