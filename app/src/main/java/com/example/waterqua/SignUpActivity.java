package com.example.waterqua;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText edUsername, edPassword, edEmail, edConfirmpass;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edUsername = findViewById(R.id.editTextSignUsername);
        edUsername = findViewById(R.id.editTextSignConfirmPassword);
        edPassword = findViewById(R.id.editTextSignPassword);
        btn = findViewById(R.id.buttonSign);
        tv = findViewById(R.id.textView3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirmpass.getText().toString();


                if (TextUtilis.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Please enter username ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtilis.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter password ", Toast.LENGTH_SHORT).show();
                }

            }

        });
        tv.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
    }
    }
}






