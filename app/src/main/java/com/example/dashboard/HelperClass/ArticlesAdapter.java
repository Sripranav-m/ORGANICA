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

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesHolder> {

    ArrayList<ArticlesHelper> location;


    public ArticlesAdapter(ArrayList<ArticlesHelper> location) {
        this.location = location;
    }

    @NonNull
    @Override
    public ArticlesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyed_card,parent,false);
        ArticlesHolder holding = new ArticlesHolder(view);
        return holding;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ArticlesHolder holder, int position) {
        ArticlesHelper ArticlesHelper = location.get(position);
        holder.image.setImageResource(ArticlesHelper.getImage());
        holder.title.setText(ArticlesHelper.getTitle());




    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    public static class ArticlesHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;

        public ArticlesHolder(@NonNull View itemView) {
            super(itemView);
            image =  itemView.findViewById(R.id.buy_image1);
            title =  itemView.findViewById(R.id.obj);

        }
    }


}
