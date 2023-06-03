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
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemontering.Models.UserAttendance;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

    String punchInTime;

    Calendar calendar;

    Intent id;

    String uid;

    //location
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;
    private String timedede;

    ArrayList<String> months = new ArrayList<>();



    FirebaseDatabase database;
    FirebaseDatabase forUpdating;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_punch_in_punch_out);
        //adding months
        months.add("JAN");
        months.add("FEB");
        months.add("MAR");
        months.add("APR");
        months.add("MAY");
        months.add("JUN");
        months.add("JUL");
        months.add("AUG");
        months.add("SEP");
        months.add("OCT");
        months.add("NOV");
        months.add("DEC");

        //initializing variables
        mMainlayout = findViewById(R.id.mmainlayout);
        punchIN = findViewById(R.id.punchin);
        punchOut = findViewById(R.id.punchout);
        viewatt = findViewById(R.id.viewatt);
        locationatt = findViewById(R.id.locationatt);
        digitalClock =findViewById(R.id.digitalClock);



        //collecting data from intents
        id  = getIntent();
        uid = id.getStringExtra("userid");

        //Firebase
        database = FirebaseDatabase.getInstance();
        forUpdating = FirebaseDatabase.getInstance();
       /* database.getReference().child("Users").child("Attendance");*/

        //time
         calendar = Calendar.getInstance();






        //location code
        location();


        punchIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PunchInbiometriccheck();

            }
        });

        punchOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PunchOutbiometriccheck();
            }
        });

        //viewAttendance
        viewatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewAttendance.class);
                intent.putExtra("id",uid);
                startActivity(intent);
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
                /*UserAttendance timestamp = new UserAttendance("Present");*/
                int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24-hour format
                int minute = calendar.get(Calendar.MINUTE);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                String weekName;

                switch (day) {
                    case 1:
                        weekName = "Sun";
                        break;
                    case 2:
                        weekName = "Mon";
                        break;
                    case 3:
                        weekName = "Tue";
                        break;
                    case 4:
                        weekName = "Wed";
                        break;
                    case 5:
                        weekName = "Thu";
                        break;
                    case 6:
                        weekName = "Fri";
                        break;
                    case 7:
                        weekName = "Sat";
                        break;
                    default:
                        weekName = "Invalid week";
                        break;
                }

                String monthName;
                switch (month) {
                    case 0:
                        monthName = "Jan";
                        break;
                    case 1:
                        monthName = "Feb";
                        break;
                    case 2:
                        monthName = "Mar";
                        break;
                    case 3:
                        monthName = "Apr";
                        break;
                    case 4:
                        monthName = "May";
                        break;
                    case 5:
                        monthName = "Jun";
                        break;
                    case 6:
                        monthName = "Jul";
                        break;
                    case 7:
                        monthName = "Aug";
                        break;
                    case 8:
                        monthName = "Sep";
                        break;
                    case 9:
                        monthName = "Oct";
                        break;
                    case 10:
                        monthName = "Nov";
                        break;
                    case 11:
                        monthName = "Dec";
                        break;
                    default:
                        monthName = "Invalid month";
                        break;
                }
                punchInTime = Integer.toString(hour)+":"+Integer.toString(minute);


                LocalDate currentDate = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    currentDate = LocalDate.now();
                }
                DateTimeFormatter formatter = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
                }
                final String formattedDate;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    formattedDate = currentDate.format(formatter);
                } else {
                    formattedDate = "Unknown"; // provide a default value
                }

                /* HashMap<String,String>timestamp = new HashMap<>();*/
                /*  timestamp.put(time,"Present");*/
                UserAttendance attendance = new UserAttendance(punchInTime,"not done",formattedDate.substring(0,2),weekName,monthName,true,false);
               /* database.getReference().child("Users").child(uid).child("Attendance").push().setValue(attendance);*/
                database.getReference().child("Users").child(uid).child("Attendance").child(formattedDate).setValue(attendance);
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
        biometricPrompt = new BiometricPrompt(SelectPunchInPunchOut.this, executor, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result){
                super.onAuthenticationSucceeded(result);
                int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24-hour format
                int minute = calendar.get(Calendar.MINUTE);
                String punchOutTime = Integer.toString(hour)+":"+Integer.toString(minute);
                LocalDate currentDate = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    currentDate = LocalDate.now();
                }
                DateTimeFormatter formatter = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
                }
                final String formattedDate;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    formattedDate = currentDate.format(formatter);
                } else {
                    formattedDate = "Unknown"; // provide a default value
                }


                HashMap attendance = new HashMap<>();
                attendance.put("punchOut",true);
                attendance.put("timeStampPunchOut",punchOutTime);



               /* UserAttendance attendance = new UserAttendance(true,true,"15:20",punchOutTime);*/

                database.getReference().child("Users").child(uid).child("Attendance").child(formattedDate).updateChildren(attendance);
                Toast.makeText(SelectPunchInPunchOut.this, "Login success", Toast.LENGTH_SHORT).show();
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