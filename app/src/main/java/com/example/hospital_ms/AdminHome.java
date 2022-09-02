package com.example.hospital_ms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHome extends AppCompatActivity {
    Button btnAllApp,buttonAddPresc2,btnAllUsers;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btnAllApp=(Button)findViewById(R.id.btnAllApp);
        Button  btnLout=(Button)findViewById(R.id.btnLout);
        buttonAddPresc2=findViewById(R.id.buttonAddPresc2);
        btnAllUsers=findViewById(R.id.btnAllUsers);

        btnAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHome.this,AdminAllUsers.class));
            }
        });

        btnAllApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent=new Intent(AdminHome.this,AdminAppointments.class);
                   startActivity(intent);
            }
        });
        buttonAddPresc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHome.this,AdminAddPres.class);
                startActivity(intent);
            }
        });


        btnLout.setOnClickListener(view -> {
            Intent intent=new Intent(AdminHome.this,LoginActivity.class);
            fAuth.signOut();
            startActivity(intent);

        });

//        btnBook=(Button)findViewById(R.id.btnpresc);
//
//        btnMyAc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //  Intent intent=new Intent(adminhome.this,MyAccountActivity.class);
//                //   startActivity(intent);
//            }
//        });
//        btnLout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(AdminHome.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(AdminHome.this,BookActivity.class);
//                startActivity(intent);
//            }
//        });
//        btnPrevBook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent=new Intent(adminhome.this,OrdersHistoryActivity.class);
////                startActivity(intent);
//            }



 //       });
    }
}


