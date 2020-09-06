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

public class ImpAdapter extends RecyclerView.Adapter<ImpAdapter.ImpHolder> {

    ArrayList<ImpHelper> location;


    public ImpAdapter(ArrayList<ImpHelper> location) {
        this.location = location;
    }

    @NonNull
    @Override
    public ImpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imp_card,parent,false);
        ImpHolder holding = new ImpHolder(view);
        return holding;
    }

    @Override
    public void onBindViewHolder(@NonNull ImpAdapter.ImpHolder holder, int position) {
        ImpHelper ImpHelper = location.get(position);
        holder.image.setImageResource(ImpHelper.getImage());
        holder.title.setText(ImpHelper.getTitle());
        holder.description.setText(ImpHelper.getDescription());



    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    public static class ImpHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,description;

        public ImpHolder(@NonNull View itemView) {
            super(itemView);
            image =  itemView.findViewById(R.id.image1);
            title =  itemView.findViewById(R.id.image1_heading);
            description =  itemView.findViewById(R.id.image1_description);
        }
    }


}
