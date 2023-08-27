package com.abc.justjob.Company.CompanyPostJob;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Candidate.CandidateActivityFragment.CndEditProfileActivity;
import com.abc.justjob.Company.CompanyActivitys.CompanyActivity;
import com.abc.justjob.Company.CompanyActivitys.MyCandidateActivity;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel1;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel2;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel3;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel4;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel5;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel6;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel7;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel8;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel9;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoJo10Fragment extends Fragment {

    //private ImageView ivImage;
    private TextInputLayout etOtherIndustry;
    private TextInputEditText etCmpName,etPocDesignation,etAboutCmp;
    private TextInputEditText etEmail,etPocName,etPocContact,etHeadOfficeLocation;
    private Spinner spCmpType;

    private Dialog dialog;
    private ArrayList<String> industryList;

    private TextView spIndustry;
    private int industryPosition;
    private AppCompatSpinner spPostedBy;
    private Button btnSubmitFragmentEleven;
    private String lastUserId,cmpRegId;
    private String dateToStr;

    public PoJo10Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo11, container, false);

        lastUserId= SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());

        cmpRegId= SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        //ivImage=v.findViewById(R.id.pj_iv_image);
        etCmpName=v.findViewById(R.id.pj_et_cmp_name);
        etEmail=v.findViewById(R.id.pj_et_cmp_email);
        etPocName=v.findViewById(R.id.pj_et_poc_name);
        etPocContact=v.findViewById(R.id.pj_et_poc_contact);
        etPocDesignation=v.findViewById(R.id.pj_et_poc_designation);
        etHeadOfficeLocation=v.findViewById(R.id.pj_et_poc_head_office_location);
        spCmpType=v.findViewById(R.id.pj_et_cmp_type);
        spIndustry=v.findViewById(R.id.pj_sp_industry);
        etOtherIndustry=v.findViewById(R.id.pj_et_other_industry);
        etAboutCmp=v.findViewById(R.id.pj_et_about_cmp);
        spPostedBy=v.findViewById(R.id.pj_sp_posted_by);
        btnSubmitFragmentEleven=v.findViewById(R.id.pj_ib_11_submit);

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        dateToStr = format.format(today);

        spIndustry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOperation();
            }
        });

        btnSubmitFragmentEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToInsert();
            }
        });

        return v;
    }

    private void selectOperation() {

        industryList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.company_industry)));

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.searchable_spinner_layout);
        dialog.getWindow().setLayout(width,height);
        dialog.show();
        EditText editText = dialog.findViewById(R.id.et_search_text);
        ListView listView = dialog.findViewById(R.id.list_item_search);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, industryList);
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

                industryPosition=position;
                if (itemsAdapter.getItem(position).equals("Other")) {
                    etOtherIndustry.setVisibility(View.VISIBLE);
                }else{
                    etOtherIndustry.setVisibility(View.GONE);
                    spIndustry.setText(itemsAdapter.getItem(position));
                }

                dialog.dismiss();

                etOtherIndustry.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        spIndustry.setText("");
                        spIndustry.setText(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

    }

    private void submitToInsert() {

        if (etCmpName.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Company Name is empty...", Toast.LENGTH_SHORT).show();
        } /*else if (etEmail.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Company Email is empty...", Toast.LENGTH_SHORT).show();
        } else if (etPocName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Company Name is empty...", Toast.LENGTH_SHORT).show();
        } else if (etPocContact.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Company Contact is empty...", Toast.LENGTH_SHORT).show();
        } */else if (etPocDesignation.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "POC Designation is empty...", Toast.LENGTH_SHORT).show();
        } /*else if (etHeadOfficeLocation.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Company Head Office location is empty...", Toast.LENGTH_SHORT).show();
        } */else if (spCmpType.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Company Type is not selected...", Toast.LENGTH_SHORT).show();
        } else if (spIndustry.getText().toString().equals("Industry") ||
                spIndustry.getText().toString().equals("Select Industry") ||
                spIndustry.getText().toString().equals("Other")) {
            Toast.makeText(getContext(), "Select Industry...", Toast.LENGTH_SHORT).show();
        } else if (etAboutCmp.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "About company is empty", Toast.LENGTH_SHORT).show();
        } else if (spPostedBy.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Select Posted By...is getting empty", Toast.LENGTH_SHORT).show();
        } else {
            String cmpName,cmpEmail,cmpPocName,cmpPocContact,cmpPocDesignation,cmpHeadOfficeLocation,cmpTypeStr,cmpIndustryStr,cmpAbout,cmpPostedBy;

            cmpName = etCmpName.getText().toString();
//            cmpEmail = etEmail.getText().toString();
            cmpEmail = "--";
//            cmpPocName = etPocName.getText().toString();
            cmpPocName = "--";
//            cmpPocContact = etPocContact.getText().toString();
            cmpPocContact = "--";
            cmpPocDesignation = etPocDesignation.getText().toString();
//            cmpHeadOfficeLocation = etHeadOfficeLocation.getText().toString();
            cmpHeadOfficeLocation = "--";
            cmpTypeStr = spCmpType.getSelectedItem().toString();
            cmpIndustryStr=spIndustry.getText().toString();
            cmpAbout = etAboutCmp.getText().toString();
            cmpPostedBy = spPostedBy.getSelectedItem().toString();

//            Toast.makeText(getContext(), lastUserId+"\n"+cmpName+"\n"+cmpEmail+"\n"+cmpPocName+"\n"+cmpPocContact+"\n"+cmpPocDesignation+"\n"+
//                    cmpHeadOfficeLocation+"\n"+cmpTypeStr+"\n"+cmpIndustryStr+"\n"+cmpAbout+"\n"+cmpPostedBy, Toast.LENGTH_SHORT).show();

//            databaseOperation(lastUserId, cmpName, cmpEmail, cmpPocName, cmpPocContact, cmpPocDesignation,
//                    cmpHeadOfficeLocation, cmpTypeStr, cmpIndustryStr,cmpAbout, cmpPostedBy);


            insertIntoDatabase(cmpRegId, cmpName, cmpEmail, cmpPocName, cmpPocContact, cmpPocDesignation,
                    cmpHeadOfficeLocation, cmpTypeStr, cmpIndustryStr,cmpAbout, cmpPostedBy);
        }
    }

    private void insertIntoDatabase(String cmpRegId,String cmpName, String cmpEmail, String cmpPocName, String cmpPocContact, String cmpPocDesignation, String cmpHeadOfficeLocation, String cmpTypeStr, String cmpIndustryStr, String cmpAbout, String cmpPostedBy) {

        String pojo_1_title, pojo_1_role,pojo_1_designation, pojo_1_type, pojo_1_state,pojo_1_city,
                pojo_2_description,pojo_2_salary_time,pojo_2_min_salary,pojo_2_max_salary,pojo_2_min_age,pojo_2_max_age,pojo_2_location,pojo_2_num_location,
                pojo_3_fresher_can,pojo_3_experience_can,pojo_3_min_exp,pojo_3_max_exp,
                pojo_4_qualification,pojo_4_male_or_female,
                pojo_5_english_know,pojo_5_languages,
                pojo_6_vehicle,pojo_6_laptop,pojo_6_phone,
                pojo_7_document,pojo_7_working_day,
                pojo_8_start_day,pojo_8_end_day,pojo_8_start_night,pojo_8_end_night,pojo_8_start_rotation,pojo_8_end_rotation,
                pojo_9_reimbursement,pojo_9_incentive,pojo_9_depositing,pojo_9_deposit_amount,pojo_9_amount_purpose;

        PojoModel1 pojoModel1=postJobSharedPref.getInstance(getContext()).getPojoFragment1();
        pojo_1_title=pojoModel1.getPojo_1_title();
        pojo_1_role=pojoModel1.getPojo_1_role();
        pojo_1_designation=pojoModel1.getPojo_1_designation();
        pojo_1_type=pojoModel1.getPojo_1_type();
        pojo_1_state=pojoModel1.getPojo_1_state();
        pojo_1_city=pojoModel1.getPojo_1_city();

        PojoModel2 pojoModel2=postJobSharedPref.getInstance(getContext()).getPojoFragment2();
        pojo_2_description=pojoModel2.getPojo_2_description();
        pojo_2_salary_time=pojoModel2.getPojo_2_salary_time();
        pojo_2_min_salary=pojoModel2.getPojo_2_min_salary();
        pojo_2_max_salary=pojoModel2.getPojo_2_max_salary();
        pojo_2_min_age=pojoModel2.getPojo_2_min_age();
        pojo_2_max_age=pojoModel2.getPojo_2_max_age();
        pojo_2_location=pojoModel2.getPojo_2_location();
        pojo_2_num_location=pojoModel2.getPojo_2_num_location();

        PojoModel3 pojoModel3=postJobSharedPref.getInstance(getContext()).getPojoFragment3();
        pojo_3_fresher_can=pojoModel3.getPojo_3_fresher_can();
        pojo_3_experience_can=pojoModel3.getPojo_3_experience_can();
        pojo_3_min_exp=pojoModel3.getPojo_3_min_exp();
        pojo_3_max_exp=pojoModel3.getPojo_3_max_exp();

        PojoModel4 pojoModel4=postJobSharedPref.getInstance(getContext()).getPojoFragment4();
        pojo_4_qualification=pojoModel4.getPojo_4_qualification();
        pojo_4_male_or_female=pojoModel4.getPojo_4_male_or_female();

        PojoModel5 pojoModel5=postJobSharedPref.getInstance(getContext()).getPojoFragment5();
        pojo_5_english_know=pojoModel5.getPojo_5_english_know();
        pojo_5_languages=pojoModel5.getPojo_5_languages();

        PojoModel6 pojoModel6=postJobSharedPref.getInstance(getContext()).getPojoFragment6();
        pojo_6_vehicle=pojoModel6.getPojo_6_vehicle();
        pojo_6_laptop=pojoModel6.getPojo_6_laptop();
        pojo_6_phone=pojoModel6.getPojo_6_phone();

        PojoModel7 pojoModel7=postJobSharedPref.getInstance(getContext()).getPojoFragment7();
        pojo_7_document=pojoModel7.getPojo_7_document();
        pojo_7_working_day=pojoModel7.getPojo_7_working_day();

        PojoModel8 pojoModel8=postJobSharedPref.getInstance(getContext()).getPojoFragment8();
        pojo_8_start_day=pojoModel8.getPojo_8_start_day();
        pojo_8_end_day=pojoModel8.getPojo_8_end_day();
        pojo_8_start_night=pojoModel8.getPojo_8_start_night();
        pojo_8_end_night=pojoModel8.getPojo_8_end_night();
        pojo_8_start_rotation=pojoModel8.getPojo_8_start_rotation();
        pojo_8_end_rotation=pojoModel8.getPojo_8_end_rotation();

        PojoModel9 pojoModel9=postJobSharedPref.getInstance(getContext()).getPojoFragment9();
        pojo_9_reimbursement=pojoModel9.getPojo_9_reimbursement();
        pojo_9_incentive=pojoModel9.getPojo_9_incentive();
        pojo_9_depositing=pojoModel9.getPojo_9_depositing();
        pojo_9_deposit_amount=pojoModel9.getPojo_9_deposit_amount();
        pojo_9_amount_purpose=pojoModel9.getPojo_9_amount_purpose();

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call = api.cmpPostJobInterfaceAll(
                cmpRegId,pojo_1_title, pojo_1_role,pojo_1_designation, pojo_1_type, pojo_1_state,pojo_1_city,
                pojo_2_description,pojo_2_salary_time,pojo_2_min_salary,pojo_2_max_salary,pojo_2_min_age,pojo_2_max_age,pojo_2_location,pojo_2_num_location,
                pojo_3_fresher_can,pojo_3_experience_can,pojo_3_min_exp,pojo_3_max_exp,
                pojo_4_qualification,pojo_4_male_or_female,
                pojo_5_english_know,pojo_5_languages,
                pojo_6_vehicle,pojo_6_laptop,pojo_6_phone,
                pojo_7_document,pojo_7_working_day,
                pojo_8_start_day,pojo_8_end_day,pojo_8_start_night,pojo_8_end_night,pojo_8_start_rotation,pojo_8_end_rotation,
                pojo_9_reimbursement,pojo_9_incentive,pojo_9_depositing,pojo_9_deposit_amount,pojo_9_amount_purpose,
                cmpName,cmpEmail,cmpPocName,cmpPocContact,cmpPocDesignation,cmpHeadOfficeLocation,cmpTypeStr,cmpIndustryStr,cmpAbout,cmpPostedBy,dateToStr);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                try {
                    if (response.body() != null) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        if (!response.body().getError()) {
                            Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                            showAleatDialog();


                        }else{
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    progressDialog.dismiss();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Exception: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void databaseOperation(String lastUserId, String cmpName, String cmpEmail, String cmpPocName, String cmpPocContact,
                                   String cmpPocDesignation,String cmpHeadOfficeLocation,String cmpTypeStr, String cmpIndustryStr,String cmpAbout, String cmpPostedBy) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call = api.cmpPostJobInterfaceEleven(lastUserId,cmpName,cmpEmail,cmpPocName,
                cmpPocContact,cmpPocDesignation,cmpHeadOfficeLocation,cmpTypeStr,cmpIndustryStr,cmpAbout,cmpPostedBy,dateToStr);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (response.body() != null) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        if (!response.body().getError()) {
                            Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                            showAleatDialog();


                        }else{
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Exception: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAleatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),
                AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setMessage("This job is under Review.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        postJobSharedPref.getInstance(getContext()).postJobDataClear();
                        updatePostJobCount();

                        startActivity(new Intent(getContext(), CompanyActivity.class));
                        ((Activity)getContext()).finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void updatePostJobCount() {
        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.cmpPostJobCount(cmpRegId);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes=stream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}