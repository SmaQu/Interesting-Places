package com.project.interestingplaces;

import android.os.Bundle;

import com.project.interestingplaces.fragment.InterestingPlacesFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(InterestingPlacesFragment.create());
        }
    }
}
