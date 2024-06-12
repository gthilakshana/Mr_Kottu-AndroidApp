package com.example.mr_kottu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SingelProuduct extends AppCompatActivity {

Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singel_prouduct);

        //-page load button-//
        button = findViewById(R.id.button8);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingelProuduct.this, HomeActivity.class));
            }
        });
        //-page load button-//


        // Retrieve the product ID from the intent
        String productId = getIntent().getStringExtra("productId");

        Log.d("ok","ok"+productId);




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference UsersCollection = db.collection("Product");
        CollectionReference UsersCollection1 = db.collection("Product2");
        CollectionReference UsersCollection3 = db.collection("Popular picks Product");

        CollectionReference cartCollection =  db.collection("Cart");

        String email  = EmailHolder.getUserEmail();


        //------------ product line2 -------------//

        UsersCollection.whereEqualTo("productId",productId)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            if (task.getResult() !=null && !task.getResult().isEmpty()){

                                DocumentSnapshot userDoc = task.getResult().getDocuments().get(0);


                                String image = userDoc.getString("imageURL");
                                String productdesc = userDoc.getString("productDesc");
                                String productname = userDoc.getString("productName");
                                String price = userDoc.getString("productPrice");

                                String priceString = userDoc.getString("productPrice");




                                TextView productName = findViewById(R.id.textView17);
                                productName.setText(productname);


                                TextView productDesc = findViewById(R.id.textView18);
                                productDesc.setText(productdesc);

                                TextView productPrice = findViewById(R.id.textView24);
                                productPrice.setText(price);

                                ImageView imageproduct =findViewById(R.id.imageView9);
                                imageproduct.setImageURI(Uri.parse(image));

                                ImageView imageProduct = findViewById(R.id.imageView9);





                                String imageUrl = "your_image_url_here";


                                Picasso.get().load(image).into(imageProduct);



                            }else {

                            }
                        }else {

                        }
                    }
                });
        //------------ product line2 -------------//


        //------------ product line1 -------------//


        UsersCollection1.whereEqualTo("productId",productId)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            if (task.getResult() !=null && !task.getResult().isEmpty()){

                                DocumentSnapshot userDoc = task.getResult().getDocuments().get(0);


                                String image = userDoc.getString("imageURL");
                                String productdesc = userDoc.getString("productDesc");
                                String productname = userDoc.getString("productName");
                                String price = userDoc.getString("productPrice");

                                String priceString = userDoc.getString("productPrice");




                                TextView productName = findViewById(R.id.textView17);
                                productName.setText(productname);


                                TextView productDesc = findViewById(R.id.textView18);
                                productDesc.setText(productdesc);

                                TextView productPrice = findViewById(R.id.textView24);
                                productPrice.setText(price);

                                ImageView imageproduct =findViewById(R.id.imageView9);
                                imageproduct.setImageURI(Uri.parse(image));

                                ImageView imageProduct = findViewById(R.id.imageView9);





                                String imageUrl = "your_image_url_here";


                                Picasso.get().load(image).into(imageProduct);



                            }else {

                            }
                        }else {

                        }
                    }
                });


        //------------ product line1 -------------//


        //------------ Popular picks Product -------------//


        UsersCollection3.whereEqualTo("productId",productId)

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            if (task.getResult() !=null && !task.getResult().isEmpty()){

                                DocumentSnapshot userDoc = task.getResult().getDocuments().get(0);


                                String image = userDoc.getString("imageURL");
                                String productdesc = userDoc.getString("productDesc");
                                String productname = userDoc.getString("productName");
                                String price = userDoc.getString("productPrice");

                                String priceString = userDoc.getString("productPrice");




                                TextView productName = findViewById(R.id.textView17);
                                productName.setText(productname);


                                TextView productDesc = findViewById(R.id.textView18);
                                productDesc.setText(productdesc);

                                TextView productPrice = findViewById(R.id.textView24);
                                productPrice.setText(price);

                                ImageView imageproduct =findViewById(R.id.imageView9);
                                imageproduct.setImageURI(Uri.parse(image));

                                ImageView imageProduct = findViewById(R.id.imageView9);





                                String imageUrl = "your_image_url_here";


                                Picasso.get().load(image).into(imageProduct);



                            }else {

                            }
                        }else {

                        }
                    }
                });


        //------------ Popular picks Product -------------//










        //----------------- calculate single product ------------------//


        TextView textViewQuantity = findViewById(R.id.textViewQuantity);
        Button buttonIncrement = findViewById(R.id.buttonIncrement);
        Button buttonDecrement = findViewById(R.id.buttonDecrement);

        final int[] quantity = {1}; // Initial quantity

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity[0]++;
                textViewQuantity.setText(String.valueOf(quantity[0]));


                TextView productPrice = findViewById(R.id.textView24);

                UsersCollection.whereEqualTo("productId",productId)

                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    if (task.getResult() !=null && !task.getResult().isEmpty()){

                                        DocumentSnapshot userDoc = task.getResult().getDocuments().get(0);




                                        String priceString = userDoc.getString("productPrice");

                                        if (priceString != null && !priceString.isEmpty()) {
                                            try {
                                                int price = Integer.parseInt(priceString);

                                                // Calculate total price
                                                int totalPrice = price * quantity[0];

                                                // Update the TextView with the new total price
                                                productPrice.setText(String.valueOf(totalPrice));

                                            } catch (NumberFormatException e) {

                                                e.printStackTrace();
                                            }
                                        }



                                    }else {

                                    }
                                }else {

                                }
                            }
                        });


            }
        });

        buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity[0] > 1) {
                    quantity[0]--;
                    textViewQuantity.setText(String.valueOf(quantity[0]));
                    // Add your logic to handle quantity decrement




                    TextView productPrice = findViewById(R.id.textView24);

                    UsersCollection.whereEqualTo("productId",productId)

                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        if (task.getResult() !=null && !task.getResult().isEmpty()){

                                            DocumentSnapshot userDoc = task.getResult().getDocuments().get(0);




                                            String priceString = userDoc.getString("productPrice");

                                            if (priceString != null && !priceString.isEmpty()) {
                                                try {
                                                    int price = Integer.parseInt(priceString);

                                                    // Calculate total price
                                                    int totalPrice = price * quantity[0];

                                                    // Update the TextView with the new total price
                                                    productPrice.setText(String.valueOf(totalPrice));

                                                } catch (NumberFormatException e) {

                                                    e.printStackTrace();
                                                }
                                            }



                                        }else {

                                        }
                                    }else {

                                    }
                                }
                            });











                }
            }
        });

          //----------------- calculate single product ------------------//




        Button add_cart_btn = findViewById(R.id.button5);

        add_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String email = user.getEmail();

                    UsersCollection.whereEqualTo("productId", productId)
                            .get()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    if (task.getResult() != null && !task.getResult().isEmpty()) {
                                        DocumentSnapshot userDoc = task.getResult().getDocuments().get(0);


                                        String productName = ((TextView) findViewById(R.id.textView17)).getText().toString();
                                        String productDesc = ((TextView) findViewById(R.id.textView18)).getText().toString();
                                        String productPrice = ((TextView) findViewById(R.id.textView24)).getText().toString();
                                        String productQty = ((TextView) findViewById(R.id.textViewQuantity)).getText().toString();

                                        String imageURL = userDoc.getString("imageURL");

                                        Map<String, Object> cartItem = new HashMap<>();
                                        cartItem.put("productName", productName);
                                        cartItem.put("productDesc", productDesc);
                                        cartItem.put("productPrice", productPrice);
                                        cartItem.put("productQty", productQty);
                                        cartItem.put("imgURL", imageURL);
                                        cartItem.put("UserEmail", email);

                                        cartCollection.add(cartItem)
                                                .addOnSuccessListener(documentReference -> {
                                                    Toast.makeText(SingelProuduct.this, "Successfully added to cart", Toast.LENGTH_SHORT).show();
                                                })
                                                .addOnFailureListener(e -> {
                                                    Toast.makeText(SingelProuduct.this, "Failed to add to cart: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                });
                                    } else {

                                    }
                                } else {
                                    Toast.makeText(SingelProuduct.this, "Error checking email" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {

                }
            }
        });










    }
}