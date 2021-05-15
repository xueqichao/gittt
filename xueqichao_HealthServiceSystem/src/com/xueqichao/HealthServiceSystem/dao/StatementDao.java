package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Feedback;
import com.xueqichao.HealthServiceSystem.eneity.Statement;

import java.util.List;

public interface StatementDao
{
    /**
     * 获得所有的数据所构成的集合
     * @return
     */
    List<Statement> findAll();

    /**
     * 根据作者进行分页查询
     * @param page
     * @param count
     * @param author
     * @return
     */
    List<Statement> FindByPage(int page, int count, String author);

    /**
     * 查询每一位作者的对应的评论总数和页数
     * @param count 分页查询中每一页的条数
     * @return a[0]表示总记录数,a[1]表示总页数
     */
    int[] totalPage(int count,String author);

    /**
     * 根据主键id来获取对应的反馈信息
     * @param sid
     * @return
     */
    Statement find(int sid);

    /**
     * 用户进行评论
     * @param statement
     */
    void addStatement(Statement statement);

    /**
     * 根据文章id来查询所有评论记录
     * @param aid
     * @return
     */
    List<Statement> FindByAid(int aid);

    /**
     * 根据主键id删除对应评论
     * @param sid
     */
    void delStatement(int sid);

}
