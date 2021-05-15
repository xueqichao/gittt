package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.ArticleType;
import com.xueqichao.HealthServiceSystem.eneity.User;
import com.xueqichao.HealthServiceSystem.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDao {
    @Override
    public User find(User user) {
        User u = null;
        String sql = "select * from user where username=? and password=? and position=?";
        Object[] data = {user.getUsername(),user.getPassword(),user.getPosition()};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while (rs.next()){
                u = new User(rs.getInt("uid"),rs.getString("username"), rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            JdbcUtil.close(JdbcUtil.conn,JdbcUtil.ps,rs);
        }
        return u;
    }

    @Override
    public int keep(User user) {
        String sql = "insert into user values(null,?,?,?,1)";
        Object[] data = {user.getUsername(),user.getPassword(),user.getPosition()};
        return JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public User findRepeat(User user) {
        User u = null;
        String sql = "select * from user where username=? and position=?";
        Object[] data = {user.getUsername(),user.getPosition()};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while (rs.next()){
                u = new User(rs.getInt("uid"),rs.getString("username"), rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            JdbcUtil.close(JdbcUtil.conn,JdbcUtil.ps,rs);
        }
        return u;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "select * from user where position=? or position=?";
        Object[] data = {"工作者","普通用户"};
        ResultSet rs = JdbcUtil.find(sql,data);
        try{
            while (rs.next()) {
                User user = new User(rs.getInt("uid"),rs.getString("username"),rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
                list.add(user);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void openState(int uid) {
        String sql = "update user set state=? where uid=?";
        Object[] data = {1,uid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void closeState(int uid) {
        String sql = "update user set state=? where uid=?";
        Object[] data = {0,uid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public User find(String username) {
        User u = null;
        String sql = "select * from user where username=?";
        Object[] data = {username};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while (rs.next()){
                u = new User(rs.getInt("uid"),rs.getString("username"), rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            JdbcUtil.close(JdbcUtil.conn,JdbcUtil.ps,rs);
        }
        return u;
    }

    @Override
    public int[] totalPage(int count) {
        String sql = "select count(*) from user";
        int[] arr = {0,1};
        ResultSet rs = JdbcUtil.find(sql,null);
        try {
            while (rs.next()){
                arr[0] = rs.getInt(1);
                if(arr[0]%count == 0){
                    arr[1] = arr[0]/count;
                }else{
                    arr[1] = arr[0]/count + 1;
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return arr;
    }

    @Override
    public List<User> findByPage(int page, int count) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "select * from user limit ?,?";
        Object[] data = {(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while(rs.next()){
                User u = new User(rs.getInt("uid"),rs.getString("username"), rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
                list.add(u);
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> findWorker() {
        ArrayList<User> list = new ArrayList<>();
        String sql ="select * from user where position=?";
        Object[] data = {"工作者"};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while(rs.next()){
                User u = new User(rs.getInt("uid"),rs.getString("username"), rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
                list.add(u);
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> findUser() {
        ArrayList<User> list = new ArrayList<>();
        String sql ="select * from user where position=?";
        Object[] data = {"普通用户"};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while(rs.next()){
                User u = new User(rs.getInt("uid"),rs.getString("username"), rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
                list.add(u);
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public User find(int uid) {
        User user = null;
        String sql = "select * from user where uid=?";
        Object[] data = {uid};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while(rs.next()){
                user = new User(rs.getInt("uid"),rs.getString("username"), rs.getString("password"),
                        rs.getString("position"),rs.getInt("state"));
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }


}
