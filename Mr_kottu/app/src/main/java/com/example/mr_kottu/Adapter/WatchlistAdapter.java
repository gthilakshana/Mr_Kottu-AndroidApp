package com.example.mr_kottu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mr_kottu.Domain.Watchlist;
import com.example.mr_kottu.R;
import com.example.mr_kottu.SingelProuduct;
import com.example.mr_kottu.Watchlist_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder> {

    private List<Watchlist> watchlistList;
    private Context context;

    public WatchlistAdapter(Context context, List<Watchlist> watchlistList) {
        this.context = context;
        this.watchlistList = watchlistList;
    }

    // Other methods remain unchanged...

    @NonNull
    @Override
    public WatchlistAdapter.WatchlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.watchlistcard, parent, false);
        return new WatchlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistViewHolder holder, int position) {
        Watchlist watchlist = watchlistList.get(position);
        holder.bind(watchlist);

        holder.favoriteIcon.setOnClickListener(v -> {
            // Get the clicked item's data
            Watchlist clickedItem = watchlistList.get(holder.getAdapterPosition());

            // Add the clicked item's data to the watchlist
            addToWatchlist(clickedItem, v.getContext());
        });

        holder.itemView.setOnClickListener(v -> {
            String productId = watchlist.getProductId();
            // Your existing code to view individual product details
        });
    }



    private void addToWatchlist(Watchlist clickedItem, Context context) {
        if (context instanceof Watchlist_Activity) {
            ((Watchlist_Activity) context).addToWatchlist(clickedItem, context);
        }
    }


    @Override
    public int getItemCount() {
        return watchlistList.size();
    }

    public void setProductList(List<Watchlist> productList) {
        this.watchlistList = productList;
        notifyDataSetChanged();
    }

    public void updateData(List<Watchlist> watchlistItems) {
        this.watchlistList.addAll(watchlistItems);
        notifyDataSetChanged(); // Notify adapter that data has changed
    }


    public class WatchlistViewHolder extends RecyclerView.ViewHolder {
        ImageView favoriteIcon; // Add this if not present

        // Other views...

        public WatchlistViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon); // Modify with your icon ID
            // Initialize other views...
        }

        public void bind(Watchlist watchlist) {
            // Bind data to views...
        }
    }
}