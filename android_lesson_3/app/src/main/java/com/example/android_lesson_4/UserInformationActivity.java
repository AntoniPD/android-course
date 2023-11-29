package com.example.android_lesson_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class UserInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        getIntent().getStringExtra("username");
//        Bundle bundle = intent.getExtras();
//        String username = bundle.getString("username");
//        System.out.println(intent);
//        System.out.println(username);
        EditText editText = findViewById(R.id.enterUsername);
//        editText.setText(username);
    }
}