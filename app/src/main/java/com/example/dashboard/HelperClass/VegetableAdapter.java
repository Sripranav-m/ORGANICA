package com.example.dashboard.HelperClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.VegetableHolder> {
    int arr[];

    public VegetableAdapter(int[] arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public VegetableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view,parent,false);
        VegetableHolder holder = new VegetableHolder (view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull VegetableHolder holder, int position) {
        holder.image.setImageResource(arr[position]);
        holder.snack.setText("Vegetable"+position);
        holder.cost.setText("Cost"+position);
        holder.buy.setText("Buy"+position);

    }

    @Override
    public int getItemCount() {
        return arr.length;
    }
    public class VegetableHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView snack;
        TextView cost;
        TextView buy;

        public VegetableHolder (@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            snack = itemView.findViewById(R.id.item_name);
            cost = itemView.findViewById(R.id.item_rate);
            buy = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}