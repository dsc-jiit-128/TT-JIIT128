package com.example.rohan.f7.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rohan.f7.Choices;
import com.example.rohan.f7.R;
import com.example.rohan.f7.RecyclerAdapter;
import com.example.rohan.f7.SubjectDetails;
import com.example.rohan.f7.TinyDB;

import java.util.ArrayList;


public class Sat extends Fragment {
    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sat, container, false);

        recyclerView=view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        TinyDB tinyDB = new TinyDB(getActivity());
        try{
            if (tinyDB.getSubjectDetails("SEMESTER_5").get(5)!=null){

                ArrayList<SubjectDetails> subjectDetails = new ArrayList<>();
                if (tinyDB.getChoices("ELECTIVES")!=null)
                {
                    for (int i=0;i<tinyDB.getSubjectDetails("SEMESTER_5").get(5).size();i++){
                        if (tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i).getBatchName().contains(tinyDB.getString("BATCH")))
                        {
                            if (tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i).getSubjectValue().equals("CORE"))
                            {
                                subjectDetails.add(tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i));
                            }else{
                                Choices choices = new Choices();
                                choices = tinyDB.getChoices("ELECTIVES");
                                if (tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i).getSubjectName().contains(choices.getElective1())
                                        || tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i).getSubjectName().contains(choices.getElective2())
                                        || tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i).getSubjectName().contains(choices.getElective3())
                                        || tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i).getSubjectName().contains(choices.getElective4()))
                                {
                                    subjectDetails.add(tinyDB.getSubjectDetails("SEMESTER_5").get(5).get(i));
                                }
                            }
                        }


                    }
                    if (subjectDetails==null){
                        view.findViewById(R.id.noClassMsg).setVisibility(View.VISIBLE);
                    }

                    recyclerAdapter=new RecyclerAdapter(subjectDetails, getContext());
                    recyclerAdapter.notifyDataSetChanged();

                }else{
                    recyclerAdapter=new RecyclerAdapter(tinyDB.getSubjectDetails("SEMESTER_5").get(5), getContext());
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

}
