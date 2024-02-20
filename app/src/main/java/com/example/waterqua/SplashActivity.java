package com.example.waterqua;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Button btn;
    TextView tv,tv1;
    LinearLayout lyt,lyt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btn=findViewById(R.id.splash_button);
        tv=findViewById(R.id.textabout);
        tv1=findViewById(R.id.textcontact);
        lyt=findViewById(R.id.layout);
        lyt.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        lyt1=findViewById(R.id.layout2);
        lyt1.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        String aboutText = getString(R.string.About);
        String contact = getString(R.string.Contact);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(aboutText, Html.FROM_HTML_MODE_COMPACT));
            tv1.setText(Html.fromHtml(contact, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(Html.fromHtml(aboutText));
            tv1.setText(Html.fromHtml(contact));
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        });


    }

    public void expand(View view) {
        if (view.getId() == R.id.card_about) {
            int visibility = (tv.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
            TransitionManager.beginDelayedTransition(lyt, new AutoTransition());
            tv.setVisibility(visibility);
        } else if (view.getId() == R.id.card_contact) {
            int visibility = (tv1.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
            TransitionManager.beginDelayedTransition(lyt1, new AutoTransition());
            tv1.setVisibility(visibility);
        }
    }
}
