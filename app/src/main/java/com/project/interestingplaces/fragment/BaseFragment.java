package com.project.interestingplaces.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.project.interestingplaces.BaseActivity;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity fragmentHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            fragmentHandler = (BaseActivity) context;
        }
    }
}
