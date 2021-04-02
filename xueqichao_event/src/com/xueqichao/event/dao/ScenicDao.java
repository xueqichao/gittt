package com.xueqichao.event.dao;

import com.xueqichao.event.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ScenicDao
{

    public Vector lookScenicMysql() {
        String sql1 = "select * from s_information";
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
