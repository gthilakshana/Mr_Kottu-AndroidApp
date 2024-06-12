package com.example.mr_kottu.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mr_kottu.Domain.Product2;
import com.example.mr_kottu.Domain.Product3;
import com.example.mr_kottu.R;

import com.example.mr_kottu.SearchActivity;
import com.example.mr_kottu.SingelProuduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter4 extends RecyclerView.Adapter<ProductAdapter4.ProductViewHolder> {

    private List<Product2> productList2;



    public ProductAdapter4() {

    }



    public ProductAdapter4(SearchActivity searchActivity, List<Object> combinedList) {

    }

    public void setProductList(List<Product2> productList) {
        this.productList2 = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_card2, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product2 product = productList2.get(position);
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
        return productList2 != null ? productList2.size() : 0;
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

        public void bind(Product2 product) {
            String imageUrl = product.getProductPrice();

            // Load image using Picasso into ImageView
            Picasso.get().load(imageUrl).into(productImageView);

            // Log the product name (for debugging)
            Log.d("ProductAdapter4", "Product Name: " + product.getProductName());

            // Set product details to their respective TextViews
            productNameTextView.setText(product.getProductName());
            productDescTextView.setText(product.getProductDesc());
            productPriceTextView.setText(product.getImageURL());
        }
    }
}