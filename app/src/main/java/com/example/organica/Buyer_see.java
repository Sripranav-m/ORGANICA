package com.example.organica;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Buyer_see extends AppCompatActivity {
    public String item_category;
    public RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<ItemInfo> Iteminfolist;
    private BuyerItemRecyclerAdapter buyeritemrecyclerAdapter;
    private Context context;
    public String username;
    private FirebaseAuth auth;
    public EditText search_item;
    private onClickInterface onclickInterface;
    private StorageReference storagereference;
    public DatabaseReference reference;
    private FirebaseStorage storage;
    public DatabaseReference databaseReference;

    public void search_(View view){
        String search=search_item.getText().toString();
        if(search.trim().length()>0) {
            GetDataFromFirebase(item_category,search.toLowerCase());
        }
        else{
            Toast.makeText(getApplicationContext(),"Failed To search.....",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_see);
        search_item=(EditText)findViewById(R.id.search_text);
        Intent i=getIntent();
        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                username=user.getEmail();
                RecyclerView.ViewHolder item=recyclerview.findViewHolderForAdapterPosition(abc);
                System.out.println(Iteminfolist.get(abc).item_image_url);
                String imageurl=Iteminfolist.get(abc).item_image_url;
                String itemname=Iteminfolist.get(abc).item_name;
                String itemrate=Iteminfolist.get(abc).item_rate;
                String itemcategory=Iteminfolist.get(abc).item_category;
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id);
                reference.child("BUYER_ORDERS").child(user.getUid()).child(id).setValue(bo);
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
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id);
                reference.child("BUYER_CART").child(user.getUid()).child(id).setValue(bo);
                Toast.makeText(Buyer_see.this,"Successfully Added to cart",Toast.LENGTH_LONG).show();
                buyeritemrecyclerAdapter.notifyDataSetChanged();
            }
        };
        item_category=i.getStringExtra("item_category");
        recyclerview=(RecyclerView) findViewById(R.id.buyer_see_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref=FirebaseDatabase.getInstance().getReference();
        Iteminfolist=new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        username=user.getEmail();
        clearall();
        GetDataFromFirebase(item_category,"");
    }

    private void GetDataFromFirebase(String item_category, final String search_string){
        System.out.println(search_string);
        Query query=myref.child("ITEMS").child(item_category);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearall();
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    ItemInfo iteminfo=new ItemInfo("","","","","","");
                    iteminfo.setitem_image_url(snapshott.child("item_image_url").getValue().toString());
                    iteminfo.setseller_username(snapshott.child("seller_username").getValue().toString());
                    iteminfo.setitem_rate(snapshott.child("item_rate").getValue().toString());
                    iteminfo.setitem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setitem_category(snapshott.child("item_category").getValue().toString());
                    iteminfo.setavailable_units(snapshott.child("available_units").getValue().toString());
                    if(search_string.trim().length()==0) {
                        Iteminfolist.add(iteminfo);
                    }
                    else{
                        String temp=snapshott.child("item_name").getValue().toString();
                        temp=temp.toLowerCase();
                        if(temp.contains(search_string)) {
                            Iteminfolist.add(iteminfo);
                        }
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