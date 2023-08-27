package com.abc.justjob.Company.CompanyActivitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Company.CompanyActivitys.CmpEditProfileActivity;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.bumptech.glide.load.engine.Resource;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CmpEditProfileActivity extends AppCompatActivity {

    private Dialog dialog;
    private String cmpRegId,cmpFNameStr,cmpContactStr,cmpDesignationStr,cmpEmailStr,cmpCompanyNameStr,cmpOfficeLocationStr,
            cmpIndustryStr,cmpCompanyCatagoryStr,cmpJobTypeStr,cmpCompanyAddressStr,cmpAboutCompanyStr;


    //Name
    private LinearLayout nameLL;
    private TextInputLayout nameTxInput;
    private Button nameBtn;
    private TextView nameTexVi;
    private ImageView nameImgVi;
    //Name

    //Contact
    private LinearLayout contactLL;
    private TextInputLayout contactTxInput;
    private Button contactBtn;
    private TextView contactTexVi;
    private ImageView contactImgVi;
    //Contact

    //designation
    private LinearLayout designationLL;
    private TextInputLayout designationTxInput;
    private Button designationBtn;
    private TextView designationTexVi;
    private ImageView designationImgVi;
    //designation

    //email
    private LinearLayout emailLL;
    private TextInputLayout emailTxInput;
    private Button emailBtn;
    private TextView emailTexVi;
    private ImageView emailImgVi;
    //email

    //company Name
    private LinearLayout cmpNameLL;
    private TextInputLayout cmpNameTxInput;
    private Button cmpNameBtn;
    private TextView cmpNameTexVi;
    private ImageView cmpNameImgVi;
    //company Name

    //Head office location
    private LinearLayout hLocationLL;
    private TextInputLayout hLocationTxInput;
    private Button hLocationBtn;
    private TextView hLocationTexVi;
    private ImageView hLocationImgVi;
    //Head office location

    //industry
    private LinearLayout industryLL;
    private Button industryBtn;
    private TextView industryTxInput,industryTexVi;
    private TextInputLayout otherIndustryTxInput;
    private ImageView industryImgVi;
    private ArrayList<String> industryList;
    //industry

    //company catagory
    private LinearLayout catagoryLL;
    private AppCompatSpinner catagoryTxInput;
    private Button catagoryBtn;
    private TextView catagoryTexVi;
    private ImageView catagoryImgVi;
    //company catagory

    //company type
    private LinearLayout cmpTypeLL;
    private AppCompatSpinner cmpTypeTxInput;
    private Button cmpTypeBtn;
    private TextView cmpTypeTexVi;
    private ImageView cmpTypeImgVi;
    //company type

    //company address
    private LinearLayout cmpAddressLL;
    private TextInputLayout cmpAddressTxInput;
    private Button cmpAddressBtn;
    private TextView cmpAddressTexVi;
    private ImageView cmpAddressImgVi;
    //company address

    //about Company
    private LinearLayout cmpAboutLL;
    private TextInputLayout cmpAboutTxInput;
    private Button cmpAboutBtn;
    private TextView cmpAboutTexVi;
    private ImageView cmpAboutImgVi;
    //about Company

    private AppCompatButton cmpSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmp_edit_profile);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        checkNightModeActivated();

        initOperation();

        getDataFromIntent();

        onClickOperation();

        cmpSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToDatabase();
            }
        });
    }

    private void updateToDatabase() {

        String cmpFNameEditStr,cmpContactEditStr,cmpDesignationEditStr,cmpEmailEditStr,cmpCompanyNameEditStr,cmpOfficeLocationEditStr,
                cmpIndustryEditStr,cmpCompanyCatagoryEditStr,cmpJobTypeEditStr,cmpCompanyAddressEditStr,cmpAboutCompanyEditStr;

        cmpFNameEditStr=nameTexVi.getText().toString();
        cmpContactEditStr=contactTexVi.getText().toString();
        cmpDesignationEditStr=designationTexVi.getText().toString();
        cmpEmailEditStr=emailTexVi.getText().toString();
        cmpCompanyNameEditStr=cmpNameTexVi.getText().toString();
        cmpOfficeLocationEditStr=hLocationTexVi.getText().toString();
        cmpIndustryEditStr=industryTexVi.getText().toString();
        cmpCompanyCatagoryEditStr=catagoryTexVi.getText().toString();
        cmpJobTypeEditStr=cmpTypeTexVi.getText().toString();
        cmpCompanyAddressEditStr=cmpAddressTexVi.getText().toString();
        cmpAboutCompanyEditStr=cmpAboutTexVi.getText().toString();

        Dialog progressDialog=new Dialog(CmpEditProfileActivity.this);
        progressDialog.setTitle("Inserting Data...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.cmpUpdateProfile(cmpRegId,cmpFNameEditStr,cmpContactEditStr,cmpDesignationEditStr,cmpEmailEditStr,cmpCompanyNameEditStr,cmpOfficeLocationEditStr,
                cmpIndustryEditStr,cmpCompanyCatagoryEditStr,cmpJobTypeEditStr,cmpCompanyAddressEditStr,cmpAboutCompanyEditStr);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (!response.body().getError()) {
                    progressDialog.dismiss();
                    Toast.makeText(CmpEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CmpEditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CmpEditProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();

        cmpRegId=bundle.getString("cm_reg_id");
        cmpFNameStr= bundle.getString("cmp_f_name");
        cmpContactStr= bundle.getString("cmp_contact");
        cmpDesignationStr= bundle.getString("cmp_designation");
        cmpEmailStr= bundle.getString("cmp_email");
        cmpCompanyNameStr= bundle.getString("cmp_company_name");
        cmpOfficeLocationStr= bundle.getString("cmp_head_location");
        cmpIndustryStr= bundle.getString("cmp_industry");
        cmpCompanyCatagoryStr= bundle.getString("cmp_company_category");
        cmpJobTypeStr= bundle.getString("cmp_job_type");
        cmpCompanyAddressStr= bundle.getString("cmp_company_address");
        cmpAboutCompanyStr= bundle.getString("cmp_about");

        nameTexVi.setText(cmpFNameStr);
        contactTexVi.setText(cmpContactStr);
        designationTexVi.setText(cmpDesignationStr);
        emailTexVi.setText(cmpEmailStr);
        cmpNameTexVi.setText(cmpCompanyNameStr);
        hLocationTexVi.setText(cmpOfficeLocationStr);
        industryTexVi.setText(cmpIndustryStr);
        catagoryTexVi.setText(cmpCompanyCatagoryStr);
        cmpTypeTexVi.setText(cmpJobTypeStr);
        cmpAddressTexVi.setText(cmpCompanyAddressStr);
        cmpAboutTexVi.setText(cmpAboutCompanyStr);
    }

    private void onClickOperation() {

        nameImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameLL.setVisibility(View.VISIBLE);
            }
        });
        nameOperation();

        contactImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactLL.setVisibility(View.VISIBLE);
            }
        });
        contactOperation();

        designationImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designationLL.setVisibility(View.VISIBLE);
            }
        });
        designationOperation();

        emailImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailLL.setVisibility(View.VISIBLE);
            }
        });
        emailOperation();

        cmpNameImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpNameLL.setVisibility(View.VISIBLE);
            }
        });
        cmpNameOperation();

        hLocationImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hLocationLL.setVisibility(View.VISIBLE);
            }
        });
        hLocationOperation();

        industryImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 industryLL.setVisibility(View.VISIBLE);
            }
        });
        industryOperation();

        catagoryImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catagoryLL.setVisibility(View.VISIBLE);
            }
        });
        catagoryOperation();

        cmpTypeImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpTypeLL.setVisibility(View.VISIBLE);
            }
        });
        cmpTypeOperation();

        cmpAddressImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpAddressLL.setVisibility(View.VISIBLE);
            }
        });
        cmpAddressOperation();

        cmpAboutImgVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpAboutLL.setVisibility(View.VISIBLE);
            }
        });
        cmpAboutOperation();

    }

    private void designationOperation() {
        designationTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                designationTexVi.setText("");
                designationTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        designationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designationLL.setVisibility(View.GONE);
            }
        });
    }

    private void contactOperation() {
        contactTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contactTexVi.setText("");
                contactTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactLL.setVisibility(View.GONE);
            }
        });
    }

    private void cmpAboutOperation() {
        cmpAboutTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cmpAboutTexVi.setText("");
                cmpAboutTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cmpAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpAboutLL.setVisibility(View.GONE);
            }
        });
    }

    private void cmpAddressOperation() {
        cmpAddressTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cmpAddressTexVi.setText("");
                cmpAddressTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cmpAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpAddressLL.setVisibility(View.GONE);
            }
        });
    }

    private void cmpTypeOperation() {
        cmpTypeTxInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItemPosition() > 0) {
                    cmpTypeTexVi.setText("");
                    cmpTypeTexVi.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cmpTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpTypeLL.setVisibility(View.GONE);
            }
        });
    }

    private void catagoryOperation() {
        catagoryTxInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItemPosition()>0) {
                    catagoryTexVi.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        catagoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catagoryLL.setVisibility(View.GONE);
            }
        });
    }

    private void industryOperation() {

        industryList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.company_industry)));

        industryTxInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                dialog = new Dialog(CmpEditProfileActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText = dialog.findViewById(R.id.et_search_text);
                ListView listView = dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CmpEditProfileActivity.this,
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
                        industryTxInput.setText(itemsAdapter.getItem(position));
                        dialog.dismiss();

                        if (itemsAdapter.getItem(position).equals("Other")) {
                            otherIndustryTxInput.setVisibility(View.VISIBLE);
                            industryTexVi.setText("");
                        } else {
                            otherIndustryTxInput.setVisibility(View.GONE);
                            industryTexVi.setText(itemsAdapter.getItem(position));
                        }
                    }
                });
            }
        });
        otherIndustryTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                industryTexVi.setText("");
                industryTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        industryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!industryTexVi.getText().toString().isEmpty()) {
                    industryLL.setVisibility(View.GONE);
                }
            }
        });
    }

    private void hLocationOperation() {
        hLocationTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hLocationTexVi.setText("");
                hLocationTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        hLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hLocationLL.setVisibility(View.GONE);
            }
        });
    }

    private void cmpNameOperation() {
        cmpNameTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cmpNameTexVi.setText("");
                cmpNameTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cmpNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmpNameLL.setVisibility(View.GONE);
            }
        });
    }

    private void emailOperation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        emailTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailTexVi.setText("");
                emailTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailTexVi.getText().toString().matches(emailPattern)) {
                    emailLL.setVisibility(View.GONE);
                }else{
                    Toast.makeText(CmpEditProfileActivity.this, "This email is not a valid email...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void nameOperation() {
        nameTxInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameTexVi.setText("");
                nameTexVi.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameLL.setVisibility(View.GONE);
            }
        });
    }

    private void initOperation() {
        //Name
        nameLL=findViewById(R.id.ll_cmp_edit_name);
        nameTxInput=findViewById(R.id.text_input_cmp_edit_name);
        nameBtn=findViewById(R.id.btn_input_cmp_edit_name_don);
        nameTexVi=findViewById(R.id.text_view_cmp_edit_name);
        nameImgVi=findViewById(R.id.iv_cmp_edit_name);
        //Name

        //contact
        contactLL=findViewById(R.id.ll_cmp_edit_contact);
        contactTxInput=findViewById(R.id.text_input_cmp_edit_contact);
        contactBtn=findViewById(R.id.btn_input_cmp_contact_don);
        contactTexVi=findViewById(R.id.text_view_cmp_edit_contact);
        contactImgVi=findViewById(R.id.iv_cmp_edit_contact);
        //contact

        //designatoin
        designationLL=findViewById(R.id.ll_cmp_edit_designation);
        designationTxInput=findViewById(R.id.text_input_cmp_edit_designation);
        designationBtn=findViewById(R.id.btn_input_cmp_edit_designation_don);
        designationTexVi=findViewById(R.id.text_view_cmp_edit_designation);
        designationImgVi=findViewById(R.id.iv_cmp_edit_designation);
        //designation

        //email
        emailLL=findViewById(R.id.ll_cmp_edit_email);
        emailTxInput=findViewById(R.id.text_input_cmp_edit_email);
        emailBtn=findViewById(R.id.btn_input_cmp_edit_email_don);
        emailTexVi=findViewById(R.id.text_view_cmp_edit_email);
        emailImgVi=findViewById(R.id.iv_cmp_edit_email);
        //email

        //Company Name
        cmpNameLL=findViewById(R.id.ll_cmp_edit_company_name);
        cmpNameTxInput=findViewById(R.id.text_input_cmp_edit_company_name);
        cmpNameBtn=findViewById(R.id.btn_input_cmp_edit_company_name_don);
        cmpNameTexVi=findViewById(R.id.text_view_cmp_edit_company_name);
        cmpNameImgVi=findViewById(R.id.iv_cmp_edit_company_name);
        //Company Name

        //Head office location
        hLocationLL=findViewById(R.id.ll_cmp_edit_head_location);
        hLocationTxInput=findViewById(R.id.text_input_cmp_edit_head_location);
        hLocationBtn=findViewById(R.id.btn_input_cmp_edit_head_location_don);
        hLocationTexVi=findViewById(R.id.text_view_cmp_edit_head_location);
        hLocationImgVi=findViewById(R.id.iv_cmp_edit_head_location);
        //Head office location


        //industry
        industryLL=findViewById(R.id.ll_cmp_edit_industry);
        industryTxInput=findViewById(R.id.text_input_cmp_edit_industry);
        industryBtn=findViewById(R.id.btn_input_cmp_edit_industry_don);
        industryTexVi=findViewById(R.id.text_view_cmp_edit_industry);
        otherIndustryTxInput=findViewById(R.id.text_view_cmp_edit_other_industry);
        industryImgVi=findViewById(R.id.iv_cmp_edit_industry);
        //industry

        //company catagory
        catagoryLL=findViewById(R.id.ll_cmp_edit_catagory);
        catagoryTxInput=findViewById(R.id.text_input_cmp_edit_catagory);
        catagoryBtn=findViewById(R.id.btn_input_cmp_edit_catagory_don);
        catagoryTexVi=findViewById(R.id.text_view_cmp_edit_catagory);
        catagoryImgVi=findViewById(R.id.iv_cmp_edit_catagory);
        //company catagory

        //company type
        cmpTypeLL=findViewById(R.id.ll_cmp_edit_company_type);
        cmpTypeTxInput=findViewById(R.id.text_input_cmp_edit_company_type);
        cmpTypeBtn=findViewById(R.id.btn_input_cmp_edit_company_type_don);
        cmpTypeTexVi=findViewById(R.id.text_view_cmp_edit_company_type);
        cmpTypeImgVi=findViewById(R.id.iv_cmp_edit_company_type);
        //company type

        //company address
        cmpAddressLL=findViewById(R.id.ll_cmp_edit_company_address);
        cmpAddressTxInput=findViewById(R.id.text_input_cmp_edit_company_address);
        cmpAddressBtn=findViewById(R.id.btn_input_cmp_edit_company_address_don);
        cmpAddressTexVi=findViewById(R.id.text_view_cmp_edit_company_address);
        cmpAddressImgVi=findViewById(R.id.iv_cmp_edit_company_address);
        //company address

        //about Company
        cmpAboutLL=findViewById(R.id.ll_cmp_edit_about);
        cmpAboutTxInput=findViewById(R.id.text_input_cmp_edit_about);
        cmpAboutBtn=findViewById(R.id.btn_input_cmp_edit_about_don);
        cmpAboutTexVi=findViewById(R.id.text_view_cmp_edit_about);
        cmpAboutImgVi=findViewById(R.id.iv_cmp_edit_about);
        //about Company

        cmpSubmitBtn=findViewById(R.id.company_submit_btn_edit);
    }
    /*public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }*/
}