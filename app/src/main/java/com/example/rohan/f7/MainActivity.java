package com.example.rohan.f7;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
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
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.transform.sax.SAXTransformerFactory;

public class MainActivity extends AppCompatActivity {

    String[] type={
            "LEC",
            "TUTE",
            "TUTE",
            "TUTE",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LAB"
    };
    String[] subject={
           "COMPUTER NETWORKS",
            "Technology & Culture",
            "Technology & Culture",
            "PRINCIPLES OF MANAGEMENT",
            "Materials Science",
            "Laser Technology and Applications",
            "COMPUTER BASED NUMERICAL TECHNIQUES",
            "STATISTICAL INFORMATION THEORY WITH APPLICATIONS",
            "DECISION MAKING USING MATHEMATICAL AND STATISTICAL APPROACH",
            "STATISTICAL INFORMATION THEORY WITH APPLICATIONS",
            "LOGICAL REASONING AND INEQUALITIES",
            "Quantum Mechanics for Engineers",
            "Technology & Culture",
            "Technology & Culture",
            "Technology & Culture",
            "Strategic Human Resource Management",
            "PRINCIPLES OF MANAGEMENT",
            "SOFTWARE ENGINEERING LAB"

    };
    String[] value={
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE"

    };
    String[] faculty={
            "RUPESH",
            "RG",
            "AA",
            "DV",
            "SD",
            "AV",
            "PKS",
            "PINKEY",
            "NFMATHS1",
            "AMITA",
            "NEHA",
            "VM",
            "ALKA",
            "AA",
            "RG",
            "PRV",
            "DV",
            "SHRUTI/NFCS2"

    };
    String[] timing={
            "9:00-9:50",
            "9:55-10:45",
            "9:55-10:45",
            "9:55-10:45",
            "10:50-11:40",
            "10:50-11:40",
            "10:50-11:40",
            "10:50-11:40",
            "10:50-11:40",
            "10:50-11:40",
            "10:50-11:40",
            "10:50-11:40",
            "11:45-12:35",
            "11:45-12:35",
            "11:45-12:35",
            "11:45-12:35",
            "12:40-1:30",
            "1:35-3:20"
          };
    String[] venue={
            "254",
            "127",
            "126",
            "121",
            "118(A)",
            "118(B)",
            "244(A)",
            "244(B)",
            "111",
            "217",
            "226",
            "228",
            "111",
            "217",
            "226",
            "228",
            "111",
            "35"
    };

    String[] type2={
            "LAB",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "TUTE",
            "TUTE",
            "TUTE",
            "TUTE",
            "TUTE",
            "TUTE",
            "TUTE"
    };
    String[] subject2={
            "Open Source Software Lab",
            "COMPUTER NETWORKS",
            "Technology & Culture",
            "Technology & Culture",
            "Technology & Culture",
            "Strategic Human Resource Management",
            "SOFTWARE ENGINEERING",
            "ARTIFICIAL INTELLIGENCE",
            "MATERIALS SCIENCE",
            "Laser Technology and Applications",
            "COMPUTER BASED NUMERICAL TECHNIQUES",
            "Matrix Computations",
            "DECISION MAKING USING MATHEMATICAL AND STATISTICAL APPROACH",
            "STATISTICAL INFORMATION THEORY WITH APPLICATIONS",
            "LOGICAL REASONING AND INEQUALITIES",
            "Quantum Mechanics for Engineers",
            "Quantum Mechanics for Engineers",
            "Materials Science",
            "Laser Technology and Applications",
            "COMPUTER BASED NUMERICAL TECHNIQUES",
            "MA331",
            "DECISION MAKING USING MATHEMATICAL AND STATISTICAL APPROACH",
            "Strategic Human Resource Management"
    };
    String [] value2={
            "CORE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE"

    };
    String [] faculty2={
            "HM/RUPESH/CHETNA",
            "RUPESH",
            "ALKA",
            "AA",
            "RG",
            "PRV",
            "NFCS2",
            "OMPRAKASH",
            "SD",
            "AV",
            "PKS",
            "PINKEY",
            "NFMATHS1",
            "AMITA",
            "NEHA",
            "VM",
            "VM",
            "SD",
            "AV",
            "PKS",
            "PINKEY",
            "NFMATHS1",
            "PRV"
    };
    String [] timing2={
            "9:00-10:45",
            "10:50-11:40",
            "11:45-12:35",
            "11:45-12:35",
            "11:45-12:35",
            "11:45-12:35",
            "12:40-1:30",
            "12:40-1:30",
            "1:35-2:25",
            "1:35-2:25",
            "1:35-2:25",
            "1:35-2:25",
            "1:35-2:25",
            "1:35-2:25",
            "1:35-2:25",
            "1:35-2:25",
            "2:30-3:20",
            "2:30-3:20",
            "2:30-3:20",
            "2:30-3:20",
            "2:30-3:20",
            "2:30-3:20",
            "3:25-4:15"
    };
    String [] venue2={
            "35",
            "111",
            "111",
            "226",
            "226",
            "118",
            "226",
            "118",
            "217",
            "226",
            "228",
            "111",
            "118",
            "123",
            "137",
            "244",
            "244(A)",
            "244(B)",
            "118(A)",
            "228",
            "111",
            "113",
            "123"
    };

