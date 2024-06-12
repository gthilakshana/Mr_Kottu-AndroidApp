package com.example.mr_kottu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.example.mr_kottu.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);

        binding.signup.setOnClickListener(v -> registerUser());
        binding.textView54.setOnClickListener(v -> startActivity(new Intent(SignUpActivity.this, LoginActivity.class)));
    }

    private void registerUser() {
        String name = binding.name.getText().toString().trim();
        String email = binding.email.getText().toString().trim();
        String number = binding.number.getText().toString().trim();
        String password = binding.password.getText().toString().trim();

        if (name.isEmpty()) {
            binding.name.setError("Name cannot be empty");
            return;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.setError("Enter a valid email address");
            return;
        }

        if (number.isEmpty()) {
            binding.number.setError("Number cannot be empty");
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            binding.password.setError("Password should be at least 6 characters long");
            return;
        }

        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    progressDialog.cancel();
                    saveUserData(name, email, number, password);
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish(); // Close this activity
                })
                .addOnFailureListener(e -> {
                    progressDialog.cancel();
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        saveUserDetails(name, email, number, password);
    }

    private void saveUserData(String name, String email, String number, String password) {
        UserModel userModel = new UserModel(name, number, email, password);
        firebaseFirestore.collection("User")
                .document(FirebaseAuth.getInstance().getUid())
                .set(userModel)
                .addOnSuccessListener(aVoid -> {
                    // Data added successfully to Firestore

                })
                .addOnFailureListener(e -> {
                    // Failed to add data to Firestore
                    Toast.makeText(SignUpActivity.this, "Failed to add user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    private void saveUserDetails(String name, String email, String number, String password) {
        SharedPreferences preferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("savedName", name);
        editor.putString("savedNumber", number);
        editor.putString("savedEmail", email);
        editor.putString("savedPassword", password);
        editor.apply();
    }
}
