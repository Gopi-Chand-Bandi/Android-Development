package com.abc.justjob.Candidate.CndAplcActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class cndAplcPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public cndAplcPagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new cndAplcAppledJobsFragment();
            case 1:
                return new cndAplcCallsHistoryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
