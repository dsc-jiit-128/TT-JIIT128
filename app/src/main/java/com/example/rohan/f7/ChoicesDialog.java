package com.example.rohan.f7;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
        RadioGroup elective1 = findViewById(R.id.elective1);
        RadioGroup elective2 = findViewById(R.id.elective2);
        RadioGroup elective3 = findViewById(R.id.elective3);
        RadioGroup elective4 = findViewById(R.id.elective4);
        Button saveElective = findViewById(R.id.saveElectives);
        final Choices choices = new Choices();


        elective1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
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
                    choices.setElective1(String.valueOf(checkedRadioButton.getText()));
                }
            }
        });
        elective2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
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
                    choices.setElective2(String.valueOf(checkedRadioButton.getText()));
                }
            }
        });
        elective3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
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
                    choices.setElective3(String.valueOf(checkedRadioButton.getText()));
                }
            }
        });
        elective4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
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
                    choices.setElective4(String.valueOf(checkedRadioButton.getText()));
                }
            }
        });
        saveElective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choices.getElective1()!=null
                && choices.getElective2()!=null
                && choices.getElective3()!=null
                && choices.getElective4()!=null)
                {
                    new TinyDB(getContext()).setChoices("ELECTIVES", choices);
                    dismiss();

                }else
                {
                    Toast.makeText(getContext(), "Fill all required choices. ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    @Override
    public void onClick(View v) {

    }
}
