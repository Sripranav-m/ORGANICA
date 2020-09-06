package com.example.organica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BuyerSearch extends AppCompatActivity {
    public String search_string;
    public RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<ItemInfo> Iteminfolist;
    private BuyerItemRecyclerAdapter buyeritemrecyclerAdapter;
    private Context context;
    public String username;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_see);
        Intent i=getIntent();
        search_string=i.getStringExtra("search_string");
        System.out.println(search_string);
        recyclerview=(RecyclerView) findViewById(R.id.buyer_see_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref=FirebaseDatabase.getInstance().getReference();
        Iteminfolist=new ArrayList<>();
//        FirebaseUser user = auth.getCurrentUser();
//        username=user.getUid();/////////////////////////////////////as of now//////////////////////
        username="SRIPRANAV";
        clearall();
        search_string=search_string.toLowerCase();
        GetDataFromFirebase(search_string,"VEGETABLES");
        GetDataFromFirebase(search_string,"FRUITS");
        GetDataFromFirebase(search_string,"BEVERAGES");
        GetDataFromFirebase(search_string,"SNACKS");
    }
    private void GetDataFromFirebase(final String search_string, String item_category){
        Query query=myref.child("ITEMS").child(item_category);
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
                    String temp=snapshott.child("item_name").getValue().toString();
                    temp=temp.toLowerCase();
                    if(temp.contains(search_string)) {
                        Iteminfolist.add(iteminfo);
                    }
                }
                buyeritemrecyclerAdapter=new BuyerItemRecyclerAdapter(getApplicationContext(),Iteminfolist);
                recyclerview.setAdapter(buyeritemrecyclerAdapter);
                buyeritemrecyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void clearall(){
        if(Iteminfolist!=null){
            Iteminfolist.clear();
            if(buyeritemrecyclerAdapter!=null){
                buyeritemrecyclerAdapter.notifyDataSetChanged();
            }
        }
        else{
            Iteminfolist=new ArrayList<>();
        }
    }
}