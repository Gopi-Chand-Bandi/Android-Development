package com.abc.justjob.Company.CompanyApplicationFrg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.justjob.ApiFile.CompanyFetch.CmpHomeFrgValues;
import com.abc.justjob.Company.CompanyActivitys.CndListDetailsActivity;
import com.abc.justjob.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class cmpAplcCndAppliedAdapter extends RecyclerView.Adapter<cmpAplcCndAppliedAdapter.cmpAplcCndAppliedHolder> {

    private List<CmpHomeFrgValues> cmpHomeFrgValuesList;
    private Context context;
    private boolean isHome;
    private String cmpRegId,cmpPojoId;

    public cmpAplcCndAppliedAdapter(List<CmpHomeFrgValues> cmpHomeFrgValues, Context context,boolean isHome,String cmpRegId,String cmpPojoId) {
        this.cmpHomeFrgValuesList = cmpHomeFrgValues;
        this.context = context;
        this.isHome=isHome;
        this.cmpRegId=cmpRegId;
        this.cmpPojoId=cmpPojoId;
    }

    @NonNull
    @Override
    public cmpAplcCndAppliedAdapter.cmpAplcCndAppliedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new cmpAplcCndAppliedAdapter.cmpAplcCndAppliedHolder(LayoutInflater.from(context)
                .inflate(R.layout.candidate_new_card_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull cmpAplcCndAppliedAdapter.cmpAplcCndAppliedHolder holder, int position) {

        CmpHomeFrgValues values=cmpHomeFrgValuesList.get(position);
        holder.cndName.setText(values.getCd_full_name());
        holder.cndAge.setText(getAge(values.getCd_date_of_birth())+" year old");
        //holder.cndRole.setText(values.getCd_job_profile_one());
        //holder.cndDesignation.setText(values.getCd_job_designation_one());
        holder.cndLocation.setText(values.getCd_current_location());
        holder.cndCity.setText(values.getCd_city());

        if (values.getCd_fresher_intern_exp().equals("Fresher")) {
            holder.cndFresherLayout.setVisibility(View.VISIBLE);
            holder.cndExpLayput.setVisibility(View.GONE);
        }else{

            holder.cndFresherLayout.setVisibility(View.GONE);
            holder.cndExpLayput.setVisibility(View.VISIBLE);
            holder.cndExpCompany.setText(values.getCd_exp_company_name());
            //holder.cndExpRole.setText(values.getCd_exp_job_role());
            //holder.cndExpdesignation.setText(values.getCd_exp_designation());
            holder.cndExpIndustry.setText(values.getCd_exp_job_industry());
        }
        holder.cndQualiStd.setText(values.getCd_qualification_std());
        holder.cndQualiStream.setText(values.getCd_qualification_stream());
        holder.cndQualiCollege.setText(values.getCd_college_name());
        holder.cndSkill.setText(values.getCd_skill());
        holder.cndLanguages.setText(values.getCd_languages());

        holder.cndCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CndListDetailsActivity.class);
                intent.putExtra("isHome",isHome);
                intent.putExtra("cmpRegId",cmpRegId);
                intent.putExtra("cmpPojoId",cmpPojoId);
                intent.putExtra("cndRegisterId",values.getRegister_login_id());
                intent.putExtra("cndGender",values.getCd_gender());
                intent.putExtra("cndName",values.getCd_full_name());
                intent.putExtra("cndEmail",values.getCd_email());
                intent.putExtra("cndContact",values.getCd_contact_no());
                intent.putExtra("cndAlterContact",values.getCd_alter_contact());
                intent.putExtra("cndState",values.getCd_state());
                intent.putExtra("cndCity",values.getCd_city());
                intent.putExtra("cndLocation",values.getCd_current_location());
                intent.putExtra("cndDob",values.getCd_date_of_birth());
                intent.putExtra("cndCommunication",values.getCd_communication());
                intent.putExtra("cndJobProfileOne",values.getCd_job_profile_one());
                intent.putExtra("cndJobDesignationOne",values.getCd_job_designation_one());
                intent.putExtra("cndJobProfileTwo",values.getCd_job_profile_two());
                intent.putExtra("cndJobDesignationTwo",values.getCd_job_designation_two());
                intent.putExtra("cndQualificationStd",values.getCd_qualification_std());
                intent.putExtra("cndQualificationStream",values.getCd_qualification_stream());
                intent.putExtra("cndQualiCollegeName",values.getCd_college_name());
                intent.putExtra("cndQualiStart",values.getCd_qualification_start_date());
                intent.putExtra("cndQualiEnd",values.getCd_qualification_end_date());
                intent.putExtra("cndFresherIntern",values.getCd_fresher_intern_exp());
                intent.putExtra("cndExpIndustry",values.getCd_exp_job_industry());
                //intent.putExtra("cndExpRole",values.getCd_exp_job_role());
                intent.putExtra("cndExpCompany",values.getCd_exp_company_name());
                intent.putExtra("cndExpCurrentSalary",values.getCd_exp_current_salary());
                //intent.putExtra("cndExpDesignation",values.getCd_exp_designation());
                intent.putExtra("cndExpStart",values.getCd_exp_start_date());
                intent.putExtra("cndExpEnd",values.getCd_exp_end_date());
                intent.putExtra("cndLanguages",values.getCd_languages());
                intent.putExtra("cndDocuments",values.getCd_documents());
                intent.putExtra("cndVehicle",values.getCd_vehicle());
                intent.putExtra("cndLicence",values.getCd_licence());
                intent.putExtra("cndSkill",values.getCd_skill());
                intent.putExtra("cndReference",values.getCd_reference());
                //intent.putExtra("cndResume",values.getCd_resume());
                context.startActivity(intent);
            }
        });
    }
    private int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }

    @Override
    public int getItemCount() {
        return cmpHomeFrgValuesList.size();
    }

    public class cmpAplcCndAppliedHolder extends RecyclerView.ViewHolder {

        CardView cndCardView;
        LinearLayout cndFresherLayout,cndExpLayput;
        TextView cndName,cndAge,cndRoleFresher,cndDesignationFresher,cndRoleExp,cndDesignationExp,cndExpCompany,cndCity,cndLocation,cndFresher,
                cndExpRole,cndExpdesignation,cndExpIndustry,cndQualiStd,
                cndQualiStream,cndQualiCollege,cndSkill,cndLanguages;

        public cmpAplcCndAppliedHolder(@NonNull View itemView) {
            super(itemView);

            cndCardView=itemView.findViewById(R.id.cnd_info_card_design);

            cndName=itemView.findViewById(R.id.cnd_info_card_design_name);
            cndAge=itemView.findViewById(R.id.cnd_info_card_design_age);

            cndRoleFresher=itemView.findViewById(R.id.cnd_info_card_design_role_fresher);
            cndDesignationFresher=itemView.findViewById(R.id.cnd_info_card_design_designation_fresher);

            cndRoleExp=itemView.findViewById(R.id.cnd_info_card_design_role_exp);
            cndDesignationExp=itemView.findViewById(R.id.cnd_info_card_design_designation_exp);
            cndExpCompany=itemView.findViewById(R.id.cnd_info_card_design_company);

            cndCity=itemView.findViewById(R.id.cnd_info_card_design_city);
            cndLocation=itemView.findViewById(R.id.cnd_info_card_design_locality);

    

            cndLocation=itemView.findViewById(R.id.cnd_info_card_design_locality);
            cndCity=itemView.findViewById(R.id.cnd_info_card_design_city);
            cndFresher=itemView.findViewById(R.id.cnd_info_card_design_fresher);
            //cndExpLayput=itemView.findViewById(R.id.cnd_info_card_design_exp_layout);
            //cndExpCompany=itemView.findViewById(R.id.cnd_info_card_design_exp_company_name);
            //cndExpRole=itemView.findViewById(R.id.cnd_info_card_design_exp_role);
            //cndExpdesignation=itemView.findViewById(R.id.cnd_info_card_design_exp_designation);
            cndExpIndustry=itemView.findViewById(R.id.cnd_info_card_design_exp_industry);
            cndQualiStd=itemView.findViewById(R.id.cnd_info_card_design_quali_std);
            cndQualiStream=itemView.findViewById(R.id.cnd_info_card_design_quali_stream);
            //cndQualiCollege=itemView.findViewById(R.id.cnd_info_card_design_quali_college);
            cndSkill=itemView.findViewById(R.id.cnd_info_card_design_skill);
            //cndLanguages=itemView.findViewById(R.id.cnd_info_card_design_languages);


            cndFresherLayout=itemView.findViewById(R.id.fresher_layout_cmp);

        }
    }
}
