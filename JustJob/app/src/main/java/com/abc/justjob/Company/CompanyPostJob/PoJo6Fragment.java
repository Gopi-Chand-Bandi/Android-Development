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

public class PoJo6Fragment extends Fragment {

    private CheckBox cbBike,cbCar;
    private TextInputEditText etOtherVehicle;
    private CheckBox cbIos,cbWindow,cbDesktop;
    private CheckBox cbIPhone,cbAndroid,cbBasic,cbWindowPhRq;
    private ImageView ibSubmitFragmentSeven;


    public PoJo6Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo7, container, false);

        cbBike=v.findViewById(R.id.pj_cb_bike);
        cbCar=v.findViewById(R.id.pj_cb_car);
        etOtherVehicle=v.findViewById(R.id.pj_et_other_vehicle);
        cbIos=v.findViewById(R.id.pj_cb_ios);
        cbWindow=v.findViewById(R.id.pj_cb_Window);
        cbDesktop=v.findViewById(R.id.pj_cb_desktop);
        cbIPhone=v.findViewById(R.id.pj_cb_iphone);
        cbAndroid=v.findViewById(R.id.pj_cb_android);
        cbBasic=v.findViewById(R.id.pj_cb_basic);
        cbWindowPhRq=v.findViewById(R.id.pj_cb_window_ph_rc);
        ibSubmitFragmentSeven=v.findViewById(R.id.pj_ib_7_submit);


        ibSubmitFragmentSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitToDatabase();
            }
        });

        return v;
    }

    private void submitToDatabase() {

        ArrayList<String> vehicleArray=new ArrayList<String>();
        if (cbBike.isChecked()){
            vehicleArray.add(cbBike.getText().toString());
        }else{
            vehicleArray.remove(cbBike.getText().toString());
        }
        if (cbCar.isChecked()) {
            vehicleArray.add(cbCar.getText().toString());
        }else{
            vehicleArray.remove(cbCar.getText().toString());
        }
        if (!etOtherVehicle.getText().toString().isEmpty()) {
            vehicleArray.add(etOtherVehicle.getText().toString());
        }
        String vehicleStr="";
        for (String seleted : vehicleArray){
            vehicleStr=vehicleStr+seleted+",";
        }

        ArrayList<String> laptopRequireArray=new ArrayList<String>();
        if (cbIos.isChecked()) {
            laptopRequireArray.add(cbIos.getText().toString());
        }else{
            laptopRequireArray.remove(cbIos.getText().toString());
        }
        if (cbWindow.isChecked()) {
            laptopRequireArray.add(cbWindow.getText().toString());
        }else{
            laptopRequireArray.remove(cbWindow.getText().toString());
        }
        if (cbDesktop.isChecked()) {
            laptopRequireArray.add(cbDesktop.getText().toString());
        }else{
            laptopRequireArray.remove(cbDesktop.getText().toString());
        }
        String laptopRequireStr="";
        for (String seleted : laptopRequireArray){
            laptopRequireStr=laptopRequireStr+seleted+",";
        }

        ArrayList<String> phoneRequireArray=new ArrayList<String>();
        if (cbIPhone.isChecked()) {
            phoneRequireArray.add(cbIPhone.getText().toString());
        }else{
            phoneRequireArray.remove(cbIPhone.getText().toString());
        }
        if (cbAndroid.isChecked()) {
            phoneRequireArray.add(cbAndroid.getText().toString());
        }else{
            phoneRequireArray.remove(cbAndroid.getText().toString());
        }
        if (cbBasic.isChecked()) {
            phoneRequireArray.add(cbBasic.getText().toString());
        }else{
            phoneRequireArray.remove(cbBasic.getText().toString());
        }
        if (cbWindowPhRq.isChecked()) {
            phoneRequireArray.add(cbWindowPhRq.getText().toString());
        }else{
            phoneRequireArray.remove(cbWindowPhRq.getText().toString());
        }
        String phoneRequireStr="";
        for (String seleted : phoneRequireArray){
            phoneRequireStr=phoneRequireStr+seleted+",";
        }

        String vehiNewStr,laptopNewStr,phoneNewStr;

        if (vehicleStr.isEmpty()) {
            vehiNewStr="--";
        } else{
            vehiNewStr=vehicleStr;
        }
        if (laptopRequireStr.isEmpty()) {
            laptopNewStr="--";
        } else{
            laptopNewStr=laptopRequireStr;
        }
        if (phoneRequireStr.isEmpty()) {
            phoneNewStr="--";
        }else {
            phoneNewStr = phoneRequireStr;
        }

            String lastUserId = SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());

//            databaseOperation(vehiNewStr,laptopNewStr,phoneNewStr, lastUserId);

            store6Fragment(vehiNewStr,laptopNewStr,phoneNewStr);

    }

    private void store6Fragment(String vehiNewStr, String laptopNewStr, String phoneNewStr) {

        postJobSharedPref.getInstance(getContext()).fragment6SharedPref(vehiNewStr,laptopNewStr,phoneNewStr);

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo7Fragment()).commit();

    }

    private void databaseOperation(String vehicleStr, String laptopRequireStr, String phoneRequireStr, String lastUserId) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call= api.cmpPostJobInterfaceSeven(lastUserId,vehicleStr,laptopRequireStr,phoneRequireStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                                new PoJo7Fragment()).commit();
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