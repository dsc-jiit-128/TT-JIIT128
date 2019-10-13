package com.example.rohan.f7;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChoicesDialog extends Dialog implements View.OnClickListener {

    public ChoicesDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choices_dialog);
        final RadioGroup batch = findViewById(R.id.batch);
        final String[] batchName = new String[1];
        batch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    batchName[0] = checkedRadioButton.getText().toString();
                }
            }
        });
        findViewById(R.id.savebatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (batchName[0].equals("F7") || batchName[0].equals("F8"))
                {
                    new TinyDB(getContext()).putString("BATCH", batchName[0]);
                    dismiss();
                }else{
                    Toast.makeText(getOwnerActivity(), "Choose a batch first !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    public void onClick(View v) {

    }
}
