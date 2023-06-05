package com.example.attendancemontering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    TextView register;
    Button login;

    private FirebaseAuth auth;

    FirebaseDatabase database;

    FirebaseUser firebaseuser; //used to get the current user.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.logreg));
        //making instance of firebasedatabase and firebaseAuth
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        firebaseuser=auth.getCurrentUser();

        //initializing variable
        email = this.findViewById(R.id.lemail);
        password =this.findViewById(R.id.lpassword);
        login = this.findViewById(R.id.loginbtn);
        register = this.findViewById(R.id.registertv);

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
                if(!saveemail.isEmpty()&&!savepassword.isEmpty()){
                    auth.signInWithEmailAndPassword(saveemail,savepassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"logged in Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,SelectPunchInPunchOut.class);
                                intent.putExtra("userid",firebaseuser.getUid());
                                Log.d("user", firebaseuser.getUid());
                                Toast.makeText(LoginActivity.this,firebaseuser.getUid() , Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }

}