package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mr_kottu.databinding.ActivityMain2Binding;
import com.example.mr_kottu.databinding.ActivitySplashBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    Handler handler = new Handler();

    ActivitySplashBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        binding= ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity2.class);
                startActivity(intent);
                finish();



            }
        },3000);







    }
}