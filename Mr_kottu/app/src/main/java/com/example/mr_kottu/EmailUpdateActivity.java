package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mr_kottu.databinding.ActivityEmailUpdateBinding;
import com.example.mr_kottu.databinding.ActivityNumberUpdateBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EmailUpdateActivity extends AppCompatActivity {

    Button updateButton,button;

    ActivityEmailUpdateBinding binding;

    TextInputLayout emailTextInputLayout;



    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);


        //-page load button-//
        button = findViewById(R.id.button18);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmailUpdateActivity.this, AccountActivity.class));
            }
        });
        //-page load button-//


        emailTextInputLayout = findViewById(R.id.textInputLayout61);

        updateButton = findViewById(R.id.update_btn3);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTextInputLayout.getEditText().getText().toString();

                String updatedEmail = email;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedEmail", updatedEmail);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }
}