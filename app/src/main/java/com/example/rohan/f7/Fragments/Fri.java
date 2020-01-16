package com.example.rohan.f7.Fragments;

import android.os.Bundle;
import android.os.Handler;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;


public class Fri extends Fragment {

    RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private InterstitialAd interstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fri, container, false);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//        AdRequest adRequest = new AdRequest.Builder().build();
//        interstitialAd = new InterstitialAd(getContext());
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        interstitialAd.loadAd(adRequest);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (interstitialAd.isLoaded())
//                {
//                    interstitialAd.show();
//                }
//            }
//        }, 3000);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TinyDB tinyDB = new TinyDB(getActivity());
        ArrayList<SubjectDetails> subjects = tinyDB.getSubjectDetails("4");
        if (subjects != null) {
            try {
                recyclerAdapter = new RecyclerAdapter(subjects,getContext());
                recyclerAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerAdapter);
            } catch (Exception e) {
            }

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