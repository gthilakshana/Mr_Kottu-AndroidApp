package com.example.mrkottu_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class Popular_picks_Product extends AppCompatActivity {

    private static final String TAG = "Popular_picks_Product";
    private static final int PICK_IMAGE_REQUEST = 1;

    EditText productNameEditText, productDescEditText, productPriceEditText;
    ImageButton imageButton;
    Uri imageUri;

    Button button;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_picks_product);

        //----- Page load Button -----//

        button = findViewById(R.id.button23);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Popular_picks_Product.this, AdminDashboard.class));
            }
        });
        //----- Page load Button -----//


        firebaseAuth = FirebaseAuth.getInstance();


        Window window = Popular_picks_Product.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Popular_picks_Product.this, androidx.cardview.R.color.cardview_dark_background));

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("product_images");

        productNameEditText = findViewById(R.id.name);
        productDescEditText = findViewById(R.id.descrption);
        productPriceEditText = findViewById(R.id.price);
        imageButton = findViewById(R.id.imageButton);

        Button saveProductButton = findViewById(R.id.Updatebtn);

        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = productNameEditText.getText().toString().trim();
                String productDesc = productDescEditText.getText().toString().trim();
                String productPrice = productPriceEditText.getText().toString().trim();

                if (!productName.isEmpty() && !productDesc.isEmpty() && !productPrice.isEmpty() && imageUri != null) {
                    uploadProductWithImage(productName, productDesc, productPrice);
                } else {
                    Toast.makeText(Popular_picks_Product.this, "Please fill in all fields and select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }


    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            imageButton.setImageURI(imageUri);
        }
    }


    private void uploadProductWithImage(String productName, String productDesc, String productPrice) {
        StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));


        fileReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {

                    fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageURL = uri.toString();

                        saveProductDetails(productName, productDesc, productPrice, imageURL);
                    }).addOnFailureListener(e -> {

                        Toast.makeText(Popular_picks_Product.this, "Failed to get image URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(Popular_picks_Product.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }



    private void saveProductDetails(String productName, String productDesc, String productPrice, String imageURL) {
        Map<String, Object> productData = new HashMap<>();
        productData.put("productName", productName);
        productData.put("productDesc", productDesc);
        productData.put("productPrice", productPrice);
        productData.put("imageURL", imageURL);


        String productId = generateProductId();


        productData.put("productId", productId);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        CollectionReference productRegister = firebaseFirestore.collection("Popular picks Product");
        productRegister.add(productData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(Popular_picks_Product.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Popular_picks_Product.this, "Failed to add product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    private String generateProductId() {

        return String.valueOf(System.currentTimeMillis());
    }





}