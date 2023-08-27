 package com.abc.justjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.abc.justjob.Authentication.LoginActivity;
import com.abc.justjob.Authentication.RegisterActivity;
import com.abc.justjob.Candidate.CandidateActivityFragment.CndRegisterationActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    private static boolean isCandidate = false;

    private ImageView imageView;
//    private boolean isDarkMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        checkThemeSelected();

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        imageView=findViewById(R.id.main_icon_image_v);

//        if (isDarkMode) {
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash));
//        } else {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        }

        findViewById(R.id.temp_candidate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).isCandidate("false");
            }
        });
        findViewById(R.id.search_jobs_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).isCandidate("true");
            }
        });
    }

   /* private void checkThemeSelected() {
        if (!SharedPrefManager.getInstance(getApplicationContext()).GetisDialogActivated(getApplicationContext())) {
            showAlertDialog();
            SharedPrefManager.getInstance(getApplicationContext()).isDialogActivated(true);
        }
    }*/

//    @Override
//    protected void onResume() {
//        super.onResume();

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        checkThemeSelected();
//    }

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
            isDarkMode=true;
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkMode=false;
        }
    }*/


//    private void showAlertDialog() {

//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
//        alertDialog.setTitle("Select Theme");
//        String[] items = {"Light Mode","Dark Mode"};
//        int checkedItem = 0;
//        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case 0:
//                        isDarkMode=false;
//                        break;
//                    case 1:
//                        isDarkMode=true;
//                        break;
//                }
//            }
//        });
//        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                saveNightModeState(isDarkMode);
//            }
//        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        AlertDialog alert = alertDialog.create();
//        alert.setCanceledOnTouchOutside(false);
//        alert.show();
//    }

//    private void saveNightModeState(boolean b) {
//
//        SharedPrefManager.getInstance(getApplicationContext()).isDarkMode(b);
//
//        if (b) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//    }



    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public void displayToastMsg(View v) {
        toastMsg("We are currently working on it, please use our website - Justjobshr.com");

    }
}
