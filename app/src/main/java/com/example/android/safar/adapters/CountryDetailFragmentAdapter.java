package com.example.android.safar.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.safar.ui.AttractionsFragment;
import com.example.android.safar.ui.CultureFragment;
import com.example.android.safar.ui.DishesFragment;

public class CountryDetailFragmentAdapter extends FragmentPagerAdapter {

    private final Context context;

    public CountryDetailFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Attractions";

            case 1:
                return "Culture";

            default:
                return "Dishes";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AttractionsFragment();

            case 1:
                return new CultureFragment();

            default:
                return new DishesFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}












