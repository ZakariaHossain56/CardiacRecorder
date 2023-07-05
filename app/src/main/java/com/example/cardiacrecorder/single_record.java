package com.example.cardiacrecorder;

public class single_record {

    String date;
    String time;
    String sys;
    String dias;
    String rate;
    String comment;
    String UserID;
    String RecordID;


    public single_record() {
    }

    public single_record(String date, String time, String sys, String dias, String rate, String comment, String userID, String recordID) {
        this.date = date;
        this.time = time;
        this.sys = sys;
        this.dias = dias;
        this.rate = rate;
        this.comment = comment;
        UserID = userID;
        RecordID = recordID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getRecordID() {
        return RecordID;
    }

    public void setRecordID(String recordID) {
        RecordID = recordID;
    }
}
