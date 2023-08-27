package com.abc.justjob.Candidate.CandidateActivityFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abc.justjob.Candidate.CndCompanies.CndCompaniesPagerAdapter;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class CompaniesFragment extends Fragment {

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabOne,tabTwo,tabThree;
    int mSelectedPosition;
    CndCompaniesPagerAdapter adapter;

    public CompaniesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_companies, container, false);
        viewPager=view.findViewById(R.id.cnd_companies_frg_view_pager);
        tabLayout=view.findViewById(R.id.cnd_companies_tab_layout);
        tabOne=view.findViewById(R.id.cnd_companies_tab_all_companies_info);
        tabTwo=view.findViewById(R.id.cnd_companies_tab_companies_contacted_info);
        //tabThree=view.findViewById(R.id.cnd_companies_tab_user_contacted_info);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupWithViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("All Companies");
        tabLayout.getTabAt(1).setText("Contacted");
        //tabLayout.getTabAt(2).setText(SharedPrefManager.getInstance(requireContext()).getUserName(requireContext())+" Contacted");

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
        adapter=new CndCompaniesPagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
    }
}