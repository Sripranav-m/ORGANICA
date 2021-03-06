package com.example.organica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BuyerCart extends AppCompatActivity {
    public String item_category;
    public RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<Buyer_Order> BuyerOrderinfolist;
    private BuyerCartRecyclerAdapter buyerCartRecyclerAdapter;
    private Context context;
    public String username;
    private FirebaseAuth auth;
    public EditText search_item;
    private onClickInterface onclickInterface;
    private StorageReference storagereference;
    public DatabaseReference reference;
    private FirebaseStorage storage;
    public DatabaseReference databaseReference;
    private ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_cart);
        ImageView back_btn = findViewById(R.id.back_btn_cart);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                username=user.getUid();
                RecyclerView.ViewHolder item=recyclerview.findViewHolderForAdapterPosition(abc);
                System.out.println(BuyerOrderinfolist.get(abc).item_image_url);
                String imageurl=BuyerOrderinfolist.get(abc).item_image_url;
                String itemname=BuyerOrderinfolist.get(abc).item_name;
                String itemrate=BuyerOrderinfolist.get(abc).item_rate;
                String itemcategory=BuyerOrderinfolist.get(abc).item_category;
                String order_id=BuyerOrderinfolist.get(abc).order_id;
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id);
                reference.child("BUYER_ORDERS").child(user.getUid()).child(id).setValue(bo);
                Toast.makeText(BuyerCart.this,"Successfully Placed Your Order",Toast.LENGTH_SHORT).show();
                reference.child("BUYER_CART").child(user.getUid()).child(order_id).removeValue();
                buyerCartRecyclerAdapter.notifyDataSetChanged();
            }
            public void setClickadd(int abc) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                username=user.getUid();
                RecyclerView.ViewHolder item=recyclerview.findViewHolderForAdapterPosition(abc);
                System.out.println(BuyerOrderinfolist.get(abc).item_image_url);
                String imageurl=BuyerOrderinfolist.get(abc).item_image_url;
                String itemname=BuyerOrderinfolist.get(abc).item_name;
                String itemrate=BuyerOrderinfolist.get(abc).item_rate;
                String itemcategory=BuyerOrderinfolist.get(abc).item_category;
                String order_id=BuyerOrderinfolist.get(abc).order_id;
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id);
                Toast.makeText(BuyerCart.this,"Removing From Cart.....",Toast.LENGTH_SHORT).show();
                reference.child("BUYER_CART").child(user.getUid()).child(order_id).removeValue();
                buyerCartRecyclerAdapter.notifyDataSetChanged();
            }
        };
        recyclerview=(RecyclerView) findViewById(R.id.buyer_cart_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref= FirebaseDatabase.getInstance().getReference();
        BuyerOrderinfolist=new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        username=user.getUid();
        clearall();
        System.out.println("=============================");
        System.out.println(username);
        GetDataFromFirebase(username);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void GetDataFromFirebase(final String username){
        Query query=myref.child("BUYER_CART").child(username);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearall();
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    Buyer_Order iteminfo=new Buyer_Order("","","","","","");
                    iteminfo.setitem_image_url(snapshott.child("item_image_url").getValue().toString());
                    iteminfo.setbuyer_username(username);
                    iteminfo.setitem_rate(snapshott.child("item_rate").getValue().toString());
                    iteminfo.setitem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setitem_category(snapshott.child("item_category").getValue().toString());
                    iteminfo.setorder_id(snapshott.child("order_id").getValue().toString());
                    BuyerOrderinfolist.add(iteminfo);
                }
                buyerCartRecyclerAdapter=new BuyerCartRecyclerAdapter(getApplicationContext(),BuyerOrderinfolist,onclickInterface);
                recyclerview.setAdapter(buyerCartRecyclerAdapter);
                buyerCartRecyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void clearall(){
        if(BuyerOrderinfolist!=null){
            BuyerOrderinfolist.clear();
            if(buyerCartRecyclerAdapter!=null){
                buyerCartRecyclerAdapter.notifyDataSetChanged();
            }
        }
        else{
            BuyerOrderinfolist=new ArrayList<>();
        }
    }
}