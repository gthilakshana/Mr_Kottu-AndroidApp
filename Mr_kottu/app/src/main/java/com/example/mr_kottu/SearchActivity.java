package com.example.mr_kottu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mr_kottu.Adapter.ProductAdapter;
import com.example.mr_kottu.Adapter.ProductAdapter4;
import com.example.mr_kottu.Adapter.searchAdaptor;
import com.example.mr_kottu.Domain.Product;
import com.example.mr_kottu.Domain.Product2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ArrayList<Product> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference PharmacyCollection = db.collection("Product");

        list = new ArrayList<>();
        searchAdaptor searchAdaptor = new searchAdaptor(SearchActivity.this, list);



//        searchAdaptor.setOnItemClickListener(this);



        EditText editText = findViewById(R.id.serachid);
        Button button = findViewById(R.id.searchbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Ok","ok");


                String name = editText.getText().toString();

                PharmacyCollection.whereEqualTo("productName", name).get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            list.clear();
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                Product product = documentSnapshot.toObject(Product.class);

                                list.add(product);
                            }
                            searchAdaptor.notifyDataSetChanged();

                            if (list.isEmpty()){
                                Toast.makeText(SearchActivity.this,"No Product in this area",Toast.LENGTH_SHORT).show();
                            }else {
                                RecyclerView recyclerView = findViewById(R.id.searchre);
                                recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                                recyclerView.setAdapter(searchAdaptor);
                            }

                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(SearchActivity.this,"No Product in this area",Toast.LENGTH_SHORT).show();
                        });


            }



        });

    }








}






