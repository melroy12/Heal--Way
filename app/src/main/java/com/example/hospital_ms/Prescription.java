package com.example.hospital_ms;

public class Prescription {
    private String Dosage;
public String PresDate,PresTime,datetime;
    public Prescription()
    {
    }

//    public String getUid() {
//        return uid;
//    }

    public String getDate() {
        return PresDate;
    }

    public String getTime() {
        return PresTime;
    }

    public String getDaTi() {
        return datetime;
    }
    public String getDosage() {
        return "Dosage : "+Dosage;
    }

//    public Prescription(String uid, String date, String time,String dosage, String daTi) {
//        this.uid = uid;
//        this.PresDate = date;
//        this.PresTime = time;
//        this.Dosage=dosage;
//        this.datetime = daTi;
//    }
    public Prescription(String date, String time,String Dosage, String daTi) {
        this.PresDate = date;
        this.PresTime = time;
        this.Dosage=Dosage;
        this.datetime = daTi;
    }
}