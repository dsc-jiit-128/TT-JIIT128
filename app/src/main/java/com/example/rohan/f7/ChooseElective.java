package com.example.rohan.f7;

import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rohan.f7.recyclerviewMultiselect.CarBinder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

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
        final List<Model> list = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        df = FirebaseDatabase.getInstance().getReference("3RD_YEAR");

        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int x = list.size();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    String[] s = new String[2];
                    s[0] = dataSnapshot1.child("0").getValue(String.class);
                    s[1] = dataSnapshot1.child("1").getValue(String.class);

                    list.add(new Model(s[0]+" - "+s[1]));
                }

                if (list.size()>x)
                {
//                    recyclerAdapterForSubject = new RecyclerAdapterForSubject(list, getApplicationContext());
//                    recyclerAdapterForSubject.notifyDataSetChanged();
//                    recyclerView.setAdapter(recyclerAdapterForSubject);
                    Log.d("TAG", "onDataChange: "+list);
                    MultiViewAdapter adapter = new MultiViewAdapter();
                    recyclerView.setAdapter(adapter);

                    // Register Binder
                    //adapter.registerBinders(new CarBinder());

                    // Create Section and add items
                    ListSection<Model> listSection = new ListSection<>();
                    listSection.addAll(list);

                    // Add Section to the adapter
                    adapter.addSection(listSection);

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

                ArrayList<String> stringArrayList2 = new TinyDB(getApplicationContext()).getSubjectNames("SUBJECTS");
                final ArrayList<String> choosedSubjects = new ArrayList<>();
                for (int i=0;i<stringArrayList2.size();i++)
                {
                    String s = stringArrayList2.get(i).substring(0, 10);

                    if (!choosedSubjects.contains(s))
                    {
                        choosedSubjects.add(s);
                        Log.d("TAG", "onClick: "+choosedSubjects);
                    }
                }

                for (int i=0;i<5;i++)
                {
                    df = FirebaseDatabase.getInstance().getReference("SEMESTER_5").child(""+i+"").child("slots");

                    final ArrayList<String> list2 = new ArrayList<>();

                    final int finalI = i;
                    df.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                            {
                                String s = dataSnapshot1.getValue(String.class);
                                assert s != null;
                                try {
                                    String st = s.trim().substring(s.indexOf("(")+1, s.indexOf(")"));
                                    //Log.d("TAG", "onDataChange: "+st.replaceAll("\\s", ""));
                                    st = st.replaceAll("\\s", "");
                                    if (st.equals("B12HS613"))
                                    {
                                        st = "19B12HS613";
                                    }
                                    if (choosedSubjects.contains(st))
                                    {
                                        Log.d("TAG", "onDataChange: "+s);

                                        list2.add(s);
                                    }
                                }catch (Exception e)
                                {
                                    //Log.d("TAG", "onDataChange: "+s);

                                    list2.add(s);

                                }
                            }

                            new TinyDB(getApplicationContext()).putSubjects(""+ finalI +"", list2);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                Toast.makeText(ChooseElective.this, ""+new TinyDB(getApplicationContext()).getSubjects("0"), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
