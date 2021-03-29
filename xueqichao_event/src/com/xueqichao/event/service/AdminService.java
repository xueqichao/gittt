package com.xueqichao.event.service;

import com.xueqichao.event.dao.AdminDao;
import com.xueqichao.event.entity.Admin;

/**
 * @author lenovo
 * 这个类用于将view层的管理员数据与dao层管理员数据进行比较
 */
public class AdminService
{
    Admin admin = null;
    AdminDao adminDao = new AdminDao();

    public boolean isExist(String adminName,String adminPassword)
    { admin = adminDao.isExistMysql(adminName,adminPassword);

      /*如果admin有返回值，说明账号密码正确，则返回true
        若无返回值，则user=null，则返回false
       */

        return admin != null;
    }




}
