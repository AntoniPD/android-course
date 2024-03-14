package com.example.android_lesson_7;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.android_lesson_7.adapter.RecyclerViewSW;
import com.example.android_lesson_7.db.AppDatabase;
import com.example.android_lesson_7.model.SWCharacter;
import com.example.android_lesson_7.model.SWResponse;
import com.example.android_lesson_7.web.StarWarsApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<SWCharacter> currentSWCharacters = new ArrayList<>();
    public static AppDatabase appDatabase;
    int page = 1;

    private void setAdapter(List<SWCharacter> swCharacterList) {
        RecyclerViewSW recyclerViewSW = new RecyclerViewSW(swCharacterList, appDatabase);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewSW);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "star-wars_new")
                .allowMainThreadQueries()
                .build();


        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnPrev = findViewById(R.id.btnPrev);

        Button btnShowSaved = findViewById(R.id.btnShowSaved);
        Intent goToHomeActivity = new Intent(this, HomeActivity.class);
        btnShowSaved.setOnClickListener(v -> {
            startActivity(goToHomeActivity);
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://swapi.py4e.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        StarWarsApiClient starWarsApiClient = retrofit.create(StarWarsApiClient.class);

        SharedPreferences sharedPreferences = getSharedPreferences("FirstDB", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int currPage = sharedPreferences.getInt("currentPage", 1);
        System.out.println("Current page " + currPage);
        page = currPage;
        loadSWCharacters(starWarsApiClient, editor);
        btnNext.setOnClickListener(v -> {
            page++;
            loadSWCharacters(starWarsApiClient, editor);
        });

        btnPrev.setOnClickListener(v -> {
            if (page == 1) {
                return;
            }
            page--;
            loadSWCharacters(starWarsApiClient, editor);
        });

    }

    private void loadSWCharacters(StarWarsApiClient starWarsApiClient, SharedPreferences.Editor editor) {
        Call<SWResponse> call = starWarsApiClient.getSWPeoplePaged(page);
        call.enqueue(new Callback<SWResponse>() {
            @Override
            public void onResponse(Call<SWResponse> call, Response<SWResponse> response) {
                if (response.isSuccessful()) {
                    SWResponse swResponse = response.body();
                    editor.putInt("currentPage", page);
                    editor.apply();
                    if (swResponse != null) {
                        setAdapter(swResponse.getResults());
                        Log.d(TAG, "Response: " + swResponse.getCount());
                    } else {
                        Log.e(TAG, "Response is null");
                    }
                } else {
                    Log.e(TAG, "Failed to get cat facts: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SWResponse> call, Throwable t) {
                Log.e(TAG, "Error getting sw response: " + t.getMessage());
            }
        });
    }
}