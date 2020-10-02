package com.rohan.rohan.f7;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {


    ArrayList<SubjectDetails> classDetails;
    Context context;
    int dayNumber;


    public RecyclerAdapter(ArrayList<SubjectDetails> classDetails, Context context, int dayNumber) {
        this.classDetails = classDetails;
        this.context = context;
        this.dayNumber = dayNumber;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyHolder holder, final int position) {
        AdRequest adRequest;
        MobileAds.initialize(context, "ca-app-pub-7233191134291345/9708550356");
        //MobileAds.initialize(context, "ca-app-pub-3940256099942544/6300978111");

        adRequest = new AdRequest.Builder().build();

        SubjectDetails classlist = classDetails.get(position);
        holder.type.setText(classlist.subjectType);
        holder.subject.setText(classlist.subjectName);
        holder.timing.setText(classlist.subjectTiming);
        holder.faculty.setText(classlist.subjectFaculty);
        holder.room.setText(classlist.subjectVenue);
        holder.forEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    ForEditDialog forEditDialog = new ForEditDialog(context, classDetails, position, dayNumber);
                    forEditDialog.show();
                    forEditDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
                }
            }


        });



        holder.adView.loadAd(adRequest);


    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (classDetails.size() == 0) {
            } else {
                arr = classDetails.size();
            }
        } catch (Exception ignored) {

        }
        return arr;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView type, subject, timing, faculty, room;
        ImageButton forEdit;
        AdView adView;


        MyHolder(View itemView) {
            super(itemView);
            adView = itemView.findViewById(R.id.bannerAd);
            type = itemView.findViewById(R.id.type);
            subject = itemView.findViewById(R.id.subject);
            timing = itemView.findViewById(R.id.timing);
            faculty = itemView.findViewById(R.id.faculty);
            room = itemView.findViewById(R.id.room);
            forEdit = itemView.findViewById(R.id.forEdit);
        }
    }
}
