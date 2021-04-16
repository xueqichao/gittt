package com.xueqichao.event.dao;

import com.xueqichao.event.entity.Scenic;
import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ScenicDao
{

    public Vector<Vector> lookScenicMysql() {
        String sql1 = "SELECT * from s_information ORDER BY sat_num DESC";
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
                vector1.add(rs.getInt("sid"));
                vector1.add(rs.getString("science_name"));
                vector1.add(rs.getString("science_desc"));
                vector1.add(rs.getInt("sat_num"));
                vector.add(vector1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps,rs);
        }
        return vector;
    }


    public void keepScenicMysql(String scenicName,String scenicDesc) {
        String sql1 = "insert into s_information values(null,?,?,0)";
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,scenicName);
            ps.setString(2,scenicDesc);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
    }


    public int deleteScenicMysql(String scenicName,String scenicDesc) {
        String sql1 = "delete from s_information where science_name=? and science_desc=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,scenicName);
            ps.setString(2,scenicDesc);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }



    public int updateScenicMysql(String scenicName,String scenicDesc,int sid) {
        String sql1 = "update s_information set science_name=? , science_desc=? where sid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,scenicName);
            ps.setString(2,scenicDesc);
            ps.setInt(3,sid);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }

    public Scenic lookExistMysql(int sid) {
        String sql1 = "select * from s_information where sid=?";

        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Scenic scenic = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1, sid);
            rs = ps.executeQuery();
            if (rs.next()) {
                scenic = new Scenic(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps, rs);
        }
        return scenic;
    }

    public Scenic isExistMysql(String scenicName) {
        String sql1 = "select * from s_information where science_name=?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Scenic scenic = null;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setString(1,scenicName);
            rs = ps.executeQuery();
            if(rs.next()){
                scenic = new Scenic(rs.getInt("sid"),rs.getString("science_name"),rs.getString("science_desc"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return scenic;
    }

    public int updateScenicMysql(int sid,int sta_num) {
        String sql1 = "update s_information set sat_num=? where sid=?";
        Connection co = null;
        PreparedStatement ps = null;
        int a = 0;
        try {
            co = JdbcUtil.getConnection();
            ps = co.prepareStatement(sql1);
            ps.setInt(1,sta_num);
            ps.setInt(2,sid);
            a = ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtil.close(co, ps);
        }
        return a;
    }

    public Vector<Vector> lookScenicMysql(String information) {
        String sql1 = "SELECT * FROM s_information where science_name like ? ORDER BY sat_num DESC";
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
                vector1.add(rs.getInt("sid"));
                vector1.add(rs.getString("science_name"));
                vector1.add(rs.getString("science_desc"));
                vector1.add(rs.getInt("sat_num"));
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
