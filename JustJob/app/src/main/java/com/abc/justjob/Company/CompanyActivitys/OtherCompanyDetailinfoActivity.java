package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherCompanyDetailinfoActivity extends AppCompatActivity
        implements cmpContactListDesign_company.contactListListener, PaymentResultListener {

    private CardView CompanyWebsite,CompanyAddressCard;

    private TextView HeadText,CompanyName,CompanyType,CompanyHeadOffice,CompanyCategory,
            CompanyIndustry,CompanyAbout,CompanySize,CompanyLink,CompanyAddress,CompanyState,CompanyCity;

    private Button Contact;

    private String cndId,cndRegName,cndEmail,cmp_company_name,cmp_id,cmp_job_type,cmp_head_office_location,cmp_company_category,cmp_industry,
            cmp_about_company,emp_size,cmp_link,cmp_company_address,cmp_state,cmp_contact,cmp_email;
    private String status,From,To;

    private String PremiumValidity,OnclickValidity,CompanyNumber,CompanyEmail;

    private Integer PremiumDataBalance,PremiumBalanceDays,FreeDataBalance,OnclickValidatedCount,amount;

    private Double result;

    public boolean backpressed = false;

    private static final String url1="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=checkpremium&id=";
    private static final String url2="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=free_company_contactinfo&id=";
    private static final String url3="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=onclick_validation&fromId=";
    private static final String url4="https://justjobshr.com//JustJobApi/JustJob/Company/cmp_payments.php?apicall=PaymentInfoFetch&id=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_other_company_detailinfo);

        cndId = SharedPrefManager.getInstance(OtherCompanyDetailinfoActivity.this).getValueOfUserId(OtherCompanyDetailinfoActivity.this);
        cndRegName = SharedPrefManager.getInstance(OtherCompanyDetailinfoActivity.this).getUserName(OtherCompanyDetailinfoActivity.this);
        cndEmail = SharedPrefManager.getInstance(OtherCompanyDetailinfoActivity.this).getUserEmail(OtherCompanyDetailinfoActivity.this);

        cmp_id = getIntent().getStringExtra("cmp_id");

        cmp_company_name= getIntent().getStringExtra("cmp_company_name");
        cmp_job_type= getIntent().getStringExtra("cmp_job_type");
        cmp_head_office_location = getIntent().getStringExtra("cmp_head_office_location");
        cmp_company_category = getIntent().getStringExtra("cmp_company_category");
        cmp_industry = getIntent().getStringExtra("cmp_industry");
        cmp_about_company = getIntent().getStringExtra("cmp_about_company");
        emp_size = getIntent().getStringExtra("emp_size");
        cmp_link = getIntent().getStringExtra("cmp_link");
        cmp_company_address = getIntent().getStringExtra("cmp_company_address");
        cmp_state = getIntent().getStringExtra("cmp_state");
        cmp_contact = getIntent().getStringExtra("cmp_contact");
        cmp_email = getIntent().getStringExtra("cmp_email");


        HeadText=findViewById(R.id.other_companies_detail_tab_cmp_info_company_name_headline);
        CompanyAddressCard=findViewById(R.id.OtherCompany_address_card);
        CompanyWebsite=findViewById(R.id.OtherCompany_website_card);
        CompanyName=findViewById(R.id.other_companies_detail_tab_cmp_info_company_name);
        CompanyType=findViewById(R.id.other_companies_detail_tab_cmp_info_company_type);
        CompanyHeadOffice=findViewById(R.id.other_companies_detail_tab_cmp_info_company_Head_location);
        CompanyCategory=findViewById(R.id.other_companies_detail_tab_cmp_info_company_category);
        CompanyIndustry=findViewById(R.id.other_companies_detail_tab_cmp_info_company_industry);
        CompanyAbout=findViewById(R.id.other_companies_detail_tab_cmp_info_company_about);
        CompanySize=findViewById(R.id.other_companies_detail_tab_cmp_info_company_size);
        CompanyLink=findViewById(R.id.other_companies_detail_tab_cmp_info_company_link);
        CompanyAddress=findViewById(R.id.other_companies_detail_tab_cmp_info_company_address);
        CompanyState=findViewById(R.id.other_companies_detail_tab_cmp_info_company_state);
        Contact=findViewById(R.id.other_companies_list_details_btn_contact);
        CompanyCity=findViewById(R.id.other_companies_detail_tab_cmp_info_company_city);

        HeadText.setText(cmp_company_name);
        CompanyName.setText(cmp_company_name);
        CompanyType.setText(cmp_job_type);
        CompanyHeadOffice.setText(cmp_head_office_location);
        CompanyCategory.setText(cmp_company_category);
        CompanyIndustry.setText(cmp_industry);
        CompanyAbout.setText(cmp_about_company);
        CompanySize.setText(emp_size);
        CompanyLink.setText(cmp_link);
        CompanyAddress.setText(cmp_company_address);
        CompanyState.setText(cmp_state);
        CompanyCity.setText(cmp_head_office_location);

        CompanyWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                //Uri searchUri = Uri.parse("https://www.google.com/search?q=" + CompanyLink.getText().toString());
                intent.putExtra(SearchManager.QUERY, CompanyLink.getText().toString());

                //if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                //}
            }
        });

        CompanyAddressCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(CompanyAddress.getText().toString()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                //if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
                //}
            }
        });

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidation();
            }
        });

    }

    private void CheckValidation(){

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                url1 + cndId,null,
                new com.android.volley.Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            PremiumValidity = response.getString("mistake");
                            if (Objects.equals(PremiumValidity,"Yes")){
                                Toast.makeText(OtherCompanyDetailinfoActivity.this,"Please take Premium Plan as soon as possible",Toast.LENGTH_SHORT).show();
                                balanceOfFree();
                            }else {
                                PremiumDataBalance = response.getInt("company_data_balance");
                                PremiumBalanceDays = response.getInt("tenure_days_balance");
                                if (PremiumBalanceDays==0){
                                    Toast.makeText(OtherCompanyDetailinfoActivity.this,"Your Plan is Expired",Toast.LENGTH_SHORT).show();
                                    balanceOfFree();
                                }else if (PremiumDataBalance==0){
                                    Toast.makeText(OtherCompanyDetailinfoActivity.this,"Your Candidate Contacting Data Balance is Over",Toast.LENGTH_SHORT).show();
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
        Call<Void> call = api.decrease_company_company_contact_premium_count(cndId);
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
                url2 + cndId,null,
                new com.android.volley.Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            FreeDataBalance = response.getInt("free_company_unlocks_balance");
                            if (FreeDataBalance==0){
                                Toast.makeText(OtherCompanyDetailinfoActivity.this,"Your Free Balance is also completed",Toast.LENGTH_SHORT).show();
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
        Call<Void> call = api.decrease_company_company_contact_free_count(cndId);
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
                url3 + cndId + "&toId=" + cmp_id + "&contact_from=" + "Company" + "&contact_to" + "Company",null,
                new com.android.volley.Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            OnclickValidity = response.getString("mistake");
                            if (Objects.equals(OnclickValidity,"Yes")){
                                getCompanyDetails();
                            }else {
                                OnclickValidatedCount = response.getInt("count");
                                if (OnclickValidatedCount==0){
                                    Toast.makeText(OtherCompanyDetailinfoActivity.this,"Your Onclick Balance is Expired",Toast.LENGTH_SHORT).show();
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
        Call<Void> call = api.decrease_company_contact_onClick_count(cndId,cmp_id,"Company","Company");
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

    private void getCompanyDetails(){

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                url4 + cndId ,null,
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

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void confirmationOfOnClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(OtherCompanyDetailinfoActivity.this);
        builder.setTitle("Confirmation")
                .setMessage("Do you want to pay ₹100 to contact this Company?")
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

        amount = Math.round(Float.parseFloat("100")*100);
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
            Log.e("TAG","Error in starting Razorpay Checkout",e);
        }

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

    private void contactModeOpen(){

        insertIntoContactTable();

        cmpContactListDesign_company cmpContactListDesign = new cmpContactListDesign_company();
        Bundle bundle = new Bundle();
        bundle.putString("contact1", cmp_contact);
        //bundle.putString("contact2", "");
        bundle.putString("email", cmp_email);
        //bundle.putString("resumeUrl", "");
        cmpContactListDesign.setArguments(bundle);

        //cmpContactListDesign.setShouldDismiss(false);
        cmpContactListDesign.show(getSupportFragmentManager(), "ContactsList");
        //showContactOptionsDialog();

    }

    private void insertIntoContactTable(){

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        String payedDate = getDateTime();

        Call<Void> call = api.directContactedHistory(payedDate,cndId,cndRegName,cndEmail,cmp_id,cmp_company_name,cmp_email,"Company","Company","Application");

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

    private void insertToDatabase(String TransactionId,Double amount){

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        String payedDate = getDateTime();

        Call<Void> call = api.OnclickPayments(payedDate,amount,TransactionId,cndId,cndRegName,cndEmail,cmp_id,cmp_company_name,cmp_email,1,"Company","Company","Application");

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


    private void showContactOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contact Options")
                .setItems(new CharSequence[]{"Through Contact Number", "Through Email"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                status="call";
                                From = "Company";
                                To = "Company";

                                Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
                                //Call<Void> call = api.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id, cmp_company_name, cmp_email, status , From, To);
                                Call<Void> call = api.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id,  cmp_company_name, cmp_email, status , From, To);

                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.isSuccessful()) {

                                            Contact.setText("Contacted");
                                            Contact.setTextColor(getResources().getColor(R.color.red_light));
                                            Toast.makeText(OtherCompanyDetailinfoActivity.this, "Contacting ....", Toast.LENGTH_SHORT).show();
                                            Handler handler = new Handler();
                                            Runnable runnable = () -> {
                                                Intent HrNum = new Intent(Intent.ACTION_DIAL);
                                                HrNum.setData(Uri.parse("tel:"+cmp_contact));
                                                startActivity(HrNum);
                                            };
                                            handler.postDelayed(runnable, 2000);

                                        } else {
                                            Toast.makeText(OtherCompanyDetailinfoActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(OtherCompanyDetailinfoActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                            case 1:
                                status="email";
                                From = "Company";
                                To = "Company";

                                Api_cmp_post_job api1 = ApiClient.getApiClient().create(Api_cmp_post_job.class);
                                //Call<Void> callie = api1.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id, cmp_company_name, cmp_email, status , From, To);
                                Call<Void> callie = api1.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id, cmp_company_name, cmp_email, status , From, To);

                                callie.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> callie, Response<Void> resp) {
                                        if (resp.isSuccessful()) {
                                            Contact.setText("Contacted");
                                            Contact.setTextColor(getResources().getColor(R.color.red_light));
                                            Toast.makeText(OtherCompanyDetailinfoActivity.this, "Contacting ...", Toast.LENGTH_SHORT).show();
                                            Handler handler = new Handler();
                                            Runnable runnable = () -> {
                                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                                intent.setData(Uri.parse("mailto:" + cmp_email));
                                                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"", ""}); // Add recipient email addresses here
                                                intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject here");
                                                intent.putExtra(Intent.EXTRA_TEXT, "Email body here");
                                                startActivity(intent);
                                            };
                                            handler.postDelayed(runnable, 2000);

                                        } else {
                                            Toast.makeText(OtherCompanyDetailinfoActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> callie, Throwable ty) {
                                        Toast.makeText(OtherCompanyDetailinfoActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();

    }

    @Override
    public void onBottonClicked(String text) {
        if (isValidMobile(text)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", text, null));
            startActivity(intent);
            //tenCallsBtnInsert();

        }else {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"+text));
            startActivity(Intent.createChooser(emailIntent, "Send Mail"));
            //tenCallsBtnInsert();
        }
    }
    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

}