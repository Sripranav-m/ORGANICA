package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dashboard.HelperClass.MostBuyAdapter;
import com.example.dashboard.HelperClass.SnackAdapter;

public class most_buyed extends AppCompatActivity {
    RecyclerView mostbuyview;
    RecyclerView.LayoutManager layoutManager;
    MostBuyAdapter mostbuyadapter;
    int arr[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_buyed);
        mostbuyview = findViewById(R.id.mostbuyview);
        layoutManager = new GridLayoutManager(this,1);
        mostbuyview.setLayoutManager(layoutManager);
        mostbuyadapter = new MostBuyAdapter(arr);
        mostbuyview.setAdapter(mostbuyadapter);
        mostbuyview.setHasFixedSize(true);

    }
}