package com.abc.justjob.Candidate.CndHomeDetailFrgActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Candidate.CndCompanies.CndCompaniesDetailInfoActivity;
import com.abc.justjob.Company.CompanyApplicationFrg.cmpAplcResponse;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cndHomeCmpJobDetailFragment extends Fragment {

    private TextView cndDetailViewNum, cndIsSelectedTv,cndDetailCandidates,cndDetailJobDescription,tvCmpPocEmail,
            cndDetailJobType, cndDetailJobRole,cndDetailJobDesignation,cndDetailJobcmpName,
            cndDetailJobState,cndDetailJobCity,cndDetailJobVacantLocation,cndDetailJobVacantNum,
            cndDetailFresherCan, cndDetailExperienceCan, cndDetailMinExp, cndDetailMaxExp,
            cndDetailMinAge, cndDetailMaxAge,cndDetailMinSalary,cndDetailMaxSalary, cndDetailGenderReq, cndDetailCommunicationReq,
            cndDetailLanguageReq, cndDetailVehicleReq, cndDetailProcessorReq,
            cndDetailPhone, cndDetailDocumentReq, cndDetailShiftFrom,cndAppliedtime,
            cndDetailShiftTo, cndDetailWorkingDay, cndDetailReimbursement,
            cndDetailIncentive,cndDepositable,cndDepositAmount,cndDepositPurpose,tvCmpPocName,tvCmpPocContact,joblocationType;

    public String datetime,cmpPostJobIdStr,cmpName, cndIsSelected,cmpRegisterIdStr, viewNumStr, candidateStr, jobTitleStr,jobRoleStr,jobDesignationStr, jobTypeStr,
            jobStateStr,jobCityStr,jobVacantLocationStr, jobVacantNumStr, jobDescriptionStr,jobSalaryTimeStr,
            educationStr, minSalaryStr, maxSalaryStr, fresherCanStr, experienceCanStr,
            expMinStr, expMaxStr, minAgeStr, maxAgeStr, genderReqStr, communicationStr,
            languageReqStr, vehicleReqStr, processorReqStr, phoneReqSre, documentReqStr, workingDayStr,
            shiftDayFromStr, shiftDayToStr, shiftNightFromStr, shiftNightToStr, shiftRotationFromStr, shiftRotationToStr,
            reimbursementStr, incentiveStr, depositableStr, depositAmountStr, depositPurposeStr,companyPocNameStr,companyPocContactStr,companyPocEmaiStr,JobLocationtypeStr;

    private String status,From,To,came;

    private boolean homeBool;
    private CardView cndDetailCardBtn, hrContactCard;
    private Button applyJobBtn;

    private String cndRegId,cndRegName,cndEmail;

    private Dialog dialog;
    private int counter;

    private static final String url_product="https://justjobshr.com//JustJobApi/JustJob/cndApplyForJobs.php?apicall=fetch_posted_job_cnd_detail";

    private static final String url_product_apply="https://justjobshr.com//JustJobApi/JustJob/cndApplyForJobs.php?apicall=cndApplyForJob";

    public cndHomeCmpJobDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cnd_home_cmp_job_detail, container, false);
    }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

//        getDataFromIntent();
//        setDataToTextView();

            initOperation(view);

            Bundle bundle=this.getArguments();
            cmpPostJobIdStr = Objects.requireNonNull(bundle).getString("PostJobId");
            homeBool=bundle.getBoolean("home_bool");

            cndRegId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());
            cndRegName = SharedPrefManager.getInstance(getContext()).getUserName(getContext());
            cndEmail = SharedPrefManager.getInstance(getContext()).getUserEmail(getContext());

//        getDataList(cmpPostJobIdStr);

//            Toast.makeText(getContext(),cmpPostJobIdStr+"\n"+cndRegId , Toast.LENGTH_SHORT).show();
            getDataByRetrofit(cmpPostJobIdStr);


