package com.example.hospital_ms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {
    Context context;
    ArrayList<Appointment> list;
    String userid;
    public MyAdapter1(Context context, ArrayList<Appointment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.eachappointment, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Appointment appointment = list.get(position);
        holder.date.setText(appointment.getDate());
        holder.time.setText(appointment.getTime());
        holder.daTi.setText(appointment.getDaTi());
       // holder.patient.setText(appointment.getUid());

        DatabaseReference databaseReference;
        Query ref;
        String uname;
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    userid=appointment.getUid();
                   if( dataSnapshot.getKey().equals(userid)){
                    holder.patient.setText(dataSnapshot.child("name").getValue(String.class));}
//                    if(dataSnapshot.getKey() == userid)
//                    {
//                       // dataSnapshot.child("name").getValue(String.class)
//                        holder.patient.setText("hii");
//                 //     patientname(holder,dataSnapshot.child("name").getValue(String.class));
//                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, time, daTi, patient;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.TVcardDate);
            time = itemView.findViewById(R.id.TVcardTime);
            daTi = itemView.findViewById(R.id.TVcardDaTi);
            patient = itemView.findViewById(R.id.TVcardName);
        }
    }

//    public void patientname(@NonNull MyViewHolder holder, String un){
//        holder.patient.setText(un);
//    }
}