package com.abc.justjob.Authentication;

import android.app.ProgressDialog;
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

public class changePassOneFragment extends Fragment {

    private ImageView imageView;
    private TextInputLayout useraNameEt, emailEt;
    private Button checkValidBtn;

    boolean isCandidate;
//    private boolean isDarkMode;

    public changePassOneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_valid_user4_new_pass, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        if (SharedPrefManager.getInstance(getContext()).GetIsCandidate(getContext()).equals("true")) {
            isCandidate = true;
        } else {
            isCandidate = false;
        }


        imageView = view.findViewById(R.id.change_pass_one_iv_logo);
        useraNameEt = view.findViewById(R.id.user_name_valid_lay);
        emailEt = view.findViewById(R.id.email_valid_lay);
        checkValidBtn = view.findViewById(R.id.check_valid_btn);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        if (isDarkMode) {
//        } else {
//            imageView.setImageDrawable(getResources().getDrawable(R.drawable.home_page_icon));
//        }

        checkValidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varification();
            }
        });
    }

    private void varification() {
        String userNameStr, emailStr;
        userNameStr = useraNameEt.getEditText().getText().toString();
        emailStr = emailEt.getEditText().getText().toString();

        if (userNameStr.isEmpty()) {
            Toast.makeText(getContext(), "User name is getting empty...!", Toast.LENGTH_SHORT).show();
            return;
        } else if (emailStr.isEmpty()) {
            Toast.makeText(getContext(), "E-mail is getting empty...!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            varifiedEmailPassword(userNameStr, emailStr);
        }
    }

    private void varifiedEmailPassword(String userNameStr, String emailStr) {
        if (isCandidate) {
            final ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("Verifying...");
            dialog.show();

            ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Result> call = api.getValidCandidate(userNameStr, emailStr);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    dialog.dismiss();
                    try {
                        assert response.body() != null;
                        if (!response.body().getError()) {

                            changePassTwoFragment fragment = new changePassTwoFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("UserEmail", emailStr);
                            fragment.setArguments(bundle);

                            requireActivity().getSupportFragmentManager().
                                    beginTransaction().add(R.id.change_pass_fragments_container, fragment).commit();

                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
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
        } else {
            final ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("Verifying...");
            dialog.show();

            ApiInterface api = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Result> call = api.getValidCompany(userNameStr, emailStr);

            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    dialog.dismiss();
                    try {
                        assert response.body() != null;
                        if (!response.body().getError()) {

                            changePassTwoFragment fragment = new changePassTwoFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("UserEmail", emailStr);
                            fragment.setArguments(bundle);

                            requireActivity().getSupportFragmentManager().
                                    beginTransaction().add(R.id.change_pass_fragments_container, fragment).commit();

                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
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