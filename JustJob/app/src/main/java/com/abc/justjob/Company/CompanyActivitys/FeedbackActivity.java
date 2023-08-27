package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    private TextInputLayout emailEt,messageEt;
    private Button sendfbBt;
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        UserId= SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        emailEt=findViewById(R.id.feedback_email_et);
        messageEt=findViewById(R.id.feedback_message_et);
        sendfbBt=findViewById(R.id.feedback_send_btn);

        sendfbBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FeedbackActivity.this, UserId, Toast.LENGTH_SHORT).show();
                sendToDatabase();
            }
        });
    }

    private void sendToDatabase() {

        String emailStr,messageStr;
        emailStr=emailEt.getEditText().getText().toString();
        messageStr=messageEt.getEditText().getText().toString();

        Api_cmp_post_job api= ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call=api.feadBackOperation(UserId,emailStr,messageStr);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        emailEt.getEditText().setText("");
                        messageEt.getEditText().setText("");
                    }else{
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure For Feedback: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}