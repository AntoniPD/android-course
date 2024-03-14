package com.example.android_lesson_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.android_lesson_7.adapter.RecyclerViewCharacter;
import com.example.android_lesson_7.adapter.RecyclerViewSW;
import com.example.android_lesson_7.dao.CharacterDao;
import com.example.android_lesson_7.entity.Character;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private void setAdapter(List<Character> swCharacterList) {
        RecyclerViewCharacter recyclerViewCharacter = new RecyclerViewCharacter(swCharacterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewCharacter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerViewHome);
        Button btnBack = findViewById(R.id.btnBack);

        Intent goBack = new Intent(this, MainActivity.class);

        btnBack.setOnClickListener(v -> {
            startActivity(goBack);
        });

        CharacterDao characterDao = MainActivity.appDatabase.characterDao();
        List<Character> characters = characterDao.findAllCharacters();
        setAdapter(characters);
    }
}