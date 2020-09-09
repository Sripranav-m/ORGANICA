package com.example.organica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link } subclass.
 * Use the {@link ToSellerBuyerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToSellerBuyerFragment extends Fragment {

    public String item_category;
    private DatabaseReference myref;
    private ArrayList<SellerBuyerInfo> Iteminfolist;
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
   public RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToSellerBuyerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToSellerBuyerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToSellerBuyerFragment newInstance(String param1, String param2) {
        ToSellerBuyerFragment fragment = new ToSellerBuyerFragment();
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
        View view =  inflater.inflate(R.layout.fragment_to_seller_buyer, container, false);

        recyclerView = view.findViewById(R.id.to_seller_buyer_recycler);

//        seller_ref = FirebaseDatabase.getInstance().getReference().child("SELLER_ORDERS");
//
//        seller_orders_List = (RecyclerView)  view.findViewById(R.id.seller_recycler_view);
//        seller_orders_List.setLayoutManager(new LinearLayoutManager(getContext()));

        myref = FirebaseDatabase.getInstance().getReference();


        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        seller_username=user.getUid();

//        seller_username="jDnfufcnNtfwAOn9v60JGQKfrrx2";




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Iteminfolist = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        GetDataFromFirebase();


    }

    private void GetDataFromFirebase(){
        Query query=myref.child("TO_SELLER_BUYER_DETAILS").child(seller_username);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshott:snapshot.getChildren()){
                    SellerBuyerInfo iteminfo=new SellerBuyerInfo("","","","","");
                    iteminfo.setitem_image_url(snapshott.child("item_image_url").getValue().toString());
                    iteminfo.setbuyer_username(snapshott.child("buyer_username").getValue().toString());
                    iteminfo.setitem_rate(snapshott.child("item_rate").getValue().toString());
                    iteminfo.setitem_name(snapshott.child("item_name").getValue().toString());
                    iteminfo.setitem_category(snapshott.child("item_category").getValue().toString());
//                    iteminfo.setavailable_units(snapshott.child("available_units").getValue().toString());
                    Iteminfolist.add(iteminfo);
                }
//                System.out.println("!@#$%^&*()");
                sellerBuyerRecyclerAdapter=new SellerBuyerRecyclerAdapter(getActivity().getApplicationContext(),Iteminfolist);
                recyclerView.setAdapter(sellerBuyerRecyclerAdapter);
                sellerBuyerRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}