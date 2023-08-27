package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CndEditProfileActivity extends AppCompatActivity implements
        education_dialog__.EducationDialogListener__, experience_dialog_new.expListener {

    private String cdGender, cdFullName, cdEmail, cdContact, cdAlterContact, cdState, cdCity, cdCurrentLocation, cdDateOfBirth, cdCommunication,
            cdJobProfileOne, cdJobDesignationOne, cdJobProfileTwo, cdJobDesignationTwo, cdQualificationStd, cdQualificationStream, cdQualiCollegeName, cdQualificationStartDate,
            cdQualificatoinEndDate, cdFresherInternExp, cdExpJobIndustry, cdExpJobRole, cdExpCompanyName, cdExpCurrentSalary, cdExpDesignation,
            cdExpStartDate, cdExpEndDate, cdLanguage, cdVehicle, cdLicence, cdDocuments, cdSkill, cdReference, resumeUrl, extensionStr;

    private TextView fullNameTv, emailTv, genderTv, contactTv, alterContactTv, stateTv, cityTv, currentLocationTv, dateOfBTv, jobProfileOneTv, jobDesignationOneTv, jobProfileTwoTv,
            jobDesignationTwoTv, qualiStreamTv, qualiStdTv, qualiCollegeTv, qualiStartDateTv, qualiEndDateTv,
            expFeresherOrNotTv, expCompanyTv, expCurrentSalaryTv, expIndustryTv, expRoleTv, expDesignationTv, expStartDateTv, expEndDateTv,
            communicationTv, languagesTv, vehicleTv, licenceTv, documentsTv, skillTv, referenceTv, resumeTv;

    private ImageView fullNameIv, emailIv, genderIv, dateOfBIv, contactIv, alterContactIv, stateIv, cityIv, curLocationIv, jobProfileOneIv, jobProfileTwoIv,
            qualificationIv, experienceIv, communicationIv, languagesIv, vehicleIv, licenceIv, documentsIv, skillIv, referenceIv, resumeIv;

    private LinearLayout fNameLL, emailLL, genderLL, dateOfBLL, contactLL, alterContactLL, stateLL, cityLL,
            cLocationLL, jProfileOneLL, jProfileTwoLL, qualiLL, commuLL;
    private AppCompatSpinner genderText, jDesignationOneText, jDesignationTwoText, commText;
    private TextView dateOfBText, stateText, cityText, jprofileOneText, jprofileTwoText, qualiText;
    private TextInputLayout fNameText, emailText, contactText, alterContactText, cLocationText,
            otherJobProfileOneText, otherJobProfileTwoText;
    private Button fNameDonBtn, emailDonBtn, genderDonBtn, dateOfBDonBtn, contactDonBtn, alterContactDonBtn,
            stateDonBtn, cityDonBtn, cLocationDonBtn, jProfileOneDonBtn, jProfileTwoBtn, qualiDonBtn, commDonBtn;

    //exp
    private LinearLayout expeLL;
    private AppCompatSpinner expSelectSp;
    private TextView expAddText;
    private Button expeDonBtn;
    //exp

    //languages
    private LinearLayout languagesLL;
    private CheckBox hindiCbEdit, marathiCbEdit, englishCbEdit, urduCbEdit, gujratiCbEdit;
    private TextInputLayout otherLanguagesEdit;
    private Button languagesDonBtn;
    //languages

    //vehicle
    private LinearLayout vehicleLL;
    private CheckBox bikeCbEdit, carCbEdit, scooterCbEdit, noCbEdit;
    private TextInputLayout otherVehicleEdit;
    private Button vehicleDonBtn;
    //vehicle

    //licence
    private LinearLayout licenceLL;
    private CheckBox twoW, fourW, transportW, heavyW, amvW, noW;
    private TextInputLayout otherLicenceEdit;
    private Button licenceDonBtn;
    //licence

    //documents
    private LinearLayout documentsLL;
    private CheckBox aadharDocEdit, panDocEdit, passportDocEdit, votingCardDocEdit, drivingLiDocEdit, bankDocEdit;
    private TextInputLayout otherDocumentEdit;
    private Button documentsDonBtn;
    //documents

    //skill
    private LinearLayout skillLL;
    private TextInputLayout skillText;
    private Button skillDonBtn;
    //skill

    //reference
    private LinearLayout referenceLL;
    private RadioGroup referenceRgrpEdit;
    private RadioButton referenceRbtnEdit;
    private boolean otherReferenceEdit = false;
    private TextInputLayout otherReferenceText;
    private Button referenceDonBtn;
    //reference

    //resume cv
    private LinearLayout resumeLL;
    private Button selectCvBtn, resumeDonBtn;
    private TextView cvNameText;
    private boolean selectingResume = false;
    private String excodedResumeUrlStr;
    public static final int PICKFILE_RESULT_CODE_EDIT = 7;
    //resume cv

    private Dialog dialog;
    private ArrayList<String> stateList, cityList, profileOneList, profileTwoList;

    private Button submitToDatabaseCndEdit;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnd_edit_profile);

        getDataFromInente();

        initOperation();

        setDataFromIntent();

        onclickOperation();

        submitToDatabaseCndEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToDatabaseCnd();
            }
        });
    }

    private void initOperation() {
        fullNameTv = findViewById(R.id.cnd_edit_profile_fname_et);
        emailTv = findViewById(R.id.cnd_edit_profile_email_et);
        genderTv = findViewById(R.id.cnd_edit_profile_gender_et);
        contactTv = findViewById(R.id.cnd_edit_profile_contact_et);
        alterContactTv = findViewById(R.id.cnd_edit_profile_alter_et);
        stateTv = findViewById(R.id.cnd_edit_profile_state_et);
        cityTv = findViewById(R.id.cnd_edit_profile_city_et);
        currentLocationTv = findViewById(R.id.cnd_edit_profile_current_location_et);
        dateOfBTv = findViewById(R.id.cnd_edit_profile_date_of_et);
        jobProfileOneTv = findViewById(R.id.cnd_edit_profile_profile_one_et);
        jobDesignationOneTv = findViewById(R.id.cnd_edit_profile_Designation_one_et);
        jobProfileTwoTv = findViewById(R.id.cnd_edit_profile_profile_two_et);
        jobDesignationTwoTv = findViewById(R.id.cnd_edit_profile_Designation_two_et);
        qualiStreamTv = findViewById(R.id.cnd_edit_profile_quali_stream_et);
        qualiStdTv = findViewById(R.id.cnd_edit_profile_quali_std);
        qualiCollegeTv = findViewById(R.id.cnd_edit_profile_quali_college_name_et);
        qualiStartDateTv = findViewById(R.id.cnd_edit_profile_quali_start_date_et);
        qualiEndDateTv = findViewById(R.id.cnd_edit_profile_quali_end_date_et);
        expFeresherOrNotTv = findViewById(R.id.cnd_edit_profile_fresher_or_not_et);
        expCompanyTv = findViewById(R.id.cnd_edit_profile_company_name_et);
        expCurrentSalaryTv = findViewById(R.id.cnd_edit_profile_current_salary_et);
        expIndustryTv = findViewById(R.id.cnd_edit_profile_industry_et);
        expRoleTv = findViewById(R.id.cnd_edit_profile_role_et);
        expDesignationTv = findViewById(R.id.cnd_edit_profile_designation_et);
        expStartDateTv = findViewById(R.id.cnd_edit_profile_exp_start_date_et);
        expEndDateTv = findViewById(R.id.cnd_edit_profile_exp_end_date_et);
        communicationTv = findViewById(R.id.cnd_edit_profile_communication_et);
        languagesTv = findViewById(R.id.cnd_edit_profile_languages_et);
        vehicleTv = findViewById(R.id.cnd_edit_profile_vehicle_et);
        licenceTv = findViewById(R.id.cnd_edit_profile_licence_et);
        documentsTv = findViewById(R.id.cnd_edit_profile_doc_et);
        skillTv = findViewById(R.id.cnd_edit_profile_skill_et);
        referenceTv = findViewById(R.id.cnd_edit_profile_reference_et);
        resumeTv = findViewById(R.id.cnd_edit_profile_resume_et);

        fullNameIv = findViewById(R.id.cnd_edit_profile_fname_edit);
        emailIv = findViewById(R.id.cnd_edit_profile_email_edit);
        genderIv = findViewById(R.id.cnd_edit_profile_gender_edit);
        dateOfBIv = findViewById(R.id.cnd_edit_profile_date_of_edit);
        contactIv = findViewById(R.id.cnd_edit_profile_contact_edit);
        alterContactIv = findViewById(R.id.cnd_edit_profile_alter_cont_edit);
        stateIv = findViewById(R.id.cnd_edit_profile_state_edit);
        cityIv = findViewById(R.id.cnd_edit_profile_city_edit);
        curLocationIv = findViewById(R.id.cnd_edit_profile_current_location_edit);
        jobProfileOneIv = findViewById(R.id.cnd_edit_profile_j_profile_one_edit);
        jobProfileTwoIv = findViewById(R.id.cnd_edit_profile_j_profile_two_edit);
        qualificationIv = findViewById(R.id.cnd_edit_profile_quali_edit);
        experienceIv = findViewById(R.id.cnd_edit_profile_experience_edit);
        communicationIv = findViewById(R.id.cnd_edit_profile_communication_edit);
        languagesIv = findViewById(R.id.cnd_edit_profile_languages_edit);
        vehicleIv = findViewById(R.id.cnd_edit_profile_vehicle_edit);
        licenceIv = findViewById(R.id.cnd_edit_profile_licence_edit);
        documentsIv = findViewById(R.id.cnd_edit_profile_doc_edit);
        skillIv = findViewById(R.id.cnd_edit_profile_skill_edit);
        referenceIv = findViewById(R.id.cnd_edit_profile_reference_edit);
        resumeIv = findViewById(R.id.cnd_edit_profile_resume_edit);

        fNameLL = findViewById(R.id.ll_cnd_edit_fname);
        fNameText = findViewById(R.id.f_name_text_layout);
        fNameDonBtn = findViewById(R.id.btn_fname_edit);

        emailLL = findViewById(R.id.ll_cnd_edit_email);
        emailText = findViewById(R.id.e_mail_text_layout);
        emailDonBtn = findViewById(R.id.btn_email_edit);

        genderLL = findViewById(R.id.ll_cnd_edit_gender);
        genderText = findViewById(R.id.gender_text_layout);
        genderDonBtn = findViewById(R.id.btn_gender_edit);

        dateOfBLL = findViewById(R.id.ll_cnd_edit_date_of_b);
        dateOfBText = findViewById(R.id.date_of_b_text_layout);
        dateOfBDonBtn = findViewById(R.id.btn_date_of_b_edit);

        contactLL = findViewById(R.id.ll_cnd_edit_contact);
        contactText = findViewById(R.id.contact_text_layout);
        contactDonBtn = findViewById(R.id.btn_contact_edit);

        alterContactLL = findViewById(R.id.ll_cnd_edit_alter_contact);
        alterContactText = findViewById(R.id.alter_contact_text_layout);
        alterContactDonBtn = findViewById(R.id.btn_alter_contact_edit);

        stateLL = findViewById(R.id.ll_cnd_edit_state);
        stateText = findViewById(R.id.state_text_layout);
        stateDonBtn = findViewById(R.id.btn_state_edit);

        cityLL = findViewById(R.id.ll_cnd_edit_city);
        cityText = findViewById(R.id.city_text_layout);
        cityDonBtn = findViewById(R.id.btn_city_edit);

        cLocationLL = findViewById(R.id.ll_cnd_edit_current_location);
        cLocationText = findViewById(R.id.c_location_text_layout);
        cLocationDonBtn = findViewById(R.id.btn_clocation_edit);

        jProfileOneLL = findViewById(R.id.ll_cnd_edit_job_profile_one);
        jprofileOneText = findViewById(R.id.job_profile_one_text_layout);
        otherJobProfileOneText = findViewById(R.id.other_profile_one_text_layout);
        jDesignationOneText = findViewById(R.id.job_designation_one_text_layout);
        jProfileOneDonBtn = findViewById(R.id.btn_job_profile_one_edit);

        jProfileTwoLL = findViewById(R.id.ll_cnd_edit_job_profile_two);
        jprofileTwoText = findViewById(R.id.job_profile_two_text_layout);
        otherJobProfileTwoText = findViewById(R.id.other_profile_two_text_layout);
        jDesignationTwoText = findViewById(R.id.job_designation_two_text_layout);
        jProfileTwoBtn = findViewById(R.id.btn_job_profile_two_edit);

        qualiLL = findViewById(R.id.ll_cnd_edit_quali);
        qualiText = findViewById(R.id.quali_text_layout);
        qualiDonBtn = findViewById(R.id.btn_quali_edit);

        //exp
        expeLL = findViewById(R.id.ll_cnd_edit_expe);
        expSelectSp = findViewById(R.id.expe_select_text_layout);
        expAddText = findViewById(R.id.expe_add_text_layout);
        expeDonBtn = findViewById(R.id.btn_expe_edit);
        //exp

        commuLL = findViewById(R.id.ll_cnd_edit_communication);
        commText = findViewById(R.id.communication_text_layout);
        commDonBtn = findViewById(R.id.btn_communication_edit);

        languagesLL = findViewById(R.id.ll_cnd_edit_languages);
        hindiCbEdit = findViewById(R.id.lang_hindi_edit);
        marathiCbEdit = findViewById(R.id.lang_marathi_edit);
        englishCbEdit = findViewById(R.id.lang_english_edit);
        urduCbEdit = findViewById(R.id.lang_urdu_edit);
        gujratiCbEdit = findViewById(R.id.lang_gujrati_edit);
        otherLanguagesEdit = findViewById(R.id.lang_other_edit);
        languagesDonBtn = findViewById(R.id.btn_languages_edit);

        vehicleLL = findViewById(R.id.ll_cnd_edit_vehicle);
        bikeCbEdit = findViewById(R.id.vehicle_bike_edit);
        carCbEdit = findViewById(R.id.vehicle_car_edit);
        scooterCbEdit = findViewById(R.id.vehicle_scooter_edit);
        noCbEdit = findViewById(R.id.vehicle_no_edit);
        otherVehicleEdit = findViewById(R.id.vehicle_other_edit);
        vehicleDonBtn = findViewById(R.id.btn_vehicle_edit);

        licenceLL = findViewById(R.id.ll_cnd_edit_licence);
        twoW = findViewById(R.id.lice_two_edit);
        fourW = findViewById(R.id.lice_four_edit);
        transportW = findViewById(R.id.lice_transport_edit);
        heavyW = findViewById(R.id.lice_heavy_edit);
        amvW = findViewById(R.id.lice_amv_edit);
        noW = findViewById(R.id.lice_no_edit);
        otherLicenceEdit = findViewById(R.id.licence_other_edit);
        licenceDonBtn = findViewById(R.id.btn_licence_edit);

        documentsLL = findViewById(R.id.ll_cnd_edit_documents);
        aadharDocEdit = findViewById(R.id.aadhar_doc_edit);
        panDocEdit = findViewById(R.id.pan_doc_edit);
        passportDocEdit = findViewById(R.id.passport_doc_edit);
        votingCardDocEdit = findViewById(R.id.voting_card_doc_edit);
        drivingLiDocEdit = findViewById(R.id.driving_licence_doc_edit);
        bankDocEdit = findViewById(R.id.bank_acc_doc_edit);
        otherDocumentEdit = findViewById(R.id.other_doc_edit);
        documentsDonBtn = findViewById(R.id.btn_documents_edit);

        skillLL = findViewById(R.id.ll_cnd_edit_skill);
        skillText = findViewById(R.id.skill_text_layout);
        skillDonBtn = findViewById(R.id.btn_skill_edit);

        referenceLL = findViewById(R.id.ll_cnd_edit_reference);
        referenceRgrpEdit = findViewById(R.id.reference_r_grp_edit);
        otherReferenceText = findViewById(R.id.reference_other_text_edit);
        referenceDonBtn = findViewById(R.id.btn_reference_edit);

        resumeLL = findViewById(R.id.ll_cnd_edit_resume);
        selectCvBtn = findViewById(R.id.select_cv_file_btn_edit);
        resumeDonBtn = findViewById(R.id.btn_resume_edit);
        cvNameText = findViewById(R.id.select_file_name_tv_edit);

        submitToDatabaseCndEdit = findViewById(R.id.candidate_submit_btn_edit);

    }

    private void updateToDatabaseCnd() {
        String fNameStr, emailStr, genderStr, dOfBirthStr, contactStr, alterContactStr, stateStr, cityStr,
                cLocationStr, jProfileOneStr, jDesignationOneStr, jProfileTwoStr, jDesignationTwoStr,
                qualiStreamStr, qualiStdStr, qualiCollegeStr, qualiStartStr, qualiEndStr,
                expFresharExpStr, expCompanyStr, expCurrentSalaryStr, expIndustryStr, expRoleStr, expDesignationStr, expStartStr, expEndStr,
                communiStr, languageStr, vehicleStr, licenceStr, documentStr, skillStr, referenceStr, resumeUrlStr,resumeExtensionStr;

        fNameStr = fullNameTv.getText().toString();
        emailStr = emailTv.getText().toString();
        genderStr = genderTv.getText().toString();
        dOfBirthStr = dateOfBTv.getText().toString();
        contactStr = contactTv.getText().toString();
        alterContactStr = alterContactTv.getText().toString();
        stateStr = stateTv.getText().toString();
        cityStr = cityTv.getText().toString();
        cLocationStr = currentLocationTv.getText().toString();
        jProfileOneStr = jobProfileOneTv.getText().toString();
        jDesignationOneStr = jobDesignationOneTv.getText().toString();
        jProfileTwoStr = jobProfileTwoTv.getText().toString();
        jDesignationTwoStr = jobDesignationTwoTv.getText().toString();
        qualiStreamStr = qualiStreamTv.getText().toString();
        qualiStdStr = qualiStdTv.getText().toString();
        qualiCollegeStr = qualiCollegeTv.getText().toString();
        qualiStartStr = qualiStartDateTv.getText().toString();
        qualiEndStr = qualiEndDateTv.getText().toString();
        expFresharExpStr = expFeresherOrNotTv.getText().toString();
        expCompanyStr = expCompanyTv.getText().toString();
        expCurrentSalaryStr = expCurrentSalaryTv.getText().toString();
        expIndustryStr = expIndustryTv.getText().toString();
        expRoleStr = expRoleTv.getText().toString();
        expDesignationStr = expDesignationTv.getText().toString();
        expStartStr = expStartDateTv.getText().toString();
        expEndStr = expEndDateTv.getText().toString();
        communiStr = communicationTv.getText().toString();
        languageStr = languagesTv.getText().toString();
        vehicleStr = vehicleTv.getText().toString();
        documentStr = documentsTv.getText().toString();
        skillStr = skillTv.getText().toString();
        referenceStr = referenceTv.getText().toString();

        if (licenceTv.getText().toString().isEmpty()) {
            licenceStr = "--";
        } else {
            licenceStr = "--";
        }

        if (selectingResume) {
            resumeUrlStr = excodedResumeUrlStr;
            resumeExtensionStr=extensionStr;
        } else {
            resumeUrlStr = "--";
            resumeExtensionStr="--";
        }

        if (resumeUrlStr.isEmpty()) {
            Toast.makeText(this, "Resume is not uploaded...!", Toast.LENGTH_SHORT).show();
            return;
        }

//        Toast.makeText(this, fNameStr+"\n"+emailStr+"\n"+genderStr+"\n"+dOfBirthStr+"\n"+contactStr+"\n"+alterContactStr+"\n"+stateStr+"\n"+
//                cLocationStr+"\n"+jProfileOneStr+"\n"+jDesignationOneStr+"\n"+jProfileTwoStr+"\n"+jDesignationTwoStr+"\n"+
//                qualiStreamStr+"\n"+qualiStdStr+"\n"+qualiCollegeStr+"\n"+qualiStartStr+"\n"+qualiEndStr+"\n"+
//                expFresharExpStr+"\n"+expCompanyStr+"\n"+expIndustryStr+"\n"+expRoleStr+"\n"+expDesignationStr+"\n"+expStartStr+"\n"+expEndStr+"\n"+
//                communiStr+"\n"+languageStr+"\n"+vehicleStr+"\n"+licenceStr+"\n"+skillStr+"\n"+referenceStr, Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, extensionStr, Toast.LENGTH_SHORT).show();

        sendToDatabaseOperation(fNameStr, emailStr, genderStr, dOfBirthStr, contactStr, alterContactStr, stateStr, cityStr,
                cLocationStr, jProfileOneStr, jDesignationOneStr, jProfileTwoStr, jDesignationTwoStr,
                qualiStreamStr, qualiStdStr, qualiCollegeStr, qualiStartStr, qualiEndStr,
                expFresharExpStr, expCompanyStr, expCurrentSalaryStr, expIndustryStr, expRoleStr, expDesignationStr, expStartStr, expEndStr,
                communiStr, languageStr, vehicleStr, licenceStr, documentStr, skillStr, referenceStr, resumeUrlStr, resumeExtensionStr);

    }

    private void sendToDatabaseOperation(String fNameStr, String emailStr, String genderStr, String dOfBirthStr,
                                         String contactStr, String alterContactStr, String stateStr, String cityStr,
                                         String cLocationStr, String jProfileOneStr, String jDesignationOneStr,
                                         String jProfileTwoStr, String jDesignationTwoStr, String qualiStreamStr,
                                         String qualiStdStr, String qualiCollegeStr, String qualiStartStr, String qualiEndStr,
                                         String expFresharExpStr, String expCompanyStr, String expCurrentSalaryStr, String expIndustryStr, String expRoleStr,
                                         String expDesignationStr, String expStartStr, String expEndStr, String communiStr,
                                         String languageStr, String vehicleStr, String licenceStr, String documentStr, String skillStr,
                                         String referenceStr, String resumeUrlStr, String extensionStr) {

        Dialog dialog = new Dialog(CndEditProfileActivity.this);
        dialog.setTitle("Data updating...");
        dialog.show();

        userId = SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call = api.cndEditProfile(userId, fNameStr, emailStr, genderStr, dOfBirthStr, contactStr,
                alterContactStr, cLocationStr, stateStr, cityStr, jProfileOneStr, jDesignationOneStr, jProfileTwoStr,
                jDesignationTwoStr, qualiStdStr, qualiStreamStr, qualiCollegeStr, qualiStartStr, qualiEndStr,
                expFresharExpStr, expIndustryStr, expRoleStr, expCompanyStr, expCurrentSalaryStr, expDesignationStr, expStartStr,
                expEndStr, communiStr, languageStr, vehicleStr, licenceStr, documentStr, skillStr, referenceStr, resumeUrlStr, extensionStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                try {

                    if (!response.body().getError()) {
                        if (selectingResume && (response.body().getFile_size() > 0)){
                            Toast.makeText(CndEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    } else {
                        Toast.makeText(CndEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CndEditProfileActivity.this, "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //progressDialog.dismiss();
                dialog.dismiss();
                Toast.makeText(CndEditProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onclickOperation() {

        fullNameIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fNameLL.setVisibility(View.VISIBLE);
            }
        });

        fNameOperation();

        emailIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailLL.setVisibility(View.VISIBLE);
            }
        });
        emailOperation();

        genderIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderLL.setVisibility(View.VISIBLE);
            }
        });
        genderOperation();

        dateOfBIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateOfBLL.setVisibility(View.VISIBLE);
            }
        });
        dateOfBirthOperation();

        contactIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactLL.setVisibility(View.VISIBLE);
            }
        });
        contactOperation();

        alterContactIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterContactLL.setVisibility(View.VISIBLE);
            }
        });
        alterContactOperation();

        stateIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateLL.setVisibility(View.VISIBLE);
            }
        });
        stateOperation();

        cityIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityLL.setVisibility(View.VISIBLE);
            }
        });
        cityOperation();

        curLocationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cLocationLL.setVisibility(View.VISIBLE);
            }
        });

        currentLocationOperation();

        jobProfileOneIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jProfileOneLL.setVisibility(View.VISIBLE);
            }
        });

        JobProfileOneOperation();

        jobProfileTwoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jProfileTwoLL.setVisibility(View.VISIBLE);
            }
        });
        JobProfileTwoOperation();

        qualificationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qualiLL.setVisibility(View.VISIBLE);
            }
        });
        qualificationOperation();

        //panding experience
        experienceIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expeLL.setVisibility(View.VISIBLE);
            }
        });
        experienceOperation();
        //experience

        communicationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commuLL.setVisibility(View.VISIBLE);
            }
        });
        communicationOperation();

        languagesIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languagesLL.setVisibility(View.VISIBLE);
            }
        });
        languagesOperation();

        vehicleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleLL.setVisibility(View.VISIBLE);
            }
        });
        vehicleOperations();

        licenceIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                licenceLL.setVisibility(View.VISIBLE);
            }
        });
        licenceOperation();

        documentsIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentsLL.setVisibility(View.VISIBLE);
            }
        });
        documentsOperation();

        skillIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillLL.setVisibility(View.VISIBLE);
            }
        });
        skillOperation();

        referenceIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceLL.setVisibility(View.VISIBLE);
            }
        });

        referenceOperation();

        resumeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeLL.setVisibility(View.VISIBLE);
            }
        });
        resumeOperation();

    }

    private void documentsOperation() {
        documentsDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> documentStrArr = new ArrayList<>();
                if (aadharDocEdit.isChecked())
                    documentStrArr.add(aadharDocEdit.getText().toString() + ",");
                else
                    documentStrArr.remove(aadharDocEdit.getText().toString() + ",");

                if (panDocEdit.isChecked())
                    documentStrArr.add(panDocEdit.getText().toString() + ",");
                else
                    documentStrArr.remove(panDocEdit.getText().toString() + ",");

                if (passportDocEdit.isChecked())
                    documentStrArr.add(passportDocEdit.getText().toString() + ",");
                else
                    documentStrArr.remove(passportDocEdit.getText().toString() + ",");

                if (votingCardDocEdit.isChecked())
                    documentStrArr.add(votingCardDocEdit.getText().toString() + ",");
                else
                    documentStrArr.remove(votingCardDocEdit.getText().toString() + ",");

                if (drivingLiDocEdit.isChecked())
                    documentStrArr.add(drivingLiDocEdit.getText().toString() + ",");
                else
                    documentStrArr.remove(drivingLiDocEdit.getText().toString() + ",");

                if (bankDocEdit.isChecked())
                    documentStrArr.add(bankDocEdit.getText().toString() + ",");
                else
                    documentStrArr.remove(bankDocEdit.getText().toString() + ",");

                if (!otherDocumentEdit.getEditText().getText().toString().equals(""))
                    documentStrArr.add(otherDocumentEdit.getEditText().getText().toString());
                else
                    documentStrArr.remove(otherDocumentEdit.getEditText().getText().toString());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    documentsTv.setText(String.join("", documentStrArr));
                }

                documentsLL.setVisibility(View.GONE);
            }
        });
    }

    private void resumeOperation() {
        String[] mimeTypes =
                {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/pdf"};
        selectCvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, PICKFILE_RESULT_CODE_EDIT);
//                startActivityForResult(Intent.createChooser(intent, "Select Resume/CV"), PICKFILE_RESULT_CODE_EDIT);
            }
        });
        resumeDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeLL.setVisibility(View.GONE);
            }
        });
    }

    private void referenceOperation() {
        referenceRgrpEdit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                referenceRbtnEdit = findViewById(checkedId);
                if (referenceRbtnEdit.getId() == R.id.reference_r_btn_other_edit) {
                    otherReferenceText.getEditText().addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            referenceTv.setText("");
                            referenceTv.setText(s);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } else {

                    referenceTv.setText(referenceRbtnEdit.getText().toString());

                }
            }
        });

        referenceDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceLL.setVisibility(View.GONE);
            }
        });
    }

    private void skillOperation() {
        skillText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                skillTv.setText("");
                skillTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skillDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillLL.setVisibility(View.GONE);
            }
        });
    }

    private void licenceOperation() {
        licenceDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> licenceStrArr = new ArrayList<>();
                if (twoW.isChecked())
                    licenceStrArr.add(twoW.getText().toString() + ",");
                else
                    licenceStrArr.remove(twoW.getText().toString() + ",");

                if (fourW.isChecked())
                    licenceStrArr.add(fourW.getText().toString() + ",");
                else
                    licenceStrArr.remove(fourW.getText().toString() + ",");

                if (transportW.isChecked())
                    licenceStrArr.add(transportW.getText().toString() + ",");
                else
                    licenceStrArr.remove(transportW.getText().toString() + ",");

                if (heavyW.isChecked())
                    licenceStrArr.add(heavyW.getText().toString() + ",");
                else
                    licenceStrArr.remove(heavyW.getText().toString() + ",");

                if (amvW.isChecked())
                    licenceStrArr.add(amvW.getText().toString() + ",");
                else
                    licenceStrArr.remove(amvW.getText().toString() + ",");

                if (noW.isChecked())
                    licenceStrArr.add(noW.getText().toString() + ",");
                else
                    licenceStrArr.remove(noW.getText().toString() + ",");

                if (!otherLicenceEdit.getEditText().getText().toString().equals(""))
                    licenceStrArr.add(otherLicenceEdit.getEditText().getText().toString());
                else
                    licenceStrArr.remove(otherLicenceEdit.getEditText().getText().toString());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vehicleTv.setText(String.join("", licenceStrArr));
                }

                licenceLL.setVisibility(View.GONE);
            }
        });
    }

    private void vehicleOperations() {
        vehicleDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> vehicleStrArr = new ArrayList<>();
                if (bikeCbEdit.isChecked())
                    vehicleStrArr.add(bikeCbEdit.getText().toString() + ",");
                else
                    vehicleStrArr.remove(bikeCbEdit.getText().toString() + ",");

                if (carCbEdit.isChecked())
                    vehicleStrArr.add(carCbEdit.getText().toString() + ",");
                else
                    vehicleStrArr.remove(carCbEdit.getText().toString() + ",");

                if (scooterCbEdit.isChecked())
                    vehicleStrArr.add(scooterCbEdit.getText().toString() + ",");
                else
                    vehicleStrArr.remove(scooterCbEdit.getText().toString() + ",");

                if (noCbEdit.isChecked())
                    vehicleStrArr.add(noCbEdit.getText().toString() + ",");
                else
                    vehicleStrArr.remove(noCbEdit.getText().toString() + ",");

                if (!otherVehicleEdit.getEditText().getText().toString().equals("")) {
                    vehicleStrArr.add(otherVehicleEdit.getEditText().getText().toString());
                } else {
                    vehicleStrArr.remove(otherVehicleEdit.getEditText().getText().toString());
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vehicleTv.setText(String.join("", vehicleStrArr));
                }

                vehicleLL.setVisibility(View.GONE);
            }
        });
    }

    private void languagesOperation() {
        languagesDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> languagesStrArr = new ArrayList<>();
                if (hindiCbEdit.isChecked())
                    languagesStrArr.add(hindiCbEdit.getText().toString() + ",");
                else
                    languagesStrArr.remove(hindiCbEdit.getText().toString() + ",");

                if (marathiCbEdit.isChecked())
                    languagesStrArr.add(marathiCbEdit.getText().toString() + ",");
                else
                    languagesStrArr.remove(marathiCbEdit.getText().toString() + ",");

                if (englishCbEdit.isChecked())
                    languagesStrArr.add(englishCbEdit.getText().toString() + ",");
                else
                    languagesStrArr.remove(englishCbEdit.getText().toString() + ",");

                if (urduCbEdit.isChecked())
                    languagesStrArr.add(urduCbEdit.getText().toString() + ",");
                else
                    languagesStrArr.remove(urduCbEdit.getText().toString() + ",");

                if (gujratiCbEdit.isChecked())
                    languagesStrArr.add(gujratiCbEdit.getText().toString() + ",");
                else
                    languagesStrArr.remove(gujratiCbEdit.getText().toString() + ",");

                if (!otherLanguagesEdit.getEditText().getText().toString().equals(""))
                    languagesStrArr.add(otherLanguagesEdit.getEditText().getText().toString());
                else
                    languagesStrArr.remove(otherLanguagesEdit.getEditText().getText().toString());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    languagesTv.setText(String.join("", languagesStrArr));
                }

                languagesLL.setVisibility(View.GONE);

            }
        });
    }

    private void communicationOperation() {
        commText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItemPosition() > 0) {
                    communicationTv.setText("");
                    communicationTv.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        commDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commuLL.setVisibility(View.GONE);
            }
        });
    }

    //experience
    private void experienceOperation() {

        expSelectSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItemPosition() == 1) {
                    expAddText.setVisibility(View.GONE);

                    expCompanyTv.setText("--");
                    expCurrentSalaryTv.setText("--");
                    expIndustryTv.setText("--");
                    expRoleTv.setText("--");
                    expDesignationTv.setText("--");
                    expStartDateTv.setText("--");
                    expEndDateTv.setText("--");
                } else if (parent.getSelectedItemPosition() > 1) {
                    expAddText.setVisibility(View.VISIBLE);
                } else {
                    expAddText.setVisibility(View.GONE);
                }
                expFeresherOrNotTv.setText(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        expAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experience_dialog_new exp_dialog_new = new experience_dialog_new();
                exp_dialog_new.show(getSupportFragmentManager(), "Experience");
            }
        });

        expeDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expeLL.setVisibility(View.GONE);
            }
        });
    }
    //experience

    private void qualificationOperation() {
        qualiText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                education_dialog__ dialog__ = new education_dialog__();
                dialog__.show(getSupportFragmentManager(), "Qualification");
            }
        });
        qualiDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qualiLL.setVisibility(View.GONE);
            }
        });
    }

    private void JobProfileTwoOperation() {
        profileTwoList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

        jprofileTwoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                dialog = new Dialog(CndEditProfileActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                EditText editText = dialog.findViewById(R.id.et_search_text);
                ListView listView = dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CndEditProfileActivity.this,
                                android.R.layout.simple_list_item_1, profileTwoList);
                listView.setAdapter(itemsAdapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        itemsAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        jprofileTwoText.setText(itemsAdapter.getItem(position));
                        dialog.dismiss();

                        if (itemsAdapter.getItem(position).equals("Other")) {
                            otherJobProfileTwoText.setVisibility(View.VISIBLE);
                        } else {
                            jobProfileTwoTv.setText(itemsAdapter.getItem(position));
                            otherJobProfileTwoText.setVisibility(View.GONE);
                        }
                    }
                });
                otherJobProfileTwoText.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        jobProfileTwoTv.setText("");
                        jobProfileTwoTv.setText(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        jDesignationTwoText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItemPosition() > 0) {
                    jobDesignationTwoTv.setText("");
                    jobDesignationTwoTv.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jProfileTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jProfileTwoLL.setVisibility(View.GONE);
            }
        });
    }

    private void JobProfileOneOperation() {

        profileOneList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

        jprofileOneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                dialog = new Dialog(CndEditProfileActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                EditText editText = dialog.findViewById(R.id.et_search_text);
                ListView listView = dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(CndEditProfileActivity.this,
                        android.R.layout.simple_list_item_1, profileOneList);

                listView.setAdapter(itemsAdapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        itemsAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        jprofileOneText.setText(itemsAdapter.getItem(position));
                        dialog.dismiss();

                        /*if (itemsAdapter.getItem(position).equals("Job Profile One")) {

                        } else if (itemsAdapter.getItem(position).equals("Select Job Profile")) {

                        } else */
                        if (itemsAdapter.getItem(position).equals("Other")) {
                            otherJobProfileOneText.setVisibility(View.VISIBLE);
                        } else {
                            jobProfileOneTv.setText(itemsAdapter.getItem(position));
                            otherJobProfileOneText.setVisibility(View.GONE);
                        }

                    }
                });
                otherJobProfileOneText.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        jobProfileOneTv.setText("");
                        jobProfileOneTv.setText(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        jDesignationOneText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItemPosition() > 0) {
                    jobDesignationOneTv.setText("");
                    jobDesignationOneTv.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jProfileOneDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jProfileOneLL.setVisibility(View.GONE);
            }
        });
    }

    private void currentLocationOperation() {
        cLocationText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentLocationTv.setText("");
                currentLocationTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cLocationDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cLocationLL.setVisibility(View.GONE);
            }
        });
    }

    private void cityOperation() {

        cityList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.indian_cities)));

        cityText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                dialog = new Dialog(CndEditProfileActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                EditText editText = dialog.findViewById(R.id.et_search_text);
                ListView listView = dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CndEditProfileActivity.this,
                                android.R.layout.simple_list_item_1, cityList);
                listView.setAdapter(itemsAdapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        itemsAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (!itemsAdapter.getItem(position).equals("Select City")) {
                            cityText.setText(itemsAdapter.getItem(position));
                            cityTv.setText(itemsAdapter.getItem(position));
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        cityDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityLL.setVisibility(View.GONE);
            }
        });
    }

    private void stateOperation() {

        stateList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.india_states)));

        stateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);
                dialog = new Dialog(CndEditProfileActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                EditText editText = dialog.findViewById(R.id.et_search_text);
                ListView listView = dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CndEditProfileActivity.this,
                                android.R.layout.simple_list_item_1, stateList);
                listView.setAdapter(itemsAdapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        itemsAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (!itemsAdapter.getItem(position).equals("Select State")) {
                            stateText.setText(itemsAdapter.getItem(position));
                            stateTv.setText(itemsAdapter.getItem(position));
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        stateDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateLL.setVisibility(View.GONE);
            }
        });
    }

    private void alterContactOperation() {
        alterContactText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alterContactTv.setText("");
                alterContactTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        alterContactDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterContactLL.setVisibility(View.GONE);
            }
        });
    }

    private void contactOperation() {
        contactText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contactTv.setText("");
                contactTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        contactDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactLL.setVisibility(View.GONE);
            }
        });
    }

    private void dateOfBirthOperation() {
        dateOfBText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int yearInt = calendar.get(Calendar.YEAR);
                final int monthInt = calendar.get(Calendar.MONTH);
                final int dayInt = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CndEditProfileActivity.this,
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                                dateOfBText.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                                dateOfBTv.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                            }
                        }, yearInt, monthInt, dayInt);
                datePickerDialog.show();
            }
        });
        dateOfBDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateOfBLL.setVisibility(View.GONE);
            }
        });
    }

    private void genderOperation() {
        genderText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderTv.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        genderDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderLL.setVisibility(View.GONE);
            }
        });
    }

    private void emailOperation() {
        emailText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailTv.setText("");
                emailTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        emailDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailLL.setVisibility(View.GONE);
            }
        });
    }

    private void fNameOperation() {
        fNameText.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fullNameTv.setText("");
                fullNameTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fNameDonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fNameLL.setVisibility(View.GONE);
            }
        });
    }

    private void setDataFromIntent() {

        fullNameTv.setText(cdFullName);
        emailTv.setText(cdEmail);
        genderTv.setText(cdGender);
        contactTv.setText(cdContact);
        alterContactTv.setText(cdAlterContact);
        stateTv.setText(cdState);
        cityTv.setText(cdCity);
        currentLocationTv.setText(cdCurrentLocation);
        dateOfBTv.setText(cdDateOfBirth);
        jobProfileOneTv.setText(cdJobProfileOne);
        jobDesignationOneTv.setText(cdJobDesignationOne);
        jobProfileTwoTv.setText(cdJobProfileTwo);
        jobDesignationTwoTv.setText(cdJobDesignationTwo);
        qualiStreamTv.setText(cdQualificationStream);
        qualiStdTv.setText(cdQualificationStd);
        qualiCollegeTv.setText(cdQualiCollegeName);
        qualiStartDateTv.setText(cdQualificationStartDate);
        qualiEndDateTv.setText(cdQualificatoinEndDate);
        expFeresherOrNotTv.setText(cdFresherInternExp);
        expCompanyTv.setText(cdExpCompanyName);
        expCurrentSalaryTv.setText(cdExpCurrentSalary);
        expIndustryTv.setText(cdExpJobIndustry);
        expRoleTv.setText(cdExpJobRole);
        expDesignationTv.setText(cdExpDesignation);
        expStartDateTv.setText(cdExpStartDate);
        expEndDateTv.setText(cdExpEndDate);
        communicationTv.setText(cdCommunication);
        languagesTv.setText(cdLanguage);
        vehicleTv.setText(cdVehicle);
        licenceTv.setText(cdLicence);
        documentsTv.setText(cdDocuments);
        skillTv.setText(cdSkill);
        referenceTv.setText(cdReference);
//        if (resumeUrl.equals("--")) {
            resumeTv.setText("--");
//        } else {
//            resumeTv.setText(resumeUrl.substring(resumeUrl.lastIndexOf('/') + 1));
//        }
    }


    private void getDataFromInente() {

        Bundle bundle = getIntent().getExtras();
        cdGender = bundle.getString("cdGender");
        cdFullName = bundle.getString("cdFullName");
        cdEmail = bundle.getString("cdEmail");
        cdContact = bundle.getString("cdContact");
        cdAlterContact = bundle.getString("cdAlterContact");
        cdState = bundle.getString("cdState");
        cdCity = bundle.getString("cdCity");
        cdCurrentLocation = bundle.getString("cdCurrentLocation");
        cdDateOfBirth = bundle.getString("cdDateOfBirth");
        cdCommunication = bundle.getString("cdCommunication");
        cdJobProfileOne = bundle.getString("cdJobProfileOne");
        cdJobDesignationOne = bundle.getString("cdJobDesignationOne");
        cdJobProfileTwo = bundle.getString("cdJobProfileTwo");
        cdJobDesignationTwo = bundle.getString("cdJobDesignationTwo");
        cdQualificationStd = bundle.getString("cdQualificationStd");
        cdQualificationStream = bundle.getString("cdQualificationStream");
        cdQualiCollegeName = bundle.getString("cdCollegeName");
        cdQualificationStartDate = bundle.getString("cdQualificationStartDate");
        cdQualificatoinEndDate = bundle.getString("cdQualificatoinEndDate");
        cdFresherInternExp = bundle.getString("cdFresherInternExp");
        cdExpJobIndustry = bundle.getString("cdExpJobIndustry");
        cdExpJobRole = bundle.getString("cdExpJobRole");
        cdExpCompanyName = bundle.getString("cdExpCompanyName");
        cdExpCurrentSalary = bundle.getString("cdExpCurrentSalary");
        cdExpDesignation = bundle.getString("cdExpDesignation");
        cdExpStartDate = bundle.getString("cdExpStartDate");
        cdExpEndDate = bundle.getString("cdExpEndDate");
        cdLanguage = bundle.getString("cdLanguage");
        cdVehicle = bundle.getString("cdVehicle");
        cdLicence = bundle.getString("cdLicence");
        cdDocuments = bundle.getString("cdDocuments");
        cdSkill = bundle.getString("cdSkill");
        cdReference = bundle.getString("cdReference");
        resumeUrl = bundle.getString("cdResumeUrl");
        extensionStr = bundle.getString("extensionStr");

    }

    @Override
    public void applyTexts(String qualiFirstStr, String qualiSecontStr, String qualiCollege, String qualiStartDate, String qualiEndDate) {
        qualiStdTv.setText(qualiFirstStr);
        qualiStreamTv.setText(qualiSecontStr);
        qualiCollegeTv.setText(qualiCollege);
        qualiStartDateTv.setText(qualiStartDate);
        qualiEndDateTv.setText(qualiEndDate);
    }

    @Override
    public void experienceData(String expCmpNameStr, String expCurrentSalaryStr, String expIndustryStr, String expRoleStr, String expDesignationStr, String expStartDateStr, String expEndDateStr) {
        expCompanyTv.setText(expCmpNameStr);
        expCurrentSalaryTv.setText(expCurrentSalaryStr);
        expIndustryTv.setText(expIndustryStr);
        expRoleTv.setText(expRoleStr);
        expDesignationTv.setText(expDesignationStr);
        expStartDateTv.setText(expStartDateStr);
        expEndDateTv.setText(expEndDateStr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKFILE_RESULT_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();

            try {
                InputStream inputStream = CndEditProfileActivity.this.getContentResolver().openInputStream(path);
                byte[] pdfInByte = new byte[inputStream.available()];
                inputStream.read(pdfInByte);

                excodedResumeUrlStr = Base64.encodeToString(pdfInByte, Base64.DEFAULT);
                selectingResume = true;

            } catch (IOException e) {
                e.printStackTrace();
            }

            String file_name = getFileName(path);

            int dotposition = file_name.lastIndexOf(".");
            extensionStr = file_name.substring(dotposition + 1, file_name.length());

            resumeTv.setText(file_name);
            cvNameText.setText(file_name);
        }
    }

    private String getFileName(Uri uri) throws IllegalArgumentException {
        // Obtain a cursor with information regarding this uri
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            throw new IllegalArgumentException("Can't obtain file name, cursor is empty");
        }

        cursor.moveToFirst();

        String fileName = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));

        cursor.close();

        return fileName;
    }
}