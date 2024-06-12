package com.example.mr_kottu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mr_kottu.Adapter.ProductAdapter;
import com.example.mr_kottu.Adapter.ProductAdapter2;
import com.example.mr_kottu.Domain.PopulerItems;
import com.example.mr_kottu.Domain.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);


        //--next page load button --//
        button = findViewById(R.id.button15);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantsActivity.this,HomeActivity.class));
            }
        });
        //--next page load button --//



        // Create a list of products
        List<PopulerItems> productList1 = new ArrayList<>();
        productList1.add(new PopulerItems("293 Thalapathpitiya Rd, Nugegoda", R.drawable.nugegoda1jpg));
        productList1.add(new PopulerItems("WV5X+8HX, Sri Jayawardenepura Kotte", R.drawable.jayawardanapura));
        productList1.add(new PopulerItems("147 Avissawella Rd, Pannipitiya 10230", R.drawable.pannipitiya));
        productList1.add(new PopulerItems("V23Q+59G, High Level Rd, Godagama ", R.drawable.godagama));
        productList1.add(new PopulerItems("371 Kaduwela Rd, Battaramulla 10120 ", R.drawable.battaramulla));
        productList1.add(new PopulerItems("319 Colombo - Horana Rd, Piliyandala ", R.drawable.piliyandara));
        productList1.add(new PopulerItems("eople’s leasing, Kandy Rd, Kelaniya ", R.drawable.kottu_2));
        productList1.add(new PopulerItems("143 High Level Rd, Maharagama 10280 ", R.drawable.mahargama));
        productList1.add(new PopulerItems("V23Q+59G, High Level Rd, Godagama ", R.drawable.kottu_2));
        // Add more products as needed

        // Set up the RecyclerView
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Set the adapter for the RecyclerView
        ProductAdapter2 adapter2 = new ProductAdapter2(productList1);
        recyclerView1.setAdapter(adapter2);






        // bottom navigation

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent = null;

                        if (item.getItemId() == R.id.menu_order) {
                            intent = new Intent(RestaurantsActivity.this, OrderActivity.class);
                        } else if (item.getItemId() == R.id.menu_restaurant) {

                        } else if (item.getItemId() == R.id.menu_home) {
                            intent = new Intent(RestaurantsActivity.this, HomeActivity.class);
                        } else if (item.getItemId() == R.id.menu_favorite) {
                            intent = new Intent(RestaurantsActivity.this, FavoriteActivity.class);
                        } else if (item.getItemId() == R.id.menu_profile) {
                            intent = new Intent(RestaurantsActivity.this, ProfileActivity.class);
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