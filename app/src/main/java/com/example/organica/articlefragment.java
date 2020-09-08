package com.example.organica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link articlefragment} factory method to
 * create an instance of this fragment.
 */
public class articlefragment extends Fragment implements View.OnClickListener {

    private View privateArticlesView;
    private RecyclerView articlesList;
    private DatabaseReference articles_ref;
    public Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        privateArticlesView =  inflater.inflate(R.layout.fragment_articles, container, false);
        button = privateArticlesView.findViewById(R.id.buttonadd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), AddArticle.class);
                startActivity(intent);
            }
        });

        articles_ref = FirebaseDatabase.getInstance().getReference().child("ARTICLES");

        articlesList = (RecyclerView)  privateArticlesView.findViewById(R.id.articles_recycler);
        articlesList.setLayoutManager(new LinearLayoutManager(getContext()));


        return  privateArticlesView;
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ArticlesInfo> options = new FirebaseRecyclerOptions.Builder<ArticlesInfo>()
                .setQuery(articles_ref, ArticlesInfo.class)
                .build();

        FirebaseRecyclerAdapter<ArticlesInfo, ArticlesViewHolder> adapter =
                new FirebaseRecyclerAdapter<ArticlesInfo, ArticlesViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ArticlesViewHolder holder, int position, @NonNull ArticlesInfo model) {

                        String id = getRef(position).getKey();
                        articles_ref.child(id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                final String retImage = snapshot.child("imageurl").getValue().toString();

                                Picasso.get().load(retImage).into(holder.image);
                                final String retUsername = snapshot.child("username").getValue().toString();
                                final String retHeading = snapshot.child("heading").getValue().toString();
                                final String retContent = snapshot.child("content").getValue().toString();

                                holder.username.setText(retUsername);
                                holder.content.setText(retContent);
                                holder.heading.setText(retHeading);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card, parent, false);
                        return new ArticlesViewHolder(view);
                    }
                };


        articlesList.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public void onClick(View view) {

    }

    public static class ArticlesViewHolder extends RecyclerView.ViewHolder{

        TextView heading,username,content;
        ImageView image;
        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.heading);
            username = itemView.findViewById(R.id.username);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.image);
        }
    }
}