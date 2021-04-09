package com.xueqichao.event.entity;

/**
 * @author lenovo
 * 这是封装用户的账号和密码的实体类
 */
public class User {
    private int uid;
    private String userName;
    private String userPassword;
    private int balance;
    private int state;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User(int uid, String userName, String userPassword, int balance, int state) {
        this.uid = uid;
        this.userName = userName;
        this.userPassword = userPassword;
        this.balance = balance;
        this.state = state;
    }
}
