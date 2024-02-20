package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Consultexpert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultexpert);

        CardView ceCHECK= findViewById(R.id.cardCEcheck);
        ceCHECK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Consultexpert.this,CheckbookActivity.class));
            }
        });
        CardView cehydrologist= findViewById(R.id.cardCEhydrologist);
        cehydrologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Consultexpert.this, DetailedActivity.class);
                it.putExtra("title","Hydrologist");
                startActivity(it);

            }
        });
        CardView cewater= findViewById(R.id.cardCEwateranalyst);
        cewater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Consultexpert.this, DetailedActivity.class);
                it.putExtra("title","Water Analyst");
                startActivity(it);
            }
        });
        CardView cewaterengineer= findViewById(R.id.cardCEwaterengineer);
        cewaterengineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent it = new Intent(Consultexpert.this, DetailedActivity.class);
                 it.putExtra("title","Water Engineer");
                 startActivity(it);
            }
        });

        CardView ceBACK= findViewById(R.id.cardCEback);
        ceBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Consultexpert.this, HomeActivity.class);
                startActivity(it);
            }
        });

    }
}