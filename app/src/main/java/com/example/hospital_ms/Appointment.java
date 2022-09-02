package com.example.hospital_ms;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Appointment {
    public String uid,AppDate,AppTime, datetime;

    public Appointment()
    {

    }

    public String getUid() {

        return uid;
    }

    public String getDate() {
        return AppDate;
    }

    public String getTime() {
        return AppTime;
    }

    public String getDaTi() {
        return datetime;
    }

    public Appointment(String uid, String date, String time, String daTi) {
        this.uid = uid;
        this.AppDate = date;
        this.AppTime = time;
        this.datetime = daTi;
    }
}