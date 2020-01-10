package com.example.rohan.f7.recyclerviewMultiselect;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rohan.f7.Model;
import com.example.rohan.f7.R;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class CarBinder extends ItemBinder<Model, CarBinder.CarViewHolder> {

@Override public CarViewHolder createViewHolder(ViewGroup parent) {
        return new CarViewHolder(inflate(parent, R.layout.subject_selection));
        }

    @Override
    public void bindViewHolder(CarViewHolder holder, Model item) {

    }

    @Override public boolean canBindData(Object item) {
        return item instanceof Model;
        }



static class CarViewHolder extends ItemViewHolder<Model> {

    TextView tvCarName;

    public CarViewHolder(View itemView) {
        super(itemView);
        tvCarName = itemView.findViewById(R.id.subjectName);
    }
}

}

