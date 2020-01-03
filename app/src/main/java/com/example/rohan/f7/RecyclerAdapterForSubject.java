package com.example.rohan.f7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterForSubject extends RecyclerView.Adapter<RecyclerAdapterForSubject.MyHolder> {

    ArrayList<String[]> subjects;
    Context context;
    public RecyclerAdapterForSubject(ArrayList<String[]> subjectName, Context context)
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
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String[] subj;

        subj = subjects.get(position);

        String s = subj[0]+" - "+subj[1];

        holder.subjectName.setText(s);


    }

    @Override
    public int getItemCount() {
        int arr=0;
        try {
            if(subjects.size()==0)
            {
                arr=0;
            }else
            {
                arr=subjects.size();
            }
        }catch (Exception ignored){

        }
        return arr;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView subjectName;
        int x = 0;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            subjectName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> s = new TinyDB(context).getSubjectNames("SUBJECTS");

                    if (x==0)
                    {
                        subjectName.setBackground(context.getDrawable(R.drawable.btn_bg_red));
                        x++;
                        if (s == null)
                        {
                            s = new ArrayList<>();
                            s.add(subjectName.getText().toString());

                        }else{
                            if (!s.contains(subjectName.getText().toString()))
                            {
                                s.add(subjectName.getText().toString());
                            }
                        }


                    }else{
                        subjectName.setBackground(context.getDrawable(R.drawable.btn_bg_yellow));
                        x=0;
                        if (s.contains(subjectName.getText().toString()))
                        {
                            s.remove(subjectName.getText().toString());
                        }
                    }

                    new TinyDB(context).putSubjectNames("SUBJECTS", s);


                }
            });
        }
    }
}
