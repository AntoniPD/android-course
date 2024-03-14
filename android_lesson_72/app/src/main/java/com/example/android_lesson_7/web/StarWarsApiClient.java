package com.example.android_lesson_7.web;

import com.example.android_lesson_7.model.SWCharacter;
import com.example.android_lesson_7.model.SWResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StarWarsApiClient {
    @GET("people")
    Call<SWResponse> getSWPeople();

    @GET("people")
    Call<SWResponse> getSWPeoplePaged(@Query("page") int page);
}
