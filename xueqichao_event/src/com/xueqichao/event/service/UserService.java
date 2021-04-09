package com.xueqichao.event.service;

import com.xueqichao.event.dao.UserDao;
import com.xueqichao.event.entity.User;

import java.util.Vector;

/**
 * @author lenovo
 * 这个类用于将view层的用户数据与dao层用户数据进行比较
 */
public class UserService
{   User user = null;
    UserDao userDao = new UserDao();

    public boolean isExist(String userName,String password)
    { user = userDao.isExistMysql(userName,password);

      /*如果user有返回值，说明账号密码正确，则返回true
        若无返回值，则user=null，则返回false
       */

      return user != null;
    }


    public boolean isExist(String userName)
    { user = userDao.isExistMysql(userName);

      /*如果user有返回值，说明账号已经存在，则返回true
        若无返回值，则user=null，则返回false
       */

        return user != null;
    }

    public void keepUser(String userName,String passWord){

        userDao.keepUserMysql(userName,passWord);

    }

    public Vector<Vector> lookUser(){
        return userDao.lookUserMysql();
    }

    public int closeUser(int uid){
        return userDao.closeUserStateMysql(uid);
    }

    public int openUser(int uid){
        return userDao.openUserStateMysql(uid);
    }

    public User look(int uid){
        return userDao.lookMysql(uid);
    }

    public void chargeUser(int uid,int balance){
        userDao.chargeUserMysql(uid, balance);
    }


}
