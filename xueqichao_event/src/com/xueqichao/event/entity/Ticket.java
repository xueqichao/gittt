package com.xueqichao.event.entity;

public class Ticket
{
    private int tid;
    private int number;
    private String time;
    private int sid;
    private int price;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Ticket(int tid, int number, String time, int price) {
        this.tid = tid;
        this.number = number;
        this.time = time;
        this.price = price;
    }
}
