package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Feedback;
import com.xueqichao.HealthServiceSystem.util.JdbcUtil;
import com.xueqichao.HealthServiceSystem.util.RsToEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FeedBackImpl implements FeedBackDao
{


    @Override
    public List<Feedback> findAll() {
        String sql = "select * from feedback";
        ResultSet rs = JdbcUtil.find(sql,null);
        return RsToEntity.rsToFeedbackList(rs);
    }

    @Override
    public int[] totalPage(int count) {
        String sql = "select count(*) from feedback";
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
    public List<Feedback> findByPage(int page, int count) {
        String sql = "select * from feedback limit ?,?";
        Object[] data = {(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        return RsToEntity.rsToFeedbackList(rs);
    }

    @Override
    public List<Feedback> FindByPage(int page, int count, String username) {
        String sql = "select * from feedback where username=? limit ?,?";
        Object[] data = {username,(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        return RsToEntity.rsToFeedbackList(rs);
    }

    @Override
    public int[] totalPage(int count, String username) {
        String sql = "select count(*) from feedback where username=?";
        int[] arr = {0,1};
        Object[] data = {username};
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
    public Feedback find(int fid) {
        String sql = "select * from feedback where fid=?";
        Object[] data = {fid};
        ResultSet rs = JdbcUtil.find(sql,data);
        return RsToEntity.rsToFeedback(rs);
    }

    @Override
    public void upDataAfterDeal(String content, int fid) {
        String sql = "update feedback set state=?,feedbackcontent=? where fid=?";
        Object[] data = {1,content,fid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void addFeed(Feedback feedback) {
        String sql = "insert into feedback values(null,?,?,?,0,null)";
        Object[] data = {feedback.getUsername(),feedback.getHead(),feedback.getContent()};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

}
