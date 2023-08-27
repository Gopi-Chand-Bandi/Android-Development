package com.abc.justjob.ApiFile;

import java.util.List;

import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgValues;
import com.abc.justjob.ApiFile.CandidateFetchCompany.cndProfileResult;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Api.php?apicall=cndGetValidUser4PasswordChange")
    Call<Result> getValidCandidate(
            @Field("jjUserName") String uName,
            @Field("jjUserEmail") String uEmail
    );

    @FormUrlEncoded
    @POST("Api.php?apicall=cndUpdateNewPassword")
    Call<Result> changeNewPassCandidate(
            @Field("jjNewPasswordCnd") String newPassword,
            @Field("jjUserEmail") String uEmail
    );

    @FormUrlEncoded
    @POST("Api.php?apicall=registerCnd")
    Call<Result> getCandidateRegi(
            @Field("jjUserName") String name,
            @Field("jjUserEmail") String email,
            @Field("jjUserPassword") String password
    );

    @FormUrlEncoded
    @POST("Api.php?apicall=loginCndChecker")
        //@POST("/Api.php?apicall=login")
    Call<Result> getCandidateLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Company/cmp_login_register.php?apicall=cmpRegisteration")
    Call<Result> getCompanyReg(
            @Field("jjUserName") String name,
            @Field("jjUserEmail") String email,
            @Field("jjUserPassword") String password
    );

    @FormUrlEncoded
    @POST("Company/cmp_login_register.php?apicall=loginCmpChecker")
    Call<Result> getCompanyLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Company/cmp_login_register.php?apicall=cmpGetValidUser4PasswordChange")
    Call<Result> getValidCompany(
            @Field("jjUserName") String uName,
            @Field("jjUserEmail") String uEmail
    );

    @FormUrlEncoded
    @POST("Company/cmp_login_register.php?apicall=cmpUpdateNewPassword")
    Call<Result> newPasswordCompany(
            @Field("jjNewPasswordCnd") String newPassword,
            @Field("jjUserEmail") String uEmail
    );

    @FormUrlEncoded
    @POST("cmp_about_uploader.php?apicall=cmpRegAboutUploader")
    Call<Result> insertCompanyAboutUploader(
            @Field("cmp_user_id_") String cmp_user_id,
            @Field("cmp_Logo_Img_") String cmpLogoImage,
            @Field("cmp_Uploader_Img_") String cmpUploaderImage,
            @Field("cmp_Full_Name_") String cmpFullName,
            @Field("cmp_Contact_No_") String cmpContactNo,
            @Field("cmp_Designation_") String cmpDesignation
    );

    @FormUrlEncoded
    @POST("cmp_about_uploader.php?apicall=CompanyRegCompanyInfo")
    Call<Result> insertCompanyInfo(
            @Field("cmp_User_Id") String cmp_user_id,
            @Field("cmp_Brand_Name") String cmpBrandName,
            @Field("cmp_Registered_Name") String cmpRegisteredName,
            @Field("cmp_Head_Office_Located") String cmpHeadOfficeLocated,
            @Field("cmp_Address") String cmpAddress,
            @Field("cmp_About") String cmpAbout,
            @Field("cmp_Industry") String cmpIndustry,
            @Field("cmp_Job_Type") String cmpJobType
    );

    @FormUrlEncoded
    @POST("student_info.php?apicall=CD_PersonalInformation")
    Call<Result> CandidateInfoInsert(
            @Field("cd_id") String userId,
            @Field("cd_profile_image")  String imageProfile,
            @Field("cd_FullName") String fullName,
            @Field("cd_ContactNumber") String contactNumber,
            @Field("cd_AlterNumber") String alterContactNumber,
            @Field("cd_Gender") String genderStr
    );

    @FormUrlEncoded
    @POST("student_info.php?apicall=StudentLocalityInfo")
    Call<Result> insertCandidateLocalityFeg(
            @Field("last_user_id") String user_id,
            @Field("cndState") String studentState,
            @Field("cndCurrentLocation") String studentCurrentLocation,
            @Field("cndDataOfBirth") String dateOfBirthEt,
            @Field("cndJobProfileOne") String jobProfileOne,
            @Field("cndJobProfileTwo") String jobProfileTwo);

    @FormUrlEncoded
    @POST("student_info.php?apicall=StudentQualifFrg")
    Call<Result> insertCandidateQualification(
            @Field("last_user_id") String user_id,
            @Field("cnd_qualifi_std") String qualiStd,
            @Field("cnd_qualifi_stream") String qualiStream,
            @Field("cnd_college_name") String qualiCollegeName,
            @Field("cnd_qualifi_start_date") String qualiStartDate,
            @Field("cnd_qualifi_end_date") String qualiEndDate
    );

    @FormUrlEncoded
    @POST("student_info.php?apicall=StudentExperience")
    Call<Result> insertCandidateExperience(
            @Field("last_user_id") String user_id,
            @Field("cd_exp_job_industry") String expIndustry,
            @Field("cd_exp_job_role") String expRole,
            @Field("cd_exp_company_name") String expCompanyName,
            @Field("cd_exp_start_date") String expStartDate,
            @Field("cd_exp_end_date") String expEndDate
    );

    @GET("student_info_fetch.php?apicall=fetchAllPostedJobs")
    Call<List<CndHomeFrgValues>> getPostedJobs();

    @FormUrlEncoded
    @POST("cnd_Update_profile.php?apicall=cnd_registration_profile")
    Call<Result> cndRegistration(
            @Field("cndRegId") String user_id,
            @Field("cndFullName") String nameStr,
            @Field("cndEmail") String emailStr,
            @Field("cndGender") String genderStr,
            @Field("cndDob") String dobStr,
            @Field("cndContact") String contactStr,
            @Field("cndAlterContact") String alterContactStr,
            @Field("cndLocation") String locationStr,
            @Field("cndState") String stateStr,
            @Field("cndCity") String cndCity,
            @Field("cndJobProfileOne") String jobProfileOneStr,
            @Field("cndJobDesignationOne") String jobDesigOneStr,
            @Field("cndJobProfileTwo") String jobProfileTwoStr,
            @Field("cndJobDesignationTwo") String jobDesigTwoStr,
            @Field("cndQualiStd") String QualiStdStr,
            @Field("cndQualiStream") String QualiStreamStr,
            @Field("cndQualiCollege") String QualiCollegeStr,
            @Field("cndQualiStartDate") String qualiStartDateStr,
            @Field("cndQualiEndDate") String qualiEndDateStr,
            @Field("cndFresherInter") String fresherInternExpStr,
            @Field("cndExpIndustry") String expJobIndustryStr,
            @Field("cndExpCompany") String expCompanyNameStr,
            @Field("cndExpCurrentSalary") String expCurrentSalaryStr,
            @Field("cndExpStartDate") String expStartDateStr,
            @Field("cndExpEndDate") String expEndDateStr,
            @Field("cndCommunication") String communicationStr,
            @Field("cndEmploymentType") String EmploymentStr,
            @Field("cndLocationprefer") String LocationpreferenceStr,
            @Field("cndLanguage") String languagesKnowStr,
            @Field("cndVehicle") String vahicleStr,
            @Field("cndLicence") String licenceStr,
            @Field("cndDocuments") String documentsStr,
            @Field("cndSkill") String skillStr,
            @Field("cndReference") String referenceStr,
            @Field("cndResume") String resumeStr,
            @Field("cndResumeExtension") String cmpResumeExtension

    );

    @FormUrlEncoded
    @POST("cnd_Update_profile.php?apicall=cnd_Update_Profile_Info")
    Call<Result> cndEditProfile(
            @Field("cndRegIdEdit") String user_id,
            @Field("cndFullNameEdit") String nameStr,
            @Field("cndEmailEdit") String emailStr,
            @Field("cndGenderEdit") String genderStr,
            @Field("cndDobEdit") String dobStr,
            @Field("cndContactEdit") String contactStr,
            @Field("cndAlterContactEdit") String alterContactStr,
            @Field("cndLocationEdit") String locationStr,
            @Field("cndStateEdit") String stateStr,
            @Field("cndCityEdit") String cityStr,
            @Field("cndJobProfileOneEdit") String jobProfileOneStr,
            @Field("cndJobDesignationOneEdit") String jobDesigOneStr,
            @Field("cndJobProfileTwoEdit") String jobProfileTwoStr,
            @Field("cndJobDesignationTwoEdit") String jobDesigTwoStr,
            @Field("cndQualiStdEdit") String QualiStdStr,
            @Field("cndQualiStreamEdit") String QualiStreamStr,
            @Field("cndQualiCollegeEdit") String QualiCollegeStr,
            @Field("cndQualiStartDateEdit") String qualiStartDateStr,
            @Field("cndQualiEndDateEdit") String qualiEndDateStr,
            @Field("cndFresherInterEdit") String fresherInternExpStr,
            @Field("cndExpIndustryEdit") String expJobIndustryStr,
            @Field("cndExpJobRoleEdit") String expJobRoleStr,
            @Field("cndExpCompanyEdit") String expCompanyNameStr,
            @Field("cndExpCurrentSalaryEdit") String expCurrentSalaryStr,
            @Field("cndExpDesignationEdit") String expDesignationStr,
            @Field("cndExpStartDateEdit") String expStartDateStr,
            @Field("cndExpEndDateEdit") String expEndDateStr,
            @Field("cndCommunicationEdit") String communicationStr,
            @Field("cndLanguageEdit") String languagesKnowStr,
            @Field("cndVehicleEdit") String vahicleStr,
            @Field("cndLicenceEdit") String licenceStr,
            @Field("cndDocumentEdit") String documentStr,
            @Field("cndSkillEdit") String skillStr,
            @Field("cndReferenceEdit") String referenceStr,
            @Field("cndResumeUrlEdit") String resumeUrl,
            @Field("cndResumeExtensionEdit") String extensionStr
    );

    @FormUrlEncoded
    @POST("candidate_resume.php?apicall=cnd_resume_upload")
    Call<Result> uploadPdf(
            @Field("jj_cnd_reg_id") String cndId,
            @Field("jj_pdf_name") String cndPdfName,
            @Field("jj_pdf_encoded") String cndPdf
    );

    @FormUrlEncoded
    @POST("candidate_resume.php?apicall=cnd_view_resume")
    Call<Result> viewPdf(
            @Field("jj_cnd_reg_id") String cndId
    );

    @GET("student_info_fetch.php?apicall=fetchAllPostedJobs")
    Call<List<CndHomeFrgValues>> getPoJoCndHomePage();

    @FormUrlEncoded
    @POST("cnd_Update_profile.php?apicall=fetchCndProfiles")
    Call<cndProfileResult> cndProfileFetch(
            @Field("cndRegId") String cmpRegisterId
    );

    @FormUrlEncoded
    @POST("Company/cmpApplicationFrg.php?apicall=deletePostedJob")
    Call<Result> cmpDeletePostedJobs(
            @Field("cmpPostJobId") String cmpPostJobId
    );

    //    @FormUrlEncoded
