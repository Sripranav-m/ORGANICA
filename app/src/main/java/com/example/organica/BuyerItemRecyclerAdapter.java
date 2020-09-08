package com.example.organica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BuyerItemRecyclerAdapter extends RecyclerView.Adapter<BuyerItemRecyclerAdapter.ViewHolder> {
    private static final String Tag="RecyclerView";
    private  Context context;
    private ArrayList<ItemInfo> iteminfolist;
    onClickInterface onClickInterface;

    public BuyerItemRecyclerAdapter(Context c,ArrayList<ItemInfo> a,onClickInterface onClickInterface){
        this.context=c;
        this.iteminfolist=a;
        this.onClickInterface = onClickInterface;
    }
    @NonNull
    @Override
    public BuyerItemRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buyer_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.item_name.setText(iteminfolist.get(position).getitem_name());
        holder.item_rate.setText(iteminfolist.get(position).getitem_rate());
        holder.available_units.setText(iteminfolist.get(position).getavailable_units());
        holder.seller_username.setText(iteminfolist.get(position).getseller_username());
        holder.item_category.setText(iteminfolist.get(position).getitem_category());
        Glide.with(context)
                .load(iteminfolist.get(position).getitem_image_url())
                .into(holder.item_image);

        holder.buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClick(position);
            }
        });
        holder.add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClickadd(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return iteminfolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_name;
        ImageView item_image;
        TextView item_rate;
        TextView available_units;
        TextView seller_username;
        TextView item_category;
        Button buy_button;
        Button add_button;
        public ViewHolder(@NonNull View itemview){
            super( itemview);
            item_image=itemview.findViewById(R.id.item_image);
            item_name=itemview.findViewById(R.id.item_name);
            item_rate=itemview.findViewById(R.id.item_rate);
            item_category=itemview.findViewById(R.id.item_category);
            available_units=itemview.findViewById(R.id.available_units);
            seller_username=itemview.findViewById(R.id.seller_username);
            buy_button=itemview.findViewById(R.id.buy_button);
            add_button=itemview.findViewById(R.id.add_button);
        }
    }
}
