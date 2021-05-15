package com.xueqichao.HealthServiceSystem.util;

import java.sql.*;

public class JdbcUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/javaweb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    /**
     * 增删改通用方法
     */
    public static int addDeleteUpdate(String sql,Object[] data){
        int a = 0;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            if(data != null){
                for(int i = 0 ; i < data.length; i++){
                    ps.setObject(i+1,data[i]);
                }
            }
            a = ps.executeUpdate();
        } catch (Exception throwable) {
            throwable.printStackTrace();
        } finally {
            close(conn,ps,null);
        }
        return a;
    }

    /**
     * 查数据所用方法
     */
    public static ResultSet find(String sql,Object[] data){
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            if(data != null){
                for(int i = 0 ; i < data.length; i++){
                    ps.setObject(i+1,data[i]);
                }
            }
            rs = ps.executeQuery();
            return rs;
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param conn 关闭数据库连接对象
     * @param stmt 关闭执行SQL语句的对象
     * @param rs   关闭返回的结果集对象
     */
    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs)
    {   if(rs != null)
    {
        try {
            rs.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
        if(stmt != null)
        {
            try {
                stmt.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        if(conn != null)
        {
            try {
                conn.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}


