package com.xueqichao.event.entity;

public class Record
{
    int rid;
    int rNum;
    int uid;
    int tid;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public Record(int rid, int rNum, int uid, int tid) {
        this.rid = rid;
        this.rNum = rNum;
        this.uid = uid;
        this.tid = tid;
    }
}
