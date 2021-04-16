package com.xueqichao.event.dao;

import com.xueqichao.event.entity.ScenicStatement;
import com.xueqichao.event.entity.Ticket;
import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StatementDao
{
    public ScenicStatement getInstanceMysql(int staid) {
        String sql1 = "select * from statement where sta_id=?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ScenicStatement scenicStatement = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1, staid);
            rs = ps.executeQuery();
            if (rs.next()) {
                scenicStatement= new ScenicStatement(rs.getInt("sta_id"),rs.getString("statement"),
                        rs.getInt("sid"),rs.getInt("uid"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return scenicStatement;
    }

    public Vector<Vector> lookStatementMysql() {
        String sql1 = "select statement.sta_id,statement.statement,`user`.user_name,s_information.science_name " +
                "from statement,`user`,s_information " +
                "where statement.sid=s_information.sid and statement.uid=`user`.uid";
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
                vector1.add(rs.getInt("sta_id"));
                vector1.add(rs.getString("user_name"));
                vector1.add(rs.getString("science_name"));
                vector1.add(rs.getString("statement"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }


    public int deleteStatementMysql(int staid) {
        String sql1 = "delete from statement where sta_id=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,staid);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }

    public Vector<Vector> lookStatementMysql(int uid) {
        String sql1 = "select statement.sta_id,statement.statement,`user`.user_name,s_information.science_name " +
                "from statement,`user`,s_information " +
                "where statement.sid=s_information.sid and statement.uid=`user`.uid and statement.uid=?";
        Vector vector = new Vector();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getInt("sta_id"));
                vector1.add(rs.getString("user_name"));
                vector1.add(rs.getString("science_name"));
                vector1.add(rs.getString("statement"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }


    public Vector<Vector> lookStatementMysql(String information) {
        String sql1 = "select statement.sta_id,statement.statement,`user`.user_name,s_information.science_name " +
                "from statement,`user`,s_information " +
                "where statement.sid=s_information.sid and statement.uid=`user`.uid and s_information.science_name like ?";
        Vector vector = new Vector();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,"%" + information + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getInt("sta_id"));
                vector1.add(rs.getString("user_name"));
                vector1.add(rs.getString("science_name"));
                vector1.add(rs.getString("statement"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }

    public int keepStatementMysql(String statement,int sid,int uid) {
        String sql1 = "insert into statement values(null,?,?,?)";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,statement);
            ps.setInt(2,sid);
            ps.setInt(3,uid);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }




}
