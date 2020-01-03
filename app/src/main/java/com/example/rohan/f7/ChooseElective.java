package com.example.rohan.f7;

import android.os.Bundle;
import android.util.Log;
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

public class ChooseElective extends AppCompatActivity {

    DatabaseReference df;
    RecyclerView recyclerView;
    RecyclerAdapterForSubject recyclerAdapterForSubject;
    Button saveSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_elective);
        saveSubjects = findViewById(R.id.save);
        recyclerView = findViewById(R.id.subjects_to_choose);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        if (new TinyDB(this).getString("BATCH")=="")
        {
            ChoicesDialog choicesDialog = new ChoicesDialog(this);
            choicesDialog.show();
        }
        df = FirebaseDatabase.getInstance().getReference("3RD_YEAR");
        final ArrayList<String[]> list = new ArrayList<>();
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int x = list.size();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    String[] s = new String[2];
                    s[0] = dataSnapshot1.child("0").getValue(String.class);
                    s[1] = dataSnapshot1.child("1").getValue(String.class);

                    list.add(s);

                }
                Log.d("TAG", "onDataChange: "+list);
                if (list.size()>x)
                {
                    recyclerAdapterForSubject = new RecyclerAdapterForSubject(list, getApplicationContext());
                    recyclerAdapterForSubject.notifyDataSetChanged();
                    recyclerView.setAdapter(recyclerAdapterForSubject);
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
                Toast.makeText(ChooseElective.this, ""+new TinyDB(getApplicationContext()).getSubjectNames("SUBJECTS"), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
