package com.example.android.safar.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.safar.ui.FavAttractionsFragment;
import com.example.android.safar.ui.FavCountriesFragment;

public class FavouritesFragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public FavouritesFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Countries";

            default:
                return "Attractions";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavCountriesFragment();

            default:
                return new FavAttractionsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}














