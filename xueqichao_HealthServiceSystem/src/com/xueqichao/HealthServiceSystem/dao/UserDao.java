package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.ArticleType;
import com.xueqichao.HealthServiceSystem.eneity.User;

import java.util.List;

public interface UserDao
{
    /**
     * 根据前台的用户信息查询数据库判断可否登录
     */
    User find(User user);

    /**
     * 保存注册成功用户信息
     * @param user
     * @return
     */
    int keep(User user);

    /**
     * 判断用户名对应身份是否重复
     */
    User findRepeat(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    List findAll();

    /**
     * 改变所传用户参数的账号状态
     * @param
     */
    void openState(int uid);
    void closeState(int uid);

    /**
     * 根据用户名获取用户实体
     * @param username
     * @return
     */
    User find(String username);

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
    List<User> findByPage(int page, int count);

    /**
     * 获得所有的工作者
     * @return
     */
    List<User> findWorker();

    /**
     * 获得所有的普通用户
     * @return
     */
    List<User> findUser();

    /**
     * 根据主键id获取User实体
     * @param uid
     * @return
     */
    User find(int uid);
}
