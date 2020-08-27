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


public class ShowArticles extends AppCompatActivity {
    RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<ArticlesInfo> Articlesinfolist;
    private RecyclerAdapter recyclerAdapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_articles);
        recyclerview=(RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        myref=FirebaseDatabase.getInstance().getReference();
        Articlesinfolist=new ArrayList<>();
        clearall();
        GetDataFromFirebase();
    }
    private void GetDataFromFirebase(){
        Query query=myref.child("ARTICLES");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearall();
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    ArticlesInfo articlesinfo=new ArticlesInfo("","","","");
                    articlesinfo.setimageurl(snapshott.child("imageurl").getValue().toString());
                    System.out.println("============================");
                    System.out.println(snapshott.child("imageurl").getValue().toString());
                    System.out.println("============================");
                    articlesinfo.setusername(snapshott.child("username").getValue().toString());
                    articlesinfo.setheading(snapshott.child("heading").getValue().toString());
                    articlesinfo.setcontent(snapshott.child("content").getValue().toString());
                    Articlesinfolist.add(articlesinfo);
                }
                recyclerAdapter=new RecyclerAdapter(getApplicationContext(),Articlesinfolist);
                recyclerview.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void clearall(){
        if(Articlesinfolist!=null){
            Articlesinfolist.clear();
            if(recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        else{
            Articlesinfolist=new ArrayList<>();
        }
    }
}