package com.abc.justjob.Company.CompanyActivitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Company.CompanyPostJob.PostJobActivity;
import com.abc.justjob.Company.NewPlaneActivity.CmpPlaneActivity;
import com.abc.justjob.MainActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.razorpay.PaymentResultListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private String cmpRegId;

    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    BottomNavigationView chipNavigationBarCompany;

    private static final long delay = 2000L;
    private boolean mRecentlyBackPressed = false;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {

        @Override
        public void run() {
            mRecentlyBackPressed=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        if (!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            startActivity(new Intent(CompanyActivity.this, MainActivity.class));
            finish();
        }else if (!SharedPrefManager.getInstance(getApplicationContext()).getIsRegistered(getApplicationContext())) {
            startActivity(new Intent(CompanyActivity.this, CompanyRegistrationActivity.class));
            finish();
            Toast.makeText(this, "not Register", Toast.LENGTH_SHORT).show();
        }
        cmpRegId = SharedPrefManager.getInstance(CompanyActivity.this).getValueOfUserId(CompanyActivity.this);
        calculateTenureDays();
        //loadFragment(new WorkFragment());
        chipNavigationBarCompany = findViewById(R.id.bottom_nav_company);
        chipNavigationBarCompany.setOnNavigationItemSelectedListener(this);
        chipNavigationBarCompany.setSelectedItemId(R.id.nave_home_comp);

        //bottomMenu();
    }

    private void calculateTenureDays(){
        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Void> call = api.calculateTenureDaysBalance(cmpRegId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try {
                    if (response.isSuccessful()) {
                        // Handle successful response
                        //Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle error response
                        Toast.makeText(getApplicationContext(), "API Call Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_company,fragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;

        switch (item.getItemId()){
            case R.id.nave_home_comp:
                fragment=new WorkFragment();
                break;

            case R.id.nave_other_company:
                fragment = new OtherCompaniesFragment();
                break;

//            case R.id.nave_myJob_comp:
////                fragment=new MyCandidatesFragment();
//                startActivity(new Intent(CompanyActivity.this,MyCandidateActivity.class));
//                finish();
//                break;

            //case R.id.nave_post_job:
//                startActivity(new Intent(CompanyActivity.this, PostJobActivity.class));
//                finish();

//                postjobOperation();
                //postJobCountOperation();
                //break;

            case R.id.nav_ComProfile:
                fragment=new CompanyProfileFragment();
                break;

            case R.id.nave_plan:
                fragment=new CompanyPlanFragmentNew();
                //fragment=new CompanyPlanFragment();
                //startActivity(new Intent(CompanyActivity.this, CmpPlaneActivity.class));
                break;
        }
        return loadFragment(fragment);
    }

    private void postJobCountOperation() {

        String cmpRegId=SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.cmpPostJobCountOperation(cmpRegId,getCurrentDate());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (!response.body().getError()) {

                        Toast.makeText(CompanyActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(CompanyActivity.this,PostJobActivity.class));
                        finish();
                    } else {
                        Toast.makeText(CompanyActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postjobOperation() {

        String cmpRegId=SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.cmpPostJobOperation(cmpRegId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (!response.body().getError()) {

                        Toast.makeText(CompanyActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(CompanyActivity.this,PostJobActivity.class));
                        finish();
                    } else {
                        Toast.makeText(CompanyActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mRecentlyBackPressed) {
            mExitHandler.removeCallbacks(mExitRunnable);
            mExitHandler = null;
            super.onBackPressed();
            finishAffinity();
        }else if (chipNavigationBarCompany.getSelectedItemId() != R.id.nave_home_comp){
            chipNavigationBarCompany.setSelectedItemId(R.id.nave_home_comp);
        }else if (chipNavigationBarCompany.getSelectedItemId() == R.id.nave_home_comp) {
            //super.onBackPressed();
            mRecentlyBackPressed = true;
            Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
            mExitHandler.postDelayed(mExitRunnable, delay);
        }
    }


    private String getCurrentDate() {

//        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

//    public void checkNightModeActivated() {
//        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//    }


/*
    @Override
    public void onPaymentSuccess(String s) {
        try {
            AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("Payment Id");
            builder.setMessage(s);
            builder.show();

            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Payment Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }*/
}