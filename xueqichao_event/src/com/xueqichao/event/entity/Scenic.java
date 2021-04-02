package com.xueqichao.event.entity;

public class Scenic
{
    int sid;
    String scenicName;
    String scenicDesc;
    int staNum;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getScenicDesc() {
        return scenicDesc;
    }

    public void setScenicDesc(String scenicDesc) {
        this.scenicDesc = scenicDesc;
    }

    public int getStaNum() {
        return staNum;
    }

    public void setStaNum(int staNum) {
        this.staNum = staNum;
    }

    public Scenic(String scenicName, String scenicDesc) {
        this.scenicName = scenicName;
        this.scenicDesc = scenicDesc;
    }
}
