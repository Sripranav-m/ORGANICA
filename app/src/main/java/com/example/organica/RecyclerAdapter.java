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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String Tag="RecyclerView";
    private  Context context;
    private ArrayList<ArticlesInfo> articlesinfolist;

    public RecyclerAdapter(Context c,ArrayList<ArticlesInfo> a){
        this.context=c;
        this.articlesinfolist=a;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_card,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(articlesinfolist.get(position).getusername());
        holder.heading.setText(articlesinfolist.get(position).getheading());
        holder.content.setText(articlesinfolist.get(position).getcontent());
        Glide.with(context)
                .load(articlesinfolist.get(position).getimageurl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articlesinfolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView username;
        TextView heading;
        TextView content;

        public ViewHolder(@NonNull View itemview){
            super( itemview);
            image=itemview.findViewById(R.id.image);
            username=itemview.findViewById(R.id.username);
            heading=itemview.findViewById(R.id.heading);
            content=itemview.findViewById(R.id.content);

        }
    }
}
