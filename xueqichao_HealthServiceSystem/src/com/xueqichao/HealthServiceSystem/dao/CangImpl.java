package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Cang;
import com.xueqichao.HealthServiceSystem.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CangImpl implements CangDao{
    @Override
    public List<Cang> find() {
        String sql = "select * from cang";
        ResultSet rs = JdbcUtil.find(sql,null);
        List<Cang> list = new ArrayList<>();
        try {
            while(rs.next()){
                Cang cang = new Cang(rs.getInt("aid"),rs.getInt("uid"));
                list.add(cang);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void AddCang(int aid, int uid) {
        String sql = "insert into cang values(?,?)";
        Object[] data = {aid,uid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void cancelCang(int aid, int uid) {
        String sql = "delete from cang where aid=? and uid=?";
        Object[] data = {aid,uid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }
}
