package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckbookActivity extends AppCompatActivity {
    private String[][] check_bookings = {};
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;
    DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbook);
        btn = findViewById(R.id.buttondetailbackCHECK);
        lst = findViewById(R.id.listviewdetailCHECK);
        DBHelper db = new DBHelper(this, "WaterQuality.db", null, 1);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckbookActivity.this, Consultexpert.class));
            }
        });

        // Assuming you want to retrieve all bookings
        ArrayList<String> dbdata = db.getAllBookings(); // Modify your getInsertData() method accordingly

        check_bookings = new String[dbdata.size()][];
        for (int i = 0; i < check_bookings.length; i++) {
            check_bookings[i] = new String[5];
            String[] strData = dbdata.get(i).split("\\$"); // Corrected splitting

            check_bookings[i][0] = strData[0];
            check_bookings[i][1] = strData[1];
            check_bookings[i][2] = "Rs." + strData[2];

            // Check if strData has enough elements before accessing indices 3 and 4
            if (strData.length >= 8) {
                check_bookings[i][3] = "Del:" + strData[4] + " " + strData[5];
                check_bookings[i][4] = strData[7];
            } else if (strData.length >= 6) {
                check_bookings[i][3] = "Del:" + strData[4];
                check_bookings[i][4] = strData[5];
            } else {
                check_bookings[i][3] = "";
                check_bookings[i][4] = "";
            }
        }


        list = new ArrayList<>();
        for (int i = 0; i < check_bookings.length; i++) {
            item = new HashMap<>();
            item.put("line1", check_bookings[i][0]);
            item.put("line2", check_bookings[i][1]);
            item.put("line3", check_bookings[i][2]);
            item.put("line4", check_bookings[i][3]);
            item.put("line5", check_bookings[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multiple_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        lst.setAdapter(sa);
    }
}
