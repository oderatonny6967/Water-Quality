package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {
    private String[][] water_expert1 = {
            {"Expertname: David Smith", "Address : New York", "Exp: 5 years", "Mobile no : 09999999", "300"},
            {"Expertname: John Doe", "Address : Los Angeles", "Exp: 9 years", "Mobile no : 0905674", "700"},
            {"Expertname: Mary Johnson", "Address : Chicago", "Exp: 15 years", "Mobile no : 09123456", "4600"},
            {"Expertname: Michael Brown", "Address : Houston", "Exp: 7 years", "Mobile no : 09234567", "980"},
            {"Expertname: Jennifer Davis", "Address : Philadelphia", "Exp: 4 years", "Mobile no : 09876543", "5600"},
            {"Expertname: Christopher Wilson", "Address : Phoenix", "Exp: 63 years", "Mobile no : 09765438", "400"},
            {"Expertname: Lisa Martinez", "Address : San Antonio", "Exp: 7 years", "Mobile no : 0934567", "9000"}
    };

    private String[][] water_expert2 = {
            {"Expertname: Ethan Johnson", "Address : Dallas", "Exp : 15 years", "Mobile no : 0987654", "900"},
            {"Expertname: Olivia Garcia", "Address : San Jose", "Exp : 3 years", "Mobile no : 09876543", "800"},
            {"Expertname: Alexander Brown", "Address : Miami", "Exp : 5 years", "Mobile no : 09765432", "750"},
            {"Expertname: Isabella Martinez", "Address : Houston", "Exp : 10 years", "Mobile no : 09654321", "1200"},
            {"Expertname: William Clark", "Address : Chicago", "Exp : 4 years", "Mobile no : 09543210", "1100"},
            {"Expertname: Sophia Thomas", "Address : New York", "Exp : 6 years", "Mobile no : 09432109", "1000"},
            {"Expertname: James Taylor", "Address : Los Angeles", "Exp : 7 years", "Mobile no : 09321098", "950"}
    };

    private String[][] water_expert3 = {
            {"Expertname: Emma Smith", "Office Address : Miami", "Exp : 15 years", "Mobile no : 09999999", "700"},
            {"Expertname: William Jones", "Office Address : San Francisco", "Exp : 8 years", "Mobile no : 0456789", "800"},
            {"Expertname: Sophia Martinez", "Office Address : Houston", "Exp : 9 years", "Mobile no : 0909876", "650"},
            {"Expertname: Ethan Johnson", "Office Address : Dallas", "Exp : 7 years", "Mobile no : 09123456", "780"},
            {"Expertname: Olivia Garcia", "Office Address : San Jose", "Exp : 4 years", "Mobile no : 09678990", "9000"},
            {"Expertname: Alexander Brown", "Office Address : Miami", "Exp : 5 years", "Mobile no : 099987643", "3560"},
            {"Expertname: Isabella Thomas", "Office Address : Chicago", "Exp : 5 years", "Mobile no : 099654321", "9870"}
    };


    TextView tv;
    ListView lst;
    Button btn;
    String [][] water_expert = {};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        tv=findViewById(R.id.titledetailtop);
        btn=findViewById(R.id.buttondetailback);
        Intent it=getIntent();
        final String title=it.getStringExtra("title");
        //String title=it.getStringExtra("title");
        // tv.setText(title);

        if (title == null) {
            // Handle the case when title is null (e.g., provide a default value or finish the activity)
            // For example:
            //title = "Default Title";
            startActivity(new Intent(DetailedActivity.this , Consultexpert.class));
            finish();
            return;
        }
        tv.setText(title);

        if (title.equals("Water Analyst"))
            water_expert = water_expert1;
        else if (title.equals("Water Engineer"))
            water_expert = water_expert2;
        else
            water_expert = water_expert3;

        //final String finalTitle = title;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DetailedActivity.this , Consultexpert.class));
                finish();
            }
        });
        list = new ArrayList();
        for(int i=0;i<water_expert.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",water_expert[i][0]);
            item.put("line2",water_expert[i][1]);
            item.put("line3",water_expert[i][2]);
            item.put("line4",water_expert[i][3]);
            item.put("line5","cons fee :"+"Ksh"+water_expert[i][4]+"/=");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multiple_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listviewdetail);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it =new Intent(DetailedActivity.this,BookAppointment.class);
                it.putExtra("text1",title);
                it.putExtra("text2",water_expert[i][0]);
                it.putExtra("text3",water_expert[i][1]);
                it.putExtra("text4",water_expert[i][3]);
                it.putExtra("text5",water_expert[i][4]);
                startActivity(it);
            }
        });

    }
}