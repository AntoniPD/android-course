package com.example.android_lesson_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Intent intent = getIntent();
        var a = intent.getExtras();

        TextView tv = findViewById(R.id.textView);
        assert a != null;
        var b = a.getString("username");
        tv.setText(b);
    }
}