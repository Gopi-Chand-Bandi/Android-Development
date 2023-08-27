package com.abc.justjob.Company.CompanyPostJob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.abc.justjob.Company.CompanyActivitys.CompanyActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

public class PostJobActivity extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    private FrameLayout postJobFrameLayout;

    public String activityTitleStr, activityRoleStr,activityDesignationStr, activityTypeStr, activityStateStr,activityCityStr;
    public String activitydescriptionStr,activitysalaryTimeStr, activityminSalaryStr, activitymaxSalaryStr, activityminAgeStr, activitymaxAgeStr, activitylocationStr, activitynumberLocationStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

//        checkNightModeActivated();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        postJobFrameLayout = findViewById(R.id.post_job_fragments_container);

        getSupportFragmentManager().beginTransaction().add(R.id.post_job_fragments_container, new PoJo1Fragment()).commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        startActivities(new Intent[]{new Intent(PostJobActivity.this, CompanyActivity.class)});
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    /*public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }*/

    public void onSuperBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    public void fragment1(String titleStr, String roleStr, String designationStr, String typeStr, String stateStr, String cityStr){

        activityTitleStr=titleStr;
        activityRoleStr=roleStr;
        activityDesignationStr=designationStr;
        activityTypeStr=typeStr;
        activityStateStr=stateStr;
        activityCityStr=cityStr;

    }

    public void Fragment3(String descriptionStr, String salaryTimeStr, String minSalaryStr, String maxSalaryStr, String minAgeStr, String maxAgeStr, String locationStr, String numberLocationStr) {
        activitydescriptionStr=descriptionStr;
        activitysalaryTimeStr=salaryTimeStr;
        activityminSalaryStr=minSalaryStr;
        activitymaxSalaryStr=maxSalaryStr;
        activityminAgeStr=minAgeStr;
        activitymaxAgeStr=maxAgeStr;
        activitylocationStr=locationStr;
        activitynumberLocationStr=numberLocationStr;
    }
}