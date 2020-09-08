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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private onClickInterface onclickInterface;
    private FirebaseAuth auth;
    private StorageReference storagereference;
    public DatabaseReference reference;
    private FirebaseStorage storage;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_see);
        Intent i=getIntent();
        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                username=user.getEmail();
                RecyclerView.ViewHolder item=recyclerview.findViewHolderForAdapterPosition(abc);
                String imageurl=Iteminfolist.get(abc).item_image_url;
                String itemname=Iteminfolist.get(abc).item_name;
                String itemrate=Iteminfolist.get(abc).item_rate;
                String itemcategory=Iteminfolist.get(abc).item_category;
                String seller=Iteminfolist.get(abc).seller_username;
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id,seller);
                reference.child("BUYER_ORDERS").child(user.getUid()).child(id).setValue(bo);
                reference.child("TO_SELLER_BUYER_DETAILS").child(seller).child(id).setValue(bo);
                Toast.makeText(Buyer_see.this,"Successfully Placed Your Order",Toast.LENGTH_LONG).show();
                buyeritemrecyclerAdapter.notifyDataSetChanged();
            }
            public void setClickadd(int abc) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                username=user.getEmail();
                RecyclerView.ViewHolder item=recyclerview.findViewHolderForAdapterPosition(abc);
                System.out.println(Iteminfolist.get(abc).item_image_url);
                String imageurl=Iteminfolist.get(abc).item_image_url;
                String itemname=Iteminfolist.get(abc).item_name;
                String itemrate=Iteminfolist.get(abc).item_rate;
                String itemcategory=Iteminfolist.get(abc).item_category;
                String seller=Iteminfolist.get(abc).seller_username;
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id,seller);
                reference.child("BUYER_CART").child(user.getUid()).child(id).setValue(bo);
                Toast.makeText(Buyer_see.this,"Successfully Added to cart",Toast.LENGTH_LONG).show();
                buyeritemrecyclerAdapter.notifyDataSetChanged();
            }
        };
        search_string=i.getStringExtra("search_string");
        System.out.println(search_string);
        recyclerview=(RecyclerView) findViewById(R.id.buyer_see_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref=FirebaseDatabase.getInstance().getReference();
        Iteminfolist=new ArrayList<>();
        FirebaseUser user = auth.getCurrentUser();
        username=user.getUid();/////////////////////////////////////as of now//////////////////////
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
                buyeritemrecyclerAdapter=new BuyerItemRecyclerAdapter(getApplicationContext(),Iteminfolist,onclickInterface);
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