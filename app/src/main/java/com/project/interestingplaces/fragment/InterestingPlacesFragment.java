package com.project.interestingplaces.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.interestingplaces.R;
import com.project.interestingplaces.adapter.InterestingPlacesAdapter;
import com.project.interestingplaces.model.Country;
import com.project.interestingplaces.viewmodel.CountryViewModel;

import java.util.List;

import retrofit2.Response;

public class InterestingPlacesFragment extends BaseFragment {

    public static final String TAG = InterestingPlacesFragment.class.getSimpleName();
    private CountryViewModel countryViewModel;
    private InterestingPlacesAdapter interestingPlacesAdapter;

    private RecyclerView countryRv;
    public static InterestingPlacesFragment create() {
        return new InterestingPlacesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        interestingPlacesAdapter = new InterestingPlacesAdapter();

        if (savedInstanceState == null) {
            countryViewModel.requestCountries();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_interesting_places, container, false);
        final Toast toast = Toast.makeText(requireContext(), getString(R.string.user_error),Toast.LENGTH_SHORT);

        countryRv = view.findViewById(R.id.recycler_country);

        countryViewModel.response.observe(getViewLifecycleOwner(), countriesResponseLiveData -> {
            if (countriesResponseLiveData != null) {
                if (countriesResponseLiveData.isFailureResponse()
                        && countriesResponseLiveData.getThrowable() != null) {
                    toast.show();
                    Log.e(TAG, countriesResponseLiveData.getThrowable().toString());
                } else if (countriesResponseLiveData.getApiResponse() != null) {
                    final Response<List<Country>> response = countriesResponseLiveData.getApiResponse();
                    switch (response.code()) {
                        case 200:
                            if (response.body() != null) {
                                interestingPlacesAdapter.setNewAdapterItems(sortValues(response.body()));
                            }
                            break;
                        case 404:
                        case 500:
                            toast.show();
                            break;
                        default:
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle(getString(R.string.country_title));
        }

        countryRv.setAdapter(interestingPlacesAdapter);
        interestingPlacesAdapter.setOnClickListener(countryId -> {
            fragmentHandler.replaceFragment(InterestingPlaceDetailFragment.create(countryId));
        });

    }

    public List<Country> sortValues(List<Country> unSorted) {
        Country country;
        for (int index = 0; index < unSorted.size(); index++) {
            for (int nestedIndex = index; nestedIndex > 0; nestedIndex--) {
                if (unSorted.get(nestedIndex).getDate() < unSorted.get(nestedIndex - 1).getDate()) {
                    country = unSorted.get(nestedIndex);
                    unSorted.set(nestedIndex, unSorted.get(nestedIndex - 1));
                    unSorted.set(nestedIndex - 1, country);
                }
            }
        }
        return unSorted;
    }
}
