package com.example.mr_kottu;

import static android.app.PendingIntent.getActivity;



import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.bumptech.glide.Glide;
import com.example.mr_kottu.databinding.ActivityAccountBinding;
import com.example.mr_kottu.databinding.ActivitySignUpBinding;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AccountActivity extends AppCompatActivity {


    public static final String TAG = AccountActivity.class.getName();
    private static final int PICK_IMAGE_REQUEST = 1;


    Button button;
    private static final int RESULT_OK = -1;




    private StorageReference imageStorageRef;

    private String userId;





    private ActivityResultLauncher<String> activityResultLauncher;

    private FirebaseAuth mAuth;
    private ImageButton imageButton;



    TextView nameTextView, numberTextView, emailTextView, nameUpdateText, NumberUpdateText, EmailUpdateText;


    ActivityAccountBinding binding;
    private DocumentReference userDocumentRef;

    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ImageButton imagebutton;

    final int REQUEST_UPDATE_NAME = 1;
    final int REQUEST_UPDATE_NUMBER = 2;

    final int REQUEST_UPDATE_EMAIL = 3;
    final int REQUEST_UPDATE_IMAGE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);


        // Get references to TextView elements
        nameTextView = findViewById(R.id.textView37);
        numberTextView = findViewById(R.id.textView38);
        emailTextView = findViewById(R.id.textView50);

        imagebutton = findViewById(R.id.imageButton);


        // Retrieve saved user information
        SharedPreferences preferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        String savedName = preferences.getString("savedName", "");
        String savedNumber = preferences.getString("savedNumber", "");
        String savedEmail = preferences.getString("savedEmail", "");

        // Update TextViews with saved user information
        nameTextView.setText(savedName);
        numberTextView.setText(savedNumber);
        emailTextView.setText(savedEmail);


        //-page load button-//
        button = findViewById(R.id.button23);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, ProfileActivity.class));
            }
        });
        //-page load button-//


        nameUpdateText = findViewById(R.id.textView37);

        nameUpdateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, NameUpdateActivity.class);
                startActivityForResult(intent, REQUEST_UPDATE_NAME);

            }
        });

        NumberUpdateText = findViewById(R.id.textView38);

        NumberUpdateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, NumberUpdateActivity.class);
                startActivityForResult(intent, REQUEST_UPDATE_NUMBER);

            }
        });

        EmailUpdateText = findViewById(R.id.textView50);

        EmailUpdateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, EmailUpdateActivity.class);
                startActivityForResult(intent, REQUEST_UPDATE_EMAIL);

            }
        });

        //-------------------------//


        //--- image button space ----//

    }
    //-------------------------//

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_UPDATE_NAME && resultCode == RESULT_OK && data != null) {
            String updatedName = data.getStringExtra("updatedName");
            nameUpdateText.setText(updatedName);


            // Update user's name in SharedPreferences
            SharedPreferences preferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("savedName", updatedName);
            editor.apply();


            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser != null) {
                String userId = currentUser.getUid();
                DocumentReference userRef = firebaseFirestore.collection("users").document(userId);
                userRef.update("name", updatedName)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }


        }


        if (requestCode == REQUEST_UPDATE_NUMBER && resultCode == RESULT_OK && data != null) {
            String updatedNumber = data.getStringExtra("updatedNumber");
            NumberUpdateText.setText(updatedNumber);


            // Update user's name in SharedPreferences
            SharedPreferences preferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("savedNumber", updatedNumber);
            editor.apply();


            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser != null) {
                String userId = currentUser.getUid();
                DocumentReference userRef = firebaseFirestore.collection("users").document(userId);
                userRef.update("number", updatedNumber)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        }

        if (requestCode == REQUEST_UPDATE_EMAIL && resultCode == RESULT_OK && data != null) {
            String updatedEmail = data.getStringExtra("updatedEmail");
            EmailUpdateText.setText(updatedEmail);


            // Update user's name in SharedPreferences
            SharedPreferences preferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("savedEmail", updatedEmail);
            editor.apply();


            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser != null) {
                String userId = currentUser.getUid();
                DocumentReference userRef = firebaseFirestore.collection("users").document(userId);
                userRef.update("email", updatedEmail)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }




        }

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
               /** selectedImageUri = data.getData();
                Imageview.setImageURI(selectedImageUri);**/
            }



    }








}




















