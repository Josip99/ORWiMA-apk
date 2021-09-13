package com.example.planiranjeobroka;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Locale;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 2;
    private static final String BASE_NAME = "Tab #%d";

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return NewMeal.newInstance();
            default:
                return MakeMeals.newInstance();
        }
    }
    @Nullable
    @Override public CharSequence getPageTitle(int position) {
        return String.format(Locale.getDefault(), BASE_NAME, position + 1);
    }
    @Override public int getCount() {
        return NUM_PAGES;
    }
}
