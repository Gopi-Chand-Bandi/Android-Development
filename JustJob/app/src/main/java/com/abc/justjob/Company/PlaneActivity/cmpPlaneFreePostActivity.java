package com.abc.justjob.Company.PlaneActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abc.justjob.Company.CompanyPostJob.PostJobActivity;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

public class cmpPlaneFreePostActivity extends AppCompatActivity {

    private AppCompatButton postJobBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmp_plane_free_post);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        checkNightModeActivated();

        postJobBtn=findViewById(R.id.free_post_acivity_btn);

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cmpPlaneFreePostActivity.this, PostJobActivity.class));
            }
        });

    }
//    public void checkNightModeActivated() {
//        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//    }
}