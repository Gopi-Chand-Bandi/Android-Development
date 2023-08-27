package com.abc.justjob.Candidate.CandidateActivityFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.CandidateFetchCompany.cndProfileResult;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Company.CompanyActivitys.FeedbackActivity;
import com.abc.justjob.MainActivity;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.abc.justjob.VeiwResumeActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    //private CircularImageView profileImage;
    private TextView profileUserName,profileUserEmail,profileGotoResume;
    private TextView profileApplied,profileShortlisted,profileIntervies,profileRejected;
    private ProgressBar profileProgress;
    private TextView profileProgressText,profileEditComplited,profileEditClickable;
    private TextView profileRecruites30,profileRecruites90;
    private TextView profileSave,profileResume,profileHelp,profileFeedback,interViewTipTv,profileSignOut;

    private RelativeLayout profileSkillView;
    private TextView goToProfileView;

    private String userId;

    private TextView editCndProfileTv,skillTv,qualificationTv,experienceTv,jobProfileTv,stateTv,locationTv,genderTv,dobTv,contactTv;

    private SwitchCompat themeSwitch;

    private String cdGender,cdFullName,cdEmail,cdContact,cdAlterContact,cdState,cdCity,cdCurrentLocation,cdDateOfBirth,cdCommunication,
            cdJobProfileOne,cdJobDesignationOne,cdJobProfileTwo,cdJobDesignationTwo,cdQualificationStd,cdQualificationStream,cdCollegeName,cdQualificationStartDate,
            cdQualificatoinEndDate,cdFresherInternExp,cdExpJobIndustry,cdExpJobRole,cdExpCompanyName,cdExpCurrentSalary,cdExpDesignation,
            cdExpStartDate,cdExpEndDate,cdLanguage, cdVehicle,cdLicence,cdDocuments,cdSkill,cdReference,resumeUrl,extensionStr;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

       // profileImage=view.findViewById(R.id.circle_image_profile);
        profileUserName=view.findViewById(R.id.profile_user_name);
        profileUserEmail=view.findViewById(R.id.profile_user_email);
        goToProfileView=view.findViewById(R.id.go_to_profile_view);

        editCndProfileTv=view.findViewById(R.id.cnd_profile_edit_tv);

        skillTv=view.findViewById(R.id.cnd_profile_skills);
        qualificationTv=view.findViewById(R.id.cnd_profile_qualifications);
        experienceTv=view.findViewById(R.id.cnd_profile_experience);
        //communicationTv=view.findViewById(R.id.cnd_profile_communication);
        // languagesTv=view.findViewById(R.id.cnd_profile_languages);
        // vehicleTv=view.findViewById(R.id.cnd_profile_vehicles);
        jobProfileTv=view.findViewById(R.id.cnd_profile_job_profile);
//        stateTv=view.findViewById(R.id.cnd_profile_state);
        locationTv=view.findViewById(R.id.cnd_profile_location);
        genderTv=view.findViewById(R.id.cnd_profile_gender);
        dobTv=view.findViewById(R.id.cnd_profile_dob);
        contactTv=view.findViewById(R.id.cnd_profile_contact);


        themeSwitch=view.findViewById(R.id.profile_change_theme);

        /*profileShortlisted=view.findViewById(R.id.profile_shortlist_number);
        profileIntervies=view.findViewById(R.id.profile_interviews_number);
        profileRejected=view.findViewById(R.id.profile_rejected_number);
        profileApplied=view.findViewById(R.id.profile_applied_number);

        profileProgress=view.findViewById(R.id.profile_progressbar);
        profileProgressText=view.findViewById(R.id.profile_progress_text);
        profileEditComplited=view.findViewById(R.id.profile_edit_complited);
        profileEditClickable=view.findViewById(R.id.profile_edit_clickable);

        profileRecruites30=view.findViewById(R.id.profile_recruites_30);
        profileRecruites90=view.findViewById(R.id.profile_recruites_90);*/

        profileEditClickable=view.findViewById(R.id.go_to_profile_view);
        profileSave=view.findViewById(R.id.profile_save);
        profileResume=view.findViewById(R.id.profile_resume);
        profileHelp=view.findViewById(R.id.profile_help);
        profileFeedback=view.findViewById(R.id.profile_feedback);
        interViewTipTv=view.findViewById(R.id.profile_interview_tip);
        profileSignOut=view.findViewById(R.id.profile_signout);

