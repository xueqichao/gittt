package com.xueqichao.HealthServiceSystem.eneity;

public class Statement
{
    private int sid;
    private String username;
    private String content;
    private int aid;
    private String author;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Statement(int sid, String username, String content, int aid, String author) {
        this.sid = sid;
        this.username = username;
        this.content = content;
        this.aid = aid;
        this.author = author;
    }

    public Statement(String username, String content, int aid, String author) {
        this.username = username;
        this.content = content;
        this.aid = aid;
        this.author = author;
    }
}
