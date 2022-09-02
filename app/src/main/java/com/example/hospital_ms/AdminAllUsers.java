package com.example.hospital_ms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminAllUsers extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<User> list;
    DatabaseReference databaseReference;
    MyAdapter3 adapter;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all_users);

        rv=findViewById(R.id.recycleViewer);
        Toast.makeText(AdminAllUsers.this, "Loading...", Toast.LENGTH_SHORT).show();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter3(this,list);
        rv.setAdapter(adapter);
//        Appointment app= new Appointment("bn","hj","hjj","uiui");
//        databaseReference.push().setValue(app);
//        list.add(app);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    c++;
                    User user=dataSnapshot.getValue(User.class);
                    list.add(user);
          //          Toast.makeText(AdminAllUsers.this,c , Toast.LENGTH_SHORT).show();
//                    Map<String, String> childs = new HashMap<>();
//                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                        String date = ds.child("date").getValue(String.class);
//                        String time = ds.child("time").getValue(String.class);
//                       // Log.d("TAG", lat + " / " + long);
//                        childs.put(date, time);
//                        list.add(childs);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}