package com.abc.justjob.Company.CompanyActivitys;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abc.justjob.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class cmpContactListDesign_company extends BottomSheetDialogFragment {
    private cmpContactListDesign.contactListListener listener;
    private TextView contact1,contact2,email,viewResume;
    private String contact1Str,contact2Str,emailStr,resumeStr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.cmp_contact_list_design,container,false);

        contact1=v.findViewById(R.id.cmp_contact_list_1);
        contact2=v.findViewById(R.id.cmp_contact_list_2);
        email = v.findViewById(R.id.cmp_contact_list_email);
        viewResume = v.findViewById(R.id.cmp_contact_list_resume);

        Bundle bundle=getArguments();
        contact1Str=bundle.getString("contact1");
        //contact2Str=bundle.getString("contact2");
        emailStr=bundle.getString("email");
        //resumeStr=bundle.getString("resumeUrl");

        contact1.setText(contact1Str);
        contact2.setVisibility(View.GONE);
        viewResume.setVisibility(View.GONE);

//        if (contact2Str.isEmpty()||contact2Str.equals("--")){
//            //contact2.setVisibility(View.GONE);
//            //viewResume.setVisibility(View.GONE);
//        }else {
//            contact2.setText(contact2Str);
//        }

        if (emailStr.isEmpty() || emailStr.equals("--")) {
            email.setVisibility(View.GONE);
        }else{
            email.setText(emailStr);
        }

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBottonClicked(contact1.getText().toString());
                //dismiss();
            }
        });
//        contact2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onBottonClicked(contact2.getText().toString());
//                //dismiss();
//            }
//        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBottonClicked(email.getText().toString());
                //dismiss();
            }
        });


//        viewResume.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onBottonClicked("resume_url");
//                dismiss();
//            }
//        });

        return v;
    }
    public interface contactListListener{
        void onBottonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener= (cmpContactListDesign.contactListListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement contactListListener");
        }
    }
}
