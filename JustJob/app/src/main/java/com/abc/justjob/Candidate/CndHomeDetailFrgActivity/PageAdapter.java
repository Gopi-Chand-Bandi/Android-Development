package com.abc.justjob.Candidate.CndHomeDetailFrgActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private String pojoId;
    private boolean homeBool;

    public PageAdapter(@NonNull FragmentManager fm, int numOfTabs, String pojoId, boolean homeBool) {
        super(fm);
        this.numOfTabs=numOfTabs;
        this.pojoId=pojoId;
        this.homeBool=homeBool;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                cndHomeCmpJobDetailFragment jobDetailFragment=new cndHomeCmpJobDetailFragment();
                Bundle bundle=new Bundle();
                bundle.putString("PostJobId",pojoId);
                bundle.putBoolean("home_bool",homeBool);
                jobDetailFragment.setArguments(bundle);
                return jobDetailFragment;
            case 1:
                cndHomeCompanyDetailFragment companyDetailFragment=new cndHomeCompanyDetailFragment();
                Bundle bundle1=new Bundle();
                bundle1.putString("PostJobId",pojoId);
                bundle1.putBoolean("home_bool",homeBool);
                companyDetailFragment.setArguments(bundle1);
                return companyDetailFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
