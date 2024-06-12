package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mr_kottu.databinding.ActivityLoginBinding;
import com.example.mr_kottu.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    Button signIn;

    ActivityMain2Binding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        binding= ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {

            Intent intent = new Intent(MainActivity2.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {

            binding = ActivityMain2Binding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            signIn = findViewById(R.id.button2);

            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                }
            });


        }
    }
}