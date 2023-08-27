package com.abc.justjob.Company.CompanyApplicationFrg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.CompanyFetch.CmpHomeFrgValues;
import com.abc.justjob.Company.CompanyActivitys.CmpPostedJobDetailsActivity;
import com.abc.justjob.R;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cmpAplcCndAppliedActivity extends AppCompatActivity {

    private TextView cmpJobRole, cmpDetails, cmpLocation, cmpMinSalary, cmpMaxSalary, cmpMinExp, cmpMaxExp,
            cmpEmpTime, cmpTypeOfJob, cmpEmail;

    private String cmpPostJobIdStr, cmpRegisterIdStr, jobTitleStr, jobIndustryStr, jobTypeStr,
            jobRoleStr, jobStateStr,jobCityStr, jobvacantLocationStr, jobvacantNumStr, jobDescriptionStr,
            educationStr,salaryTimeStr, minSalaryStr, maxSalaryStr, fresherCanStr, experienceCanStr,
            expMinStr, expMaxStr, minAgeStr, maxAgeStr, genderReqStr, communicationStr,
            languageReqStr, vehicleReqStr, processorReqStr, phoneReqSre, documentReqStr, workingDayStr,
            shiftDayFromStr, shiftDayToStr, shiftNightFromStr, shiftNightToStr, shiftRotationFromStr,
            shiftRotationToStr, reimbursementStr, incentiveStr, companyNameStr,
            companyEmailStr, companyPocNameStr, companyPocContactStr, companyPocDesignationStr,
            companyHeadOfficeLocationStr,companyTypeStr,companyAboutStr, companyPostedByStr;

    private CardView posjobCard;
    private LinearLayout shimmerLayout, emptyLayout;
    private ShimmerFrameLayout shimmerFrameLayout;
    private RecyclerView cndAppliedRecycler;
    private cmpAplcCndAppliedAdapter adapter;
    private List<CmpHomeFrgValues> list;


    private static final String url_product = "https://justjobshr.com//JustJobApi/JustJob/Company/cmpApplicationFrg.php?apicall=fetchCndAppliedJobs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmp_aplc_cnd_applied);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        init();

        operations();

    }

    private void operations() {

        ///pending for checking.......
        Intent intentStr = getIntent();
        cmpPostJobIdStr = intentStr.getStringExtra("cmp_post_job_id");
        cmpRegisterIdStr = intentStr.getStringExtra("cmp_register_id");
        jobTitleStr = intentStr.getStringExtra("cmp_pojo_title");
        jobIndustryStr = intentStr.getStringExtra("cmp_pojo_industry");
        jobRoleStr = intentStr.getStringExtra("cmp_pojo_role");
        jobTypeStr = intentStr.getStringExtra("cmp_pojo_job_type");
        jobStateStr = intentStr.getStringExtra("cmp_pojo_state");
        jobCityStr = intentStr.getStringExtra("cmp_pojo_city");
        jobvacantLocationStr = intentStr.getStringExtra("cmp_pojo_locality");
        jobvacantNumStr = intentStr.getStringExtra("cmp_pojo_opening");
        jobDescriptionStr = intentStr.getStringExtra("cmp_pojo_description");
        salaryTimeStr=intentStr.getStringExtra("cmp_pojo_salary_time");
        minSalaryStr = intentStr.getStringExtra("cmp_pojo_offering_min_salary");
        maxSalaryStr = intentStr.getStringExtra("cmp_pojo_offering_max_salary");
        minAgeStr = intentStr.getStringExtra("cmp_pojo_min_age");
        maxAgeStr = intentStr.getStringExtra("cmp_pojo_max_age");
        fresherCanStr = intentStr.getStringExtra("cmp_pojo_fresher_can");
        experienceCanStr = intentStr.getStringExtra("cmp_pojo_experience_can");
        expMinStr=intentStr.getStringExtra("cmp_pojo_min_exp");
        expMaxStr = intentStr.getStringExtra("cmp_pojo_max_exp");
        educationStr = intentStr.getStringExtra("cmp_pojo_education");
        genderReqStr = intentStr.getStringExtra("cmp_pojo_male_or_female");
        communicationStr = intentStr.getStringExtra("cmp_pojo_english_know");
        languageReqStr = intentStr.getStringExtra("cmp_pojo_language_know");
        vehicleReqStr = intentStr.getStringExtra("cmp_pojo_vehicle");
        processorReqStr = intentStr.getStringExtra("cmp_pojo_processor");
        phoneReqSre = intentStr.getStringExtra("cmp_pojo_phone");
        documentReqStr = intentStr.getStringExtra("cmp_pojo_documents");
        workingDayStr = intentStr.getStringExtra("cmp_pojo_working_day");
        shiftDayFromStr = intentStr.getStringExtra("cmp_pojo_day_shift_from");
        shiftDayToStr = intentStr.getStringExtra("cmp_pojo_day_shift_to");
        shiftNightFromStr = intentStr.getStringExtra("cmp_pojo_night_shift_from");
        shiftNightToStr = intentStr.getStringExtra("cmp_pojo_night_shift_to");
        shiftRotationFromStr = intentStr.getStringExtra("cmp_pojo_rotate_shift_from");
        shiftRotationToStr = intentStr.getStringExtra("cmp_pojo_rotate_shift_to");
        reimbursementStr = intentStr.getStringExtra("cmp_pojo_reimbursement");
        incentiveStr = intentStr.getStringExtra("cmp_pojo_incentive");
        companyNameStr = intentStr.getStringExtra("cmp_pojo_company_name");
        companyEmailStr = intentStr.getStringExtra("cmp_pojo_company_email");
        companyPocNameStr = intentStr.getStringExtra("cmp_pojo_company_poc_name");
        companyPocContactStr = intentStr.getStringExtra("cmp_pojo_company_poc_contact");
        companyPocDesignationStr = intentStr.getStringExtra("cmp_pojo_company_poc_designation");
        companyHeadOfficeLocationStr=intentStr.getStringExtra("cmp_pojo_company_poc_head_office_location");
        companyTypeStr=intentStr.getStringExtra("cmp_pojo_company_type");
        companyAboutStr = intentStr.getStringExtra("cmp_pojo_company_about");
        companyPostedByStr = intentStr.getStringExtra("cmp_pojo_company_posted_by");

        cmpJobRole.setText(jobRoleStr);
        cmpDetails.setText(companyPocDesignationStr);
        cmpLocation.setText(jobvacantLocationStr);
        cmpMinSalary.setText(minSalaryStr);
        cmpMaxSalary.setText(maxSalaryStr);
        cmpMinExp.setText(expMinStr);
        cmpMaxExp.setText(expMaxStr);
        cmpEmpTime.setText(jobTypeStr);
        cmpTypeOfJob.setText(jobIndustryStr);
        cmpEmail.setText(companyEmailStr);

        posjobCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentDetails();
            }
        });
        getDataList();
    }

    private void intentDetails() {
        Intent intent=new Intent(getApplicationContext(), CmpPostedJobDetailsActivity.class);
        intent.putExtra("cmp_post_job_id",cmpPostJobIdStr);
        intent.putExtra("cmp_register_id",cmpRegisterIdStr);
        intent.putExtra("cmp_pojo_title",jobTitleStr);
        intent.putExtra("cmp_pojo_industry",jobIndustryStr);
        intent.putExtra("cmp_pojo_role",jobRoleStr);
        intent.putExtra("cmp_pojo_job_type",jobTypeStr);
        intent.putExtra("cmp_pojo_state",jobStateStr);
        intent.putExtra("cmp_pojo_city",jobCityStr);
        intent.putExtra("cmp_pojo_locality",jobvacantLocationStr);
        intent.putExtra("cmp_pojo_opening",jobvacantNumStr);
        intent.putExtra("cmp_pojo_description",jobDescriptionStr);
        intent.putExtra("cmp_pojo_salary_time",salaryTimeStr);
        intent.putExtra("cmp_pojo_offering_min_salary",minSalaryStr);
        intent.putExtra("cmp_pojo_offering_max_salary",maxSalaryStr);
        intent.putExtra("cmp_pojo_min_age",minAgeStr);
        intent.putExtra("cmp_pojo_max_age",maxAgeStr);
        intent.putExtra("cmp_pojo_fresher_can",fresherCanStr);
        intent.putExtra("cmp_pojo_experience_can",experienceCanStr);
        intent.putExtra("cmp_pojo_min_exp",expMinStr);
        intent.putExtra("cmp_pojo_max_exp",expMaxStr);
        intent.putExtra("cmp_pojo_education",educationStr);
        intent.putExtra("cmp_pojo_male_or_female",genderReqStr);
        intent.putExtra("cmp_pojo_english_know",communicationStr);
        intent.putExtra("cmp_pojo_language_know",languageReqStr);
        intent.putExtra("cmp_pojo_vehicle",vehicleReqStr);
        intent.putExtra("cmp_pojo_processor",processorReqStr);
        intent.putExtra("cmp_pojo_phone",phoneReqSre);
        intent.putExtra("cmp_pojo_documents",documentReqStr);
        intent.putExtra("cmp_pojo_working_day",workingDayStr);
        intent.putExtra("cmp_pojo_day_shift_from",shiftDayFromStr);
        intent.putExtra("cmp_pojo_day_shift_to",shiftDayToStr);
        intent.putExtra("cmp_pojo_night_shift_from",shiftNightFromStr);
        intent.putExtra("cmp_pojo_night_shift_to",shiftNightToStr);
        intent.putExtra("cmp_pojo_rotate_shift_from",shiftRotationFromStr);
        intent.putExtra("cmp_pojo_rotate_shift_to",shiftRotationToStr);
        intent.putExtra("cmp_pojo_reimbursement",reimbursementStr);
        intent.putExtra("cmp_pojo_incentive",incentiveStr);
        intent.putExtra("cmp_pojo_company_name",companyNameStr);
        intent.putExtra("cmp_pojo_company_email",companyEmailStr);
        intent.putExtra("cmp_pojo_company_poc_name",companyPocNameStr);
        intent.putExtra("cmp_pojo_company_poc_contact",companyPocContactStr);
        intent.putExtra("cmp_pojo_company_poc_designation",companyPocDesignationStr);
        intent.putExtra("cmp_pojo_company_poc_head_office_location",companyHeadOfficeLocationStr);
        intent.putExtra("cmp_pojo_company_type",companyTypeStr);
        intent.putExtra("cmp_pojo_company_about",companyPocDesignationStr);
        intent.putExtra("cmp_pojo_company_posted_by",companyPocDesignationStr);
        startActivity(intent);
    }

    private void init() {
//        posjobCard=findViewById(R.id.cmp_aplc_pojo_details_card);
        posjobCard=findViewById(R.id.cmp_aplc_pojo_details_card_pojo_details);
//        cmpJobTitle = findViewById(R.id.cmp_apcl_cnd_aplly_job_title_aplc);
        cmpJobRole=findViewById(R.id.cmp_apcl_cnd_aplly_job_role_aplc_short);
        cmpDetails = findViewById(R.id.cmp_apcl_cnd_aplly_details_aplc);
        cmpLocation = findViewById(R.id.cmp_apcl_cnd_aplly_location_aplc);
        cmpMinSalary = findViewById(R.id.cmp_apcl_cnd_aplly_min_salary_aplc);
        cmpMaxSalary = findViewById(R.id.cmp_apcl_cnd_aplly_max_salary_aplc);
        cmpMinExp = findViewById(R.id.cmp_apcl_cnd_aplly_min_exp_aplc);
        cmpMaxExp = findViewById(R.id.cmp_apcl_cnd_aplly_max_exp_aplc);
        cmpEmpTime = findViewById(R.id.cmp_apcl_cnd_aplly_time_aplc);
        cmpTypeOfJob = findViewById(R.id.cmp_apcl_cnd_aplly_job_industy_aplc);
        cmpEmail = findViewById(R.id.cmp_apcl_cnd_aplly_email_aplc);

        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout_cmp_aplc_cnd_applied);
        cndAppliedRecycler = findViewById(R.id.cmpAplcCndAppliedRecycler);
        shimmerLayout = findViewById(R.id.cmp_aplc_cnd_applied_shimmer_layout);
        emptyLayout = findViewById(R.id.cmp_aplc_cnd_applied_empty_layout);

    }

    private void getDataList() {

        cndAppliedRecycler.setHasFixedSize(true);
        cndAppliedRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cndAppliedRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_product,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject userObject = array.getJSONObject(i);
                                list.add(new CmpHomeFrgValues(
                                        userObject.getString("cd_id"),
                                        userObject.getString("register_login_id"),
                                        userObject.getString("cd_gender"),
                                        userObject.getString("cd_full_name"),
                                        userObject.getString("cd_email"),
                                        userObject.getString("cd_contact_no"),
                                        userObject.getString("cd_alter_contact"),
                                        userObject.getString("cd_state"),
                                        userObject.getString("cd_city"),
                                        userObject.getString("cd_current_location"),
                                        userObject.getString("cd_date_of_birth"),
                                        userObject.getString("cd_communication"),
                                        userObject.getString("cd_job_profile_one"),
                                        userObject.getString("cd_job_designation_one"),
                                        userObject.getString("cd_job_profile_two"),
                                        userObject.getString("cd_job_designation_two"),
                                        userObject.getString("cd_qualification_std"),
                                        userObject.getString("cd_qualification_stream"),
                                        userObject.getString("cd_college_name"),
                                        userObject.getString("cd_qualification_start_date"),
                                        userObject.getString("cd_qualification_end_date"),
                                        userObject.getString("cd_fresher_intern_exp"),
                                        userObject.getString("cd_exp_job_industry"),
                                        userObject.getString("cd_exp_job_role"),
                                        userObject.getString("cd_exp_company_name"),
                                        userObject.getString("cd_exp_current_salary"),
                                        userObject.getString("cd_exp_designation"),
                                        userObject.getString("cd_exp_start_date"),
                                        userObject.getString("cd_exp_end_date"),
                                        userObject.getString("cd_languages"),
                                        userObject.getString("cd_vehicle"),
                                        userObject.getString("cd_licence"),
                                        userObject.getString("cd_documents"),
                                        userObject.getString("cd_skill"),
                                        userObject.getString("cd_reference"),
                                        userObject.getString("cd_resume")
                                ));
//                                shimmerFrameLayout.setVisibility(View.GONE);
//                                shimmerFrameLayout.stopShimmer();
//                                cndAppliedRecycler.setVisibility(View.VISIBLE);
//                                adapter=new cmpAplcCndAppliedAdapter(list,getApplicationContext());
//                                cndAppliedRecycler.setAdapter(adapter);
                            }
                            if (list.isEmpty()) {
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerLayout.setVisibility(View.GONE);
                                emptyLayout.setVisibility(View.VISIBLE);
                                cndAppliedRecycler.setVisibility(View.GONE);
                            } else {
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);
                                cndAppliedRecycler.setVisibility(View.VISIBLE);
                                adapter = new cmpAplcCndAppliedAdapter(list, getApplicationContext(),false,cmpRegisterIdStr,cmpPostJobIdStr);
                                cndAppliedRecycler.setAdapter(adapter);
                            }
                            //progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Successfully fetched...", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            //progressDialog.dismiss();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();

                        try {
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cmpPostJobsId", cmpPostJobIdStr);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}