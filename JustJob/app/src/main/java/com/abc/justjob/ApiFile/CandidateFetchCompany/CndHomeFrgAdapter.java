package com.abc.justjob.ApiFile.CandidateFetchCompany;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.Candidate.CndHomeDetailFrgActivity.cndHomePostedJobInfoActivity;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CndHomeFrgAdapter extends RecyclerView.Adapter<CndHomeFrgAdapter.CndViewHolder> implements Filterable {

    private List<CndHomeFrgValues> cndHomeFrgValuesList;
    private List<CndHomeFrgValues> cndHomeFrgFilterList;
    private Context context;
    private String sample;
    private boolean homeBl;
    private int clickCount = 0;
    int num = 1;

    public CndHomeFrgAdapter(List<CndHomeFrgValues> cndHomeFrgValuesList,Context context,boolean homeBl) {
        this.cndHomeFrgValuesList = cndHomeFrgValuesList;
        this.cndHomeFrgFilterList = cndHomeFrgValuesList;
        this.context = context;
        this.homeBl=homeBl;
    }

    @NonNull
    @Override
    public CndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();

        return new CndHomeFrgAdapter.CndViewHolder(LayoutInflater.from(context).inflate(R.layout.updated_posted_job_design_4_cnd,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CndViewHolder holder, int position) {
        final CndHomeFrgValues values = cndHomeFrgFilterList.get(position);

        holder.pojoRole.setText(values.getCmp_pojo_role());
        holder.pojoDesignation.setText(values.getCmp_pojo_designation());
        holder.pojoCmpName.setText(values.getCmp_pojo_company_name());
        holder.pojoState.setText(values.getCmp_pojo_state());
        holder.pojoCity.setText(values.getCmp_pojo_cities());
        holder.pojoIndustry.setText(values.getCmp_pojo_industry());
        holder.pojoType.setText(values.getCmp_pojo_job_type());
        holder.pojoMinSalary.setText(values.getCmp_pojo_offering_min_salary());
        holder.pojoMaxSalary.setText(values.getCmp_pojo_offering_max_salary());
        holder.pojoMinExp.setText(values.getCmp_pojo_min_exp());
        holder.pojoMaxExp.setText(values.getCmp_pojo_max_exp());
        holder.pojoQualification.setText(values.getCmp_pojo_education());
        if (values.getCmp_job_status().length()==22){
            sample= values.getCmp_job_status();
            holder.pojostatus.setText(sample.substring(0,10));
        }else {
            holder.pojostatus.setText(values.getCmp_job_status());
        }
        holder.pojopostedby.setText(values.getCmp_pojo_company_posted_by());

        holder.pojoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean homeBool;
                if (homeBl){
                    addViewCount(values.getCmp_post_job_id());
                    homeBool=true;
                }else{
                    homeBool=false;
                }

                Intent intent=new Intent(context, cndHomePostedJobInfoActivity.class);
                intent.putExtra("cnd_home_frg",homeBool);
                intent.putExtra("cmp_post_job_id",values.getCmp_post_job_id());
                context.startActivity(intent);

            }
        });

        holder.cmpBtnShair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Temporary closed.", Toast.LENGTH_SHORT).show();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this link");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.abc.justjob");
                context.startActivity(Intent.createChooser(sharingIntent, "Share link using"));
            }
        });
    }

    private void addViewCount(String cmp_post_job_id) {

        String cndRegisterId= SharedPrefManager.getInstance(context).getValueOfUserId(context);
        Api_cmp_post_job api= ApiClient.getApiClient().create(Api_cmp_post_job.class);
        Call<Result> call=api.cndCountViewPostJobs(cndRegisterId,cmp_post_job_id);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                try {
                    Log.d("Appling message: ", response.body().getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cndHomeFrgFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String newText = charSequence.toString();
                if (newText.isEmpty()) {
                    cndHomeFrgFilterList = cndHomeFrgValuesList;
                } else {
                    List<CndHomeFrgValues> filterList = new ArrayList<>();
                    for (CndHomeFrgValues values : cndHomeFrgValuesList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (values.getCmp_pojo_role().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCmp_pojo_locality().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCmp_pojo_company_name().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCmp_pojo_description().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCmp_pojo_job_type().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        } else if (values.getCmp_pojo_company_type().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        }else if (values.getCmp_pojo_cities().toLowerCase().contains(newText.toLowerCase())) {
                            filterList.add(values);
                        }else if (values.getCmp_job_status().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getCmp_pojo_company_posted_by().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }
                    }
                    cndHomeFrgFilterList = filterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cndHomeFrgFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cndHomeFrgFilterList = (ArrayList<CndHomeFrgValues>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CndViewHolder extends RecyclerView.ViewHolder {

        ImageView cmpBtnShair;
        CardView pojoCardView;
        TextView pojoRole,pojoDesignation,pojoCmpName,pojoState,pojoCity,pojoIndustry,pojoType,pojoMinSalary,pojoMaxSalary,pojoMinExp,pojoMaxExp,pojoQualification,pojostatus,pojopostedby;

        public CndViewHolder(@NonNull View view) {
            super(view);

            cmpBtnShair=view.findViewById(R.id.updated_posted_job_share_iv);
            pojoCardView=view.findViewById(R.id.cand_view_design_updated);
            pojoRole=view.findViewById(R.id.updated_posted_job_role);
            pojoDesignation=view.findViewById(R.id.updated_posted_job_designation);
            pojoCmpName=view.findViewById(R.id.updated_posted_job_company_name);
            pojoCity=view.findViewById(R.id.updated_posted_job_city);
            pojoState=view.findViewById(R.id.updated_posted_job_state);
            pojoIndustry=view.findViewById(R.id.updated_posted_job_cmp_industry);
            pojoType=view.findViewById(R.id.updated_posted_job_type);
            pojoMinSalary=view.findViewById(R.id.updated_posted_job_min_salary);
            pojoMaxSalary=view.findViewById(R.id.updated_posted_job_max_salary);
            pojoMinExp=view.findViewById(R.id.updated_posted_job_min_exp);
            pojoMaxExp=view.findViewById(R.id.updated_posted_job_max_exp);
            pojoQualification=view.findViewById(R.id.updated_posted_job_qualification);
            pojostatus=view.findViewById(R.id.posted_job_status);
            pojopostedby=view.findViewById(R.id.posted_job_info);
        }
    }

}