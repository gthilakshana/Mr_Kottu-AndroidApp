package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mr_kottu.databinding.ActivityInformationBinding;
import com.example.mr_kottu.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class InformationActivity extends AppCompatActivity {

    TextInputLayout firstNameTextInputLayout, lastNameTextInputLayout, mobileNumberTextInputLayout;
    Button nextButton;
    ActivityInformationBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        GoogleSignInAccount account = getIntent().getParcelableExtra("googleAccount");

        nextButton = binding.button20;
        firstNameTextInputLayout = binding.textInputLayout6;
        lastNameTextInputLayout = binding.textInputLayout61;
        mobileNumberTextInputLayout = binding.textInputLayout;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameTextInputLayout.getEditText().getText().toString();
                String lastName = lastNameTextInputLayout.getEditText().getText().toString();
                String mobileNumber = mobileNumberTextInputLayout.getEditText().getText().toString();

                saveToFirebase(account.getEmail(), firstName, lastName, mobileNumber);

                Intent homeIntent = new Intent(InformationActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });
    }

    private void saveToFirebase(String email, String firstName, String lastName, String mobileNumber) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("mobileNumber", mobileNumber);

        db.collection("users")
                .document(email)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(InformationActivity.this, "Information saved successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InformationActivity.this, "Failed to save information", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
