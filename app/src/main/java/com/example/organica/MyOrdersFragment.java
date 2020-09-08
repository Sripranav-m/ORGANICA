package com.example.organica;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class MyOrdersFragment extends Fragment {

    private View privateOrdersView;
    private RecyclerView ordersList;
    private DatabaseReference orders_ref;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        privateOrdersView =  inflater.inflate(R.layout.fragment_my_orders, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String id = user.getUid();

        orders_ref = FirebaseDatabase.getInstance().getReference().child("BUYER_ORDERS").child(id);

        ordersList = (RecyclerView)  privateOrdersView.findViewById(R.id.orders_recycler);
        ordersList.setLayoutManager(new LinearLayoutManager(getContext()));


        return  privateOrdersView;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        onStart();
//    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<OrderInfo> options = new FirebaseRecyclerOptions.Builder<OrderInfo>()
                .setQuery(orders_ref, OrderInfo.class)
                .build();

        FirebaseRecyclerAdapter<OrderInfo, OrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<OrderInfo, OrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final OrdersViewHolder holder, int position, @NonNull OrderInfo model) {

                        String id1 = getRef(position).getKey();

//                        if(orders_ref.child(id1) != null) {
                        orders_ref.child(id1).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                final String retImage = snapshot.child("item_image_url").getValue().toString();

                                Picasso.get().load(retImage).into(holder.image);
                                final String retItemName = snapshot.child("item_name").getValue().toString();
                                final String retItemRate = snapshot.child("item_rate").getValue().toString();
                                final String retCategory = snapshot.child("item_category").getValue().toString();

                                holder.item_name.setText(retItemName);
                                holder.item_rate.setText(retItemRate);
                                holder.item_category.setText(retCategory);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
//                        }
//                        else{
//                            Toast.makeText(getActivity(),"You have not ordered any items",Toast.LENGTH_SHORT).show();
//                        }
                    }



                    @NonNull
                    @Override
                    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyer_myorder_card, parent, false);
                        return new OrdersViewHolder(view);
                    }
                };


        ordersList.setAdapter(adapter);
        adapter.startListening();

    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder{

        TextView item_name,item_rate,item_category;
        ImageView image;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_category = itemView.findViewById(R.id.item_category);
            item_rate = itemView.findViewById(R.id.item_rate);
            image = itemView.findViewById(R.id.item_image);
        }
    }
}