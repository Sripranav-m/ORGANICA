package com.example.organica;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class Seller_see extends AppCompatActivity {
    public RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<ItemInfo> Iteminfolist;
    private ItemRecyclerAdapter itemrecyclerAdapter;
    private Context context;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_see);
        recyclerview=(RecyclerView) findViewById(R.id.seller_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref=FirebaseDatabase.getInstance().getReference();
        Iteminfolist=new ArrayList<>();
        username="SRIPRANAV";/////////////////////////////////////as of now//////////////////////
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
                    if(snapshott.child("seller_username").getValue().toString().equals(username)) {
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