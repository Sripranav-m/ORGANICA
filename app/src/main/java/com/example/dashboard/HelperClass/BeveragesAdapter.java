package com.example.dashboard.HelperClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;

import java.util.ArrayList;

public class BeveragesAdapter extends RecyclerView.Adapter<BeveragesAdapter.BeveragesHolder> {
    ArrayList<BeveragesHelper> examplelist;

    public BeveragesAdapter(ArrayList<BeveragesHelper> examplelist) {
        this.examplelist = examplelist;
    }

    @NonNull
    @Override
    public BeveragesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        BeveragesHolder holder = new BeveragesHolder (view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull BeveragesHolder holder, int position) {
        BeveragesHelper beveragesHelper = examplelist.get(position);
        holder.image.setImageResource(beveragesHelper.getImage());
        holder.snack.setText(beveragesHelper.getName());
        holder.cost.setText(beveragesHelper.getCost());
//        holder.image.setImageResource(arr[position]);
//        holder.snack.setText("Snack"+position);
//        holder.cost.setText("Cost"+position);
        holder.buy.setText("Buy" + position);
    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }
    public void filterlist(ArrayList<BeveragesHelper> filteredlist){
        examplelist = filteredlist;
        notifyDataSetChanged();

    }
    public class BeveragesHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView snack;
        TextView cost;
        TextView buy;

        public BeveragesHolder (@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            snack = itemView.findViewById(R.id.item_name);
            cost = itemView.findViewById(R.id.item_rate);
            buy = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}