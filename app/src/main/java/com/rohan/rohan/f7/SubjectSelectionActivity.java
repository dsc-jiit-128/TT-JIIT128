package com.rohan.rohan.f7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SubjectSelectionActivity extends AppCompatActivity {

    int TOTAL_BUTTONS = 34;
    Switch[] subjects = new Switch[TOTAL_BUTTONS];
    Button saveSubjects;
    DatabaseReference df;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selection);
        setFindViewById();
        tinyDB = new TinyDB(this);
        df = FirebaseDatabase.getInstance().getReference("3RD_YEAR");

        if (tinyDB.getString("BATCH").equals("")) {
            ChoicesDialog choicesDialog = new ChoicesDialog(this);
            choicesDialog.show();
        }
        final List<String> subjectCode = new ArrayList<>();


        df.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> list = new ArrayList<>();
                subjectCode.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String[] s = new String[2];
                    s[0] = dataSnapshot1.child("0").getValue(String.class);
                    s[1] = dataSnapshot1.child("1").getValue(String.class);

                    list.add(s[0] + " - " + s[1]);
                    subjectCode.add(s[0]);
                }

                if (list.size() == TOTAL_BUTTONS) {
                    for (int i = 0; i < TOTAL_BUTTONS; i++) {
                        subjects[i].setText(list.get(i));
                    }
                    findViewById(R.id.progress_circular).setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        saveSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    findViewById(R.id.progress_circular).setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
                final ArrayList<String> subjectCodeChosen = new ArrayList<>();

                ArrayList<String> subjectsChosen = new ArrayList<>();
                for (int i = 0; i < TOTAL_BUTTONS; i++) {
                    if (subjects[i].isChecked()) {
                        subjectsChosen.add(String.valueOf(subjects[i].getText()));
                        subjectCodeChosen.add(subjectCode.get(i));

                        //Toast.makeText(SubjectSelectionActivity.this, ""+subjects[i].getText(), Toast.LENGTH_SHORT).show();
                    }
                }
                tinyDB.putSubjectNames("SUBJECTCODES", subjectCodeChosen);
                tinyDB.putSubjectNames("SUBJECTS", subjectsChosen);

                for (int i = 0; i < 5; i++) {
                    final ArrayList<SubjectDetails> finalSubjects = new ArrayList<>();

                    df = FirebaseDatabase.getInstance().getReference("TIMETABLE").child(tinyDB.getString("YEAR")).child("TT").child("" + i + "");


                    try{
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

                                            if (subjectCodeChosen.contains(s.getsubjectCode())) {
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
                                startActivity(new Intent(SubjectSelectionActivity.this, MainActivity.class));
                                finish();
                                //Toast.makeText(SubjectSelectionActivity.this, ""+finalSubjects, Toast.LENGTH_SHORT).show();
                                //recreate();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }catch (Exception e){
                        Toast.makeText(SubjectSelectionActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }

                }
                //Toast.makeText(SubjectSelectionActivity.this, ""+tinyDB.getSubjects("0"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFindViewById() {

        saveSubjects = findViewById(R.id.saveSubjects);
        subjects[0] = findViewById(R.id.subjectName0);
        subjects[1] = findViewById(R.id.subjectName1);
        subjects[2] = findViewById(R.id.subjectName2);
        subjects[3] = findViewById(R.id.subjectName3);
        subjects[4] = findViewById(R.id.subjectName4);
        subjects[5] = findViewById(R.id.subjectName5);
        subjects[6] = findViewById(R.id.subjectName6);
        subjects[7] = findViewById(R.id.subjectName7);
        subjects[8] = findViewById(R.id.subjectName8);
        subjects[9] = findViewById(R.id.subjectName9);
        subjects[10] = findViewById(R.id.subjectName10);
        subjects[11] = findViewById(R.id.subjectName11);
        subjects[12] = findViewById(R.id.subjectName12);
        subjects[13] = findViewById(R.id.subjectName13);
        subjects[14] = findViewById(R.id.subjectName14);
        subjects[15] = findViewById(R.id.subjectName15);
        subjects[16] = findViewById(R.id.subjectName16);
        subjects[17] = findViewById(R.id.subjectName17);
        subjects[18] = findViewById(R.id.subjectName18);
        subjects[19] = findViewById(R.id.subjectName19);
        subjects[20] = findViewById(R.id.subjectName20);
        subjects[21] = findViewById(R.id.subjectName21);
        subjects[22] = findViewById(R.id.subjectName22);
        subjects[23] = findViewById(R.id.subjectName23);
        subjects[24] = findViewById(R.id.subjectName24);
        subjects[25] = findViewById(R.id.subjectName25);
        subjects[26] = findViewById(R.id.subjectName26);
        subjects[27] = findViewById(R.id.subjectName27);
        subjects[28] = findViewById(R.id.subjectName28);
        subjects[29] = findViewById(R.id.subjectName29);
        subjects[30] = findViewById(R.id.subjectName30);
        subjects[31] = findViewById(R.id.subjectName31);
        subjects[32] = findViewById(R.id.subjectName32);
        subjects[33] = findViewById(R.id.subjectName33);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.changeBatch)
        {
            ChoicesDialog choicesDialog = new ChoicesDialog(this);
            choicesDialog.show();
        }
        return super.onOptionsItemSelected(item);


    }
}