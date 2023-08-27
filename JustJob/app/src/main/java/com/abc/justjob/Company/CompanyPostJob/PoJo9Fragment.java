package com.abc.justjob.Company.CompanyPostJob;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoJo9Fragment extends Fragment {

    private CheckBox cbTravalling,cbMobile,cbDailyAllowance;
    private TextInputEditText etOtherReimbursement,etIncentivesDetails;
    private CheckBox cbForYourCmp;

    private LinearLayout depostLayout;
    private RadioGroup rGrpDeposit;
    private boolean deposetClicked=false;
    private RadioButton rBtnDeposit;
    private String depositingOrNot;
    private TextInputLayout etDepositAmount,etDepositPurpose;

    private ImageView ibSubmitFragmentTen;
    private Button btnSubmitFragmentTen;

    public PoJo9Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo10, container, false);

        cbTravalling=v.findViewById(R.id.pj_cb_travelling);
        cbMobile=v.findViewById(R.id.pj_cb_mobile);
        cbDailyAllowance=v.findViewById(R.id.pj_cb_daily_allowance);
        etOtherReimbursement=v.findViewById(R.id.pj_et_other_reimbursement);
        etIncentivesDetails=v.findViewById(R.id.pj_et_incentives);

        depostLayout=v.findViewById(R.id.pj_et_deposit_layout);
        rGrpDeposit=v.findViewById(R.id.pj_deposit_r_grp);
        etDepositAmount=v.findViewById(R.id.pj_et_deposit_amount);
        etDepositPurpose=v.findViewById(R.id.pj_et_deposit_purpose);

        cbForYourCmp=v.findViewById(R.id.pj_cb_for_your_cmp);
        ibSubmitFragmentTen=v.findViewById(R.id.pj_ib_10_submit);
        btnSubmitFragmentTen=v.findViewById(R.id.pj_btn_10_submit);

        rGrpDeposit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.pj_rb_deposit_yes) {
                    depostLayout.setVisibility(View.VISIBLE);
                }else{
                    depostLayout.setVisibility(View.GONE);
                }
                deposetClicked=true;
                rBtnDeposit=rGrpDeposit.findViewById(checkedId);
                depositingOrNot=rBtnDeposit.getText().toString();
            }
        });

        cbForYourCmp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ibSubmitFragmentTen.setVisibility(View.GONE);
                    btnSubmitFragmentTen.setVisibility(View.VISIBLE);
                }else{
                    ibSubmitFragmentTen.setVisibility(View.VISIBLE);
                    btnSubmitFragmentTen.setVisibility(View.GONE);
                }
            }
        });

        btnSubmitFragmentTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToInsert();
            }
        });

        ibSubmitFragmentTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToInsert();
            }
        });

        return v;
    }

    private void submitToInsert() {

        ArrayList<String> reimbursementArray=new ArrayList<String>();
        if (cbTravalling.isChecked()){
            reimbursementArray.add(cbTravalling.getText().toString());
        }else{
            reimbursementArray.remove(cbTravalling.getText().toString());
        }
        if (cbMobile.isChecked()) {
            reimbursementArray.add(cbMobile.getText().toString());
        }else{
            reimbursementArray.remove(cbMobile.getText().toString());
        }
        if (cbDailyAllowance.isChecked()) {
            reimbursementArray.add(cbDailyAllowance.getText().toString());
        }else{
            reimbursementArray.remove(cbDailyAllowance.getText().toString());
        }
        if (!etOtherReimbursement.getText().toString().isEmpty()) {
            reimbursementArray.add(etOtherReimbursement.getText().toString());
        }
        String reimbursementStr="";
        for (String seleted : reimbursementArray){
            reimbursementStr=reimbursementStr+seleted+",";
        }

        String reimStr,incetStr,amountStr,amountPurposeStr;

        if (etDepositAmount.getEditText().getText().toString().isEmpty()) {
            amountStr="--";
        }else{
            amountStr=etDepositAmount.getEditText().getText().toString();
        }
        if (etDepositPurpose.getEditText().getText().toString().isEmpty()) {
            amountPurposeStr="--";
        }else{
            amountPurposeStr=etDepositPurpose.getEditText().getText().toString();
        }

        if (reimbursementStr.isEmpty()) {
            reimStr="--";
        }else{
            reimStr=reimbursementStr;
        }
        if (etIncentivesDetails.getText().toString().isEmpty()){
            incetStr="--";
        }else {
            incetStr = etIncentivesDetails.getText().toString();
        }

        if (!deposetClicked) {
            Toast.makeText(getContext(), "Deposit or not is require...!", Toast.LENGTH_SHORT).show();
            return;
        }

        String lastUserId= SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());

//        Toast.makeText(getContext(),lastUserId+"\n"+reimStr+"\n"+incetStr+"\n"+depositingOrNot+"\n"+amountStr+"\n"+amountPurposeStr , Toast.LENGTH_SHORT).show();

//        databaseOperation(reimStr,incetStr,lastUserId,depositingOrNot,amountStr,amountPurposeStr);

        store9Fragment(reimStr,incetStr,depositingOrNot,amountStr,amountPurposeStr);
    }

    private void store9Fragment(String reimStr, String incetStr, String depositingOrNot, String amountStr, String amountPurposeStr) {

        postJobSharedPref.getInstance(getContext()).fragment9SharedPref(reimStr,incetStr,depositingOrNot,amountStr,amountPurposeStr);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo10Fragment()).commit();
    }

    private void databaseOperation(String reimStr, String incetStr, String lastUserId, String depositingOrNot, String amountStr, String amountPurposeStr) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call= api.cmpPostJobInterfaceTen(lastUserId,reimStr,incetStr,depositingOrNot,amountStr,amountPurposeStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        //SharedPrefManager.getInstance(getContext()).saveLastId(getContext(),response.body().getLast_id());

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                                new PoJo10Fragment()).commit();
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