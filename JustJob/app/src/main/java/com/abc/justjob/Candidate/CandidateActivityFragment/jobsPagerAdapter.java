package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.abc.justjob.Candidate.CndAplcActivity.cndAplcAppledJobsFragment;
import com.abc.justjob.Candidate.CndAplcActivity.cndAplcCallsHistoryFragment;

public class jobsPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public jobsPagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new ApplicationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
