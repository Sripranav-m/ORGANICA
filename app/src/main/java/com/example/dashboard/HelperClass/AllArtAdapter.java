package com.example.dashboard.HelperClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;

public class AllArtAdapter extends RecyclerView.Adapter<AllArtAdapter.AllArtHolder> {
    int arr[];

    public AllArtAdapter(int[] arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public AllArtHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card,parent,false);
        AllArtHolder holder = new AllArtHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull AllArtHolder holder, int position) {
        holder.image.setImageResource(arr[position]);
        holder.snack.setText("Snack"+position);


    }

    @Override
    public int getItemCount() {
        return arr.length;
    }
    public class AllArtHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView snack;


        public AllArtHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pic);
            snack = itemView.findViewById(R.id.snack);

        }
    }
}
