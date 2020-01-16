package com.example.rohan.f7.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rohan.f7.R;
import com.example.rohan.f7.RecyclerAdapter;
import com.example.rohan.f7.SubjectDetails;
import com.example.rohan.f7.TinyDB;

import java.util.ArrayList;


public class Mon extends Fragment {

    RecyclerView recyclerView;
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

                        try {
                            s = s.substring(s.indexOf("-") + 1);

                            room = s.substring(0, s.indexOf("/"));

                            s = s.substring(s.indexOf("/") + 1);

                            faculty = s;
                        }catch (Exception e){
                            room = "-";
                            faculty = "-";
                        }

                    } catch (Exception e) {
                        subject = "-";
                        room = "-";
                        faculty = "-";

                    }
                    if (type.contains(tinyDB.getString("BATCH")) || type.contains("ALL"))
                    {
                        if (tinyDB.getSubjectNames("SUBJECTCODES").contains(subject) || subject.equals("-") || subject.equals("T&P") || subject.contains("NSS"))
                        {

                            String sub;
                            try{
                                sub =tinyDB.getSubjectNames("SUBJECTS").get(tinyDB.getSubjectNames("SUBJECTCODES").indexOf(subject));
                                sub = sub.substring(sub.indexOf("-")+1);
                            }catch (Exception e){
                                sub = subject;
                            }

                            classDetailArrayList.add(new SubjectDetails(type, sub, timeslot, faculty, room, "", ""));
                        }
                    }


                }
            } catch (Exception e) {
            }
            recyclerAdapter = new RecyclerAdapter(classDetailArrayList,getContext());
            recyclerAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(recyclerAdapter);
            try {
                view.findViewById(R.id.noClassMsg).setVisibility(View.GONE);
            }catch (Exception e){

            }
        }else{

            try {
                view.findViewById(R.id.noClassMsg).setVisibility(View.VISIBLE);
            }catch (Exception e){

            }
        }


        return view;
    }



}
