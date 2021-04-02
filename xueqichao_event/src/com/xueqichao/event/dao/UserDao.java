package com.xueqichao.event.dao;

import com.xueqichao.event.entity.User;
import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lenovo
 * 这是用于用户数据与数据库交互的一个类
 */
public class UserDao {
    public User isExistMysql(String userName, String password) {
        String sql1 = "select * from user where user_name = ? and user_password = ?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user1 = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user1 = new User(rs.getString("user_name"), rs.getString("user_password"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return user1;
    }

    public User isExistMysql(String userName) {
        String sql1 = "select * from user where user_name = ?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user1 = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                user1 = new User(rs.getString("user_name"), null);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return user1;
    }


    public void keepUserMysql(String userName, String passWord) {
        String sql1 = "insert into user values(null,?,?,0)";
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1, userName);
            ps.setString(2, passWord);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
    }

}
