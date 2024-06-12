package com.example.mr_kottu.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mr_kottu.Domain.Product;
import com.example.mr_kottu.Domain.Product2;
import com.example.mr_kottu.Domain.search;
import com.example.mr_kottu.R;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @androidx.annotation.NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    // ... (other methods and variables remain unchanged)

    // ...

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImageView;
        private TextView productNameTextView;
        private TextView productDescTextView;
        private TextView productPriceTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImage);
            productNameTextView = itemView.findViewById(R.id.productName);
            productDescTextView = itemView.findViewById(R.id.Description);
            productPriceTextView = itemView.findViewById(R.id.price);
        }

        public void bind(Object item) {
            if (item instanceof Product) {
                Product product = (Product) item;

                // Set values from Product object to respective views
                productNameTextView.setText(product.getProductName());
                productDescTextView.setText(product.getProductDesc());
                productPriceTextView.setText(product.getProductPrice());

                // Load image using Picasso into ImageView
                String imageUrl = product.getImageURL();
                Picasso.get().load(imageUrl).into(productImageView);

                // Log for debugging
                Log.d("ProductAdapter4", "Product Name: " + product.getProductName());
            } else if (item instanceof Product2) {
                Product2 product2 = (Product2) item;

                // Set values from Product2 object to respective views
                productNameTextView.setText(product2.getProductName());
                productDescTextView.setText(product2.getProductDesc());
                productPriceTextView.setText(product2.getImageURL());

                // Load image using Picasso into ImageView
                String imageUrl = product2.getProductPrice();
                Picasso.get().load(imageUrl).into(productImageView);

                // Log for debugging
                Log.d("ProductAdapter4", "Product2 Name: " + product2.getProductName());
            }
        }
    }
}
