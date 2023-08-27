package com.abc.justjob.ApiFile.CompiesDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.justjob.R;

import java.util.ArrayList;
import java.util.List;

public class CompaniesContactedFrgAdapter extends RecyclerView.Adapter<CompaniesContactedFrgAdapter.CndViewHolder> implements Filterable {

    private List<CompaniesContactedFrgValues> companiesContactedFrgValuesList;
    private List<CompaniesContactedFrgValues> companiesContactedFrgFilterList;
    private Context context;
    private String sample;
    private boolean homeBl;
    private int clickCount=0;
    int num=1;

    public CompaniesContactedFrgAdapter(List<CompaniesContactedFrgValues> companiesContactedFrgValuesList, Context context,boolean homeBl){
        this.companiesContactedFrgValuesList=companiesContactedFrgValuesList;
        this.companiesContactedFrgFilterList=companiesContactedFrgValuesList;
        this.context=context;
        this.homeBl=homeBl;
    }

    @NonNull
    @Override
    public CompaniesContactedFrgAdapter.CndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new CompaniesContactedFrgAdapter.CndViewHolder(LayoutInflater.from(context).inflate(R.layout.candidate_companies_contact_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompaniesContactedFrgAdapter.CndViewHolder holder, int position) {
        final CompaniesContactedFrgValues values=companiesContactedFrgFilterList.get(position);
        holder.CompanyName.setText(values.getContacted_to_name());
        holder.ContactTime.setText(values.getDate_and_time());
        if (values.getContacted_by_mobile().equals("true")){
            holder.CallSent.setVisibility(View.GONE);
            holder.MailSent.setVisibility(View.VISIBLE);
        }else {
            holder.CallSent.setVisibility(View.VISIBLE);
            holder.MailSent.setVisibility(View.GONE);
        }

        //holder.CallSent.setVisibility(values.getContacted_by_mobile().equals("true") ? View.VISIBLE : View.GONE);
        //holder.CallReceived.setVisibility(values.getCall_received().equals("true") ? View.VISIBLE : View.GONE);
        //holder.CallReceived.setVisibility(View.GONE);
        //holder.MailSent.setVisibility(values.getContacted_by_email().equals("true") ? View.VISIBLE : View.GONE);
        //holder.MailReceived.setVisibility(values.getEmail_received().equals("true") ? View.VISIBLE : View.GONE);
        //holder.MailReceived.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return companiesContactedFrgFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String newText = constraint.toString();
                if (newText.isEmpty()){
                    companiesContactedFrgFilterList=companiesContactedFrgValuesList;
                }else {
                    List<CompaniesContactedFrgValues> filterList = new ArrayList<>();
                    for (CompaniesContactedFrgValues values: companiesContactedFrgValuesList){
                        if (values.getContacted_to_name().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getDate_and_time().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }
                    }
                    companiesContactedFrgFilterList = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = companiesContactedFrgFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                companiesContactedFrgFilterList=(ArrayList<CompaniesContactedFrgValues>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CndViewHolder extends RecyclerView.ViewHolder{
        CardView companyContactCardView;
        TextView CompanyName,ContactTime;
        LinearLayout CallSent,CallReceived,MailSent,MailReceived;
        public CndViewHolder(@NonNull View view) {
            super(view);
            companyContactCardView=view.findViewById(R.id.company_contact_card_view_design);
            CompanyName=view.findViewById(R.id.contacted_company_name);
            CallSent=view.findViewById(R.id.call_made_to_the_company);
            //CallReceived=view.findViewById(R.id.call_received_from_the_company);
            MailSent=view.findViewById(R.id.email_sent_to_the_company);
            //MailReceived=view.findViewById(R.id.email_received_from_the_company);
            ContactTime=view.findViewById(R.id.contact_company_time);
        }
    }
}
