package com.example.organica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public BuyerItemRecyclerAdapter(Context c,ArrayList<ItemInfo> a){
        this.context=c;
        this.iteminfolist=a;
    }

    @NonNull
    @Override
    public BuyerItemRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buyer_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_name.setText(iteminfolist.get(position).getitem_name());
        holder.item_rate.setText(iteminfolist.get(position).getitem_rate());
        holder.available_units.setText(iteminfolist.get(position).getavailable_units());
        holder.seller_username.setText(iteminfolist.get(position).getseller_username());
        Glide.with(context)
                .load(iteminfolist.get(position).getitem_image_url())
                .into(holder.item_image);
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

        public ViewHolder(@NonNull View itemview){
            super( itemview);
            item_image=itemview.findViewById(R.id.item_image);
            item_name=itemview.findViewById(R.id.item_name);
            item_rate=itemview.findViewById(R.id.item_rate);
            available_units=itemview.findViewById(R.id.available_units);
            seller_username=itemview.findViewById(R.id.seller_username);

        }
    }
}
