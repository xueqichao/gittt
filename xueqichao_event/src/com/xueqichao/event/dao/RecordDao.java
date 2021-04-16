package com.xueqichao.event.dao;

import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class RecordDao
{
    public void keepRecordMysql(int r_num,int uid,int tid) {
        String sql1 = "insert into ticket_record values(null,?,?,?)";
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,r_num);
            ps.setInt(2,uid);
            ps.setInt(3,tid);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
    }

    public Vector<Vector> lookRecordMysql(String userName) {
        String sql1 = "SELECT ticket_record.r_number,`user`.user_name,s_information.science_name,ticket.time " +
                "FROM ticket_record,`user`,s_information,ticket " +
                "where ticket_record.uid=`user`.uid and ticket_record.tid=ticket.tid" +
                " and ticket.sid=s_information.sid and `user`.user_name=?";
        Vector vector = new Vector();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector vector1 = new Vector();
                vector1.add(rs.getInt(1));
                vector1.add(rs.getString(2));
                vector1.add(rs.getString(3));
                vector1.add(rs.getString(4));
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
