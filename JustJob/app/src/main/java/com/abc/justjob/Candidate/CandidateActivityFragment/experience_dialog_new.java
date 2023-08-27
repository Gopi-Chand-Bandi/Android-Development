package com.abc.justjob.Candidate.CandidateActivityFragment;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.justjob.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class experience_dialog_new extends AppCompatDialogFragment {

    private DatePickerDialog datePickerDialog;
    private TextInputLayout cmpNameEt,cmpCurrentSalaryEt,otherIndustryEt,otherRoleEt;
    private TextView expIndustrySp,expRoleSp;
    private AppCompatSpinner expDesignationSp;
    ArrayList<String> industryList,roleList;
    Dialog dialog;
    private Spinner spSalaryTime;
    private CheckBox workingCheckBox;
    private boolean workingIsChecked;
    private EditText startDateEt,endDateEt;

    private Button expSaveBtn;

    private expListener listener;
    private String expDesignationSt;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Dialog builder=new Dialog(getActivity());
        final LayoutInflater inflater= requireActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.experience_dialog_new,null);

//        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
//        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
        builder.setContentView(view);
        builder.setTitle("Add Experience");
        builder.setCanceledOnTouchOutside(false);
//        builder.getWindow().setLayout(width,height);
        builder.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        cmpNameEt=view.findViewById(R.id.exp_dig_new_company_name);
        spSalaryTime=view.findViewById(R.id.exp_dig_new_company_salary_time);
        cmpCurrentSalaryEt=view.findViewById(R.id.exp_dig_new_company_salary);
        expIndustrySp=view.findViewById(R.id.exp_dig_new_company_industry_sp);
        otherIndustryEt=view.findViewById(R.id.exp_dig_new_company_other_industry_et_lay);
        expRoleSp=view.findViewById(R.id.exp_dig_new_role_sp);
        otherRoleEt=view.findViewById(R.id.exp_dig_new_other_role_et);
        expDesignationSp=view.findViewById(R.id.exp_dig_new_designation_sp);

        workingCheckBox=view.findViewById(R.id.exp_dig_new_working_cBox);
        startDateEt=view.findViewById(R.id.start_date_exp_dg);
        endDateEt=view.findViewById(R.id.end_date_exp_dg);

        expSaveBtn=view.findViewById(R.id.exp_dig_new_save_btn);

        operations();

        expSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                validationOperation();

                if (cmpNameEt.getEditText().getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Company Name getting Empty...!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (expIndustrySp.getText().equals("Select Industry") || expIndustrySp.getText().toString().equals("Industry")) {
                    Toast.makeText(getContext(), "Select Industry...!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (expRoleSp.getText().equals("Select Job Profile") || expRoleSp.getText().toString().equals("Role")) {
                    Toast.makeText(getContext(), "Select Role...!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (expDesignationSt.isEmpty()) {
                    Toast.makeText(getContext(), "Select designation...!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!workingIsChecked && endDateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "End date is getting empty...!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (cmpCurrentSalaryEt.getEditText().getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Current Salary is getting empty...!", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    String expCmpNameStr,expCurrentSalaryStr, expIndustryStr = "", expRoleStr = "", expDesignationStr, expStartDateStr, expEndDateStr;

                    expCmpNameStr = cmpNameEt.getEditText().getText().toString();

                    expCurrentSalaryStr=cmpCurrentSalaryEt.getEditText().getText().toString() +" /"+
                            spSalaryTime.getSelectedItem().toString();

                    expIndustryStr = expIndustrySp.getText().toString();

                    expRoleStr = expRoleSp.getText().toString();

                    expDesignationStr = expDesignationSt;

                    expStartDateStr = startDateEt.getText().toString();

                    if (!workingIsChecked) {
                        expEndDateStr=endDateEt.getText().toString();
                    }else{
                        expEndDateStr="--";
                    }
                    validated(expCmpNameStr,expCurrentSalaryStr,expIndustryStr,expRoleStr,expDesignationStr,expStartDateStr,expEndDateStr);
                    builder.dismiss();
                }
            }
        });
        return builder;
    }

    private void operations() {

        industryList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.company_industry)));
        roleList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.job_profile)));

        expIndustrySp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(950,1600);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, industryList);
                listView.setAdapter(itemsAdapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        itemsAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        dialog.dismiss();

                        if (itemsAdapter.getItem(position).equals("Other")) {
                            otherIndustryEt.setVisibility(View.VISIBLE);
                            expIndustrySp.setText("");
                        }else{
                            otherIndustryEt.setVisibility(View.GONE);
                            expIndustrySp.setText(itemsAdapter.getItem(position));
                        }
                    }
                });
                otherIndustryEt.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        expIndustrySp.setText("");
                        expIndustrySp.setText(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        expRoleSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.searchable_spinner_layout);
                dialog.getWindow().setLayout(950,1600);
                dialog.show();
                EditText editText=dialog.findViewById(R.id.et_search_text);
                ListView listView=dialog.findViewById(R.id.list_item_search);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, roleList);
                listView.setAdapter(itemsAdapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        itemsAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (itemsAdapter.getItem(position).equals("Other")) {
                            otherRoleEt.setVisibility(View.VISIBLE);
                            expRoleSp.setText("");
                        }else{
                            otherRoleEt.setVisibility(View.GONE);
                            expRoleSp.setText(itemsAdapter.getItem(position));
                        }
                        dialog.dismiss();
                    }
                });
                otherRoleEt.getEditText().addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        expRoleSp.setText("");
                        expRoleSp.setText(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        expDesignationSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItemPosition() == 0) {
                    expDesignationSt="";
                }else {
                    expDesignationSt = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        workingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    endDateEt.setVisibility(View.INVISIBLE);
                    workingIsChecked=true;
                }else{
                    workingIsChecked=false;
                    endDateEt.setVisibility(View.VISIBLE);
                }
            }
        });
        startDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDateEt.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        endDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endDateEt.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    private void validationOperation() {


    }

    private void validated(String expCmpNameStr,String expCurrentSalaryStr, String expIndustryStr, String expRoleStr, String expDesignationStr, String expStartDateStr, String expEndDateStr) {
//        Toast.makeText(getContext(), expCmpNameStr+"\n"+expIndustryStr+"\n"+expRoleStr+"\n"+expDesignationStr+"\n"+expStartDateStr+"\n"+expEndDateStr, Toast.LENGTH_SHORT).show();

        listener.experienceData(expCmpNameStr,expCurrentSalaryStr,expIndustryStr,expRoleStr,expDesignationStr,expStartDateStr,expEndDateStr);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener= (expListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface expListener{
        void experienceData(String expCmpNameStr,String expCurrentSalaryStr, String expIndustryStr, String expRoleStr, String expDesignationStr, String expStartDateStr, String expEndDateStr);
    }
}
