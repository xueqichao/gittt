package com.xueqichao.event.dao;

import com.xueqichao.event.entity.Ticket;
import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TicketDao
{
    public Vector<Vector> lookTicketMysql() {
        String sql1 = "select ticket.tid,s_information.science_name,ticket.time,ticket.number,ticket.price " +
                      "from s_information,ticket " +
                      "where ticket.sid=s_information.sid ";
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
                vector1.add(rs.getInt("tid"));
                vector1.add(rs.getString("science_name"));
                vector1.add(rs.getString("time"));
                vector1.add(rs.getInt("number"));
                vector1.add(rs.getInt("price"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }

    public void keepTicketMysql(String time,int number,int sid,int price) {
        String sql1 = "insert into ticket values(null,?,?,?,?)";
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,time);
            ps.setInt(2,number);
            ps.setInt(3,sid);
            ps.setInt(4,price);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
    }

    public int deleteTicketMysql(int tid) {
        String sql1 = "delete from ticket where tid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,tid);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }

    public Ticket getInstanceMysql(int tid) {
        String sql1 = "select * from ticket where tid=?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ticket ticket1 = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1, tid);
            rs = ps.executeQuery();
            if (rs.next()) {
                ticket1= new Ticket(rs.getInt("tid"),rs.getInt("number"),rs.getString("time"),rs.getInt("price"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return ticket1;
    }

    public int updateTicketMysql(int tid,String time,int number,int price) {
        String sql1 = "update ticket set time=?,number=?,price=? where tid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,time);
            ps.setInt(2,number);
            ps.setInt(3,price);
            ps.setInt(4,tid);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }


    public Ticket getInstanceMysql(int sid,String time) {
        String sql1 = "select * from ticket where sid=? and time=?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ticket ticket1 = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1, sid);
            ps.setString(2,time);
            rs = ps.executeQuery();
            if (rs.next()) {
                ticket1= new Ticket(rs.getInt("tid"),rs.getInt("number"),rs.getString("time"),rs.getInt("price"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return ticket1;
    }

    public Vector<Vector> lookTicketMysql(String information) {
        String sql1 = "select ticket.tid,s_information.science_name,ticket.time,ticket.number,ticket.price " +
                "from s_information,ticket " +
                "where s_information.science_name like ? and ticket.sid=s_information.sid";
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
                vector1.add(rs.getInt("tid"));
                vector1.add(rs.getString("science_name"));
                vector1.add(rs.getString("time"));
                vector1.add(rs.getInt("number"));
                vector1.add(rs.getInt("price"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }


    public int updateTicketMysql(int tid,int number) {
        String sql1 = "update ticket set number=? where tid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,number);
            ps.setInt(2,tid);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }














}
