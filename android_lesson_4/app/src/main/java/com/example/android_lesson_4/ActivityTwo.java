package com.example.android_lesson_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_lesson_4.model.ItemQuantityPrice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // Взимаме intent-intentExtras чрез който сме дошли на това activity, той винаги е един единствен
        Intent intent = getIntent();
        var intentExtras = intent.getExtras();

        TextView tv = findViewById(R.id.textView);
        assert intentExtras != null;
        var usernameString = intentExtras.getString("username");

        // чрез функцията intent.getSerializableExtra, като й подадем ключа под който сме сложили мапа,
        // подаваме като втори аргумент, типа на обекта който очакваме да получим
        // в случая HashMap.class и в integerItemQuantityPriceMap вече имаме съдържанието на мапа от предишното activity
        Map<Integer, ItemQuantityPrice> integerItemQuantityPriceMap
                = intent.getSerializableExtra("mapWithQuantities", HashMap.class);
        // sumToPay e atomic, защото се използва във forEach, forEach не ни гарантира
        // че в даден момент променливата, ще се обработва само от една нишка
        // защото работи асинхронно
        AtomicReference<Double> sumToPay = new AtomicReference<>(0.0);
        // textMessage по същата причина е от тип final String[]
        // Дори и да се използва само String, иде-то само ще ви предложи да промените типа на този отгоре
        final String[] textMessage = {"Hello, " + usernameString + " You ordered the following: "};
        // обхождаме всяка една напитка от мап-а който сме получили
        //                       ---key = drink от тип String, quantityPrice = value от тип ItemQuantityPrice
        integerItemQuantityPriceMap.forEach((drink, quantityPrice) -> {
            var drinkPrice = quantityPrice.getQuantity() * quantityPrice.getPrice();
            sumToPay.updateAndGet(v -> Double.valueOf(v + drinkPrice));
            // залепяме към стринга който ще изобразява броя на напитките и цените им
            textMessage[0] += quantityPrice.getQuantity()
                    + " " + quantityPrice.getDrink()
                    + " for price: "
                    + drinkPrice + "\n";
        });
        tv.setText(textMessage[0]);

        // Показваме цялата сума в Toast.
        // this -> ни казва, че искаме Toast-intentExtras да се покаже на това activity
        // Toast.LENGTH_LONG -> казваме за какво време искаме да се покаже самият Toast
        Toast.makeText(this, "Sum to pay: " +
                        sumToPay.get(), Toast.LENGTH_LONG)
                .show();

        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {

        });


    }
}