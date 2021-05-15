package com.xueqichao.HealthServiceSystem.eneity;

import java.util.Date;

public class Article {

    private int aid;
    private String head;
    private String content;
    private int views;
    private int supports;
    private int collections;
    private String type;
    private String author;
    private String date;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getSupports() {
        return supports;
    }

    public void setSupports(int supports) {
        this.supports = supports;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Article(int aid, String head, String content, int views, int supports, int collections, String type, String author, String date) {
        this.aid = aid;
        this.head = head;
        this.content = content;
        this.views = views;
        this.supports = supports;
        this.collections = collections;
        this.type = type;
        this.author = author;
        this.date = date;
    }

    public Article(String head, String content, String type, String author) {
        this.head = head;
        this.content = content;
        this.type = type;
        this.author = author;
    }



}
