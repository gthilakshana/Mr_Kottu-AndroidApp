package com.example.mr_kottu;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


import com.example.mr_kottu.Adapter.ProductAdapter;
import com.example.mr_kottu.Adapter.ProductAdapter3;
import com.example.mr_kottu.Adapter.ProductAdapter4;
import com.example.mr_kottu.Domain.Product;
import com.example.mr_kottu.Domain.Product2;
import com.example.mr_kottu.Domain.Product3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    TextView textView, addressTextView;
    ImageView imageView;

    Button button;
    Button button1;

    private RecyclerView recyclerView1,recyclerView2,recyclerView;
    private ProductAdapter3 adapter3;
    private ProductAdapter4 adapter4;
    private ProductAdapter adapter;
    private FirebaseFirestore firebaseFirestore;

    private CollectionReference productsCollection;
    private CollectionReference productsCollection1;
    private CollectionReference productsCollection2;



    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        button = findViewById(R.id.button22);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        button1 = findViewById(R.id.button21);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });

        button = findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProductCart.class));
            }
        });

        addressTextView = findViewById(R.id.textView69);


        SharedPreferences Preferences = getSharedPreferences("LocationPrefs", MODE_PRIVATE);
        String savedAddress = Preferences.getString("savedAddress", "");

        addressTextView.setText(savedAddress);


       /** // Create a list of products
        List<Product> productList1 = new ArrayList<>();
        productList1.add(new Product("Kottu Rotty", R.drawable.rectangle_2878, 1));
        productList1.add(new Product("Kottu Rotty", R.drawable.kottu_2, 2));
        productList1.add(new Product("Kottu Rotty", R.drawable.kottu123, 3));
        productList1.add(new Product("Kottu Rotty", R.drawable.rectangle_2878, 4));
        productList1.add(new Product("Kottu Rotty", R.drawable.rectangle_2878, 5));
        // Add more products as needed

        // Set up the RecyclerView
        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        ProductAdapter adapter1 = new ProductAdapter(productList1);
        recyclerView2.setAdapter(adapter1);**/




      /**  // Create a list of products
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Kottu Rotty1", R.drawable.rectangle_2878, 1));
        productList.add(new Product("Kottu Rotty2", R.drawable.kottu_2, 2));
        productList.add(new Product("Kottu Rotty3", R.drawable.kottu123, 3));
        productList.add(new Product("Kottu Rotty4", R.drawable.rectangle_2878, 4));
        productList.add(new Product("Kottu Rotty5", R.drawable.rectangle_2878, 5));
        // Add more products as needed

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Set the adapter for the RecyclerView
        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int productId) {

                if (productId == 1) {
                    Intent intent = new Intent(HomeActivity.this, SingleProductActivity.class);
                    startActivity(intent);
                }
                if (productId == 2) {
                    Intent intent = new Intent(HomeActivity.this, SingleProductActivity.class);
                    startActivity(intent);
                }

            }
        });**/







        //----- fastDelivery1 food -----//

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapter3 = new ProductAdapter3();
        recyclerView2.setAdapter(adapter3);

        firebaseFirestore = FirebaseFirestore.getInstance();
        productsCollection = firebaseFirestore.collection("Product");

        fetchCounterproductiveFromFirestorm();


        //----- fastDelivery1 food -----//


   //----- fastDelivery2 food -----//


        recyclerView1 = findViewById(R.id.recyclerView11);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapter4 = new ProductAdapter4();
        recyclerView1.setAdapter(adapter4);

        firebaseFirestore = FirebaseFirestore.getInstance();
        productsCollection1 = firebaseFirestore.collection("Product2");

        fetchProductsFromFirestore();

    //----- fastDelivery2 food -----//


    //----- fastDelivery3 food -----//


    recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

    firebaseFirestore = FirebaseFirestore.getInstance();
    productsCollection2 = firebaseFirestore.collection("Popular picks Product");

    fetchProductsFromFirestore1();
}
    //----- fastDelivery3 food -----//






    //----- fastDelivery1 food  -----//
    private void fetchCounterproductiveFromFirestorm() {
        productsCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Product3> productList = new ArrayList<>();
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        String productName = documentSnapshot.getString("productName");
                        String productDesc = documentSnapshot.getString("productDesc");
                        String productPrice = documentSnapshot.getString("productPrice");
                        String imageURL = documentSnapshot.getString("imageURL");
                        String ProductId = documentSnapshot.getString("productId");

                        Product3 product = new Product3(productName, productDesc, productPrice, imageURL,ProductId);
                        productList.add(product);
                    }
                    adapter3.setProductList(productList);
                }
            } else {

            }
        });
    }

        //----- fastDelivery1 food  -----//




    //----- fastDelivery2 food -----//
    private void fetchProductsFromFirestore() {
        productsCollection1.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Product2> productList = new ArrayList<>();
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        String productName = documentSnapshot.getString("productName");
                        String productDesc = documentSnapshot.getString("productDesc");
                        String productPrice = documentSnapshot.getString("productPrice");
                        String imageURL = documentSnapshot.getString("imageURL");
                        String ProductId = documentSnapshot.getString("productId");

                        Product2 product = new Product2(productName, productDesc, productPrice, imageURL, ProductId);
                        productList.add(product);
                    }
                    adapter4.setProductList(productList);
                }
            } else {

            }
        });
    }
        //----- fastDelivery2 food -----//


        //----- fastDelivery3 food -----//
        private void fetchProductsFromFirestore1() {
            productsCollection2.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<Product> productList = new ArrayList<>();
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {
                        for (DocumentSnapshot documentSnapshot : querySnapshot) {
                            String productName = documentSnapshot.getString("productName");
                            String productDesc = documentSnapshot.getString("productDesc");
                            String productPrice = documentSnapshot.getString("productPrice");
                            String imageURL = documentSnapshot.getString("imageURL");
                            String ProductId = documentSnapshot.getString("productId");

                            Product product = new Product(productName, productDesc, productPrice, imageURL, ProductId);
                            productList.add(product);
                        }
                        adapter.setProductList(productList);
                    }
                } else {

                }
            });

            //----- fastDelivery3 food -----//











            /**  // Assuming this code is within your activity or fragment
              for (final Product product : productList1) {
                  // Create an ImageView for each product's image
                  ImageView productImageView = new ImageView(this);

                  // Set the image resource for the ImageView
                  productImageView.setImageResource(product.getImageResourceId());

                  // Set up layout parameters for the ImageView (adjust as per your layout requirements)
                  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                          LinearLayout.LayoutParams.WRAP_CONTENT,
                          LinearLayout.LayoutParams.WRAP_CONTENT
                  );
                  productImageView.setLayoutParams(layoutParams);

                  // Set up a click listener for each product's image
                  productImageView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          // Start SingleProductActivity and pass necessary information
                          Intent intent = new Intent(getApplicationContext(), SingleProductActivity.class);
                          intent.putExtra("productName", product.getName());
                          intent.putExtra("productImage", product.getImageResourceId());
                          startActivity(intent);
                      }
                  });

                  // Add the ImageView to your layout (LinearLayout, GridLayout, etc.)
                  LinearLayout productListLayout = findViewById(R.id.recyclerView2); // Replace with your layout ID
                  productListLayout.addView(productImageView);
              }**/







     // bottom navigation

     BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

     bottomNavigationView.setOnNavigationItemSelectedListener(
             new BottomNavigationView.OnNavigationItemSelectedListener() {
                 @Override
                 public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                     Intent intent = null;

                     if (item.getItemId() == R.id.menu_order) {
                         intent = new Intent(HomeActivity.this, OrderActivity.class);
                     } else if (item.getItemId() == R.id.menu_restaurant) {
                         intent = new Intent(HomeActivity.this, RestaurantsActivity.class);
                     } else if (item.getItemId() == R.id.menu_home) {
                         // Handle 'Home' logic if needed
                     } else if (item.getItemId() == R.id.menu_favorite) {
                         intent = new Intent(HomeActivity.this, FavoriteActivity.class);
                     } else if (item.getItemId() == R.id.menu_profile) {
                         intent = new Intent(HomeActivity.this, ProfileActivity.class);
                     }

                     if (intent != null) {
                         startActivity(intent);
                         overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                         finish();
                         return true;
                     }

                     return false;
                 }
             });
     // bottom navigation

 }

 

}