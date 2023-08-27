package com.abc.justjob.Company.NewPlaneActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.abc.justjob.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.razorpay.PaymentResultListener;

public class CmpPlaneActivity extends AppCompatActivity implements PaymentResultListener {

    TabLayout tabLayout;
    TabItem itPlan, nonItPlan;
    ViewPager planPager;

    PricePageAdapter pricePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmp_plane);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        tabLayout = findViewById(R.id.pricing_plan_tablayout);
        itPlan = findViewById(R.id.it_plan_tab);
        nonItPlan = findViewById(R.id.nonit_plan_tab);
        planPager = findViewById(R.id.pricing_plan_pager);

        pricePageAdapter = new PricePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        planPager.setAdapter(pricePageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                planPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1)
                    pricePageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        planPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Payment Id");
        builder.setMessage(s);
        builder.show();

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}