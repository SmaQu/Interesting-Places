package com.project.interestingplaces.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.interestingplaces.R;
import com.project.interestingplaces.model.CountryDetail;
import com.project.interestingplaces.util.DateParser;
import com.project.interestingplaces.viewmodel.CountryDetailsViewModel;

import retrofit2.Response;

public class InterestingPlaceDetailFragment extends BaseFragment {

    public static final String TAG = InterestingPlaceDetailFragment.class.getSimpleName();
    private static final String ARG_COUNTRY_DETAIL_ID = "arg_country_detail_id";
    private CountryDetailsViewModel countryDetailsViewModel;
    private Toast toast = Toast.makeText(requireContext(), getString(R.string.user_error), Toast.LENGTH_SHORT);

    private ActionBar actionBar;

    private ProgressBar loadingPb;
    private ImageView countryPictureIv;
    private TextView countryDescriptionTv;
    private TextView visitDateTv;
    private Button showMoreBt;

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

        loadingPb = view.findViewById(R.id.progress_load_details);
        countryPictureIv = view.findViewById(R.id.image_detail_country_picture);
        countryDescriptionTv = view.findViewById(R.id.text_country_description);
        visitDateTv = view.findViewById(R.id.text_detail_visit_date);
        showMoreBt = view.findViewById(R.id.button_show_more);

        countryDetailsViewModel.response.observe(getViewLifecycleOwner(), countryDetailResponseLiveData -> {
            if (countryDetailResponseLiveData != null) {
                if (countryDetailResponseLiveData.isFailureResponse()
                        && countryDetailResponseLiveData.getThrowable() != null) {
                    toast.show();
                    Log.e(TAG, countryDetailResponseLiveData.getThrowable().toString());
                } else if (countryDetailResponseLiveData.getApiResponse() != null) {
                    final Response<CountryDetail> response = countryDetailResponseLiveData.getApiResponse();
                    switch (response.code()) {
                        case 200:
                            if (response.body() != null) {
                                loadingPb.setVisibility(View.GONE);
                                showMoreBt.setVisibility(View.VISIBLE);
                                final CountryDetail countryDetail = response.body();

                                Glide.with(this)
                                        .load(countryDetail.getPictureUrl())
                                        .into(countryPictureIv);

                                countryDescriptionTv.setText(countryDetail.getDescription());
                                visitDateTv.setText(DateParser.formatDate(countryDetail.getDate()));
                                actionBar.setTitle(countryDetail.getName());

                                showMoreBt.setOnClickListener(v -> {
                                    final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(countryDetail.getSeeMoreUrl()));
                                    startActivity(intent);
                                });
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
        actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        showMoreBt.setVisibility(View.GONE);
        loadingPb.setVisibility(View.VISIBLE);

    }
}
