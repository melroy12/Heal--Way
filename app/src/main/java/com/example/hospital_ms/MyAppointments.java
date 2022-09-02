package com.example.hospital_ms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAppointments extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Appointment> list;
    DatabaseReference databaseReference;
    MyAdapter1 adapter;
    int c=0;
    FirebaseUser user;
    String userID;
    Query ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();
        rv=findViewById(R.id.recycleView1);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Appointments");
        ref=databaseReference.orderByChild("uid").equalTo(userID);

        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));        adapter = new MyAdapter1(this,list);
        rv.setAdapter(adapter);
//        Appointment app= new Appointment("bn","hj","hjj","uiui");
//        databaseReference.push().setValue(app);
//        list.add(app);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    c++;
                    Appointment appointment=dataSnapshot.getValue(Appointment.class);
                    list.add(appointment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}