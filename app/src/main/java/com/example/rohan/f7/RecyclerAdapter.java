package com.example.rohan.f7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {


    private ArrayList<SubjectDetails> classDetails;
    private Context context;
    public RecyclerAdapter(ArrayList<SubjectDetails> classDetails, Context context)
    {
        this.classDetails=classDetails;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyHolder holder, int position) {

        SubjectDetails classlist=classDetails.get(position);
        holder.type.setText(classlist.subjectType);
        holder.subject.setText(classlist.subjectName);
        holder.timing.setText(classlist.subjectTiming);
        holder.faculty.setText(classlist.subjectFaculty);
        holder.room.setText(classlist.subjectVenue);
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
        }catch (Exception ignored){

        }
        return arr;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView type, subject,timing, faculty,room;
        MyHolder(View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
            subject=itemView.findViewById(R.id.subject);
            timing=itemView.findViewById(R.id.timing);
            faculty=itemView.findViewById(R.id.faculty);
            room=itemView.findViewById(R.id.room);
        }
    }
}
