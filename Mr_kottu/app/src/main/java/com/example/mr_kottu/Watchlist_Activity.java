package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mr_kottu.Adapter.WatchlistAdapter;
import com.example.mr_kottu.Domain.Watchlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Watchlist_Activity extends AppCompatActivity {

    private WatchlistAdapter watchlistAdapter;
    private FirebaseFirestore db;
    private CollectionReference watchlistCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<Watchlist> productList = new ArrayList<>(); // Initialize the productList
        watchlistAdapter = new WatchlistAdapter(this, productList);
        recyclerView.setAdapter(watchlistAdapter);

        db = FirebaseFirestore.getInstance();
        watchlistCollection = db.collection("Watchlist");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
            fetchProductsFromFirestore(userEmail);
        }
    }

    private void fetchProductsFromFirestore(String userEmail) {
        watchlistCollection.whereEqualTo("userEmail", userEmail)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Watchlist> watchlistItems = new ArrayList<>();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        Watchlist watchlist = snapshot.toObject(Watchlist.class);
                        watchlistItems.add(watchlist);
                    }
                    // Update the RecyclerView data
                    watchlistAdapter.updateData(watchlistItems);
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Toast.makeText(this, "Failed to fetch watchlist items", Toast.LENGTH_SHORT).show();
                });
    }

    public void addToWatchlist(Watchlist itemToAdd, Context context) {
        // Assuming itemToAdd contains necessary fields
        watchlistCollection.add(itemToAdd)
                .addOnSuccessListener(documentReference -> {
                    // Handle successful addition
                    Toast.makeText(context, "Added to Watchlist", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Handle unsuccessful addition
                    Toast.makeText(context, "Failed to add to Watchlist", Toast.LENGTH_SHORT).show();
                });
    }
}