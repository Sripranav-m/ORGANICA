package com.example.organica;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView

public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String Tag="RecyclerView";
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView content;
        TextView heading;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.uploadedimage);
            content=itemView.findViewById(R.id.content);
            heading=itemView.findViewById(R.id.heading);

        }
    }
}
