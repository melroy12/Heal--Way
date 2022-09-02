package com.example.hospital_ms;

public class Orders {
    public String uid,datetime,AppDate,AppTime;
    public Orders(){

    }
    public Orders(String uid, String datetime, String AppDate, String AppTime)
    {
        this.uid= uid;
        this.datetime=datetime;
        this.AppDate=AppDate;
        this.AppTime=AppTime;
    }
    public Orders(String datetime, String AppDate, String AppTime)
    {
        this.datetime=datetime;
        this.AppDate=AppDate;
        this.AppTime=AppTime;
    }

    public String getUid() {
        return uid;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getAppDate() {
        return AppDate;
    }

    public String getAppTime() {
        return AppTime;
    }
}
