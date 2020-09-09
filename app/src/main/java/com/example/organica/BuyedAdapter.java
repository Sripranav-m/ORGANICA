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

public class BuyedAdapter extends RecyclerView.Adapter<BuyedAdapter.ViewHolder> {
    private static final String Tag="RecyclerView";
    private  Context context;
    private ArrayList<BuyedHelper> iteminfolist;

    public BuyedAdapter(Context c, ArrayList<BuyedHelper> a){
        this.context=c;
        this.iteminfolist=a;
    }



    @NonNull
    @Override
    public BuyedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buyed_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_name.setText(iteminfolist.get(position).getItem_name());
        Glide.with(context)
                .load(iteminfolist.get(position).getItem_image_url())
                .into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return iteminfolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_name;
        ImageView item_image;

        public ViewHolder(@NonNull View itemview){
            super( itemview);
            item_image=itemview.findViewById(R.id.buy_image1);
            item_name=itemview.findViewById(R.id.obj);
        }
    }
}
