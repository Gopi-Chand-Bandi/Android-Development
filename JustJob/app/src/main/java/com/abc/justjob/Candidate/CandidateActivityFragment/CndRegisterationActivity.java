package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatSpinner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.ConnectivityManager;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Company.CompanyActivitys.CompanyActivity;
import com.abc.justjob.Company.CompanyPostJob.PostJobActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class CndRegisterationActivity extends AppCompatActivity implements
//        education_dialog__.EducationDialogListener__,experience_dialog_new.expListener{
public class CndRegisterationActivity extends AppCompatActivity{

    //private static final int REQUEST_CAMERA_PERMISSION = 200;


    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    private String isRegisterationForm="";

    private TextView jobProfileOneEP,jobProfileTwoEP,stateSpEP,citySpEp,fresherProfileOneEP,fresherjobDesignationOneEp,fresherjobDesignationTwoEp,fresherProfileTwoEP,expProfileOneEP,expjobDesignationOneEp,expjobDesignationTwoEp,expProfileTwoEP;//spinner popup
    private int profileOnePosition,profileTwoPosition,statePosition;
    ArrayList<String> profileOneList,profileTwoList,stateList,cityList,genderlist,communicationlist,Experiencelist,jobdesignlist,industrylist,salarytypelist,qualificationlist,belowsscclasslist,sscboardlist,hscboardlist,hscstreamlist,higherstreamlist;
    Dialog dialog;
    private TextInputLayout otherProfileOneEt,otherProfileTwoEt;
    public static final int PICK_IMAGE = 1;
    public static final int PICKFILE_RESULT_CODE=2;
    private String encodedPdf;
    private String cmpResumeExtension;
    //private CircularImageView profileImage;
    private TextInputEditText nameEP,emailEP,birthDateEP,contactEP,alterContactEP,addressEP,skillEP,expStartDateEt,expEndDateEt,qualificationstartdate,qualificationenddate;
    private TextView addExperience;
    //private AppCompatSpinner genderSpEP,jobDesignationOneEp,jobDesignationTwoEp,qlfExpEP,experienceSpEP,communicationSpEP;
    private AppCompatSpinner jobDesignationOneEp,jobDesignationTwoEp,qlfExpEP;
    private AutoCompleteTextView genderSpEP,communicationSpEP,experienceSpEP,Salarytype,qualificationSpEP,below_ssc_class,ssc_board,hsc_stream,hsc_board,higher_stream;
    //private Spinner genderSpEP;
    private CheckBox lngHindiEP,lngMarathiEP,lngEnglishEP,lngUrduEP,lngGujratiEP;
    private EditText lngOtherEP;
    private CheckBox vhcBikeEP,vhcCarEP,vhcScooterEP,vhcNoEP;
    private CheckBox empfulltime,empparttime,empfreelancer;
    private CheckBox loconsite,lochybrid,locremote;
    private EditText belowssc_school,ssc_school,hsc_school,collegename;
    private EditText vhcOtherEP;
    private CheckBox licTwoEP,licFourEP,licTransportEP,licHeavyEP,licAmvEP,licNoEP;
    private EditText licOtherEP;
    private CheckBox docAadharEP,docPanEP,docPassportEp,docVotingCardEp,docDrivingLicenceEp,docBankEp;
    private EditText docOtherEP,expCompanyEt,referenceEP,OthercitySpEP,fresherotherProfileOneEt,fresherotherProfileTwoEt,expIndustryOther,expotherProfileOneEt,expotherProfileTwoEt,expCurrentSalaryEt;
    //private TextInputLayout referenceEP;
    private Button selectFileBtn;
    private boolean resumeIsSelected=false;
    private TextView selectedFileName;
    private Button submitBtn;
    private CheckBox jobstatus,qualificationstatus;
    private int selectedReferenceId;
    private RadioGroup referenceRgrp;
    private RadioButton referenceRbtn;
    private boolean otherReference=false;

  /*  private ImageView cover;
    private FloatingActionButton fab;*/

    private String userId;

    private boolean startOrEndEdt = true;

    private TextView qualiStreamEt,qualiStdEt,qualiCollegeNameEt,qualiStartDateEt,qualiEndDateEt;
    private TextView expIndustryEt,expRoleEt,expDesignationEt;

    private String stateName="",qualificationSt="",experienceSt="",genderSt="",SalarySt="";
    private String licence="",lng="",doc="",vehi="",loctype="",emptype="";

    private TextView quali_add_tv;
    private LinearLayout quali_layout,exp_layout,fresher_layout,experience_layout,below_ssc,ssc_passed,hsc_passed,ug,qualifyextra;

    private String expFresherInternExpStr;

    private String nameSprefStr,emailSPreStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnd_registeration);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
//                REQUEST_CAMERA_PERMISSION);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

