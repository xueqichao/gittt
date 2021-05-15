package com.xueqichao.HealthServiceSystem.service;

import com.xueqichao.HealthServiceSystem.dao.ArticleImpl;
import com.xueqichao.HealthServiceSystem.eneity.Article;

import java.util.List;

public class ArticleService
{
    ArticleImpl articleDao = new ArticleImpl();

    public List find(String author){
        return articleDao.find(author);
    }

    public void addArticle(Article article){
        articleDao.addArticle(article);
    }

    public Article find(int aid){
        return articleDao.find(aid);
    }

    public List<Article> find(){
        return articleDao.find();
    }

    public void delArticle(int aid){
        articleDao.delArticle(aid);
    }

    public void upDataLookNum(Article article){
        articleDao.upDataLookNum(article);
    }

    public void addSupport(Article article){
        articleDao.addSupport(article);
    }

    public void delSupport(Article article){
        articleDao.delSupport(article);
    }

    public int[] totalPage(int count){return articleDao.totalPage(count);}

    public List<Article> findByPage(int page, int count){return articleDao.findByPage(page,count);}

    public List<Article> workerFindByPage(int page,int count,String author){return articleDao.workerFindByPage(page,count,author);}

    public int[] totalPage(int count, String author){return articleDao.totalPage(count,author);}

    public int[] userKeyTotalPage(int count, String keyword){return articleDao.userKeyTotalPage(count,keyword);}

    public List<Article> findByPage(int page, int count, String keyword){return articleDao.findByPage(page,count,keyword);}

    public int findSumLookNum(String type){return articleDao.findSumLookNum(type);}

    public void addCang(Article article){articleDao.addCang(article);}

    public void delCang(Article article){articleDao.delCang(article);}
}
