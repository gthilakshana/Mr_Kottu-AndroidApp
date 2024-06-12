package com.example.mr_kottu.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mr_kottu.Domain.Product;
import com.example.mr_kottu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class searchAdaptor extends RecyclerView.Adapter<searchAdaptor.ProductViewHolder> {

    private Context context;
    private ArrayList<Product> productList;

    public searchAdaptor(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_card3, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImageView;
        private TextView productNameTextView;
        private TextView productDescTextView;
        private TextView productPriceTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImage);
            productNameTextView = itemView.findViewById(R.id.productName);
            productDescTextView = itemView.findViewById(R.id.productDesc);
            productPriceTextView = itemView.findViewById(R.id.productPrice);
        }

        public void bind(Product product) {
            String imageUrl = product.getImageURL();

            // Load image using Picasso into ImageView
            Picasso.get().load(imageUrl).into(productImageView);

            // Log the product name (for debugging)
            Log.d("SearchAdapter", "Product Name: " + product.getProductName());

            // Set product details to their respective TextViews
            productNameTextView.setText(product.getProductName());
            productDescTextView.setText(product.getProductDesc());
            productPriceTextView.setText(product.getProductPrice());
        }
    }
}
