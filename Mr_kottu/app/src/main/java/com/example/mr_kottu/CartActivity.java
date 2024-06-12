package com.example.mr_kottu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mr_kottu.Adapter.ProductAdapter3;
import com.example.mr_kottu.Domain.Product;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

   Button button,plusButton,minusButton;
   TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        button = findViewById(R.id.button19);
        plusButton= findViewById(R.id.button6);
        minusButton = findViewById(R.id.button5);









        //card layout --------//

        Intent intent = getIntent();
        if (intent != null) {
            String productName = intent.getStringExtra("productName");
            String productPrice = intent.getStringExtra("productPrice");





            TextView nameTextView = findViewById(R.id.productName);
            TextView priceTextView = findViewById(R.id.textView2);
            ImageView cartImageView = findViewById(R.id.productImage2);


            if (intent != null) {
                int productImageResource = intent.getIntExtra("productImageResource", R.drawable.default_image); // Default image resource

                cartImageView.setImageResource(productImageResource);
                nameTextView.setText(productName);
                priceTextView.setText(productPrice);

         //card layout ----------//

                //------ card calculate -------//

                Button minusButton = findViewById(R.id.button5);
                Button plusButton = findViewById(R.id.button6);
                Button addTo1Button = findViewById(R.id.button4);
                TextView total      = findViewById(R.id.textView31);
                TextView SubTotal  = findViewById(R.id.textView6);


                final int[] value = {0};
                double initialPrice = Double.parseDouble(priceTextView.getText().toString().replaceAll("[^\\d.]", ""));

                final double[] totalValue = {initialPrice};

                plusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        value[0] += 1;

                        addTo1Button.setText("Add to " + value[0]);

                        if (value[0] == 1) {
                            priceTextView.setText("LKR " + String.format("%.1f", initialPrice));
                            total.setText("LKR " + String.format("%.1f", initialPrice));
                            SubTotal.setText("LKR " + String.format("%.1f", initialPrice));
                            totalValue[0] = initialPrice;
                        } else {
                            totalValue[0] += initialPrice;
                            priceTextView.setText("LKR " + String.format("%.1f", totalValue[0]));
                            total.setText("LKR " + String.format("%.1f", totalValue[0]));
                            SubTotal.setText("LKR " + String.format("%.1f", totalValue[0]));
                        }
                    }
                });

                minusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value[0] > 1) {
                            value[0] -= 1;

                            addTo1Button.setText("Add to " + value[0]);

                            if (value[0] == 1) {
                                priceTextView.setText("LKR " + String.format("%.1f", initialPrice));
                                total.setText("LKR " + String.format("%.1f", initialPrice));
                                SubTotal.setText("LKR " + String.format("%.1f", initialPrice));
                                totalValue[0] = initialPrice;
                            } else {
                                totalValue[0] -= initialPrice;
                                priceTextView.setText("LKR " + String.format("%.1f", totalValue[0]));
                                total.setText("LKR " + String.format("%.1f", totalValue[0]));
                                SubTotal.setText("LKR " + String.format("%.1f", totalValue[0]));
                            }
                        }
                    }
                });

                //------ card calculate -------//






            }
        }
    }
}




