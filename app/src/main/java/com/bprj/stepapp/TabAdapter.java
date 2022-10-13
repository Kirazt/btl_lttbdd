package com.bprj.stepapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bprj.stepapp.Item.items_activity;
import com.bprj.stepapp.ranking.ranking_activity;

public class TabAdapter extends FragmentStatePagerAdapter {

     public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StepCounter_Activity();
            case 1:
                return new ranking_activity();
            case 2:
                return new account_activity();
            case 3:
                return new Support_Activity();
            default:
                return new StepCounter_Activity();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
