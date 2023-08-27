package com.abc.justjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.abc.justjob.Candidate.CandidateActivityFragment.CandidateActivity;
import com.abc.justjob.Company.CompanyActivitys.CompanyActivity;

public class SplashActivity extends AppCompatActivity {

//    private boolean isDarkMode;

    private ImageView imageView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        checkNightModeActivated();

        imageView=findViewById(R.id.ss_img);
        relativeLayout=findViewById(R.id.ss_layout);

//        if (isDarkMode) {
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash));
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.white));
//        } else {
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//            relativeLayout.setBackgroundColor(getResources().getColor(R.color.drawable_icon_tint));
//        }

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                if (SharedPrefManager.getInstance(getApplicationContext()).GetIsCandidate(getApplicationContext())==null){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }else if (SharedPrefManager.getInstance(getApplicationContext()).GetIsCandidate(getApplicationContext()).equals("true")) {
                    startActivity(new Intent(SplashActivity.this, CandidateActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, CompanyActivity.class));
                }
                finish();
            }
        }, secondsDelayed * 2000);

    }


//    public void checkNightModeActivated() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
//            isDarkMode=true;
//        }else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            isDarkMode=false;
//        }
//    }

}