package com.example.dashboard.HelperClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.dashboard.HelperClass.SnackAdapter;
import com.example.dashboard.R;

import java.util.ArrayList;

public class fruits extends AppCompatActivity {
    RecyclerView fruitview;
    RecyclerView.LayoutManager layoutManager;
   FruitsAdapter fruitsAdapter;
   EditText search_fruits;
   ArrayList<FruitsHelper> examplelist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        fruitview = findViewById(R.id.fruitsview);
        layoutManager = new GridLayoutManager(this,1);
        fruitview.setLayoutManager(layoutManager);
        examplelist.add(new FruitsHelper(R.drawable.img,"item1","$10"));
        examplelist.add(new FruitsHelper(R.drawable.img,"ffwf","$10"));
        examplelist.add(new FruitsHelper(R.drawable.img,"eewfwem1f","$10"));
        examplelist.add(new FruitsHelper(R.drawable.img,"htem1","$10"));
        examplelist.add(new FruitsHelper(R.drawable.img,"gttem1","$10"));
       fruitsAdapter = new FruitsAdapter(examplelist);
        fruitview.setAdapter(fruitsAdapter);
        fruitview.setHasFixedSize(true);
       search_fruits = findViewById(R.id.search_fruits);
       search_fruits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });

    }
    private void filter(String text){
        ArrayList<FruitsHelper> filteredList = new ArrayList<>();
        for(FruitsHelper item: examplelist){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        fruitsAdapter.filterlist(filteredList);
    }
}