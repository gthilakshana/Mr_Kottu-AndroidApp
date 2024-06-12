package com.example.mrkottu_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Admin_loginActivity extends AppCompatActivity {

    private EditText editTextUseremail, editTextPassword;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        editTextUseremail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_btn);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUseremail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                authenticateAdmin(username, password);
            }
        });
    }

    // Inside your Admin_loginActivity

    private void authenticateAdmin(final String username, final String password) {
        String userEmail = "gavrawavanniarachchi@gmail.com";
        String userPassword = "gavrawa@#";

        if (username.equals(userEmail) && password.equals(userPassword)) {
            // Show a progress dialog while loading
            ProgressDialog progressDialog = new ProgressDialog(Admin_loginActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false); // Prevent dismissing on touch outside
            progressDialog.show();

            // Simulate a delay to mimic loading time (remove this in your actual app)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss(); // Dismiss the progress dialog
                    Intent intent = new Intent(Admin_loginActivity.this, AdminDashboard.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000); // 2 seconds delay (change this to suit your loading time)
        } else {
            Toast.makeText(Admin_loginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
        }
    }


}

