package com.project.interestingplaces.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.project.interestingplaces.R;
import com.project.interestingplaces.model.CountryDetail;
import com.project.interestingplaces.viewmodel.CountryDetailsViewModel;

import retrofit2.Response;

public class InterestingPlaceDetailFragment extends BaseFragment {

    public static final String TAG = InterestingPlaceDetailFragment.class.getSimpleName();
    private static final String ARG_COUNTRY_DETAIL_ID = "arg_country_detail_id";
    private CountryDetailsViewModel countryDetailsViewModel;

    private Toolbar toolbar;

    public static InterestingPlaceDetailFragment create(int countryId) {
        final InterestingPlaceDetailFragment interestingPlaceDetailFragment = new InterestingPlaceDetailFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_COUNTRY_DETAIL_ID, countryId);
        interestingPlaceDetailFragment.setArguments(bundle);
        return interestingPlaceDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryDetailsViewModel = ViewModelProviders.of(this).get(CountryDetailsViewModel.class);
        if (savedInstanceState == null && getArguments() != null) {
            countryDetailsViewModel.requestCountryDetail(getArguments().getInt(ARG_COUNTRY_DETAIL_ID));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_interesting_place_detail, container, false);

        toolbar = view.findViewById(R.id.toolbar);
S
        countryDetailsViewModel.response.observe(getViewLifecycleOwner(), countryDetailResponseLiveData -> {
            if (countryDetailResponseLiveData != null) {
                if (countryDetailResponseLiveData.isFailureResponse()
                        && countryDetailResponseLiveData.getThrowable() != null) {
                    //TODO User Error
                    Log.e(TAG, countryDetailResponseLiveData.getThrowable().toString());
                } else if (countryDetailResponseLiveData.getApiResponse() != null) {
                    final Response<CountryDetail> response = countryDetailResponseLiveData.getApiResponse();
                    switch (response.code()) {
                        case 200:
                            if (response.body() != null) {
                                //TODO update views
                            }
                            break;
                        case 404:
                        case 500:
                            //TODO User Error
                            break;
                        default:
                    }
                }
            }
        });

        return view;
    }
}
