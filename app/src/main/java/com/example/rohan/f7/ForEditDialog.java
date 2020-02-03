package com.example.rohan.f7;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class ForEditDialog extends Dialog implements DialogInterface.OnClickListener {

    EditText subjectType, subjectName, subjectVenue, subjectFaculty, subjectTiming;
    ArrayList<SubjectDetails> subjectDetails;
    int position;
    int dayNumber;
    Button saveDetails;
    TinyDB tinyDB;
    AdRequest adRequest;

    public ForEditDialog(@NonNull Context context, ArrayList<SubjectDetails> subjectDetails, int position, int DayNumber) {
        super(context);
        this.subjectDetails = subjectDetails;
        this.position = position;
        this.dayNumber = DayNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.for_edit_dialog);
        tinyDB = new TinyDB(getContext());

        //setAd();



        subjectType = findViewById(R.id.type);
        subjectName = findViewById(R.id.subject);
        subjectVenue = findViewById(R.id.room);
        subjectFaculty = findViewById(R.id.faculty);
        subjectTiming = findViewById(R.id.timing);

        SubjectDetails subjectDetails1 = new SubjectDetails();
        subjectDetails1 = subjectDetails.get(position);

        subjectType.setText(subjectDetails1.getSubjectType());
        subjectName.setText(subjectDetails1.getSubjectName());
        subjectVenue.setText(subjectDetails1.getSubjectVenue());
        subjectTiming.setText(subjectDetails1.getSubjectTiming());
        subjectFaculty.setText(subjectDetails1.getSubjectFaculty());

        saveDetails = findViewById(R.id.saveDetails);
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SubjectDetails subjectDetails2 = new SubjectDetails(subjectType.getText().toString(),
                        subjectName.getText().toString(),
                        subjectTiming.getText().toString(),
                        subjectFaculty.getText().toString(),
                        subjectVenue.getText().toString(),
                        "", "");
                subjectDetails.get(position).setSubjectFaculty(subjectDetails2.getSubjectFaculty());
                subjectDetails.get(position).setSubjectName(subjectDetails2.getSubjectName());
                subjectDetails.get(position).setSubjectTiming(subjectDetails2.getSubjectTiming());
                subjectDetails.get(position).setSubjectType(subjectDetails2.getSubjectType());
                subjectDetails.get(position).setSubjectVenue(subjectDetails2.getSubjectVenue());

                tinyDB.putSubjectDetails(""+dayNumber, subjectDetails);
                dismiss();

            }
        });

    }

//    private void setAd() {
//        adRequest = new AdRequest.Builder().build();
//        final InterstitialAd interstitialAd = new InterstitialAd(getContext());
//        interstitialAd.setAdUnitId(String.valueOf(R.string.interestitialAd));
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
//        AdView adView = findViewById(R.id.adview);
//        MobileAds.initialize(getContext(), String.valueOf(R.string.bannerAd));
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-7233191134291345/1400289307");
//        adView.loadAd(adRequest);
//    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
