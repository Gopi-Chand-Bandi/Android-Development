package com.abc.justjob.ApiFile.CompanyFetch;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgValues;
import com.abc.justjob.Company.CompanyActivitys.CndListDetailsActivity;
import com.abc.justjob.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CmpHomeFrgAdapter extends RecyclerView.Adapter<CmpHomeFrgAdapter.CmpHomeFrgViewHolder> implements Filterable {

    private List<CmpHomeFrgValues> cmpHomeFrgValuesList;
    private List<CmpHomeFrgValues> cmpHomeFrgFilterList;
    private Context context;
    private String sample;
    private boolean homeBl;
    private int clickCount = 0;
    int num = 1;
    private boolean isHome;

    public CmpHomeFrgAdapter(List<CmpHomeFrgValues> cmpHomeFrgValuesList, Context context,boolean homeBl) {
        this.cmpHomeFrgValuesList = cmpHomeFrgValuesList;
        this.cmpHomeFrgFilterList = cmpHomeFrgValuesList;
        this.context = context;
        this.homeBl=homeBl;
    }
//    public void clear() {
//        int size = cmpHomeFrgFilterList.size();
//        cmpHomeFrgFilterList.clear();
//        notifyItemRangeRemoved(0, size);
//    }

    @NonNull
    @Override
    public CmpHomeFrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new CmpHomeFrgAdapter.CmpHomeFrgViewHolder(LayoutInflater.from(context).
                inflate(R.layout.candidate_new_card_design, parent, false));
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CmpHomeFrgViewHolder holder, int position) {

        final CmpHomeFrgValues values=cmpHomeFrgFilterList.get(position);

        holder.cndName.setText(values.getCd_full_name());
        holder.cndAge.setText(getAge(values.getCd_date_of_birth())+" year old");

        if (values.getCd_fresher_intern_exp().equals("Fresher")) {
            holder.cndFresherLayout.setVisibility(View.VISIBLE);
            holder.cndExpLayput.setVisibility(View.GONE);
            holder.cndExpDetails.setVisibility(View.GONE);
            holder.cndFresherRole.setText(values.getCd_job_profile_one());
            holder.cndFresherDesignation.setText(values.getCd_job_designation_one());
        }else {
            holder.cndFresherLayout.setVisibility(View.GONE);
            holder.cndExpLayput.setVisibility(View.VISIBLE);
            holder.cndExpDetails.setVisibility(View.VISIBLE);
            holder.cndExpRole.setText(values.getCd_job_profile_one());
            holder.cndExpDesignation.setText(values.getCd_job_designation_one());
            holder.cndExpCompany.setText(values.getCd_exp_company_name());
            holder.cndFresher.setText(values.getCd_fresher_intern_exp());
            holder.cndExpIndustry.setText(values.getCd_exp_job_industry());
        }

        holder.cndCity.setText(values.getCd_city());
        holder.cndLocation.setText(values.getCd_current_location());

        holder.cndQualiStd.setText(values.getCd_qualification_std());
        holder.cndQualiStream.setText(values.getCd_qualification_stream());

        holder.cndCommunication.setText(values.getCd_communication());

        holder.cndSkill.setText(values.getCd_skill());

        holder.cndCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CndListDetailsActivity.class);
                //intent.putExtra("isHome",isHome);
                intent.putExtra("cd_id",values.getCd_id());
                intent.putExtra("register_login_id",values.getRegister_login_id());
                intent.putExtra("cd_gender",values.getCd_gender());
                intent.putExtra("cd_full_name",values.getCd_full_name());
                intent.putExtra("cd_email",values.getCd_email());
                intent.putExtra("cd_contact_no",values.getCd_contact_no());
                intent.putExtra("cd_alter_contact",values.getCd_alter_contact());
                intent.putExtra("cd_state",values.getCd_state());
                intent.putExtra("cd_city",values.getCd_city());
                intent.putExtra("cd_current_location",values.getCd_current_location());
                intent.putExtra("cd_date_of_birth",values.getCd_date_of_birth());
                intent.putExtra("cd_communication",values.getCd_communication());
                intent.putExtra("cd_job_profile_one",values.getCd_job_profile_one());
                intent.putExtra("cd_job_designation_one",values.getCd_job_designation_one());
                intent.putExtra("cd_job_profile_two",values.getCd_job_profile_two());
                intent.putExtra("cd_job_designation_two",values.getCd_job_designation_two());
                intent.putExtra("cd_qualification_std",values.getCd_qualification_std());
                intent.putExtra("cd_qualification_stream",values.getCd_qualification_stream());
                intent.putExtra("cd_college_name",values.getCd_college_name());
                intent.putExtra("cd_qualification_start_date",values.getCd_qualification_start_date());
                intent.putExtra("cd_qualification_end_date",values.getCd_qualification_end_date());
                intent.putExtra("cd_fresher_intern_exp",values.getCd_fresher_intern_exp());
                intent.putExtra("cd_exp_job_industry",values.getCd_exp_job_industry());
                intent.putExtra("cd_exp_company_name",values.getCd_exp_company_name());
                intent.putExtra("cd_exp_current_salary",values.getCd_exp_current_salary());
                intent.putExtra("cd_exp_start_date",values.getCd_exp_start_date());
                intent.putExtra("cd_exp_end_date",values.getCd_exp_end_date());
                intent.putExtra("cd_employment_type",values.getCd_employment_type());
                intent.putExtra("cd_location_prefer",values.getCd_location_prefer());
                intent.putExtra("cd_languages",values.getCd_languages());
                intent.putExtra("cd_vehicle",values.getCd_vehicle());
                intent.putExtra("cd_licence",values.getCd_licence());
                intent.putExtra("cd_documents",values.getCd_documents());
                intent.putExtra("cd_skill",values.getCd_skill());
                intent.putExtra("cd_reference",values.getCd_reference());
                intent.putExtra("cd_resume_cv_url",values.getCd_resume_cv_url());

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
        return cmpHomeFrgFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String newText = charSequence.toString();
                if (newText.isEmpty()) {
                    cmpHomeFrgFilterList = cmpHomeFrgValuesList;
                } else {
                    List<CmpHomeFrgValues> filterList = new ArrayList<>();
                    for (CmpHomeFrgValues values : cmpHomeFrgValuesList) {
                        if (values.getCd_full_name().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCd_current_location().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCd_job_profile_one().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCd_skill().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCd_communication().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        }
                    }
                    cmpHomeFrgFilterList = filterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cmpHomeFrgFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cmpHomeFrgFilterList = (ArrayList<CmpHomeFrgValues>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class CmpHomeFrgViewHolder extends RecyclerView.ViewHolder {

        CardView cndCardView;
        LinearLayout cndFresherLayout,cndExpLayput,cndExpDetails;
        TextView cndName,cndAge,cndFresherRole,cndFresherDesignation,cndExpRole,cndExpDesignation,cndExpCompany,cndCity,
                cndLocation,cndFresher,cndExpIndustry,cndQualiStd,cndQualiStream,cndCommunication,cndSkill;

        public CmpHomeFrgViewHolder(@NonNull View itemView) {
            super(itemView);

            cndCardView=itemView.findViewById(R.id.cnd_info_card_design);
            cndName=itemView.findViewById(R.id.cnd_info_card_design_name);
            cndAge=itemView.findViewById(R.id.cnd_info_card_design_age);
            cndFresherRole=itemView.findViewById(R.id.cnd_info_card_design_role_fresher);
            cndFresherDesignation=itemView.findViewById(R.id.cnd_info_card_design_designation_fresher);
            cndExpRole=itemView.findViewById(R.id.cnd_info_card_design_role_exp);
            cndExpDesignation=itemView.findViewById(R.id.cnd_info_card_design_designation_exp);
            cndExpCompany=itemView.findViewById(R.id.cnd_info_card_design_company);
            cndCity=itemView.findViewById(R.id.cnd_info_card_design_city);
            cndLocation=itemView.findViewById(R.id.cnd_info_card_design_locality);
            cndFresher=itemView.findViewById(R.id.cnd_info_card_design_fresher);
            cndExpIndustry=itemView.findViewById(R.id.cnd_info_card_design_exp_industry);
            cndQualiStd=itemView.findViewById(R.id.cnd_info_card_design_quali_std);
            cndQualiStream=itemView.findViewById(R.id.cnd_info_card_design_quali_stream);
            cndCommunication=itemView.findViewById(R.id.cnd_info_card_design_english);
            cndSkill=itemView.findViewById(R.id.cnd_info_card_design_skill);

            cndFresherLayout=itemView.findViewById(R.id.fresher_layout_cmp);
            cndExpLayput=itemView.findViewById(R.id.experience_layout_cmp);
            cndExpDetails=itemView.findViewById(R.id.cnd_exp_details_cmp);

        }
    }
}