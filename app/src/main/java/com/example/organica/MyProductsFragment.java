package com.example.organica;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProductsFragment extends Fragment {

    public RecyclerView recyclerview;
    private DatabaseReference myref;
    private ArrayList<ItemInfo> Iteminfolist;
    private ItemRecyclerAdapter itemrecyclerAdapter;
    private Context context;
    public String username;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProductsFragment newInstance(String param1, String param2) {
        MyProductsFragment fragment = new MyProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_products, container, false);

        recyclerview=(RecyclerView) view.findViewById(R.id.seller_recycler_view);

        myref= FirebaseDatabase.getInstance().getReference();

        FirebaseAuth auth;
        auth= FirebaseAuth.getInstance();
        username=auth.getCurrentUser().getUid();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);

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
                    if(snapshott.child("seller_username").getValue().toString().equals(username)) {
                        Iteminfolist.add(iteminfo);
                    }
                }
                itemrecyclerAdapter=new ItemRecyclerAdapter(getActivity().getApplicationContext(),Iteminfolist);
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