package com.example.attendancemontering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

public class SelectPunchInPunchOut extends AppCompatActivity {
    BiometricPrompt biometricPrompt;
    /*The BiometricPrompt class is part of the AndroidX Biometric library introduced in Android 9 (API level 28) as a standardized way to authenticate users using biometric data,
such as fingerprints or face recognition. It provides a system-provided biometric dialog for authentication purposes.*/
    BiometricPrompt.PromptInfo promptInfo;
    /*The PromptInfo class is a nested class within BiometricPrompt that represents the configuration options for the biometric prompt.
It allows you to customize the appearance and behavior of the biometric dialog, such as the title, subtitle, and other related settings*/
    ConstraintLayout mMainlayout;

    Button punchIN,punchOut,viewatt;
    TextView locationatt;
    DigitalClock digitalClock;

    //location
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;


    FirebaseDatabase database;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_punch_in_punch_out);

        mMainlayout = findViewById(R.id.mmainlayout);
        punchIN = findViewById(R.id.punchin);
        punchOut = findViewById(R.id.punchout);
        viewatt = findViewById(R.id.viewatt);
        locationatt = findViewById(R.id.locationatt);
        digitalClock =findViewById(R.id.digitalClock);







        //location code
        location();


        punchIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PunchInbiometriccheck();


            }
        });

        punchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PunchOutbiometriccheck();
            }
        });

    }

    //punchIn
    public void PunchInbiometriccheck(){
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "Device doesn't have required hardware", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Hardware not responding", Toast.LENGTH_SHORT).show();
                break;


            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "Biometric not enrolled", Toast.LENGTH_SHORT).show();
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(SelectPunchInPunchOut.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(SelectPunchInPunchOut.this, "Login success", Toast.LENGTH_SHORT).show();
                /* mMainlayout.setVisibility(View.VISIBLE);*/
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Punch In")
                .setDescription("Mark here").setDeviceCredentialAllowed(true).build();

        biometricPrompt.authenticate(promptInfo);
    }

    //punchOut
    public void PunchOutbiometriccheck(){
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "Device doesn't have required hardware", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Hardware not responding", Toast.LENGTH_SHORT).show();
                break;


            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "Biometric not enrolled", Toast.LENGTH_SHORT).show();
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(SelectPunchInPunchOut.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(SelectPunchInPunchOut.this, "Login success", Toast.LENGTH_SHORT).show();
                /* mMainlayout.setVisibility(View.VISIBLE);*/
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Punch Out")
                .setDescription("Mark here").setDeviceCredentialAllowed(true).build();

        biometricPrompt.authenticate(promptInfo);
    }

    public void location(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){
                        Geocoder gecoder = new Geocoder(SelectPunchInPunchOut.this, Locale.getDefault());
                        List<Address> addresses = null;

                        try {
                            addresses = gecoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                          /*  lattitude.setText("Lattitude: "+addresses.get(0).getLatitude());
                            longitude.setText("Longitude: "+addresses.get(0).getLongitude());*/
                            locationatt.setText(addresses.get(0).getAddressLine(0));
                            /*city.setText("City: "+addresses.get(0).getLocality());
                            country.setText("Country: "+addresses.get(0).getCountryName());*/
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }
            });


        }else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(SelectPunchInPunchOut.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


                location();

            }else {


                Toast.makeText(SelectPunchInPunchOut.this,"Please provide the required permission", Toast.LENGTH_SHORT).show();

            }



        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}