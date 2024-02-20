package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleActivity extends AppCompatActivity {


    private  String [][] water_quality = {
            {"Water quality indicators ","","","","Click for more details"},
            {"An ignored crisis ","","","","Click for more details"},
            {" Contaminants found in water ","","","","Click for more details"},
            {"Impact on water quality ","","","","Click for more details"},
            {"The future of water quality  ","","","","Click for more details"},
    };
    private int[] images={
            R.drawable.quality1,
            R.drawable.quality2,
            R.drawable.quality3,
            R.drawable.quality4,
            R.drawable.quality5,
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        lst= findViewById(R.id.List);
        btnBack=findViewById(R.id.buttonback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArticleActivity.this, HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i =0 ;i <water_quality.length;i++){
            item=new HashMap<String,String>();
            item.put("line 1",water_quality[i][0]);
            item.put("line 2",water_quality[i][1]);
            item.put("line 3",water_quality[i][2]);
            item.put("line 4",water_quality[i][3]);
            item.put("line 5",water_quality[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list, R.layout.multi_lines, new String[]{"line 1","line 2","line 3","line 4","line 5"}, new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_ad,R.id.line_e});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i , long l) {
                Intent it = new Intent(ArticleActivity.this, ArticleActivityfull.class);
                it.putExtra("text1",water_quality[i][0]);
                it.putExtra("text2",images [i]);
                startActivity(it);
            }
        });
    }
}
