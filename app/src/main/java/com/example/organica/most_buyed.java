package com.example.organica;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
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

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class most_buyed extends AppCompatActivity {
    private onClickInterface onclickInterface;
    String username;
    FirebaseAuth auth;
    DatabaseReference myref;
    DatabaseReference reference;
    RecyclerView mostbuyview;
    ArrayList<BuyedHelperViewAll> most_buyed;
    MostBuyedViewAllAdapter most_buyed_adapter;
    ImageView back_btn ;

//    int arr[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_buyed);
        mostbuyview = findViewById(R.id.mostbuyview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        layoutManager = new GridLayoutManager(this,1);
//
//
//        mostbuyview.setAdapter(mostbuyadapter);
//        mostbuyview.setHasFixedSize(true);

        back_btn = findViewById(R.id.back_btn_mostbuyed);


        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                username=user.getEmail();
                RecyclerView.ViewHolder item=mostbuyview.findViewHolderForAdapterPosition(abc);
//                System.out.println(Iteminfolist.get(abc).item_image_url);
                String imageurl=most_buyed.get(abc).item_image_url;
                String itemname=most_buyed.get(abc).item_name;
                String itemrate=most_buyed.get(abc).item_rate;
                String itemcategory=most_buyed.get(abc).item_category;
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id);
                reference.child("BUYER_ORDERS").child(user.getUid()).child(id).setValue(bo);
                Toast.makeText(most_buyed.this,"Successfully Placed Your Order",Toast.LENGTH_LONG).show();
                most_buyed_adapter.notifyDataSetChanged();
            }
            public void setClickadd(int abc) {
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                username=user.getEmail();
                RecyclerView.ViewHolder item=mostbuyview.findViewHolderForAdapterPosition(abc);
//                System.out.println(.get(abc).item_image_url);
                String imageurl=most_buyed.get(abc).item_image_url;
                String itemname=most_buyed.get(abc).item_name;
                String itemrate=most_buyed.get(abc).item_rate;
                String itemcategory=most_buyed.get(abc).item_category;
                reference = FirebaseDatabase.getInstance().getReference();
                String id=reference.push().getKey();
                Buyer_Order bo=new Buyer_Order(username,itemname,itemrate,itemcategory,imageurl,id);
                reference.child("BUYER_CART").child(user.getUid()).child(id).setValue(bo);
                Toast.makeText(most_buyed.this,"Successfully Added to cart",Toast.LENGTH_LONG).show();
                most_buyed_adapter.notifyDataSetChanged();
            }
        };

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myref = FirebaseDatabase.getInstance().getReference().child("ITEMS");

        mostbuyview.setHasFixedSize(true);
        mostbuyview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        most_buyed = new ArrayList<>();

        GetDataFromFirebase("VEGETABLES");
        GetDataFromFirebase("BEVERAGES");
        GetDataFromFirebase("SNACKS");
        GetDataFromFirebase("FRUITS");

    }

    public void GetDataFromFirebase(final String item_category){

        Query query = myref.child(item_category);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    final BuyedHelperViewAll iteminfo=new BuyedHelperViewAll("","","","","","",0);
                    iteminfo.setitem_image_url(snapshott.child("item_image_url").getValue().toString());

//                    String seller_id = snapshott.child("seller_username").getValue().toString();

                    iteminfo.setseller_username(snapshott.child("seller_username").getValue().toString());
//                    String a = myref.child("Sellers").child(seller_id).child("Name").getValue().toString();
                    iteminfo.setitem_rate(snapshott.child("item_rate").getValue().toString());
                    iteminfo.setitem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setitem_category(snapshott.child("item_category").getValue().toString());
                    iteminfo.setavailable_units(snapshott.child("available_units").getValue().toString());
                    iteminfo.setItem_buy_count(Integer.parseInt(snapshott.child("item_buy_count").getValue().toString()));

                    most_buyed.add(iteminfo);
                }
                Collections.sort(most_buyed);
                most_buyed_adapter =new MostBuyedViewAllAdapter(getApplicationContext(),most_buyed,onclickInterface);
                mostbuyview.setAdapter(most_buyed_adapter);
                most_buyed_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    public String getData(String seller_id){
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Sellers").child(seller_id);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if(dataSnapshot.hasChild("Name")) {
//
//
//                    String TestValueString = dataSnapshot.child("Name").getValue(String.class);
//                    return TestValueString;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });
//    }
}