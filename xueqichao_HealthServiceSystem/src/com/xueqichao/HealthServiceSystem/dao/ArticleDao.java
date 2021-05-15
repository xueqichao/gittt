package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.eneity.User;

import java.util.List;

public interface ArticleDao
{
    /**
     * 根据作者来展示文章
     * @param author
     * @return
     */
    List find(String author);

    /**
     * 根据页面信息将文章数据存入数据库
     * @param article
     */
    void addArticle(Article article);

    /**
     * 根据主键id查询文章
     * @param aid
     * @return
     */
    Article find(int aid);

    /**
     * 查询所有的文章信息
     * @return
     */
    List<Article> find();

    /**
     * 根据aid删除文章
     * @param aid
     */
    void delArticle(int aid);

    /**
     * 更新浏览量
     * @param article
     */
    void upDataLookNum(Article article);

    /**
     * 增加文章的点赞量
     * @param article
     */
    void addSupport(Article article);

    /**
     * 减少文章点赞量
     * @param article
     */
    void delSupport(Article article);

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
    List<Article> findByPage(int page, int count);

    /**
     * 根据作者进行分页查询
     * @param page
     * @param count
     * @param author
     * @return
     */
    List<Article> workerFindByPage(int page,int count,String author);

    /**
     * 查询每一位工作者的写作总数和页数
     * @param count 分页查询中每一页的条数
     * @return a[0]表示总记录数,a[1]表示总页数
     */
    int[] totalPage(int count,String author);

    /**
     *
     * @param count 每一页条数
     * @param keyword 关键字
     * @return a[0]表示总记录数,a[1]表示总页数
     */
    int[] userKeyTotalPage(int count,String keyword);

    /**
     *
     * @param page 获得的是第几页的数据
     * @param count 每一页数据个数
     * @param keyword 关键词
     * @return 该页面的数据的list集合
     */
    List<Article> findByPage(int page, int count,String keyword);

    /**
     * 根据类型名称查询这个类型文章的总浏览量
     * @param type
     * @return
     */
    int findSumLookNum(String type);

    /**
     * 增加文章的收藏量
     * @param article
     */
    void addCang(Article article);

    /**
     * 减少文章收藏量
     * @param article
     */
    void delCang(Article article);
}