//    @POST("Company/cmp_payments.php?apicall=cmp_payments_operation")
//    Call<Result> userPaymentInfo(
//            @Field("cmp_reg_id") String cmpRegId,
//            @Field("cmp_payed_date") String payedDate,
//            @Field("cmp_pay_expire") String expireDate,
//            @Field("cmp_transection_id") String transactionId,
//            @Field("cmp_days") String cmpDays,
//            @Field("cmp_pay_mrp") int amount,
//            @Field("cmp_data_access") int cmpDataAccess,
//            @Field("cmp_daily_data_access") int cmpDailyDataAccess,
//            @Field("cmp_weekly_data_access") int cmpWeeklyDataAccess,
//            @Field("cmp_post_job_count") int cmpPostJobCount,
//            @Field("cmp_unlock_cnd_aplc_contact") int unloaCndAplcContact,
//            @Field("cmp_tenure_unlocking_day") int tenureUnloadingDate
//    );
    @FormUrlEncoded
    @POST("Company/cmp_payments.php?apicall=cmp_payments_operation")
    Call<Result> userPaymentInfo(
            @Field("cmp_reg_id") String cmpRegId,
            @Field("paid_by_email") String Email,
            @Field("cmp_payed_date") String payedDate,
            @Field("PlanStartDate") String StartingDate,
            @Field("cmp_pay_expire") String expireDate,
            @Field("cmp_transaction_id") String transactionId,
            @Field("cmp_days") String cmpDays,
            @Field("cmp_pay_mrp") double amount,
            @Field("posted_job") int JobPosts,
            @Field("application_unlocks") int Application_Unlocks,
            @Field("candidate_contact_unlocks") int CandidateContactUnlocks,
            @Field("company_unlocks") int CompanyUnlocks,
            @Field("validity") int Validity
    );


}
