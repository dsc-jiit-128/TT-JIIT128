package com.example.rohan.f7.recyclerviewMultiselect;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReAdMulSel extends RecyclerView.Adapter<ReAdMulSel.MyHolder> {
    List<String> subjects;
    Context context;

    public ReAdMulSel(List<String> subjectName, Context context)
    {
        this.subjects=subjectName;
        this.context=context;
    }

    @NonNull
    @Override
    public ReAdMulSel.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReAdMulSel.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
