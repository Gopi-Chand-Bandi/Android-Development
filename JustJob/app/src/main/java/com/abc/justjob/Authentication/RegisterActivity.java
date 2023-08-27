package com.abc.justjob.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.ApiFile.LoginRegisterApi.Users;
import com.abc.justjob.Candidate.CandidateActivityFragment.CandidateActivity;
import com.abc.justjob.Candidate.CandidateActivityFragment.CndRegisterationActivity;
import com.abc.justjob.Company.CompanyActivitys.CompanyActivity;
import com.abc.justjob.Company.CompanyActivitys.CompanyRegistrationActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    ImageView imageView;
    TextInputEditText txt_fullName,txt_Email,txt_Password,txt_cfm_Password;
    Button btn_Register;
    TextView TextViewSignIn;

    private boolean isCandidate,isDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsCandidate(getApplicationContext()).equals("true")) {
            isCandidate=true;
        }else{
            isCandidate=false;
        }
//        checkNightModeActivated();

        imageView=findViewById(R.id.register_iv_logo);
        txt_fullName=findViewById(R.id.full_name_reg);
        txt_Email=findViewById(R.id.email_reg);
        txt_Password=findViewById(R.id.password_reg);
        txt_cfm_Password=findViewById(R.id.cfm_password_reg);
        btn_Register=findViewById(R.id.btn_register);
        TextViewSignIn=findViewById(R.id.text_btn_si);


//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash));
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));

