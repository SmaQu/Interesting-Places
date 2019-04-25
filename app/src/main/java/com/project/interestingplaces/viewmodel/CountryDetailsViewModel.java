package com.project.interestingplaces.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.project.interestingplaces.model.Country;
import com.project.interestingplaces.model.CountryDetail;
import com.project.interestingplaces.repository.NetworkApiController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailsViewModel extends ViewModel {

    public MutableLiveData<ResponseLiveDataObject<Response<CountryDetail>>> response = new MutableLiveData<>();

    private final Callback<CountryDetail> onApiResponseListener = new Callback<CountryDetail>() {
        @Override
        public void onResponse(Call<CountryDetail> call, Response<CountryDetail> value) {
            response.setValue(new ResponseLiveDataObject<>(value, null, false));
        }

        @Override
        public void onFailure(Call<CountryDetail> call, Throwable throwable) {
            response.setValue(new ResponseLiveDataObject<>(null, throwable, true));
        }
    };

    private final NetworkApiController networkApiController = new NetworkApiController();

    public void requestCountryDetail(int id) {
        networkApiController.requestCountryDetail(id, onApiResponseListener);
    }
}
