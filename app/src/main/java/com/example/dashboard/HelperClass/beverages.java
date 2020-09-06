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

public class beverages extends AppCompatActivity {
    RecyclerView beveragesview;
    RecyclerView.LayoutManager layoutManager;
    BeveragesAdapter beveragesAdapter;
    EditText search_beverages;
    ArrayList<BeveragesHelper> examplelist = new ArrayList<>();
//    int arr[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);
        beveragesview = findViewById(R.id.beveragesview);
        layoutManager = new GridLayoutManager(this,1);
        beveragesview.setLayoutManager(layoutManager);
        examplelist.add(new BeveragesHelper(R.drawable.img,"item1","$10"));
        examplelist.add(new BeveragesHelper(R.drawable.img,"ffwf","$10"));
        examplelist.add(new BeveragesHelper(R.drawable.img,"eewfwem1f","$10"));
        examplelist.add(new BeveragesHelper(R.drawable.img,"htem1","$10"));
        examplelist.add(new BeveragesHelper(R.drawable.img,"gttem1","$10"));
        beveragesAdapter = new BeveragesAdapter(examplelist);
        beveragesview.setAdapter(beveragesAdapter);
        beveragesview.setHasFixedSize(true);
        search_beverages = findViewById(R.id.search_beverages);
        search_beverages.addTextChangedListener(new TextWatcher() {
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
        ArrayList<BeveragesHelper> filteredList = new ArrayList<>();
        for(BeveragesHelper item: examplelist){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        beveragesAdapter.filterlist(filteredList);
    }
}