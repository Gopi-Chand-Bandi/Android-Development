package com.abc.justjob.ApiFile.OtherCompanies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.justjob.Company.CompanyActivitys.OtherCompanyDetailinfoActivity;
import com.abc.justjob.R;

import java.util.List;

public class OtherCompaniesFrgAdapter extends RecyclerView.Adapter<OtherCompaniesFrgAdapter.CndViewHolder> {

    private List<OtherCompaniesFrgValues> companiesDetailFrgValuesList;
    private List<OtherCompaniesFrgValues> companiesDetailFrgFilterList;
    private Context context;
    private String sample;
    private boolean homeBl;
    private int clickCount=0;
    int num=1;

    public OtherCompaniesFrgAdapter(List<OtherCompaniesFrgValues> companiesDetailFrgValuesList, Context context,boolean homeBl){
        this.companiesDetailFrgValuesList=companiesDetailFrgValuesList;
        this.companiesDetailFrgFilterList=companiesDetailFrgValuesList;
        this.context=context;
        this.homeBl=homeBl;
    }

    @NonNull
    @Override
    public CndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();

        return new OtherCompaniesFrgAdapter.CndViewHolder(LayoutInflater.from(context).inflate(R.layout.companies_details_card,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull OtherCompaniesFrgAdapter.CndViewHolder holder, int position){
        final OtherCompaniesFrgValues values=companiesDetailFrgFilterList.get(position);

        holder.companyName.setText(values.getCmp_company_name());
        holder.companyCity.setText(values.getCmp_head_office_location());
        holder.companyState.setText(values.getCmp_state());
        holder.companyIndustry.setText(values.getCmp_industry());
        holder.companyType.setText(values.getCmp_job_type());
        holder.companySize.setText(values.getEmp_size());
        holder.companyCategory.setText(values.getCmp_company_category());

        holder.companyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean homeBool;

                Intent intent = new Intent(context, OtherCompanyDetailinfoActivity.class);
                intent.putExtra("cmp_id",values.getCmp_id());
                intent.putExtra("cmp_company_name",values.getCmp_company_name());
                intent.putExtra("cmp_head_office_location",values.getCmp_head_office_location());
                intent.putExtra("cmp_industry",values.getCmp_industry());
                intent.putExtra("cmp_company_category",values.getCmp_company_category());
                intent.putExtra("cmp_job_type",values.getCmp_job_type());
                intent.putExtra("cmp_company_address",values.getCmp_company_address());
                intent.putExtra("cmp_about_company",values.getCmp_about_company());
                intent.putExtra("cmp_link",values.getCmp_link());
                intent.putExtra("emp_size",values.getEmp_size());
                intent.putExtra("cmp_state",values.getCmp_state());
                intent.putExtra("cmp_contact",values.getCmp_contact());
                intent.putExtra("cmp_email",values.getCmp_email());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return companiesDetailFrgFilterList.size();
    }

    public class CndViewHolder extends RecyclerView.ViewHolder{

        CardView companyCardView;
        TextView companyName,companyState,companyCity,companyIndustry,companyType,companySize,companyCategory;

        public CndViewHolder(@NonNull View view) {
            super(view);

            companyCardView=view.findViewById(R.id.company_card_view_design_updated);
            companyName=view.findViewById(R.id.company_name);
            companyState=view.findViewById(R.id.company_state);
            companyCity=view.findViewById(R.id.company_city);
            companyIndustry=view.findViewById(R.id.company_industry);
            companyType=view.findViewById(R.id.company_card_company_type);
            companySize=view.findViewById(R.id.company_card_company_size);
            companyCategory=view.findViewById(R.id.company_card_company_category);


        }
    }

}