//        Date today = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
//        dateToStr = format.format(today);

        userId= SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        nameSprefStr=SharedPrefManager.getInstance(getApplicationContext()).getUserName(getApplicationContext());
        emailSPreStr=SharedPrefManager.getInstance(getApplicationContext()).getUserEmail(getApplicationContext());

        initiat();

        nameEP.setText(nameSprefStr);
        emailEP.setText(emailSPreStr);

        selectFile();

        referenceRgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.reference_r_btn_other) {
                    otherReference=true;
                    referenceEP.setVisibility(View.VISIBLE);
                }else{
                    otherReference=false;
                    selectedReferenceId=checkedId;
                    referenceEP.setVisibility(View.GONE);
                    referenceEP.setText("");
                    referenceEP.setHint("Other reference");
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonAction();
            }
        });

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case REQUEST_CAMERA_PERMISSION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // Permission granted, you can proceed to access the camera
//                    // Open the camera or perform any camera-related operations here
//                } else {
//                    // Permission denied, handle the scenario gracefully (e.g., show a message or disable camera functionality)
//                }
//                break;
//        }
//    }



    @SuppressLint("CutPasteId")
    private void initiat() {

        Toolbar toolbar = findViewById(R.id.crToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");

//        cover = findViewById(R.id.Profile_photo);
//        fab = findViewById(R.id.floatingActionButton);

        nameEP=findViewById(R.id.name_Et);
        emailEP=findViewById(R.id.email_Et);
        genderSpEP=findViewById(R.id.gender_Et_spinner);
        birthDateEP=findViewById(R.id.birth_date_Et);
        contactEP=findViewById(R.id.contact_number_Et);
        alterContactEP=findViewById(R.id.alternate_number_Et);
        addressEP=findViewById(R.id.address_Et);
        stateSpEP=findViewById(R.id.state_spinner_sp);
        citySpEp=findViewById(R.id.city_spinner_sp);
        OthercitySpEP=findViewById(R.id.other_city);


        //Experience Part
        experienceSpEP=findViewById(R.id.experience_spinner_Et);
        fresher_layout=findViewById(R.id.fresher_layout);
        experience_layout=findViewById(R.id.experience_linear_layout);

        //Fresher employeement references
        fresherProfileOneEP=findViewById(R.id.fresher_profile_one_Et);
        fresherotherProfileOneEt=findViewById(R.id.fresher_reg_other_profile_one);
        fresherjobDesignationOneEp=findViewById(R.id.fresher_designation_one_Et);
        fresherProfileTwoEP=findViewById(R.id.fresher_profile_two_Et);
        fresherotherProfileTwoEt=findViewById(R.id.fresher_reg_other_profile_two);
        fresherjobDesignationTwoEp=findViewById(R.id.fresher_designation_two_Et);

        //Experienced employeement details
        expProfileOneEP=findViewById(R.id.exp_profile_one_Et);
        expotherProfileOneEt=findViewById(R.id.exp_reg_other_profile_one);
        expjobDesignationOneEp=findViewById(R.id.exp_designation_one_Et);
        expProfileTwoEP=findViewById(R.id.exp_profile_two_Et);
        expotherProfileTwoEt=findViewById(R.id.exp_reg_other_profile_two);
        expjobDesignationTwoEp=findViewById(R.id.exp_designation_two_Et);
        expIndustryEt=findViewById(R.id.edit_exp_industry_et);
        expIndustryOther=findViewById(R.id.edit_exp_industry_other);
        expCompanyEt=findViewById(R.id.edit_exp_company_et);
        //Salarytype=findViewById(R.id.salary_type);
        jobstatus=findViewById(R.id.checkbox_job);
        expCurrentSalaryEt=findViewById(R.id.edit_exp_current_salary_et);
        expStartDateEt=findViewById(R.id.edit_exp_start_date_et);
        expEndDateEt=findViewById(R.id.edit_exp_end_date_et);


        //Qualification_layouts
        below_ssc=findViewById(R.id.below_SSC_layout);
        ssc_passed=findViewById(R.id.SSC_passed_layout);
        hsc_passed=findViewById(R.id.HSC_passed_layout);
        ug=findViewById(R.id.UG_layout);
        qualifyextra=findViewById(R.id.qualifyextra_layout);


        //Qualification Part

        qualificationSpEP=findViewById(R.id.qualification_spinner_Et);
        below_ssc_class=findViewById(R.id.below_SSC_stream);
        ssc_board=findViewById(R.id.SSC_Board);
        hsc_stream=findViewById(R.id.HSC_Stream);
        //hsc_board=findViewById(R.id.HSC_Board);
        higher_stream=findViewById(R.id.UG_Stream);
        belowssc_school=findViewById(R.id.below_SSC_School);
        ssc_school=findViewById(R.id.SSC_School);
        hsc_school=findViewById(R.id.HSC_School);
        collegename=findViewById(R.id.UG_name);
        qualificationstatus=findViewById(R.id.checkbox_qualification);
        qualificationstartdate=findViewById(R.id.edit_quali_start_date_et);
        qualificationenddate=findViewById(R.id.edit_quali_end_date_et);






        quali_layout=findViewById(R.id.qualification_layout);
        //qualiStreamEt=findViewById(R.id.quali_stream_tv);
        //qualiStdEt=findViewById(R.id.quali_std_tv);
        //qualiCollegeNameEt=findViewById(R.id.quali_college_tv);
        //qualiStartDateEt=findViewById(R.id.quali_start_date_tv);
        //qualiEndDateEt=findViewById(R.id.quali_end_date_tv);
        //
        //quali_add_tv=findViewById(R.id.quali_add_tv_new);
        //
        //Experienced employee details

        //expRoleEt=findViewById(R.id.edit_exp_role_et);

        //expDesignationEt=findViewById(R.id.edit_exp_designation_et);

        //

        //exp_layout=findViewById(R.id.experience_linear_layout);

        //
        communicationSpEP=findViewById(R.id.communication_spinner_Et);

        //Employment Type
        empfulltime=findViewById(R.id.emp_full_Et);
        empparttime=findViewById(R.id.emp_part_Et);
        empfreelancer=findViewById(R.id.emp_free_Et);

        //Location Type
        loconsite=findViewById(R.id.emp_onsite_Et);
        lochybrid=findViewById(R.id.emp_hybrid_Et);
        locremote=findViewById(R.id.emp_remote_Et);

        lngHindiEP=findViewById(R.id.lang_hindi_Et);
        lngMarathiEP=findViewById(R.id.lang_marathi_Et);
        lngEnglishEP=findViewById(R.id.lang_english_Et);
        lngUrduEP=findViewById(R.id.lang_urdu_Et);
        lngGujratiEP=findViewById(R.id.lang_gujrati_Et);
        lngOtherEP=findViewById(R.id.lang_other_Et);

        vhcBikeEP=findViewById(R.id.vehicle_bike_Et);
        vhcCarEP=findViewById(R.id.vehicle_car_Et);
        vhcScooterEP=findViewById(R.id.vehicle_scooter_Et);
        vhcNoEP=findViewById(R.id.vehicle_no_Et);
        vhcOtherEP=findViewById(R.id.vehicle_other_Et);

        licTwoEP=findViewById(R.id.lice_two_Et);
        licFourEP=findViewById(R.id.lice_four_Et);
        licTransportEP=findViewById(R.id.lice_transport_Et);
        licHeavyEP=findViewById(R.id.lice_heavy_Et);
        licAmvEP=findViewById(R.id.lice_amv_Et);
        licNoEP=findViewById(R.id.lice_no_Et);
        licOtherEP=findViewById(R.id.lice_other_Et);

        docAadharEP=findViewById(R.id.aadhar_doc_Et);
        docPanEP=findViewById(R.id.pan_doc_Et);
        docPassportEp=findViewById(R.id.passport_dl_doc_Et);
        docVotingCardEp=findViewById(R.id.voting_card_dl_doc_Et);
        docDrivingLicenceEp=findViewById(R.id.driving_li_doc_Et);
        docBankEp=findViewById(R.id.bank_acc_doc_Et);
        docOtherEP=findViewById(R.id.other_doc_Et);

        skillEP=findViewById(R.id.skill_et);
        referenceRgrp=findViewById(R.id.reference_r_grp);

        referenceEP=findViewById(R.id.reference_Et);

        selectFileBtn=findViewById(R.id.select_file_btn);
        selectedFileName=findViewById(R.id.select_file_tv);

        submitBtn=findViewById(R.id.candidate_submit_btn);

    }

    private void selectFile() {
       /* profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });*/

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImagePicker.with(CndRegisterationActivity.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                        .start();
//            }
//        });

        birthDateEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int yearInt=calendar.get(Calendar.YEAR);
                final int monthInt=calendar.get(Calendar.MONTH);
                final int dayInt=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepickerdialog = new DatePickerDialog(CndRegisterationActivity.this,
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                                birthDateEP.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                                birthDateEP.setError(null);
                            }
                        },yearInt,monthInt,dayInt);
                datepickerdialog.show();
            }
        });

        selectFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] mimeTypes =
                        {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                                "application/pdf"};
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, PICKFILE_RESULT_CODE);


                /*Intent intent = new Intent();
                intent.setType("application/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Resume"), PICKFILE_RESULT_CODE);*/
            }
        });

        genderlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.gender)));
        ArrayAdapter<String> gender_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_dropdown_item_1line,genderlist );
        genderSpEP.setAdapter(gender_adapter);
        genderSpEP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderSt="";
                genderSt=genderSt+genderSpEP.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CndRegisterationActivity.this, "Select a Gender", Toast.LENGTH_SHORT).show();
            }
        });

        stateList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.india_states)));

        stateSpEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CndRegisterationActivity.this,
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
                            statePosition=position;
                            stateSpEP.setText(itemsAdapter.getItem(position));
                            if(!citySpEp.getText().toString().equals("")){
                                citySpEp.setText("");
                                citySpEp.setHint("Select a City");
                                OthercitySpEP.setText("");
                                OthercitySpEP.setHint("Ex:Other City");
                                OthercitySpEP.setVisibility(View.GONE);
                                Toast.makeText(CndRegisterationActivity.this,"Select the city",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });


        //cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.indian_cities)));

        citySpEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);

                String s=stateSpEP.getText().toString();

                switch (s){
                    case "Andaman and Nicobar Islands":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Andaman_Nicobar)));
                        break;
                    case "Andhra Pradesh":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Andhra_cities)));
                        break;
                    case "Arunachal Pradesh":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Arunachal_cities)));
                        break;
                    case "Assam":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Assam_cities)));
                        break;
                    case "Bihar":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Bihar_cities)));
                        break;
                    case "Chandigarh":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Chandigarh_Union_Territory)));
                        break;
                    case "Chattisgarh":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Chhattisgarh_cities)));
                        break;
                    case "Dadra and Nagar Haveli":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Dadra_cities)));
                        break;
                    case "Daman and Diu":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Daman_cities)));
                        break;
                    case "Delhi":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Delhi_cities)));
                        break;
                    case "Goa":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Goa_cities)));
                        break;
                    case "Gujarat":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Gujarat_cities)));
                        break;
                    case "Haryana":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Haryana)));
                        break;
                    case "Himachal Pradesh":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Himachal_Pradesh_cities)));
                        break;
                    case "Jammu and Kashmir":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Jammu_and_Kashmir_cities)));
                        break;
                    case "Jharkhand":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Jharkhand_cities)));
                        break;
                    case "Karnataka":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Karnataka_cities)));
                        break;
                    case "Kerala":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Kerala_cities)));
                        break;
                    case "Lakshadweep":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Lakshadweep_cities)));
                        break;
                    case "Madhya Pradesh":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Madhya_Pradesh_cities)));
                        break;
                    case "Maharashtra":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Maharashtra_cities)));
                        break;
                    case "Manipur":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Manipur_cities)));
                        break;
                    case "Meghalaya":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Meghalaya_cities)));
                        break;
                    case "Mizoram":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Mizoram_cities)));
                        break;
                    case "Nagaland":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Nagaland_cities)));
                        break;
                    case "Orissa":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Odisha_cities)));
                        break;
                    case "Pondicherry":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Puducherry_cities)));
                        break;
                    case "Punjab":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Punjab_cities)));
                        break;
                    case "Rajasthan":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Rajasthan_cities)));
                        break;
                    case "Sikkim":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Sikkim_cities)));
                        break;
                    case "Tamil Nadu":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Tamil_Nadu_cities)));
                        break;
                    case "Telangana":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Telangana_cities)));
                        break;
                    case "Tripura":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Tripura_cities)));
                        break;
                    case "Uttaranchal":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Uttarakhand_cities)));
                        break;
                    case "Uttar Pradesh":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.Uttar_Pradesh_cities)));
                        break;
                    case "West Bengal":
                        cityList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.West_Bengal_cities)));
                        break;
                    default:
                        Toast.makeText(CndRegisterationActivity.this,"Select the state first",Toast.LENGTH_SHORT).show();
                        return;
                }

                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CndRegisterationActivity.this,
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
                            citySpEp.setText(itemsAdapter.getItem(position));
                            if (citySpEp.getText().toString().equals("Other")){
                                OthercitySpEP.setVisibility(View.VISIBLE);
                                OthercitySpEP.setFocusableInTouchMode(true);
                                OthercitySpEP.setFocusable(true);
                                OthercitySpEP.setEnabled(true);
                                OthercitySpEP.setCursorVisible(true);
                            }
                            else{
                                OthercitySpEP.setText("");
                                OthercitySpEP.setHint("Ex: Other City");
                                OthercitySpEP.setVisibility(View.GONE);
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });


        fresherProfileOneEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                profileOneList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

                final List<String> filteredList = new ArrayList<>(profileOneList);

                ArrayAdapter<String> jobprofilelist = new ArrayAdapter<String>(CndRegisterationActivity.this, android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(jobprofilelist);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        filteredList.clear();
                        for (String item : profileOneList) {
                            if (item.toLowerCase().contains(s.toString().toLowerCase()) || item.equals("Other")) {
                                filteredList.add(item);
                            }
                        }
                        jobprofilelist.notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!jobprofilelist.getItem(position).equals("Select Job Profile")) {
                            fresherProfileOneEP.setText(jobprofilelist.getItem(position));
                            if (fresherProfileOneEP.getText().toString().equals("Other")){
                                fresherotherProfileOneEt.setVisibility(View.VISIBLE);
                                fresherotherProfileOneEt.setFocusableInTouchMode(true);
                                fresherotherProfileOneEt.setFocusable(true);
                                fresherotherProfileOneEt.setEnabled(true);
                                fresherotherProfileOneEt.setCursorVisible(true);
                            }
                            else{
                                fresherotherProfileOneEt.setText("");
                                fresherotherProfileOneEt.setVisibility(View.GONE);
                            }
                            if (!fresherjobDesignationOneEp.getText().toString().isEmpty()){
                                fresherjobDesignationOneEp.setText("");
                                Toast.makeText(CndRegisterationActivity.this,"Select the Role Designation",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        fresherjobDesignationOneEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.40);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                jobdesignlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.fresher_designation)));
                ArrayAdapter<String> job_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_list_item_1,jobdesignlist );
                listView.setAdapter(job_adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        job_adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!job_adapter.getItem(position).equals("Select Role Designation")) {
                            fresherjobDesignationOneEp.setText(job_adapter.getItem(position));
                            dialog.dismiss();
                        }
                    }
                });
            }
        });


        fresherProfileTwoEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                profileTwoList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

                final List<String> filteredList = new ArrayList<>(profileTwoList);

                ArrayAdapter<String> jobprofilelist = new ArrayAdapter<String>(CndRegisterationActivity.this, android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(jobprofilelist);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        filteredList.clear();
                        for (String item : profileTwoList) {
                            if (item.toLowerCase().contains(s.toString().toLowerCase()) || item.equals("Other")) {
                                filteredList.add(item);
                            }
                        }
                        jobprofilelist.notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!jobprofilelist.getItem(position).equals("Select Job Profile")) {
                            fresherProfileTwoEP.setText(jobprofilelist.getItem(position));
                            if (fresherProfileTwoEP.getText().toString().equals("Other")){
                                fresherotherProfileTwoEt.setVisibility(View.VISIBLE);
                                fresherotherProfileTwoEt.setFocusableInTouchMode(true);
                                fresherotherProfileTwoEt.setFocusable(true);
                                fresherotherProfileTwoEt.setEnabled(true);
                                fresherotherProfileTwoEt.setCursorVisible(true);
                            }
                            else{
                                fresherotherProfileTwoEt.setText("");
                                fresherotherProfileTwoEt.setVisibility(View.GONE);
                            }
                            if (!fresherjobDesignationTwoEp.getText().toString().isEmpty()){
                                fresherjobDesignationTwoEp.setText("");
                                Toast.makeText(CndRegisterationActivity.this,"Select a Role Designation",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        fresherjobDesignationTwoEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.40);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                jobdesignlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.fresher_designation)));
                ArrayAdapter<String> job_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_list_item_1,jobdesignlist);
                listView.setAdapter(job_adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        job_adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!job_adapter.getItem(position).equals("Select Role Designation")) {
                            fresherjobDesignationTwoEp.setText(job_adapter.getItem(position));
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        expProfileOneEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                profileOneList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

                final List<String> filteredList = new ArrayList<>(profileOneList);

                ArrayAdapter<String> jobprofilelist = new ArrayAdapter<String>(CndRegisterationActivity.this, android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(jobprofilelist);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        filteredList.clear();
                        for (String item : profileOneList) {
                            if (item.toLowerCase().contains(s.toString().toLowerCase()) || item.equals("Other")) {
                                filteredList.add(item);
                            }
                        }
                        jobprofilelist.notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!jobprofilelist.getItem(position).equals("Select Job Profile")) {
                            expProfileOneEP.setText(jobprofilelist.getItem(position));
                            if (expProfileOneEP.getText().toString().equals("Other")){
                                expotherProfileOneEt.setVisibility(View.VISIBLE);
                                expotherProfileOneEt.setFocusableInTouchMode(true);
                                expotherProfileOneEt.setFocusable(true);
                                expotherProfileOneEt.setEnabled(true);
                                expotherProfileOneEt.setCursorVisible(true);
                            }
                            else{
                                expotherProfileOneEt.setText("");
                                expotherProfileOneEt.setVisibility(View.GONE);
                            }
                            if (!expjobDesignationOneEp.getText().toString().isEmpty()){
                                expjobDesignationOneEp.setText("");
                                Toast.makeText(CndRegisterationActivity.this,"Select Role Designation",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        expjobDesignationOneEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                jobdesignlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.designation_spinner)));
                ArrayAdapter<String> job_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_list_item_1,jobdesignlist);
                listView.setAdapter(job_adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        job_adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!job_adapter.getItem(position).equals("Select Role Designation")) {
                            expjobDesignationOneEp.setText(job_adapter.getItem(position));
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        expProfileTwoEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                profileTwoList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

                final List<String> filteredList = new ArrayList<>(profileTwoList);

                ArrayAdapter<String> jobprofilelist = new ArrayAdapter<String>(CndRegisterationActivity.this, android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(jobprofilelist);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        filteredList.clear();
                        for (String item : profileTwoList) {
                            if (item.toLowerCase().contains(s.toString().toLowerCase()) || item.equals("Other")) {
                                filteredList.add(item);
                            }
                        }
                        jobprofilelist.notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!jobprofilelist.getItem(position).equals("Select Job Profile")) {
                            expProfileTwoEP.setText(jobprofilelist.getItem(position));
                            if (expProfileTwoEP.getText().toString().equals("Other")){
                                expotherProfileTwoEt.setVisibility(View.VISIBLE);
                                expotherProfileTwoEt.setFocusableInTouchMode(true);
                                expotherProfileTwoEt.setFocusable(true);
                                expotherProfileTwoEt.setEnabled(true);
                                expotherProfileTwoEt.setCursorVisible(true);
                            }
                            else{
                                expotherProfileTwoEt.setText("");
                                expotherProfileTwoEt.setVisibility(View.GONE);
                            }
                            if (!expjobDesignationTwoEp.getText().toString().isEmpty()){
                                expjobDesignationTwoEp.setText("");
                                Toast.makeText(CndRegisterationActivity.this,"Select Role Designation",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        expjobDesignationTwoEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                jobdesignlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.designation_spinner)));
                ArrayAdapter<String> job_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_list_item_1,jobdesignlist);
                listView.setAdapter(job_adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        job_adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!job_adapter.getItem(position).equals("Select Role Designation")) {
                            expjobDesignationTwoEp.setText(job_adapter.getItem(position));
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

//        expIndustryEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
//                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
//                dialog=new Dialog(CndRegisterationActivity.this);
//                dialog.setContentView(R.layout.searchable_spinner_layout);
//                dialog.getWindow().setLayout(width,height);
//                dialog.show();
//                EditText editText=dialog.findViewById(R.id.et_search_text);
//                ListView listView=dialog.findViewById(R.id.list_item_search);
//                industrylist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.company_industry)));
//                ArrayAdapter<String> companylist =
//                        new ArrayAdapter<String>(CndRegisterationActivity.this,
//                                android.R.layout.simple_list_item_1, industrylist);
//                listView.setAdapter(companylist);
//                editText.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                            companylist.getFilter().filter(s);
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });
//
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        if (!companylist.getItem(position).equals("Select Industry")) {
//                            expIndustryEt.setText(companylist.getItem(position));
//
//                            if (expIndustryEt.getText().toString().equals("Other")){
//                                expIndustryOther.setVisibility(View.VISIBLE);
//                                expIndustryOther.setFocusableInTouchMode(true);
//                                expIndustryOther.setFocusable(true);
//                                expIndustryOther.setEnabled(true);
//                                expIndustryOther.setCursorVisible(true);
//                            }
//                            else{
//                                expIndustryOther.setText("");
//                                expIndustryOther.setVisibility(View.GONE);
//                            }
//                            dialog.dismiss();
//                        }
//                    }
//                });
//            }
//        });
        expIndustryEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog = new Dialog(CndRegisterationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                EditText editText = dialog.findViewById(R.id.et_search_text);
                ListView listView = dialog.findViewById(R.id.list_item_search);
                industrylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.company_industry)));

                final List<String> filteredList = new ArrayList<>(industrylist);
                ArrayAdapter<String> companylist = new ArrayAdapter<String>(CndRegisterationActivity.this,
                        android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(companylist);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        filteredList.clear();
                        for (String item : industrylist) {
                            if (item.toLowerCase().contains(s.toString().toLowerCase()) || item.equals("Other")) {
                                filteredList.add(item);
                            }
                        }
                        companylist.notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!filteredList.get(position).equals("Select Industry")) {
                            expIndustryEt.setText(filteredList.get(position));

                            if (expIndustryEt.getText().toString().equals("Other")) {
                                expIndustryOther.setVisibility(View.VISIBLE);
                                expIndustryOther.setFocusableInTouchMode(true);
                                expIndustryOther.setFocusable(true);
                                expIndustryOther.setEnabled(true);
                                expIndustryOther.setCursorVisible(true);
                            } else {
                                expIndustryOther.setText("");
                                expIndustryOther.setVisibility(View.GONE);
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });


        jobstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jobstatus.isChecked()){
                    expEndDateEt.setVisibility(View.INVISIBLE);
                    expEndDateEt.setText("");
                }
                else {
                    expEndDateEt.setVisibility(View.VISIBLE);
                }
            }
        });

        expStartDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int yearInt=calendar.get(Calendar.YEAR);
                final int monthInt=calendar.get(Calendar.MONTH);
                final int dayInt=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepickerdialog = new DatePickerDialog(CndRegisterationActivity.this,
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                                expStartDateEt.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                                expStartDateEt.setError(null);
                            }
                        },yearInt,monthInt,dayInt);
                datepickerdialog.show();
            }
        });

        expEndDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int yearInt=calendar.get(Calendar.YEAR);
                final int monthInt=calendar.get(Calendar.MONTH);
                final int dayInt=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepickerdialog = new DatePickerDialog(CndRegisterationActivity.this,
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                                expEndDateEt.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                                expEndDateEt.setError(null);
                            }
                        },yearInt,monthInt,dayInt);
                datepickerdialog.show();
            }
        });


        Experiencelist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.experience)));
        ArrayAdapter<String> experience_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_dropdown_item_1line, Experiencelist);
        experienceSpEP.setAdapter(experience_adapter);
        experienceSpEP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CndRegisterationActivity.this,R.array.gender, android.R.layout.simple_spinner_item);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                experienceSt=experienceSt+parent.getItemAtPosition(position).toString();
                expFresherInternExpStr=experienceSpEP.getText().toString();
                if (experienceSpEP.getText().toString().equals("Fresher")){
                    fresher_layout.setVisibility(View.VISIBLE);
                    experience_layout.setVisibility(View.GONE);
                    fresherProfileOneEP.setText("");
                    fresherProfileTwoEP.setText("");
                    fresherotherProfileOneEt.setText("");
                    fresherotherProfileTwoEt.setText("");
                    fresherjobDesignationOneEp.setText("");
                    fresherjobDesignationTwoEp.setText("");
                }
                else {
                    fresher_layout.setVisibility(View.GONE);
                    experience_layout.setVisibility(View.VISIBLE);
                    expProfileOneEP.setText("");
                    expProfileTwoEP.setText("");
                    expotherProfileOneEt.setText("");
                    expotherProfileTwoEt.setText("");
                    expjobDesignationOneEp.setText("");
                    expjobDesignationTwoEp.setText("");
                    expIndustryEt.setText("");
                    expIndustryOther.setText("");
                    //Salarytype.setText("");
                    //Salarytype.setHint("Salary Type");
                    expStartDateEt.setText("");
                    expEndDateEt.setText("");
                    expCompanyEt.setText("");
                    expCurrentSalaryEt.setText("");
                    jobstatus.setChecked(false);
                }
            }

        });


        qualificationlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.qualification)));
        ArrayAdapter<String> qualification_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_dropdown_item_1line, qualificationlist);
        qualificationSpEP.setAdapter(qualification_adapter);
        qualificationSpEP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                qualifyextra.setVisibility(View.VISIBLE);
                if (qualificationSpEP.getText().toString().equals("Below SSC")){
                    below_ssc.setVisibility(View.VISIBLE);
                    ssc_passed.setVisibility(View.GONE);
                    ssc_board.setText("");
                    ssc_school.setText("");
                    hsc_passed.setVisibility(View.GONE);
                    hsc_stream.setText("");
                    hsc_school.setText("");
                    ug.setVisibility(View.GONE);
                    higher_stream.setText("");
                    collegename.setText("");

                    belowsscclasslist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.below_ssc)));
                    ArrayAdapter<String> below_ssc_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this, android.R.layout.simple_dropdown_item_1line,belowsscclasslist);
                    below_ssc_class.setAdapter(below_ssc_adapter);

                    qualificationstatus.setChecked(false);
                    qualificationstartdate.setText("");
                    qualificationenddate.setText("");

                }
                else if (qualificationSpEP.getText().toString().equals("SSC Passed")){
                    below_ssc.setVisibility(View.GONE);
                    below_ssc_class.setText("");
                    belowssc_school.setText("");
                    ssc_passed.setVisibility(View.VISIBLE);
                    hsc_passed.setVisibility(View.GONE);
                    hsc_stream.setText("");
                    hsc_school.setText("");
                    ug.setVisibility(View.GONE);
                    higher_stream.setText("");
                    collegename.setText("");

                    sscboardlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.board)));
                    ArrayAdapter<String> ssc_adapter = new ArrayAdapter<>(CndRegisterationActivity.this, android.R.layout.simple_dropdown_item_1line,sscboardlist);
                    ssc_board.setAdapter(ssc_adapter);

                    qualificationstatus.setChecked(false);
                    qualificationstartdate.setText("");
                    qualificationenddate.setText("");
                }
                else if(qualificationSpEP.getText().toString().equals("HSC Passed")){
                    below_ssc.setVisibility(View.GONE);
                    below_ssc_class.setText("");
                    belowssc_school.setText("");
                    ssc_passed.setVisibility(View.GONE);
                    ssc_board.setText("");
                    ssc_school.setText("");
                    hsc_passed.setVisibility(View.VISIBLE);
                    ug.setVisibility(View.GONE);
                    higher_stream.setText("");
                    collegename.setText("");

                    hscstreamlist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hhc_pass)));
                    ArrayAdapter<String> hscstream_adapter = new ArrayAdapter<>(CndRegisterationActivity.this, android.R.layout.simple_dropdown_item_1line,hscstreamlist);
                    hsc_stream.setAdapter(hscstream_adapter);

                    qualificationstatus.setChecked(false);
                    qualificationstartdate.setText("");
                    qualificationenddate.setText("");
                }
                else if (qualificationSpEP.getText().toString().equals("Under Graduate")){
                    below_ssc.setVisibility(View.GONE);
                    below_ssc_class.setText("");
                    belowssc_school.setText("");
                    ssc_passed.setVisibility(View.GONE);
                    ssc_board.setText("");
                    ssc_school.setText("");
                    hsc_passed.setVisibility(View.GONE);
                    hsc_stream.setText("");
                    hsc_school.setText("");
                    ug.setVisibility(View.VISIBLE);

                    higherstreamlist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.under_Graduate)));
                    ArrayAdapter<String> up_adapter = new ArrayAdapter<>(CndRegisterationActivity.this, android.R.layout.simple_dropdown_item_1line,higherstreamlist);
                    higher_stream.setAdapter(up_adapter);

                    qualificationstatus.setChecked(false);
                    qualificationstartdate.setText("");
                    qualificationenddate.setText("");
                }
                else if (qualificationSpEP.getText().toString().equals("Post Graduate")){
                    below_ssc.setVisibility(View.GONE);
                    below_ssc_class.setText("");
                    belowssc_school.setText("");
                    ssc_passed.setVisibility(View.GONE);
                    ssc_board.setText("");
                    ssc_school.setText("");
                    hsc_passed.setVisibility(View.GONE);
                    hsc_stream.setText("");
                    hsc_school.setText("");
                    ug.setVisibility(View.VISIBLE);

                    higherstreamlist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.post_graduat)));
                    ArrayAdapter<String> up_adapter = new ArrayAdapter<>(CndRegisterationActivity.this, android.R.layout.simple_dropdown_item_1line,higherstreamlist);
                    higher_stream.setAdapter(up_adapter);

                    qualificationstatus.setChecked(false);
                    qualificationstartdate.setText("");
                    qualificationenddate.setText("");
                }
                else{
                    below_ssc.setVisibility(View.GONE);
                    below_ssc_class.setText("");
                    belowssc_school.setText("");
                    ssc_passed.setVisibility(View.GONE);
                    ssc_board.setText("");
                    ssc_school.setText("");
                    hsc_passed.setVisibility(View.GONE);
                    hsc_stream.setText("");
                    hsc_school.setText("");
                    ug.setVisibility(View.VISIBLE);

                    higherstreamlist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.PHD)));
                    ArrayAdapter<String> up_adapter = new ArrayAdapter<>(CndRegisterationActivity.this, android.R.layout.simple_dropdown_item_1line,higherstreamlist);
                    higher_stream.setAdapter(up_adapter);

                    qualificationstatus.setChecked(false);
                    qualificationstartdate.setText("");
                    qualificationenddate.setText("");
                }
            }
        });

        below_ssc_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                belowssc_school.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ssc_board.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ssc_school.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hsc_stream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!hsc_school.getText().toString().equals("") || !hsc_board.getText().toString().equals("")){
                    hsc_board.setText("");
                    hsc_school.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        higher_stream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collegename.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        qualificationstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!qualificationSpEP.getText().toString().equals("")){
                    if (qualificationstatus.isChecked()){
                        qualificationenddate.setVisibility(View.INVISIBLE);
                        qualificationenddate.setText("");
                    }
                    else {
                        qualificationenddate.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    Toast.makeText(CndRegisterationActivity.this,"Please select the qualification",Toast.LENGTH_SHORT).show();
                    qualificationstatus.setChecked(false);
                }

            }
        });


        qualificationstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int yearInt=calendar.get(Calendar.YEAR);
                final int monthInt=calendar.get(Calendar.MONTH);
                final int dayInt=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepickerdialog = new DatePickerDialog(CndRegisterationActivity.this,
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                                qualificationstartdate.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                                qualificationstartdate.setError(null);
                            }
                        },yearInt,monthInt,dayInt);
                datepickerdialog.show();
            }
        });

        qualificationenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final int yearInt=calendar.get(Calendar.YEAR);
                final int monthInt=calendar.get(Calendar.MONTH);
                final int dayInt=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datepickerdialog = new DatePickerDialog(CndRegisterationActivity.this,
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                                qualificationenddate.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                                qualificationenddate.setError(null);
                            }
                        },yearInt,monthInt,dayInt);
                datepickerdialog.show();
            }
        });


        communicationlist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.communication)));
        ArrayAdapter<String> communication_adapter = new ArrayAdapter<String>(CndRegisterationActivity.this,android.R.layout.simple_dropdown_item_1line, communicationlist);

        communicationSpEP.setAdapter(communication_adapter);
        communicationSpEP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CndRegisterationActivity.this, "Select a Communication", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKFILE_RESULT_CODE  && resultCode == RESULT_OK && data != null) {
                Uri path=data.getData();

                //try this one
                try {
                    InputStream inputStream= CndRegisterationActivity.this.getContentResolver().openInputStream(path);
                    byte[] pdfInByte=new byte[inputStream.available()];
                    inputStream.read(pdfInByte);

                    resumeIsSelected=true;

                    encodedPdf = Base64.encodeToString(pdfInByte, Base64.DEFAULT);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                String file_name=getFileName(path);
                selectedFileName.setText(file_name);


            int dotposition= file_name.lastIndexOf(".");
            cmpResumeExtension = file_name.substring(dotposition + 1, file_name.length());
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

//    @Override
//    public void applyTexts(String qualiFirstStr, String qualiSecontStr, String qualiCollege, String qualiStartDate, String qualiEndDate) {
//        qualiStdEt.setText(qualiFirstStr);
//        qualiStreamEt.setText(qualiSecontStr);
//        qualiCollegeNameEt.setText(qualiCollege);
//        qualiStartDateEt.setText(qualiStartDate);
//        qualiEndDateEt.setText(qualiEndDate);
//        quali_layout.setVisibility(View.VISIBLE);
//    }

//    @Override
//    public void experienceData(String expCmpNameStr,String expCurrentSalaryStr, String expIndustryStr, String expRoleStr, String expDesignationStr, String expStartDateStr, String expEndDateStr) {
//        expIndustryEt.setText(expIndustryStr);
//        expRoleEt.setText(expRoleStr);
//        expCompanyEt.setText(expCmpNameStr);
//        expCurrentSalaryEt.setText(expCurrentSalaryStr);
//        expDesignationEt.setText(expDesignationStr);
//        expStartDateEt.setText(expStartDateStr);
//        expEndDateEt.setText(expEndDateStr);
//        exp_layout.setVisibility(View.VISIBLE);
//    }
    /*@Override
    public void appTextExp(String otherIndustryStr, String otherRoleStr, String companyName, String startDate, String endDate) {
        expIndustryEt.setText(otherIndustryStr);
        expRoleEt.setText(otherRoleStr);
        expCompanyEt.setText(companyName);
        expStartDateEt.setText(startDate);
        expEndDateEt.setText(endDate);
        exp_layout.setVisibility(View.VISIBLE);
    }*/


    private void submitButtonAction() {

        ArrayList<String> languagesStrArr = new ArrayList<>();
        if (lngHindiEP.isChecked())
            languagesStrArr.add(lngHindiEP.getText().toString()+",");
        else
            languagesStrArr.remove(lngHindiEP.getText().toString()+",");

        if (lngMarathiEP.isChecked())
            languagesStrArr.add(lngMarathiEP.getText().toString()+",");
        else
            languagesStrArr.remove(lngMarathiEP.getText().toString()+",");

        if (lngEnglishEP.isChecked())
            languagesStrArr.add(lngEnglishEP.getText().toString()+",");
        else
            languagesStrArr.remove(lngEnglishEP.getText().toString()+",");

        if (lngUrduEP.isChecked())
            languagesStrArr.add(lngUrduEP.getText().toString()+",");
        else
            languagesStrArr.remove(lngUrduEP.getText().toString()+",");

        if (lngGujratiEP.isChecked())
            languagesStrArr.add(lngGujratiEP.getText().toString()+",");
        else
            languagesStrArr.remove(lngGujratiEP.getText().toString()+",");

        if (!lngOtherEP.getText().toString().equals(""))
            languagesStrArr.add(lngOtherEP.getText().toString());
        else
            languagesStrArr.remove(lngOtherEP.getText().toString());

        ArrayList<String> vehicleStrArr = new ArrayList<>();
        if (vhcBikeEP.isChecked())
            vehicleStrArr.add(vhcBikeEP.getText().toString()+",");
        else
            vehicleStrArr.remove(vhcBikeEP.getText().toString()+",");

        if (vhcCarEP.isChecked())
            vehicleStrArr.add(vhcCarEP.getText().toString()+",");
        else
            vehicleStrArr.remove(vhcCarEP.getText().toString()+",");

        if (vhcScooterEP.isChecked())
            vehicleStrArr.add(vhcScooterEP.getText().toString()+",");
        else
            vehicleStrArr.remove(vhcScooterEP.getText().toString()+",");

        if (vhcNoEP.isChecked())
            vehicleStrArr.add(vhcNoEP.getText().toString()+",");
        else
            vehicleStrArr.remove(vhcNoEP.getText().toString()+",");

        if (!vhcOtherEP.getText().toString().equals("")){
            vehicleStrArr.add(vhcOtherEP.getText().toString());
        }else{
            vehicleStrArr.remove(vhcOtherEP.getText().toString());
        }

        ArrayList<String> employmentTypeStrArr = new ArrayList<>();
        if (empfulltime.isChecked())
            employmentTypeStrArr.add(empfulltime.getText().toString()+",");
        else
            employmentTypeStrArr.remove(empfulltime.getText().toString()+",");

        if (empparttime.isChecked())
            employmentTypeStrArr.add(empparttime.getText().toString()+",");
        else
            employmentTypeStrArr.remove(empparttime.getText().toString()+",");

        if (empfreelancer.isChecked())
            employmentTypeStrArr.add(empfreelancer.getText().toString()+",");
        else
            employmentTypeStrArr.remove(empfreelancer.getText().toString()+",");


        ArrayList<String> locationTypeStrArr = new ArrayList<>();
        if (loconsite.isChecked())
            locationTypeStrArr.add(loconsite.getText().toString()+",");
        else
            locationTypeStrArr.remove(loconsite.getText().toString()+",");

        if (lochybrid.isChecked())
            locationTypeStrArr.add(lochybrid.getText().toString()+",");
        else
            locationTypeStrArr.remove(lochybrid.getText().toString()+",");

        if (locremote.isChecked())
            locationTypeStrArr.add(locremote.getText().toString()+",");
        else
            locationTypeStrArr.remove(locremote.getText().toString()+",");

        ArrayList<String> licenceStrArr = new ArrayList<>();
        if (licTwoEP.isChecked())
            licenceStrArr.add(licTwoEP.getText().toString()+",");
        else
            licenceStrArr.remove(licTwoEP.getText().toString()+",");

        if (licFourEP.isChecked())
            licenceStrArr.add(licFourEP.getText().toString()+",");
        else
            licenceStrArr.remove(licFourEP.getText().toString()+",");

        if (licTransportEP.isChecked())
            licenceStrArr.add(licTransportEP.getText().toString()+",");
        else
            licenceStrArr.remove(licTransportEP.getText().toString()+",");

        if (licHeavyEP.isChecked())
            licenceStrArr.add(licHeavyEP.getText().toString()+",");
        else
            licenceStrArr.remove(licHeavyEP.getText().toString()+",");

        if (licAmvEP.isChecked())
            licenceStrArr.add(licAmvEP.getText().toString()+",");
        else
            licenceStrArr.remove(licAmvEP.getText().toString()+",");

        if (licNoEP.isChecked())
            licenceStrArr.add(licNoEP.getText().toString()+",");
        else
            licenceStrArr.remove(licNoEP.getText().toString()+",");

        if (!licOtherEP.getText().toString().equals(""))
            licenceStrArr.add(licOtherEP.getText().toString());
        else
            licenceStrArr.remove(licOtherEP.getText().toString());


        ArrayList<String> docStrArr = new ArrayList<>();
        if (docAadharEP.isChecked())
            docStrArr.add(docAadharEP.getText().toString()+",");
        else
            docStrArr.remove(docAadharEP.getText().toString()+",");

        if (docPanEP.isChecked())
            docStrArr.add(docPanEP.getText().toString()+",");
        else
            docStrArr.remove(docPanEP.getText().toString()+",");

        if (docPassportEp.isChecked())
            docStrArr.add(docPassportEp.getText().toString()+",");
        else
            docStrArr.remove(docPassportEp.getText().toString()+",");

        if (docVotingCardEp.isChecked())
            docStrArr.add(docVotingCardEp.getText().toString()+",");
        else
            docStrArr.remove(docVotingCardEp.getText().toString()+",");

        if (docDrivingLicenceEp.isChecked())
            docStrArr.add(docDrivingLicenceEp.getText().toString()+",");
        else
            docStrArr.remove(docDrivingLicenceEp.getText().toString()+",");

        if (docBankEp.isChecked())
            docStrArr.add(docBankEp.getText().toString()+",");
        else
            docStrArr.remove(docBankEp.getText().toString()+",");

        if (!docOtherEP.getText().toString().equals(""))
            docStrArr.add(docOtherEP.getText().toString());
        else
            docStrArr.remove(docOtherEP.getText().toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lng=languagesStrArr.stream().collect(Collectors.joining());
            doc=docStrArr.stream().collect((Collectors.joining()));
            vehi=vehicleStrArr.stream().collect(Collectors.joining());
            licence=licenceStrArr.stream().collect(Collectors.joining());
            emptype=employmentTypeStrArr.stream().collect(Collectors.joining());
            loctype=locationTypeStrArr.stream().collect(Collectors.joining());
        }

        varification(lng,doc,vehi,licence,emptype,loctype);
    }

    private void varification(String lng,String doc, String vehi, String licence,String emptype,String loctype) {


        String fullNameStr,emailStr,genderStr,dobStr,contactStr,alterContactStr,locationStr,stateStr,cityStr,jobProfileOneStr,jobDesigOneStr,
                jobProfileTwoStr,jobDesigTwoStr,qualiStreamStr,qualiStdStr,qualiCollegeStr,qualiStartDateStr,qualiEndDateStr,
                expIndustryStr,expRoleStr,expCompanyStr,expCurrentSalaryStr,expDesignationStr,expStartDateStr,expEndDateStr,
                communicationStr,EmploymentStr,LocationpreferenceStr,languageStr,vehicleStr,licenceStr,documentsStr,skillStr,referenceStr,resumeStr,resumeExtensionStr,cndDateTimeStr;

        if (nameEP.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name Index is empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            fullNameStr = nameEP.getText().toString();
        }
        if (emailEP.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email Index is empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            emailStr = emailEP.getText().toString();
        }
        if (genderSpEP.getText().toString().equals("")) {
            Toast.makeText(this, "Gender Index is empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            genderStr = genderSpEP.getText().toString().trim();
        }
        if (birthDateEP.getText().toString().isEmpty()) {
            Toast.makeText(this, "Birth date is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            dobStr = birthDateEP.getText().toString();
        }
        if (contactEP.getText().toString().isEmpty() || contactEP.getText().toString().length()<10) {
            Toast.makeText(this, "Contact getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            contactStr = contactEP.getText().toString();
        }
        if (alterContactEP.getText().toString().isEmpty()) {
            alterContactStr = "--";
        }else {
            alterContactStr = alterContactEP.getText().toString();
        }

        if (stateSpEP.getText().toString().isEmpty()) {
            Toast.makeText(this, "State is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else{
            stateStr=stateSpEP.getText().toString().trim();
        }

        if (citySpEp.getText().toString().isEmpty()) {
            Toast.makeText(this, "City is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (citySpEp.getText().toString().equals("Other")){
            cityStr=OthercitySpEP.getText().toString().trim();
        }
        else {
            cityStr=citySpEp.getText().toString().trim();
        }


        if (addressEP.getText().toString().isEmpty()) {
            Toast.makeText(this, "Current location getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            locationStr = addressEP.getText().toString();

        }


        if (qualificationSpEP.getText().toString().equals("")){
            Toast.makeText(CndRegisterationActivity.this, "Please fill the Qualification part", Toast.LENGTH_SHORT).show();
            return;
        }else {
            qualiStreamStr=qualificationSpEP.getText().toString();
            if (qualificationSpEP.getText().toString().equals("Below SSC")){
                if (below_ssc_class.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the class", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiStdStr=below_ssc_class.getText().toString();
                }
                if (belowssc_school.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the school", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiCollegeStr=belowssc_school.getText().toString();
                }
            }else if (qualificationSpEP.getText().toString().equals("SSC Passed")){
                if (ssc_board.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the board", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiStdStr=ssc_board.getText().toString();
                }
                if (ssc_school.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the School", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiCollegeStr=ssc_school.getText().toString();
                }
            }else if (qualificationSpEP.getText().toString().equals("HSC Passed")){
                if (hsc_stream.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the stream", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiStdStr=hsc_stream.getText().toString();
                }
                if (hsc_school.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the school", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiCollegeStr=hsc_school.getText().toString();
                }
            }else {
                if (higher_stream.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the stream", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiStdStr=higher_stream.getText().toString();
                }
                if (collegename.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the college name", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiCollegeStr=collegename.getText().toString();
                }
            }
            if (qualificationstatus.isChecked()){
                if (qualificationstartdate.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the start date", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiStartDateStr=qualificationstartdate.getText().toString();
                    qualiEndDateStr="--";
                }
            }else {
                if (qualificationstartdate.getText().toString().equals("") || qualificationenddate.getText().toString().equals("")){
                    Toast.makeText(CndRegisterationActivity.this, "Please select the qualification dates", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    qualiStartDateStr=qualificationstartdate.getText().toString();
                    qualiEndDateStr=qualificationenddate.getText().toString();
                }
            }
        }

        if (experienceSpEP.getText().toString().equals("Fresher")){
            if (fresherProfileOneEP.getText().toString().isEmpty()||
                    fresherjobDesignationOneEp.getText().toString().isEmpty()||
                    fresherProfileTwoEP.getText().toString().isEmpty()||
                    fresherjobDesignationTwoEp.getText().toString().isEmpty()){
                Toast.makeText(this, "Experience is empty", Toast.LENGTH_SHORT).show();
                return;
            }else {
                if (fresherProfileOneEP.getText().toString().equals("Other") &&
                        fresherotherProfileOneEt.getText().toString().isEmpty() &&
                        fresherProfileTwoEP.getText().toString().equals("Other") &&
                        fresherotherProfileTwoEt.getText().toString().isEmpty()){
                    Toast.makeText(this, "Experience is empty", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (fresherProfileOneEP.getText().toString().equals("Other")){
                        jobProfileOneStr=fresherotherProfileOneEt.getText().toString();
                    }
                    else{
                        jobProfileOneStr=fresherProfileOneEP.getText().toString();
                    }
                    jobDesigOneStr=fresherjobDesignationOneEp.getText().toString();

                    if (fresherProfileTwoEP.getText().toString().equals("Other")){
                        jobProfileTwoStr=fresherotherProfileTwoEt.getText().toString();
                    }
                    else{
                        jobProfileTwoStr=fresherProfileTwoEP.getText().toString();
                    }
                    jobDesigTwoStr=fresherjobDesignationTwoEp.getText().toString();

                    expIndustryStr="--";
                    //expRoleStr="--";
                    expCompanyStr="--";
                    expCurrentSalaryStr="--";
                    //expDesignationStr="--";
                    expStartDateStr="--";
                    expEndDateStr="--";
                }
            }

        }else{
            if (jobstatus.isChecked()){
                if (expProfileOneEP.getText().toString().isEmpty()||
                        expjobDesignationOneEp.getText().toString().isEmpty()||
                        expCompanyEt.getText().toString().isEmpty()||
                        expIndustryEt.getText().toString().isEmpty()||
                        expCurrentSalaryEt.getText().toString().isEmpty()||
                        expStartDateEt.getText().toString().isEmpty()||
                        expProfileTwoEP.getText().toString().isEmpty()||
                        expjobDesignationTwoEp.getText().toString().isEmpty()){
                    Toast.makeText(this, "Experience is empty", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if ((expProfileOneEP.getText().toString().equals("Other") && expotherProfileOneEt.getText().toString().isEmpty()) ||
                            (expIndustryEt.getText().toString().equals("Other") && expIndustryOther.getText().toString().isEmpty())||
                            (expProfileTwoEP.getText().toString().equals("Other") && expotherProfileTwoEt.getText().toString().isEmpty())){
                        Toast.makeText(this, "Experience is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        if (expProfileOneEP.getText().toString().equals("Other")){
                            jobProfileOneStr=expotherProfileOneEt.getText().toString();
                        }else {
                            jobProfileOneStr=expProfileOneEP.getText().toString();
                        }

                        jobDesigOneStr=expjobDesignationOneEp.getText().toString();

                        if (expProfileTwoEP.getText().toString().equals("Other")){
                            jobProfileTwoStr=expotherProfileTwoEt.getText().toString();
                        }else {
                            jobProfileTwoStr=expProfileTwoEP.getText().toString();
                        }

                        jobDesigTwoStr=expjobDesignationTwoEp.getText().toString();

                        expCompanyStr=expCompanyEt.getText().toString();
                        if (expIndustryEt.getText().toString().equals("Other")){
                            expIndustryStr=expIndustryOther.getText().toString();
                        }else {
                            expIndustryStr=expIndustryEt.getText().toString();
                        }

                        if (expCurrentSalaryEt.getText().toString().length()>=4){
                            expCurrentSalaryStr=expCurrentSalaryEt.getText().toString();
                        }
                        else {
                            Toast.makeText(CndRegisterationActivity.this, "Please enter minimum 4 digit salary", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        expStartDateStr= expStartDateEt.getText().toString();
                        expEndDateStr="--";

//                        jobProfileOneStr = expRoleStr;
//
//                        jobDesigOneStr=expDesignationStr;

//                        if (expProfileTwoEP.getText().toString().equals("Other")){
//                            jobProfileTwoStr = expotherProfileTwoEt.getText().toString();
//                        }else {
//                            jobProfileTwoStr = expProfileTwoEP.getText().toString();
//                        }
//
//                        jobDesigTwoStr = expjobDesignationTwoEp.getText().toString();
                    }
                }
            }else {
                if (expProfileOneEP.getText().toString().isEmpty()||
                        expjobDesignationOneEp.getText().toString().isEmpty()||
                        expCompanyEt.getText().toString().isEmpty()||
                        expIndustryEt.getText().toString().isEmpty()||
                        expCurrentSalaryEt.getText().toString().isEmpty()||
                        expStartDateEt.getText().toString().isEmpty()||
                        expEndDateEt.getText().toString().isEmpty()||
                        expProfileTwoEP.getText().toString().isEmpty()||
                        expjobDesignationTwoEp.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Experience is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if ((expProfileOneEP.getText().toString().equals("Other") && expotherProfileOneEt.getText().toString().isEmpty()) ||
                            (expIndustryEt.getText().toString().equals("Other") && expIndustryOther.getText().toString().isEmpty())||
                            (expProfileTwoEP.getText().toString().equals("Other") && expotherProfileTwoEt.getText().toString().isEmpty())){
                        Toast.makeText(this, "Experience is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        if (expProfileOneEP.getText().toString().equals("Other")){
                            jobProfileOneStr=expotherProfileOneEt.getText().toString();
                        }else {
                            jobProfileOneStr=expProfileOneEP.getText().toString();
                        }

                        jobDesigOneStr=expjobDesignationOneEp.getText().toString();

                        if (expProfileTwoEP.getText().toString().equals("Other")){
                            jobProfileTwoStr=expotherProfileTwoEt.getText().toString();
                        }else {
                            jobProfileTwoStr=expProfileTwoEP.getText().toString();
                        }

                        jobDesigTwoStr=expjobDesignationTwoEp.getText().toString();

                        expCompanyStr=expCompanyEt.getText().toString();
                        if (expIndustryEt.getText().toString().equals("Other")){
                            expIndustryStr=expIndustryOther.getText().toString();
                        }else {
                            expIndustryStr=expIndustryEt.getText().toString();
                        }

                        if (expCurrentSalaryEt.getText().toString().length()>=4){
                            expCurrentSalaryStr=expCurrentSalaryEt.getText().toString();
                        }
                        else {
                            Toast.makeText(CndRegisterationActivity.this, "Please enter minimum 4 digit salary", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        expStartDateStr= expStartDateEt.getText().toString();
                        expEndDateStr=expEndDateEt.getText().toString();

//                        jobProfileOneStr = expRoleStr;
//
//                        jobDesigOneStr=expDesignationStr;

//                        if (expProfileTwoEP.getText().toString().equals("Other")){
//                            jobProfileTwoStr = expotherProfileTwoEt.getText().toString();
//                        }else {
//                            jobProfileTwoStr = expProfileTwoEP.getText().toString();
//                        }
//
//                        jobDesigTwoStr = expjobDesignationTwoEp.getText().toString();
                    }
                }
            }
        }

        if (communicationSpEP.getText().toString().isEmpty()){
            Toast.makeText(this, "Communication getting empty", Toast.LENGTH_SHORT).show();
            return;
        }else{
            communicationStr = communicationSpEP.getText().toString();
        }

        if (emptype.isEmpty()){
            empfulltime.findFocus();
            Toast.makeText(this,"Employment Type is empty",Toast.LENGTH_SHORT).show();
            return;
        }else {
            EmploymentStr=emptype;
        }

        if (loctype.isEmpty()){
            loconsite.findFocus();
            Toast.makeText(this,"Location Preference is empty",Toast.LENGTH_SHORT).show();
            return;
        }else {
            LocationpreferenceStr=loctype;
        }

        if (lng.isEmpty()) {
            lngHindiEP.findFocus();
            Toast.makeText(this, "Language getting empty", Toast.LENGTH_SHORT).show();
            return;
        }else {
            languageStr = lng;
        }
        if (vehi.isEmpty()) {
            vhcBikeEP.findFocus();
            Toast.makeText(this, "Vehicle getting empty", Toast.LENGTH_SHORT).show();
            return;
        }else {
            vehicleStr = vehi;
        }
        if (licence.isEmpty()) {
            licenceStr="--";
        }else {
            licenceStr = licence;
        }

        if (doc.isEmpty()) {
            Toast.makeText(this, "Documents getting empty", Toast.LENGTH_SHORT).show();
            return;
        }else{
            documentsStr=doc;
        }

        if (otherReference) {
            referenceStr=referenceEP.getText().toString().trim();
        }else {
            referenceRbtn = findViewById(selectedReferenceId);
            referenceStr = referenceRbtn.getText().toString();
        }
        if (referenceStr.isEmpty()) {
            Toast.makeText(CndRegisterationActivity.this, "Reference is getting Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (skillEP.getText().toString().isEmpty()) {
            skillEP.findFocus();
            Toast.makeText(this, "Skill getting empty", Toast.LENGTH_SHORT).show();
            return;
        }else{
            skillStr=skillEP.getText().toString();
        }

        if (!resumeIsSelected) {
            resumeStr="--";
            resumeExtensionStr="--";
        }else{
            resumeStr=encodedPdf;
            resumeExtensionStr=cmpResumeExtension;
        }


        updateDatabase(fullNameStr,emailStr,genderStr,dobStr,contactStr,alterContactStr,locationStr,stateStr,cityStr,jobProfileOneStr,jobDesigOneStr,
                jobProfileTwoStr,jobDesigTwoStr,qualiStreamStr,qualiStdStr,qualiCollegeStr,qualiStartDateStr,qualiEndDateStr,
                expIndustryStr,expCompanyStr,expCurrentSalaryStr,expStartDateStr,expEndDateStr,
                communicationStr,EmploymentStr,LocationpreferenceStr,languageStr,vehicleStr,licenceStr,documentsStr,skillStr,referenceStr,resumeStr,resumeExtensionStr);

    }

    private void updateDatabase(String fullNameStr,String emailStr,String genderStr,String dobStr,
                                String contactStr,String alterContactStr,String locationStr,String stateStr,
                                String cityStr,String jobProfileOneStr,String jobDesigOneStr,
                                String jobProfileTwoStr,String jobDesigTwoStr,String qualiStreamStr,
                                String qualiStdStr,String qualiCollegeStr,String qualiStartDateStr,
                                String qualiEndDateStr,String expIndustryStr,String expCompanyStr,String expCurrentSalaryStr,
                                String expStartDateStr, String expEndDateStr, String communicationStr,String EmploymentStr,
                                String LocationpreferenceStr,String languageStr, String vehicleStr, String licenceStr, String documentsStr,
                                String skillStr, String referenceStr, String resumeStr, String cmpResumeExtension) {

        Dialog dialog=new Dialog(CndRegisterationActivity.this);
        dialog.setTitle("Data inserting...");
        dialog.show();

        /*final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Data Inserting...");
        progressDialog.show();*/

//        uploadOperation(userId);

        ApiInterface api= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call=api.cndRegistration(userId,fullNameStr,emailStr,genderStr,dobStr,contactStr,
                alterContactStr,locationStr,stateStr,cityStr,jobProfileOneStr,jobDesigOneStr,jobProfileTwoStr,jobDesigTwoStr,qualiStdStr,
                qualiStreamStr,qualiCollegeStr,qualiStartDateStr,qualiEndDateStr,expFresherInternExpStr,expIndustryStr,
                expCompanyStr,expCurrentSalaryStr,expStartDateStr, expEndDateStr,communicationStr,EmploymentStr,LocationpreferenceStr,languageStr,
                vehicleStr,licenceStr,documentsStr,skillStr,referenceStr,resumeStr,cmpResumeExtension);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                dialog.dismiss();
                try {
                    if (resumeIsSelected && (response.body().getFile_size() > 0)) {
                        if (!response.body().getError()) {
                            SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),true);

                            startActivity(new Intent(CndRegisterationActivity.this,CandidateActivity.class));
                            finish();
                        }else{
                            Toast.makeText(CndRegisterationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else if (!response.body().getError()) {
                        SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),true);

                        startActivity(new Intent(CndRegisterationActivity.this,CandidateActivity.class));
                        finish();
                    }else{
                        Toast.makeText(CndRegisterationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(CndRegisterationActivity.this, "Exception: "+e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Exception: ",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //progressDialog.dismiss();
                dialog.dismiss();
                Log.i("Message",t.getMessage());
                Toast.makeText(CndRegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadOperation(String userId) {

        String fileName=selectedFileName.getText().toString();

        ApiInterface api= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call=api.uploadPdf(userId,fileName,encodedPdf);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null) {
                    Toast.makeText(CndRegisterationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CndRegisterationActivity.this, "empty....", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(CndRegisterationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onBackPressed();
                        quit();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void quit() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(start);
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


    private static final String[] stateArrayStr =
            new String[] { "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana",
                    "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra",
                    "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
                    "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh",
                    "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry"
            };

}