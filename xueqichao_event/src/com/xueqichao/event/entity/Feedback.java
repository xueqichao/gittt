package com.xueqichao.event.entity;

public class Feedback
{
    int fid;
    String username;
    String content;
    int state;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Feedback(int fid, String username, String content, int state) {
        this.fid = fid;
        this.username = username;
        this.content = content;
        this.state = state;
    }

    public Feedback() {
    }
}
