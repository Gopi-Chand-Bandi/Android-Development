package com.abc.justjob.Company.CompanyPostJob;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class PoJo3Fragment extends Fragment {

    private CheckBox cbFresherCan, cbExpericence;
    private LinearLayout expeLayout;
    private TextInputEditText etMinExp, etMaxExp;
    private ImageView tbSubmitFragmentFour;
    private boolean fresherCanBo, experienceCanBo;

    public PoJo3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo4, container, false);

        cbFresherCan = v.findViewById(R.id.pj_cb_fresher_can);
        cbExpericence = v.findViewById(R.id.pj_cb_experience_can);
        expeLayout = v.findViewById(R.id.pojo_exp_layout);
        etMinExp = v.findViewById(R.id.pj_et_min_exp);
        etMaxExp = v.findViewById(R.id.pj_max_exp);
        tbSubmitFragmentFour = v.findViewById(R.id.pj_ib_4_submit);

        cbFresherCan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fresherCanBo = true;
                } else {
                    fresherCanBo = false;
                }
            }
        });

        cbExpericence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    expeLayout.setVisibility(View.VISIBLE);
                    experienceCanBo = true;
                } else {
                    expeLayout.setVisibility(View.GONE);
                    experienceCanBo = false;
                }
            }
        });

        tbSubmitFragmentFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToInsertFragmentFour();
            }
        });

        return v;
    }

    private void submitToInsertFragmentFour() {
        String fresherCanStr;
        if (fresherCanBo) {
            fresherCanStr = "True";
        } else {
            fresherCanStr = "False";
        }


        if (fresherCanStr.isEmpty()) {
            Toast.makeText(getContext(), "Check Box is not selected", Toast.LENGTH_SHORT).show();
            return;
        } else if (experienceCanBo && (etMinExp.getText().toString().isEmpty() || etMaxExp.getText().toString().isEmpty())) {
            Toast.makeText(getContext(), "Experience is getting empty", Toast.LENGTH_SHORT).show();
            return;
        } else {

            String lastUserId = SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());
            String minExpStr = etMinExp.getText().toString();
            String maxExpStr = etMaxExp.getText().toString();
            String expCanStr = String.valueOf(experienceCanBo);

//            insertToDatabase(lastUserId,fresherCanStr,expCanStr, minExpStr, maxExpStr);

            store3Fragment(fresherCanStr,expCanStr, minExpStr, maxExpStr);
        }
    }

    private void store3Fragment(String fresherCanStr, String expCanStr, String minExpStr, String maxExpStr) {

        postJobSharedPref.getInstance(getContext()).fragment3SharedPref(fresherCanStr,expCanStr, minExpStr, maxExpStr);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo4Fragment()).commit();

    }

    private void insertToDatabase( String lastUserId,String fresherCanStr,String expCanStr, String minExpStr, String maxExpStr) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call = api.cmpPostJobInterfaceFour(lastUserId, fresherCanStr,expCanStr, minExpStr, maxExpStr);

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
                                new PoJo4Fragment()).commit();

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