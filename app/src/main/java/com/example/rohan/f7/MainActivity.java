package com.example.rohan.f7;

import android.support.annotation.NonNull;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.transform.sax.SAXTransformerFactory;

public class MainActivity extends AppCompatActivity {

  /*  String[] type={
            "[LEC]"
    };
    String[] subject={
           "Environmental Science"
    };
    String[] faculty={


            "MANISHA"

    };
    String[] timing={


            "10:50-11:40"



          };
    String[] room={

            "254"

    };*/

    FirebaseDatabase firebaseDatabase;
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
      /* databaseReference = FirebaseDatabase.getInstance().getReference();
      //  List<ClassDetail> allDetails = new ArrayList<ClassDetail>();
        for(int i=0;i<1;i++)
        {
            ClassDetail classDetail = new ClassDetail();
            classDetail.setType(type[i]);
            classDetail.setSubject(subject[i]);
            classDetail.setFaculty(faculty[i]);
            classDetail.setTiming(timing[i]);
            classDetail.setRoom(room[i]);
           // allDetails.add(classDetail);

            databaseReference.child("Sat").child(String.valueOf(i+1)).setValue(classDetail);

        }

       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })*/






    }

    public void writeToDatabase(String type, String subject, String timing, String faculty, String room){

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
            return true;
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
