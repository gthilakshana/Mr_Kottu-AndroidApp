package com.example.mr_kottu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mr_kottu.databinding.ActivityProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    TextView edit_account,setting_account;

    Button button,imageView;
    TextView nameTextView,EmailUpdateText,emailTextView;

    ActivityProfileBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;

    final int REQUEST_UPDATE_EMAIL = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);


        // Get references to TextView elements
        nameTextView = findViewById(R.id.textView35);

        emailTextView = findViewById(R.id.textView71);


        // Retrieve saved user information
        SharedPreferences preferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        String savedName = preferences.getString("savedName", "");
        String savedEmail = preferences.getString("savedEmail", "");



        // Update TextViews with saved user information
        nameTextView.setText(savedName);
        emailTextView.setText(savedEmail);




        //-my profile text-//
        edit_account = findViewById(R.id.textView33);

        edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, AccountActivity.class));
            }
        });
        //-my profile text-//

        //-Setting text-//
        setting_account = findViewById(R.id.textView22);

        setting_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SettingActivity.class));
            }
        });
        //-Setting text-//

        //- profile image button -//
        button = findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SettingActivity.class));
            }
        });
        //- profile image button -//






        // bottom navigation

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent = null;

                        if (item.getItemId() == R.id.menu_order) {
                            intent = new Intent(ProfileActivity.this, OrderActivity.class);
                        } else if (item.getItemId() == R.id.menu_restaurant) {
                            intent = new Intent(ProfileActivity.this, RestaurantsActivity.class);
                        } else if (item.getItemId() == R.id.menu_home) {
                            intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        } else if (item.getItemId() == R.id.menu_favorite) {
                            intent = new Intent(ProfileActivity.this, FavoriteActivity.class);
                        } else if (item.getItemId() == R.id.menu_profile) {

                        }

                        if (intent != null) {
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish(); // Finish the current activity after starting the new one
                            return true;
                        }

                        return false;
                    }
                });
        // bottom navigation




    }
}
