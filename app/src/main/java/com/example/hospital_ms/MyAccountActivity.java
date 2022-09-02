//package com.example.hospital_ms;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class MyAccountActivity extends AppCompatActivity {
//TextView Vname,Vemail,Vphone,Vaddress,Vpasswd;
//String gname,gemail,gphone,gaddress,gpassword;
//Button btnEditProf,logout1,Home;
//FirebaseAuth mAuth;
//FirebaseUser user;
//DatabaseReference databaseReference;
//String userID;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_account);
//        Vname=(TextView)findViewById(R.id.textVName);
//        Vemail=(TextView)findViewById(R.id.textVEmail);
//        Vphone=(TextView)findViewById(R.id.textVPhone);
//        Vaddress=(TextView)findViewById(R.id.textVAddress);
//        Vpasswd=(TextView)findViewById(R.id.textVPassword);
//mAuth=FirebaseAuth.getInstance();
//        btnEditProf=(Button)findViewById(R.id.btnEditProf);
//        logout1=(Button)findViewById(R.id.btnLogout1);
//        Home=(Button)findViewById(R.id.btnHome);
//
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        databaseReference = firebaseDatabase.getReference("Users");
//        userID=user.getUid();
//
//        databaseReference.child(userID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User userProfile=snapshot.getValue(User.class);
//
//                if(userProfile!=null)
//                {
//                    String name= userProfile.name;
//                    String email= userProfile.email;
//                    String phone= userProfile.phone;
//                    String address= userProfile.address;
//                    String password= userProfile.password;
//                    gname=name;
//                    gemail=email;
//                    gphone=phone;
//                    gaddress=address;
//                    gpassword=password;
//                    Vname.setText(name);
//                    Vemail.setText(email);
//                    Vphone.setText(phone);
//                    Vaddress.setText(address);
//                    Vpasswd.setText(password);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MyAccountActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        btnEditProf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(view.getContext(),EditProfile.class);
//                intent.putExtra("name",gname);
//                intent.putExtra("email",gemail);
//                intent.putExtra("phone",gphone);
//                intent.putExtra("address",gaddress);
//                intent.putExtra("password",gpassword);
//                startActivity(intent);
//            }
//        });
//
//        logout1.setOnClickListener(view -> {
//            Intent intent=new Intent(MyAccountActivity.this,LoginActivity.class);
//            mAuth.signOut();
//            startActivity(intent);
//            finish();
//        });
//        Home.setOnClickListener(view -> {
//            Intent intent=new Intent(MyAccountActivity.this, adminhome.class);
//            startActivity(intent);
//        });
//    }
//}
