package com.abc.justjob.Candidate.CndCompanies;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.abc.justjob.Candidate.CandidateActivityFragment.ApplicationFragment;
import com.abc.justjob.Candidate.CandidateActivityFragment.HomeFragment;

public class CndCompaniesPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private FragmentManager fragmentManager;

    public CndCompaniesPagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CndCompaniesAll();
            case 1:
                return new CndCompaniesContacted();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        Fragment fragment = (Fragment) object;
//        fragmentManager.beginTransaction().remove(fragment).commit();
//        super.destroyItem(container, position, object);
//    }
}
