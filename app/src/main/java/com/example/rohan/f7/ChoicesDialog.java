package com.example.rohan.f7;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class ChoicesDialog extends Dialog implements View.OnClickListener {

    private EditText batch;

    String[] s = {"F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8"};
    ArrayList<String> arrayList = new ArrayList<>();

    public ChoicesDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final TinyDB tinyDB = new TinyDB(getContext());

        setContentView(R.layout.choices_dialog);
        final Spinner spinner = (Spinner) findViewById(R.id.selectyear);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tinyDB.putString("YEAR", adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        batch = findViewById(R.id.batch);

        batch.setText(tinyDB.getString("BATCH"));

        arrayList.addAll(Arrays.asList(s).subList(0, 16));
        findViewById(R.id.savebatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinyDB.getString("YEAR").equals("")) {
                    Toast.makeText(getContext(), "Select Year", Toast.LENGTH_SHORT).show();
                } else {
                    if (arrayList.contains(batch.getText().toString().trim().toUpperCase())) {
                        tinyDB.putString("BATCH", batch.getText().toString().trim().toUpperCase());
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "PLEASE ENTER BATCH NAME CORRECTLY.", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


    }


    @Override
    public void onClick(View v) {

    }
}
