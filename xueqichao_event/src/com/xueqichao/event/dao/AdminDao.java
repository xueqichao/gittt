package com.xueqichao.event.dao;


import com.xueqichao.event.entity.Admin;
import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lenovo
 * 这个类用于管理员的数据与数据库中管理员的数据交互的类
 */
public class AdminDao
{
    public Admin isExistMysql(String adminName, String adminPassword)
    {   String sql2 = "select * from admin where admin_name = ? and admin_password = ?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Admin admin = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql2);
            ps.setString(1,adminName);
            ps.setString(2,adminPassword);
            rs = ps.executeQuery();
            if(rs.next()){
                admin = new Admin(rs.getString("admin_name"),rs.getString("admin_password"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally{
            JdbcUtil.close(co,ps,rs);
        }
        return admin;
    }


}
