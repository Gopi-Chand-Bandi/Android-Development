package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CmpPayActivity extends AppCompatActivity implements PaymentResultListener {

    private int JobPosts,Application_Unlocks,CandidateContactUnlocks,CandidateProfileViews,
            CompanyProfileViews,CompanyUnlocks,Validity;
    private String cmpRegId,cmpDays,expireDate,PlanName,cmpRegName,Email,ContactNumber;

    private double amount;

    private TextView planName,UserName,jobPosts,applicationUnlocks,candidate_contactUnlocks,companyUnlocks,validity,price;

    private AppCompatButton Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cmp_pay);

        planName=findViewById(R.id.Payment_Plan);
        UserName=findViewById(R.id.Payment_UserName);
        jobPosts=findViewById(R.id.Payment_JobPosts);
        applicationUnlocks=findViewById(R.id.Payment_ApplicationUnlocks);
        candidate_contactUnlocks=findViewById(R.id.Payment_CandidateContactUnlocks);
        companyUnlocks=findViewById(R.id.Payment_CompanyUnlocks);
        validity=findViewById(R.id.Payment_PlanValidity);
        price=findViewById(R.id.Payment_PlanPrice);
        Button=findViewById(R.id.payment_btn);

        Checkout.preload(getApplicationContext());

        cmpRegName = SharedPrefManager.getInstance(getApplicationContext()).getUserName(getApplicationContext());


        Bundle bundle = getIntent().getExtras();
        //amount = bundle.getInt("cmpAmount");
        amount = bundle.getDouble("cmpAmount");
        cmpRegId=bundle.getString("cmpRegId");
        cmpDays=bundle.getString("cmpDays");
        expireDate=bundle.getString("expireDate");
        JobPosts=bundle.getInt("JobPosts");
        Application_Unlocks=bundle.getInt("Application_Unlocks");
        CandidateContactUnlocks=bundle.getInt("CandidateContactUnlocks");
        CandidateProfileViews=bundle.getInt("CandidateProfileViews");
        CompanyProfileViews=bundle.getInt("CompanyProfileViews");
        CompanyUnlocks=bundle.getInt("CompanyUnlocks");
        Validity=bundle.getInt("Validity");
        PlanName=bundle.getString("PlanName");
        ContactNumber=bundle.getString("ContactNumber");
        Email= bundle.getString("Email");


        planName.setText(PlanName);
        UserName.setText(cmpRegName);
        jobPosts.setText(String.valueOf(JobPosts));
        applicationUnlocks.setText(String.valueOf(Application_Unlocks));
        candidate_contactUnlocks.setText(String.valueOf(CandidateContactUnlocks));
        companyUnlocks.setText(String.valueOf(CompanyUnlocks));
        validity.setText(String.valueOf(Validity));
        validity.setText(validity.getText()+" Days");
        price.setText(String.valueOf(amount/100));

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment();
                //insertToDatabase("Hai Hello Namaste");
            }
        });

    }

    private void makePayment(){

        Checkout checkout=new Checkout();
        //checkout.setKeyID("rzp_live_jIbYN5rAx0mxj3");
        checkout.setKeyID("rzp_live_jIbYN5rAx0mxj3");
        checkout.setImage(R.drawable.splash);

        final Activity activity=this;

        try{
            JSONObject object=new JSONObject();

            object.put("name","JustJobs HR");
            object.put("Description","Plan");
            object.put("theme.color","#0d0d0d");
            object.put("currency","INR");
            object.put("amount",amount);
            //object.put("image","https://s3.amazonaws.com./rzp-mobile/images/rzp.png");
            //object.put("order_id","");
            object.put("prefill.contact",ContactNumber);
            object.put("prefill.email",Email);
            checkout.open(activity,object);

        }catch (Exception e) {
            //e.printStackTrace();
            Log.e("TAG","Error in starting Razorpay Checkout",e);
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
//        Toast.makeText(this, "Payment Don.", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "cmpRegId: "+cmpRegId+"\nExpireDate: "+expireDate+"\nPayed: "+amount, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Payment Successful",Toast.LENGTH_SHORT).show();
        finish();
        insertToDatabase(s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void insertToDatabase(String transactionId) {

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
        String payedDate = getDateTime();
        String StartingDate=getDate();

//        Call<Result> call = api.userPaymentInfo(cmpRegId,payedDate,expireDate,transectionId,cmpDays,amount,dataAccess,
//                dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);
        Call<Result> call = api.userPaymentInfo(cmpRegId,Email,payedDate,StartingDate,expireDate,transactionId,cmpDays,amount,JobPosts,
                Application_Unlocks,CandidateContactUnlocks,CompanyUnlocks,Validity);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Toast.makeText(getApplicationContext(), Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}