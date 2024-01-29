package com.example.waterqua;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {
    Button btn,btn1;
    TextView tvFeedback;
    RatingBar tstar;
    EditText editTet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn1=findViewById(R.id.buttonsubmitfeedback);
        btn=findViewById(R.id.buttonbackfeedback);
        tvFeedback=findViewById(R.id.textfeedback);
        tstar =findViewById(R.id.ratingBar);
        editTet=findViewById(R.id.editTextTextfeedback);
        tstar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0){
                    tvFeedback.setText("Very Dissatisfied");
                } else if (rating==1) {
                    tvFeedback.setText("Dissatisfied");
                }else if (rating==2 || rating==3  ) {
                    tvFeedback.setText("Ok");
                }else if (rating==4) {
                    tvFeedback.setText("Satisfied");
                }else if (rating==5) {
                    tvFeedback.setText("Very Satisfied");
                } else {
                    tvFeedback.setText("Okay Good");
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float rating = tstar.getRating();  // Get the rating value (float)
                String feedbackText = editTet.getText().toString().trim();  // Get text and trim

                if (tvFeedback.length()==0 || 0== rating) {
                    // Message or rating is blank
                    Toast.makeText(FeedbackActivity.this, "Please provide feedback and a rating.", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed with email
                }

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/html");
                i.putExtra(Intent.EXTRA_EMAIL,new String("xyz@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT, "FEEDBACK FROM THE APP");
                i.putExtra(Intent.EXTRA_TEXT,"Message:"+editTet.getText()+"\n\n Rating:\n"+tstar.getRating() );
                try {
                    startActivity(Intent.createChooser(i,"Please select email"));
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(FeedbackActivity.this, "There are no email clients ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedbackActivity.this, HomeActivity.class));
            }
        });
    }
}