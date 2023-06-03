package com.example.attendancemontering.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemontering.Models.RetriveAtt;
import com.example.attendancemontering.Models.UserAttendance;
import com.example.attendancemontering.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<RetriveAtt> list;

    public MyAdapter(Context context, ArrayList<RetriveAtt> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RetriveAtt userAttendance = list.get(position);
        if(userAttendance.getPunchIn().equals(true)&&userAttendance.getPunchOut().equals(true)){
            holder.maincard.setCardBackgroundColor(Color.parseColor("#66c341"));
            holder.daycard.setCardBackgroundColor(Color.parseColor("#f5c300"));
        }else {
            holder.maincard.setCardBackgroundColor(Color.parseColor("#f5c500"));
            holder.daycard.setCardBackgroundColor(Color.parseColor("#844266"));
        }



        holder.timestampPunchIn.setText(userAttendance.getTimeStampPunchIN());
        holder.timestampPunchOut.setText(userAttendance.getTimeStampPunchOut());
        holder.date.setText(userAttendance.getDate());
        holder.month.setText(userAttendance.getMonth());
        holder.day.setText(userAttendance.getDay());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView timestampPunchIn,timestampPunchOut,date,day,month;
        CardView daycard,maincard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            timestampPunchIn = itemView.findViewById(R.id.rtIN);
            timestampPunchOut = itemView.findViewById(R.id.rtOUT);
            date = itemView.findViewById(R.id.date);
            day = itemView.findViewById(R.id.day);
            month = itemView.findViewById(R.id.month);
            daycard = itemView.findViewById(R.id.daycard);
            maincard = itemView.findViewById(R.id.maincard);

        }
    }
}
