package com.example.rohan.f7;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohan.f7.Fragments.Fri;
import com.example.rohan.f7.Fragments.Mon;
import com.example.rohan.f7.Fragments.Sat;
import com.example.rohan.f7.Fragments.Thu;
import com.example.rohan.f7.Fragments.Tue;
import com.example.rohan.f7.Fragments.Wed;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    ArrayList<ArrayList<SubjectDetails>> subjectDetailsArrayList= new ArrayList<>();
    DatabaseReference databaseReference;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    ArrayList<String> subjectCodes= new ArrayList<>();

    private ViewPager mViewPager;
    private int i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK);
        if(dayofweek==1 || dayofweek==2)
        {
            mViewPager.setCurrentItem(0,true);
        }
        else{
            mViewPager.setCurrentItem(dayofweek-2,true);
        }
        //setTimeTableToDataBase();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        final TinyDB tinyDB= new TinyDB(this);
        databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    GenericTypeIndicator<ArrayList<ArrayList<SubjectDetails>>> t = new GenericTypeIndicator<ArrayList<ArrayList<SubjectDetails>>>() {
                    };

                    if (tinyDB.getSubjectDetails("3RD_YEAR")==null)
                    {
                        tinyDB.putSubjectDetails("3RD_YEAR",dataSnapshot.child("3RD_YEAR").getValue(t));

                    }else if ((tinyDB.getSubjectDetails("3RD_YEAR")!=dataSnapshot.child("3RD_YEAR").getValue(t))){
                        tinyDB.putSubjectDetails("3RD_YEAR",dataSnapshot.child("3RD_YEAR").getValue(t));

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }

            });







        if (tinyDB.getChoices("ELECTIVES")==null){
            Toast.makeText(this, "CHOOSE ELECTIVES", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ChooseElective.class));
            finish();

        }


    }

    private String setSubjectName(String subjectName) {

        for(int j = 0;j<subjectCodes.size();j++)
        {
            subjectCodes.get(j).replace("[", "");
            subjectCodes.get(j).replace("]", "");
            if (subjectCodes.get(j).contains(subjectName)){
                Log.d("tag", "setSubjectName: "+subjectCodes.get(j).substring(subjectCodes.get(i).indexOf(",")-1));
                return subjectCodes.get(j).substring(subjectCodes.get(i).indexOf(",")-1);
            }

        }
       return subjectName;
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
        if (id==R.id.changeElective){
           startActivity(new Intent(MainActivity.this, ChooseElective.class));
           finish();
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
            if(position==0)
            {
                Mon t1= new Mon();
                return t1;
            }
            else if (position==1)
            {
                Tue t2= new Tue();
                return t2;
            }
            else if (position==2)
            {
                Wed t3= new Wed();
                return t3;
            }
            else if (position==3)
            {
                Thu t4= new Thu();
                return t4;
            }
            else if (position==4)
            {
                Fri t5= new Fri();
                return t5;
            }
            else if (position==5)
            {
                Sat t6= new Sat();
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
