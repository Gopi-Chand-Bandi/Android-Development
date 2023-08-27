package com.abc.justjob.Company.CompanyPostJob;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoJo2Fragment extends Fragment {

    private TextInputEditText etDescription,etMinSalary,etMaxSalary,etMinAge,etMaxAge,etLocation,etNumLocation;
    private Spinner spSalaryTime;
    private ImageView tbSubmitFragmentThree;


    public PoJo2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo3, container, false);

        etDescription=v.findViewById(R.id.pj_et_description);
        spSalaryTime=v.findViewById(R.id.pj_sp_time_salary);
        etMinSalary=v.findViewById(R.id.pj_et_min_salary);
        etMaxSalary=v.findViewById(R.id.pj_et_max_salary);
        etMinAge=v.findViewById(R.id.pj_et_min_age);
        etMaxAge=v.findViewById(R.id.pj_et_max_age);
        etLocation=v.findViewById(R.id.pj_et_locality);
        etNumLocation=v.findViewById(R.id.pj_et_opening);
        tbSubmitFragmentThree=v.findViewById(R.id.pj_ib_3_submit);

//        PojoFragmentDataSet pojoFragmentDataSet=postJobSharedPref.getInstance(getContext()).getPojoFragment1();
//        Toast.makeText(getContext(), "1St fragment: \n"+pojoFragmentDataSet.getActivityRoleStr()+"\n"
//                +pojoFragmentDataSet.getActivityDesignationStr(), Toast.LENGTH_SHORT).show();

        tbSubmitFragmentThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToInsertFragmentThree();
            }
        });
        return v;
    }

    private void submitToInsertFragmentThree() {
        if (etDescription.getText().toString().isEmpty()) {
            etDescription.setError("Description is Empty...");
        } else if (etMinSalary.getText().toString().isEmpty()) {
            etMinSalary.setError("Min Salary is Empty...");
        } else if (etMaxSalary.getText().toString().isEmpty()) {
            etMaxSalary.setError("Max Salary is Empty...");
        } else if (etMinAge.getText().toString().isEmpty()) {
            etMinAge.setError("Min Age is Empty...");
        } else if (etMaxAge.getText().toString().isEmpty()) {
            etMaxAge.setError("Max Age is Empty...");
        }else if (etLocation.getText().toString().isEmpty()) {
            etLocation.setError("Location is Empty...");
        }else if (etNumLocation.getText().toString().isEmpty()) {
            etNumLocation.setError("Number of Opening is Empty...");
        }else{

            String lastUserId= SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());
            String descriptionStr=etDescription.getText().toString();
            String salaryTimeStr=spSalaryTime.getSelectedItem().toString();
            String minSalaryStr=etMinSalary.getText().toString();
            String maxSalaryStr=etMaxSalary.getText().toString();
            String minAgeStr=etMinAge.getText().toString();
            String maxAgeStr=etMaxAge.getText().toString();
            String locationStr=etLocation.getText().toString();
            String numberLocationStr=etNumLocation.getText().toString();

//            databaseOperation(lastUserId, descriptionStr,salaryTimeStr, minSalaryStr, maxSalaryStr, minAgeStr, maxAgeStr, locationStr, numberLocationStr);

            storeFragment2(descriptionStr,salaryTimeStr, minSalaryStr, maxSalaryStr, minAgeStr, maxAgeStr, locationStr, numberLocationStr);

        }
    }

    private void storeFragment2(String descriptionStr, String salaryTimeStr, String minSalaryStr, String maxSalaryStr, String minAgeStr, String maxAgeStr, String locationStr, String numberLocationStr) {

        postJobSharedPref.getInstance(getContext()).fragment2SharedPref(descriptionStr,salaryTimeStr, minSalaryStr, maxSalaryStr, minAgeStr, maxAgeStr, locationStr, numberLocationStr);

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo3Fragment()).commit();
    }

    private void databaseOperation(String lastUserId, String descriptionStr,String salaryTimeStr, String minSalaryStr, String maxSalaryStr, String minAgeStr, String maxAgeStr, String locationStr, String numberLocationStr) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call= api.cmpPostJobInterfaceThree(lastUserId,descriptionStr,salaryTimeStr,
                minSalaryStr,maxSalaryStr,minAgeStr,maxAgeStr,locationStr,numberLocationStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                                new PoJo3Fragment()).commit();

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
}