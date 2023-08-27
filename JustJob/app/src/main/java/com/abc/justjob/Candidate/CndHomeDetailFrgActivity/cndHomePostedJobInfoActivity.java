package com.abc.justjob.Candidate.CndHomeDetailFrgActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class cndHomePostedJobInfoActivity extends AppCompatActivity {

    public String cmpPostJobIdStr;
    private boolean cndHomeBl,cndApplied;
    public NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnd_home_posted_job_info);

        getDataFromIntent();
//        initOperation();

        TabLayout tabLayout=findViewById(R.id.cnd_home_detail_tab_layout);
        TabItem tiJobInfo=findViewById(R.id.cnd_home_detail_tab_job_info);
        TabItem tiCmpInfo=findViewById(R.id.cnd_home_detail_tab_cmp_info);
        ViewPager viewPager=findViewById(R.id.cnd_home_detail_vp);

        PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),cmpPostJobIdStr,cndHomeBl);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.getTabAt(0).setText("Job Details");
        tabLayout.getTabAt(1).setText("Company Info");

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

    public void getDataFromIntent() {

        Bundle bundle=getIntent().getExtras();
        cmpPostJobIdStr = bundle.getString("cmp_post_job_id");
        cndHomeBl=bundle.getBoolean("cnd_home_frg");
    }
}