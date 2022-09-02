package com.example.hospital_ms;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {
    Context context;
    ArrayList<User> list;

    public MyAdapter3(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.eachuser, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
        holder.address.setText(user.getAddress());
        holder.password.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, address, password;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.TVcardUName);
            email = itemView.findViewById(R.id.TVcardEmail);
            phone = itemView.findViewById(R.id.TVcardPhone);
            address =itemView.findViewById(R.id.TVcardAddress);
            password=itemView.findViewById(R.id.TVcardPasswd);
        }
    }
}
