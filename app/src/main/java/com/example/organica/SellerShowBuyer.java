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

public class SellerShowBuyer extends AppCompatActivity {
    public String item_category;
    public RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<SellerBuyerInfo> Iteminfolist;
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
    String seller_username;
    SellerBuyerRecyclerAdapter sellerBuyerRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_show_buyer);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        seller_username=user.getUid();
//        seller_username="jDnfufcnNtfwAOn9v60JGQKfrrx2";
        recyclerview=(RecyclerView) findViewById(R.id.seller_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref= FirebaseDatabase.getInstance().getReference();
        Iteminfolist=new ArrayList<>();
        clearall();
        GetDataFromFirebase();
    }
    private void GetDataFromFirebase(){
        Query query=myref.child("TO_SELLER_BUYER_DETAILS").child(seller_username);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    SellerBuyerInfo iteminfo=new SellerBuyerInfo("","","","","","");
                    iteminfo.setitem_image_url(snapshott.child("item_image_url").getValue().toString());
                    iteminfo.setbuyer_username(snapshott.child("buyer_username").getValue().toString());
                    iteminfo.setitem_rate(snapshott.child("item_rate").getValue().toString());
                    iteminfo.setitem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setitem_category(snapshott.child("item_category").getValue().toString());
//                    iteminfo.setavailable_units(snapshott.child("available_units").getValue().toString());
                    Iteminfolist.add(iteminfo);
                }
                System.out.println("!@#$%^&*()");
                sellerBuyerRecyclerAdapter=new SellerBuyerRecyclerAdapter(getApplicationContext(),Iteminfolist);
                recyclerview.setAdapter(sellerBuyerRecyclerAdapter);
                sellerBuyerRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void clearall(){
        if(Iteminfolist!=null){
            Iteminfolist.clear();
            if(sellerBuyerRecyclerAdapter!=null){
                sellerBuyerRecyclerAdapter.notifyDataSetChanged();
            }
        }
        else{
            Iteminfolist=new ArrayList<>();
        }
    }

}