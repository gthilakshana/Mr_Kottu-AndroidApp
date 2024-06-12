package com.example.mr_kottu.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr_kottu.Domain.Product;
import com.example.mr_kottu.Domain.Product3;
import com.example.mr_kottu.R;
import com.example.mr_kottu.SearchActivity;
import com.example.mr_kottu.SingelProuduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter3 extends RecyclerView.Adapter<ProductAdapter3.ProductViewHolder> {

    private List<Product3> productList;


    public ProductAdapter3() {

    }




    public void setProductList(List<Product3> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_card3, parent, false);
        return new ProductViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product3 product = productList.get(position);
        holder.bind(product);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productId = product.getproductId();


                Intent intent = new Intent(v.getContext(), SingelProuduct.class);
                intent.putExtra("productId", productId);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
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
            productDescTextView = itemView.findViewById(R.id.Description);
            productPriceTextView = itemView.findViewById(R.id.price);
        }

        public void bind(Product3 product) {
            String imageUrl = product.getProductPrice();

            // Load image using Picasso into ImageView
            Picasso.get().load(imageUrl).into(productImageView);

            // Log the product name (for debugging)
            Log.d("ProductAdapter3", "Product Name: " + product.getProductName());

            // Set product details to their respective TextViews
            productNameTextView.setText(product.getProductName());
            productDescTextView.setText(product.getProductDesc());
            productPriceTextView.setText(product.getImageURL());
        }
    }
}