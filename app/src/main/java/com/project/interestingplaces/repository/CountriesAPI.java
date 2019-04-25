package com.project.interestingplaces.repository;

import com.project.interestingplaces.model.Country;
import com.project.interestingplaces.model.CountryDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CountriesAPI {

    @GET("/countries")
    Call<List<Country>> getAllCountries();

    @GET("/countries/{id}")
    Call<CountryDetail> getCountryById(@Path("id") int countryId);
}
