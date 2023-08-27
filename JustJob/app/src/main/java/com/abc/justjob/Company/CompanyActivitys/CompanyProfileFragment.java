package com.abc.justjob.Company.CompanyActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.CompanyFetch.companyProfileResult;
import com.abc.justjob.Candidate.CandidateActivityFragment.CandidateActivity;
import com.abc.justjob.Company.TransectionHistory.transectionActivity;
import com.abc.justjob.MainActivity;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyProfileFragment extends Fragment {

    private TextView editCmp,postedJobCount,transactionTv,trackerTv,dataViewedCount,contactUs,feedbackCmp,signout_cmp;
    private RelativeLayout postedJobLayout,dataViewLayout;

    private TextView cmpNameTv,cmpEmailTv,cmpCompanyNameTv,cmpDesignationTv,cmpHeadLocationTv,
            cmpIndustryTv,cmpJobTypeTv,cmpCompanyAboutTv;

    private Context context;
    private String UserId;

    private SwitchCompat themeSwitch;

    private String countStr,cmpFNameStr,cmpContactStr,cmpDesignationStr,cmpEmailStr,cmpCompanyNameStr,cmpOfficeLocationStr,
            cmpIndustryStr,cmpCompanyCatagoryStr,cmpJobTypeStr,cmpCompanyAddressStr,cmpAboutCompanyStr;

    public CompanyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_profile, container, false);

        context=getActivity().getApplicationContext();

        UserId=SharedPrefManager.getInstance(context).getValueOfUserId(context);
        themeSwitch=view.findViewById(R.id.cmp_profile_change_theme);

//        checkNightModeActivated();

        cmpNameTv=view.findViewById(R.id.cmp_profile_name_tv);
        cmpEmailTv=view.findViewById(R.id.cmp_profile_email_tv);
        cmpCompanyNameTv=view.findViewById(R.id.cmp_profile_company_name);
        cmpDesignationTv=view.findViewById(R.id.cmp_profile_company_designation);
        cmpHeadLocationTv=view.findViewById(R.id.cmp_profile_company_head_location);
        cmpIndustryTv=view.findViewById(R.id.cmp_profile_company_industry);
        cmpJobTypeTv=view.findViewById(R.id.cmp_profile_company_job_type);
        cmpCompanyAboutTv=view.findViewById(R.id.cmp_profile_company_about);

        editCmp=view.findViewById(R.id.edit_company_profile);
        postedJobLayout=view.findViewById(R.id.relative_posted_job);
        postedJobCount=view.findViewById(R.id.posted_job_count);
        transactionTv=view.findViewById(R.id.transaction_comp_profile);
        trackerTv=view.findViewById(R.id.tracker_comp_profile);
        dataViewLayout=view.findViewById(R.id.relative_data_view);
        dataViewedCount=view.findViewById(R.id.data_view_count);
        contactUs=view.findViewById(R.id.contact_comp_profile);
        feedbackCmp=view.findViewById(R.id.feedback_cmp);
        signout_cmp=view.findViewById(R.id.signout_cmp);


        fetchDataFromDatabase();

