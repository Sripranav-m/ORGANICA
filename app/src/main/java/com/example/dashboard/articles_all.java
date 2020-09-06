package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dashboard.HelperClass.AllArtAdapter;
import com.example.dashboard.HelperClass.SnackAdapter;

public class articles_all extends AppCompatActivity {
    RecyclerView mostartview;
    RecyclerView.LayoutManager layoutManager;
    AllArtAdapter mostartadapter;
    int arr[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_all);
         mostartview = findViewById(R.id.mostarticlesview);
        layoutManager = new GridLayoutManager(this,2);
         mostartview.setLayoutManager(layoutManager);
       mostartadapter= new AllArtAdapter(arr);
         mostartview.setAdapter(mostartadapter);
         mostartview.setHasFixedSize(true);

    }
}