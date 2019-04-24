package com.project.interestingplaces;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false);
    }

    public void replaceFragment(Fragment fragment, boolean preserveNavigation) {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        final Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        final String currentFragmentTag = currentFragment.getClass().getSimpleName();
        final String newFragmentTag = fragment.getClass().getSimpleName();

        if (currentFragmentTag.equals(newFragmentTag)) {
            return;
        }

        if (preserveNavigation) {
            popBackStackAllEntries();
        }

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void popBackStackAllEntries() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