    String [] type3={
            "LAB",
            "TUTE",
            "TUTE",
            "LAB",
            "TUTE"
    };
    String [] subject3={
            "Information Security Lab",
            "STATISTICAL INFORMATION THEORY WITH APPLICATIONS",
            "LOGICAL REASONING AND INEQUALITIES",
            "COMPUTER NETWORKS LAB",
            "COMPUTER NETWORKS"
    };
    String [] value3={
            "CORE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE"

};

    String [] faculty3={
            "KM",
            "AMITA",
            "NEHA",
            "BDJ/SB/NEERAJ",
            "RUPESH"
    };
    String [] timing3={
            "9:00-10:45",
            "11:45-12:35",
            "11:45-12:35",
            "1:35-3:20",
            "3:25-4:15"
    };
    String [] venue3={
            "MML",
            "121",
            "226",
            "151",
            "229"
    };
    String [] type4={
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "LEC",
            "TUTE",
            "TUTE",
            "TUTE",
            "LAB"
    };
    String [] subject4={
            "COMPUTER NETWORKS",
            "Materials Science",
            "Laser Technology and Applications",
            "COMPUTER BASED NUMERICAL TECHNIQUES",
            "Matrix Computations",
            "DECISION MAKING USING MATHEMATICAL AND STATISTICAL APPROACH",
            "STATISTICAL INFORMATION THEORY WITH APPLICATIONS",
            "LOGICAL REASONING AND INEQUALITIES",
            "Quantum Mechanics for Engineers",
            "SOFTWARE ENGINEERING",
            "ARTIFICIAL INTELLIGENCE",
            "NHS521",
            "SOFTWARE ENGINEERING",
            "ARTIFICIAL INTELLIGENCE",
            "ARTIFICIAL INTELLIGENCE LAB"
    };
    String [] value4={
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE"

    };
    String [] faculty4={
            "RUPESH",
            "SD",
            "AV",
            "PKS",
            "PINKEY",
            "NFMATHS1",
            "AMITA",
            "NEHA",
            "VM",
            "NFCS2",
            "OMPRAKASH",
            "ALKA",
            "NFCS2",
            "NFCS1",
            "NEHA"
    };
    String [] timing4={
            "9:00-9:50",
            "9:55-10:45",
            "9:55-10:45",
            "9:55-10:45",
            "9:55-10:45",
            "9:55-10:45",
            "9:55-10:45",
            "9:55-10:45",
            "9:55-10:45",
            "10:50-11:40",
            "10:50-11:40",
            "12:40-1:30",
            "1:35-2:25",
            "2:30-3:20",
            "3:25-5:10"
    };
    String [] venue4={
            "226",
            "217",
            "226",
            "228",
            "111",
            "244(A)",
            "244(B)",
            "118(A)",
            "148",
            "228",
            "118(A)",
            "116",
            "126",
            "126",
            "151"
    };

    String [] type5={
            "LEC",
            "LEC",
            "LEC"
    };
    String [] subject5={
            "ARTIFICIAL INTELLIGENCE LAB",
            "SOFTWARE ENGINEERING",
            "PRINCIPLES OF MANAGEMENT"
    };
    String [] value5={
            "ELECTIVE",
            "ELECTIVE",
            "ELECTIVE"
    };

