package com.abc.justjob.Company.TransectionHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.abc.justjob.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class transectionAdapter extends RecyclerView.Adapter<transectionAdapter.transectionViewHolder> {

    private List<transectionModel> list;
    private Context context;

    public transectionAdapter(List<transectionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NotNull
    @Override
    public transectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new transectionAdapter.transectionViewHolder(LayoutInflater.from(context).
                inflate(R.layout.transection_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull transectionAdapter.transectionViewHolder holder, int position) {

        transectionModel model = list.get(position);

        holder.amountTv.setText(model.getCmp_pay_mrp());
        holder.cndAccessTv.setText(model.getCmp_data_acccess()+" Candidate Data Access");
        holder.daysTv.setText(model.getCmp_days());
        holder.transectionIdTv.setText(model.getCmp_transection_id());
        holder.dateTv.setText(model.getCmp_payed_date());

        holder.ivExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.planeDetailsLl.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(holder.planeCard, new AutoTransition());
                    holder.planeDetailsLl.setVisibility(View.VISIBLE);
                    holder.ivExpand.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(holder.planeCard, new AutoTransition());
                    holder.planeDetailsLl.setVisibility(View.GONE);
                    holder.ivExpand.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class transectionViewHolder extends RecyclerView.ViewHolder {

        CardView planeCard;
        ImageView ivExpand;
        LinearLayout planeDetailsLl;
        TextView amountTv,cndAccessTv,daysTv,transectionIdTv,dateTv;

        public transectionViewHolder(@NonNull View itemView) {
            super(itemView);

            planeCard=itemView.findViewById(R.id.transaction_design_card);
            ivExpand=itemView.findViewById(R.id.trans_expand_iv);
            planeDetailsLl=itemView.findViewById(R.id.planeDetailLayout);
            amountTv=itemView.findViewById(R.id.trans_purch_amount_tv);
            cndAccessTv=itemView.findViewById(R.id.trans__cnd_access_tv);
            daysTv=itemView.findViewById(R.id.trans_days_tv);
            transectionIdTv=itemView.findViewById(R.id.trans_transection_id);
            dateTv=itemView.findViewById(R.id.trans_date_tv);

        }
    }
}
