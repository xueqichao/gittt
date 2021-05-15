package com.xueqichao.HealthServiceSystem.service;

import com.xueqichao.HealthServiceSystem.dao.UserImpl;
import com.xueqichao.HealthServiceSystem.eneity.User;

import java.util.List;

public class UserService
{
    UserImpl userDao = new UserImpl();

    public User find(User user){
        return userDao.find(user);
    }

    public void keep(User user){
         userDao.keep(user);
    }

    public User findRepeat(User user){
        return userDao.findRepeat(user);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public void openState(int uid){
         userDao.openState(uid);
    }

    public void closeState(int uid){ userDao.closeState(uid);}

    public User find(String username){return userDao.find(username);}

    public int[] totalPage(int count){return userDao.totalPage(count);}

    public List<User> findByPage(int page, int count){return userDao.findByPage(page,count);}

    public List<User> findWorker(){return userDao.findWorker();}

    public List<User> findUser(){return userDao.findUser();}

    public User find(int uid){return userDao.find(uid);}
}