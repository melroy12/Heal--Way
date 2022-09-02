package com.example.hospital_ms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BookActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,Reference1;
    Query ref;
    Button btnConfirm;
    FirebaseUser user;
    String userID,amt;
    int pendFlag=0;
    EditText editTextDate,editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        editTextDate=(EditText)findViewById(R.id.editTextDate);
        editTextTime=(EditText)findViewById(R.id.editTextTime);
        btnConfirm=(Button)findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String AppDate = editTextDate.getText().toString().trim();
                String AppTime = editTextTime.getText().toString().trim();
                user= FirebaseAuth.getInstance().getCurrentUser();
                userID=user.getUid();
//                firebaseDatabase = FirebaseDatabase.getInstance();
//
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String datetime=dtf.format(now);
//
//                databaseReference = FirebaseDatabase.getInstance().getReference().child("Appointments");
//                Appointment app1 = new Appointment(userID, datetime,AppDate ,AppTime );
//                databaseReference.push().setValue(app1);
               // Appointment app1 = new Appointment(userID, "datetime","AppDate" ,"AppTime" );

                HashMap appointment = new HashMap();
                appointment.put("uid",userID);
                appointment.put("datetime",datetime);
                appointment.put("AppDate",AppDate);
                appointment.put("AppTime",AppTime);

                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference myref=firebaseDatabase.getReference("Appointments");
                myref.push().setValue(appointment);
                Toast.makeText(BookActivity.this, "Success\nBook-Status:Pending", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(BookActivity.this,MainActivity.class);
                startActivity(intent);

//                Reference1 = firebaseDatabase.getReference().child("LpgPrice");
//                Reference1.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        databaseReference=FirebaseDatabase.getInstance().getReference().child("Orders");
//                        ref=databaseReference.orderByChild("uid").equalTo(userID);
//                        ref.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot)
//                            {
//
//                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                                    Orders order = dataSnapshot.getValue(Orders.class);
//                                    if (order.getStatus().equals("Pending"))
//                                    {
//                                        pendFlag = 1;
//                                        break;
//                                    }
//                                }
//                                if (pendFlag == 0) {
//
//                                    Orders order1 = new Orders(userID, datetime, amt, "Pending");
//                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders");
//                                    databaseReference.push().setValue(order1);
//                                    pendFlag = 2;
//                                    Toast.makeText(BookActivity.this, "Success\nBook-Status:Pending", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }
//                                else {
//                                        Toast.makeText(BookActivity.this, "Previous booking on progress", Toast.LENGTH_SHORT).show();
//                                        finish();
//                                }
//
//                            }
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//                                }
//                            });
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });
            }
        });
    }
}