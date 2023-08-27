package com.abc.justjob.Candidate.CandidateActivityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.abc.justjob.Candidate.CndAplcActivity.cndAplcPagerAdapter;
import com.abc.justjob.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ApplicationFragment extends Fragment {

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabOne,tabTwo;
    int mSelectedPosition;
    cndAplcPagerAdapter adapter;

    public ApplicationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_application, container, false);
        viewPager=view.findViewById(R.id.cnd_aplc_frg_view_pager);
        tabLayout=view.findViewById(R.id.cnd_aplc_frg_tab_layout);
        tabOne=view.findViewById(R.id.cnd_aplc_frg_tab_1);
        tabTwo=view.findViewById(R.id.cnd_aplc_frg_tab_2);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupWithViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Applied Jobs");
        tabLayout.getTabAt(1).setText("Calls History");

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
        adapter=new cndAplcPagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
    }
}