package com.example.datiqiapp.bean;

import java.io.Serializable;
//题目数据库表
public class Question implements Serializable {

    private int id;
    private String leixing;
    private String wenti;
    private String daan;

    public Question() {

    }
    public Question(int id, String leixing, String wenti, String daan) {
        this.id = id;
        this.leixing = leixing;
        this.wenti = wenti;
        this.daan = daan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getWenti() {
        return wenti;
    }

    public void setWenti(String wenti) {
        this.wenti = wenti;
    }

    public String getDaan() {
        return daan;
    }

    public void setDaan(String daan) {
        this.daan = daan;
    }
}
