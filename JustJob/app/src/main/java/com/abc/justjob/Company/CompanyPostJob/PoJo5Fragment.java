package com.abc.justjob.Company.CompanyPostJob;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class PoJo5Fragment extends Fragment {

    private RadioGroup cbCommuniRGrp;
    private RadioButton cbCommuniRBtn;
    private TextView cbCommuniTv;
    private CheckBox cbHindi, cbMarathi, cbEnglish, cbUrdu, cbGujrati;
    private TextInputEditText etOtherLanguages;
    private ImageView ibSubmitFragmentSix;

    private String communicationListStr, languageKnowStr;

    private String communicationStr;

    public PoJo5Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo6, container, false);

        cbCommuniRGrp=v.findViewById(R.id.pj_communi_r_grp_edit);
        cbCommuniTv=v.findViewById(R.id.pj_tv_communi_alert);

        cbHindi = v.findViewById(R.id.pj_cb_hindi);
        cbMarathi = v.findViewById(R.id.pj_cb_marathi);
        cbEnglish = v.findViewById(R.id.pj_cb_english);
        cbUrdu = v.findViewById(R.id.pj_cb_urdu);
        cbGujrati = v.findViewById(R.id.pj_cb_gujrati);
        etOtherLanguages = v.findViewById(R.id.pj_et_other_languages);
        ibSubmitFragmentSix = v.findViewById(R.id.pj_ib_6_submit);

        cbCommuniRGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                cbCommuniRBtn=v.findViewById(checkedId);
                communicationStr=cbCommuniRBtn.getText().toString();
                if (cbCommuniRBtn.getId() == R.id.pj_cb_no_engl || cbCommuniRBtn.getId() == R.id.pj_cb_thoda_engl) {
                    cbCommuniTv.setText(R.string.pojo_english_commu_alert_1);
                    cbCommuniTv.setVisibility(View.VISIBLE);
                }else{
                    cbCommuniTv.setText(R.string.pojo_english_commu_alert_2);
                    cbCommuniTv.setVisibility(View.VISIBLE);
                }

            }
        });

        ibSubmitFragmentSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertIntoDatabase();
            }
        });
        return v;
    }

    private void insertIntoDatabase() {

        ArrayList<String> languageArray = new ArrayList<>();
        if (cbHindi.isChecked()) {
            languageArray.add(cbHindi.getText().toString()+",");
        } else {
            languageArray.remove(cbHindi.getText().toString()+",");
        }
        if (cbMarathi.isChecked()) {
            languageArray.add(cbMarathi.getText().toString()+",");
        } else {
            languageArray.remove(cbMarathi.getText().toString()+",");
        }
        if (cbEnglish.isChecked()) {
            languageArray.add(cbEnglish.getText().toString()+",");
        } else {
            languageArray.remove(cbEnglish.getText().toString()+",");
        }
        if (cbUrdu.isChecked()) {
            languageArray.add(cbUrdu.getText().toString()+",");
        } else {
            languageArray.remove(cbUrdu.getText().toString()+",");
        }
        if (cbGujrati.isChecked()) {
            languageArray.add(cbGujrati.getText().toString()+",");
        } else {
            languageArray.remove(cbGujrati.getText().toString()+",");
        }
        if (!etOtherLanguages.getText().toString().isEmpty()) {
            languageKnowStr = etOtherLanguages.getText().toString();
            languageArray.add(languageKnowStr);
        }

        String lastUserId = SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());
        String languageStr = "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            languageStr=(String.join("", languageArray));
        }

//        insertToDatabase(lastUserId, communicationStr, languageStr);

        store5Fragment(communicationStr,languageStr);

//        Toast.makeText(getContext(), communicationStr+" "+languageStr, Toast.LENGTH_SHORT).show();
    }

    private void store5Fragment(String communicationStr, String languageStr) {

        postJobSharedPref.getInstance(getContext()).fragment5SharedPref(communicationStr,languageStr);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo6Fragment()).commit();
    }

    private void insertToDatabase(String lastUserId, String englishKnow, String languageStr) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call = api.cmpPostJobInterfaceSix(lastUserId, englishKnow, languageStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                                new PoJo6Fragment()).commit();

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