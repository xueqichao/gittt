package com.xueqichao.HealthServiceSystem.dao;

import com.xueqichao.HealthServiceSystem.eneity.Article;
import com.xueqichao.HealthServiceSystem.util.DateToStringUtil;
import com.xueqichao.HealthServiceSystem.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleImpl implements ArticleDao{
    @Override
    public List find(String author) {
        String sql = "select * from article where author=?";
        Object[] data = {author};
        ResultSet rs = JdbcUtil.find(sql,data);
        List<Article> list = new ArrayList<>();
        try{
            while(rs.next()){
                Article article = new Article(rs.getInt("aid"),rs.getString("head"),
                        rs.getString("content"),rs.getInt("looknum"),
                        rs.getInt("zannum"),rs.getInt("shounum"),rs.getString("type"),
                        rs.getString("author"),rs.getString("time"));
                list.add(article);
            }
        }catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void addArticle(Article article) {
        String sql = "insert into article values(null,?,?,0,0,0,?,?,?)";
        Object[] data = {article.getHead(),article.getContent(),article.getType(),article.getAuthor(), DateToStringUtil.dateToString(new Date())};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public Article find(int aid) {
        String sql = "select * from article where aid=?";
        Object[] data = {aid};
        ResultSet rs = JdbcUtil.find(sql,data);
        Article article = null;
        try{
            while(rs.next()){
                article = new Article(rs.getInt("aid"),rs.getString("head"),
                        rs.getString("content"),rs.getInt("looknum"),
                        rs.getInt("zannum"),rs.getInt("shounum"),rs.getString("type"),
                        rs.getString("author"),rs.getString("time"));
            }
        }catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return article;
    }

    @Override
    public List<Article> find() {
        String sql = "select * from article";
        ResultSet rs = JdbcUtil.find(sql,null);
        List<Article> list = new ArrayList<>();
        try{
            while(rs.next()){
                Article article = new Article(rs.getInt("aid"),rs.getString("head"),
                        rs.getString("content"),rs.getInt("looknum"),
                        rs.getInt("zannum"),rs.getInt("shounum"),rs.getString("type"),
                        rs.getString("author"),rs.getString("time"));
                list.add(article);
            }
        }catch(SQLException throwable){
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void delArticle(int aid) {
        String sql = "delete from article where aid=?";
        Object[] data = {aid};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void upDataLookNum(Article article) {
        String sql = "update article set looknum=? where aid=?";
        Object[] data = {article.getViews()+1,article.getAid()};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void addSupport(Article article) {
        String sql = "update article set zannum=? where aid=?";
        Object[] data = {article.getSupports()+1,article.getAid()};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void delSupport(Article article) {
        String sql = "update article set zannum=? where aid=?";
        Object[] data = {article.getSupports()-1,article.getAid()};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public int[] totalPage(int count) {
        String sql = "select count(*) from article";
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
    public List<Article> findByPage(int page, int count) {
        ArrayList<Article> list = new ArrayList<>();
        String sql = "select * from article limit ?,?";
        Object[] data = {(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while(rs.next()){
                Article article = new Article(rs.getInt("aid"),rs.getString("head"),
                        rs.getString("content"),rs.getInt("looknum"),
                        rs.getInt("zannum"),rs.getInt("shounum"),rs.getString("type"),
                        rs.getString("author"),rs.getString("time"));
                list.add(article);
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Article> workerFindByPage(int page,int count,String author) {
        ArrayList<Article> list = new ArrayList<>();
        String sql = "select * from article where author=? limit ?,?";
        Object[] data = {author,(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            if(rs != null){
                while(rs.next()){
                    Article article = new Article(rs.getInt("aid"),rs.getString("head"),
                            rs.getString("content"),rs.getInt("looknum"),
                            rs.getInt("zannum"),rs.getInt("shounum"),rs.getString("type"),
                            rs.getString("author"),rs.getString("time"));
                    list.add(article);
                }
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public int[] totalPage(int count, String author) {
        String sql = "select count(*) from article where author=?";
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
    public int[] userKeyTotalPage(int count, String keyword) {
        String sql = "select count(*) from article where head like ?";
        int[] arr = {0,1};
        Object[] data = {"%"+keyword+"%"};
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
    public List<Article> findByPage(int page, int count, String keyword) {
        ArrayList<Article> list = new ArrayList<>();
        String sql = "select * from article where head like ? limit ?,?";
        Object[] data = {"%"+keyword+"%",(page-1)*count,count};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
            while(rs.next()){
                Article article = new Article(rs.getInt("aid"),rs.getString("head"),
                        rs.getString("content"),rs.getInt("looknum"),
                        rs.getInt("zannum"),rs.getInt("shounum"),rs.getString("type"),
                        rs.getString("author"),rs.getString("time"));
                list.add(article);
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public int findSumLookNum(String type) {
        int a = 0;
        String sql = "select sum(looknum) from article where type=?";
        Object[] data = {type};
        ResultSet rs = JdbcUtil.find(sql,data);
        try {
                while(rs.next()){
                    a = rs.getInt("sum(looknum)");
                }
        } catch (SQLException throwable) {
                throwable.printStackTrace();
        }
        return a;
    }

    @Override
    public void addCang(Article article) {
        String sql = "update article set shounum=? where aid=?";
        Object[] data = {article.getCollections()+1,article.getAid()};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

    @Override
    public void delCang(Article article) {
        String sql = "update article set shounum=? where aid=?";
        Object[] data = {article.getCollections()-1,article.getAid()};
        JdbcUtil.addDeleteUpdate(sql,data);
    }

}