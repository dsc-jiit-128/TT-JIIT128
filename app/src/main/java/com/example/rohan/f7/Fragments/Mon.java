package com.example.rohan.f7.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class Mon extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mon, container, false);



        recyclerView=view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TinyDB tinyDB = new TinyDB(getActivity());





        try{
            if (tinyDB.getSubjectDetailsOfADay("Mon")!=null){

                ArrayList<SubjectDetails> subjectDetails = new ArrayList<>();
                if (tinyDB.getChoices("ELECTIVES")!=null)
                {
                    for (int i=0;i<tinyDB.getSubjectDetailsOfADay("Mon").size();i++){
                        if (tinyDB.getSubjectDetailsOfADay("Mon").get(i).getBatchName().contains(tinyDB.getString("BATCH")))
                        {
                            if (tinyDB.getSubjectDetailsOfADay("Mon").get(i).getSubjectValue().equals("CORE"))
                            {
                                subjectDetails.add(tinyDB.getSubjectDetailsOfADay("Mon").get(i));
                            }else{
                                Choices choices = new Choices();
                                choices = tinyDB.getChoices("ELECTIVES");
                                if (tinyDB.getSubjectDetails("SEMESTER_5").get(0).get(i).getSubjectName().contains(choices.getElective1())
                                        || tinyDB.getSubjectDetails("SEMESTER_5").get(0).get(i).getSubjectName().contains(choices.getElective2())
                                        || tinyDB.getSubjectDetails("SEMESTER_5").get(0).get(i).getSubjectName().contains(choices.getElective3())
                                        || tinyDB.getSubjectDetails("SEMESTER_5").get(0).get(i).getSubjectName().contains(choices.getElective4()))
                                {
                                    subjectDetails.add(tinyDB.getSubjectDetails("SEMESTER_5").get(0).get(i));
                                }
                            }
                        }

                    }
                    if (subjectDetails.size()==0){
                        view.findViewById(R.id.noClassMsg).setVisibility(View.VISIBLE);
                    }

                    recyclerAdapter=new RecyclerAdapter(subjectDetails, getContext());
                    recyclerAdapter.notifyDataSetChanged();
                }else{
                    recyclerAdapter=new RecyclerAdapter(tinyDB.getSubjectDetails("SEMESTER_5").get(0), getContext());
                    recyclerAdapter.notifyDataSetChanged();
                }


                recyclerView.setAdapter(recyclerAdapter);
            }else{
                view.findViewById(R.id.noClassMsg).setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            view.findViewById(R.id.noClassMsg).setVisibility(View.VISIBLE);
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
