package com.abc.justjob.Candidate.CndAplcActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.abc.justjob.R;

public class cndAplcCallHistoryInfoActivity extends AppCompatActivity {

    private String dateTimeStr,cmpIdStr,cmpFullNameStr,cmpContactStr,
            cmpDesignationStr,cmpEmailStr,cmpCompanyNameStr,
            cmpHeadOfficeLocationStr,cmpIndustryStr,cmpCmpCategoryStr,
            cmpJobTypeStr,cmpAddressStr,cmpAboutCompanyStr;

    private TextView dateTimeTv,cmpIdTv,cmpFullNameTv,cmpContactTv,
            cmpDesignationTv,cmpEmailTv,cmpCompanyNameTv,
            cmpHeadOfficeLocationTv,cmpIndustryTv,cmpCmpCategoryTv,
            cmpJobTypeTv,cmpAddressTv,cmpAboutCompanyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnd_aplc_call_history_info);

        getDataByIntent();

        init();

        setDataByIntent();
    }

    private void setDataByIntent() {
        dateTimeTv.setText(dateTimeStr);
        cmpFullNameTv.setText(cmpFullNameStr);
        cmpContactTv.setText(cmpContactStr);
        cmpDesignationTv.setText(cmpDesignationStr);
        cmpEmailTv.setText(cmpEmailStr);
        cmpCompanyNameTv.setText(cmpCompanyNameStr);
        cmpHeadOfficeLocationTv.setText(cmpHeadOfficeLocationStr);
        cmpIndustryTv.setText(cmpIndustryStr);
        cmpCmpCategoryTv.setText(cmpCmpCategoryStr);
        cmpJobTypeTv.setText(cmpJobTypeStr);
        cmpAddressTv.setText(cmpAddressStr);
        cmpAboutCompanyTv.setText(cmpAboutCompanyStr);
    }

    private void init() {

        dateTimeTv=findViewById(R.id.cnd_aplc_call_history_date);
        cmpFullNameTv=findViewById(R.id.cnd_aplc_call_history_user_name);
        cmpContactTv=findViewById(R.id.cnd_aplc_call_history_cmp_contact);
        cmpDesignationTv=findViewById(R.id.cnd_aplc_call_history_cmp_designation);
        cmpEmailTv=findViewById(R.id.cnd_aplc_call_history_cmp_email);
        cmpCompanyNameTv=findViewById(R.id.cnd_aplc_call_history_cmp_name);
        cmpHeadOfficeLocationTv=findViewById(R.id.cnd_aplc_call_history_office_location);
        cmpIndustryTv=findViewById(R.id.cnd_aplc_call_history_cmp_industry);
        cmpCmpCategoryTv=findViewById(R.id.cnd_aplc_call_history_cmp_category);
        cmpJobTypeTv=findViewById(R.id.cnd_aplc_call_history_job_type);
        cmpAddressTv=findViewById(R.id.cnd_aplc_call_history_cmp_address);
        cmpAboutCompanyTv=findViewById(R.id.cnd_aplc_call_history_cmp_about);

    }

    private void getDataByIntent() {
        Bundle bundle=getIntent().getExtras();
        dateTimeStr=bundle.getString("date_time");
        cmpIdStr=bundle.getString("cmp_id");
        cmpCompanyNameStr=bundle.getString("cmp_company_name");
        cmpFullNameStr=bundle.getString("cmp_full_name");
        cmpContactStr=bundle.getString("cmp_contact");
        cmpIndustryStr=bundle.getString("cmp_industry");
        cmpDesignationStr=bundle.getString("cmp_designation");
        cmpEmailStr=bundle.getString("cmp_email");
        cmpHeadOfficeLocationStr=bundle.getString("cmp_head_office_location");
        cmpCmpCategoryStr=bundle.getString("cmp_company_category");
        cmpJobTypeStr=bundle.getString("cmp_job_type");
        cmpAddressStr=bundle.getString("cmp_company_address");
        cmpAboutCompanyStr=bundle.getString("cmp_about_company");
    }
}