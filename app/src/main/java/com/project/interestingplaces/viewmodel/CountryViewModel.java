package com.project.interestingplaces.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.project.interestingplaces.model.Country;
import com.project.interestingplaces.repository.NetworkApiController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {

    public MutableLiveData<ResponseLiveDataObject<Response<List<Country>>>> response = new MutableLiveData<>();

    private final Callback<List<Country>> onApiResponseListener = new Callback<List<Country>>() {
        @Override
        public void onResponse(Call<List<Country>> call, Response<List<Country>> value) {
            response.setValue(new ResponseLiveDataObject<>(value, null, false));
        }

        @Override
        public void onFailure(Call<List<Country>> call, Throwable throwable) {
            response.setValue(new ResponseLiveDataObject<>(null, throwable, true));
        }
    };

    private final NetworkApiController networkApiController = new NetworkApiController();

    public void requestCountries() {
        networkApiController.requestCountries(onApiResponseListener);
    }
}
