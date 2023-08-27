package com.abc.justjob.Candidate.CandidateActivityFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abc.justjob.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class jobsFragment extends Fragment {

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabOne,tabTwo;
    int mSelectedPosition;
    jobsPagerAdapter adapter;

    public jobsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_jobs, container, false);
        viewPager=view.findViewById(R.id.cnd_aplc_frg_view_pager);
        tabLayout=view.findViewById(R.id.cnd_home_tab_layout);
        tabOne=view.findViewById(R.id.cnd_home_tab_all_jobs_info);
        tabTwo=view.findViewById(R.id.cnd_home_tab_applied_job_info);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupWithViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("All jobs");
        tabLayout.getTabAt(1).setText("Application");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupWithViewPager(ViewPager viewPager) {
        adapter=new jobsPagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
    }

}