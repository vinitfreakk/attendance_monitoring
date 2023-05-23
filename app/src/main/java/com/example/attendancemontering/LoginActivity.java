package com.example.attendancemontering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    TextView register;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initializing variable
        email = findViewById(R.id.name);
        password =findViewById(R.id.email);
        login = findViewById(R.id.registerbtn);
        register = findViewById(R.id.registertv);

        //applying intent to register activity

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistartionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //adding login feature
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveemail = email.getText().toString();
                String savepassword = password.getText().toString();
                if(saveemail.equals(null)&&savepassword.equals(null)){
                    Toast.makeText(LoginActivity.this, "Don't leave the filed empty", Toast.LENGTH_SHORT).show();
                } else if (savepassword.length()>=6) {
                    Toast.makeText(LoginActivity.this, "Password must at least contain 6 character", Toast.LENGTH_SHORT).show();
                }else {
                    //login code here
                }
            }
        });




    }
}