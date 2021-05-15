package com.xueqichao.HealthServiceSystem.service;

import com.xueqichao.HealthServiceSystem.dao.ArticleTypeImpl;
import com.xueqichao.HealthServiceSystem.eneity.ArticleType;

import java.util.List;

public class ArticleTypeService {

    ArticleTypeImpl articleTypeDao = new ArticleTypeImpl();

    public List<ArticleType> findAll(){
        return articleTypeDao.findAll();
    }

    public void delType(int tid){
        articleTypeDao.delType(tid);
    }

    public void addType(String name){
        articleTypeDao.addType(name);
    }

    public boolean typeIsExist(String typename){return articleTypeDao.typeIsExist(typename);}

    public int[] totalPage(int count){
        return articleTypeDao.totalPage(count);
    }

    public List<ArticleType> findByPage(int page, int count){
        return articleTypeDao.findByPage(page,count);
    }

    public void upDataLookNum(int lookNum, String type){articleTypeDao.upDataLookNum(lookNum,type);}
}
