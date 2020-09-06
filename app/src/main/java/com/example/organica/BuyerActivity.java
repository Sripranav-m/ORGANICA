package com.example.organica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class BuyerActivity extends AppCompatActivity {
    Button sign_out;
    private FirebaseAuth auth;
    public EditText search_item;

    public void search(View view) {
        String search = search_item.getText().toString();
        if (search.trim().length() > 0) {
            Intent i = new Intent(BuyerActivity.this, BuyerSearch.class);
            i.putExtra("search_string", search);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Failed To search.....", Toast.LENGTH_SHORT).show();
        }
    }

    public void buyer_vegetables(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "VEGETABLES");
        startActivity(i);
    }

    public void buyer_fruits(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "FRUITS");
        startActivity(i);
    }

    public void buyer_beverages(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "BEVERAGES");
        startActivity(i);
    }

    public void buyer_snacks(View view) {
        Intent i = new Intent(BuyerActivity.this, Buyer_see.class);
        i.putExtra("item_category", "SNACKS");
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
//        sign_out = findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        search_item = (EditText) findViewById(R.id.search_text);
//        sign_out.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    auth.signOut();
//                    startActivity(new Intent(BuyerActivity.this, MainActivity.class));
//                }
//            });


    }
}