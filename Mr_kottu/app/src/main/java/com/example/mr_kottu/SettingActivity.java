package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mr_kottu.databinding.ActivitySettinBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    Button button,getLocation,workButton;
    TextView nameTextView,location_home,address,city,addressTextView,cityTextView;

    ActivitySettinBinding binding;
    FirebaseAuth firebaseAuth;

    FirebaseFirestore firebaseFirestore;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final  static int REQUEST_CODE = 100;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocation = findViewById(R.id.button12);
        location_home = findViewById(R.id.textView73);
        workButton = findViewById(R.id.button13);


        // Get references to TextView elements
        nameTextView = findViewById(R.id.textView52);
        addressTextView = findViewById(R.id.textView48);
        cityTextView = findViewById(R.id.textView72);


        SharedPreferences preferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        String savedName = preferences.getString("savedName", "");


        SharedPreferences Preferences = getSharedPreferences("LocationPrefs", MODE_PRIVATE);
        String savedAddress = Preferences.getString("savedAddress", "");
        String saveCity = Preferences.getString("saveCity", "");


        nameTextView.setText(savedName);
        addressTextView.setText(savedAddress);
        cityTextView.setText(saveCity);


        //- Edit account button -//
        button = findViewById(R.id.button9);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AccountActivity.class));
            }
        });
        //- Edit account button -//

        //- next page load button -//
        button = findViewById(R.id.button11);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, ProfileActivity.class));
            }
        });
        //- next page load button -//

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
            }
        });


        Button signOutButton = findViewById(R.id.button14);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsActivity();
            }
        });
    }

        private void openMapsActivity() {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }









    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                try {
                                    Geocoder geocoder = new Geocoder(SettingActivity.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                    String addressText = addresses.get(0).getAddressLine(0);
                                    String cityText = addresses.get(0).getLocality();

                                    addressTextView.setText("Home: " + addressText);
                                    cityTextView.setText("City: " + cityText);

                                    saveLocationToFirestore(addressText, cityText);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        private void saveLocationToFirestore(String addressText, String cityText) {
                            String accountId = firebaseAuth.getCurrentUser().getUid();
                            String accountEmail = firebaseAuth.getCurrentUser().getEmail();


                            LocationData locationData = new LocationData();
                            locationData.setAccountId(accountId);
                            locationData.setAccountEmail(accountEmail);
                            locationData.setAddress(addressText);
                            locationData.setCity(cityText);

                            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                            firebaseFirestore.collection("Location")
                                    .document(accountId)
                                    .set(locationData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(SettingActivity.this,
                                                    "Location saved to Firestore",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SettingActivity.this,
                                                    "Failed to save location: " + e.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                            Log.e("FirestoreError", "Failed to save location: " + e.getMessage());
                                        }
                                    });
                        }
                    });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(SettingActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(SettingActivity.this, "Please provide the required permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
