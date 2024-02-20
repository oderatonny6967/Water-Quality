package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PredictActivity extends AppCompatActivity {
    private EditText editTextTurbidity, editTextConductivity, editTextHardness;
    private Spinner spinnerPH;
    private Button predictButton,predictbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        editTextTurbidity = findViewById(R.id.editTextTurbidity);
        editTextConductivity = findViewById(R.id.editTextConductivity);
        editTextHardness = findViewById(R.id.editTextHardness);
        spinnerPH = findViewById(R.id.spinnerPH);
        predictButton = findViewById(R.id.predictbutton);
        predictbackbutton=findViewById(R.id.predictionback);
        DBHelper db = new DBHelper(this, "WaterQuality.db", null, 1);

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String turbidityText = editTextTurbidity.getText().toString();
                String conductivityText = editTextConductivity.getText().toString();
                String hardnessText = editTextHardness.getText().toString();

                if (turbidityText.isEmpty() || conductivityText.isEmpty() || hardnessText.isEmpty()) {
                    // Display a pop-up message indicating that fields cannot be empty
                    Toast.makeText(PredictActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method
                }

                // Retrieve input values
                double turbidity = Double.parseDouble(turbidityText);
                double conductivity = Double.parseDouble(conductivityText);
                double hardness = Double.parseDouble(hardnessText);
                double pH = Double.parseDouble(spinnerPH.getSelectedItem().toString());

                // Calculate the number of parameters within the safe range
                int safeParameterCount = 0;
                if (turbidity <= 5) safeParameterCount++;
                if (conductivity <= 800) safeParameterCount++;
                if (hardness <= 150) safeParameterCount++;
                if (pH >= 6.5 && pH <= 8.5) safeParameterCount++;

                // Determine water quality based on the number of safe parameters
                String waterQuality = (safeParameterCount <= 2) ? "Not Safe" : "Safe";

                // Provide recommendation based on water quality
                String recommendation = waterQuality.equals("Safe") ?
                        "Water quality is safe for consumption." :
                        "Water quality is not safe. Please take necessary precautions.";

                // Insert prediction data into the database
                DBHelper db = new DBHelper(getApplicationContext(),"WaterQuality.db",null,1);
                db.insertPrediction(turbidity, conductivity, hardness, pH, waterQuality, recommendation);

                // Start ViewpredictionActivity to display prediction result
                Intent intent = new Intent(PredictActivity.this, ViewpredictionActivity.class);
                intent.putExtra("turbidity", turbidity);
                intent.putExtra("conductivity", conductivity);
                intent.putExtra("hardness", hardness);
                intent.putExtra("pH", pH);
                intent.putExtra("waterQuality", waterQuality);
                intent.putExtra("recommendation", recommendation);
                startActivity(intent);
            }
        });
        predictbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PredictActivity.this,HomeActivity.class));
            }
        });

    }
}

