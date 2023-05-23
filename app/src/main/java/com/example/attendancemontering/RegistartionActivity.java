package com.example.attendancemontering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemontering.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class RegistartionActivity extends AppCompatActivity {

    EditText email,password,name;
    Button  register;
    TextView Login;
    ImageView backbtn;

    private FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);


        //making instance of firebasedatabase and firebaseAuth
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        //initializing variables
        email = this.findViewById(R.id.email);
        password = this.findViewById(R.id.password);
        name = this.findViewById(R.id.name);
        backbtn = this.findViewById(R.id.backbtn);
        Login = this.findViewById(R.id.login);
        register = this.findViewById(R.id.registerbtn);


        //applying intent filter to login textview and back backbtn

        //login textview
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistartionActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //backBtn
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistartionActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String savemail = email.getText().toString();
                String savepassword = password.getText().toString();
                String savename = name.getText().toString();
                auth.createUserWithEmailAndPassword(savemail,savepassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String UId = task.getResult().getUser().getUid();
                            Users users = new Users(savemail,savepassword,savename);
                            database.getReference().child("Users").child(UId).setValue(users);
                            Toast.makeText(RegistartionActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistartionActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(RegistartionActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }


}