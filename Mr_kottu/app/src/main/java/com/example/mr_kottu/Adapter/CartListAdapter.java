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

import com.example.mr_kottu.Domain.Cart;
import com.example.mr_kottu.R;
import com.example.mr_kottu.SingelProuduct;
import com.squareup.picasso.Picasso;
import java.util.List;

public class  CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private List<Cart> productList;
    private LayoutInflater inflater;

    public CartListAdapter(Context context, List<Cart> productList) {
        this.productList = productList;
        inflater = LayoutInflater.from(context);
    }

    public void setProductList(List<Cart> updatedProductList) {
        this.productList = updatedProductList;
        notifyDataSetChanged(); // Refresh the RecyclerView
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart product = productList.get(position);
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

    public List<Cart> getProductList() {
        return productList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView;
        TextView productDescTextView;
        TextView productPriceTextView;
        TextView productQtyTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.Image);
            productNameTextView = itemView.findViewById(R.id.ProductName);
            productDescTextView = itemView.findViewById(R.id.Description);
            productPriceTextView = itemView.findViewById(R.id.Price);
            productQtyTextView = itemView.findViewById(R.id.Quintity);
        }

        public void bind(Cart product) {
            String imageUrl = product.getImageURL();

            // Load image using Picasso into ImageView
            Picasso.get().load(imageUrl).into(productImageView);

            // Set product details to their respective TextViews
            productNameTextView.setText(product.getProductName());
            productDescTextView.setText(product.getProductDesc());
            productPriceTextView.setText(product.getProductPrice());
            productQtyTextView.setText(product.getQty());
        }
    }
}
