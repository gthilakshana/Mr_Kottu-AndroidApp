package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mr_kottu.databinding.ActivityNameUpdateBinding;
import com.example.mr_kottu.databinding.ActivityNumberUpdateBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class NumberUpdateActivity extends AppCompatActivity {

    Button updateButton,button;

    ActivityNumberUpdateBinding binding;

    TextInputLayout numberTextInputLayout;



    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumberUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);


        //-page load button-//
        button = findViewById(R.id.button17);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NumberUpdateActivity.this, AccountActivity.class));
            }
        });
        //-page load button-//


        numberTextInputLayout = findViewById(R.id.textInputLayout);

        updateButton = findViewById(R.id.update_btn);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numberTextInputLayout.getEditText().getText().toString();

                String updatedName = number;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedNumber", updatedName);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }
}