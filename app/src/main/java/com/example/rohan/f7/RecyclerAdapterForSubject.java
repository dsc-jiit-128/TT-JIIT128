package com.example.rohan.f7;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RecyclerAdapterForSubject extends SelectableAdapter<RecyclerAdapterForSubject.MyHolder> {

    List<Model> subjects;
    Context context;




    public RecyclerAdapterForSubject(List<Model> subjectName, Context context)
    {
        this.subjects=subjectName;
        this.context=context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subject_selection, parent, false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        final Model model = subjects.get(position);


        holder.subjectName.setText(model.getText());
        holder.view.setBackgroundColor(model.isSelected() ? Color.CYAN : Color.WHITE);
        holder.subjectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model.setSelected(!model.isSelected());

                holder.view.setBackgroundColor(model.isSelected() ? Color.CYAN : Color.WHITE);

                String x = holder.subjectName.getText().toString();
                TinyDB tinyDB = new TinyDB(context);
                ArrayList<String> subjects = tinyDB.getSubjectNames("SUBJECTS");

                if (model.isSelected())
                {
                    if (subjects!=null && !subjects.contains(x))
                    {
                        subjects.add(x);
                        tinyDB.putSubjects("SUBJECTS", subjects);

                    }
                    assert subjects != null;
                    Toast.makeText(context, "added "+ x +" in "+ subjects, Toast.LENGTH_LONG).show();
                }else{
                    subjects.remove(x);
                    tinyDB.putSubjects("SUBJECTS", subjects);
                    Toast.makeText(context, "removed "+ x +" from "+ subjects, Toast.LENGTH_LONG).show();

                }

            }
        });



    }

    @Override
    public int getItemCount() {

        return subjects.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView subjectName;
        View view;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);

            view= itemView;

            subjectName.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {



        }



    }





}
