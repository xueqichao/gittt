package com.xueqichao.HealthServiceSystem.util;

import com.xueqichao.HealthServiceSystem.eneity.Feedback;
import com.xueqichao.HealthServiceSystem.eneity.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RsToEntity
{
    public static List<Feedback> rsToFeedbackList(ResultSet rs){
        List<Feedback> list = new ArrayList<>();
        try {
            if(rs != null){
                while (rs.next()){
                    Feedback feedback = new Feedback(rs.getInt("fid"),rs.getString("username"),
                            rs.getString("head"), rs.getString("content"),rs.getInt("state"),
                            rs.getString("feedbackcontent"));
                    list.add(feedback);
                }
            }
        } catch (SQLException throwable) {
                throwable.printStackTrace();
        }
        return list;
    }

    public static Feedback rsToFeedback(ResultSet rs){
        Feedback feedback = null;
        try {
            while (rs.next()){
                 feedback = new Feedback(rs.getInt("fid"),rs.getString("username"),
                        rs.getString("head"), rs.getString("content"),rs.getInt("state"),
                        rs.getString("feedbackcontent"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return feedback;
    }

    public static List<Statement> rsToStatementList(ResultSet rs){
        List<Statement> list = new ArrayList<>();
        try {
            if(rs != null){
                while (rs.next()){
                    Statement statement = new Statement(rs.getInt("sid"),rs.getString("username"),
                            rs.getString("content"),rs.getInt("aid"),rs.getString("author"));
                    list.add(statement);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public static Statement rsToStatement(ResultSet rs){
        Statement statement = null;
        try {
            while (rs.next()){
                statement = new Statement(rs.getInt("sid"),rs.getString("username"),
                        rs.getString("content"),rs.getInt("aid"),rs.getString("author"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return statement;
    }









}
