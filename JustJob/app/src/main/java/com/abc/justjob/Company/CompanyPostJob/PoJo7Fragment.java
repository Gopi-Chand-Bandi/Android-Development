package com.abc.justjob.Company.CompanyPostJob;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoJo7Fragment extends Fragment {

    private CheckBox cbAadharCard,cbPanCard,cbTwoWheelerDl,cbFourWheelerDl,cbTrDl,cbHeavyDl,cbAddressProof,cbBankAccount;
    private TextInputEditText etOtherDocuments;
    private ImageView ibSubmitFragmentEaght;
    private Spinner spWorkingDay;

    public PoJo7Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_po_jo8, container, false);

        cbAadharCard=v.findViewById(R.id.pj_cb_aadhar_card);
        cbPanCard=v.findViewById(R.id.pj_cb_pan_card);
        cbTwoWheelerDl=v.findViewById(R.id.pj_cb_two_wheeler_dl);
        cbFourWheelerDl=v.findViewById(R.id.pj_cb_four_wheeler_dl);
        cbTrDl=v.findViewById(R.id.pj_cb_tr_dl);
        cbHeavyDl=v.findViewById(R.id.pj_cb_heavy_dl);
        cbAddressProof=v.findViewById(R.id.pj_cb_address_proof);
        cbBankAccount=v.findViewById(R.id.pj_cb_bank_account);
        etOtherDocuments=v.findViewById(R.id.pj_et_other_document);
        spWorkingDay=v.findViewById(R.id.pj_sp_working_day);
        ibSubmitFragmentEaght=v.findViewById(R.id.pj_ib_8_submit);

        ibSubmitFragmentEaght.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToInsert();
            }
        });

        return v;
    }

    private void submitToInsert() {
        ArrayList<String> documentArray=new ArrayList<String>();
        if (cbAadharCard.isChecked()){
            documentArray.add(cbAadharCard.getText().toString());
        }else{
            documentArray.remove(cbAadharCard.getText().toString());
        }
        if (cbPanCard.isChecked()){
            documentArray.add(cbPanCard.getText().toString());
        }else{
            documentArray.remove(cbPanCard.getText().toString());
        }
        if (cbTwoWheelerDl.isChecked()){
            documentArray.add(cbTwoWheelerDl.getText().toString());
        }else{
            documentArray.remove(cbTwoWheelerDl.getText().toString());
        }
        if (cbFourWheelerDl.isChecked()){
            documentArray.add(cbFourWheelerDl.getText().toString());
        }else{
            documentArray.remove(cbFourWheelerDl.getText().toString());
        }
        if (cbTrDl.isChecked()){
            documentArray.add(cbTrDl.getText().toString());
        }else{
            documentArray.remove(cbTrDl.getText().toString());
        }
        if (cbHeavyDl.isChecked()){
            documentArray.add(cbHeavyDl.getText().toString());
        }else{
            documentArray.remove(cbHeavyDl.getText().toString());
        }
        if (cbAddressProof.isChecked()){
            documentArray.add(cbAddressProof.getText().toString());
        }else{
            documentArray.remove(cbAddressProof.getText().toString());
        }
        if (cbBankAccount.isChecked()){
            documentArray.add(cbBankAccount.getText().toString());
        }else{
            documentArray.remove(cbBankAccount.getText().toString());
        }
        if (!etOtherDocuments.getText().toString().isEmpty()){
            documentArray.add(etOtherDocuments.getText().toString());
        }else{
            documentArray.remove(etOtherDocuments.getText().toString());
        }
        String documentsStr="";
        for (String seleted : documentArray){
            documentsStr=documentsStr+seleted+",";
        }

        String workingDayStr=spWorkingDay.getSelectedItem().toString();

        if (documentsStr.isEmpty()) {
            Toast.makeText(getContext(), "Documents is require...!", Toast.LENGTH_SHORT).show();
            return;
        } else if (spWorkingDay.getSelectedItemPosition()==0) {
            Toast.makeText(getContext(), "Working day is require...!", Toast.LENGTH_SHORT).show();
            return;
        }else{

            String lastUserId= SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());

//            databaseOperation(documentsStr, workingDayStr, lastUserId);

            store7Fragment(documentsStr, workingDayStr);
        }
    }

    private void store7Fragment(String documentsStr, String workingDayStr) {

        postJobSharedPref.getInstance(getContext()).fragment7SharedPref(documentsStr, workingDayStr);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo8Fragment()).commit();
    }

    private void databaseOperation(String documentsStr, String workingDayStr, String lastUserId) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call= api.cmpPostJobInterfaceEight(lastUserId,documentsStr,workingDayStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                                new PoJo8Fragment()).commit();
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