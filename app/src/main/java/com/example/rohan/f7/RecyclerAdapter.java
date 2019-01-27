package com.example.rohan.f7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {


    List<ClassDetail> classDetails;
    Context context;
    public RecyclerAdapter(List<ClassDetail> classDetails, Context context)
    {
        this.classDetails=classDetails;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, parent,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyHolder holder, int position) {

        ClassDetail classlist=classDetails.get(position);
        holder.type.setText(classlist.getType());
        holder.subject.setText(classlist.getSubject());
        holder.timing.setText(classlist.getTiming());
        holder.faculty.setText(classlist.getFaculty());
        holder.room.setText(classlist.getRoom());
    }

    @Override
    public int getItemCount() {
        int arr=0;
        try {
            if(classDetails.size()==0)
            {
                arr=0;
            }else
            {
                arr=classDetails.size();
            }
        }catch (Exception e){

        }
        return arr;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView type, subject,timing, faculty,room;
        public MyHolder(View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
            subject=itemView.findViewById(R.id.subject);
            timing=itemView.findViewById(R.id.timing);
            faculty=itemView.findViewById(R.id.faculty);
            room=itemView.findViewById(R.id.room);
        }
    }
}
