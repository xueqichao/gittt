package com.xueqichao.HealthServiceSystem.eneity;

public class ArticleType {

    private int tid;
    private String name;
    private int lookNum;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLookNum() {
        return lookNum;
    }

    public void setLookNum(int lookNum) {
        this.lookNum = lookNum;
    }

    public ArticleType(int tid, String name, int lookNum) {
        this.tid = tid;
        this.name = name;
        this.lookNum = lookNum;
    }

}