//        checkNightModeActivated();

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

//        getUrlPdf4view();

        findOperation();

        onclickTextview();

        return view;
    }

    private void restartActivity() {
        startActivity( new Intent(getContext(),CandidateActivity.class));
        getActivity().finish();
    }

    @Override
    public void onResume() {
        findOperation();
        super.onResume();
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

    private void findOperation() {

        //String userId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        ApiInterface api= ApiClient.getApiClient().create(ApiInterface.class);
        Call<cndProfileResult> call=api.cndProfileFetch(userId);
        call.enqueue(new Callback<cndProfileResult>() {
            @Override
            public void onResponse(Call<cndProfileResult> call, Response<cndProfileResult> response) {
                try {
                    if (!response.body().isError()) {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        cdGender=response.body().getCdGender();
                        cdFullName=response.body().getCdFullName();
                        cdEmail=response.body().getCdEmail();
                        cdContact=response.body().getCdContact();
                        cdAlterContact=response.body().getCdAlterContact();
                        cdState=response.body().getCdState();
                        cdCity=response.body().getCdCity();
                        cdCurrentLocation=response.body().getCdCurrentLocation();
                        cdDateOfBirth=response.body().getCdDateOfBirth();
                        cdCommunication=response.body().getCdCommunicationk();
                        cdJobProfileOne=response.body().getCdJobProfileOne();
                        cdJobDesignationOne=response.body().getCdJobDesignationOne();
                        cdJobProfileTwo=response.body().getCdJobProfileTwo();
                        cdJobDesignationTwo=response.body().getCdJobDesignationTwo();
                        cdQualificationStd=response.body().getCdQualificationStd();
                        cdQualificationStream=response.body().getCdQualificationStream();
                        cdCollegeName=response.body().getCdCollegeName();
                        cdQualificationStartDate=response.body().getCdQualificationStartDate();
                        cdQualificatoinEndDate=response.body().getCdQualificatoinEndDate();
                        cdFresherInternExp=response.body().getCdFresherInternExp();
                        cdExpJobIndustry=response.body().getCdExpJobIndustry();
                        cdExpJobRole=response.body().getCdExpJobRole();
                        cdExpCompanyName=response.body().getCdExpCompanyName();
                        cdExpCurrentSalary=response.body().getCdExpCurrentSalary();
                        cdExpDesignation=response.body().getCdExpDesignation();
                        cdExpStartDate=response.body().getCdExpStartDate();
                        cdExpEndDate=response.body().getCdExpEndDate();
                        cdLanguage=response.body().getCdLanguage();
                        cdVehicle=response.body().getCdVehicle();
                        cdLicence=response.body().getCdLicence();
                        cdDocuments=response.body().getCdDocuments();
                        cdSkill=response.body().getCdSkill();
                        cdReference=response.body().getCdReference();
                        resumeUrl=response.body().getCdResume();

                        int dotposition= resumeUrl.lastIndexOf(".");
                        extensionStr= resumeUrl.substring(dotposition + 1, resumeUrl.length());

                        profileUserName.setText(cdFullName);
                        profileUserEmail.setText(cdEmail);
                        skillTv.setText(cdSkill);
                        qualificationTv.setText(cdQualificationStd+"\n"+
                                cdQualificationStream+"\n"+
                                cdCollegeName+"\n"+
                                cdQualificationStartDate+"\n"+
                                cdQualificatoinEndDate);
                        experienceTv.setText(cdExpJobIndustry+"\n"+
                                cdExpJobRole+"\n"+
                                cdExpCompanyName+"\n"+
                                cdExpCurrentSalary+"\n"+
                                cdExpStartDate+"\n"+
                                cdExpEndDate);
//                        String commStr=response.body().getCdCommunicationk();
//                        communicationTv.setText(commStr.replaceAll(",$", ""));

//                        String langStr=response.body().getCdLanguage();
//                        languagesTv.setText(langStr.replaceAll(",$", ""));

//                        String vehiStr=response.body().getCdVehicle();
//                        vehicleTv.setText(vehiStr.replaceAll(",$", ""));

                        jobProfileTv.setText(cdJobProfileOne+"\n"+
                                cdJobProfileTwo);
//                        stateTv.setText(cdState);
                        locationTv.setText(cdCurrentLocation+" | "+cdCity+" | "+cdState);
                        genderTv.setText(cdGender);
                        dobTv.setText(cdDateOfBirth);
                        contactTv.setText(cdContact+"\n"+
                                cdAlterContact);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<cndProfileResult> call, Throwable t) {

            }
        });
    }

    private void onclickTextview() {

        editCndProfileTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentToEdit();
            }
        });
        profileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "company saved...", Toast.LENGTH_SHORT).show();
            }
        });
        profileResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Patterns.WEB_URL.matcher(resumeUrl).matches()) {
                    Toast.makeText(getContext(), "Resume is not uploaded yet...!", Toast.LENGTH_SHORT).show();
                }else {

                    Bundle bundle=new Bundle();
                    bundle.putString("resumeUrl",resumeUrl);
                    Intent intent=new Intent(getContext(), VeiwResumeActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    /*Intent intentViewCv;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intentViewCv = new Intent(Intent.ACTION_VIEW);
                        intentViewCv.setData(Uri.parse(resumeUrl));
                        intentViewCv.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intentViewCv);
                    } else {
                        intentViewCv = new Intent(Intent.ACTION_VIEW);
                        intentViewCv.setDataAndType(Uri.parse(resumeUrl), "application/*");
                        intentViewCv = Intent.createChooser(intentViewCv, "Open File");
                        intentViewCv.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentViewCv);
                    }*/
                }
            }
        });
        profileHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpActivity.class));
                Toast.makeText(getContext(), "Help", Toast.LENGTH_SHORT).show();
            }
        });
        profileFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FeedbackActivity.class));
            }
        });
        interViewTipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Interview_tips.class));
                Toast.makeText(getContext(), "interview tips", Toast.LENGTH_SHORT).show();
            }
        });
        profileSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sign out", Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(getContext()).logout();
                startActivity(new Intent(getContext(), MainActivity.class));
                requireActivity().finish();
            }
        });
    }

    private void getUrlPdf4view() {
        ApiInterface api= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call=api.viewPdf(userId);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null) {
                    resumeUrl = response.body().getMessage();
                }else{
                    Toast.makeText(getContext(), "empty....", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intentToEdit() {
        Bundle bundle=new Bundle();
        bundle.putBoolean("isEditPart",true);
        bundle.putString("cdGender",cdGender);
        bundle.putString("cdFullName",cdFullName);
        bundle.putString("cdEmail",cdEmail);
        bundle.putString("cdContact",cdContact);
        bundle.putString("cdAlterContact",cdAlterContact);
        bundle.putString("cdState",cdState);
        bundle.putString("cdCity",cdCity);
        bundle.putString("cdCurrentLocation",cdCurrentLocation);
        bundle.putString("cdDateOfBirth",cdDateOfBirth);
        bundle.putString("cdCommunication",cdCommunication);
        bundle.putString("cdJobProfileOne",cdJobProfileOne);
        bundle.putString("cdJobDesignationOne",cdJobDesignationOne);
        bundle.putString("cdJobProfileTwo",cdJobProfileTwo);
        bundle.putString("cdJobDesignationTwo",cdJobDesignationTwo);
        bundle.putString("cdQualificationStd",cdQualificationStd);
        bundle.putString("cdQualificationStream",cdQualificationStream);
        bundle.putString("cdCollegeName",cdCollegeName);
        bundle.putString("cdQualificationStartDate",cdQualificationStartDate);
        bundle.putString("cdQualificatoinEndDate",cdQualificatoinEndDate);
        bundle.putString("cdFresherInternExp",cdFresherInternExp);
        bundle.putString("cdExpJobIndustry",cdExpJobIndustry);
        bundle.putString("cdExpJobRole",cdExpJobRole);
        bundle.putString("cdExpCompanyName",cdExpCompanyName);
        bundle.putString("cdExpCurrentSalary",cdExpCurrentSalary);
        bundle.putString("cdExpDesignation",cdExpDesignation);
        bundle.putString("cdExpStartDate",cdExpStartDate);
        bundle.putString("cdExpEndDate",cdExpEndDate);
        bundle.putString("cdLanguage",cdLanguage);
        bundle.putString("cdVehicle",cdVehicle);
        bundle.putString("cdLicence",cdLicence);
        bundle.putString("cdDocuments",cdDocuments);
        bundle.putString("cdSkill",cdSkill);
        bundle.putString("cdReference",cdReference);
        bundle.putString("cdResumeUrl",resumeUrl);
        bundle.putString("extensionStr",extensionStr);

        Intent intent=new Intent(getContext(), CndEditProfileActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
/*
public class ProfileFragment extends Fragment {

    private CircularImageView profileImage;
    private TextView profileUserName,profileUserEmail,profileGotoResume;
    private TextView profileApplied,profileShortlisted,profileIntervies,profileRejected;
    private ProgressBar profileProgress;
    private TextView profileProgressText,profileEditComplited,profileEditClickable;
    private TextView profileRecruites30,profileRecruites90;
    private TextView profileSave,profileHelp,profileFeedback,interViewTipTv,profileSignOut;

    private RelativeLayout profileSkillView;
    private TextView goToProfileView;

    private String userId;


    private TextView editCndProfileTv,skillTv,qualificationTv,experienceTv,communicationTv,languagesTv,vehicleTv,
            licenceTv,jobProfileTv,stateTv,locationTv,genderTv,dobTv,contactTv,referenceTv;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        userId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        Toast.makeText(getContext(), userId, Toast.LENGTH_SHORT).show();

        profileImage=view.findViewById(R.id.circle_image_profile);
        profileUserName=view.findViewById(R.id.profile_user_name);
        profileUserEmail=view.findViewById(R.id.profile_user_email);
        goToProfileView=view.findViewById(R.id.go_to_profile_view);

        editCndProfileTv=view.findViewById(R.id.cnd_profile_edit_tv);

        skillTv=view.findViewById(R.id.cnd_profile_skills);
        qualificationTv=view.findViewById(R.id.cnd_profile_qualifications);
        experienceTv=view.findViewById(R.id.cnd_profile_experience);
        communicationTv=view.findViewById(R.id.cnd_profile_communication);
        languagesTv=view.findViewById(R.id.cnd_profile_languages);
        vehicleTv=view.findViewById(R.id.cnd_profile_vehicles);
        licenceTv=view.findViewById(R.id.cnd_profile_licence);
        jobProfileTv=view.findViewById(R.id.cnd_profile_job_profile);
        stateTv=view.findViewById(R.id.cnd_profile_state);
        locationTv=view.findViewById(R.id.cnd_profile_location);
        genderTv=view.findViewById(R.id.cnd_profile_gender);
        dobTv=view.findViewById(R.id.cnd_profile_dob);
        contactTv=view.findViewById(R.id.cnd_profile_contact);
        referenceTv=view.findViewById(R.id.cnd_profile_reference);

        */
/*profileShortlisted=view.findViewById(R.id.profile_shortlist_number);
        profileIntervies=view.findViewById(R.id.profile_interviews_number);
        profileRejected=view.findViewById(R.id.profile_rejected_number);
        profileApplied=view.findViewById(R.id.profile_applied_number);

        profileProgress=view.findViewById(R.id.profile_progressbar);
        profileProgressText=view.findViewById(R.id.profile_progress_text);
        profileEditComplited=view.findViewById(R.id.profile_edit_complited);
        profileEditClickable=view.findViewById(R.id.profile_edit_clickable);

        profileRecruites30=view.findViewById(R.id.profile_recruites_30);
        profileRecruites90=view.findViewById(R.id.profile_recruites_90);*/
/*


        profileEditClickable=view.findViewById(R.id.go_to_profile_view);
        profileSave=view.findViewById(R.id.profile_save);
        profileHelp=view.findViewById(R.id.profile_help);
        profileFeedback=view.findViewById(R.id.profile_feedback);
        interViewTipTv=view.findViewById(R.id.profile_interview_tip);
        profileSignOut=view.findViewById(R.id.profile_signout);


        onclickTextview();

        findOperation();
        return view;
    }

    private void findOperation() {

        //String userId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        ApiInterface api= ApiClient.getApiClient().create(ApiInterface.class);
        Call<cndProfileResult> call=api.cndProfileFetch(userId);
        call.enqueue(new Callback<cndProfileResult>() {
            @Override
            public void onResponse(Call<cndProfileResult> call, Response<cndProfileResult> response) {
                try {
                    if (!response.body().isError()) {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        profileUserName.setText(response.body().getCdFullName());
                        profileUserEmail.setText(response.body().getCdEmail());
                        skillTv.setText(response.body().getCdSkill());
                        qualificationTv.setText(response.body().getCdQualificationStd()+"\n"+
                                response.body().getCdQualificationStream()+"\n"+
                                response.body().getCdCollegeName()+"\n"+
                                response.body().getCdQualificationStartDate()+"\n"+
                                response.body().getCdQualificatoinEndDate());
                        experienceTv.setText(response.body().getCdExpJobIndustry()+"\n"+
                                response.body().getCdExpJobRole()+"\n"+
                                response.body().getCdExpCompanyName()+"\n"+
                                response.body().getCdExpStartDate()+"\n"+
                                response.body().getCdExpEndDate());
                        String commStr=response.body().getCdCommunicationk();
                        communicationTv.setText(commStr.replaceAll(",$", ""));

                        String langStr=response.body().getCdLanguage();
                        languagesTv.setText(langStr.replaceAll(",$", ""));

                        String vehiStr=response.body().getCdVehicle();
                        vehicleTv.setText(vehiStr.replaceAll(",$", ""));

                        String licStr=response.body().getCdLicence();
                        licenceTv.setText(licStr.replaceAll(",$", ""));
                        jobProfileTv.setText(response.body().getCdJobProfileOne()+"\n"+response.body().getCdJobProfileTwo());
                        stateTv.setText(response.body().getCdState());
                        locationTv.setText(response.body().getCdCurrentLocation());
                        genderTv.setText(response.body().getCdGender());
                        dobTv.setText(response.body().getCdDateOfBirth());
                        contactTv.setText(response.body().getCdContact()+"\n"+response.body().getCdAlterContact());
                        referenceTv.setText(response.body().getCdReference());

                }
                } catch (Exception e) {
                e.printStackTrace();
            }
            }

            @Override
            public void onFailure(Call<cndProfileResult> call, Throwable t) {

            }
        });
    }

    private void onclickTextview() {

        editCndProfileTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CndRegisterationActivity.class));
            }
        });
        profileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "company saved...", Toast.LENGTH_SHORT).show();
            }
        });
        profileHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpActivity.class));
                Toast.makeText(getContext(), "Help", Toast.LENGTH_SHORT).show();
            }
        });
        profileFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FeedbackActivity.class));
            }
        });
        interViewTipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Interview_tips.class));
                Toast.makeText(getContext(), "interview tips", Toast.LENGTH_SHORT).show();
            }
        });
        profileSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sign out", Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(getContext()).logout();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
}*/
