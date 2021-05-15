package com.xueqichao.HealthServiceSystem.eneity;

public class Feedback
{
    private int fid;
    private String username;
    private String head;
    private String content;
    private int state;
    private String feedbackContent;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Feedback(int fid, String username, String head, String content, int state, String feedbackContent) {
        this.fid = fid;
        this.username = username;
        this.head = head;
        this.content = content;
        this.state = state;
        this.feedbackContent = feedbackContent;
    }

    public Feedback(String username, String head, String content) {
        this.username = username;
        this.head = head;
        this.content = content;
    }
}
