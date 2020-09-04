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

    public void seller_add_item(View view){
        Intent i=new Intent(MainActivity.this,Seller_post.class);
        startActivity(i);
    }

    public void seller_see_his_item(View view){
        Intent i=new Intent(MainActivity.this,Seller_see.class);
        startActivity(i);
    }

    public void buyer_vegetables(View view){
        Intent i=new Intent(MainActivity.this,Buyer_see.class);
        i.putExtra("item_category","VEGETABLES");
        startActivity(i);
    }
    public void buyer_fruits(View view){
        Intent i=new Intent(MainActivity.this,Buyer_see.class);
        i.putExtra("item_category","FRUITS");
        startActivity(i);
    }
    public void buyer_beverages(View view){
        Intent i=new Intent(MainActivity.this,Buyer_see.class);
        i.putExtra("item_category","BEVERAGES");
        startActivity(i);
    }
    public void buyer_snacks(View view){
        Intent i=new Intent(MainActivity.this,Buyer_see.class);
        i.putExtra("item_category","SNACKS");
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}