package com.example.hospital_ms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminAddPres extends AppCompatActivity {

    Spinner spinnerV;
    DatabaseReference spinnerRef;
    ArrayList<String> spinnerList;
    ArrayAdapter<String> adapter;
    Button buttonAddP;
    EditText editTStartDate,editTDosage,editTStartTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_pres);

    spinnerV = findViewById(R.id.spinner);
    buttonAddP=findViewById(R.id.buttonAddP);
    spinnerRef= FirebaseDatabase.getInstance().getReference("Users");
    editTDosage=findViewById(R.id.editTDosage);
    editTStartDate=findViewById(R.id.editTStartDate);
    editTStartTime=findViewById(R.id.editTStartTime);

    spinnerList= new ArrayList<>();
    adapter =new ArrayAdapter<String>(AdminAddPres.this, android.R.layout.simple_spinner_dropdown_item,spinnerList);

        buttonAddP.setOnClickListener(view -> {

            String spinText = spinnerV.getSelectedItem().toString().trim();
            String sDate = editTStartDate.getText().toString().trim();
            String sTime = editTStartTime.getText().toString().trim();
            String dosage = editTDosage.getText().toString().trim();
            String UID="";
            for(int i=0;i<spinText.length();i++)
            {
                if(spinText.charAt(i)!=':')
                        UID+=spinText.charAt(i);
                else break;
            }


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String datetime=dtf.format(now);

            HashMap presc1 = new HashMap();
            presc1.put("uid",UID);
            presc1.put("PresDate",sDate);
            presc1.put("PresTime",sTime);
            presc1.put("Dosage",dosage);
            presc1.put("datetime",datetime);
//            Prescription presc1 = new Prescription(UID, sDate,sTime ,dosage ,datetime);
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference myref=firebaseDatabase.getReference("Prescriptions");

            myref.push().setValue(presc1);
            Toast.makeText(AdminAddPres.this, "Successfully added prescription", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(AdminAddPres.this,AdminHome.class);
            startActivity(intent);
        });

        spinnerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren())
                {
                    if(!item.child("name").getValue(String.class).equals("admin"))
                    spinnerList.add(item.getKey().toString()+" : "+item.child("name").getValue(String.class));
                }
                adapter.notifyDataSetChanged();
                spinnerV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}