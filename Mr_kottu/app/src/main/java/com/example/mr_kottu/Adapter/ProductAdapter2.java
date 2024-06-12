package com.example.mr_kottu.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr_kottu.Domain.PopulerItems;
import com.example.mr_kottu.Domain.Product;
import com.example.mr_kottu.R;

import java.util.List;

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ProductViewHolder>  {

    private List<PopulerItems> productList;

    public ProductAdapter2(List<PopulerItems> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        PopulerItems product = productList.get(position);

        // Set the data to the views in the ViewHolder
        holder.productNameTextView.setText(product.getName());
        holder.productImageView.setImageResource(product.getImageResource());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImage1);
            productNameTextView = itemView.findViewById(R.id.textView43);
        }
    }
}