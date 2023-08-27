package com.abc.justjob.Candidate.CndHomeDetailFrgActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.Company.CompanyApplicationFrg.cmpAplcResponse;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cndHomeCompanyDetailFragment extends Fragment {

    private LinearLayout cndHomeDetailsCmpLayout;
    private TextView tvCmpName,tvCmpEmail,tvCmpPocName,tvCmpPocContact,tvCmpPocDesignation,tvCmpPocHLocation,tvCmpType,tvCndDetailJobIndustry,
            tvCmpAbout,tvCmpPostedBy,tvCmpHeadOffice;
    private String cmpPostJobIdStr,companyNameStr,companyname,companyEmailStr, companyPocNameStr, companyPocContactStr,
            companyPocDesignationStr,companyPocHeadOfficeLocation,companyPocCmpTypeStr,jobIndustryStr,
            companyAboutStr, companyPostedByStr,headoffice;
    private String cndRegId,isSelected;
    private boolean isHome;

    private static final String url_product="https://justjobshr.com//JustJobApi/JustJob/cndApplyForJobs.php?apicall=fetch_posted_job_cnd_detail";
    private static final String url_company="https://justjobshr.com//JustJobApi/Justjob/Company/cmp_fetch.php?apicall=cmp_fetch_company_profileo&company=";

    public cndHomeCompanyDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cnd_home_company_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        cndRegId= SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        Bundle bundle=this.getArguments();
        cmpPostJobIdStr = Objects.requireNonNull(bundle).getString("PostJobId");
        isHome=bundle.getBoolean("home_bool");

//        getDataList(cmpPostJobIdStr);

        cndHomeDetailsCmpLayout=view.findViewById(R.id.cnd_home_details_cmp_layout);

        tvCmpName=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_name);
        // tvCmpEmail=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_email);
//         tvCmpPocName=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_poc_name);
//          tvCmpPocContact=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_HR_Cont);
//        tvCmpPocDesignation=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_poc_designation);
        // tvCmpPocHLocation=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_h_location);
        tvCmpType=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_type);
        tvCmpHeadOffice=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_Head_location);
        tvCndDetailJobIndustry=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_industry);
        tvCmpAbout=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_about);
        tvCmpPostedBy=view.findViewById(R.id.cnd_home_detail_tab_cmp_pojo_by);
       // tvCmpPostedBy=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_posted_by);

//        tvCmpPocContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent HrNum = new Intent(Intent.ACTION_DIAL);
//                HrNum.setData(Uri.parse("tel:"+companyPocContactStr));
//                startActivity(HrNum);
//
//            }
//
//        });

        getDataByRetrofit(cmpPostJobIdStr);


    }

    private void getDataByRetrofit(String cmpPostJobIdStr) {

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<cmpAplcResponse> call = api.getSelectedCandidates(cmpPostJobIdStr,cndRegId);
        call.enqueue(new Callback<cmpAplcResponse>() {
            @Override
            public void onResponse(Call<cmpAplcResponse> call, Response<cmpAplcResponse> response) {

                if (!Objects.requireNonNull(response.body()).isError()) {

                    isSelected=response.body().getIsSelected();
                    companyNameStr=response.body().getCmp_pojo_company_name();//("cmp_pojo_company_name");
                    companyPocCmpTypeStr=response.body().getcmp_pojo_company_type();//("cmp_pojo_company_type");
                    headoffice=response.body().getCmp_pojo_company_poc_head_office_location();
                    jobIndustryStr=response.body().getCmp_pojo_industry();//("cmp_pojo_industry");
                    companyAboutStr=response.body().getCmp_pojo_company_about();//("cmp_pojo_company_about");
                    companyPostedByStr=response.body().getCmp_pojo_company_posted_by();

                    //getDataList(companyNameStr);

//                    StringRequest stringRequest=new StringRequest(Request.Method.GET, url_company+companyNameStr,
//                            new com.android.volley.Response.Listener<String>() {
//
//                                @Override
//                                public void onResponse(String response) {
//
//                                    try {
//                                        Toast.makeText(getContext(),"bye2", Toast.LENGTH_SHORT).show();
//                                        JSONArray array = new JSONArray(response);
//                                        Toast.makeText(getContext(),"bye", Toast.LENGTH_SHORT).show();
//                                        JSONObject userObject = array.getJSONObject(0);
//
//                                        //companyname=userObject.getString("cmp_company_name");
//                                        companyPocCmpTypeStr= userObject.getString("cmp_company_category");
//                                        Toast.makeText(getContext(), companyPocCmpTypeStr, Toast.LENGTH_SHORT).show();
//                                        jobIndustryStr= userObject.getString("cmp_industry");
//                                        companyAboutStr= userObject.getString("cmp_about_company");
//
//                                        //setDataToTextView();
//                                    } catch (JSONException e) {
//                                        Toast.makeText(getContext(), "Charitha", Toast.LENGTH_SHORT).show();
//                                        Log.d("Exception: ", e.getMessage());
//                                        //Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            },
//                            new com.android.volley.Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                    RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
//                    requestQueue.add(stringRequest);
                    //Volley.newRequestQueue(requireContext()).add(stringRequest);

                    setDataToTextView();

                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<cmpAplcResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataList(String company) {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url_company+company,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        JSONArray jsonArray   = null;
                        try {
                            Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject userObject = jsonArray.getJSONObject(0);

                            companyname=userObject.getString("cmp_company_name");
                            //companyEmailStr=userObject.getString("cmp_pojo_company_email");
                            //companyPocNameStr=userObject.getString("cmp_pojo_company_poc_name");
//                            companyPocContactStr=userObject.getString("cmp_pojo_company_poc_contact");
                            //companyPocDesignationStr=userObject.getString("cmp_pojo_company_poc_designation");
                            //companyPocHeadOfficeLocation=userObject.getString("cmp_pojo_company_poc_head_office_location");
                            companyPocCmpTypeStr=userObject.getString("cmp_company_category");
                            jobIndustryStr=userObject.getString("cmp_industry");
                            companyAboutStr=userObject.getString("cmp_about_company");
                            //companyPostedByStr=userObject.getString("cmp_pojo_company_posted_by");

                            setDataToTextView();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(getContext()).add(stringRequest);
        //setDataToTextView();
    }

    private void setDataToTextView() {

        tvCmpName.setText(companyNameStr);
        tvCmpType.setText(companyPocCmpTypeStr);
        tvCndDetailJobIndustry.setText(jobIndustryStr);
        tvCmpAbout.setText(companyAboutStr);
        tvCmpPostedBy.setText(companyPostedByStr);
        tvCmpHeadOffice.setText(headoffice);
    }

}