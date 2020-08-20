package com.rohan.rohan.f7.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rohan.rohan.f7.R;
import com.rohan.rohan.f7.RecyclerAdapter;
import com.rohan.rohan.f7.SubjectDetails;
import com.rohan.rohan.f7.TinyDB;

import java.util.ArrayList;


public class Day extends Fragment {

    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    String dayNameString;
    TextView dayName;

    int dayNumber;
    public Day(int dayNumber, String dayName)
    {
        this.dayNumber=dayNumber;
        this.dayNameString=dayName;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        dayName=view.findViewById(R.id.dayName);
        dayName.setText(dayNameString);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        TinyDB tinyDB = new TinyDB(getActivity());


        try{
            ArrayList<SubjectDetails> subjects = tinyDB.getSubjectDetails(""+dayNumber);
            if (subjects != null) {
                try {
                    recyclerAdapter = new RecyclerAdapter(subjects, getActivity(), dayNumber);
                    recyclerAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(recyclerAdapter);
                } catch (Exception e) {
                    Log.e("TAG", "onCreateView: +e"+e );

                }

                try {
                    if (subjects.size()==0)
                    {
                        view.findViewById(R.id.noClassMsg).setVisibility(View.VISIBLE);
                    }else{
                        view.findViewById(R.id.noClassMsg).setVisibility(View.GONE);

                    }
                }catch (Exception e){
                    Log.e("TAG", "onCreateView: +e"+e );

                }
            }else{

                try {
                    view.findViewById(R.id.noClassMsg).setVisibility(View.VISIBLE);
                }catch (Exception e){
                    Log.e("TAG", "onCreateView: +e"+e );

                }
            }
        }catch (Exception e){
            Log.e("TAG", "onCreateView: +e"+e );
            //startActivity(new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), BatchAndYearActivity.class));
            //getActivity().finish();


        }



        return view;
    }



}
