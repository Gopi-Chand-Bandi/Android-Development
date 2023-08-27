package com.abc.justjob.ApiFile;

import com.abc.justjob.ApiFile.CompanyFetch.companyProfileResult;
import com.abc.justjob.ApiFile.CompiesDetails.CompaniesContactedFrgValues;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Company.CompanyActivitys.cmpTrackerModel;
import com.abc.justjob.Company.CompanyApplicationFrg.cmpAplcResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api_cmp_post_job {

    @FormUrlEncoded
    @POST("Company/cmp_post_job_new.php?apicall=post_job_all_fragments")
    Call<Result> cmpPostJobInterfaceAll(
            @Field("cmp_reg_id") String cmp_user_id,
            @Field("cmp_pojo_title") String jobTitle,
            @Field("cmp_pojo_role") String jobRole,
            @Field("cmp_pojo_designation") String jobDesig,
            @Field("cmp_pojo_type") String jobType,
            @Field("cmp_pojo_state") String jobState,
            @Field("cmp_pojo_cities") String jobCities,

            @Field("cmp_pojo_description") String jobDescription,
            @Field("cmp_pojo_salary_time") String jobSalaryTime,
            @Field("cmp_pojo_offering_min_salary") String jobMinSalary,
            @Field("cmp_pojo_offering_max_salary") String jobMaxSalary,
            @Field("cmp_pojo_min_age") String jobMinAge,
            @Field("cmp_pojo_max_age") String jobMaxAge,
            @Field("cmp_pojo_locality") String jobLocality,
            @Field("cmp_pojo_opening") String jobNoOpening,

            @Field("cmp_pojo_fresher_can") String jobFresherCan,
            @Field("cmp_pojo_experience_can") String jobExpCan,
            @Field("cmp_pojo_min_exp") String jobMinExp,
            @Field("cmp_pojo_max_exp") String jobMaxExp,

            @Field("cmp_pojo_education") String jobEducation,
            @Field("cmp_pojo_male_or_female") String jobMaleFemale,

            @Field("cmp_pojo_english_know") String jobEnglishKnow,
            @Field("cmp_pojo_language_know") String jobLanguageKnow,

            @Field("cmp_pojo_vehicle") String jobVehicle,
            @Field("cmp_pojo_processor") String jobProcessor,
            @Field("cmp_pojo_phone") String jobPhone,

            @Field("cmp_pojo_documents") String jobDocument,
            @Field("cmp_pojo_working_day") String jobWorkingDay,

            @Field("cmp_pojo_day_shift_from") String jobDayFrom,
            @Field("cmp_pojo_day_shift_to") String jobDayTo,
            @Field("cmp_pojo_night_shift_from") String jobNightFrom,
            @Field("cmp_pojo_night_shift_to") String jobNightTo,
            @Field("cmp_pojo_rotate_shift_from") String jobRotateFrom,
            @Field("cmp_pojo_rotate_shift_to") String jobRotateTo,

            @Field("cmp_pojo_reimbursement") String jobReim,
            @Field("cmp_pojo_incentive") String jobIncentive,
            @Field("cmp_pojo_depositing") String jobDepositing,
            @Field("cmp_pojo_deposit_amount") String jobDepoAmount,
            @Field("cmp_pojo_amount_purpose") String jobDepoPurp,

            @Field("cmpPjCmpName") String jobCmpName,
            @Field("cmpPjEmail") String jobCmpEmail,
            @Field("cmPjCmpPocName") String jobCmpPocName,
            @Field("cmpPjPocContact") String jobCmpPocContact,
            @Field("cmpPjPocDesignation") String jobCmpPocDesig,
            @Field("cmpPjPocOfficeLocation") String jobCmpPocOfficeLocation,
            @Field("cmpPojoCompanyType") String jobCmpType,
            @Field("cmpPojoIndustry") String cmpIndustryStr,
            @Field("cmpPjPocAbout") String jobCmpAbout,
            @Field("cmpPjPocPostedBy") String jobCmpPostedBy,
            @Field("cmpPjDateTime") String dateToStr
    );


    @FormUrlEncoded
    @POST("Company/cmpApplicationFrg.php?apicall=inserDeletedPostedJob")
    Call<Result> cmpPostJobDelete(
            @Field("cmp_delete_date") String cmpDelete,
            @Field("cmp_reg_id") String cmp_user_id,
            @Field("cmp_pojo_title") String jobTitle,
            @Field("cmp_pojo_role") String jobRole,
            @Field("cmp_pojo_designation") String jobDesig,
            @Field("cmp_pojo_type") String jobType,
            @Field("cmp_pojo_state") String jobState,
            @Field("cmp_pojo_cities") String jobCities,

            @Field("cmp_pojo_description") String jobDescription,
            @Field("cmp_pojo_salary_time") String jobSalaryTime,
            @Field("cmp_pojo_offering_min_salary") String jobMinSalary,
            @Field("cmp_pojo_offering_max_salary") String jobMaxSalary,
            @Field("cmp_pojo_min_age") String jobMinAge,
            @Field("cmp_pojo_max_age") String jobMaxAge,
            @Field("cmp_pojo_locality") String jobLocality,
            @Field("cmp_pojo_opening") String jobNoOpening,

            @Field("cmp_pojo_fresher_can") String jobFresherCan,
            @Field("cmp_pojo_experience_can") String jobExpCan,
            @Field("cmp_pojo_min_exp") String jobMinExp,
            @Field("cmp_pojo_max_exp") String jobMaxExp,

            @Field("cmp_pojo_education") String jobEducation,
            @Field("cmp_pojo_male_or_female") String jobMaleFemale,

            @Field("cmp_pojo_english_know") String jobEnglishKnow,
            @Field("cmp_pojo_language_know") String jobLanguageKnow,

            @Field("cmp_pojo_vehicle") String jobVehicle,
            @Field("cmp_pojo_processor") String jobProcessor,
            @Field("cmp_pojo_phone") String jobPhone,

            @Field("cmp_pojo_documents") String jobDocument,
            @Field("cmp_pojo_working_day") String jobWorkingDay,

            @Field("cmp_pojo_day_shift_from") String jobDayFrom,
            @Field("cmp_pojo_day_shift_to") String jobDayTo,
            @Field("cmp_pojo_night_shift_from") String jobNightFrom,
            @Field("cmp_pojo_night_shift_to") String jobNightTo,
            @Field("cmp_pojo_rotate_shift_from") String jobRotateFrom,
            @Field("cmp_pojo_rotate_shift_to") String jobRotateTo,

            @Field("cmp_pojo_reimbursement") String jobReim,
            @Field("cmp_pojo_incentive") String jobIncentive,
            @Field("cmp_pojo_depositing") String jobDepositing,
            @Field("cmp_pojo_deposit_amount") String jobDepoAmount,
            @Field("cmp_pojo_amount_purpose") String jobDepoPurp,

            @Field("cmpPjCmpName") String jobCmpName,
            @Field("cmpPjEmail") String jobCmpEmail,
            @Field("cmPjCmpPocName") String jobCmpPocName,
            @Field("cmpPjPocContact") String jobCmpPocContact,
            @Field("cmpPjPocDesignation") String jobCmpPocDesig,
            @Field("cmpPjPocOfficeLocation") String jobCmpPocOfficeLocation,
            @Field("cmpPojoCompanyType") String jobCmpType,
            @Field("cmpPojoIndustry") String cmpIndustryStr,
            @Field("cmpPjPocAbout") String jobCmpAbout,
            @Field("cmpPjPocPostedBy") String jobCmpPostedBy,
            @Field("cmpPjDateTime") String dateToStr
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_one")
    Call<Result> cmpPostJobInterfaceOne(
            @Field("cmp_pojo_user_Id") String cmp_user_id,
            @Field("cmp_pojo_title") String jobTitle,
            @Field("cmp_pojo_role") String jobRole,
            @Field("cmp_pojo_designation") String jobDesig,
            @Field("cmp_pojo_type") String jobType,
            @Field("cmp_pojo_state") String jobState,
            @Field("cmp_pojo_cities") String jobCities
    );

    @FormUrlEncoded
    @POST("cmp_post_job.php?apicall=postjob_fragment_two")
    Call<Result> cmpPostJobInterfaceTwo(
            @Field("cmp_post_job_id") int cmpPostJobId,
            @Field("cmp_pojo_locality") String jobLocality,
            @Field("cmp_pojo_opening") String jobNoOpening
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_three")
    Call<Result> cmpPostJobInterfaceThree(
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_description") String jobDescription,
            @Field("cmp_pojo_salary_time") String jobSalaryTime,
            @Field("cmp_pojo_offering_min_salary") String jobMinSalary,
            @Field("cmp_pojo_offering_max_salary") String jobMaxSalary,
            @Field("cmp_pojo_min_age") String jobMinAge,
            @Field("cmp_pojo_max_age") String jobMaxAge,
            @Field("cmp_pojo_locality") String jobLocality,
            @Field("cmp_pojo_opening") String jobNoOpening
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_four")
    Call<Result> cmpPostJobInterfaceFour(
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_fresher_can") String jobFresherCan,
            @Field("cmp_pojo_experience_can") String jobExpCan,
            @Field("cmp_pojo_min_exp") String jobMinExp,
            @Field("cmp_pojo_max_exp") String jobMaxExp
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_five")
    Call<Result> cmpPostJobInterfaceFive(
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_education") String jobEducation,
            @Field("cmp_pojo_male_or_female") String jobMaleFemale
    );
    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_six")
    Call<Result>  cmpPostJobInterfaceSix(
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_english_know") String jobEnglishKnow,
            @Field("cmp_pojo_language_know") String jobLanguageKnow
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_seven")
    Call<Result> cmpPostJobInterfaceSeven(
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_vehicle") String jobVehicle,
            @Field("cmp_pojo_processor") String jobProcessor,
            @Field("cmp_pojo_phone") String jobPhone
    );
    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_eight")
    Call<Result> cmpPostJobInterfaceEight(
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_documents") String jobDocument,
            @Field("cmp_pojo_working_day") String jobWorkingDay
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_nine")
    Call<Result> cmpPostJobInterfaceNine(
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_day_shift_from") String jobDayFrom,
            @Field("cmp_pojo_day_shift_to") String jobDayTo,
            @Field("cmp_pojo_night_shift_from") String jobNightFrom,
            @Field("cmp_pojo_night_shift_to") String jobNightTo,
            @Field("cmp_pojo_rotate_shift_from") String jobRotateFrom,
            @Field("cmp_pojo_rotate_shift_to") String jobRotateTo
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_ten")
    Call<Result> cmpPostJobInterfaceTen (
            @Field("cmp_pojo_last_id") String jobLastId,
            @Field("cmp_pojo_reimbursement") String jobReim,
            @Field("cmp_pojo_incentive") String jobIncentive,
            @Field("cmp_pojo_depositing") String jobDepositing,
            @Field("cmp_pojo_deposit_amount") String jobDepoAmount,
            @Field("cmp_pojo_amount_purpose") String jobDepoPurp
    );

    @FormUrlEncoded
    @POST("Company/cmp_post_job.php?apicall=postjob_fragment_eleven")
    Call<Result> cmpPostJobInterfaceEleven(
            @Field("cmpPjLastId") String jobLastId,
            @Field("cmpPjCmpName") String jobCmpName,
            @Field("cmpPjEmail") String jobCmpEmail,
            @Field("cmPjCmpPocName") String jobCmpPocName,
            @Field("cmpPjPocContact") String jobCmpPocContact,
            @Field("cmpPjPocDesignation") String jobCmpPocDesig,
            @Field("cmpPjPocOfficeLocation") String jobCmpPocOfficeLocation,
            @Field("cmpPojoCompanyType") String jobCmpType,
            @Field("cmpPojoIndustry") String cmpIndustryStr,
            @Field("cmpPjPocAbout") String jobCmpAbout,
            @Field("cmpPjPocPostedBy") String jobCmpPostedBy,
            @Field("cmpPjDateTime") String dateToStr
    );

    @FormUrlEncoded
    @POST("cndApplyForJobs.php?apicall=insertViewCountById")
    Call<Result> cndCountViewPostJobs(
            @Field("cndRegister_id") String cndRegId,
            @Field("cmpPostJob_id") String cmpPostjobId
    );

    @FormUrlEncoded
    @POST("cndApplyForJobs.php?apicall=cndApplyForJob")
    Call<Result> cndApplyForJob(
            @Field("cndRegId") String cndRegisterId,
            @Field("cmpRegId") String cmpRegisterId,
            @Field("cmpPoJoId") String cndPostJobId
    );

    @FormUrlEncoded
    @POST("cndApplyForJobs.php?apicall=fetchPostedJobCndDetail")
    Call<cmpAplcResponse> getSelectedCandidates(
            @Field("cmpPojoId") String cmpPojoId,
            @Field("cndRegId") String cndRegId
    );

    @FormUrlEncoded
    @POST("cndApplyForJobs.php?apicall=check_cnd_applied_4_job")
    Call<cmpAplcResponse> getCndApplied4Job(
            @Field("cmpRegId") String cmpRegId,
            @Field("cmpPojoId") String cmpPojoId,
            @Field("cndRegId") String cndRegId
    );

//    @FormUrlEncoded
//    @POST("student_info_fetch.php?apicall=contact_company")
//    Call<CompaniesContactedFrgValues> setCandidate_ContactedDetails(
//            @Field("cndRegName") String cndRegName,
//            @Field("company_name") String company_name,
//            @Field("status") String status
//    );

    @FormUrlEncoded
    @POST("student_info_fetch.php?apicall=contact_verification")
    Call<Void> ContactedDetails_verified(
            @Field("cndId") String cndId,
            @Field("cmp_id") String cmp_id,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("student_info_fetch.php?apicall=candidate_apply_contact")
    Call<Void> setCandidate_Apply_Contact_Details(
            @Field("cndRegId") String cndRegId,
            @Field("cndRegName") String cndRegName,
            @Field("cndEmail") String cndEmail,
            @Field("cmpPostJobIdStr") String cmpPostJobIdStr,
            @Field("jobRoleStr") String jobRoleStr,
            @Field("cmpRegisterIdStr") String cmpRegisterIdStr,
            @Field("cmpName") String cmpName,
            @Field("companyPocEmaiStr") String companyPocEmaiStr,
            @Field("status") String status,
            @Field("From") String From,
            @Field("To") String To,
            @Field("came") String came
    );

    @FormUrlEncoded
    @POST("student_info_fetch.php?apicall=contact_company")
    Call<Void> setCandidate_ContactedDetails(
            //@Query("apicall") String apicall,
            @Field("cndId") String cndId,
            @Field("cndRegName") String cndRegName,
            @Field("cndEmail") String cndEmail,
            @Field("cmp_id") String cmp_id,
            @Field("cmp_company_name") String cmp_company_name,
            @Field("cmp_email") String cmp_email,
            @Field("status") String status,
            @Field("From") String From,
            @Field("To") String To
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=decrease_free_company_candidate_contact")
    Call<Void> decrease_company_candidate_contact_free_count(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=decrease_free_company_company_contact")
    Call<Void> decrease_company_company_contact_free_count(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=decrease_onclick_count")
    Call<Void> decrease_company_contact_onClick_count(
            @Field("fromId") String fromId,
            @Field("ToId") String ToId,
            @Field("from") String from,
            @Field("to") String to
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=decrease_company_candidate_contact_premium_count")
    Call<Void> decrease_company_candidate_contact_premium_count(
            @Field("cmpRegId") String cmpRegId
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=decrease_company_company_contact_premium_count")
    Call<Void> decrease_company_company_contact_premium_count(
            @Field("cmpRegId") String cmpRegId
    );

    @FormUrlEncoded
    @POST("Company/cmp_payments.php?apicall=OnclickPayments")
    Call<Void> OnclickPayments(
            @Field("payedDate") String payedDate,
            @Field("amount") Double amount,
            @Field("TransactionId") String TransactionId,
            @Field("cmpRegId") String cmpRegId,
            @Field("cmpRegName") String cmpRegName,
            @Field("cmpRegEmail") String cmpRegEmail,
            @Field("cndRegisterId") String cndRegisterId,
            @Field("nameStr") String nameStr,
            @Field("emailStr") String emailStr,
            @Field("count") Integer count,
            @Field("from") String from,
            @Field("to") String to,
            @Field("came_from") String came_from
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=company_contact_details")
    Call<Void> directContactedHistory(
            @Field("payedDate") String payedDate,
            @Field("cmpRegId") String cmpRegId,
            @Field("cmpRegName") String cmpRegName,
            @Field("cmpRegEmail") String cmpRegEmail,
            @Field("cndRegisterId") String cndRegisterId,
            @Field("nameStr") String nameStr,
            @Field("emailStr") String emailStr,
            @Field("from") String from,
            @Field("to") String to,
            @Field("came_from") String came_from
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=TenureDaysBalance")
    Call<Void> calculateTenureDaysBalance(
            @Field("cmpRegId") String cmpRegId
    );


//    @FormUrlEncoded
//    @POST("Company/cmp_login_register.php?apicall=cmp_register_activity")
//    Call<Result> cmpRegisteration(
//            @Field("cmp_reg_id") String cmpRegisterId,
//            @Field("cmp_full_name") String cmp_full_name,
//            @Field("cmp_contact") String cmp_contact,
//            @Field("cmp_designation") String cmp_designation,
//            @Field("cmp_email") String cmp_email,
//            @Field("cmp_Company_name") String cmp_company_name,
//            @Field("cmp_head_office_location") String cmp_head_office_location,
//            @Field("cmp_industry") String cmp_industry,
//            @Field("cmp_company_category") String cmp_category,
//            @Field("cmp_job_type") String cmp_job_type,
//            @Field("cmp_company_address") String cmp_company_address,
//            @Field("cmp_about_company") String cmp_about_company,
//            @Field("cmp_date_time") String cmpDateTime
//    );

    @FormUrlEncoded
    @POST("Company/cmp_login_register.php?apicall=cmp_register_activity")
    Call<Result> cmpRegisteration(
            @Field("cmp_reg_id") String cmpRegisterId,
            @Field("cmp_full_name") String cmp_full_name,
            @Field("cmp_contact") String cmp_contact,
            @Field("cmp_designation") String cmp_designation,
            @Field("cmp_email") String cmp_email,
            @Field("cmp_Company_name") String cmp_company_name,
            @Field("cmp_head_office_location") String cmp_head_office_location,
            @Field("cmp_industry") String cmp_industry,
            @Field("cmp_company_category") String cmp_category,
            @Field("cmp_job_type") String cmp_job_type,
            @Field("cmp_company_address") String cmp_company_address,
            @Field("cmp_about_company") String cmp_about_company,
            @Field("cmp_link") String cmpWebsiteLinkStr,
            @Field("emp_size") String empsSizeStr,
            @Field("cmp_state") String stateStr,
            @Field("cmp_city") String cityStr
    );

    @FormUrlEncoded
    @POST("Company/cmp_login_register.php?apicall=cmp_update_profile")
    Call<Result> cmpUpdateProfile(
            @Field("cmp_reg_id") String cmpRegisterId,
            @Field("cmp_full_name") String cmp_full_name,
            @Field("cmp_contact") String cmp_contact,
            @Field("cmp_designation") String cmp_designation,
            @Field("cmp_email") String cmp_email,
            @Field("cmp_Company_name") String cmp_company_name,
            @Field("cmp_head_office_location") String cmp_head_office_location,
            @Field("cmp_industry") String cmp_industry,
            @Field("cmp_company_category") String cmp_category,
            @Field("cmp_job_type") String cmp_job_type,
            @Field("cmp_company_address") String cmp_company_address,
            @Field("cmp_about_company") String cmp_about_company
    );

    @FormUrlEncoded
    @POST("Company/cmp_fetch.php?apicall=cmp_fetch_company_profile")
    Call<companyProfileResult> cmpProfileFetch(
            @Field("company_reg_id") String cmpRegisterId
    );

    @FormUrlEncoded
    @POST("payments_operations.php?apicall=cmp_tracker_activity")
    Call<cmpTrackerModel> cmpTracker(
            @Field("cmp_reg_id") String cmpRegisterId
    );

    @FormUrlEncoded
    @POST("Company/cmp_feadback.php?apicall=cmpFeadBackOperation")
    Call<Result> feadBackOperation(
            @Field("justjobUserId") String cmpRegisterId,
            @Field("justjobUserEmail") String cmpUserEmail,
            @Field("justjobFdMessage") String cmpUserFdMessage
    );
    @FormUrlEncoded
    @POST("payments_operations.php?apicall=get_cmp_pay_count_new")
    Call<Result> cndCountPaymentInfo(
            @Field("cmp_reg_id") String cmpRegId,
            @Field("current_date") String currentDate
    );

    @FormUrlEncoded
    @POST("Company/cmp_payments.php?apicall=cmp_post_job_payment")
    Call<Result> cmpPostJobOperation(
            @Field("cmp_reg_id") String cmpRegId
    );

    @FormUrlEncoded
    @POST("Company/cmp_payments.php?apicall=count_post_job")
    Call<Result> cmpPostJobCountOperation(
            @Field("cmp_reg_id") String cmpRegId,
            @Field("cmp_current_date") String cmpCurrentDate
    );

    @FormUrlEncoded
    @POST("Company/cmp_payments.php?apicall=cmp_post_job_count_update")
    Call<Result> cmpPostJobCount(
            @Field("cmp_reg_id") String cmpRegId
    );

    @FormUrlEncoded
    @POST("payments_operations.php?apicall=update_data_access_count")
    Call<Result> updateDataAccess(
            @Field("cmp_reg_id") String cmpRegId
    );

    @FormUrlEncoded
    @POST("payments_operations.php?apicall=update_cnd_data_access_count_4_cmp")
    Call<Result> updateCndViewDataAccess(
            @Field("cmp_reg_id") String cmpRegId,
            @Field("cnd_reg_id") String cndRegId
    );

    @FormUrlEncoded
    @POST("payments_operations.php?apicall=update_cmp_call_count")
    Call<Result> updateCmpCallCount(
            @Field("pay_date_time") String dateTime,
            @Field("cmp_reg_id") String cmpRegId,
            @Field("cnd_reg_id") String cndRegisterId
    );
    @FormUrlEncoded
    @POST("payments_operations.php?apicall=update_cmp_pay_call_count")
    Call<Result> updateCmpPayCallCount(
            @Field("pay_date_time") String payDateTime,
            @Field("cmp_reg_id") String cmpRegId,
            @Field("cnd_reg_id") String cndRegisterId,
            @Field("pay_reference_key") String pay_reference_key
    );
    @FormUrlEncoded
    @POST("Company/cmp_select_reject_cnd.php?apicall=get_cmp_select_reject_cnd")
    Call<Result> getSelectOrNotByCompany(
            @Field("cmpPostJob_id") String cmpPostJob_id,
            @Field("cndRegister_id") String cndRegister_id
    );

    @FormUrlEncoded
    @POST("Company/cmp_select_reject_cnd.php?apicall=get_cmp_select_reject_cnd_for_cmp_home")
    Call<Result> getSelectOrNotByCompanyForHome(
            @Field("cmpCmp_Reg_id") String cmpCmp_Reg_id,
            @Field("cndRegister_id") String cndRegister_id
    );

    @FormUrlEncoded
    @POST("Company/cmp_select_reject_cnd.php?apicall=cmp_select_reject_cnd")
    Call<Result> selectRejectByCompany(
            @Field("cmpReg_Id") String cmpReg_Id,
            @Field("cmpPostJob_id") String cmpPostJob_id,
            @Field("cndRegister_id") String cndRegister_id,
            @Field("select_bl") boolean select_bl
    );
}
