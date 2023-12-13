package com.example.android_lesson_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.android_lesson_4.model.ItemQuantityPrice;
import com.example.android_lesson_4.model.UserDetails;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class UserInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        Intent intent = getIntent();
        if (intent == null) {
            System.out.println("ERROR");
            return;
        }
        String username = intent.getStringExtra("username");

        EditText edtName = findViewById(R.id.edtName);
        edtName.setText(username);

        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtEmail = findViewById(R.id.edtEmail);

        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setEnabled(false);
        btnNext.setBackgroundColor(Color.RED);

        AtomicReference<Boolean> emailIsPopulated = new AtomicReference<>(false);
        AtomicReference<Boolean> phoneIsPopulated = new AtomicReference<>(false);
        AtomicReference<Boolean> addressIsPopulated = new AtomicReference<>(false);

        edtEmail.setOnFocusChangeListener((v, onFocus) -> {
            if (!onFocus && edtEmail.getText().toString().isBlank()) {
                edtEmail.setError("Email is required");
            } else if (!onFocus && !edtEmail.getText().toString().isBlank()) {
                emailIsPopulated.set(true);
                if (emailIsPopulated.get() && phoneIsPopulated.get() && addressIsPopulated.get()) {
                    btnNext.setEnabled(true);
                    btnNext.setBackgroundColor(Color.GREEN);
                }
            }
        });

        edtPhone.setOnFocusChangeListener((v, onFocus) -> {
            if (!onFocus && edtPhone.getText().toString().isBlank()) {
                edtPhone.setError("Phone is required");
            } else if (!onFocus && !edtPhone.getText().toString().isBlank()) {
                phoneIsPopulated.set(true);
                if (emailIsPopulated.get() && phoneIsPopulated.get() && addressIsPopulated.get()) {
                    btnNext.setEnabled(true);
                    btnNext.setBackgroundColor(Color.GREEN);
                }
            }
        });

        edtAddress.setOnFocusChangeListener((v, onFocus) -> {
            if (!onFocus && edtAddress.getText().toString().isBlank()) {
                edtAddress.setError("Address is required");
            } else if (!onFocus && !edtAddress.getText().toString().isBlank()) {
                addressIsPopulated.set(true);
                if (emailIsPopulated.get() && phoneIsPopulated.get() && addressIsPopulated.get()) {
                    btnNext.setEnabled(true);
                    btnNext.setBackgroundColor(Color.GREEN);
                }
            }
        });

        UserDetails userDetails = new UserDetails();
        userDetails.setAddress(edtAddress.getText().toString());
        userDetails.setName(username);
        userDetails.setEmail(edtEmail.getText().toString());
        userDetails.setPhone(edtPhone.getText().toString());


        Intent goToFinishOrderScreen = new Intent(this, OrderActivity.class);
        goToFinishOrderScreen.putExtra("userDetails", userDetails);

        HashMap<Integer, ItemQuantityPrice> integerItemQuantityPriceMap
                = intent.getSerializableExtra("mapWithQuantities", HashMap.class);

        AtomicReference<Double> sumToPay = new AtomicReference<>(0.0);

        if (integerItemQuantityPriceMap != null) {
            integerItemQuantityPriceMap.forEach((drink, quantityPrice) -> {
                var drinkPrice = quantityPrice.getQuantity() * quantityPrice.getPrice();
                sumToPay.updateAndGet(v -> Double.valueOf(v + drinkPrice));
            });
        }

        goToFinishOrderScreen.putExtra("sumPrice", sumToPay.get());


        // ...screen 3

//        goToScreenThree.putExtra("")

    }

    /*
       EditText edtName = findViewById(R.id.edtName);
        EditText edtPhone = findViewById(R.id.edtPhoneNumber);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtEmail = findViewById(R.id.edtEmail);

        Button btnNext = findViewById(R.id.btnNext);

        // Взимаме intent-intentExtras чрез който сме дошли на това activity, той винаги е един единствен
        Intent intent = getIntent();
        var intentExtras = intent.getExtras();

        assert intentExtras != null;
        var usernameString = intentExtras.getString("username");

        // чрез функцията intent.getSerializableExtra, като й подадем ключа под който сме сложили мапа,
        // подаваме като втори аргумент, типа на обекта който очакваме да получим
        // в случая HashMap.class и в integerItemQuantityPriceMap вече имаме съдържанието на мапа от предишното activity
        Map<Integer, ItemQuantityPrice> integerItemQuantityPriceMap
                = intent.getSerializableExtra("mapWithQuantities", HashMap.class);

        edtName.setText(usernameString);

        Intent goToScreenThree = new Intent(this, OrderActivity.class);
        btnNext.setEnabled(false);

        AtomicBoolean edtEmailPopulated = new AtomicBoolean(false);
        AtomicBoolean edtPhonePopulated = new AtomicBoolean(false);
        AtomicBoolean edtAddressPopulated = new AtomicBoolean(false);

        edtEmail.setOnFocusChangeListener((k, isOnFocus) -> {
            if (!isOnFocus && edtEmail.getText().toString().isBlank()) {
                edtEmail.setError(edtEmail.getHint() + " should be populated");
                btnNext.setEnabled(false);
            } else if (!isOnFocus && !edtEmail.getText().toString().isBlank()) {
                edtEmailPopulated.set(true);
                if (edtAddressPopulated.get() && edtEmailPopulated.get() && edtPhonePopulated.get()) {
                    btnNext.setEnabled(true);
                }
            }
        });

        edtAddress.setOnFocusChangeListener((k, isOnFocus) -> {
            if (!isOnFocus && edtAddress.getText().toString().isBlank()) {
                edtAddress.setError(edtAddress.getHint() + " should be populated");
                btnNext.setEnabled(false);
            } else if (!isOnFocus && !edtAddress.getText().toString().isBlank()) {
                edtAddressPopulated.set(true);
                if (edtAddressPopulated.get() && edtEmailPopulated.get() && edtPhonePopulated.get()) {
                    btnNext.setEnabled(true);
                }
            }
        });

        edtPhone.setOnFocusChangeListener((k, isOnFocus) -> {
            if (!isOnFocus && edtPhone.getText().toString().isBlank()) {
                edtPhone.setError(edtPhone.getHint() + " should be populated");
                btnNext.setEnabled(false);
            } else if (!isOnFocus && !edtPhone.getText().toString().isBlank()) {
                edtPhonePopulated.set(true);
                if (edtAddressPopulated.get() && edtEmailPopulated.get() && edtPhonePopulated.get()) {
                    btnNext.setEnabled(true);
                }
            }
        });




        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(edtEmail.getText().toString());
        userDetails.setPhoneNumber(edtPhone.getText().toString());
        userDetails.setName(usernameString);
        userDetails.setAddress(edtAddress.getText().toString());

        btnNext.setOnClickListener(v -> {

        });
        private static boolean checkFields(EditText editText, Button btnNext) {
            AtomicBoolean isPopulated = new AtomicBoolean(false);
            editText.setOnFocusChangeListener((k, isOnFocus) -> {
                if (!isOnFocus && editText.getText().toString().isBlank()) {
                    editText.setError(editText.getHint() + " should be populated");
                    btnNext.setEnabled(false);
                } else if(!isOnFocus && !editText.getText().toString().isBlank()) {
                    isPopulated.set(true);
                }
            });

            return isPopulated.get();
        }
     */

}