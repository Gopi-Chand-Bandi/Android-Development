package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.abc.justjob.VeiwResumeActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.AlertDialog;


public class CndListDetailsActivity extends AppCompatActivity
        implements cmpContactListDesign.contactListListener, PaymentResultListener {

    private TextView cndDetailsName,cndDetailsLocation,cndDetailsCity,cndDetailsState,cndDetailsGender, cndDetailsDob,
            cndDetailsJobType,cndDetailsEmploymentType,
            cndDetailsLanguage, cndDetailsCommunication,cndDetailsProfile1,cndDetailsDesignation1,cndDetailsProfile2,cndDetailsDesignation2, cndDetailsQualifiStd,cndDetailsQualifiStream,cndDetailsQualifiCollege,
            cndDetailsQualiStart, cndDetailsQualiEnd, cndDetailsExpFresher,cndDetailsExpRole,cndDetailsExpDesignation,cndDetailsExpIndustry,cndDetailsExpCompany,cndDetailsExpCurrentSalary, cndDetailsExpStart,
            cndDetailsExpEnd, cndDetailsVehicles, cndDetailsLicence,cndDetailsDocuments, cndDetailsSkill, cndDetailsReference;

    private LinearLayout cndDetailsExpFresherLayout,cndDetailsExpLayout, paymentLayout;
    private ScrollView cndDetailsLayout;

    private Button Contact;

    private String status,From,To,PremiumValidity,OnclickValidationDate,OnclickValidity,CompanyNumber,CompanyEmail;

    private Integer FreeContacts,PremiumDataBalance,PremiumBalanceDays,OnclickCount,payID;

    private Integer FreeDataBalance;

    private Integer OnclickValidatedCount;

    private Integer amount;

    private Double result;

    private String cmpRegId,cmpRegName,cmpRegEmail,cmpId, cndRegisterId, genderStr, nameStr, emailStr, contactStr, alterContactStr, stateStr,cityStr,
            dobStr, locationStr, communicationStr, profile1Str, designationOneStr, profile2Str, designationTwoStr, qualiStdStr,
            qualiCollegeNameStr, qualiStreamStr, qualiStartStr, qualiEndStr, fresherInternStr, expIndustry,
            expCompanyStr,expCurrentSalaryStr,expStartStr, expEndStr, employeeTypeStr,locationPreferStr, languageStr, vehicleStr, licenceStr,documentsStr,
            skillStr, referenceStr, resumeStr;

    private boolean tenCrosed=false;

    private FrameLayout paymentContainer;

    private boolean isHomePage;

    //paymet operation
    private static final String TAG = "Razorpay";

    private String order_receipt_no = "Receipt No. " + System.currentTimeMillis() / 1000;
    private String order_reference_no = "Reference No. #" + System.currentTimeMillis() / 1000;

    private boolean contactBln = false;

    private static final String url1="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=checkpremium&id=";
    private static final String url2="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=free_company_contactinfo&id=";
    private static final String url3="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=onclick_validation&fromId=";
    private static final String url4="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_payments.php?apicall=PaymentInfoFetch&id=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnd_list_details);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        cmpRegId = SharedPrefManager.getInstance(CndListDetailsActivity.this).getValueOfUserId(CndListDetailsActivity.this);
        cmpRegName = SharedPrefManager.getInstance(CndListDetailsActivity.this).getUserName(CndListDetailsActivity.this);
        cmpRegEmail = SharedPrefManager.getInstance(CndListDetailsActivity.this).getUserEmail(CndListDetailsActivity.this);

//        StrictMode.ThreadPolicy policy = new
//                StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        Checkout.preload(getApplicationContext());

        initMethod();

        setDataToTextView();

        onclickOperations();

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckValidation();

            }
        });

        //getPaymetnButtonOperation();
    }

    private void CheckValidation(){

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                url1 + cmpRegId,null,
                new com.android.volley.Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            PremiumValidity = response.getString("mistake");
                            if (Objects.equals(PremiumValidity,"Yes")){
                                Toast.makeText(CndListDetailsActivity.this,"Please take Premium Plan as soon as possible",Toast.LENGTH_SHORT).show();
                                balanceOfFree();
                            }else {
                                PremiumDataBalance = response.getInt("candidates_data_balance");
                                PremiumBalanceDays = response.getInt("tenure_days_balance");
                                if (PremiumBalanceDays==0){
                                    Toast.makeText(CndListDetailsActivity.this,"Your Plan is Expired",Toast.LENGTH_SHORT).show();
                                    balanceOfFree();
                                }else if (PremiumDataBalance==0){
                                    Toast.makeText(CndListDetailsActivity.this,"Your Candidate Contacting Data Balance is Over",Toast.LENGTH_SHORT).show();
                                    balanceOfFree();
                                }else {
                                    decreasePremiumCount();
                                    contactModeOpen();
                                }
                            }
                        }catch (JSONException e){
                            Log.d("Exception: ",e.getMessage());
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        try {
                            if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof AuthFailureError) {
                                Toast.makeText(getApplicationContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ParseError) {
                                Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void decreasePremiumCount(){

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Void> call = api.decrease_company_candidate_contact_premium_count(cmpRegId);
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

    private void balanceOfFree(){

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                url2 + cmpRegId,null,
                new com.android.volley.Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            FreeDataBalance = response.getInt("free_candidate_unlocks_balance");
                            if (FreeDataBalance==0){
                                Toast.makeText(CndListDetailsActivity.this,"Your Free Balance is also completed",Toast.LENGTH_SHORT).show();
                                checkForOnclickBalance();
                            }else {
                                decreaseFreeCount();
                                contactModeOpen();
                            }
                        }catch (JSONException e){
                            Log.d("Exception: ",e.getMessage());
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        try {
                            if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof AuthFailureError) {
                                Toast.makeText(getApplicationContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ParseError) {
                                Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void decreaseFreeCount(){

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Void> call = api.decrease_company_candidate_contact_free_count(cmpRegId);
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


    private void checkForOnclickBalance(){

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                url3 + cmpRegId + "&toId=" + cndRegisterId + "&contact_from=" + "Company" + "&contact_to" + "Candidate",null,
                new com.android.volley.Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            OnclickValidity = response.getString("mistake");
                            if (Objects.equals(OnclickValidity,"Yes")){
                                Toast.makeText(CndListDetailsActivity.this,"You need to take OnClick payment",Toast.LENGTH_SHORT).show();
                                getCompanyDetails();
                            }else {
                                OnclickValidatedCount = response.getInt("count");
                                if (OnclickValidatedCount==0){
                                    Toast.makeText(CndListDetailsActivity.this,"Your Onclick Balance is Expired",Toast.LENGTH_SHORT).show();
                                    getCompanyDetails();
                                }else {
                                    decreaseOnClickCount();
                                    contactModeOpen();
                                }
                            }
                        }catch (JSONException e){
                            Log.d("Exception: ",e.getMessage());
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        try {
                            if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof AuthFailureError) {
                                Toast.makeText(getApplicationContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ParseError) {
                                Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void getCompanyDetails(){

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                url4 + cmpRegId ,null,
                new com.android.volley.Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            CompanyNumber = response.getString("cmp_contact");
                            CompanyEmail = response.getString("cmp_email");
                            confirmationOfOnClick();
                        }catch (JSONException e){
                            Log.d("Exception: ",e.getMessage());
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        try {
                            if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof AuthFailureError) {
                                Toast.makeText(getApplicationContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ParseError) {
                                Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void decreaseOnClickCount(){

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Void> call = api.decrease_company_contact_onClick_count(cmpRegId,cndRegisterId,"Company","Candidate");
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

    private void confirmationOfOnClick(){

        AlertDialog.Builder builder = new AlertDialog.Builder(CndListDetailsActivity.this);
        builder.setTitle("Confirmation")
                .setMessage("Do you want to pay ₹50 to contact this Candidate?")
                .setPositiveButton("Pay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Checkout.preload(getApplicationContext());
                        makePayment();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Don't Want", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void makePayment(){

        amount = Math.round(Float.parseFloat("50")*100);
        double percentage = 0.18;
        double addition = amount * percentage;

        result = amount + addition;

        Checkout checkout=new Checkout();
        //checkout.setKeyID("rzp_live_jIbYN5rAx0mxj3");
        checkout.setKeyID("rzp_live_jIbYN5rAx0mxj3");
        checkout.setImage(R.drawable.splash);

        final Activity activity=this;

        try{
            JSONObject object=new JSONObject();

            object.put("name","JustJobs HR");
            object.put("Description","Contacting");
            object.put("theme.color","#0d0d0d");
            object.put("currency","INR");
            object.put("amount",result);
            //object.put("image","https://s3.amazonaws.com./rzp-mobile/images/rzp.png");
            //object.put("order_id","");
            object.put("prefill.contact",CompanyNumber);
            object.put("prefill.email",CompanyEmail);
            checkout.open(activity,object);

        }catch (Exception e) {
            //e.printStackTrace();
            //Log.e("TAG","Error in starting Razorpay Checkout",e);
        }

    }

    private void contactModeOpen(){

        insertIntoContactTable();

        cmpContactListDesign cmpContactListDesign = new cmpContactListDesign();
        Bundle bundle = new Bundle();
        bundle.putString("contact1", contactStr);
        bundle.putString("contact2", alterContactStr);
        bundle.putString("email", emailStr);
        bundle.putString("resumeUrl", resumeStr);
        cmpContactListDesign.setArguments(bundle);

        //cmpContactListDesign.setShouldDismiss(false);
        cmpContactListDesign.show(getSupportFragmentManager(), "ContactsList");

    }

    private void insertIntoContactTable(){

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        String payedDate = getDateTime();

        Call<Void> call = api.directContactedHistory(payedDate,cmpRegId,cmpRegName,cmpRegEmail,cndRegisterId,nameStr,emailStr,"Company","Candidate","Application");

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

    @Override
    public void onPaymentSuccess(String s) {
//        Toast.makeText(this, "Payment Don.", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "cmpRegId: "+cmpRegId+"\nExpireDate: "+expireDate+"\nPayed: "+amount, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Payment Successful",Toast.LENGTH_SHORT).show();
        finish();
        insertToDatabase(s,result/100);
    }

    @Override
    public void onPaymentError(int i, String s) {
        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void insertToDatabase(String TransactionId,Double amount){

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        String payedDate = getDateTime();

        Call<Void> call = api.OnclickPayments(payedDate,amount,TransactionId,cmpRegId,cmpRegName,cmpRegEmail,cndRegisterId,nameStr,emailStr,1,"Company","Candidate","Application");

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

//    private void getIsSelectedOrNotForMyCandidates() {
//
//        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
//        Call<Result> call = api.getSelectOrNotByCompany(cmpId, cndRegisterId);
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                try {
//                    if (!response.body().getError()) {
//
//                        if (response.body().getSelected() == null) {
//                            cndSelectBtn.setVisibility(View.VISIBLE);
//                            cndRejectBtn.setVisibility(View.VISIBLE);
//                        } else if (response.body().getSelected().equals("true")) {
//                            cndSelectBtn.setText("Selected");
//                            cndRejectBtn.setVisibility(View.INVISIBLE);
//                        } else if (response.body().getSelected().equals("false")) {
//                            cndRejectBtn.setText("Rejected");
//                            cndSelectBtn.setVisibility(View.INVISIBLE);
//                            cndDetailsBtnContact.setVisibility(View.GONE);
//                        }
//                    } else {
//                        Toast.makeText(CndListDetailsActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    private void getIsSelectedOrNotForHome() {
//
//        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
//        Call<Result> call = api.getSelectOrNotByCompanyForHome(cmpRegId, cndRegisterId);
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                try {
//                    if (!response.body().getError()) {
//
//                        if (response.body().getSelected() == null) {
//                            cndSelectBtn.setVisibility(View.VISIBLE);
//                            cndRejectBtn.setVisibility(View.VISIBLE);
//                        } else if (response.body().getSelected().equals("true")) {
//                            cndSelectBtn.setText("Selected");
//                            cndRejectBtn.setVisibility(View.INVISIBLE);
//                        } else if (response.body().getSelected().equals("false")) {
//                            cndRejectBtn.setText("Rejected");
//                            cndSelectBtn.setVisibility(View.INVISIBLE);
//                        }
//                    } else {
//                        Toast.makeText(CndListDetailsActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void initMethod() {

//        paymentLayout=findViewById(R.id.payment_layout);
//        paymentContainer=findViewById(R.id.fragment_payment_container);


        cndDetailsLayout=findViewById(R.id.cnd_detailes_layout);


        cndDetailsName = findViewById(R.id.cnd_list_details_4_cmp_name);
        cndDetailsLocation = findViewById(R.id.cnd_list_details_4_cmp_location);
        cndDetailsCity = findViewById(R.id.cnd_list_details_4_cmp_city);
        cndDetailsState = findViewById(R.id.cnd_list_details_4_cmp_state);
        cndDetailsGender = findViewById(R.id.cnd_list_details_4_cmp_gender);
        cndDetailsDob = findViewById(R.id.cnd_list_details_4_cmp_date_of_birth);


        cndDetailsJobType = findViewById(R.id.cnd_list_details_4_cmp_job_type);


        cndDetailsEmploymentType = findViewById(R.id.cnd_list_details_4_cmp_employment_type);


        cndDetailsLanguage = findViewById(R.id.cnd_list_details_4_cmp_languages);
        cndDetailsCommunication = findViewById(R.id.cnd_list_details_4_cmp_communication);


        cndDetailsProfile1=findViewById(R.id.cnd_list_details_4_cmp_profile_1);
        cndDetailsDesignation1=findViewById(R.id.cnd_list_details_4_cmp_designation_1);
        cndDetailsProfile2=findViewById(R.id.cnd_list_details_4_cmp_profile_2);
        cndDetailsDesignation2=findViewById(R.id.cnd_list_details_4_cmp_designation_2);


        cndDetailsQualifiStd = findViewById(R.id.cnd_list_details_4_cmp_quali_std);
        cndDetailsQualifiStream = findViewById(R.id.cnd_list_details_4_cmp_qualifi_stream);
        cndDetailsQualifiCollege = findViewById(R.id.cnd_list_details_4_cmp_qualifi_college);
        cndDetailsQualiStart = findViewById(R.id.cnd_list_details_4_cmp_quali_start_date);
        cndDetailsQualiEnd = findViewById(R.id.cnd_list_details_4_cmp_quali_end_date);


        cndDetailsExpFresherLayout = findViewById(R.id.cnd_list_details_4_cmp_fresher_layout);
        cndDetailsExpFresher = findViewById(R.id.cnd_list_details_4_cmp_fresher);

        cndDetailsExpLayout = findViewById(R.id.cnd_list_details_4_cmp_exp_layout);
        cndDetailsExpRole = findViewById(R.id.cnd_list_details_4_cmp_exp_role);
        cndDetailsExpDesignation=findViewById(R.id.cnd_list_details_4_cmp_exp_designation);
        cndDetailsExpIndustry=findViewById(R.id.cnd_list_details_4_cmp_exp_industry);
        cndDetailsExpCompany = findViewById(R.id.cnd_list_details_4_cmp_exp_company);
        cndDetailsExpCurrentSalary = findViewById(R.id.cnd_list_details_4_cmp_exp_current_salary);
        cndDetailsExpStart = findViewById(R.id.cnd_list_details_4_cmp_exp_start);
        cndDetailsExpEnd = findViewById(R.id.cnd_list_details_4_cmp_exp_end);


        cndDetailsSkill = findViewById(R.id.cnd_list_details_4_cmp_skill);


        cndDetailsVehicles = findViewById(R.id.cnd_list_details_4_cmp_has_vehicle);
        cndDetailsLicence = findViewById(R.id.cnd_list_details_4_cmp_licence);
        cndDetailsDocuments = findViewById(R.id.cnd_list_details_4_cmp_documents);

        cndDetailsReference = findViewById(R.id.cnd_list_details_4_cmp_reference);

        Contact = findViewById(R.id.company_contact_candidate_list_details_btn_contact);

    }


    private void onclickOperations() {

//        cndDetailsResumeView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (resumeStr.isEmpty() || !URLUtil.isValidUrl(resumeStr)) {
//                    Toast.makeText(CndListDetailsActivity.this, "Resume is not uploaded yet...!", Toast.LENGTH_SHORT).show();
//                }else {
//                    /*Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(resumeStr));
//                    startActivity(i);*/
//                    Bundle bundle=new Bundle();
//                    bundle.putString("resumeUrl",resumeStr);
//                    Intent intent=new Intent(CndListDetailsActivity.this, VeiwResumeActivity.class);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
//            }
//        });

//        if (isHomePage) {
//            getIsSelectedOrNotForHome();
//            cndSelectRejectLayout.setVisibility(View.GONE);
//        } else {
//            getIsSelectedOrNotForMyCandidates();
//            cndSelectRejectLayout.setVisibility(View.VISIBLE);
//        }

//        cndSelectBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean selectedBl = true;
//                selectRejectOnclickOperation(selectedBl);
//            }
//        });
//        cndRejectBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean selectedBl = false;
//                selectRejectOnclickOperation(selectedBl);
//            }
//        });
//
//        cndDetailsBtnPay4Contact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(CndListDetailsActivity.this, "Pressing", Toast.LENGTH_SHORT).show();
//                //init payment operation
////                startPayment();
//
//                paymentLayout.setVisibility(View.VISIBLE);
//                cndDetailsLayout.setVisibility(View.GONE);
//
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_payment_container,new CompanyPlanFragment())
//                        .addToBackStack(null)
//                        .commitAllowingStateLoss();
//
//
////                cmpContactListDesign cmpContactListDesign=new cmpContactListDesign();
////                Bundle bundle=new Bundle();
////                bundle.putString("contact1",contactStr);
////                bundle.putString("contact2",alterContactStr);
////                cmpContactListDesign.setArguments(bundle);
////                cmpContactListDesign.show(getSupportFragmentManager(),"ContactsList");
//            }
//        });
//
//        cndDetailsBtnContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                CompanyActivity companyActivity=new CompanyActivity();
////                companyActivity.loadFragment(new CompanyPlanFragment());
//
//                cmpContactListDesign cmpContactListDesign = new cmpContactListDesign();
//                Bundle bundle = new Bundle();
//                bundle.putString("contact1", contactStr);
//                bundle.putString("contact2", alterContactStr);
//                bundle.putString("email", emailStr);
//                bundle.putString("resumeUrl", resumeStr);
//                cmpContactListDesign.setArguments(bundle);
//                cmpContactListDesign.show(getSupportFragmentManager(), "ContactsList");
//
//                if (tenCrosed) {
//                    updateDatabase4DataAccess();
//                }
//                updateDatabase4UpdateCndView();
//
//            }
//        });
//
////        if (contactBln) {
////            cndDetailsBtnPay4Contact.setVisibility(View.GONE);
////            cndDetailsBtnContact.setVisibility(View.VISIBLE);
////        }else{
////            cndDetailsBtnPay4Contact.setVisibility(View.VISIBLE);
////            cndDetailsBtnContact.setVisibility(View.GONE);
////        }
//
//        cndDetailsBtnHireCandidate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    private void updateDatabase4UpdateCndView() {
        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.updateCndViewDataAccess(cmpRegId,cndRegisterId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (!response.body().getError()) {
                        Toast.makeText(CndListDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CndListDetailsActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

    private void updateDatabase4DataAccess() {
        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.updateDataAccess(cmpRegId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (!response.body().getError()) {
                        Toast.makeText(CndListDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CndListDetailsActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

    private void selectRejectOnclickOperation(boolean selectedBl) {

//        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
//        Call<Result> call = api.selectRejectByCompany(cmpRegId, cmpId, cndRegisterId, selectedBl);
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                try {
//                    if (!response.body().getError()) {
//                        if (response.body().getSelected().equals("true")) {
//                            cndSelectBtn.setVisibility(View.VISIBLE);
//                            cndSelectBtn.setText("Selected");
//                            cndRejectBtn.setVisibility(View.INVISIBLE);
//                        } else if (response.body().getSelected().equals("false")) {
//                            cndRejectBtn.setVisibility(View.VISIBLE);
//                            cndRejectBtn.setText("Rejected");
//                            cndSelectBtn.setVisibility(View.INVISIBLE);
//                            cndDetailsBtnContact.setVisibility(View.GONE);
//                        }
//                    } else {
//                        Toast.makeText(CndListDetailsActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void setDataToTextView() {

        Bundle bundle = getIntent().getExtras();
        cmpId = bundle.getString("cd_id");
        //isHomePage = bundle.getBoolean("isHome");
        cndRegisterId = bundle.getString("register_login_id");
        genderStr = bundle.getString("cd_gender");
        nameStr = bundle.getString("cd_full_name");
        emailStr = bundle.getString("cd_email");
        contactStr = bundle.getString("cd_contact_no");
        alterContactStr = bundle.getString("cd_alter_contact");
        stateStr = bundle.getString("cd_state");
        cityStr = bundle.getString("cd_city");
        locationStr = bundle.getString("cd_current_location");
        dobStr = bundle.getString("cd_date_of_birth");
        communicationStr = bundle.getString("cd_communication");
        profile1Str = bundle.getString("cd_job_profile_one");
        designationOneStr = bundle.getString("cd_job_designation_one");
        profile2Str = bundle.getString("cd_job_profile_two");
        designationTwoStr = bundle.getString("cd_job_designation_two");
        qualiStdStr = bundle.getString("cd_qualification_std");
        qualiStreamStr = bundle.getString("cd_qualification_stream");
        qualiCollegeNameStr = bundle.getString("cd_college_name");
        qualiStartStr = bundle.getString("cd_qualification_start_date");
        qualiEndStr = bundle.getString("cd_qualification_end_date");
        fresherInternStr = bundle.getString("cd_fresher_intern_exp");
        expIndustry = bundle.getString("cd_exp_job_industry");
        expCompanyStr = bundle.getString("cd_exp_company_name");
        expCurrentSalaryStr = bundle.getString("cd_exp_current_salary");
        expStartStr = bundle.getString("cd_exp_start_date");
        expEndStr = bundle.getString("cd_exp_end_date");
        employeeTypeStr = bundle.getString("cd_employment_type");
        locationPreferStr = bundle.getString("cd_location_prefer");
        languageStr = bundle.getString("cd_languages");
        vehicleStr = bundle.getString("cd_vehicle");
        licenceStr = bundle.getString("cd_licence");
        documentsStr = bundle.getString("cd_documents");
        skillStr = bundle.getString("cd_skill");
        referenceStr = bundle.getString("cd_reference");
        resumeStr = bundle.getString("cd_resume_cv_url");

        if (nameStr.isEmpty() || nameStr.equals("")) {
            cndDetailsName.setText("---");
        } else {
            cndDetailsName.setText(nameStr);
        }

        if (genderStr.isEmpty() || genderStr.equals("")) {
            cndDetailsGender.setText("---");
        } else {
            cndDetailsGender.setText(genderStr);
        }

        if (dobStr.isEmpty() || dobStr.equals("")) {
            cndDetailsDob.setText("---");
        } else {
            cndDetailsDob.setText(dobStr);
        }

        if (locationStr.isEmpty() || cityStr.isEmpty() || stateStr.isEmpty()) {
            cndDetailsLocation.setText("---");
        } else {
            cndDetailsLocation.setText(locationStr+" | "+cityStr+" | "+stateStr);
        }

        if (languageStr.isEmpty() || languageStr.equals("")) {
            cndDetailsLanguage.setText("---");
        } else {
//            String lngStr = languageStr.replaceAll(",$", "");
            cndDetailsLanguage.setText(languageStr);
        }

        if (communicationStr.isEmpty() || communicationStr.equals("")) {
            cndDetailsCommunication.setText("---");
        } else {
//            String commStr = communicationStr.replaceAll(",$", "");
            cndDetailsCommunication.setText(communicationStr);
        }

        if (profile1Str.isEmpty()) {
            cndDetailsProfile1.setText("--");
        }else{
            cndDetailsProfile1.setText(profile1Str);
        }

        if (designationOneStr.isEmpty()) {
            cndDetailsDesignation1.setText("--");
        }else{
            cndDetailsDesignation1.setText(designationOneStr);
        }
        if (profile2Str.isEmpty()) {
            cndDetailsProfile2.setText("--");
        }else{
            cndDetailsProfile2.setText(profile2Str);
        }

        if (designationTwoStr.isEmpty()) {
            cndDetailsDesignation2.setText("--");
        }else{
            cndDetailsDesignation2.setText(designationTwoStr);
        }

        if (qualiStdStr.isEmpty()) {
            cndDetailsQualifiStd.setText("---");
        } else {
            cndDetailsQualifiStd.setText(qualiStdStr);
        }
        if (qualiStreamStr.isEmpty()) {
            cndDetailsQualifiStream.setText("---");
        }else{
            cndDetailsQualifiStream.setText(qualiStreamStr);
        }
        if (qualiCollegeNameStr.isEmpty()) {
            cndDetailsQualifiCollege.setText("---");
        }else{
            cndDetailsQualifiCollege.setText(qualiCollegeNameStr);
        }
        if (qualiStartStr.isEmpty()) {
            cndDetailsQualiStart.setText("---");
        }else{
            cndDetailsQualiStart.setText(qualiStartStr);
        }
        if (qualiEndStr.isEmpty()) {
            cndDetailsQualiEnd.setText("---");
        }else{
            cndDetailsQualiEnd.setText(qualiEndStr);
        }

        if (fresherInternStr.equals("Fresher")) {
            cndDetailsExpLayout.setVisibility(View.GONE);
            cndDetailsExpFresher.setText(fresherInternStr);
        }else{
            cndDetailsExpLayout.setVisibility(View.VISIBLE);
            cndDetailsExpFresher.setText(fresherInternStr);
        }

        if (expIndustry.isEmpty()) {
            cndDetailsExpIndustry.setText("---");
        }else{
            cndDetailsExpIndustry.setText(expIndustry);
        }
        if (profile1Str.isEmpty()) {
            cndDetailsExpRole.setText("---");
        }else{
            cndDetailsExpRole.setText(profile1Str);
        }
        if (expCompanyStr.isEmpty()) {
            cndDetailsExpCompany.setText("---");
        }else{
            cndDetailsExpCompany.setText(expCompanyStr);
        }
        if (expCurrentSalaryStr.isEmpty()) {
            cndDetailsExpCurrentSalary.setText("---");
        }else{
            cndDetailsExpCurrentSalary.setText(expCurrentSalaryStr);
        }
        if (designationOneStr.isEmpty()) {
            cndDetailsExpDesignation.setText("--");
        }else{
            cndDetailsExpDesignation.setText(designationOneStr);
        }
        if (expStartStr.isEmpty()) {
            cndDetailsExpStart.setText("---");
        }else{
            cndDetailsExpStart.setText(expStartStr);
        }
        if (expEndStr.isEmpty()) {
            cndDetailsExpEnd.setText("---");
        }else{
            cndDetailsExpEnd.setText(expEndStr);
        }

        if (vehicleStr.isEmpty()) {
            cndDetailsVehicles.setText("---");
        } else {
//            String vehStr = vehicleStr.replaceAll(",$", "");
            cndDetailsVehicles.setText(vehicleStr);
        }
        if (licenceStr.isEmpty()) {
            cndDetailsLicence.setText("---");
        } else {
//            String licStr = licenceStr.replaceAll(",$", "");
            cndDetailsLicence.setText(licenceStr);
        }

        if (documentsStr.isEmpty()) {
            cndDetailsDocuments.setText("--");
        }else{
            cndDetailsDocuments.setText(documentsStr);
        }

        if (skillStr.isEmpty()) {
            cndDetailsSkill.setText("---");
        } else {
            String skiStr = skillStr.replaceAll(",$", "");
            cndDetailsSkill.setText(skiStr);
        }
        if (referenceStr.isEmpty()) {
            cndDetailsReference.setText("---");
        } else {
            String refStr = referenceStr.replaceAll(",$", "");
            cndDetailsReference.setText(refStr);
        }

//        if (resumeStr.isEmpty()) {
//            cndDetailsResume.setText("---");
//        } else {
//            cndDetailsResume.setText(resumeStr.substring(resumeStr.lastIndexOf('/') + 1));
//        }
    }

    private String getCurrentDate() {

//        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

//    private void getPaymetnButtonOperation() {
//
//        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
//        Call<Result> call = api.cndCountPaymentInfo(cmpRegId,getCurrentDate());
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                try {
//                    if (!response.body().getError()) {
//                        if (!response.body().getCountTenBol()) {
//                            cndDetailsBtnPay4Contact.setVisibility(View.GONE);
//                            cndDetailsBtnContact.setVisibility(View.VISIBLE);
//                            tenCrosed=false;
//                        } else {
//                            tenCrosed=true;
//                            if (response.body().getPayedRowsCount() == 0 || response.body().isPlaneExpire()) {
//                                cndDetailsBtnPay4Contact.setVisibility(View.VISIBLE);
//                                cndDetailsBtnContact.setVisibility(View.GONE);
//                            } else {
//                                cndDetailsBtnPay4Contact.setVisibility(View.GONE);
//                                cndDetailsBtnContact.setVisibility(View.VISIBLE);
//                            }
//                        }
//                        Toast.makeText(CndListDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(CndListDetailsActivity.this, "Error: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void onBottonClicked(String text) {

        if (isValidMobile(text)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", text, null));
            startActivity(intent);
            //tenCallsBtnInsert();

        } else if (text.equals("resume_url")){
            if (resumeStr.isEmpty() || !URLUtil.isValidUrl(resumeStr)) {
                Toast.makeText(CndListDetailsActivity.this, "Resume is not uploaded yet...!", Toast.LENGTH_SHORT).show();
            }else {
                    /*Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(resumeStr));
                    startActivity(i);*/
                Bundle bundle=new Bundle();
                bundle.putString("resumeUrl",resumeStr);
                Intent intent=new Intent(CndListDetailsActivity.this, VeiwResumeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"+text));
            startActivity(Intent.createChooser(emailIntent, "Send Mail"));
            //tenCallsBtnInsert();
        }

    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private void tenCallsBtnInsert() {
        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.updateCmpCallCount(getDateTime(), cmpRegId, cndRegisterId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
//                    if (!response.body().getError()) {
//                        Toast.makeText(CndListDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(CndListDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                    Log.d("Calling Message",response.body().getMessage());
                } catch (Exception e) {

                    Log.d("Calling Exception: ",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startPayment() {

        String sAmount="100";

        int amount = Math.round(Float.parseFloat(sAmount) * 100);

        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_live_jIbYN5rAx0mxj3");
        checkout.setImage(R.drawable.ic_razorpay_logo);
        JSONObject object=new JSONObject();

        try {
            object.put("name" , "JustJobs HR");
            object.put("description","Payment");
            object.put("theme.color","#0093DD");
            object.put("currency","INR");
            object.put("amount",amount);
            object.put("prefill.contact","7715092805");
            object.put("profill.email","ansarisarfaraz4519@gmail.com");

            checkout.open(CndListDetailsActivity.this,object);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public void onPaymentSuccess(String s) {
//        try {
////            cndDetailsBtnPay4Contact.setVisibility(View.GONE);
////            cndDetailsBtnContact.setVisibility(View.VISIBLE);
////            afterTenCallsBtnInsert(s);
//            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void onPaymentError(int i, String s) {
//        try {
//            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//            contactBln = false;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void afterTenCallsBtnInsert(String payKey) {
        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.updateCmpPayCallCount(getDateTime(), cmpRegId, cndRegisterId, payKey);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (!response.body().getError()) {
                        Toast.makeText(CndListDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CndListDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

    //this is for insert time in database operation
//    private String getDateTime() {
//        Calendar c = Calendar.getInstance();
//        @SuppressLint("SimpleDateFormat")
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
//        return df.format(c.getTime());
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        paymentLayout.setVisibility(View.GONE);
//        cndDetailsLayout.setVisibility(View.VISIBLE);
    }
}