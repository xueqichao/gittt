package com.xueqichao.event.entity;

public class DealFeedback
{
    int did;
    String content;
    String user;
    int fid;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public DealFeedback(int did, String content, String user, int fid) {
        this.did = did;
        this.content = content;
        this.user = user;
        this.fid = fid;
    }

    public DealFeedback() {
    }
}
