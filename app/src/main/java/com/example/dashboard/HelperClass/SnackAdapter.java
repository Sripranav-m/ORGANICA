package com.example.dashboard.HelperClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;

import java.util.ArrayList;
import java.util.List;

public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.SnackHolder> implements Filterable {
    List<SnackHelper> examplelist;
    List<SnackHelper> examplelistfull;


    public SnackAdapter(List<SnackHelper> examplelist) {
        this.examplelist = examplelist;
        examplelistfull = new ArrayList<SnackHelper>(examplelist); {
        }
    }

    @NonNull
    @Override
    public SnackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        SnackHolder holder = new SnackHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull SnackHolder holder, int position) {
        SnackHelper snackHelper = examplelist.get(position);
        holder.image.setImageResource(snackHelper.getImage());
        holder.snack.setText(snackHelper.getName());
        holder.cost.setText(snackHelper.getCost());
//        holder.image.setImageResource(arr[position]);
//        holder.snack.setText("Snack"+position);
//        holder.cost.setText("Cost"+position);
        holder.buy.setText("Buy" + position);

    }

    @Override
    public int getItemCount() {
        return examplelist.size();
    }

    public void filterlist(ArrayList<SnackHelper> filteredlist){
        examplelist = filteredlist;
        notifyDataSetChanged();

    }


    public class SnackHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView snack;
        TextView cost;
        TextView buy;

        public SnackHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            snack = itemView.findViewById(R.id.item_name);
            cost = itemView.findViewById(R.id.item_rate);
            buy = itemView.findViewById(R.id.add_to_cart_button);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<SnackHelper> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(examplelistfull);
            }
            else{
                String filterpattern = charSequence.toString().toLowerCase().trim();
                for(SnackHelper item : examplelistfull){
                    if(item.getName().toLowerCase().contains(filterpattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            examplelist.clear();
            examplelist.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
