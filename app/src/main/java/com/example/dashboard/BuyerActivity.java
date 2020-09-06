package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dashboard.R;
import com.google.firebase.auth.FirebaseAuth;

public class BuyerActivity extends AppCompatActivity {
    Button sign_out;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        sign_out = findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        sign_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auth.signOut();
                    startActivity(new Intent(BuyerActivity.this, MainActivity.class));
                }
            });
    }

}