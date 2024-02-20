package com.example.waterqua;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayInsertedPredictionActivity extends AppCompatActivity {
    private String[][] predictions = {};
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    ListView listView;
    Button btn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_inserted_prediction);

        if (savedInstanceState == null) {

            btn = findViewById(R.id.buttondetailbackCHECKDis);
            listView = findViewById(R.id.listviewdetailCHECKDis);
            db = new DBHelper(this, "WaterQuality.db", null, 1);

            SQLiteDatabase database = db.getWritableDatabase();


            // Assuming you want to retrieve all predictions
            ArrayList<String> dbData = db.getAllPredictions(); // Modify your getAllPredictions() method accordingly

            predictions = new String[dbData.size()][];
            for (int i = 0; i < predictions.length; i++) {
                predictions[i] = new String[6];
                String[] strData = dbData.get(i).split("\\$");
                //predictions[i][0] = strData[0];
                //predictions[i][1] = strData[1];
                //predictions[i][2] = strData[2];
                //predictions[i][3] = strData[3];

                if (strData.length >= 6) {
                    predictions[i][0] = strData[0];
                    predictions[i][1] = strData[1];
                    predictions[i][2] = strData[2];
                    predictions[i][3] = strData[3];
                    predictions[i][4] = strData[4];
                    predictions[i][5] = strData[5];

                } else {
                    predictions[i][0] = "NA";
                    predictions[i][1] = "NA";
                    predictions[i][2] = "NA";
                    predictions[i][3] = "NA";
                    predictions[i][4] = "NA";
                    predictions[i][5] = "NA";
                }
            }

            list = new ArrayList<>();
            for (int i = 0; i < predictions.length; i++) {
                item = new HashMap<>();
                item.put("line1", predictions[i][0]);
                item.put("line2", predictions[i][1]);
                item.put("line3", predictions[i][2]);
                item.put("line4", predictions[i][3]);
                item.put("line5", predictions[i][4]);
                item.put("line6", predictions[i][5]);
                list.add(item);
            }

            sa = new SimpleAdapter(this, list, R.layout.multiplee_lines,
                    new String[]{"line1", "line2", "line3", "line4", "line5", "line6"},
                    new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_f});

            listView.setAdapter(sa);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DisplayInsertedPredictionActivity.this, HomeActivity.class));
                }
            });
        }
    }
}
