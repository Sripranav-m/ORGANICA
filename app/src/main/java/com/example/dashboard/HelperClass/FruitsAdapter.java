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

public class FruitsAdapter extends RecyclerView.Adapter<FruitsAdapter.FruitsHolder> {
    ArrayList<FruitsHelper> examplelist;

    public FruitsAdapter(ArrayList<FruitsHelper> examplelist) {
        this.examplelist = examplelist;
    }

    @NonNull
    @Override
    public FruitsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        FruitsHolder holder = new FruitsHolder (view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull FruitsHolder holder, int position) {
        FruitsHelper fruitsHelper = examplelist.get(position);
        holder.image.setImageResource(fruitsHelper.getImage());
        holder.snack.setText(fruitsHelper.getName());
        holder.cost.setText(fruitsHelper.getCost());
//        holder.image.setImageResource(arr[position]);
//        holder.snack.setText("Snack"+position);
//        holder.cost.setText("Cost"+position);
        holder.buy.setText("Buy" + position);

    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }
    public void filterlist(ArrayList<FruitsHelper> filteredlist){
        examplelist = filteredlist;
        notifyDataSetChanged();

    }
    public class FruitsHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView snack;
        TextView cost;
        TextView buy;

        public FruitsHolder (@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            snack = itemView.findViewById(R.id.item_name);
            cost = itemView.findViewById(R.id.item_rate);
            buy = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}