package com.example.kenan.calorify.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kenan.calorify.fragments.ProfileFragment;
import com.example.kenan.calorify.fragments.ScanFragment;
import com.example.kenan.calorify.fragments.SchemeFragment;

/**
 * Created by Kenan on 11/10/2017.
 */

public class MenuPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;

    public MenuPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return SchemeFragment.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment
                return ScanFragment.newInstance();
            case 2: // Fragment # 0 - This will show FirstFragment
                return ProfileFragment.newInstance();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return new String[] {"Scheme", "Scan", "Profile"}[position];
    }
}