//        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    SharedPrefManager.getInstance(getContext()).isDarkMode(true);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    restartActivity();
//                }else{
//                    SharedPrefManager.getInstance(getContext()).isDarkMode(false);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    restartActivity();
//                }
//            }
//        });

        editCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToEditForComnpany();
            }
        });
        postedJobCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MyCandidateActivity.class));
            }
        });
        transactionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Not Active For Now.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), transectionActivity.class));
            }
        });
        trackerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),PaymentTrackerActivity.class));
            }
        });
        feedbackCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),FeedbackActivity.class);
                startActivity(intent);
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CmpContactUsActivity.class));
            }
        });
        signout_cmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(context).logout();
                startActivity(new Intent(context, MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

    /*public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getContext()).GetIsDarkMode(getContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            themeSwitch.setChecked(true);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            themeSwitch.setChecked(false);
        }
    }*/

    private void restartActivity() {
        startActivity(new Intent(getContext(), CompanyActivity.class));
        getActivity().finish();
    }

    private void jumpToEditForComnpany() {

        Bundle bundle=new Bundle();
        bundle.putString("cm_reg_id",UserId);
        bundle.putString("cmp_f_name",cmpFNameStr);
        bundle.putString("cmp_contact",cmpContactStr);
        bundle.putString("cmp_designation",cmpDesignationStr);
        bundle.putString("cmp_email",cmpEmailStr);
        bundle.putString("cmp_company_name",cmpCompanyNameStr);
        bundle.putString("cmp_head_location",cmpOfficeLocationStr);
        bundle.putString("cmp_industry",cmpIndustryStr);
        bundle.putString("cmp_company_category",cmpCompanyCatagoryStr);
        bundle.putString("cmp_job_type",cmpJobTypeStr);
        bundle.putString("cmp_company_address",cmpCompanyAddressStr);
        bundle.putString("cmp_about",cmpAboutCompanyStr);

        Intent intent=new Intent(getContext(),CmpEditProfileActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void fetchDataFromDatabase() {

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<companyProfileResult> call = api.cmpProfileFetch(UserId);

        call.enqueue(new Callback<companyProfileResult>() {
            @Override
            public void onResponse(Call<companyProfileResult> call, Response<companyProfileResult> response) {
                try {
                    if (!response.body().isError()) {

                        countStr=response.body().getPosted_job_number();
                        cmpFNameStr=response.body().getCmp_full_name();
                        cmpContactStr=response.body().getCmp_contact();
                        cmpDesignationStr=response.body().getCmp_designation();
                        cmpEmailStr=response.body().getCmp_email();
                        cmpCompanyNameStr=response.body().getCmp_company_name();
                        cmpOfficeLocationStr=response.body().getCmp_head_office_location();
                        cmpIndustryStr=response.body().getCmp_industry();
                        cmpCompanyCatagoryStr=response.body().getCmp_company_category();
                        cmpJobTypeStr=response.body().getCmp_job_type();
                        cmpCompanyAddressStr=response.body().getCmp_company_address();
                        cmpAboutCompanyStr=response.body().getCmp_about_company();


                        setDataFromDatabase();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<companyProfileResult> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataFromDatabase() {
        postedJobCount.setText(countStr);
        cmpNameTv.setText(cmpFNameStr);
        cmpEmailTv.setText(cmpEmailStr);
        cmpCompanyNameTv.setText(cmpCompanyNameStr);
        cmpDesignationTv.setText(cmpDesignationStr);
        cmpHeadLocationTv.setText(cmpOfficeLocationStr);
        cmpIndustryTv.setText(cmpIndustryStr);
        cmpJobTypeTv.setText(cmpJobTypeStr);
        cmpCompanyAboutTv.setText(cmpAboutCompanyStr);
    }
}
/*
public class CompanyProfileFragment extends Fragment
{
    private TextView editCmp,postedJobCount,transactionTv,dataViewedCount,contactUs,feedbackCmp,signout_cmp;
    private RelativeLayout postedJobLayout,dataViewLayout;

    private TextView cmpNameTv,cmpEmailTv,cmpCompanyNameTv,cmpDesignationTv,cmpHeadLocationTv,
            cmpIndustryTv,cmpJobTypeTv,cmpCompanyAboutTv;

    private Context context;
    private String UserId;

    public CompanyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_profile, container, false);

        context=getActivity().getApplicationContext();

        cmpNameTv=view.findViewById(R.id.cmp_profile_name_tv);
        cmpEmailTv=view.findViewById(R.id.cmp_profile_email_tv);
        cmpCompanyNameTv=view.findViewById(R.id.cmp_profile_company_name);
        cmpDesignationTv=view.findViewById(R.id.cmp_profile_company_designation);
        cmpHeadLocationTv=view.findViewById(R.id.cmp_profile_company_head_location);
        cmpIndustryTv=view.findViewById(R.id.cmp_profile_company_industry);
        cmpJobTypeTv=view.findViewById(R.id.cmp_profile_company_job_type);
        cmpCompanyAboutTv=view.findViewById(R.id.cmp_profile_company_about);

        editCmp=view.findViewById(R.id.edit_company_profile);
        postedJobLayout=view.findViewById(R.id.relative_posted_job);
        postedJobCount=view.findViewById(R.id.posted_job_count);
        transactionTv=view.findViewById(R.id.transaction_comp_profile);
        dataViewLayout=view.findViewById(R.id.relative_data_view);
        dataViewedCount=view.findViewById(R.id.data_view_count);
        contactUs=view.findViewById(R.id.contact_comp_profile);
        feedbackCmp=view.findViewById(R.id.feedback_cmp);
        signout_cmp=view.findViewById(R.id.signout_cmp);

        UserId=SharedPrefManager.getInstance(context).getValueOfUserId(context);

        fetchDataFromDatabase();

        editCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,CompanyRegistrationActivity.class));
            }
        });
        postedJobCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MyCandidateActivity.class));
            }
        });
        transactionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not Active For Now.", Toast.LENGTH_SHORT).show();
            }
        });
        feedbackCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),FeedbackActivity.class);
                startActivity(intent);
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),CmpContactUsActivity.class));
            }
        });
        signout_cmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(context).logout();
                startActivity(new Intent(context, MainActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }

    private void fetchDataFromDatabase() {

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<companyProfileResult> call = api.cmpProfileFetch(UserId);

        call.enqueue(new Callback<companyProfileResult>() {
            @Override
            public void onResponse(Call<companyProfileResult> call, Response<companyProfileResult> response) {
                try {
                    if (!response.body().isError()) {
                        postedJobCount.setText(response.body().getPosted_job_number());
                        cmpNameTv.setText(response.body().getCmp_full_name());
                        cmpEmailTv.setText(response.body().getCmp_email());
                        cmpCompanyNameTv.setText(response.body().getCmp_company_name());
                        cmpDesignationTv.setText(response.body().getCmp_designation());
                        cmpHeadLocationTv.setText(response.body().getCmp_head_office_location());
                        cmpIndustryTv.setText(response.body().getCmp_industry());
                        cmpJobTypeTv.setText(response.body().getCmp_job_type());
                        cmpCompanyAboutTv.setText(response.body().getCmp_about_company());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<companyProfileResult> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}*/