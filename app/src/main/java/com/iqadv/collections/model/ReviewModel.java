package com.iqadv.collections.model;

public class ReviewModel {
    private String UserName;
    private String date;
    private String desc;
    private int image;

    public ReviewModel(String userName, String date, String desc, int image) {
        UserName = userName;
        this.date = date;
        this.desc = desc;
        this.image = image;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
