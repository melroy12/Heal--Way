package com.example.hospital_ms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdaptor2 extends RecyclerView.Adapter<MyAdaptor2.MyViewHolder> {
    Context context;
    ArrayList<Prescription> list;
//    ArrayList<HashMap> list2;
    public MyAdaptor2(Context context, ArrayList<Prescription> list) {
        this.context = context;
        this.list = list;
    }
//    public MyAdaptor2(Context context, ArrayList<HashMap> list) {
//        this.context = context;
//        this.list2 = list;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.eachpresc, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Prescription prescription = list.get(position);

        holder.PresDate.setText(prescription.getDate());
        holder.PresTime.setText(prescription.getTime());
        holder.Dosage.setText(prescription.getDosage());
        holder.datetime.setText(prescription.getDaTi());
//        holder.PresDate.setText(prescription.get("PresDate").toString());
//        holder.PresTime.setText(prescription.get("PresTime").toString());
//        holder.Dosage.setText(prescription.get("Dosage").toString());
//        holder.datetime.setText(prescription.get("datetime").toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView PresDate,PresTime,Dosage, datetime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            PresDate = itemView.findViewById(R.id.TVcardDate2);
            PresTime = itemView.findViewById(R.id.TVcardTime2);
            Dosage = itemView.findViewById(R.id.textViewDos);
            datetime = itemView.findViewById(R.id.TVcardDaTi2);

        }
    }
}