    String [] faculty5={
            "OMPRAKASH",
            "NFCS2",
            "DV"
    };
    String [] timing5={
            "9:00-9:50",
            "9:00-9:50",
            "1:35-2:25"
    };
    String [] venue5={
            "228",
            "118(B)",
            "217"
    };

    ArrayList<ArrayList<SubjectDetails>> subjectDetailsArrayList= new ArrayList<>();
    DatabaseReference databaseReference;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

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

                    if (tinyDB.getSubjectDetails("SEMESTER_5")==null)
                    {
                        tinyDB.putSubjectDetails("SEMESTER_5",dataSnapshot.child("SEMESTER_5").getValue(t));

                    }else if ((tinyDB.getSubjectDetails("SEMESTER_5")!=dataSnapshot.child("SEMESTER_5").getValue(t))){
                        tinyDB.putSubjectDetails("SEMESTER_5",dataSnapshot.child("SEMESTER_5").getValue(t));

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });


        if (tinyDB.getSubjectDetails("SEMESTER_5")!=null)
        {
            Toast.makeText(this, ""+tinyDB.getSubjectDetails("SEMESTER_5").get(0).get(0).getSubjectName(), Toast.LENGTH_SHORT).show();
        }

        if (tinyDB.getChoices("ELECTIVES")==null){
            Toast.makeText(this, "CHOOSE ELECTIVES", Toast.LENGTH_SHORT).show();

            ChoicesDialog choicesDialog= new ChoicesDialog(this);
            choicesDialog.show();

        }


    }

    private void setTimeTableToDataBase() {
        ArrayList<SubjectDetails> MONDAY = new ArrayList<>();
        for (int i=0;i<subject.length;i++)
        {
            SubjectDetails subjectDetails = new SubjectDetails(
                    type[i],
                    subject[i],
                    timing[i],
                    faculty[i],
                    venue[i],
                    value[i]);
            MONDAY.add(subjectDetails);
        }
        ArrayList<SubjectDetails> TUESDAY = new ArrayList<>();
        for (int i=0;i<subject2.length;i++)
        {
            SubjectDetails subjectDetails = new SubjectDetails(
                    type2[i],
                    subject2[i],
                    timing2[i],
                    faculty2[i],
                    venue2[i],
                    value2[i]);
            TUESDAY.add(subjectDetails);
        }
        ArrayList<SubjectDetails> WEDNESDAY = new ArrayList<>();
        for (int i=0;i<subject3.length;i++)
        {
            SubjectDetails subjectDetails = new SubjectDetails(
                    type3[i],
                    subject3[i],
                    timing3[i],
                    faculty3[i],
                    venue3[i],
                    value3[i]);
            WEDNESDAY.add(subjectDetails);
        }
        ArrayList<SubjectDetails> THURSDAY = new ArrayList<>();
        for (int i=0;i<subject4.length;i++)
        {
            SubjectDetails subjectDetails = new SubjectDetails(
                    type4[i],
                    subject4[i],
                    timing4[i],
                    faculty4[i],
                    venue4[i],
                    value4[i]);
            THURSDAY.add(subjectDetails);
        }
        ArrayList<SubjectDetails> FRIDAY = new ArrayList<>();
        for (int i=0;i<subject5.length;i++)
        {
            SubjectDetails subjectDetails = new SubjectDetails(
                    type5[i],
                    subject5[i],
                    timing5[i],
                    faculty5[i],
                    venue5[i],
                    value5[i]);
            FRIDAY.add(subjectDetails);
        }
        ArrayList<SubjectDetails> SATURDAY = new ArrayList<>();

        subjectDetailsArrayList.add(MONDAY);
        subjectDetailsArrayList.add(TUESDAY);
        subjectDetailsArrayList.add(WEDNESDAY);
        subjectDetailsArrayList.add(THURSDAY);
        subjectDetailsArrayList.add(FRIDAY);
        subjectDetailsArrayList.add(SATURDAY);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("SEMESTER_5").setValue(subjectDetailsArrayList);

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
            ChoicesDialog choicesDialog = new ChoicesDialog(this);
            choicesDialog.show();
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
