package com.abc.justjob.Company.CompanyPostJob;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoJo4Fragment extends Fragment {

    private RadioGroup rgrpQualification,rgrpMaleFemale;
    private ImageView tbSubmitFragmentFive;

    private String maleFemaleStr,qualiStr;

    public PoJo4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo5, container, false);

//        cbBelowSsc = v.findViewById(R.id.pj_cb_below_ssc);
//        cbSscPass = v.findViewById(R.id.pj_cb_ssc_pass);
//        cbHscPass = v.findViewById(R.id.pj_cb_hsc_pass);
//        cbUnderGraduate = v.findViewById(R.id.pj_cb_under_graduate);
//        cbGraduate = v.findViewById(R.id.pj_cb_graduate);
//        cbPostGraduate = v.findViewById(R.id.pj_cb_post_graduate);
        rgrpMaleFemale = v.findViewById(R.id.pj_cb_male_female_radio_grp);
        rgrpQualification=v.findViewById(R.id.pj_cb_quali_radio_grp);
        tbSubmitFragmentFive = v.findViewById(R.id.pj_ib_5_submit);

        getRadioButtonStr();

        tbSubmitFragmentFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButtonIsChecked();
            }
        });

        return v;
    }

    private void getRadioButtonStr() {
        rgrpMaleFemale.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pj_cb_only_male:
                        maleFemaleStr = "Only Male";
                        break;
                    case R.id.pj_cb_only_female:
                        maleFemaleStr = "Only Female";
                        break;
                    case R.id.pj_cb_male_female:
                        maleFemaleStr = "Both Male and Female";
                        break;
                    default:
                        maleFemaleStr = "";
                }
            }
        });
        rgrpQualification.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pj_cb_below_ssc:
                        qualiStr = "Below SSC";
                        break;
                    case R.id.pj_cb_ssc_pass:
                        qualiStr = "SSC Pass";
                        break;
                    case R.id.pj_cb_hsc_pass:
                        qualiStr = "HSC Pass";
                        break;
                    case R.id.pj_cb_under_graduate:
                        qualiStr = "Under Graduate";
                        break;
                    case R.id.pj_cb_graduate:
                        qualiStr = "Graduate";
                        break;
                    case R.id.pj_cb_post_graduate:
                        qualiStr = "Post Graduate";
                        break;
                    default:
                        qualiStr = "";
                }
            }
        });
    }

    private void checkButtonIsChecked() {

        if (qualiStr.isEmpty()) {
            Toast.makeText(getContext(), "Qualification Require...!", Toast.LENGTH_SHORT).show();
            return;
        } else if (maleFemaleStr.isEmpty()) {
            Toast.makeText(getContext(), "Gender Require...!", Toast.LENGTH_SHORT).show();
            return;
        }else {
            String lastUserId = SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());
//            databaseOperation(lastUserId);

            store4Fragment(qualiStr,maleFemaleStr);
        }
    }

    private void store4Fragment(String qualiStr, String maleFemaleStr) {
        postJobSharedPref.getInstance(getContext()).fragment4SharedPref(qualiStr,maleFemaleStr);


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo5Fragment()).commit();
    }

    private void databaseOperation(String lastUserId) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call = api.cmpPostJobInterfaceFive(lastUserId, qualiStr, maleFemaleStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                        //SharedPrefManager.getInstance(getContext()).saveLastId(getContext(),response.body().getLast_id());

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                                new PoJo5Fragment()).commit();

                    } else {
                        Toast.makeText(getContext(), Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
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