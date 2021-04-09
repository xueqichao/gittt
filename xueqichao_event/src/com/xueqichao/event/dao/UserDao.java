package com.xueqichao.event.dao;

import com.xueqichao.event.entity.User;
import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
        String sql1 = "insert into user values(null,?,?,0,1)";
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

    public Vector<Vector> lookUserMysql() {
        String sql1 = "select * from user";
        Vector vector = new Vector();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getInt("uid"));
                vector1.add(rs.getString("user_name"));
                vector1.add(rs.getString("user_password"));
                vector1.add(rs.getInt("balance"));
                vector1.add(rs.getInt("state"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }


    public int closeUserStateMysql(int uid) {
        String sql1 = "update user set state=? where uid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,0);
            ps.setInt(2,uid);
            a = ps.executeUpdate();
            }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }

    public int openUserStateMysql(int uid) {
        String sql1 = "update user set state=? where uid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,1);
            ps.setInt(2,uid);
            a = ps.executeUpdate();
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }

    public User lookMysql(int uid) {
        String sql1 = "select * from user where uid=?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user1 = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,uid);
            rs = ps.executeQuery();
            if (rs.next()) {
                user1 = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return user1;
    }

    public void chargeUserMysql(int uid,int balance) {
        String sql1 = "update user set balance=? where uid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,balance);
            ps.setInt(2,uid);
            a = ps.executeUpdate();
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            JdbcUtil.close(co, ps);
        }
    }














}
