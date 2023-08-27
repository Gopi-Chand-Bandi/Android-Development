package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.CompanyFetch.companyProfileResult;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentTrackerActivity extends AppCompatActivity {

    CardView jobPost_track_card, application_track_card, cndView_track_card;

    LinearLayout postJobsDetails, apl_details, cndView_details;

    ImageView postViewBtn, aplViewBtn, cndViewBtn;

    private TextView postedJobCountTv,pojoBalanceCountTv,pojoDeletedCountTv;
    private TextView aplcCountTvReceived,aplcCountTvViewed,aplcCountTvSelected,aplcCountTvReject,aplcCountTvBalance;
    private TextView cndCountViewed,cndCountBanalce;

    private String cmpRegId;
    private String pojoPostedJobStr,pojoBalanceStr,pojoDeletedStr,aplcReceivedStr,aplcViewedStr,
            aplcSelectedStr,aplcRejectedStr,aplcBalanceStr,cndViewedStr, cndAccessedStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_tracker);

        //cardView
        jobPost_track_card = findViewById(R.id.jobPost_track_card);
        application_track_card = findViewById(R.id.application_track_card);
        cndView_track_card = findViewById(R.id.cndView_track_card);

        //linearLayout
        postJobsDetails = findViewById(R.id.postDetail);
        apl_details = findViewById(R.id.apl_details);
        cndView_details = findViewById(R.id.cndViewDetail);

        //arrowBtn
        postViewBtn = findViewById(R.id.post_view_Btn);
        aplViewBtn = findViewById(R.id.aplView_Btn);
        cndViewBtn = findViewById(R.id.cndView_view_Btn);

        //texview
        postedJobCountTv=findViewById(R.id.tracker_posted_job_count);
        pojoBalanceCountTv=findViewById(R.id.balance_job_count);
        pojoDeletedCountTv=findViewById(R.id.delete_job_count);

        aplcCountTvReceived=findViewById(R.id.received_cnd_apl_count);
        aplcCountTvViewed=findViewById(R.id.viewe_cnd_apl_count);
        aplcCountTvSelected=findViewById(R.id.select_cnd_apl_count);
        aplcCountTvReject=findViewById(R.id.reject_cnd_apl_count);
        aplcCountTvBalance=findViewById(R.id.balance_cnd_apl_count);

        cndCountViewed=findViewById(R.id.view_cnd_count);
        cndCountBanalce=findViewById(R.id.balance_cnd_count);

        cmpRegId= SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        getdataOfCount(cmpRegId);

//        Toast.makeText(this, cmpRegId, Toast.LENGTH_SHORT).show();


        postViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postJobsDetails.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(jobPost_track_card, new AutoTransition());
                    postJobsDetails.setVisibility(View.VISIBLE);
                    postViewBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(jobPost_track_card, new AutoTransition());
                    postJobsDetails.setVisibility(View.GONE);
                    postViewBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        aplViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (apl_details.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(application_track_card, new AutoTransition());
                    apl_details.setVisibility(View.VISIBLE);
                    aplViewBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(application_track_card, new AutoTransition());
                    apl_details.setVisibility(View.GONE);
                    aplViewBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        cndViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cndView_details.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cndView_track_card, new AutoTransition());
                    cndView_details.setVisibility(View.VISIBLE);
                    cndViewBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cndView_track_card, new AutoTransition());
                    cndView_details.setVisibility(View.GONE);
                    cndViewBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

    }

    private void getdataOfCount(String cmpRegId) {

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<cmpTrackerModel> call = api.cmpTracker(cmpRegId);

        call.enqueue(new Callback<cmpTrackerModel>() {
            @Override
            public void onResponse(Call<cmpTrackerModel> call, Response<cmpTrackerModel> response) {
                try {
                    if (!response.body().isError()) {

                        pojoPostedJobStr=response.body().getTracker_pojo_post_job();
                        pojoBalanceStr=response.body().getTracker_pojo_balance();
                        pojoDeletedStr=response.body().getTracker_pojo_deleted();
                        aplcReceivedStr=response.body().getTracker_aplc_received();
                        aplcViewedStr=response.body().getTracker_aplc_viewed();
                        aplcSelectedStr=response.body().getTracker_aplc_selected();
                        aplcRejectedStr=response.body().getTracker_aplc_rejected();
                        aplcBalanceStr=response.body().getTracker_aplc_balance();
                        cndViewedStr=response.body().getTracker_cnd_viewed();
                        cndAccessedStr=response.body().getTracker_cnd_balance();

//                        Toast.makeText(PaymentTrackerActivity.this, pojoDeletedStr, Toast.LENGTH_SHORT).show();

                        setDataFromDatabase();

                    }else {
                        Toast.makeText(PaymentTrackerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PaymentTrackerActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<cmpTrackerModel> call, Throwable t) {
                Toast.makeText(PaymentTrackerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataFromDatabase() {
        postedJobCountTv.setText(pojoPostedJobStr);
        pojoBalanceCountTv.setText(pojoBalanceStr);
        pojoDeletedCountTv.setText(pojoDeletedStr);
        aplcCountTvReceived.setText(aplcReceivedStr);
        aplcCountTvViewed.setText(aplcViewedStr);
        aplcCountTvSelected.setText(aplcSelectedStr);
        aplcCountTvReject.setText(aplcRejectedStr);
        aplcCountTvBalance.setText(aplcBalanceStr);
        cndCountViewed.setText(cndViewedStr);
        cndCountBanalce.setText(cndAccessedStr);
    }
}