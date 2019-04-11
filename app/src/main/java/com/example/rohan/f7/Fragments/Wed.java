package com.example.rohan.f7.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rohan.f7.ClassDetail;
import com.example.rohan.f7.R;
import com.example.rohan.f7.RecyclerAdapter;
import com.example.rohan.f7.SQLite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Wed extends Fragment {

    FirebaseDatabase firebaseDatabase;
    List<ClassDetail> classDetails;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<ClassDetail> offline=new ArrayList<>();
    SQLite sqLite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wed, container, false);
        recyclerView=view.findViewById(R.id.recycle);
        sqLite= new SQLite(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Wed");
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classDetails=new ArrayList<ClassDetail>();
                offline.clear();
                int count=0;
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    ClassDetail value=dataSnapshot1.getValue(ClassDetail.class);
                    /*ClassDetail classDetail=new ClassDetail();*/
                    String type = value.getType();
                    String subject = value.getSubject();
                    String timing = value.getTiming();
                    String faculty = value.getFaculty();
                    String room = value.getRoom();
                    count++;
                    sqLite.insertClass("WED", count, type, subject, timing, faculty, room);
                    /*classDetail.setType(type);
                    classDetail.setSubject(subject);
                    classDetail.setTiming(timing);
                    classDetail.setFaculty(faculty);
                    classDetail.setRoom(room);*/
                    //offline.add(value);
                    classDetails.add(value);
                }
                RecyclerAdapter recyclerAdapter= new RecyclerAdapter(classDetails, getContext());
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        if (!isNetworkAvailable())
        {
            offline=sqLite.getClassDetail("WED");
            RecyclerAdapter recyclerAdapter= new RecyclerAdapter(offline, getContext());
            recyclerView.setAdapter(recyclerAdapter);
        }
        return view;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }





}
