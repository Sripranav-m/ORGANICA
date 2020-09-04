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

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {
    private static final String Tag="RecyclerView";
    private  Context context;
    private ArrayList<ItemInfo> iteminfolist;

    public ItemRecyclerAdapter(Context c,ArrayList<ItemInfo> a){
        this.context=c;
        this.iteminfolist=a;
    }

    @NonNull
    @Override
    public ItemRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_name.setText(iteminfolist.get(position).getitem_name());
        holder.item_rate.setText(iteminfolist.get(position).getitem_rate());
        holder.item_category.setText(iteminfolist.get(position).getitem_category());
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
        TextView item_category;
        TextView seller_username;

        public ViewHolder(@NonNull View itemview){
            super( itemview);
            item_image=itemview.findViewById(R.id.item_image);
            item_name=itemview.findViewById(R.id.item_name);
            item_rate=itemview.findViewById(R.id.item_rate);
            item_category=itemview.findViewById(R.id.item_category);
            seller_username=itemview.findViewById(R.id.seller_username);

        }
    }
}
