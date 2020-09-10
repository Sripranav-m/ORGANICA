package com.example.dashboard.HelperClass;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;

import java.util.ArrayList;

public class BuyedAdapter extends RecyclerView.Adapter<BuyedAdapter.BuyedHolder> {

    ArrayList<BuyedHelper> location;


    public BuyedAdapter(ArrayList<BuyedHelper> location) {
        this.location = location;
    }

    @NonNull
    @Override
    public BuyedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyed_card,parent,false);
        BuyedHolder holding = new BuyedHolder(view);
        return holding;
    }

    @Override
    public void onBindViewHolder(@NonNull BuyedAdapter.BuyedHolder holder, int position) {
        BuyedHelper BuyedHelper = location.get(position);
        holder.image.setImageResource(BuyedHelper.getImage());
        holder.title.setText(BuyedHelper.getTitle());




    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    public static class BuyedHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;

        public BuyedHolder(@NonNull View itemView) {
            super(itemView);
            image =  itemView.findViewById(R.id.buy_image1);
            title =  itemView.findViewById(R.id.obj);

        }
    }


}
