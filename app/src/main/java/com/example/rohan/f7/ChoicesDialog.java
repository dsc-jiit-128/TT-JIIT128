package com.example.rohan.f7;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.ArrayList;

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
        setContentView(R.layout.choices_dialog);
        batch = findViewById(R.id.batch);

        for (int u =0; u<16; u++)
        {
            arrayList.add(s[u]);
        }
        findViewById(R.id.savebatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.contains(batch.getText().toString().trim().toUpperCase()))
                {
                    new TinyDB(getContext()).putString("BATCH", batch.getText().toString().trim().toUpperCase());
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "PLEASE ENTER BATCH NAME CORRECTLY.", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    @Override
    public void onClick(View v) {

    }
}
