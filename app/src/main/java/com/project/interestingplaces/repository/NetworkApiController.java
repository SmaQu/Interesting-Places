package com.project.interestingplaces.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.interestingplaces.model.Country;
import com.project.interestingplaces.model.CountryDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApiController {

    private static final String COUNTRIES_URL = "https://serene-mountain-2455.herokuapp.com";
    private static final String TAG = NetworkApiController.class.getSimpleName();

    private final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(COUNTRIES_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private final CountriesAPI countriesAPI = retrofit.create(CountriesAPI.class);

    public void requestCountries(Callback<List<Country>> onApiResponseListener) {
        Call<List<Country>> call = countriesAPI.getAllCountries();
        call.enqueue(onApiResponseListener);
    }

    public void requestCountryDetail(int id, Callback<CountryDetail> onApiResponseListener) {
        Call<CountryDetail> call = countriesAPI.getCountryById(id);
        call.enqueue(onApiResponseListener);
    }
}
