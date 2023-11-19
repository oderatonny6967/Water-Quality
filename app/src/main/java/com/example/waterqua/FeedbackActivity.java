package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {
    Button btn;
    TextView tvFeedback;
    RatingBar tbstar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn=findViewById(R.id.buttonbackfeedback);
        tvFeedback=findViewById(R.id.textfeedback);
        tbstar=findViewById(R.id.ratingBar);

        tbstar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0){

                    tvFeedback.setText("Very Disatisfied");
                } else if (rating==1) {
                    tvFeedback.setText("Disatisfied");
                }else if (rating==2 || rating==3  ) {
                    tvFeedback.setText("Ok");
                }else if (rating==4) {
                    tvFeedback.setText("Satisfied");
                }else if (rating==5) {
                    tvFeedback.setText("Very Satisfied");
                } else {

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