package com.example.rohan.f7.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rohan.f7.Choices;
import com.example.rohan.f7.ClassDetail;
import com.example.rohan.f7.R;
import com.example.rohan.f7.RecyclerAdapter;
import com.example.rohan.f7.SQLite;
import com.example.rohan.f7.SubjectDetails;
import com.example.rohan.f7.TinyDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Mon extends Fragment {
    FirebaseDatabase firebaseDatabase;
    List<ClassDetail> classDetails;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<ClassDetail> offline = new ArrayList<>();
    SQLite sqLite;
    private RecyclerAdapter recyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mon, container, false);


        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        TinyDB tinyDB = new TinyDB(getActivity());


        ArrayList<String> subjects = tinyDB.getSubjects("0");
        ArrayList<SubjectDetails> classDetailArrayList = new ArrayList<>();
        if (subjects != null) {
            try {
                for (int i = 0; i < subjects.size(); i++) {
                    String type, timeslot, faculty, subject, room;
                    String s = subjects.get(i);
                    timeslot = s.substring(0, s.indexOf("-"));
                    s = s.substring(s.indexOf("+") + 1);
                    try {
                        type = s.substring(0, s.indexOf("("));

                    } catch (Exception e) {
                        type = "-";
                    }
                    try {
                        s = s.substring(s.indexOf("(") + 1);

                        subject = s.substring(0, s.indexOf(")"));

                        s = s.substring(s.indexOf("-") + 1);

                        room = s.substring(0, s.indexOf("/"));

                        s = s.substring(s.indexOf("/") + 1);

                        faculty = s;

                    } catch (Exception e) {
                        subject = "-";
                        room = "-";
                        faculty = "-";

                    }

                    classDetailArrayList.add(new SubjectDetails(type, subject, timeslot, faculty, room, "", ""));


                }
            } catch (Exception e) {
            }
            recyclerAdapter = new RecyclerAdapter(classDetailArrayList,getContext());
            recyclerAdapter.notifyDataSetChanged();
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
