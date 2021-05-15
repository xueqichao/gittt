package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Support;
import com.xueqichao.HealthServiceSystem.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupportDaoImpl implements SupportDao{
    @Override
    public List<Support> find() {
        String sql = "select * from zan";
        ResultSet rs = JdbcUtil.find(sql,null);
        List<Support> list = new ArrayList<>();
        try {
            while(rs.next()){
                Support support = new Support(rs.getInt("aid"),rs.getInt("uid"));
                list.add(support);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void AddSupport(int aid, int uid) {
        String sql = "insert into zan values(?,?)";
        Object[] data = {aid,uid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void cancelSupport(int aid, int uid) {
        String sql = "delete from zan where aid=? and uid=?";
        Object[] data = {aid,uid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }
}