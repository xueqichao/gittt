package com.xueqichao.event.entity;

public class ScenicStatement
{
    int staid;
    String statement;
    int sid;
    int uid;

    public int getStaid() {
        return staid;
    }

    public void setStaid(int staid) {
        this.staid = staid;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public ScenicStatement(int staid, String statement, int sid, int uid) {
        this.staid = staid;
        this.statement = statement;
        this.sid = sid;
        this.uid = uid;
    }

}
