package com.xueqichao.HealthServiceSystem.eneity;

public class Support
{
    private int aid;

    private int uid;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Support(int aid, int uid) {
        this.aid = aid;
        this.uid = uid;
    }
}
