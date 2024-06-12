package com.example.mr_kottu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        // bottom navigation

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent = null;

                        if (item.getItemId() == R.id.menu_order) {
                            intent = new Intent(FavoriteActivity.this, OrderActivity.class);
                        } else if (item.getItemId() == R.id.menu_restaurant) {
                            intent = new Intent(FavoriteActivity.this, RestaurantsActivity.class);
                        } else if (item.getItemId() == R.id.menu_home) {
                            intent = new Intent(FavoriteActivity.this, HomeActivity.class);
                        } else if (item.getItemId() == R.id.menu_favorite) {

                        } else if (item.getItemId() == R.id.menu_profile) {
                            intent = new Intent(FavoriteActivity.this, ProfileActivity.class);
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