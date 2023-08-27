package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;

public class CmpPostedJobDetailsActivity extends AppCompatActivity {


    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    private TextView cmpDetailJobType, cmpDetailJobRole,
            cmpDetailJobState, cmpDetailJobCity, cmpDetailVacantLocation, cmpDetailVacantNum,
            cmpDetailJobDescription, cmpDetailEducationRequire,cmpDetailSalaryMonthly, cmpDetailMinSalary,
            cmpDetailMaxSalary, cmpDetailFresherCan, cmpDetailExperienceCan,
            cmpDetailMinExp, cmpDetailMaxExp, cmpDetailMinAge,
            cmpDetailMaxAge, cmpDetailGenderReq, cmpDetailCommunicationReq,
            cmpDetailLanguageReq, cmpDetailVehicleReq, cmpDetailProcessorReq,
            cmpDetailPhone, cmpDetailDocumentReq, cmpDetailShiftFrom,
            cmpDetailShiftTo, cmpDetailWorkingDay, cmpDetailReimbursement, cmpDetailIncentive;


    private String cmpPostJobIdStr, cmpRegisterIdStr, viewNumStr, candidateStr, jobTitleStr, jobTypeStr,
            jobRoleStr, jobStateStr,jobCityStr, jobvacantLocationStr, jobvacantNumStr, jobDescriptionStr,jobSalaryTimeStr,
            educationStr, minSalaryStr, maxSalaryStr, fresherCanStr, experienceCanStr,
            expMonthStr, expMinStr, expMaxStr, minAgeStr, maxAgeStr, genderReqStr, communicationStr,
            languageReqStr, vehicleReqStr, processorReqStr, phoneReqSre, documentReqStr, workingDayStr,
            shiftDayFromStr, shiftDayToStr, shiftNightFromStr, shiftNightToStr, shiftRotationFromStr, shiftRotationToStr,
            reimbursementStr, incentiveStr, companyLogoStr, companyNameStr,
            companyEmailStr, companyPocNameStr, companyPocContactStr, companyPocDesignationStr,companyPocHeadOfficeLocation,companyPocCmpTypeStr,
            companyAboutStr, companyPostedByStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmp_posted_job_details);
        initMethod();
        setTextMethod();
    }

    private void setTextMethod() {

        Intent intentStr = getIntent();
        cmpPostJobIdStr = intentStr.getStringExtra("cmp_post_job_id");
        cmpRegisterIdStr = intentStr.getStringExtra("cmp_register_id");
        viewNumStr = intentStr.getStringExtra("");
        candidateStr = intentStr.getStringExtra("");
        jobTitleStr = intentStr.getStringExtra("cmp_pojo_title");
        jobRoleStr = intentStr.getStringExtra("cmp_pojo_role");
        jobTypeStr = intentStr.getStringExtra("cmp_pojo_job_type");
        jobStateStr = intentStr.getStringExtra("cmp_pojo_state");
        jobCityStr = intentStr.getStringExtra("cmp_pojo_city");
        jobvacantLocationStr = intentStr.getStringExtra("cmp_pojo_locality");
        jobvacantNumStr = intentStr.getStringExtra("cmp_pojo_opening");
        jobDescriptionStr = intentStr.getStringExtra("cmp_pojo_description");
        jobSalaryTimeStr = intentStr.getStringExtra("cmp_pojo_salary_time");
        minSalaryStr = intentStr.getStringExtra("cmp_pojo_offering_min_salary");
        maxSalaryStr = intentStr.getStringExtra("cmp_pojo_offering_max_salary");
        minAgeStr = intentStr.getStringExtra("cmp_pojo_min_age");
        maxAgeStr = intentStr.getStringExtra("cmp_pojo_max_age");
        fresherCanStr = intentStr.getStringExtra("cmp_pojo_fresher_can");
        experienceCanStr = intentStr.getStringExtra("cmp_pojo_experience_can");
        expMonthStr = intentStr.getStringExtra("cmp_pojo_exp_time");
        expMinStr = intentStr.getStringExtra("cmp_pojo_min_exp");
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
        companyLogoStr = intentStr.getStringExtra("cmp_pojo_company_logo");
        companyNameStr = intentStr.getStringExtra("cmp_pojo_company_name");
        companyEmailStr = intentStr.getStringExtra("cmp_pojo_company_email");
        companyPocNameStr = intentStr.getStringExtra("cmp_pojo_company_poc_name");
        companyPocContactStr = intentStr.getStringExtra("cmp_pojo_company_poc_contact");
        companyPocDesignationStr = intentStr.getStringExtra("cmp_pojo_company_poc_designation");
        companyPocHeadOfficeLocation=intentStr.getStringExtra("cmp_pojo_company_poc_head_office_location");
        companyPocCmpTypeStr=intentStr.getStringExtra("cmp_pojo_company_type");
        companyAboutStr = intentStr.getStringExtra("cmp_pojo_company_about");
        companyPostedByStr = intentStr.getStringExtra("cmp_pojo_company_posted_by");

//        if (jobTitleStr.isEmpty()) {
//            cmpDetailJobTitle.setText("--");
//        } else {
//            cmpDetailJobTitle.setText(jobTitleStr);
//        }
        if (jobRoleStr.isEmpty()) {
            cmpDetailJobRole.setText("--");
        } else {
            cmpDetailJobRole.setText(jobRoleStr);
        }

        if (jobTypeStr.isEmpty()) {
            cmpDetailJobType.setText("--");
        } else {
            cmpDetailJobType.setText(jobTypeStr);
        }

        if (jobStateStr.isEmpty()) {
            cmpDetailJobState.setText("--");
        } else {
            cmpDetailJobState.setText(jobStateStr);
        }

        if (jobCityStr.isEmpty()) {
            cmpDetailJobCity.setText("--");
        }else{
            cmpDetailJobCity.setText(jobCityStr);
        }

        if (jobvacantLocationStr.isEmpty()) {
            cmpDetailVacantLocation.setText("--");
        } else {
            cmpDetailVacantLocation.setText(jobvacantLocationStr);
        }

        if (jobvacantNumStr.isEmpty()) {
            cmpDetailVacantNum.setText("--");
        } else {
            cmpDetailVacantNum.setText(jobvacantNumStr);
        }

        if (jobDescriptionStr.isEmpty()) {
            cmpDetailJobDescription.setText("--");
        } else {
            cmpDetailJobDescription.setText(jobDescriptionStr);
        }

        cmpDetailSalaryMonthly.setText(jobSalaryTimeStr);
        cmpDetailMinSalary.setText(minSalaryStr);
        cmpDetailMaxSalary.setText(maxSalaryStr);
        cmpDetailMinAge.setText(minAgeStr);
        cmpDetailMaxAge.setText(maxAgeStr);
        if (fresherCanStr.isEmpty() || fresherCanStr.equals("True")) {
            cmpDetailFresherCan.setText("Fresher Can Apply");
        } else {
            cmpDetailFresherCan.setText("Fresher cannot Apply");
        }
        if (experienceCanStr.isEmpty() || experienceCanStr.equals("True")) {
            cmpDetailExperienceCan.setText("Experience can apply");
        } else {
            cmpDetailExperienceCan.setText("Experience cannot apply");
        }
        cmpDetailMinExp.setText(expMinStr);
        cmpDetailMaxExp.setText(expMaxStr);
        cmpDetailEducationRequire.setText(educationStr.replaceAll(",$", ""));
        cmpDetailGenderReq.setText(genderReqStr);
        cmpDetailCommunicationReq.setText(communicationStr.replaceAll(",$", ""));
        cmpDetailLanguageReq.setText(languageReqStr.replaceAll(",$", ""));
        cmpDetailVehicleReq.setText(vehicleReqStr.replaceAll(",$", ""));
        cmpDetailProcessorReq.setText(processorReqStr.replaceAll(",$", ""));
        cmpDetailPhone.setText(phoneReqSre.replaceAll(",$", ""));
        cmpDetailDocumentReq.setText(documentReqStr.replaceAll(",$", ""));

        if (!shiftDayFromStr.equals("--") && !shiftDayToStr.equals("--")) {
            cmpDetailShiftFrom.setText(shiftDayFromStr);
            cmpDetailShiftTo.setText(shiftDayToStr);
        } else if (!shiftNightFromStr.equals("--") && !shiftNightToStr.equals("--")) {
            cmpDetailShiftFrom.setText(shiftNightFromStr);
            cmpDetailShiftTo.setText(shiftNightToStr);
        } else if (!shiftRotationFromStr.equals("--") && !shiftRotationToStr.equals("--")) {
            cmpDetailShiftFrom.setText(shiftRotationFromStr);
            cmpDetailShiftTo.setText(shiftRotationToStr);
        } else {
            cmpDetailShiftFrom.setText("--");
            cmpDetailShiftTo.setText("--");
        }

        cmpDetailWorkingDay.setText(workingDayStr);
        cmpDetailReimbursement.setText(reimbursementStr.replaceAll(",$", ""));
        cmpDetailIncentive.setText(incentiveStr);

    }
    private void initMethod() {

//        cmpDetailJobTitle = findViewById(R.id.cmp_list_details_job_title);
        cmpDetailJobType = findViewById(R.id.cmp_list_details_job_type);
        cmpDetailJobRole = findViewById(R.id.cmp_list_details_job_role);
        cmpDetailJobState = findViewById(R.id.cmp_list_details_job_state);
        cmpDetailJobCity = findViewById(R.id.cmp_list_details_job_city);
        cmpDetailVacantLocation = findViewById(R.id.cmp_list_details_vacant_location);
        cmpDetailVacantNum = findViewById(R.id.cmp_list_details_vacant_location_num);
        cmpDetailJobDescription = findViewById(R.id.cmp_list_details_job_description);
        cmpDetailEducationRequire = findViewById(R.id.cmp_list_details_education);
        cmpDetailSalaryMonthly=findViewById(R.id.cmp_list_details_salary_time);
        cmpDetailMinSalary = findViewById(R.id.cmp_list_details_min_salary);
        cmpDetailMaxSalary = findViewById(R.id.cmp_list_details_max_salary);
        cmpDetailFresherCan = findViewById(R.id.cmp_list_details_fresher_can);
        cmpDetailExperienceCan = findViewById(R.id.cmp_list_details_experience_can);
        cmpDetailMinExp = findViewById(R.id.cmp_list_details_exp_from);
        cmpDetailMaxExp = findViewById(R.id.cmp_list_details_exp_to);
        cmpDetailMinAge = findViewById(R.id.cmp_list_details_min_age);
        cmpDetailMaxAge = findViewById(R.id.cmp_list_details_max_age);
        cmpDetailGenderReq = findViewById(R.id.cmp_list_details_gender_req);
        cmpDetailCommunicationReq = findViewById(R.id.cmp_list_details_communication_req);
        cmpDetailLanguageReq = findViewById(R.id.cmp_list_details_language_req);
        cmpDetailVehicleReq = findViewById(R.id.cmp_list_details_vehicle_req);
        cmpDetailProcessorReq = findViewById(R.id.cmp_list_details_processor_req);
        cmpDetailPhone = findViewById(R.id.cmp_list_details_phone_req);
        cmpDetailDocumentReq = findViewById(R.id.cmp_list_details_documents_req);
        cmpDetailShiftFrom = findViewById(R.id.cmp_list_details_shift_from);
        cmpDetailShiftTo = findViewById(R.id.cmp_list_details_shift_to);
        cmpDetailWorkingDay = findViewById(R.id.cmp_list_details_working_day_num);
        cmpDetailReimbursement = findViewById(R.id.cmp_list_details_reimbursement);
        cmpDetailIncentive = findViewById(R.id.cmp_list_details_incentive_num);

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