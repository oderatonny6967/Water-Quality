package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewpredictionActivity extends AppCompatActivity {
    Button save ,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprediction);
        save=findViewById(R.id.buttonsave);
        back=findViewById(R.id.buttonback);

        // Retrieve input values from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double turbidity = extras.getDouble("turbidity");
            double conductivity = extras.getDouble("conductivity");
            double hardness = extras.getDouble("hardness");
            double pH = extras.getDouble("pH");
            String waterQuality = extras.getString("waterQuality");

            // Display prediction result and recommendations
            TextView predictionResultTextView = findViewById(R.id.predictionResultTextView);
            TextView recommendationTextView = findViewById(R.id.recommendationTextView);

            // Set prediction result text
            predictionResultTextView.setText("Prediction Result: " + waterQuality);

            // Set recommendation text based on the prediction result
            if (waterQuality.equals("Safe")) {
                recommendationTextView.setText("Recommendation: The water quality is safe for consumption.");
            } else {
                recommendationTextView.setText("Recommendation: The water quality is not safe. Please take necessary precautions.");
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewpredictionActivity.this,PredictActivity.class));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    double turbidity = extras.getDouble("turbidity");
                    double conductivity = extras.getDouble("conductivity");
                    double hardness = extras.getDouble("hardness");
                    double pH = extras.getDouble("pH");
                    String waterQuality = extras.getString("waterQuality");
                    String recommendation = extras.getString("recommendation");

                    // Insert prediction result and recommendation into database
                    DBHelper db = new DBHelper(getApplicationContext(),"WaterQuality.db",null,1);
                    //if(db.checkPredictionexists(turbidity, conductivity, hardness, pH, waterQuality, recommendation)==1){
                       // Toast.makeText(getApplicationContext(), "Data already saved ", Toast.LENGTH_SHORT).show();
                    //}
                    //else {
                        db.insertPrediction(turbidity, conductivity, hardness, pH, waterQuality, recommendation);
                        Toast.makeText(ViewpredictionActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                    }

                    // Start a new activity to display the inserted data from the database

                }



        });
    }
}
