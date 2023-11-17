package com.example.waterqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArticleActivityfull extends AppCompatActivity {
    ImageView img;
    Button btn;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_activityfull);

        btn=findViewById(R.id.butnbackfull);
        img=findViewById(R.id.imgfull);
        tv1=findViewById(R.id.titlefull);
        Intent intent=getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle!= null){
            int resId= bundle.getInt("text2");
            img.setImageResource(resId);

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArticleActivityfull.this, HomeActivity.class));
            }
        });
    }
}