package com.example.organica;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Seller#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Seller extends Fragment {

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
    public RecyclerView imprecycler;
    public RecyclerView buyrecycler;
    RecyclerView.Adapter adapter;

    ArrayList<BuyedHelper> most_buyed;

    private  BuyedAdapter most_buyed_adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Seller() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Seller.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Seller newInstance(String param1, String param2) {
        Home_Seller fragment = new Home_Seller();
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
        View view =  inflater.inflate(R.layout.fragment_home_seller, container, false);

        imprecycler = (RecyclerView) view.findViewById(R.id.importance);
        imprecycler();

        buyrecycler = view.findViewById(R.id.buyed);

//        seller_ref = FirebaseDatabase.getInstance().getReference().child("SELLER_ORDERS");
//
//        seller_orders_List = (RecyclerView)  view.findViewById(R.id.seller_recycler_view);
//        seller_orders_List.setLayoutManager(new LinearLayoutManager(getContext()));

//
//        auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//        seller_username=user.getUid();
////        seller_username="jDnfufcnNtfwAOn9v60JGQKfrrx2";
//        recyclerview=(RecyclerView) view.findViewById(R.id.seller_recycler_view);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//        recyclerview.setLayoutManager(layoutManager);
//        recyclerview.setHasFixedSize(true);

        myref= FirebaseDatabase.getInstance().getReference().child("ITEMS");






        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        buyrecycler.setHasFixedSize(true);
        buyrecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        most_buyed = new ArrayList<>();

        GetDataFromFirebase("VEGETABLES");
        GetDataFromFirebase("BEVERAGES");
        GetDataFromFirebase("SNACKS");
        GetDataFromFirebase("FRUITS");

    }

    private void imprecycler() {
        imprecycler.setHasFixedSize(true);
        imprecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<ImpHelper> location = new ArrayList<>();
        location.add(new ImpHelper(R.drawable.organic8, "MONEY", "Why to waste money? Try Our App"));
        location.add(new ImpHelper(R.drawable.organic9, "LOVE ORGANIC", "Wanna be healthy? Go for Organic"));
        location.add(new ImpHelper(R.drawable.organic5, "ALERT", "Reduces your exposure to pesticides"));
        adapter = new ImpAdapter(location);
        imprecycler.setAdapter(adapter);

    }

    public void GetDataFromFirebase(final String item_category){

        Query query = myref.child(item_category);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    BuyedHelper iteminfo=new BuyedHelper("","",0);
                    iteminfo.setItem_image_url(snapshott.child("item_image_url").getValue().toString());
                    iteminfo.setItem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setItem_buy_count(Integer.parseInt(snapshott.child("item_buy_count").getValue().toString()));

                    most_buyed.add(iteminfo);
                }
                Collections.sort(most_buyed);
                most_buyed_adapter =new BuyedAdapter(getActivity().getApplicationContext(),most_buyed);
                buyrecycler.setAdapter(most_buyed_adapter);
                most_buyed_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    private void GetDataFromFirebase(){
//        Query query=myref.child("TO_SELLER_BUYER_DETAILS").child(seller_username);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot snapshott:snapshot.getChildren()){
//                    SellerBuyerInfo iteminfo=new SellerBuyerInfo("","","","","","");
//                    iteminfo.setitem_image_url(snapshott.child("item_image_url").getValue().toString());
//                    iteminfo.setbuyer_username(snapshott.child("buyer_username").getValue().toString());
//                    iteminfo.setitem_rate(snapshott.child("item_rate").getValue().toString());
//                    iteminfo.setitem_name(snapshott.child("item_name").getValue().toString());
//                    iteminfo.setitem_category(snapshott.child("item_category").getValue().toString());
////                    iteminfo.setavailable_units(snapshott.child("available_units").getValue().toString());
//                    Iteminfolist.add(iteminfo);
//                }
//                System.out.println("!@#$%^&*()");
//                sellerBuyerRecyclerAdapter=new SellerBuyerRecyclerAdapter(getActivity().getApplicationContext(),Iteminfolist);
//                recyclerview.setAdapter(sellerBuyerRecyclerAdapter);
//                sellerBuyerRecyclerAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }


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