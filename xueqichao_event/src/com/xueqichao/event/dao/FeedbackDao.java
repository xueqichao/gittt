package com.xueqichao.event.dao;

import com.xueqichao.event.entity.Feedback;
import com.xueqichao.event.entity.User;
import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class FeedbackDao
{
    public Vector<Vector> lookFeedbackMysql() {
        String sql1 = "select * from feedback";
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
                vector1.add(rs.getInt("fid"));
                vector1.add(rs.getString("username"));
                vector1.add(rs.getString("content"));
                vector1.add(rs.getString("state"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }

    public Vector<Vector> lookFeedbackMysql(String username) {
        String sql1 = "select * from feedback where username=?";
        Vector vector = new Vector();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getInt("fid"));
                vector1.add(rs.getString("username"));
                vector1.add(rs.getString("content"));
                vector1.add(rs.getString("state"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }

    public void keepFeedbackMysql(String username,String content) {
        String sql1 = "insert into feedback values(null,?,?,0)";
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,username);
            ps.setString(2,content);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
    }

    public int backMysql(int fid) {
        String sql1 = "update feedback set state=? where fid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,1);
            ps.setInt(2,fid);
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

    public Feedback getInstanceMysql(int fid) {
        String sql1 = "select * from feedback where fid=?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Feedback feedback = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,fid);
            rs = ps.executeQuery();
            if (rs.next()) {
                feedback = new Feedback(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return feedback;
    }








}
