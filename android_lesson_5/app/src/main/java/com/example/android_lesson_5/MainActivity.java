package com.example.android_lesson_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btnOne);
        Button btn2 = findViewById(R.id.btnOne);
        FragmentManager fragmentManager = getSupportFragmentManager();

        btn1.setOnClickListener(v -> {
            // Make a transaction to call the desired fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentOne())
                    // without commit, won't work
                    .commit();
        });

        btn2.setOnClickListener(v -> {
            // Make a transaction to call the desired fragment
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentTwo())
                    // without commit, won't work
                    .commit();
        });

        // MUST HAVE THIS IF YOU WANT YOUR TOOLBAR TO BE ACTIVE
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This is must have, in order to show the menu buttons
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Here we are setting what to do when a menu item is clicked
        switch (item.getItemId()) {
            case R.id.itemAddGroup:
                Toast.makeText(this, "Add group clicked",
                        Toast.LENGTH_LONG).show();
            case R.id.itemEditGroup:
                Toast.makeText(this, "Edit group clicked",
                        Toast.LENGTH_LONG).show();
        }
        return true;
    }


    /*
    Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);

        LayoutInflater layoutInflater = getLayoutInflater();
        btn1.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_one, new FragmentOne());
            fragmentTransaction.commit();
        });

        btn2.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_one, new FragmentTwo());
            fragmentTransaction.commit();
        });
     */
}