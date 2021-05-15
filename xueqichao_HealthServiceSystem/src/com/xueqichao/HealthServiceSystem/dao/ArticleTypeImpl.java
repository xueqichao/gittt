package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.ArticleType;
import com.xueqichao.HealthServiceSystem.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleTypeImpl implements ArticleTypeDao
{
    @Override
    public List<ArticleType> findAll(){
        ArrayList<ArticleType> list = new ArrayList<>();
        String sql = "select * from type";
        ResultSet rs = JdbcUtil.find(sql, null);
        try {
            while(rs.next()){
                ArticleType articleType = new ArticleType(rs.getInt("tid"),
                        rs.getString("name"),rs.getInt("looknum"));
                list.add(articleType);
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void delType(int tid) {
        String sql = "delete from type where tid=?";
        Object[] data = {tid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void addType(String name) {
        String sql = "insert into type values(null,?,0)";
        Object[] data = {name};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public boolean typeIsExist(String typename) {
        String sql = "select * from type where name=?";
        Object[] data = {typename};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    @Override
    public int[] totalPage(int count) {
        String sql = "select count(*) from type";
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
    public List<ArticleType> findByPage(int page, int count) {
        ArrayList<ArticleType> list = new ArrayList<>();
        String sql = "select * from type limit ?,?";
        Object[] data = {(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while(rs.next()){
                ArticleType articleType = new ArticleType(rs.getInt("tid"),
                        rs.getString("name"),rs.getInt("looknum"));
                list.add(articleType);
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void upDataLookNum(int lookNum, String type) {
        String sql = "update type set looknum=? where name=?";
        Object[] data = {lookNum,type};
        JdbcUtil.addDeleteUpdate(sql,data);
    }


}
