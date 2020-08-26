package com.example.organica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void addarticle(View view){
        Intent i=new Intent(MainActivity.this,AddArticle.class);
        startActivity(i);
    }
    public void showarticles(View view){
        Intent i=new Intent(MainActivity.this,ShowArticles.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}