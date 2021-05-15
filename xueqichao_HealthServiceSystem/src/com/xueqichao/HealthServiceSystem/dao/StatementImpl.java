package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Statement;
import com.xueqichao.HealthServiceSystem.util.JdbcUtil;
import com.xueqichao.HealthServiceSystem.util.RsToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatementImpl implements StatementDao{
    @Override
    public List<Statement> findAll() {
        String sql = "select * from statement";
        ResultSet rs = JdbcUtil.find(sql,null);
        return RsToEntity.rsToStatementList(rs);
    }

    @Override
    public List<Statement> FindByPage(int page, int count, String author) {
        String sql = "select * from statement where author=? limit ?,?";
        Object[] data = {author,(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        return RsToEntity.rsToStatementList(rs);
    }

    @Override
    public int[] totalPage(int count, String author) {
        String sql = "select count(*) from statement where author=?";
        int[] arr = {0,1};
        Object[] data = {author};
        ResultSet rs = JdbcUtil.find(sql,data);
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
    public Statement find(int sid) {
        String sql = "select * from statement where sid=?";
        Object[] data = {sid};
        ResultSet rs = JdbcUtil.find(sql,data);
        return RsToEntity.rsToStatement(rs);
    }

    @Override
    public void addStatement(Statement statement) {
        String sql = "insert into statement values(null,?,?,?,?)";
        Object[] data = {statement.getUsername(),statement.getContent(),statement.getAid(),statement.getAuthor()};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public List<Statement> FindByAid(int aid) {
        String sql = "select * from statement where aid=?";
        Object[] data = {aid};
        ResultSet rs = JdbcUtil.find(sql,data);
        return RsToEntity.rsToStatementList(rs);
    }

    @Override
    public void delStatement(int sid) {
        String sql = "delete from statement where sid=?";
        Object[] data = {sid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

}
