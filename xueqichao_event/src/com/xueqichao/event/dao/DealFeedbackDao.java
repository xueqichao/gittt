package com.xueqichao.event.dao;

import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DealFeedbackDao
{
    public void keepDealfeedbackMysql(String username,String content,int fid) {
        String sql1 = "insert into dealfeedback values(null,?,?,?)";
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(2,username);
            ps.setString(1,content);
            ps.setInt(3,fid);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
    }

    public Vector<Vector> lookDealFeedbackMysql() {
        String sql1 = "select * from dealfeedback";
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
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }

    public Vector<Vector> lookDealFeedbackMysql(String username) {
        String sql1 = "select * from dealfeedback where username=?";
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
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }








}
