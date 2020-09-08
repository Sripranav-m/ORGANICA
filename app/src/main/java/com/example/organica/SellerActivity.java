package com.example.organica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellerActivity extends AppCompatActivity {
    Button sign_out;
    private FirebaseAuth auth;
    public RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<ItemInfo> Iteminfolist;
    private ItemRecyclerAdapter itemrecyclerAdapter;
    private Context context;
    public String seller_username;

    public void seller_post_items(View view){
        Intent intent = new Intent(SellerActivity.this, Seller_post.class);
        intent.putExtra("seller_username",seller_username);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
//        sign_out = findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        seller_username=user.getEmail();
//        sign_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                auth.signOut();
//                startActivity(new Intent(SellerActivity.this, MainActivity.class));
//            }
//        });
        recyclerview=(RecyclerView) findViewById(R.id.seller_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref= FirebaseDatabase.getInstance().getReference();
        Iteminfolist=new ArrayList<>();
        clearall();
//        GetDataFromFirebase();
        GetDataFromFirebase("VEGETABLES");
        GetDataFromFirebase("FRUITS");
        GetDataFromFirebase("BEVERAGES");
        GetDataFromFirebase("SNACKS");
    }
    private void GetDataFromFirebase(String item_category_){
        Query query=myref.child("ITEMS").child(item_category_);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    ItemInfo iteminfo=new ItemInfo("","","","","","");
                    iteminfo.setitem_image_url(snapshott.child("item_image_url").getValue().toString());
                    iteminfo.setseller_username(snapshott.child("seller_username").getValue().toString());
                    iteminfo.setitem_rate(snapshott.child("item_rate").getValue().toString());
                    iteminfo.setitem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setitem_category(snapshott.child("item_category").getValue().toString());
                    iteminfo.setavailable_units(snapshott.child("available_units").getValue().toString());
                    if(snapshott.child("seller_username").getValue().toString().equals(seller_username)) {
                        Iteminfolist.add(iteminfo);
                    }
                }
                itemrecyclerAdapter=new ItemRecyclerAdapter(getApplicationContext(),Iteminfolist);
                recyclerview.setAdapter(itemrecyclerAdapter);
                itemrecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void clearall(){
        if(Iteminfolist!=null){
            Iteminfolist.clear();
            if(itemrecyclerAdapter!=null){
                itemrecyclerAdapter.notifyDataSetChanged();
            }
        }
        else{
            Iteminfolist=new ArrayList<>();
        }
    }

}