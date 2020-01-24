package com.example.rohan.f7;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseSubjects extends AppCompatActivity {

    DatabaseReference df;
    RecyclerView recyclerView;
    RecyclerAdapterForSubject recyclerAdapterForSubject;
    Button saveSubjects;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_elective);
        saveSubjects = findViewById(R.id.save);

        tinyDB = new TinyDB(this);
        tinyDB.putSubjects("SUBJECTS2", null);

        if (tinyDB.getString("BATCH").equals("")) {
            startActivity(new Intent(this, BatchAndYearActivity.class));
            finish();
        }
        recyclerView = findViewById(R.id.subjects_to_choose);
        final List<Model> list = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        df = FirebaseDatabase.getInstance().getReference("TIMETABLE").child(tinyDB.getString("YEAR")).child("SUBJECTS");

        df.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int x = list.size();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String[] s = new String[2];
                    s[0] = dataSnapshot1.getKey();
                    s[1] = dataSnapshot1.getValue(String.class);

                    list.add(new Model(s[0] + " - " + s[1]));
                }

                if (list.size() > x) {
                    recyclerAdapterForSubject = new RecyclerAdapterForSubject(list, getApplicationContext());
                    recyclerAdapterForSubject.notifyDataSetChanged();
                    recyclerView.setAdapter(recyclerAdapterForSubject);
                    Log.d("TAG", "onDataChange: " + list);

                    findViewById(R.id.progress_circular).setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        //new TinyDB(this).clear();
        saveSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.progress_circular).setVisibility(View.VISIBLE);

                try {
                    ArrayList<String> stringArrayList2 = tinyDB.getSubjectNames("SUBJECTS2");
                    final ArrayList<String> choosedSubjects = new ArrayList<>();
                    for (int i = 0; i < stringArrayList2.size(); i++) {
                        String s = stringArrayList2.get(i).substring(0, 10);

                        if (!choosedSubjects.contains(s)) {
                            choosedSubjects.add(s);
                            Log.d("TAG", "onClick: " + choosedSubjects);
                        }
                    }


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

                                                if (choosedSubjects.contains(s.getsubjectCode())) {
                                                    Log.d("TAG", "onDataChange: " + s);
                                                    finalSubjects.add(s);
                                                }
                                            }

                                        } catch (Exception e) {
                                            //Log.d("TAG", "onDataChange: "+s);
                                            //finalSubjects.add(s);

                                        }
                                    }

                                    tinyDB.putSubjectDetails("" + finalI + "", finalSubjects);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(ChooseSubjects.this, "" + e, Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(ChooseSubjects.this, ""+tinyDB.getSubjects("02"), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                }
                startActivity(new Intent(ChooseSubjects.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.changeBatch) {
            startActivity(new Intent(ChooseSubjects.this, BatchAndYearActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
