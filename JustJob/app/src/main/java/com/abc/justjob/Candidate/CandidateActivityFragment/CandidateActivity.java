package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.abc.justjob.Candidate.CndAplcActivity.cndAplcPagerAdapter;
import com.abc.justjob.MainActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class CandidateActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView chipNavigationBar;
    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    boolean doubleBackToExitPressedOnce = false;
    //un use
    private static final long delay = 2000L;
    private boolean mRecentlyBackPressed = false;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {

        @Override
        public void run() {
            mRecentlyBackPressed=false;
        }
    };
    //....


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        if (!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            startActivity(new Intent(CandidateActivity.this, MainActivity.class));
            finish();
        }else if (!SharedPrefManager.getInstance(getApplicationContext()).getIsRegistered(getApplicationContext())) {
            startActivity(new Intent(CandidateActivity.this, CndRegisterationActivity.class));
            finish();
        }

        chipNavigationBar = findViewById(R.id.bottom_navigation);
        loadFragment(new HomeFragment());
        chipNavigationBar.setOnNavigationItemSelectedListener(this);
        chipNavigationBar.setSelectedItemId(R.id.nave_home);
        //bottomMenu();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){

            case R.id.nave_home:
                fragment=new jobsFragment();
                break;
            case R.id.nave_company:
                fragment=new CompaniesFragment();
                break;
            case R.id.nave_help:
                fragment=new LearnFragment();
                break;
            case R.id.nave_profile:
                fragment=new ProfileFragment();
                break;

        }
        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commitAllowingStateLoss();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mRecentlyBackPressed) {
            mExitHandler.removeCallbacks(mExitRunnable);
            mExitHandler = null;
            super.onBackPressed();
            finishAffinity();
        }else if (chipNavigationBar.getSelectedItemId() != R.id.nave_home) {
            chipNavigationBar.setSelectedItemId(R.id.nave_home);
        }else if (chipNavigationBar.getSelectedItemId() == R.id.nave_home) {
            //super.onBackPressed();
            mRecentlyBackPressed = true;
            Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
            mExitHandler.postDelayed(mExitRunnable, delay);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    /*public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }*/
}