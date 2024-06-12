package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mr_kottu.databinding.ActivityAccountBinding;
import com.example.mr_kottu.databinding.ActivityNameUpdateBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class NameUpdateActivity extends AppCompatActivity {

    Button updateButton,button;

    ActivityNameUpdateBinding binding;

    TextInputLayout firstNameTextInputLayout;
    TextInputLayout lastNameTextInputLayout;


    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNameUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);

        firstNameTextInputLayout = findViewById(R.id.textInputLayout61);


        firstNameTextInputLayout = findViewById(R.id.textInputLayout61);
        lastNameTextInputLayout = findViewById(R.id.textInputLayout6);
        updateButton = findViewById(R.id.update_btn2);

        //-page load button-//
        button = findViewById(R.id.button16);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NameUpdateActivity.this, AccountActivity.class));
            }
        });
        //-page load button-//

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameTextInputLayout.getEditText().getText().toString();
                String lastName = lastNameTextInputLayout.getEditText().getText().toString();
                String updatedName = firstName + " " + lastName;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedName", updatedName);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }
    }
