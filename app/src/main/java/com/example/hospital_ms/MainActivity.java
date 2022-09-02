package com.example.hospital_ms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
Button btnMyappoint,btnpresc,btnLout,btnaddapp;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMyappoint=(Button)findViewById(R.id.btnAllUsers);
        btnLout=(Button)findViewById(R.id.btnLout);
        btnpresc=(Button)findViewById(R.id.btnMypresc);
        btnaddapp=(Button)findViewById(R.id.buttonAddPresc2);
        fAuth=FirebaseAuth.getInstance();
        btnaddapp.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,BookActivity.class);
            startActivity(intent);
        });
        btnpresc.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,MyPrescriptions.class);
            startActivity(intent);
        });

        btnMyappoint.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,MyAppointments.class);
            startActivity(intent);
        });

        btnLout.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            fAuth.signOut();
           startActivity(intent);

        });

//        btnPrevBook.setOnClickListener(view -> {
////                Intent intent=new Intent(MainActivity.this,OrdersHistoryActivity.class);
////                startActivity(intent);
//        });
    }
}