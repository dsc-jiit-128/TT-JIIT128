package com.example.rohan.f7;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.rohan.f7.Fragments.Fri;
import com.example.rohan.f7.Fragments.Mon;
import com.example.rohan.f7.Fragments.Sat;
import com.example.rohan.f7.Fragments.Thu;
import com.example.rohan.f7.Fragments.Tue;
import com.example.rohan.f7.Fragments.Wed;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    DatabaseReference df;

    AdView adView;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    TinyDB tinyDB;
    private InterstitialAd interstitialAd;
    private AdRequest adRequest;
    private RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tinyDB = new TinyDB(this);
        try{
            if (tinyDB.getString("BATCH").equals("")) {
                startActivity(new Intent(this, ChooseSubjects.class));
                finish();
            }
        }catch (Exception e){
            startActivity(new Intent(this, ChooseSubjects.class));
            finish();
        }



        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                // do transformation here
                page.setRotationY(position * -30);

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1 || dayofweek == 2) {
            mViewPager.setCurrentItem(0, true);
        } else {
            mViewPager.setCurrentItem(dayofweek - 2, true);
        }

        adView = findViewById(R.id.bannerAd);
        MobileAds.initialize(this, String.valueOf(R.string.bannerAd));
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.loadAd(String.valueOf(R.string.videoAd), adRequest);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse("https://webkiosk.jiit.ac.in/"));
        }
        if (id == R.id.changeElective) {

            startActivity(new Intent(MainActivity.this, ChooseSubjects.class));
            finish();

//            if (rewardedVideoAd.isLoaded()) {
//                if (!adOpened)
//                {
//                    rewardedVideoAd.show();
//                    adOpened=true;
//                }else{
//                    startActivity(new Intent(MainActivity.this, ChooseSubjects.class));
//                    finish();
//                }
//            }else{
//                startActivity(new Intent(MainActivity.this, ChooseSubjects.class));
//                finish();
//            }


        }
        if (id == R.id.refresh) {
            for (int i = 0; i < 5; i++) {
                final ArrayList<SubjectDetails> finalSubjects = new ArrayList<>();

                df = FirebaseDatabase.getInstance().getReference("TIMETABLE").child(tinyDB.getString("YEAR")).child("TT").child("" + i + "");


                try {
                    final int finalI = i;
                    df.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                SubjectDetails s = dataSnapshot1.getValue(SubjectDetails.class);
                                assert s != null;
                                try {

                                    String batch = tinyDB.getString("BATCH");
                                    //Log.d("TAG", "onDataChange: "+s);
                                    if (s.getBatchName().equals("ALL") || s.getBatchName().contains(batch)) {

                                        if (new TinyDB(getApplicationContext()).getSubjectNames("SUBJECTCODES").contains(s.getsubjectCode())) {
                                            Log.d("TAG", "onDataChange: " + s);
                                            finalSubjects.add(s);
                                        }
                                    }

                                } catch (Exception e) {
                                    //Log.d("TAG", "onDataChange: "+s);
                                    //finalSubjects.add(s);

                                }
                            }
                            recreate();
                            //Toast.makeText(SubjectSelectionActivity.this, ""+finalSubjects, Toast.LENGTH_SHORT).show();
                            //recreate();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }

            }
        }
        if (id == R.id.feedback) {
            startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
            //finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            interstitialAd = new InterstitialAd(getApplicationContext());
            interstitialAd.setAdUnitId("ca-app-pub-7233191134291345/7587736789");
            interstitialAd.loadAd(adRequest);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    }
                }
            }, 3000);
            if (position == 0) {
                Mon t1 = new Mon();
                return t1;
            } else if (position == 1) {
                Tue t2 = new Tue();
                return t2;
            } else if (position == 2) {
                Wed t3 = new Wed();
                return t3;
            } else if (position == 3) {
                Thu t4 = new Thu();
                return t4;
            } else if (position == 4) {
                Fri t5 = new Fri();
                return t5;
            } else if (position == 5) {
                Sat t6 = new Sat();
                return t6;
            }

            return null;
        }

        @Override
        public int getCount() {

            return 6;
        }
    }

}
