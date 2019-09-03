package com.example.rohan.f7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseElective extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_elective);
        if (new TinyDB(this).getString("BATCH")=="")
        {
            ChoicesDialog choicesDialog = new ChoicesDialog(this);
            choicesDialog.show();
        }
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
                    new TinyDB(getApplicationContext()).setChoices("ELECTIVES", choices);

                    startActivity(new Intent(ChooseElective.this, MainActivity.class));
                    finish();

                }else
                {
                    Toast.makeText(getApplicationContext(), "Fill all required choices. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
