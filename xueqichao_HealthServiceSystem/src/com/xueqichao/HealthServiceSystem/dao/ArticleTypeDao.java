package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.ArticleType;

import java.util.List;

public interface ArticleTypeDao {

    /**
     * 查询所有类别以及访问量
     * @return
     */
    List<ArticleType> findAll();

    /**
     * 根据主键id删除对应数据
     * @param tid
     */
    void delType(int tid);

    /**
     * 根据类别名称添加类别
     * @param name
     */
    void addType(String name);

    /**
     * 根据页面传来的数据判断类型是否重复
     * @param typename
     * @return
     */
    boolean typeIsExist(String typename);

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
    List<ArticleType> findByPage(int page,int count);

    /**
     * 根据type名修改该类型的浏览总数
     * @param lookNum
     * @param type
     */
    void upDataLookNum(int lookNum,String type);
}
