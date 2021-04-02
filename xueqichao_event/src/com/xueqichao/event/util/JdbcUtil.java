package com.xueqichao.event.util;

import java.sql.*;

/**
 * 这是一个连接数据库的工具类
 * @author lenovo
 */
public class JdbcUtil
{ private static final String URL = "jdbc:mysql://localhost:3306/xueqichao_event";
  private static final String USER = "root";
  private static final String PASSWORD = "1234";

  /**
   *
   * @return 返回数据库连接对象
   */
  public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(URL, USER, PASSWORD);
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

  /**
   *
   * @param conn 关闭数据库连接对象
   * @param stmt 关闭执行SQL语句的对象
   */

  public static void close(Connection conn, PreparedStatement stmt)
  {
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

}
