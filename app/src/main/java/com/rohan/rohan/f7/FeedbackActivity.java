package com.rohan.rohan.f7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackActivity extends AppCompatActivity {
    TextView disclaimer;
    EditText feedBack;
    Button sendFeedback;


    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        disclaimer = findViewById(R.id.disclaimer);
        feedBack = findViewById(R.id.feedback);
        sendFeedback = findViewById(R.id.sendFeedback);
        databaseReference = FirebaseDatabase.getInstance().getReference("DISCLAIMER").child("textMessage");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                disclaimer.setText(dataSnapshot.getValue().toString());
                findViewById(R.id.progress_circular).setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("FEEDBACK").child("messages");

        //databaseReference.setValue(null);


        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.progress_circular).setVisibility(View.VISIBLE);
                final int[] x = new int[1];
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        x[0] = (int) dataSnapshot.getChildrenCount();
                        if (feedBack.getText().toString().length()>0)
                        {
                            databaseReference.child(""+(dataSnapshot.getChildrenCount() +1)).setValue(feedBack.getText().toString());

                            finish();
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            //finish();
                        }else
                        {
                            findViewById(R.id.progress_circular).setVisibility(View.GONE);
                            Toast.makeText(FeedbackActivity.this, "No text available to send.", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
