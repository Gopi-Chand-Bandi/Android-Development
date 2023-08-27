package com.abc.justjob.ApiFile.CndAplcCallHistory;

import android.content.Context;
import android.content.Intent;
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

import com.abc.justjob.ApiFile.CompiesDetails.CompaniesContactedFrgValues;
import com.abc.justjob.Candidate.CndHomeDetailFrgActivity.cndHomePostedJobInfoActivity;
import com.abc.justjob.R;

import java.util.ArrayList;
import java.util.List;

public class CndAplcCallHistoryFrgAdaper extends RecyclerView.Adapter<CndAplcCallHistoryFrgAdaper.CndViewHolder> implements Filterable {

    private List<CndAplcCallHistoryFrgValues> cndAplcCallHistoryFrgValuesList;
    private List<CndAplcCallHistoryFrgValues> cndAplcCallHistoryFrgFilterList;
    private Context context;
    private String sample;
    private boolean homeBl;
    private int clickCount=0;
    int num=1;

    public CndAplcCallHistoryFrgAdaper(List<CndAplcCallHistoryFrgValues> cndAplcCallHistoryFrgValuesList, Context context,boolean homeBl){

        this.cndAplcCallHistoryFrgValuesList = cndAplcCallHistoryFrgValuesList;
        this.cndAplcCallHistoryFrgFilterList = cndAplcCallHistoryFrgValuesList;
        this.context = context;
        this.homeBl = homeBl;

    }

    @NonNull
    @Override
    public CndAplcCallHistoryFrgAdaper.CndViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        return new CndAplcCallHistoryFrgAdaper.CndViewHolder(LayoutInflater.from(context).inflate(R.layout.cnd_aplc_call_history_design,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CndAplcCallHistoryFrgAdaper.CndViewHolder holder, int position) {
        final CndAplcCallHistoryFrgValues values = cndAplcCallHistoryFrgFilterList.get(position);

        holder.ContactTime.setText(values.getDate_and_time());
        holder.JobRole.setText(values.getJob_role());

        if (values.getState_from().equals("Candidate") && values.getState_to().equals("Company")){
            holder.CompanyName.setText(values.getContacted_to_name());
            if (values.getContacted_by_mobile().equals("true")){
                holder.CallSent.setVisibility(View.VISIBLE);
                holder.CallReceived.setVisibility(View.GONE);
                holder.MailSent.setVisibility(View.GONE);
                holder.MailReceived.setVisibility(View.GONE);
                holder.Resume.setVisibility(View.GONE);
            }else if (values.getContacted_by_email().equals("true")){
                holder.CallSent.setVisibility(View.GONE);
                holder.CallReceived.setVisibility(View.GONE);
                holder.MailSent.setVisibility(View.VISIBLE);
                holder.MailReceived.setVisibility(View.GONE);
                holder.Resume.setVisibility(View.GONE);
            }
        }else if (values.getState_from().equals("Company") && values.getState_to().equals("Candidate")){
            holder.CompanyName.setText(values.getContact_by_name());
            if (values.getContacted_by_mobile().equals("true")){
                holder.CallSent.setVisibility(View.GONE);
                holder.CallReceived.setVisibility(View.VISIBLE);
                holder.MailSent.setVisibility(View.GONE);
                holder.MailReceived.setVisibility(View.GONE);
                holder.Resume.setVisibility(View.GONE);
            }else if (values.getContacted_by_email().equals("true")){
                holder.CallSent.setVisibility(View.GONE);
                holder.CallReceived.setVisibility(View.GONE);
                holder.MailSent.setVisibility(View.GONE);
                holder.MailReceived.setVisibility(View.VISIBLE);
                holder.Resume.setVisibility(View.GONE);
            }else if (values.getContacted_by_resume().equals("true")){
                holder.CallSent.setVisibility(View.GONE);
                holder.CallReceived.setVisibility(View.GONE);
                holder.MailSent.setVisibility(View.GONE);
                holder.MailReceived.setVisibility(View.GONE);
                holder.Resume.setVisibility(View.VISIBLE);
            }
        }

        holder.companyContactCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, cndHomePostedJobInfoActivity.class);
                intent.putExtra("cmp_post_job_id",values.getJobid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return cndAplcCallHistoryFrgFilterList.size();

    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String newText = constraint.toString();
                if (newText.isEmpty()){
                    cndAplcCallHistoryFrgFilterList=cndAplcCallHistoryFrgValuesList;
                }else {
                    List<CndAplcCallHistoryFrgValues> filterList = new ArrayList<>();
                    for (CndAplcCallHistoryFrgValues values: cndAplcCallHistoryFrgValuesList){
                        if (values.getDate_and_time().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContact_by_id().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContact_by_name().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContact_by_email().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getJobid().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getJob_role().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContacted_to_id().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContacted_to_name().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContacted_to_email().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContacted_by_mobile().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContacted_by_email().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getContacted_by_resume().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getState_from().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }else if (values.getState_to().toLowerCase().contains(newText.toLowerCase())){
                            filterList.add(values);
                        }
                    }
                    cndAplcCallHistoryFrgFilterList = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = cndAplcCallHistoryFrgFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cndAplcCallHistoryFrgFilterList=(ArrayList<CndAplcCallHistoryFrgValues>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CndViewHolder extends RecyclerView.ViewHolder{

        CardView companyContactCardView;
        TextView CompanyName,ContactTime,JobRole;
        LinearLayout CallSent,CallReceived,MailSent,MailReceived,Resume;
        public CndViewHolder(@NonNull View view) {

            super(view);
            companyContactCardView = view.findViewById(R.id.cnd_call_history_info_card_view);
            CompanyName = view.findViewById(R.id.cnd_call_history_info_cmp_name);
            JobRole = view.findViewById(R.id.cnd_call_history_info_job_role);
            ContactTime = view.findViewById(R.id.cnd_call_history_info_date_time);
            CallSent = view.findViewById(R.id.cnd_call_history_call_made);
            CallReceived = view.findViewById(R.id.cnd_call_history_call_got);
            MailSent = view.findViewById(R.id.cnd_call_history_email_made);
            MailReceived = view.findViewById(R.id.cnd_call_history_email_got);
            Resume = view.findViewById(R.id.cnd_call_history_resume);

        }

    }

}