//            if (homeBool) {
//                cndDetailCardBtn.setVisibility(View.VISIBLE);
//                cndIsSelectedTv.setVisibility(View.GONE);
//            }else{
//                //cndAppliedtime.setText(datetime);
//                //cndAppliedtime.setVisibility(View.VISIBLE);
//                cndIsSelectedTv.setVisibility(View.VISIBLE);
//                cndDetailCardBtn.setVisibility(View.GONE);
//            }

            applyJobBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!applyJobBtn.getText().toString().equals("Applied")){
                        String cndRegisterId = SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());
                        applyForJobAction(cmpRegisterIdStr, cmpPostJobIdStr, cndRegisterId);
                    }else {
                        Toast.makeText(getContext(),"Already Applied",Toast.LENGTH_SHORT).show();
                    }



//                    Toast.makeText(getContext(), cmpRegisterIdStr+"\n"+cmpPostJobIdStr+"\n"+cndRegisterId, Toast.LENGTH_SHORT).show();


                    //hrContactCard.setVisibility((hrContactCard.getVisibility() == View.VISIBLE) ? View.INVISIBLE : View.VISIBLE);
                    //Toast.makeText(getContext(),"Currently we are working on this",Toast.LENGTH_SHORT).show();
//                applyWithVolley(cmpRegisterIdStr, cmpPostJobIdStr, cndRegisterId);
                }
            });
        }

    private void getCndAppliedJobs(String cmpRegisterIdStr, String cmpPostJobIdStr, String cndRegId) {

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<cmpAplcResponse> call = api.getCndApplied4Job(cmpRegisterIdStr,cmpPostJobIdStr,cndRegId);
        call.enqueue(new Callback<cmpAplcResponse>() {
            @Override
            public void onResponse(Call<cmpAplcResponse> call, Response<cmpAplcResponse> response) {
                if (!response.body().isError()) {
                    if (response.body().isCndApplied()) {
                        //applyJobBtn.setBackgroundColor(getResources().getColor(R.color.icon_color));
                        applyJobBtn.setText("Applied");
                        applyJobBtn.setTextColor(getResources().getColor(R.color.red_light));
                        if (cndIsSelected.equals("false")){
                            hrContactCard.setVisibility(View.GONE);
                        }else {
                            hrContactCard.setVisibility(View.VISIBLE);
                        }
                        cndIsSelectedTv.setVisibility(View.VISIBLE);
                        if (cndIsSelected.equals("false")) {
                            cndIsSelectedTv.setText("Your Application is Rejected.");
                            cndIsSelectedTv.setTextColor(getResources().getColor(R.color.red_light));
                        }else if (cndIsSelected.equals("true")) {
                            cndIsSelectedTv.setText("Your Application Shortlisted.");
                            cndIsSelectedTv.setTextColor(getResources().getColor(R.color.green_light));
                        }else {
                            cndIsSelectedTv.setText("Your Application is Pending.");
                            cndIsSelectedTv.setTextColor(getResources().getColor(R.color.yellow_dark));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<cmpAplcResponse> call, Throwable t) {

            }
        });
    }

    private void applyWithVolley(String cmpRegisterIdStr, String cmpPostJobIdStr, String cndRegisterId) {

            StringRequest stringRequest=new StringRequest(Request.Method.POST, url_product_apply,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject object=new JSONObject(response);

                                Toast.makeText(getContext(), object.getString("message"), Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();


                            if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                                Toast.makeText(getContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof AuthFailureError) {
                                Toast.makeText(getContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ServerError) {
                                Toast.makeText(getContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof NetworkError) {
                                Toast.makeText(getContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ParseError) {
                                Toast.makeText(getContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("cmpRegisterId", cmpRegisterIdStr);
                    params.put("cmpPostJobId", cmpPostJobIdStr);
                    params.put("cndRegisterId", cndRegisterId);
                    return params;
                }
            };
            Volley.newRequestQueue(getContext()).add(stringRequest);
        }

        private void getDataByRetrofit(String cmpPostJobIdStr) {

            Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
            Call<cmpAplcResponse> call = api.getSelectedCandidates(cmpPostJobIdStr,cndRegId);
            call.enqueue(new Callback<cmpAplcResponse>() {
                @Override
                public void onResponse(Call<cmpAplcResponse> call, Response<cmpAplcResponse> response) {

                    if (!Objects.requireNonNull(response.body()).isError()) {
                        //datetime=response.body().getDatetime();
                        cmpName=response.body().getCmp_pojo_company_name(); //verified
                        cndIsSelected=response.body().getIsSelected();//("isSelected"); //need to check
                        cmpRegisterIdStr=response.body().getCmp_register_id();//("cmp_id"); //verified
                        jobTitleStr=response.body().getCmp_pojo_title();//("cmp_pojo_title"); //verified
                        jobRoleStr=response.body().getCmp_pojo_role();//("cmp_pojo_role"); //verified
                        jobDesignationStr=response.body().getCmp_pojo_designation();//("cmp_pojo_designation"); //verified
                        jobTypeStr=response.body().getCmp_pojo_job_type();//("cmp_pojo_job_type"); //verified
                        jobStateStr=response.body().getCmp_pojo_state();//("cmp_pojo_state"); //verified
                        jobCityStr=response.body().getCmp_pojo_cities(); //verified
                        jobDescriptionStr=response.body().getCmp_pojo_description();//("cmp_pojo_description"); //verified
                        //jobSalaryTimeStr=response.body().getCmp_pojo_salary_time();//("cmp_pojo_salary_time"); //verified
                        minSalaryStr=response.body().getCmp_pojo_offering_min_salary();//("cmp_pojo_offering_min_salary"); //verified
                        maxSalaryStr=response.body().getCmp_pojo_offering_max_salary();//("cmp_pojo_offering_max_salary"); //verified
                        minAgeStr=response.body().getCmp_pojo_min_age();//("cmp_pojo_min_age"); //verified
                        maxAgeStr=response.body().getCmp_pojo_max_age();//("cmp_pojo_max_age"); //verified
                        jobVacantLocationStr=response.body().getCmp_pojo_locality();//("cmp_pojo_locality"); //verified
                        jobVacantNumStr=response.body().getCmp_pojo_opening();//("cmp_pojo_opening"); //verified
                        fresherCanStr=response.body().getCmp_pojo_fresher_can();//("cmp_pojo_fresher_can"); //verified
                        experienceCanStr=response.body().getCmp_pojo_experience_can();//("cmp_pojo_experience_can"); //verified
                        expMinStr=response.body().getCmp_pojo_min_exp();//("cmp_pojo_min_exp"); //verified
                        expMaxStr=response.body().getCmp_pojo_max_exp();//("cmp_pojo_max_exp"); //verified
                        educationStr=response.body().getCmp_pojo_education();//("cmp_pojo_education"); //verified
                        genderReqStr=response.body().getCmp_pojo_male_or_female();//("cmp_pojo_male_or_female"); //verified
                        communicationStr=response.body().getCmp_pojo_english_know();//("cmp_pojo_english_know"); //verified
                        languageReqStr=response.body().getCmp_pojo_language_know();//("cmp_pojo_language_know"); //verified
                        vehicleReqStr=response.body().getCmp_pojo_vehicle();//("cmp_pojo_vehicle"); //verified
                        processorReqStr=response.body().getCmp_pojo_processor();//("cmp_pojo_processor"); //verified
                        phoneReqSre=response.body().getCmp_pojo_phone();//("cmp_pojo_phone"); //verified
                        documentReqStr=response.body().getCmp_pojo_documents();//("cmp_pojo_documents"); //verified
                        workingDayStr=response.body().getCmp_pojo_working_day();//("cmp_pojo_working_day"); //verified
                        shiftDayFromStr=response.body().getCmp_pojo_day_shift_from();//("cmp_pojo_day_shift_from"); //verified
                        shiftDayToStr=response.body().getCmp_pojo_day_shift_to();//("cmp_pojo_day_shift_to"); //verified
                        shiftNightFromStr=response.body().getCmp_pojo_night_shift_from();//("cmp_pojo_night_shift_from"); //verified
                        shiftNightToStr=response.body().getCmp_pojo_night_shift_to();//("cmp_pojo_night_shift_to"); //verified
                        shiftRotationFromStr=response.body().getCmp_pojo_rotate_shift_from();//("cmp_pojo_rotate_shift_from"); //verified
                        shiftRotationToStr=response.body().getCmp_pojo_rotate_shift_to();//("cmp_pojo_rotate_shift_to"); //verified
                        reimbursementStr=response.body().getCmp_pojo_reimbursement();//("cmp_pojo_reimbursement");
                        incentiveStr=response.body().getCmp_pojo_incentive();//("cmp_pojo_incentive"); //verified
                        depositableStr=response.body().getCmp_pojo_depositing(); //verified
                        depositAmountStr=response.body().getCmp_pojo_deposit_amount(); //verified
                        depositPurposeStr=response.body().getCmp_pojo_deposit_purpose(); //verified
                        companyPocNameStr=response.body().getCmp_pojo_company_poc_name(); //verified
                        companyPocContactStr=response.body().getCmp_pojo_company_poc_contact();//("Cmp_pojo_company_poc_contact"); //verified
                        companyPocEmaiStr=response.body().getCmp_pojo_company_email();
                        JobLocationtypeStr=response.body().getCmp_pojo_location();


                        getCndAppliedJobs(cmpRegisterIdStr,cmpPostJobIdStr,cndRegId);
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

        private void applyForJobAction(String cmpRegisterIdStr, String cmpPostJobIdStr, String cndRegisterId) {

            Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
            Call<Result> call = api.cndApplyForJob(cndRegisterId,cmpRegisterIdStr, cmpPostJobIdStr);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {

                    if (!Objects.requireNonNull(response.body()).getError()) {

                        dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.applied_dialogbox);
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.setCancelable(true);
                        dialog.show();
                        new CountDownTimer(2000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                // Do nothing
                            }
                            public void onFinish() {
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        }.start();
                        //Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //applyJobBtn.setBackgroundColor(getResources().getColor(R.color.icon_color));;
                        applyJobBtn.setText("Applied");
                        applyJobBtn.setTextColor(getResources().getColor(R.color.red_light));
                        hrContactCard.setVisibility(View.VISIBLE);
                        cndIsSelectedTv.setVisibility(View.VISIBLE);
                        cndIsSelectedTv.setText("Your Application is Pending.");
                        cndIsSelectedTv.setTextColor(getResources().getColor(R.color.yellow_dark));
//                        if (cndIsSelected.equals("false")) {
//                            cndIsSelectedTv.setText("Your Application is Rejected.");
//                            cndIsSelectedTv.setTextColor(getResources().getColor(R.color.red_light));
//                        }else if (cndIsSelected.equals("true")) {
//                            cndIsSelectedTv.setText("Your Application Shortlisted.");
//                            cndIsSelectedTv.setTextColor(getResources().getColor(R.color.green_light));
//                        }else {
//                            cndIsSelectedTv.setText("Your Application is Pending.");
//                            cndIsSelectedTv.setTextColor(getResources().getColor(R.color.yellow_dark));
//                        }
                        //cndAppliedtime.setText(datetime);
                        //cndAppliedtime.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void initOperation(View view) {

            cndIsSelectedTv=view.findViewById(R.id.cnd_job_details_selected_rejected_tv); //verified

            cndDetailViewNum = view.findViewById(R.id.cnd_home_list_details_view_num); //verified
            cndDetailCandidates = view.findViewById(R.id.cnd_home_list_details_candidates); //verified

            cndDetailJobRole = view.findViewById(R.id.cnd_home_list_details_job_role); //verified
            cndDetailJobDesignation=view.findViewById(R.id.cnd_home_list_details_job_designation); //verified
            cndDetailJobcmpName=view.findViewById(R.id.cnd_home_list_details_company_name); //verified
            cndDetailMinSalary = view.findViewById(R.id.cnd_home_list_details_min_salary); //verified
            cndDetailMaxSalary = view.findViewById(R.id.cnd_home_list_details_max_salary); //verified
            cndDetailMinExp = view.findViewById(R.id.cnd_home_list_details_exp_from); //verified
            cndDetailMaxExp = view.findViewById(R.id.cnd_home_list_details_exp_to); //verified
            cndDetailShiftFrom = view.findViewById(R.id.cnd_home_list_details_shift_from); //verified
            cndDetailShiftTo = view.findViewById(R.id.cnd_home_list_details_shift_to); //verified
            cndDetailJobCity = view.findViewById(R.id.cnd_home_list_details_job_city); //verified
            cndDetailJobState = view.findViewById(R.id.cnd_home_list_details_job_state); //verified
            cndDetailJobVacantLocation = view.findViewById(R.id.cnd_home_list_details_vacant_location); //verified

            cndDetailJobType = view.findViewById(R.id.cnd_home_list_details_job_type); //verified
            joblocationType = view.findViewById(R.id.cnd_home_list_details_job_location_type);

            cndDetailJobDescription = view.findViewById(R.id.cnd_home_list_details_job_description); //verified
            cndDetailDocumentReq = view.findViewById(R.id.cnd_home_list_details_documents_req); //verified
            cndDetailIncentive = view.findViewById(R.id.cnd_home_list_details_incentive_num); //verified
            cndDetailFresherCan = view.findViewById(R.id.cnd_home_list_details_fresher_can); //verified

            cndDetailGenderReq = view.findViewById(R.id.cnd_home_list_details_gender_req); //verified
            cndDetailMinAge = view.findViewById(R.id.cnd_home_list_details_min_age); //verified
            cndDetailMaxAge = view.findViewById(R.id.cnd_home_list_details_max_age); //verified
            cndDetailCommunicationReq = view.findViewById(R.id.cnd_home_list_details_communication_req); //verified
            cndDetailLanguageReq = view.findViewById(R.id.cnd_home_list_details_language_req); //verified
            cndDetailWorkingDay = view.findViewById(R.id.cnd_home_list_details_working_day_num); //verified
            cndDetailJobVacantNum = view.findViewById(R.id.cnd_home_list_details_vacant_num); //verified
            cndDetailVehicleReq = view.findViewById(R.id.cnd_home_list_details_vehicle_req); //verified
            cndDetailProcessorReq = view.findViewById(R.id.cnd_home_list_details_processor_req); //verified
            cndDetailPhone = view.findViewById(R.id.cnd_home_list_details_phone_req); //verified
            cndDetailReimbursement = view.findViewById(R.id.cnd_home_list_details_reimbursement); //verified
            cndDepositable=view.findViewById(R.id.cnd_home_list_details_depositable); //verified
            cndDepositAmount=view.findViewById(R.id.cnd_home_list_details_deposit_amount); //verified
            cndDepositPurpose=view.findViewById(R.id.cnd_home_list_details_deposit_purpose); //verified

            hrContactCard=view.findViewById(R.id.hrContactCard); //verified
            tvCmpPocName=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_company_poc_name); //verified
            tvCmpPocContact=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_HR_Cont); //verified
            tvCmpPocEmail=view.findViewById(R.id.cnd_home_detail_tab_cmp_info_HR_Email);

            cndDetailCardBtn=view.findViewById(R.id.cnd_detail_card_btn); //verified
            applyJobBtn = view.findViewById(R.id.cnd_home_list_details_btn_apply); //verified

            tvCmpPocContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    status="call";
                    From = "Candidate";
                    To = "Company";
                    came = "Application";
                    Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
                    Call<Void> call = api.setCandidate_Apply_Contact_Details( cndRegId, cndRegName, cndEmail, cmpPostJobIdStr,
                            jobRoleStr, cmpRegisterIdStr, cmpName, companyPocEmaiStr, status , From, To, came);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Contacting ....", Toast.LENGTH_SHORT).show();
                                Handler handler = new Handler();
                                Runnable runnable = () -> {
                                    Intent HrNum = new Intent(Intent.ACTION_DIAL);
                                    HrNum.setData(Uri.parse("tel:"+companyPocContactStr));
                                    startActivity(HrNum);
                                };
                                handler.postDelayed(runnable, 2000);
                            } else {
                                Toast.makeText(getContext(), "Error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), "API call failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            tvCmpPocEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    status="email";
                    From = "Candidate";
                    To = "Company";
                    came = "Application";

                    Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
                    Call<Void> call = api.setCandidate_Apply_Contact_Details( cndRegId, cndRegName, cndEmail, cmpPostJobIdStr,
                            jobRoleStr, cmpRegisterIdStr, cmpName, companyPocEmaiStr, status , From, To, came);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Contacting ....", Toast.LENGTH_SHORT).show();
                                Handler handler = new Handler();
                                Runnable runnable = () -> {
                                    if (tvCmpPocEmail.getText().toString().equals("NA")){
                                        Toast.makeText(getContext(),"Sorry There is no HR Email Address",Toast.LENGTH_SHORT).show();
                                    }else {
                                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                                        intent.setData(Uri.parse("mailto:" + tvCmpPocEmail.getText().toString()));
                                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"", ""}); // Add recipient email addresses here
                                        intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject here");
                                        intent.putExtra(Intent.EXTRA_TEXT, "Email body here");
                                        startActivity(intent);
                                    }
                                };
                                handler.postDelayed(runnable, 2000);
                            } else {
                                Toast.makeText(getContext(), "Error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), "API call failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }


    /*private void getDataList(String cmpPostJobIdStr) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_product,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray   = new JSONArray(response);
                            JSONObject userObject = jsonArray.getJSONObject(0);

                            cndIsSelected=userObject.getString("isSelected");
                            cmpRegisterIdStr=userObject.getString("cmp_id");
                            jobTitleStr=userObject.getString("cmp_pojo_title");
                            jobRoleStr=userObject.getString("cmp_pojo_role");
                            jobDesignationStr=userObject.getString("cmp_pojo_designation");
                            jobTypeStr=userObject.getString("cmp_pojo_job_type");
                            jobStateStr=userObject.getString("cmp_pojo_state");
                            jobDescriptionStr=userObject.getString("cmp_pojo_description");
                            jobSalaryTimeStr=userObject.getString("cmp_pojo_salary_time");
                            minSalaryStr=userObject.getString("cmp_pojo_offering_min_salary");
                            maxSalaryStr=userObject.getString("cmp_pojo_offering_max_salary");
                            minAgeStr=userObject.getString("cmp_pojo_min_age");
                            maxAgeStr=userObject.getString("cmp_pojo_max_age");
                            jobvacantLocationStr=userObject.getString("cmp_pojo_locality");
                            jobvacantNumStr=userObject.getString("cmp_pojo_opening");
                            fresherCanStr=userObject.getString("cmp_pojo_fresher_can");
                            experienceCanStr=userObject.getString("cmp_pojo_experience_can");
                            expMinStr=userObject.getString("cmp_pojo_min_exp");
                            expMaxStr=userObject.getString("cmp_pojo_max_exp");
                            educationStr=userObject.getString("cmp_pojo_education");
                            genderReqStr=userObject.getString("cmp_pojo_male_or_female");
                            communicationStr=userObject.getString("cmp_pojo_english_know");
                            languageReqStr=userObject.getString("cmp_pojo_language_know");
                            vehicleReqStr=userObject.getString("cmp_pojo_vehicle");
                            processorReqStr=userObject.getString("cmp_pojo_processor");
                            phoneReqSre=userObject.getString("cmp_pojo_phone");
                            documentReqStr=userObject.getString("cmp_pojo_documents");
                            workingDayStr=userObject.getString("cmp_pojo_working_day");
                            shiftDayFromStr=userObject.getString("cmp_pojo_day_shift_from");
                            shiftDayToStr=userObject.getString("cmp_pojo_day_shift_to");
                            shiftNightFromStr=userObject.getString("cmp_pojo_night_shift_from");
                            shiftNightToStr=userObject.getString("cmp_pojo_night_shift_to");
                            shiftRotationFromStr=userObject.getString("cmp_pojo_rotate_shift_from");
                            shiftRotationToStr=userObject.getString("cmp_pojo_rotate_shift_to");
                            reimbursementStr=userObject.getString("cmp_pojo_reimbursement");
                            incentiveStr=userObject.getString("cmp_pojo_incentive");

                            setDataToTextView();
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Exception: "+e.toString(), Toast.LENGTH_SHORT).show();
                            //e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cmpPojoId", cmpPostJobIdStr);
                params.put("cndRegId",cndRegId);
                return params;
            }
        };
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }*/


        private void setDataToTextView() {

//            if (homeBool) { //verified
//                if (cndIsSelected.isEmpty()){
//                    cndIsSelectedTv.setText("Your Application is Pending.");
//                    cndIsSelectedTv.setTextColor(getResources().getColor(R.color.yellow_dark));
//                } else if (cndIsSelected.equals("true")) {
//                    cndIsSelectedTv.setText("Your Application Shortlisted.");
//                    cndIsSelectedTv.setTextColor(getResources().getColor(R.color.green_light));
//                }else if (cndIsSelected.equals("false")) {
//                    cndIsSelectedTv.setText("Your Application is Rejected.");
//                    cndIsSelectedTv.setTextColor(getResources().getColor(R.color.red_light));
//                }
//            }

            if (jobRoleStr.isEmpty()) { //verified
                cndDetailJobRole.setText("--");
            } else {
                cndDetailJobRole.setText(jobRoleStr);
            }
            if (jobDesignationStr.isEmpty()) { //verified
                cndDetailJobDesignation.setText("--");
            } else {
                cndDetailJobDesignation.setText(jobDesignationStr);
            }

            if (jobTypeStr.isEmpty()) { //verified
                cndDetailJobType.setText("--");
            } else {
                cndDetailJobType.setText(jobTypeStr);
            }

//            if (jobStateStr.isEmpty()) {
//                cndDetailJobState.setText("--");
//            } else {
//                cndDetailJobState.setText(""+jobStateStr);
//            }

            if (jobCityStr.isEmpty()) { //verified
                cndDetailJobCity.setText("--");
            }else{
                cndDetailJobCity.setText(jobCityStr);
            }

            cndDetailJobState.setText(jobStateStr);
            cndDetailJobVacantLocation.setText(jobVacantLocationStr);
//            if (jobVacantLocationStr.isEmpty()) {
//                cndDetailJobVacantLocation.setText("--");
//            }else{
//                cndDetailJobVacantLocation.setText(jobVacantLocationStr);
//            }
//            if (jobVacantNumStr.isEmpty()) {
//                cndDetailJobVacantNum.setText("--");
//            }else{
//                cndDetailJobVacantNum.setText(jobVacantNumStr);
//            }

            if (JobLocationtypeStr.isEmpty()){
                joblocationType.setText("N/A");
            }else {
                joblocationType.setText(JobLocationtypeStr);
            }

            if (jobDescriptionStr.isEmpty()) { //verified
                cndDetailJobDescription.setText("--");
            } else {
                cndDetailJobDescription.setText(jobDescriptionStr);
            }

            if (vehicleReqStr.isEmpty()) {
                cndDetailVehicleReq.setVisibility(View.GONE);
            } else {
                if (vehicleReqStr.endsWith(",")){
                    cndDetailVehicleReq.setText(vehicleReqStr.substring(0,vehicleReqStr.length()-1));
                }else {
                    cndDetailVehicleReq.setText(vehicleReqStr);
                }
            }

            if (processorReqStr.isEmpty()) {
                cndDetailProcessorReq.setVisibility(View.GONE);
            } else {
                if (processorReqStr.endsWith(",")){
                    cndDetailProcessorReq.setText(processorReqStr.substring(0,processorReqStr.length()-1));
                }else {
                    cndDetailProcessorReq.setText(processorReqStr);
                }
            }

            if (phoneReqSre.isEmpty()) {
                cndDetailPhone.setVisibility(View.GONE);
            } else {
                if (phoneReqSre.endsWith(",")){
                    cndDetailPhone.setText(phoneReqSre.substring(0,phoneReqSre.length()-1));
                }else {
                    cndDetailPhone.setText(phoneReqSre);
                }
            }

            if (vehicleReqStr.isEmpty() && processorReqStr.isEmpty() && phoneReqSre.isEmpty()){
                cndDetailPhone.setText("N/A");
                cndDetailPhone.setVisibility(View.VISIBLE);
            }

/*
            *//*if (jobvacantLocationStr.isEmpty()) {
                cndDetailVacantLocation.setText("--");
            } else {
                cndDetailVacantLocation.setText(jobvacantLocationStr);
            }*/


            if (jobVacantNumStr.isEmpty()) {
                cndDetailJobVacantNum.setText("N/A");
            } else {
                cndDetailJobVacantNum.setText("No of opening: "+jobVacantNumStr);
            }

            cndDetailMinSalary.setText(minSalaryStr); //verified
            cndDetailMaxSalary.setText(maxSalaryStr); //veriied

            cndDetailMinAge.setText(minAgeStr); //verified
            cndDetailMaxAge.setText(maxAgeStr); //verified
//            if (fresherCanStr.isEmpty() || fresherCanStr.equals("True")) {
//            if (fresherCanStr.equals("True")) {
//
//                    cndDetailFresherCan.setText("Fresher Can Apply");
//            } else {
//                cndDetailFresherCan.setText("Fresher Cannot Apply");
//            }
            cndDetailFresherCan.setText(educationStr);
//            if (experienceCanStr.isEmpty() || experienceCanStr.equals("True")) {
//            if (experienceCanStr.equals("True")) {
//
//                    cndDetailExperienceCan.setText("Experience Can Apply");
//            } else {
//                cndDetailExperienceCan.setText("Experience Cannot Apply");
//            }
            /*cndSalaryTime.setText(jobSalaryTimeStr);*/
            cndDetailJobcmpName.setText(cmpName); //verified
            cndDetailMinExp.setText(expMinStr); //verified
            cndDetailMaxExp.setText(expMaxStr); //verified
            /*cndDetailEducationRequire.setText(educationStr.replaceAll(",$", ""));*/
            cndDetailGenderReq.setText(genderReqStr); //verified
            cndDetailCommunicationReq.setText(communicationStr.replaceAll(",$", "")); //verified
            cndDetailLanguageReq.setText(languageReqStr.replaceAll(",$", "")); //verified
            //cndDetailVehicleReq.setText(vehicleReqStr.replaceAll(",$", ""));
            //cndDetailProcessorReq.setText(processorReqStr.replaceAll(",$", ""));
            //cndDetailPhone.setText(phoneReqSre.replaceAll(",$", ""));
            cndDetailDocumentReq.setText(documentReqStr.replaceAll(",$", "")); //verified

            if (!shiftDayFromStr.equals("--") && !shiftDayToStr.equals("--")) { //verified
                cndDetailShiftFrom.setText(shiftDayFromStr);
                cndDetailShiftTo.setText(shiftDayToStr);
            } else if (!shiftNightFromStr.equals("--") && !shiftNightToStr.equals("--")) {
                cndDetailShiftFrom.setText(shiftNightFromStr);
                cndDetailShiftTo.setText(shiftNightToStr);
            } else if (!shiftRotationFromStr.equals("--") && !shiftRotationToStr.equals("--")) {
                cndDetailShiftFrom.setText(shiftRotationFromStr);
                cndDetailShiftTo.setText(shiftRotationToStr);
            } else {
                cndDetailShiftFrom.setText("--");
                cndDetailShiftTo.setText("--");
            }

            cndDetailWorkingDay.setText(workingDayStr); //verified
            if (reimbursementStr.isEmpty()){
                cndDetailReimbursement.setText(cndDetailReimbursement.getText().toString()+"N/A");
            }else {
                cndDetailReimbursement.setText(reimbursementStr.replaceAll(", $", ""));
            }
            //cndDetailReimbursement.setText(reimbursementStr.replaceAll(", $", ""));
            if (incentiveStr.isEmpty()){
                cndDetailIncentive.setText("N/A");
            }else {
                cndDetailIncentive.setText(incentiveStr); //verified
            }

            //cndDepositable.setText(depositableStr);
            //cndDepositAmount.setText(depositAmountStr);

            if (depositableStr.isEmpty()) {
                cndDepositable.setText("N/A");
            }else {
                cndDepositable.setText(depositableStr);
            }

            if (depositAmountStr.isEmpty()||depositAmountStr.equals("--")) {
                cndDepositAmount.setText("N/A");
            }else {
                cndDepositAmount.setText(depositAmountStr);
            }

            if (depositPurposeStr.isEmpty()||depositPurposeStr.equals("--")) {
                cndDepositPurpose.setText("N/A");
            }else {
                cndDepositPurpose.setText(depositPurposeStr);
            }
            tvCmpPocName.setText(companyPocNameStr); //verified
            tvCmpPocContact.setText(companyPocContactStr); //verified
            if (companyPocEmaiStr.isEmpty()||companyPocEmaiStr.equals("--")){
                tvCmpPocEmail.setText("N/A");
            }else {
                tvCmpPocEmail.setText(companyPocEmaiStr);
                tvCmpPocEmail.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
}