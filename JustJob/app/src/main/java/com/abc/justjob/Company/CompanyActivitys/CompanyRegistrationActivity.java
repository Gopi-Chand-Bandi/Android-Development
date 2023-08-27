package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Candidate.CandidateActivityFragment.CndRegisterationActivity;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyRegistrationActivity extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    private String cmpRegId,cmpNameStr,cmpemailStr;

    private TextInputEditText cmpFullNameEt,cmpContactEt,cmpDesignationEt,cmpEmailEt,cmpCompanyName,cmpWebsitelink,
            cmpHeadLocationEt,cmpCompanyAddressEt,cmpAboutEt;
    private TextView cmpEtIndustry,State,City;
    ArrayList<String> emp_size_array,industrylist,stateList,cityList,companyCategorylist,companyTypelist;
    Dialog dialog;

    private Button saveEditBtn;

    private String empsSizeStr="",CategoryStr="",CompanyTypeStr="";

    private EditText OtherCity,cmpEditOtherIndustry;

    private AutoCompleteTextView emp_size,cmpCompanyCategory,cmpJobTypeSp;

    private int statePosition;

    private String cmpFullNameStr,cmpContactStr,cmpDesignationStr,cmpEmailStr,cmpCompanyNameStr,cmpWebsiteLinkStr,stateStr,cityStr,cmpHeadLocationStr,
            cmpIndustryStr,cmpCategoryStr,cmpJobTypeStr,cmpCompanyAddressStr,cmpAboutStr,cmpDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);

        cmpRegId = SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());
        cmpNameStr = SharedPrefManager.getInstance(getApplicationContext()).getUserName(getApplicationContext());
        cmpemailStr = SharedPrefManager.getInstance(getApplicationContext()).getUserEmail(getApplicationContext());




        cmpFullNameEt = findViewById(R.id.cmp_edit_profile_name);
        cmpFullNameEt.setText(cmpNameStr);

        cmpContactEt = findViewById(R.id.cmp_edit_profile_contact);

        cmpDesignationEt = findViewById(R.id.cmp_edit_profile_designation);

        cmpEmailEt = findViewById(R.id.cmp_edit_profile_email);
        cmpEmailEt.setText(cmpemailStr);

        cmpCompanyName = findViewById(R.id.cmp_edit_company_name);

        cmpWebsitelink = findViewById(R.id.cmp_edit_company_website_link);
        cmpWebsitelink.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
        cmpWebsitelink.setAutoLinkMask(Linkify.WEB_URLS);

        emp_size = findViewById(R.id.emp_size_Et_spinner);

        State = findViewById(R.id.State_spinner);

        City = findViewById(R.id.City_spinner);
        OtherCity = findViewById(R.id.other_City_SP);

        cmpHeadLocationEt=findViewById(R.id.cmp_edit_profile_Head_office_location);

        cmpEtIndustry=findViewById(R.id.register_company_industry);

        cmpEditOtherIndustry=findViewById(R.id.register_company_industry_other);

        cmpCompanyCategory=findViewById(R.id.Company_category_Et_spinner);

        cmpJobTypeSp=findViewById(R.id.Company_type_Et_spinner);

        cmpCompanyAddressEt=findViewById(R.id.cmp_edit_profile_company_address);

        cmpAboutEt=findViewById(R.id.cmp_edit_profile_about_company);

        saveEditBtn=findViewById(R.id.cmp_edit_profile_save_btn);

        emp_size_array=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.emp_size)));
        ArrayAdapter<String> emp_size_adapter = new ArrayAdapter<String>(CompanyRegistrationActivity.this,android.R.layout.simple_dropdown_item_1line,emp_size_array);
        emp_size.setAdapter(emp_size_adapter);
        emp_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                empsSizeStr="";
                empsSizeStr=empsSizeStr+emp_size.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CompanyRegistrationActivity.this, "Select Employee Size", Toast.LENGTH_SHORT).show();
            }
        });

        stateList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.india_states)));

        State.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CompanyRegistrationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CompanyRegistrationActivity.this,
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
                            State.setText(itemsAdapter.getItem(position));
                            if(!City.getText().toString().equals("")){
                                City.setText("");
                                City.setHint("Select a City");
                                OtherCity.setText("");
                                OtherCity.setHint("Ex:Other City");
                                OtherCity.setVisibility(View.GONE);
                                Toast.makeText(CompanyRegistrationActivity.this,"Select the city",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        City.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog=new Dialog(CompanyRegistrationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);

                String s=State.getText().toString();

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
                        Toast.makeText(CompanyRegistrationActivity.this,"Select the state first",Toast.LENGTH_SHORT).show();
                        return;
                }

                final List<String> filteredList = new ArrayList<>(cityList);

                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(CompanyRegistrationActivity.this,
                                android.R.layout.simple_list_item_1, filteredList);
                listView.setAdapter(itemsAdapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        filteredList.clear();
                        for (String item : cityList) {
                            if (item.toLowerCase().contains(s.toString().toLowerCase()) || item.equals("Other")) {
                                filteredList.add(item);
                            }
                        }
                        itemsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!itemsAdapter.getItem(position).equals("Select City")) {
                            City.setText(itemsAdapter.getItem(position));
                            if (City.getText().toString().equals("Other")){
                                OtherCity.setVisibility(View.VISIBLE);
                                OtherCity.setFocusableInTouchMode(true);
                                OtherCity.setFocusable(true);
                                OtherCity.setEnabled(true);
                                OtherCity.setCursorVisible(true);
                            }
                            else{
                                OtherCity.setText("");
                                OtherCity.setHint("Ex: Other City");
                                OtherCity.setVisibility(View.GONE);
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        cmpDateTime = format.format(today);


        cmpEtIndustry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dialog = new Dialog(CompanyRegistrationActivity.this);
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                EditText editText = dialog.findViewById(R.id.et_search_text);
                ListView listView = dialog.findViewById(R.id.list_item_search);
                industrylist = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.company_industry)));

                final List<String> filteredList = new ArrayList<>(industrylist);
                ArrayAdapter<String> companylist = new ArrayAdapter<String>(CompanyRegistrationActivity.this,
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
                            cmpEtIndustry.setText(filteredList.get(position));

                            if (cmpEtIndustry.getText().toString().equals("Other")) {
                                cmpEditOtherIndustry.setVisibility(View.VISIBLE);
                                cmpEditOtherIndustry.setFocusableInTouchMode(true);
                                cmpEditOtherIndustry.setFocusable(true);
                                cmpEditOtherIndustry.setEnabled(true);
                                cmpEditOtherIndustry.setCursorVisible(true);
                            } else {
                                cmpEtIndustry.setError(null);
                                cmpEditOtherIndustry.setText("");
                                cmpEditOtherIndustry.setVisibility(View.GONE);
                            }
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        companyCategorylist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.company_type)));
        ArrayAdapter<String> company_Category_adapter = new ArrayAdapter<String>(CompanyRegistrationActivity.this,android.R.layout.simple_dropdown_item_1line,companyCategorylist);
        cmpCompanyCategory.setAdapter(company_Category_adapter);
        cmpCompanyCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryStr="";
                CategoryStr=CategoryStr+cmpCompanyCategory.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CompanyRegistrationActivity.this, "Select Company Category", Toast.LENGTH_SHORT).show();
            }
        });

        companyTypelist=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_cmp_category)));
        ArrayAdapter<String> company_Type_adapter = new ArrayAdapter<String>(CompanyRegistrationActivity.this,android.R.layout.simple_dropdown_item_1line,companyTypelist);
        cmpJobTypeSp.setAdapter(company_Type_adapter);
        cmpJobTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CompanyTypeStr="";
                CompanyTypeStr=CompanyTypeStr+cmpJobTypeSp.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(CompanyRegistrationActivity.this, "Select Company Type", Toast.LENGTH_SHORT).show();
            }
        });

        saveEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOperation();
            }
        });
    }

    private void saveOperation() {

        if (cmpFullNameEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Name Index is empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            cmpFullNameStr = cmpFullNameEt.getText().toString();
        }

        if (cmpContactEt.getText().toString().isEmpty() || cmpContactEt.getText().toString().length()<10) {
            Toast.makeText(this, "Contact getting empty...", Toast.LENGTH_SHORT).show();
            cmpContactEt.setError("Enter 10 digit valid number");
            return;
        }else {
            cmpContactStr= cmpContactEt.getText().toString();
        }

        if (cmpDesignationEt.getText().toString().isEmpty() || cmpContactEt.getText().toString().length()<10) {
            Toast.makeText(this, "Designation getting empty...", Toast.LENGTH_SHORT).show();
            cmpDesignationEt.setError("Enter the Designation");
            return;
        }else {
            cmpDesignationStr= cmpDesignationEt.getText().toString();
        }

        if (cmpEmailEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email is empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            cmpEmailStr= cmpEmailEt.getText().toString();
        }

        if (cmpCompanyName.getText().toString().isEmpty() || cmpContactEt.getText().toString().length()<10) {
            Toast.makeText(this, "Company Name getting empty...", Toast.LENGTH_SHORT).show();
            cmpCompanyName.setError("Enter the Company Name");
            return;
        }else {
            cmpCompanyNameStr= cmpCompanyName.getText().toString();
        }

        if (cmpWebsitelink.getText().toString().isEmpty()) {
            Toast.makeText(this, "Website field is empty", Toast.LENGTH_SHORT).show();
            cmpWebsitelink.setError("Enter your company website link");
            return;
        } else {
            cmpWebsiteLinkStr = cmpWebsitelink.getText().toString();
            if ((cmpWebsiteLinkStr.startsWith("https://")&& cmpWebsiteLinkStr.indexOf("https://") == 0 && cmpWebsiteLinkStr.indexOf("https://", 8) == -1)||
                    (cmpWebsiteLinkStr.startsWith("www.")&& cmpWebsiteLinkStr.indexOf("www.") == 0 && cmpWebsiteLinkStr.indexOf("www.", 4) == -1)){

            }else {
                cmpWebsitelink.setError("Enter your company website link");
                Toast.makeText(this, "Invalid link", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (emp_size.getText().toString().isEmpty()) {
            Toast.makeText(this, "Employee Size is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            empsSizeStr = emp_size.getText().toString();
        }

        if (State.getText().toString().isEmpty()) {
            Toast.makeText(this, "State is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else{
            stateStr=State.getText().toString().trim();
        }

        if (City.getText().toString().isEmpty()) {
            Toast.makeText(this, "City is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (City.getText().toString().equals("Other")){
            cityStr=OtherCity.getText().toString().trim();
        }
        else {
            cityStr=City.getText().toString().trim();
        }

        if (cmpHeadLocationEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Location getting empty...", Toast.LENGTH_SHORT).show();
            cmpHeadLocationEt.setError("Enter your Company Head Office Location");
            return;
        }else {
            cmpHeadLocationStr= cmpHeadLocationEt.getText().toString();
        }

        if (cmpEtIndustry.getText().toString().isEmpty()) {
            Toast.makeText(this, "Industry getting empty...", Toast.LENGTH_SHORT).show();
            cmpEtIndustry.setError("Enter your Company Industry");
            return;
        }else {
            if (cmpEtIndustry.getText().toString().equals("Other")){
                if (cmpEditOtherIndustry.getText().toString().isEmpty()){
                    Toast.makeText(this, "Industry getting empty...", Toast.LENGTH_SHORT).show();
                    cmpEditOtherIndustry.setError("Enter your Company Industry");
                    return;
                }else {
                    cmpIndustryStr=cmpEditOtherIndustry.getText().toString();
                }
            }else {
                cmpIndustryStr=cmpEtIndustry.getText().toString();
            }
        }

        if (cmpCompanyCategory.getText().toString().isEmpty()) {
            Toast.makeText(this, "Company Category is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            CategoryStr = cmpCompanyCategory.getText().toString();
        }

        if (cmpJobTypeSp.getText().toString().isEmpty()) {
            Toast.makeText(this, "Company Type is getting empty...", Toast.LENGTH_SHORT).show();
            return;
        }else {
            CompanyTypeStr = cmpJobTypeSp.getText().toString();
        }

        if (cmpCompanyAddressEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Address getting empty...", Toast.LENGTH_SHORT).show();
            cmpCompanyAddressEt.setError("Enter your Company Address");
            return;
        }else {
            cmpCompanyAddressStr= cmpCompanyAddressEt.getText().toString();
        }

        if (cmpAboutEt.getText().toString().isEmpty()) {
            Toast.makeText(this, "About getting empty...", Toast.LENGTH_SHORT).show();
            cmpAboutEt.setError("Enter About your Company");
            return;
        }else {
            cmpAboutStr= cmpAboutEt.getText().toString();
        }

        databaseInsertOperation();

    }

    private void databaseInsertOperation() {

        Dialog progressDialog=new Dialog(CompanyRegistrationActivity.this);
        progressDialog.setTitle("Inserting Data...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = api.cmpRegisteration(cmpRegId,cmpFullNameStr,cmpContactStr,
                cmpDesignationStr,cmpEmailStr,cmpCompanyNameStr,cmpHeadLocationStr,cmpIndustryStr,CategoryStr,
                CompanyTypeStr,cmpCompanyAddressStr,cmpAboutStr,cmpWebsiteLinkStr,empsSizeStr,stateStr,cityStr);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (!response.body().getError()) {
                    progressDialog.dismiss();
                    SharedPrefManager.getInstance(getApplicationContext()).isRegistered(getApplicationContext(),true);
                    //Toast.makeText(CompanyRegistrationActivity.this, "Update operation Successfully done.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CompanyRegistrationActivity.this, CompanyActivity.class));
                    finish();
                }else{
                    Toast.makeText(CompanyRegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CompanyRegistrationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        dialog.dismiss();
                        quit();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
//        super.onBackPressed();
//        startActivity(new Intent(CompanyRegistrationActivity.this,CompanyActivity.class));
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
}