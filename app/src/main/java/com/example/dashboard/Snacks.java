package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.dashboard.HelperClass.ImpAdapter;
import com.example.dashboard.HelperClass.ImpHelper;
import com.example.dashboard.HelperClass.SnackAdapter;
import com.example.dashboard.HelperClass.SnackHelper;

import java.util.ArrayList;

public class Snacks extends AppCompatActivity {
        RecyclerView snackview;
        EditText search_snack;
        RecyclerView.LayoutManager layoutManager;
        SnackAdapter snackadapter;
    ArrayList<SnackHelper> examplelist = new ArrayList<>();
//        int arr[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        snackview = findViewById(R.id.snacksview);
        layoutManager = new GridLayoutManager(this,1);
        snackview.setLayoutManager(layoutManager);

        examplelist.add(new SnackHelper(R.drawable.img,"item1","$10"));
        examplelist.add(new SnackHelper(R.drawable.img,"ffwf","$10"));
        examplelist.add(new SnackHelper(R.drawable.img,"eewfwem1f","$10"));
        examplelist.add(new SnackHelper(R.drawable.img,"htem1","$10"));
        examplelist.add(new SnackHelper(R.drawable.img,"gttem1","$10"));
        snackadapter = new SnackAdapter(examplelist);
        snackview.setAdapter(snackadapter);
        snackview.setHasFixedSize(true);
        search_snack = findViewById(R.id.search_snacks);
        search_snack.addTextChangedListener(new TextWatcher() {
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
        ArrayList<SnackHelper> filteredList = new ArrayList<>();
        for(SnackHelper item: examplelist){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        snackadapter.filterlist(filteredList);
    }
}