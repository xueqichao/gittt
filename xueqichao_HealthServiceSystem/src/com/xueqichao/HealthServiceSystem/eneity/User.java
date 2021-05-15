package com.xueqichao.HealthServiceSystem.eneity;

public class User
{
    private int uid;
    private String username;
    private String password;
    private String position;
    private int state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User(String username, String password, String position, int state) {
        this.username = username;
        this.password = password;
        this.position = position;
        this.state = state;
    }

    public User(int uid, String username, String password, String position, int state) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.position = position;
        this.state = state;
    }

    public User(int uid, int state) {
        this.uid = uid;
        this.state = state;
    }
}
