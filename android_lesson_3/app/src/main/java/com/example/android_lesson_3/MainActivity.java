package com.example.android_lesson_3;

import androidx.appcompat.app.AppCompatActivity;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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