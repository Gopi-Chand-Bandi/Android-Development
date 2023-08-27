package com.abc.justjob.Company.PlaneActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

public class cmpPlanePremiumPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmp_plane_premium_post);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        checkNightModeActivated();

        Button premPost_btn = findViewById(R.id.premium_buy_activity_btn);

    }

//    public void checkNightModeActivated() {
//        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//    }
}