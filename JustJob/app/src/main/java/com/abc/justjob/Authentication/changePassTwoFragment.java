package com.abc.justjob.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changePassTwoFragment extends Fragment {

    private String userEmailId;

    private ImageView imageView;
    private TextInputLayout newPasswTv,newRePassTv;
    private Button saveBtn;
    boolean isCandidate;
//    private boolean isDarkMode;

    public changePassTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_pass, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        if (SharedPrefManager.getInstance(getContext()).GetIsCandidate(getContext()).equals("true")) {
            isCandidate=true;
        }else{
            isCandidate=false;
        }


        Bundle bundle=this.getArguments();
        userEmailId = Objects.requireNonNull(bundle).getString("UserEmail");

        imageView=view.findViewById(R.id.change_pass_two_iv_logo);
        newPasswTv=view.findViewById(R.id.new_passw_lay);
        newRePassTv=view.findViewById(R.id.re_enter_new_passw_lay);
        saveBtn=view.findViewById(R.id.submit_valid_btn);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        if (isDarkMode) {
//        } else {
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varificationAction();
            }
        });
    }

    private void varificationAction() {
        String passStr,rePassStr;
        passStr=newPasswTv.getEditText().getText().toString();
        rePassStr=newRePassTv.getEditText().getText().toString();

        if (passStr.isEmpty()) {
            Toast.makeText(getContext(), "Getting Empty password...!", Toast.LENGTH_SHORT).show();
            return;
        }else if (rePassStr.isEmpty()){
            Toast.makeText(getContext(), "Getting Empty Re-Entered Password...!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!rePassStr.matches(passStr)) {
            Toast.makeText(getContext(), "Password is not same...!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            varified(passStr);
        }

    }

    private void varified(String passStr) {
        if (isCandidate) {
            final ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("Verifying...");
            dialog.show();

            ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Result> call=api.changeNewPassCandidate(passStr,userEmailId);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    dialog.dismiss();
                    try {
                        assert response.body() != null;
                        if (!response.body().getError()) {

                            startActivity(new Intent(getContext(),LoginActivity.class));
                            requireActivity().finish();

                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        } else
        {
            final ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("Verifying...");
            dialog.show();

            ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Result> call=api.newPasswordCompany(passStr,userEmailId);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    dialog.dismiss();
                    try {
                        assert response.body() != null;
                        if (!response.body().getError()) {

                            startActivity(new Intent(getContext(),LoginActivity.class));
                            requireActivity().finish();

                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
    }

    /*public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getContext()).GetIsDarkMode(getContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkMode = true;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkMode = false;
        }
    }*/
}