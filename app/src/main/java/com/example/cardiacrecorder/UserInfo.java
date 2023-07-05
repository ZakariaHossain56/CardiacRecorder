package com.example.cardiacrecorder;

public class UserInfo {
    String userid;
    String name;
    String phone;
    String email;
    String height;
    String weight;

    public UserInfo() {
    }

    public UserInfo(String userid, String name, String phone, String email, String height, String weight) {
        this.userid = userid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.height = height;
        this.weight = weight;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
