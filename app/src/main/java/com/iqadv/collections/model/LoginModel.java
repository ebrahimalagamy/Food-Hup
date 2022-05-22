package com.iqadv.collections.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "user")
public class LoginModel implements Serializable {

    public LoginModel(String blocked, String pass, String name, String mobile, @NonNull String id, String pic, String email) {
        this.blocked = blocked;
        this.pass = pass;
        this.name = name;
        this.mobile = mobile;
        this.id = id;
        this.pic = pic;
        this.email = email;
    }

    @SerializedName("blocked")
    private String blocked;

    @SerializedName("pass")
    private String pass;

    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    private String id;

    @SerializedName("pic")
    private String pic;

    @SerializedName("email")
    private String email;

    public String getBlocked() {
        return blocked;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public String getEmail() {
        return email;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}