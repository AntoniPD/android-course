package com.example.android_lesson_4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, Double> drinksPrices = new HashMap<>();


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("In ONStart");
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtName = findViewById(R.id.editTextName);

        CheckBox chbCoffee = findViewById(R.id.chbCoffee);
        CheckBox chbTee = findViewById(R.id.chbTee);
        CheckBox chbWatter = findViewById(R.id.chbWater);

        drinksPrices.put(chbCoffee.getId(), 2.5);
        drinksPrices.put(chbTee.getId(), 1.5);
        drinksPrices.put(chbWatter.getId(), 1.0);

        Button btnMinus = findViewById(R.id.btnDecrease);
        Button btnPlus = findViewById(R.id.btnIncrease);

        TextView tvCounter = findViewById(R.id.txtCounter);

        btnMinus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tvCounter.getText().toString());
            if (quantity > 0) {
                quantity--;
                tvCounter.setText(Integer.toString(quantity));
            } else {
                Toast.makeText(this, "Quantity is already 0",
                        Toast.LENGTH_LONG).show();
            }
        });

        btnPlus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tvCounter.getText().toString());
            quantity++;
            tvCounter.setText(Integer.toString(quantity));
        });

        Button btnOrder = findViewById(R.id.btnOrder);

        Intent goToScreenTwo = new Intent(this, ActivityTwo.class);

        btnOrder.setOnClickListener(v -> {
            double finalSum = 0.0;
            int quantity = Integer.parseInt(tvCounter.getText().toString());
            if (chbCoffee.isChecked()) {
                finalSum += drinksPrices.get(chbCoffee.getId()) * quantity;
            }
            if (chbTee.isChecked()) {
                finalSum += drinksPrices.get(chbTee.getId()) * quantity;
            }
            if (chbWatter.isChecked()) {
                finalSum += drinksPrices.get(chbWatter.getId()) * quantity;
            }

            Toast.makeText(this, "Hello, " +
                            edtName.getText() + " your order price is "
                            + finalSum, Toast.LENGTH_LONG)
                    .show();
            String username = edtName.getText().toString();
            goToScreenTwo.putExtra("username", username);

            startActivity(goToScreenTwo);

        });


    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    public void aClass() {
        CheckBox chbCoffee = findViewById(R.id.chbCoffee);
        CheckBox chbTee = findViewById(R.id.chbTee);
        CheckBox chbWater = findViewById(R.id.chbWater);

        drinksPrices.put(chbCoffee.getId(), 1.5);
        drinksPrices.put(chbTee.getId(), 1.2);
        drinksPrices.put(chbWater.getId(), 1.0);

        EditText edtName = findViewById(R.id.editTextName);

        Button btnDec = findViewById(R.id.btnDecrease);
        Button btnInc = findViewById(R.id.btnIncrease);

        TextView counter = findViewById(R.id.txtCounter);

        btnInc.setOnClickListener(v -> {
            int quantity = Integer.parseInt(counter.getText().toString());
            quantity++;
            counter.setText(quantity);
        });

        btnDec.setOnClickListener(v -> {
            int quantity = Integer.parseInt(counter.getText().toString());
            if (quantity > 0) {
                quantity--;
                counter.setText(quantity);
            } else {
                Toast.makeText(this, "Quantity is already 0",
                        Toast.LENGTH_LONG).show();
            }
        });

        Button order = findViewById(R.id.btnOrder);

        order.setOnClickListener(v -> {
            double orderPrice = 0;
            if (chbCoffee.isChecked()) {
                orderPrice += Integer.parseInt(counter.getText().toString())
                        * drinksPrices.get(chbCoffee.getId());
            }
            Toast.makeText(this, "Your order is " + orderPrice + "$",
                    Toast.LENGTH_LONG).show();
        });
    }
}