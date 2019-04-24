package com.project.interestingplaces.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.interestingplaces.model.Country;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkApiController {

    private static final String COUNTRIES_URL = "https://serene-mountain-2455.herokuapp.com";

    private Callback<List<Country>> onResponseListener = new Callback<List<Country>>() {

        @Override
        public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

        }

        @Override
        public void onFailure(Call<List<Country>> call, Throwable t) {

        }
    };

    public void requestCountries() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(COUNTRIES_URL)
                .addConverterFactory()
                .build();

        final CountriesAPI countriesAPI = retrofit.create(CountriesAPI.class);

        Call<List<Country>> response = countriesAPI.getAllCountries();
    }
}
