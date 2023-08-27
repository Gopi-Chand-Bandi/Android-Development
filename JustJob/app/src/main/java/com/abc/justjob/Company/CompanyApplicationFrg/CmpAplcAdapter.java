package com.abc.justjob.Company.CompanyApplicationFrg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Company.CompanyActivitys.MyCandidateActivity;
import com.abc.justjob.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CmpAplcAdapter extends RecyclerView.Adapter<CmpAplcAdapter.CmpAplcViewHolder> {

    private List<cmpAplcResponse> cmpAdlcList;
    private Context context;

    public CmpAplcAdapter(List<cmpAplcResponse> cmpAdlcList, Context context) {
        this.cmpAdlcList = cmpAdlcList;
        this.context = context;
    }

    @NonNull
    @Override
    public CmpAplcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
//        return new CmpAplcAdapter.CmpAplcViewHolder(LayoutInflater.from(context).inflate(R.layout.cmp_aplc_posted_job_adapter,parent,false));
        return new CmpAplcAdapter.CmpAplcViewHolder(LayoutInflater.from(context).
                inflate(R.layout.updated_posted_job_design_4_cmp, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CmpAplcViewHolder holder, int position) {

        cmpAplcResponse values = cmpAdlcList.get(position);

        holder.pojoRole.setText(values.getCmp_pojo_role());
        holder.pojoDesignation.setText(values.getCmp_pojo_designation());
        holder.pojoCmpName.setText(values.getCmp_pojo_company_name());
        holder.pojoLocation.setText(values.getCmp_pojo_locality());
        holder.pojoCity.setText(values.getCmp_pojo_cities());
        holder.pojoIndustry.setText(values.getCmp_pojo_industry());
        holder.pojoType.setText(values.getCmp_pojo_job_type());
        holder.pojoMinSalary.setText(values.getCmp_pojo_offering_min_salary());
        holder.pojoMaxSalary.setText(values.getCmp_pojo_offering_max_salary());
        holder.pojoMinExp.setText(values.getCmp_pojo_min_exp());
        holder.pojoMaxExp.setText(values.getCmp_pojo_max_exp());
        holder.pojoQualification.setText(values.getCmp_pojo_education());
        holder.pojoViewCount.setText(values.getView_count());


//        holder.cmpJobRole.setText(values.getCmp_pojo_role());
//        holder.cmpDetails.setText(values.getCmp_pojo_description());
//        holder.cmpLocation.setText(values.getCmp_pojo_locality());
//        holder.cmpMinSalary.setText(values.getCmp_pojo_offering_min_salary());
//        holder.cmpMaxSalary.setText(values.getCmp_pojo_offering_max_salary());
//        holder.cmpMinExp.setText(values.getCmp_pojo_min_exp());
//        holder.cmpMaxExp.setText(values.getCmp_pojo_max_exp());
//        holder.cmpJobType.setText(values.getCmp_pojo_job_type());
//        holder.cmpjobIndustry.setText(values.getCmp_pojo_industry());
//
//        if (!values.getCmp_pojo_day_shift_from().equals("--") && !values.getCmp_pojo_day_shift_to().equals("--")) {
//            holder.cmpShiftTime.setText("Day shift");
//        }else if (!values.getCmp_pojo_night_shift_from().equals("--") && !values.getCmp_pojo_night_shift_to().equals("--")){
//            holder.cmpShiftTime.setText("Night Shift");
//        }else if(!values.getCmp_pojo_rotate_shift_from().equals("--") && !values.getCmp_pojo_rotate_shift_to().equals("--")){
//            holder.cmpShiftTime.setText("Rotation Shift");
//        }
//        holder.cmpEducation.setText(values.getCmp_pojo_education());
//        holder.cmpCndViewCount.setText(values.getView_count());

        holder.cmpDeleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setCancelable(true);
                builder.setTitle("Delete");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setMessage("Are you sour you want to delete ?");
                builder.setPositiveButton("Ok", (dialog, which) -> {

                    insertDataOperation(values);

                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(context, "Canceled...", Toast.LENGTH_SHORT).show();
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        holder.cmpJobCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, cmpAplcCndAppliedActivity.class);
                intent.putExtra("cmp_post_job_id", values.getCmp_post_job_id());
                intent.putExtra("cmp_register_id", values.getCmp_register_id());
                intent.putExtra("cmp_pojo_title", values.getCmp_pojo_title());
                intent.putExtra("cmp_pojo_industry", values.getCmp_pojo_industry());
                intent.putExtra("cmp_pojo_role", values.getCmp_pojo_role());
                intent.putExtra("cmp_pojo_job_type", values.getCmp_pojo_job_type());
                intent.putExtra("cmp_pojo_state", values.getCmp_pojo_state());
                intent.putExtra("cmp_pojo_city", values.getCmp_pojo_cities());
                intent.putExtra("cmp_pojo_locality", values.getCmp_pojo_locality());
                intent.putExtra("cmp_pojo_opening", values.getCmp_pojo_opening());
                intent.putExtra("cmp_pojo_description", values.getCmp_pojo_description());
                intent.putExtra("cmp_pojo_salary_time", values.getCmp_pojo_salary_time());
                intent.putExtra("cmp_pojo_offering_min_salary", values.getCmp_pojo_offering_min_salary());
                intent.putExtra("cmp_pojo_offering_max_salary", values.getCmp_pojo_offering_max_salary());
                intent.putExtra("cmp_pojo_min_age", values.getCmp_pojo_min_age());
                intent.putExtra("cmp_pojo_max_age", values.getCmp_pojo_max_age());
                intent.putExtra("cmp_pojo_fresher_can", values.getCmp_pojo_fresher_can());
                intent.putExtra("cmp_pojo_experience_can", values.getCmp_pojo_experience_can());
                intent.putExtra("cmp_pojo_min_exp", values.getCmp_pojo_min_exp());
                intent.putExtra("cmp_pojo_max_exp", values.getCmp_pojo_max_exp());
                intent.putExtra("cmp_pojo_education", values.getCmp_pojo_education());
                intent.putExtra("cmp_pojo_male_or_female", values.getCmp_pojo_male_or_female());
                intent.putExtra("cmp_pojo_english_know", values.getCmp_pojo_english_know());
                intent.putExtra("cmp_pojo_language_know", values.getCmp_pojo_language_know());
                intent.putExtra("cmp_pojo_vehicle", values.getCmp_pojo_vehicle());
                intent.putExtra("cmp_pojo_processor", values.getCmp_pojo_processor());
                intent.putExtra("cmp_pojo_phone", values.getCmp_pojo_phone());
                intent.putExtra("cmp_pojo_documents", values.getCmp_pojo_documents());
                intent.putExtra("cmp_pojo_working_day", values.getCmp_pojo_working_day());
                intent.putExtra("cmp_pojo_day_shift_from", values.getCmp_pojo_day_shift_from());
                intent.putExtra("cmp_pojo_day_shift_to", values.getCmp_pojo_day_shift_to());
                intent.putExtra("cmp_pojo_night_shift_from", values.getCmp_pojo_night_shift_from());
                intent.putExtra("cmp_pojo_night_shift_to", values.getCmp_pojo_night_shift_to());
                intent.putExtra("cmp_pojo_rotate_shift_from", values.getCmp_pojo_rotate_shift_from());
                intent.putExtra("cmp_pojo_rotate_shift_to", values.getCmp_pojo_rotate_shift_to());
                intent.putExtra("cmp_pojo_reimbursement", values.getCmp_pojo_reimbursement());
                intent.putExtra("cmp_pojo_incentive", values.getCmp_pojo_incentive());
                intent.putExtra("cmp_pojo_company_name", values.getCmp_pojo_company_name());
                intent.putExtra("cmp_pojo_company_email", values.getCmp_pojo_company_email());
                intent.putExtra("cmp_pojo_company_poc_name", values.getCmp_pojo_company_poc_name());
                intent.putExtra("cmp_pojo_company_poc_contact", values.getCmp_pojo_company_poc_contact());
                intent.putExtra("cmp_pojo_company_poc_designation", values.getCmp_pojo_company_poc_designation());
                intent.putExtra("cmp_pojo_company_poc_head_office_location", values.getCmp_pojo_company_poc_head_office_location());
                intent.putExtra("cmp_pojo_company_type", values.getcmp_pojo_company_type());
                intent.putExtra("cmp_pojo_company_about", values.getCmp_pojo_company_about());
                intent.putExtra("cmp_pojo_company_posted_by", values.getCmp_pojo_company_posted_by());
                context.startActivity(intent);
            }
        });
    }

    private void insertDataOperation(cmpAplcResponse values) {

        String regIdStr, titleStr, roleStr, desigStr, jobTypeStr, jobStateStr, jobCitieStr, jobDescriptionStr,
                jobSalaryTimeStr, minSalaryStr, maxSalaryStr, minAgeStr, maxAgeStr, localityStr, noOpeningStr,
                fresherCanStr, experienceCanStr, minExpStr, maxExpStr, educationStr, maleFemaleStr, englishKnowStr,
                languageStr, vehicleStr, processorStr, phoneStr, documentStr, workingDayStr, dayFromStr, dayToStr,
                nightFromStr, nightToStr, rotateFromStr, rotateToStr, reimStr, incentiveStr, depositingStr, depoAmountStr,
                depoPurpStr, cmpNameStr, jobCmpEmailStr, cmpPocNameStr, cmpPocContactStr, cmpPocDesigStr,
                cmpPocLocationStr, cmpTypeStr, cmpindustryStr, cmpAboutStr, cmpPostedByStr, cmpDateToStr;

        regIdStr = values.getCmp_register_id();
        titleStr = values.getCmp_pojo_title();
        roleStr = values.getCmp_pojo_role();
        desigStr = values.getCmp_pojo_designation();
        jobTypeStr = values.getCmp_pojo_job_type();
        jobStateStr = values.getCmp_pojo_state();
        jobCitieStr = values.getCmp_pojo_cities();
        jobDescriptionStr = values.getCmp_pojo_description();
        jobSalaryTimeStr = values.getCmp_pojo_salary_time();
        minSalaryStr = values.getCmp_pojo_offering_min_salary();
        maxSalaryStr = values.getCmp_pojo_offering_min_salary();
        minAgeStr = values.getCmp_pojo_min_age();
        maxAgeStr = values.getCmp_pojo_min_age();
        localityStr = values.getCmp_pojo_locality();
        noOpeningStr = values.getCmp_pojo_opening();
        fresherCanStr = values.getCmp_pojo_fresher_can();
        experienceCanStr = values.getCmp_pojo_experience_can();
        minExpStr = values.getCmp_pojo_min_exp();
        maxExpStr = values.getCmp_pojo_max_exp();
        educationStr = values.getCmp_pojo_education();
        maleFemaleStr = values.getCmp_pojo_male_or_female();
        englishKnowStr = values.getCmp_pojo_english_know();
        languageStr = values.getCmp_pojo_language_know();
        vehicleStr = values.getCmp_pojo_vehicle();
        processorStr = values.getCmp_pojo_processor();
        phoneStr = values.getCmp_pojo_phone();
        documentStr = values.getCmp_pojo_documents();
        workingDayStr = values.getCmp_pojo_working_day();
        dayFromStr = values.getCmp_pojo_day_shift_from();
        dayToStr = values.getCmp_pojo_day_shift_to();
        nightFromStr = values.getCmp_pojo_night_shift_from();
        nightToStr = values.getCmp_pojo_night_shift_to();
        rotateFromStr = values.getCmp_pojo_rotate_shift_from();
        rotateToStr = values.getCmp_pojo_rotate_shift_to();
        reimStr = values.getCmp_pojo_reimbursement();
        incentiveStr = values.getCmp_pojo_incentive();
        depositingStr = values.getCmp_pojo_depositing();
        depoAmountStr = values.getCmp_pojo_deposit_amount();
        depoPurpStr = values.getCmp_pojo_deposit_purpose();
        cmpNameStr = values.getCmp_pojo_company_name();
        jobCmpEmailStr = values.getCmp_pojo_company_email();
        cmpPocNameStr = values.getCmp_pojo_company_poc_name();
        cmpPocContactStr = values.getCmp_pojo_company_poc_contact();
        cmpPocDesigStr = values.getCmp_pojo_company_poc_designation();
        cmpPocLocationStr = values.getCmp_pojo_company_poc_head_office_location();
        cmpTypeStr = values.getcmp_pojo_company_type();
        cmpindustryStr = values.getCmp_pojo_industry();
        cmpAboutStr = values.getCmp_pojo_company_about();
        cmpPostedByStr = values.getCmp_pojo_company_posted_by();
        cmpDateToStr = values.getCmp_pojo_date_time();


        final Dialog progressDialog = new Dialog(context);
        progressDialog.setTitle("Deleting...");
        progressDialog.show();

        String dateStr = getDateTime();

        Api_cmp_post_job apiInterface = ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call = apiInterface.cmpPostJobDelete(
                dateStr, regIdStr, titleStr, roleStr, desigStr, jobTypeStr, jobStateStr, jobCitieStr, jobDescriptionStr,
                jobSalaryTimeStr, minSalaryStr, maxSalaryStr, minAgeStr, maxAgeStr, localityStr, noOpeningStr,
                fresherCanStr, experienceCanStr, minExpStr, maxExpStr, educationStr, maleFemaleStr, englishKnowStr,
                languageStr, vehicleStr, processorStr, phoneStr, documentStr, workingDayStr, dayFromStr, dayToStr,
                nightFromStr, nightToStr, rotateFromStr, rotateToStr, reimStr, incentiveStr, depositingStr, depoAmountStr,
                depoPurpStr, cmpNameStr, jobCmpEmailStr, cmpPocNameStr, cmpPocContactStr, cmpPocDesigStr,
                cmpPocLocationStr, cmpTypeStr, cmpindustryStr, cmpAboutStr, cmpPostedByStr, cmpDateToStr
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        deleteOperation(values);
                    } else {
                        Toast.makeText(context, Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void deleteOperation(cmpAplcResponse values) {

        final Dialog progressDialog = new Dialog(context);
        progressDialog.setTitle("Deleting...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call = apiInterface.cmpDeletePostedJobs(values.getCmp_post_job_id());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, MyCandidateActivity.class));
                        ((Activity) context).finish();
                    } else {
                        Toast.makeText(context, Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Exception: " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cmpAdlcList.size();
    }

    public class CmpAplcViewHolder extends RecyclerView.ViewHolder {

        //new Card
        CardView cmpJobCardView;
        ImageView cmpDeleteIv;
        TextView pojoViewCount, pojoRole, pojoDesignation, pojoCmpName, pojoLocation, pojoCity, pojoIndustry,
                pojoType, pojoMinSalary, pojoMaxSalary, pojoMinExp, pojoMaxExp, pojoQualification;

        public CmpAplcViewHolder(@NonNull View view) {
            super(view);

            cmpJobCardView = view.findViewById(R.id.updated_posted_job_4_cmp_card_view);
            cmpDeleteIv = view.findViewById(R.id.updated_posted_job_4_cmp_share_iv);
            pojoViewCount = view.findViewById(R.id.updated_posted_job_4_cmp_count_btn);

            pojoRole = view.findViewById(R.id.updated_posted_job_4_cmp_role);
            pojoDesignation = view.findViewById(R.id.updated_posted_job_4_cmp_designation);
            pojoCmpName = view.findViewById(R.id.updated_posted_job_4_cmp_company_name);
            pojoLocation = view.findViewById(R.id.updated_posted_job_4_cmp_locality);
            pojoCity = view.findViewById(R.id.updated_posted_job_4_cmp_city);
            pojoIndustry = view.findViewById(R.id.updated_posted_job_4_cmp_cmp_industry);
            pojoType = view.findViewById(R.id.updated_posted_job_4_cmp_type);
            pojoMinSalary = view.findViewById(R.id.updated_posted_job_4_cmp_min_salary);
            pojoMaxSalary = view.findViewById(R.id.updated_posted_job_4_cmp_max_salary);
            pojoMinExp = view.findViewById(R.id.updated_posted_job_4_cmp_min_exp);
            pojoMaxExp = view.findViewById(R.id.updated_posted_job_4_cmp_max_exp);
            pojoQualification = view.findViewById(R.id.updated_posted_job_4_cmp_qualification);

        }
    }
}
