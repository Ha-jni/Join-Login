package com.hajni.join.model;

public class User {

    private int id;
    private String userId;
    private String passWord;

    public User() {
    }

    public User(int id, String userId, String passWord) {
        this.id = id;
        this.userId = userId;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
