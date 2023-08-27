package com.abc.justjob.Candidate.CndCompanies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.CompiesDetails.CompaniesContactedFrgValues;
import com.abc.justjob.Candidate.CandidateActivityFragment.CompaniesFragment;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Handler;

import org.json.JSONObject;

import java.io.IOException;


public class CndCompaniesDetailInfoActivity extends AppCompatActivity {

    private CardView CompanyWebsite,CompanyAddressCard;

    private TextView HeadText,CompanyName,CompanyType,CompanyHeadOffice,CompanyCategory,
                     CompanyIndustry,CompanyAbout,CompanySize,CompanyLink,CompanyAddress,CompanyState,CompanyCity;

    private Button Contact;

    private String cndId,cndRegName,cndEmail,cmp_company_name,cmp_id,cmp_job_type,cmp_head_office_location,cmp_company_category,cmp_industry,
                   cmp_about_company,emp_size,cmp_link,cmp_company_address,cmp_state,cmp_contact,cmp_email;
    private String status,From,To;

    public boolean backpressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_cnd_companies_detail_info);



        cndId = SharedPrefManager.getInstance(CndCompaniesDetailInfoActivity.this).getValueOfUserId(CndCompaniesDetailInfoActivity.this);
        cndRegName = SharedPrefManager.getInstance(CndCompaniesDetailInfoActivity.this).getUserName(CndCompaniesDetailInfoActivity.this);
        cndEmail = SharedPrefManager.getInstance(CndCompaniesDetailInfoActivity.this).getUserEmail(CndCompaniesDetailInfoActivity.this);


        cmp_id = getIntent().getStringExtra("cmp_id");

        //Toast.makeText(CndCompaniesDetailInfoActivity.this,cmp_id,Toast.LENGTH_SHORT).show();
//        String cmp_company_name= getIntent().getStringExtra("cmp_company_name");
//        String cmp_job_type= getIntent().getStringExtra("cmp_job_type");
//        String cmp_head_office_location = getIntent().getStringExtra("cmp_head_office_location");
//        String cmp_company_category = getIntent().getStringExtra("cmp_company_category");
//        String cmp_industry = getIntent().getStringExtra("cmp_industry");
//        String cmp_about_company = getIntent().getStringExtra("cmp_about_company");
//        String emp_size = getIntent().getStringExtra("emp_size");
//        String cmp_link = getIntent().getStringExtra("cmp_link");
//        String cmp_company_address = getIntent().getStringExtra("cmp_company_address");
//        String cmp_state = getIntent().getStringExtra("cmp_state");

        cmp_company_name= getIntent().getStringExtra("cmp_company_name");
        cmp_job_type= getIntent().getStringExtra("cmp_job_type");
        cmp_head_office_location = getIntent().getStringExtra("cmp_head_office_location");
        cmp_company_category = getIntent().getStringExtra("cmp_company_category");
        cmp_industry = getIntent().getStringExtra("cmp_industry");
        cmp_about_company = getIntent().getStringExtra("cmp_about_company");
        emp_size = getIntent().getStringExtra("emp_size");
        cmp_link = getIntent().getStringExtra("cmp_link");
        cmp_company_address = getIntent().getStringExtra("cmp_company_address");
        cmp_state = getIntent().getStringExtra("cmp_state");
        cmp_contact = getIntent().getStringExtra("cmp_contact");
        cmp_email = getIntent().getStringExtra("cmp_email");


        HeadText=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_name_headline);
        CompanyAddressCard=findViewById(R.id.Company_address_card);
        CompanyWebsite=findViewById(R.id.Company_website_card);
        CompanyName=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_name);
        CompanyType=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_type);
        CompanyHeadOffice=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_Head_location);
        CompanyCategory=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_category);
        CompanyIndustry=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_industry);
        CompanyAbout=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_about);
        CompanySize=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_size);
        CompanyLink=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_link);
        CompanyAddress=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_address);
        CompanyState=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_state);
        Contact=findViewById(R.id.cnd_companies_list_details_btn_contact);
        CompanyCity=findViewById(R.id.cnd_companies_detail_tab_cmp_info_company_city);

        HeadText.setText(cmp_company_name);
        CompanyName.setText(cmp_company_name);
        CompanyType.setText(cmp_job_type);
        CompanyHeadOffice.setText(cmp_head_office_location);
        CompanyCategory.setText(cmp_company_category);
        CompanyIndustry.setText(cmp_industry);
        CompanyAbout.setText(cmp_about_company);
        CompanySize.setText(emp_size);
        CompanyLink.setText(cmp_link);
        CompanyAddress.setText(cmp_company_address);
        CompanyState.setText(cmp_state);
        CompanyCity.setText(cmp_head_office_location);


        CompanyWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                //Uri searchUri = Uri.parse("https://www.google.com/search?q=" + CompanyLink.getText().toString());
                intent.putExtra(SearchManager.QUERY, CompanyLink.getText().toString());

                //if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                //}
            }
        });

        CompanyAddressCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(CompanyAddress.getText().toString()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                //if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                //}
            }
        });

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactOptionsDialog();
            }
        });

    }

    private void showContactOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contact Options")
                .setItems(new CharSequence[]{"Through Contact Number", "Through Email"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                status="call";
                                From = "Candidate";
                                To = "Company";

                                Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);
                                //Call<Void> call = api.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id, cmp_company_name, cmp_email, status , From, To);
                                Call<Void> call = api.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id,  cmp_company_name, cmp_email, status , From, To);

                                call.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.isSuccessful()) {

                                            Contact.setText("Contacted");
                                            Contact.setTextColor(getResources().getColor(R.color.red_light));
                                            Toast.makeText(CndCompaniesDetailInfoActivity.this, "Contacting ....", Toast.LENGTH_SHORT).show();
                                            Handler handler = new Handler();
                                            Runnable runnable = () -> {
                                                Intent HrNum = new Intent(Intent.ACTION_DIAL);
                                                HrNum.setData(Uri.parse("tel:"+cmp_contact));
                                                startActivity(HrNum);
                                            };
                                            handler.postDelayed(runnable, 2000);

                                        } else {
                                            Toast.makeText(CndCompaniesDetailInfoActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(CndCompaniesDetailInfoActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                            case 1:
                                status="email";
                                From = "Candidate";
                                To = "Company";

                                Api_cmp_post_job api1 = ApiClient.getApiClient().create(Api_cmp_post_job.class);
                                //Call<Void> callie = api1.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id, cmp_company_name, cmp_email, status , From, To);
                                Call<Void> callie = api1.setCandidate_ContactedDetails( cndId, cndRegName, cndEmail, cmp_id, cmp_company_name, cmp_email, status , From, To);

                                callie.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> callie, Response<Void> resp) {
                                        if (resp.isSuccessful()) {
                                            Contact.setText("Contacted");
                                            Contact.setTextColor(getResources().getColor(R.color.red_light));
                                            Toast.makeText(CndCompaniesDetailInfoActivity.this, "Contacting ...", Toast.LENGTH_SHORT).show();
                                            Handler handler = new Handler();
                                            Runnable runnable = () -> {
                                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                                intent.setData(Uri.parse("mailto:" + cmp_email));
                                                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"", ""}); // Add recipient email addresses here
                                                intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject here");
                                                intent.putExtra(Intent.EXTRA_TEXT, "Email body here");
                                                startActivity(intent);
                                            };
                                            handler.postDelayed(runnable, 2000);

                                        } else {
                                            Toast.makeText(CndCompaniesDetailInfoActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> callie, Throwable ty) {
                                        Toast.makeText(CndCompaniesDetailInfoActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}