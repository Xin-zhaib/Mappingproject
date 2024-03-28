package com.example.datiqiapp.bean;

import java.io.Serializable;
//用户数据库表
public class User implements Serializable {
    private int id;
    private String username;
    private String userpwd;


    public User() {

    }
    public User(int id, String username, String userpwd, String userchengji) {
        this.id = id;
        this.username = username;
        this.userpwd = userpwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

}
