package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
       // String username = sharedPreferences.getString("usename" , "").toString();
        Toast.makeText(getApplicationContext(), "Hi  " + "Welcome " , Toast.LENGTH_SHORT).show();

        CardView waterNews = findViewById(R.id.cardWaternews);
        waterNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ArticleActivity.class));
            }
        });

    }

}