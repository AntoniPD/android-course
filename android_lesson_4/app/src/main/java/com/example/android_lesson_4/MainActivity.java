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

import com.example.android_lesson_4.model.ItemQuantityPrice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, ItemQuantityPrice> itemsQuantityAndPrice = new HashMap<>();

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

        // Създаваме си нов обект за всяка напитка, който съдържа име на напитката, количество и цена
        // Добавяме всеки обект в мап-а по ключ, id-то на checkBox-a на който съответства напитката
        itemsQuantityAndPrice.put(chbCoffee.getId(), new ItemQuantityPrice(2.0, 0, chbCoffee.getText().toString()));
        itemsQuantityAndPrice.put(chbTee.getId(), new ItemQuantityPrice(1.5, 0, chbTee.getText().toString()));
        itemsQuantityAndPrice.put(chbWatter.getId(), new ItemQuantityPrice(1.0, 0, chbWatter.getText().toString()));

        // Взимаме си бутоните за всяка от трите напитки
        Button btnMinusC = findViewById(R.id.btnDecreaseCoffee);
        Button btnPlusC = findViewById(R.id.btnIncreaseCoffee);
        TextView tvCounterC = findViewById(R.id.txtCounterCoffee);

        Button btnMinusT = findViewById(R.id.btnDecreaseTee);
        Button btnPlusT = findViewById(R.id.btnIncreaseTee);
        TextView tvCounterT = findViewById(R.id.txtCounterTee);

        Button btnMinusW = findViewById(R.id.btnDecreaseWater);
        Button btnPlusW = findViewById(R.id.btnIncreaseWater);
        TextView tvCounterW = findViewById(R.id.txtCounterWater);

        controlQuantityForDrink(chbCoffee, btnMinusC, btnPlusC, tvCounterC);
        controlQuantityForDrink(chbTee, btnMinusT, btnPlusT, tvCounterT);
        controlQuantityForDrink(chbWatter, btnMinusW, btnPlusW, tvCounterW);

        Button btnOrder = findViewById(R.id.btnOrder);

        Intent goToScreenTwo = new Intent(this, ActivityTwo.class);

        btnOrder.setOnClickListener(v -> {
            Toast.makeText(this, "Hello, " +
                            edtName.getText(), Toast.LENGTH_LONG)
                    .show();
            String username = edtName.getText().toString();
            goToScreenTwo.putExtra("username", username);
            // Слагаме целия мап в intent-a, така че да можем да го вземем от другия екран.
            // За да го вземем трябва да използваме същия ключ - "mapWithQuantities" в другия екран, чрез този ключ можем да го достъпим
            // В случая е задължително да CAST-нем към Serializable, понеже обект от такъв тип се очаква в метода ->
            // ..... putExtra(String key, Serializable object)
            goToScreenTwo.putExtra("mapWithQuantities", (Serializable) itemsQuantityAndPrice);

            startActivity(goToScreenTwo);

        });


    }

    // Тази функция служи за това да променим броя напитки
    // за всеки checkbox съответстващ на напитката
    private void controlQuantityForDrink(CheckBox checkBox, Button btnMinus, Button btnPlus, TextView counter) {
        btnMinus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(counter.getText().toString());
            if (quantity > 0) {
                quantity--;
                counter.setText(Integer.toString(quantity));
                // взимаме напитката която съотвества на подадения checkbox и му задаваме количеството което е сложено, чрез + и - бутоните
                Objects.requireNonNull(itemsQuantityAndPrice.get(checkBox.getId())).setQuantity(quantity);
            } else {
                Toast.makeText(this, "Quantity is already 0",
                        Toast.LENGTH_LONG).show();
            }
        });

        btnPlus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(counter.getText().toString());
            quantity++;
            counter.setText(Integer.toString(quantity));
            // взимаме напитката която съотвества на подадения checkbox и му задаваме количеството което е сложено, чрез + и - бутоните
            Objects.requireNonNull(itemsQuantityAndPrice.get(checkBox.getId())).setQuantity(quantity);
        });
    }
}


////    public void aClass() {
////        CheckBox chbCoffee = findViewById(R.id.chbCoffee);
////        CheckBox chbTee = findViewById(R.id.chbTee);
////        CheckBox chbWater = findViewById(R.id.chbWater);
////
////        drinksPrices.put(chbCoffee.getId(), 1.5);
////        drinksPrices.put(chbTee.getId(), 1.2);
////        drinksPrices.put(chbWater.getId(), 1.0);
////
////        EditText edtName = findViewById(R.id.editTextName);
////
////        Button btnDec = findViewById(R.id.btnDecrease);
////        Button btnInc = findViewById(R.id.btnIncrease);
////
////        TextView counter = findViewById(R.id.txtCounter);
////
////        btnInc.setOnClickListener(v -> {
////            int quantity = Integer.parseInt(counter.getText().toString());
////            quantity++;
////            counter.setText(quantity);
////        });
////
////        btnDec.setOnClickListener(v -> {
////            int quantity = Integer.parseInt(counter.getText().toString());
////            if (quantity > 0) {
////                quantity--;
////                counter.setText(quantity);
////            } else {
////                Toast.makeText(this, "Quantity is already 0",
////                        Toast.LENGTH_LONG).show();
////            }
////        });
////
////        Button order = findViewById(R.id.btnOrder);
////
////        order.setOnClickListener(v -> {
////            double orderPrice = 0;
////            if (chbCoffee.isChecked()) {
////                orderPrice += Integer.parseInt(counter.getText().toString())
////                        * drinksPrices.get(chbCoffee.getId());
////            }
////            Toast.makeText(this, "Your order is " + orderPrice + "$",
////                    Toast.LENGTH_LONG).show();
////        });
////    }
//}