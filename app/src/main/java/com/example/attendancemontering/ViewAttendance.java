package com.example.attendancemontering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemontering.Adapters.MyAdapter;
import com.example.attendancemontering.Models.RetriveAtt;
import com.example.attendancemontering.Models.UserAttendance;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAttendance extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<RetriveAtt> list;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        Intent intent = getIntent();
        String uid = intent.getStringExtra("id");
        Log.d("uid", "onCreate: "+uid);
        text = findViewById(R.id.attheading);
        recyclerView = findViewById(R.id.recyclerView);
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Attendance");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    RetriveAtt ma = snapshot1.getValue(RetriveAtt.class);
                     String timeStampPunchIN = ma.getTimeStampPunchIN();
                     String timeStampPunchOut = ma.getTimeStampPunchOut();
                     list.add(ma);

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}