package com.abc.justjob.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Candidate.CandidateActivityFragment.CandidateActivity;
import com.abc.justjob.Candidate.CandidateActivityFragment.CndRegisterationActivity;
import com.abc.justjob.Company.CompanyActivitys.CompanyActivity;
import com.abc.justjob.Company.CompanyActivitys.CompanyRegistrationActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    ImageView imageView;
    EditText txt_Email, txt_Password;
    Button btn_Login;
    TextView TextViewRegister,tvChangePassword;
    private CheckBox policyCheck;
    private TextView policyTextView;
    private boolean policyIsChecked;
    private String emailStr,passStr;
    private boolean isCandidate;
//    private boolean isDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsCandidate(getApplicationContext()).equals("true")) {
            isCandidate=true;
        }else{
            isCandidate=false;
        }
//        checkOperation();


        imageView=findViewById(R.id.login_iv_logo);
        txt_Email=findViewById(R.id.email_log);
        txt_Password=findViewById(R.id.password_log);
        btn_Login=findViewById(R.id.btn_login);
        policyCheck=findViewById(R.id.login_check_policy);
        policyTextView=findViewById(R.id.login_text_policy);
        TextViewRegister=findViewById(R.id.text_btn_reg);
        tvChangePassword=findViewById(R.id.text_forget_password);


//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.splash));
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        if (isDarkMode) {
//        } else {
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        }

        policyTextView.setMovementMethod(LinkMovementMethod.getInstance());

        policyCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    policyIsChecked=true;
                }else{
                    policyIsChecked=false;
                }
            }
        });

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (policyIsChecked) {
                    emailStr = txt_Email.getText().toString();
                    passStr = txt_Password.getText().toString();

                    if (isEmpty(txt_Email) || !isValidEmailId(emailStr.trim())) {
                        txt_Email.setError("Invalid Email");
                    } else if (isEmpty(txt_Password)) {
                        txt_Password.setError("Invalid Password");
                    } else {
                        //Toast.makeText(LoginActivity.this, "Candidate Login: "+isCandidate, Toast.LENGTH_SHORT).show();
                        vaidationComplite(emailStr, passStr);
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Policy is not checked...!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        TextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ChangePasswordActivity.class));
            }
        });
    }

    /*public void checkOperation() {
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkMode=true;
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkMode=false;
        }


    }*/


    private void vaidationComplite(String emailStr, String passStr) {
        if (isCandidate){

//            Toast.makeText(this, "Candidate Login...", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, " Login... ", Toast.LENGTH_SHORT).show();

            final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Login...");
            dialog.show();

            ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Result> call=api.getCandidateLogin(emailStr,passStr);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    dialog.dismiss();
                    try {
                        if (!response.body().getError()) {
                            //Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            SharedPrefManager.getInstance(getApplicationContext()).saveUserId(getApplicationContext(),response.body().getUsers_id());
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUsers_id());
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserName(getApplicationContext(),response.body().getUser_name());
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserEmail(getApplicationContext(),emailStr);

                            boolean fullFeg=response.body().isResponseReg();
                            if (fullFeg) {
                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullFeg);
                                startActivity(new Intent(LoginActivity.this, CandidateActivity.class));
                                finish();
                            }else {
                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullFeg);
                                Intent intent=new Intent(LoginActivity.this, CndRegisterationActivity.class);
                                intent.putExtra("isEditPart",false);
                                startActivity(intent);
//                              startActivity(new Intent(LoginActivity.this, CndRegisterationActivity.class));
                                finish();
                                //Toast.makeText(LoginActivity.this, response.body().getUsers_id(), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }else if (!isCandidate){

            final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Login...");
            dialog.show();

            ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Result> call=api.getCompanyLogin(emailStr,passStr);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    dialog.dismiss();
                    try {
                        if (!response.body().getError()) {// Toast.makeText(LoginActivity.this, response.body().getUsers_id(), Toast.LENGTH_SHORT).show();
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserId(getApplicationContext(), response.body().getUsers_id());
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUsers_id());
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserName(getApplicationContext(),response.body().getUser_name());
                            SharedPrefManager.getInstance(getApplicationContext()).saveUserEmail(getApplicationContext(),emailStr);

                            boolean fullCmpReg=response.body().isResponseReg();
                            //Toast.makeText(LoginActivity.this, "True : "+fullCmpReg, Toast.LENGTH_SHORT).show();
                            if (fullCmpReg) {
                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullCmpReg);
                                startActivity(new Intent(LoginActivity.this, CompanyActivity.class));
                                finish();
                            }else{
                                SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),fullCmpReg);
                                startActivity(new Intent(LoginActivity.this, CompanyRegistrationActivity.class));
                                finish();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
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