package com.example.mr_kottu;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mr_kottu.Adapter.CartListAdapter;
import com.example.mr_kottu.Domain.Cart;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductCart extends AppCompatActivity {


    private CartListAdapter cartListAdapter; // Ensure correct scope for the adapters
    private FirebaseFirestore db;
    private FirebaseFirestore db2;
    private CollectionReference productsCollection;

    private TextView subtotal;
    private  TextView totelview;
    private Double totalPrice;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference cartCollection =  db.collection("Cart");

        // Assuming you have declared 'cartList' somewhere before this point
        List<Cart> cartList = new ArrayList<>(); // Initialize an empty list

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        cartListAdapter = new CartListAdapter(this, cartList);
        recyclerView.setAdapter(cartListAdapter);

        db = FirebaseFirestore.getInstance();
        productsCollection = db.collection("Cart");

        String Email = EmailHolder.getUserEmail();

        fetchProductsFromFirestore();



        button = findViewById(R.id.button26);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductCart.this, HomeActivity.class));
            }
        });




        Button checkbtn = findViewById(R.id.checkoutButton);
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db2 = FirebaseFirestore.getInstance();
                CollectionReference usersOrder = db2.collection("Order");
                CollectionReference userCart = db2.collection("Cart"); // Reference to the "cart" collection

                TextView subtotal = findViewById(R.id.subtotal2);
                String subtotalText = subtotal.getText().toString(); // Get the text content

                TextView totalView = findViewById(R.id.totalPrice2);
                String totalText = totalView.getText().toString(); // Get the text content

                String Email = EmailHolder.getUserEmail();

                Log.d("Total", "Total Quantity: " + Email);


                usersOrder.whereEqualTo("email", Email)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                if (task.getResult() != null && !task.getResult().isEmpty()) {
                                    Map<String, Object> userOrderData = new HashMap<>();

                                    userOrderData.put("email", Email);
                                    userOrderData.put("subtotal", subtotalText); // Store as String
                                    userOrderData.put("total", totalText); // Store as String


                                    usersOrder.add(userOrderData)
                                            .addOnSuccessListener(documentReference -> {
                                                // On successfully adding order data
                                                Toast.makeText(ProductCart.this, "Successfully added order", Toast.LENGTH_SHORT).show();

                                                // Now, delete data from the "cart" collection
                                                userCart.whereEqualTo("UserEmail", Email)
                                                        .get()
                                                        .addOnCompleteListener(cartTask -> {
                                                            if (cartTask.isSuccessful()) {
                                                                for (QueryDocumentSnapshot document : cartTask.getResult()) {
                                                                    document.getReference().delete()
                                                                            .addOnSuccessListener(aVoid -> {
                                                                                // Deletion successful
                                                                                Log.d("Delete", "DocumentSnapshot successfully deleted from cart!");
                                                                            })
                                                                            .addOnFailureListener(e -> {
                                                                                // Deletion failed
                                                                                Log.w("Delete", "Error deleting document from cart", e);
                                                                            });
                                                                }
                                                                // Refresh the cart or fetch updated products from Firestore
                                                                fetchProductsFromFirestore();
                                                            } else {
                                                                Log.d("Delete", "Error getting cart documents: ", cartTask.getException());
                                                            }
                                                        });
                                            })
                                            .addOnFailureListener(e -> {
                                                // Failed to add order data
                                                Toast.makeText(ProductCart.this, "Failed to add order: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            });

                                } else {
                                    // Logic for creating a new order if one doesn't exist
                                    // Similar to the logic inside the previous else block
                                }
                            } else {
                                Toast.makeText(ProductCart.this, "Error checking email: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });







    }




    private void fetchProductsFromFirestore() {


        String Email = EmailHolder.getUserEmail();
        productsCollection.whereEqualTo("UserEmail", Email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Cart> productList = new ArrayList<>();
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        String imageURL = documentSnapshot.getString("imageURL");
                        String productDesc = documentSnapshot.getString("productDesc");
                        String productName = documentSnapshot.getString("productName");

                        String productPrice = documentSnapshot.getString("productPrice");

                        String productQty = documentSnapshot.getString("productQty");

                        Cart product = new Cart(imageURL, productDesc, productName, productPrice, productQty);
                        productList.add(product);
                    }
                    cartListAdapter.setProductList(productList);
                    calculateTotal();
                }
            } else {

            }
        });
    }








    private void calculateTotal() {
        int totalQuantity = 0;
        double totalPrice = 0.0;

        List<Cart> productList = cartListAdapter.getProductList();

        if (productList != null) {
            for (Cart product : productList) {
                String quantityStr = product.getQty();
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    totalQuantity += quantity;

                    String priceStr = product.getProductPrice();
                    if (priceStr != null && !priceStr.trim().isEmpty()) {
                        double price = Double.parseDouble(priceStr.trim());
                        totalPrice += quantity * price;
                    } else {
                        Log.e("Total", "Price string is null or empty for product: " + product.getProductPrice());

                    }
                } catch (NumberFormatException e) {
                    Log.e("Total", "Error parsing quantity or price: " + e.getMessage());
                    Log.e("Total", "Product: " + product.getProductPrice() + ", Quantity: " + quantityStr + ", Price: " + product.getProductPrice());

                }
            }

            Log.d("Total", "Total Quantity: " + totalQuantity);
            Log.d("Total", "Total Price: " + totalPrice);

            // Update Subtotal TextView
            TextView subtotal = findViewById(R.id.subtotal2);
            subtotal.setText(String.valueOf(totalPrice));

            // Calculate Total by adding a fixed value (200 in this case) to Subtotal
            double total = totalPrice + 200;

            // Update Total TextView
            TextView totalView = findViewById(R.id.totalPrice2);
            totalView.setText(String.valueOf(total));
        }
    }











}






