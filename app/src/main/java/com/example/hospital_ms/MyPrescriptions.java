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

public class MyPrescriptions extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Prescription> list;
    //ArrayList<HashMap> list2;
    DatabaseReference databaseReference;
    MyAdaptor2 adapter;
    int c=0;
    FirebaseUser user;
    String userID;
    Query ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prescriptions);
        user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();
        rv=findViewById(R.id.recycleView2);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Prescriptions");
        ref=databaseReference.orderByChild("uid").equalTo(userID);
//        Prescription prescription= new Prescription("PresDate","789","789","567890");
//        databaseReference.push().setValue(prescription);
          list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdaptor2(this,list);
        rv.setAdapter(adapter);

   //     list2 = new ArrayList<>();

//        Appointment app= new Appointment("bn","hj","hjj","uiui");
//        databaseReference.push().setValue(app);
 //       list.add(app);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
//                    HashMap presc = new HashMap();
//                    presc.put("PresDate",dataSnapshot.child("PresDate").getValue(String.class));
//                    presc.put("PresTime",dataSnapshot.child("PresTime").getValue(String.class));
//                    presc.put("datetime",dataSnapshot.child("datetime").getValue(String.class));
//                    presc.put("Dosage",dataSnapshot.child("Dosage").getValue(String.class));
                    c++;
                //    Toast.makeText(MyPrescriptions.this, dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                   Prescription prescription=dataSnapshot.getValue(Prescription.class);
                    list.add(prescription);
                   // list2.add(presc);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}