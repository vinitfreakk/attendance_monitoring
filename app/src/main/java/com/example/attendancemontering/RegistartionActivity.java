package com.example.attendancemontering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegistartionActivity extends AppCompatActivity {

    EditText email,password,name;
    Button  register;
    TextView Login;
    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);

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







    }
}