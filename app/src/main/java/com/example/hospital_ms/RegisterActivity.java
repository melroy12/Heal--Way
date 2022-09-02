package com.example.hospital_ms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText editTxtName,editTxtPhone,editTextPassword,editTextAddress,editTextEmail;
    TextView backToLogin;
    Button btnRegister;

    private FirebaseAuth mAuth;
    private DatabaseReference userDBref;
    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backToLogin=(TextView)findViewById(R.id.backToLogin);

        editTxtName=(EditText)findViewById(R.id.editTxtName);
        editTxtPhone=(EditText)findViewById(R.id.editTxtPhone);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextAddress=(EditText)findViewById(R.id.editTextAddress);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnRegister=(Button)findViewById(R.id.btnRegister);

        loader=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

    backToLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    });

    btnRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String nameReg = editTxtName.getText().toString().trim();
        String phoneReg = editTxtPhone.getText().toString().trim();
        String passwdReg = editTextPassword.getText().toString().trim();
        String addressReg = editTextAddress.getText().toString().trim();
        String emailReg = editTextEmail.getText().toString().trim();
if(nameReg.length()==0 || phoneReg.length()==0 || passwdReg.length()==0 || addressReg.length()==0 ||emailReg.length()==0)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);

            builder.setCancelable(true);
            builder.setTitle("Empty field!");
            builder.setMessage("Fill all fields");

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //alertTextView.setVisibility(View.VISIBLE);
                }
            });
            builder.show();
        }
        else
        {
            loader.setMessage("Registration in progress");
            loader.setCanceledOnTouchOutside(false);
            loader.show();

            mAuth.createUserWithEmailAndPassword(emailReg,passwdReg)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                String error = task.getException().toString();
                                Toast.makeText(RegisterActivity.this,
                                        "Error occured:"+error,Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                String crntUserId = mAuth.getCurrentUser().getUid();
                                userDBref = FirebaseDatabase.getInstance().getReference().child("Users").child(crntUserId);
                                HashMap userInfo = new HashMap();
                                userInfo.put("name",nameReg);
                                userInfo.put("phone",phoneReg);
                                userInfo.put("password",passwdReg);
                                userInfo.put("address",addressReg);
                                userInfo.put("email",emailReg);
                                userInfo.put("type","customer");

                                userDBref.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this,
                                                    "Details set successfully",Toast.LENGTH_SHORT).show();

                                        }
                                        else
                                        {
                                            Toast.makeText(RegisterActivity.this,task.getException().toString(),
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                        finish();
                                        loader.dismiss();
                                    }
                                });
                            }
                        }
                    });
            }
        }
        });

    }


}