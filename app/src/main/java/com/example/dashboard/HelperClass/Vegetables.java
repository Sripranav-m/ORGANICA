package com.example.dashboard.HelperClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dashboard.HelperClass.SnackAdapter;
import com.example.dashboard.R;

public class Vegetables extends AppCompatActivity {
    RecyclerView vegetabelview;
    RecyclerView.LayoutManager layoutManager;
    VegetableAdapter  vegetableadapter;
    int arr[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);
        vegetabelview = findViewById(R.id.vegetableview);
        layoutManager = new GridLayoutManager(this,2);
        vegetabelview.setLayoutManager(layoutManager);
        vegetableadapter = new VegetableAdapter(arr);
        vegetabelview.setAdapter(vegetableadapter);
        vegetabelview.setHasFixedSize(true);

    }
}