//        if (isDarkMode) {
//        } else {
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        }

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSuccess();
            }
        });
        TextViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

   /* public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkMode=true;
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkMode=false;
        }


    }*/


    private void validateSuccess() {

        String nameStr=txt_fullName.getText().toString();
        String emailStr=txt_Email.getText().toString();
        String passStr=txt_Password.getText().toString();
        String cfmPassStr=txt_cfm_Password.getText().toString();

        if (nameStr.isEmpty()) {
            Toast.makeText(this, "user name is getting invalid input...", Toast.LENGTH_SHORT).show();
        }else if (emailStr.isEmpty()||!isValidEmailId(emailStr.trim())){
            Toast.makeText(this, "Email Id is getting invalid input...", Toast.LENGTH_SHORT).show();
        }else if (passStr.isEmpty()){
            Toast.makeText(this, "Password is getting Invalid input...", Toast.LENGTH_SHORT).show();
        }else if (cfmPassStr.isEmpty()||!cfmPassStr.matches(passStr)){
            Toast.makeText(this, "Password not same as previous...", Toast.LENGTH_SHORT).show();
        }else {
            if (isCandidate){

                String[] words = nameStr.split("\\s+");

                StringBuilder convertedName = new StringBuilder();
                for (String word : words) {

                    String convertedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();

                    convertedName.append(convertedWord).append(" ");
                }

                nameStr=convertedName.toString();


                //defining a progress dialog to show while signing up
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Signing Up...");
                progressDialog.show();

                ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
                //Toast.makeText(this,"hello..",Toast.LENGTH_SHORT).show();
                Users users = new Users(nameStr, emailStr, passStr);
                Call<Result> call = api.getCandidateRegi(
                        users.getUserName(),
                        users.getUserEmail(),
                        users.getUserPassword()
                );
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        //hiding progress dialog
                        progressDialog.dismiss();

                        //Toast.makeText(getApplicationContext(), Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();

                        //if there is no error
                        if (!response.body().getError()) {
                            //starting profile activity

                            SharedPrefManager.getInstance(getApplicationContext()).saveUserEmail(getApplicationContext(),emailStr);
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserName(getApplicationContext(),users.getUserName());
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserId(getApplicationContext(),response.body().getUsers_id());
                           //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            ApiInterface api_interface = ApiClient.getApiClient().create(ApiInterface.class);
                            //Call<Result> result=api_interface.getCandidateLogin(users.getUserEmail(), users.getUserPassword());
                            Call<Result> result=api_interface.getCandidateLogin(emailStr, passStr);
                            result.enqueue(new Callback<Result>() {
                                @Override
                                public void onResponse(Call<Result> result, Response<Result> resp) {
                                    //dialog.dismiss();
                                    try {
                                        if (!resp.body().getError()) {
                                            //Toast.makeText(RegisterActivity.this, resp.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            SharedPrefManager.getInstance(getApplicationContext()).saveUserId(getApplicationContext(),resp.body().getUsers_id());
                                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(resp.body().getUsers_id());

                                            SharedPrefManager.getInstance(getApplicationContext()).saveUserEmail(getApplicationContext(),emailStr);
                                            SharedPrefManager.getInstance(getApplicationContext()).saveUserName(getApplicationContext(),users.getUserName());

                                            boolean fullFeg=resp.body().isResponseReg();
                                            if (fullFeg) {
                                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullFeg);
                                                startActivity(new Intent(RegisterActivity.this, CandidateActivity.class));
                                                finish();
                                            }else {
                                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullFeg);
                                                Intent intent=new Intent(RegisterActivity.this, CndRegisterationActivity.class);
                                                intent.putExtra("isEditPart",false);
                                                startActivity(intent);
//                              startActivity(new Intent(LoginActivity.this, CndRegisterationActivity.class));
                                                finish();
                                                //Toast.makeText(RegisterActivity.this, resp.body().getUsers_id(), Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(RegisterActivity.this, resp.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        //Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Result> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });
                            //finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }else if (!isCandidate) {

                String[] words = nameStr.split("\\s+");

                StringBuilder convertedName = new StringBuilder();
                for (String word : words) {

                    String convertedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();

                    convertedName.append(convertedWord).append(" ");
                }

                nameStr=convertedName.toString();


                //defining a progress dialog to show while signing up
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Signing Up...");
                progressDialog.show();

                ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);

                Call<Result> call=api.getCompanyReg(nameStr,emailStr,passStr);

                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {

                        progressDialog.dismiss();

                        if (!response.body().getError()) {
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserEmail(getApplicationContext(),emailStr);
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserName(getApplicationContext(),response.body().getUser_name());
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserId(getApplicationContext(),response.body().getUsers_id());

                            ApiInterface api_interface = ApiClient.getApiClient().create(ApiInterface.class);

                            Call<Result> result=api_interface.getCompanyLogin(emailStr, passStr);
                            result.enqueue(new Callback<Result>() {
                                @Override
                                public void onResponse(Call<Result> result, Response<Result> resp) {
                                    try {
                                        if (!resp.body().getError()) {
                                            //Toast.makeText(RegisterActivity.this, resp.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            SharedPrefManager.getInstance(getApplicationContext()).saveUserId(getApplicationContext(),resp.body().getUsers_id());
                                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(resp.body().getUsers_id());

                                            SharedPrefManager.getInstance(getApplicationContext()).saveUserEmail(getApplicationContext(),emailStr);
                                            SharedPrefManager.getInstance(getApplicationContext()).saveUserName(getApplicationContext(),response.body().getUser_name());

                                            boolean fullFeg=resp.body().isResponseReg();
                                            if (fullFeg) {
                                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullFeg);
                                                startActivity(new Intent(RegisterActivity.this, CompanyActivity.class));
                                                finish();
                                            }else {
                                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullFeg);
                                                Intent intent=new Intent(RegisterActivity.this, CompanyRegistrationActivity.class);
                                                //intent.putExtra("isEditPart",false);
                                                startActivity(intent);
//                              startActivity(new Intent(LoginActivity.this, CndRegisterationActivity.class));
                                                finish();
                                                //Toast.makeText(RegisterActivity.this, resp.body().getUsers_id(), Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(RegisterActivity.this, resp.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        //Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Result> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });


                        }

                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                });

//                call.enqueue(new Callback<Result>() {
//                    @Override
//                    public void onResponse(Call<Result> call, Response<Result> response) {
//                        //hiding progress dialog
//                        progressDialog.dismiss();
//
//                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        //if there is no error
//                        if (!response.body().getError()) {
//                            //starting profile activity
//                            SharedPrefManager.getInstance(getApplicationContext()).saveUserEmail(getApplicationContext(),emailStr);
//                            //SharedPrefManager.getInstance(getApplicationContext()).saveUserName(getApplicationContext(),nameStr);
//                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Result> call, Throwable t) {
//                        //Toast.makeText(RegisterActivity.this, call.toString()+"\n"+t.toString(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                    }
//                });
            }
        }
    }

    private boolean isValidEmailId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
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
}