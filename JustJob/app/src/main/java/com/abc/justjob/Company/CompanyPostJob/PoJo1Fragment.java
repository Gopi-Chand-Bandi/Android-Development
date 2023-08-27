package com.abc.justjob.Company.CompanyPostJob;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoJo1Fragment extends Fragment {


    private Dialog dialog;
    private ArrayList<String> roleList,cityList;

    String[] spinnerStrArray;
//    private TextInputEditText etTitle;
    private TextInputLayout etOtherRole;
    private TextView spRole,spCity;
    private AppCompatSpinner spDesignation,spType;
    private AutoCompleteTextView acState;
    private ImageView ibSubmitFragmentOne;
//    private String[] itemIndustryList,itemsRoleList,itemTypeList;
//    private String jobIndustryStr,jobRoleStr,jobTypeStr;
//    private ArrayAdapter jobInductryAdapter,jobRoleAdapter,jobTypeAdapter;

    private Context context;
    private String cmpRegisterId;

    public PoJo1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo1, container, false);

        context=getActivity().getApplicationContext();
        try {
            cmpRegisterId= SharedPrefManager.getInstance(context).getValueOfUserId(context);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        etTitle=v.findViewById(R.id.pj_et_title);
//        spIndustry=v.findViewById(R.id.pj_sp_industry);
//        etOtherIndustry=v.findViewById(R.id.pj_et_other_industry);
        spRole=v.findViewById(R.id.pj_sp_role);
        etOtherRole=v.findViewById(R.id.pj_et_other_role);
        spDesignation=v.findViewById(R.id.pj_sp_designation);
        spType=v.findViewById(R.id.pj_sp_type);
        acState=v.findViewById(R.id.pj_ac_state);
        spCity=v.findViewById(R.id.pj_ac_city);
        ibSubmitFragmentOne=v.findViewById(R.id.pj_ib_1_submit);

        spinnerStrArray=getResources().getStringArray(R.array.job_industry_list);

//        itemIndustryList=getResources().getStringArray(R.array.job_industry_list);
//        jobInductryAdapter= new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,itemIndustryList);
//        jobInductryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spIndustry.setAdapter(jobInductryAdapter);

//        itemTypeList=getResources().getStringArray(R.array.Job_Type);
//        jobTypeAdapter= new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,itemTypeList);
//        jobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spType.setAdapter(jobTypeAdapter);

        roleList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

        spRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roleOperation();
            }
        });
        cityList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.indian_cities)));
        spCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityOperation();
            }
        });

        ibSubmitFragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToInsertTitle();
            }
        });

        ArrayAdapter<String> adapter4State =new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, stateStr);
        acState.setAdapter(adapter4State);

        return v;
    }

    private void cityOperation() {
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
                    spCity.setText(itemsAdapter.getItem(position));
                    dialog.dismiss();
                }
            }
        });
    }

    private void roleOperation() {

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
                        android.R.layout.simple_list_item_1, roleList);
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

                if (itemsAdapter.getItem(position).equals("Other")) {
                    spRole.setText("");
                    etOtherRole.setVisibility(View.VISIBLE);
                } else {
                    spRole.setText(itemsAdapter.getItem(position));
                    etOtherRole.setVisibility(View.GONE);
                }
                dialog.dismiss();
            }
        });
        etOtherRole.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                spRole.setText("");
                spRole.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void submitToInsertTitle() {
        /*if (etTitle.getText().toString().isEmpty()){
            etTitle.setError("Job Title is Empty");
        } else*/ if (spRole.getText().toString().equals("Job Role") ||
                spRole.getText().toString().equals("Select Job Profile") ||
                spRole.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Job Role is empty...!", Toast.LENGTH_SHORT).show();
        } else if (spDesignation.getSelectedItemPosition()==0) {
            Toast.makeText(context, "Designation is empty...!", Toast.LENGTH_SHORT).show();
        } else if (spType.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "Job Type is empty...!", Toast.LENGTH_SHORT).show();
        } else if (acState.getText().toString().isEmpty()) {
            acState.setError("State is empty...!");
        } else if (spCity.getText().toString().equals("Select City") ||
                spCity.getText().toString().equals("City")) {
            acState.setError("City is empty...!");
        } else {
            String titleStr, roleStr,designationStr, typeStr, stateStr, cityStr;

//            titleStr = etTitle.getText().toString();
            titleStr="--";
            roleStr=spRole.getText().toString();
//            String industryStr = spIndustry.getSelectedItem().toString();

//            if (rolePosition == 1) {
//                roleStr = etOtherRole.getEditText().getText().toString();
//            } else {
//                roleStr = spRole.getText().toString();
//            }

            designationStr=spDesignation.getSelectedItem().toString();
            typeStr = spType.getSelectedItem().toString();
            stateStr = acState.getText().toString();
            cityStr=spCity.getText().toString();
//            Toast.makeText(context, cityStr, Toast.LENGTH_SHORT).show();

//            databaseOperation(titleStr, roleStr,designationStr, typeStr, stateStr,cityStr);

            storeData1Fragment(titleStr, roleStr, designationStr, typeStr, stateStr, cityStr);
        }
    }

    private void storeData1Fragment(String titleStr, String roleStr, String designationStr, String typeStr, String stateStr, String cityStr) {

        postJobSharedPref.getInstance(getContext()).fragment1SharedPref(titleStr, roleStr, designationStr, typeStr, stateStr, cityStr);

//        PostJobActivity postJobActivity=new PostJobActivity();
//        postJobActivity.fragment1(titleStr, roleStr, designationStr, typeStr, stateStr, cityStr);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo2Fragment()).commit();
    }

    private void databaseOperation(String titleStr, String roleStr,String designationStr, String typeStr, String stateStr, String cityStr) {
        //defining a progress dialog to show while signing up
        final Dialog progressDialog = new Dialog(getActivity());
        progressDialog.setTitle("Inserting...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call= api.cmpPostJobInterfaceOne(cmpRegisterId,titleStr,roleStr,designationStr,typeStr,stateStr,cityStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                        SharedPrefManager.getInstance(getContext()).saveLastId(getContext(),response.body().getLast_id());
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.post_job_fragments_container,
                                new PoJo2Fragment()).commit();

                    }else{
                        Toast.makeText(getContext(), Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();
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

    private static final String[] stateStr =
            new String[] { "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana",
                    "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra",
                    "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
                    "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh",
                    "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry"
            };
}