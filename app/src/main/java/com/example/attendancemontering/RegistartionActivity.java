package com.example.attendancemontering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        backbtn = findViewById(R.id.backbtn);
        Login = findViewById(R.id.login);
        register = findViewById(R.id.registerbtn);


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
                String savepassword = email.getText().toString();
                auth.createUserWithEmailAndPassword(savemail,savepassword);

            }
        });

    }


}