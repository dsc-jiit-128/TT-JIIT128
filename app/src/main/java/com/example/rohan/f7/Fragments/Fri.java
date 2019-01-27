package com.example.rohan.f7.Fragments;

import android.content.Context;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fri extends Fragment {
    FirebaseDatabase firebaseDatabase;
    List<ClassDetail> classDetails;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fri, container, false);

        recyclerView=view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Fri");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classDetails=new ArrayList<ClassDetail>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    ClassDetail value=dataSnapshot1.getValue(ClassDetail.class);
                   /* ClassDetail classDetail=new ClassDetail();
                    String type = value.getType();
                    String subject = value.getSubject();
                    String timing = value.getTiming();
                    String faculty = value.getFaculty();
                    String room = value.getRoom();
                    classDetail.setType(type);
                    classDetail.setSubject(subject);
                    classDetail.setTiming(timing);
                    classDetail.setFaculty(faculty);
                    classDetail.setRoom(room);*/
                   classDetails.add(value);
                }
                RecyclerAdapter recyclerAdapter= new RecyclerAdapter(classDetails, getContext());
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
