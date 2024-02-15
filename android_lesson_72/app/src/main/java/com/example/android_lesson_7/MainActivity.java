package com.example.android_lesson_7;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.android_lesson_7.adapter.RecyclerViewSW;
import com.example.android_lesson_7.model.SWCharacter;
import com.example.android_lesson_7.model.SWResponse;
import com.example.android_lesson_7.web.StarWarsApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

//    private void setAdapter() {
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(users);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(recyclerAdapter);
//    }

    private void setAdapter(List<SWCharacter> swCharacterList) {
        RecyclerViewSW recyclerViewSW = new RecyclerViewSW(swCharacterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewSW);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsApiClient starWarsApiClient = retrofit.create(StarWarsApiClient.class);
        Call<SWResponse> call = starWarsApiClient.getSWPeople();
        call.enqueue(new Callback<SWResponse>() {
            @Override
            public void onResponse(Call<SWResponse> call, Response<SWResponse> response) {
                if (response.isSuccessful()) {
                    SWResponse swResponse = response.body();
                    if (swResponse != null) {
                        // Process the list of cat facts
                        setAdapter(swResponse.getResults());
                        Log.d(TAG, "Response: " + swResponse.getCount());
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


//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://cat-fact.herokuapp.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//    ApiClient apiClient = retrofit.create(ApiClient.class);
//
//    Call<List<Fact>> call = apiClient.getCatFacts();
//        call.enqueue(new Callback<List<Fact>>() {
//        @Override
//        public void onResponse(Call<List<Fact>> call, Response<List<Fact>> response) {
//            if (response.isSuccessful()) {
//                List<Fact> catFacts = response.body();
//                if (catFacts != null) {
//                    // Process the list of cat facts
//                    for (Fact catFact : catFacts) {
//                        Log.d(TAG, "Cat Fact: " + catFact.getText());
//                    }
//                }
//            } else {
//                Log.e(TAG, "Failed to get cat facts: " + response.message());
//            }
//        }
//
//        @Override
//        public void onFailure(Call<List<Fact>> call, Throwable t) {
//            Log.e(TAG, "Error getting cat facts: " + t.getMessage());
//        }
//    });
}