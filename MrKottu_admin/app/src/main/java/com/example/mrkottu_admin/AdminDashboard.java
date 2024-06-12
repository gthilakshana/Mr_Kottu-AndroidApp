package com.example.mrkottu_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.request.Request;
import com.google.android.gms.common.api.Response;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class AdminDashboard extends AppCompatActivity {

    ImageButton imageButton;


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        //----- Product1 -----//

        imageButton = findViewById(R.id.imageButton3);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, ProductAdd.class));
            }
        });


        //----- product2-----//

        imageButton = findViewById(R.id.imageButton4);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, ProductAdd2.class));
            }
        });

        //----- Popular_picks_Product-----//

        imageButton = findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, Popular_picks_Product.class));
            }
        });
















    }
}