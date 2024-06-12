package com.example.mrkottu_admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        firebaseAuth = FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firebaseAuth.getCurrentUser() != null) {

                    Intent intent = new Intent(Splash.this, AdminDashboard.class);
                    startActivity(intent);
                } else {

                    Intent intent = new Intent(Splash.this, Admin_loginActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        }, 3000);
    }
}
