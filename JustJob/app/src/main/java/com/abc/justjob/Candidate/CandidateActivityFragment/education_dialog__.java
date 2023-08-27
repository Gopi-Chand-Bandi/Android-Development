package com.abc.justjob.Candidate.CandidateActivityFragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.justjob.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class education_dialog__ extends AppCompatDialogFragment {

    private DatePickerDialog datePickerDialog;
    private int selected=1;

    private EditText startDateEd_dg,endDateEd_dg;
    private CheckBox currentPursuing_dg;
    private boolean startOrEnd_dg = true;
    private ArrayList<String> below_ssc,ssc_pass,hhc_pass,under_Graduate,
            GraduateList,post_graduat,PHD;
    //private ArrayList<String> qlfChieldAdapter;
    private String[] listStream;
    private TextInputLayout quali_std_lay,quali_stream_lay,quali_other_stream_lay,quali_clg_lay;
    /*private TextInputEditText qualfClgNameEt_dg;*/
    private TextInputEditText quali_std,quali_stream,quali_clg;
    /* private String qualiFirstStr="",qualiSecontStr="",qualiCollege="",
             qualiStartDate="",qualiEndDate="";*/
    private Button saveBtnDg;
    private EducationDialogListener__ listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.alertDialog);

        final Dialog dialog=new Dialog(getActivity());

        LayoutInflater inflater= getActivity().getLayoutInflater();
        @SuppressLint("InflateParams")
        View view=inflater.inflate(R.layout.education_dialog,null);

//        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
//        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.setTitle("Add Qualification");
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        quali_std=view.findViewById(R.id.quali_std);
        quali_stream=view.findViewById(R.id.quali_stream);
        quali_clg=view.findViewById(R.id.quali_clg);

        quali_std_lay=view.findViewById(R.id.quali_std_lay);
        quali_stream_lay=view.findViewById(R.id.quali_stream_lay);
        quali_other_stream_lay=view.findViewById(R.id.quali_other_stream_lay);
        quali_clg_lay=view.findViewById(R.id.quali_clg_lay);

        currentPursuing_dg=view.findViewById(R.id.edu_pursuing_chk_dg);
        startDateEd_dg=view.findViewById(R.id.edu_start_date_dg);
        endDateEd_dg=view.findViewById(R.id.edu_end_date_dg);
        saveBtnDg=view.findViewById(R.id.edu_save_btn_dialog);

        arraylistDropdown();

        quali_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForStd();
            }
        });

        quali_std.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                validateStdText(s);
            }
        });

        quali_stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForStream();
            }
        });

        quali_other_stream_lay.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateStreamText(s);
            }
        });

        quali_stream.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                validateStreamText(s);
            }
        });
        quali_clg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                validateTextChange(s);
            }
        });

        quali_clg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quali_clg.setCursorVisible(true);
            }
        });
        currentPursuing_dg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (currentPursuing_dg.isChecked()){
                    endDateEd_dg.setVisibility(View.INVISIBLE);
                }else if (!currentPursuing_dg.isChecked()){
                    endDateEd_dg.setVisibility(View.VISIBLE);
                }
            }
        });
        startDateEd_dg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quali_clg.setCursorVisible(false);

                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startDateEd_dg.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
        endDateEd_dg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endDateEd_dg.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        saveBtnDg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String qualiFirstStr,qualiSecontStr,qualiCollege,qualiStartDate,qualiEndDate;

                qualiFirstStr= Objects.requireNonNull(quali_std_lay.getEditText()).getText().toString();

                if (quali_stream.getText().toString().equals("Other")) {
                    qualiSecontStr=quali_other_stream_lay.getEditText().getText().toString();
                }else {
                    qualiSecontStr = Objects.requireNonNull(quali_stream_lay.getEditText()).getText().toString();
                }
                qualiCollege= Objects.requireNonNull(quali_clg_lay.getEditText().getText()).toString().trim();
                qualiStartDate=startDateEd_dg.getText().toString();

                if (quali_std_lay.getEditText().getText().toString().isEmpty()){
                    quali_std_lay.setError("Field Require");
                }else if (qualiSecontStr.isEmpty()) {
                    quali_stream_lay.setError("Field Require");
                }else if (quali_clg_lay.isShown() && qualiCollege.equals("") || !validateCollegeName()){
                    quali_clg_lay.setError("Enter College Name");
                }else if (qualiStartDate.equals("")){

                    startDateEd_dg.setError("Field Require");

                }else if (endDateEd_dg.isShown() && endDateEd_dg.getText().toString().equals("")||!validateEndDate()){

                    endDateEd_dg.setError("Field Require");

                }else {

                    if (!endDateEd_dg.isShown()){
                        qualiEndDate="--";
                    }else {
                        qualiEndDate = endDateEd_dg.getText().toString();
                    }

//                    Toast.makeText(getContext(),qualiFirstStr+"\n"+qualiSecontStr+"\n"+qualiCollege+"\n"+qualiStartDate+"\n"+qualiEndDate , Toast.LENGTH_SHORT).show();
                    beforvarification(qualiFirstStr, qualiSecontStr, qualiCollege, qualiStartDate, qualiEndDate);

                    dialog.dismiss();
                }
            }
        });

        dialog.setCancelable(false);
        return dialog;
    }

    private void validateStreamText(Editable s){
        if (!TextUtils.isEmpty(s)){
            quali_stream_lay.setError(null);
        }else{
            quali_stream_lay.setError("Require Field");
        }
    }

    private void validateStdText(Editable s) {
        if (!TextUtils.isEmpty(s)){
            quali_std_lay.setError(null);
        }else {
            quali_std_lay.setError("Require Field");
        }
    }

    private void validateTextChange(Editable s) {
        if (!TextUtils.isEmpty(s)){
            quali_clg_lay.setError(null);
        }else{
            quali_clg_lay.setError("Require Field");
        }
    }


    private void showDialogForStream() {
        final AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Select Job Stream");
        mBuilder.setSingleChoiceItems(listStream, selected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (listStream[which].equals("Other")) {
                    quali_other_stream_lay.setVisibility(View.VISIBLE);
                }else{
                    quali_other_stream_lay.setVisibility(View.GONE);
                }
                quali_stream.setText(listStream[which]);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void showDialogForStd() {
        final String[] listStd=getResources().getStringArray(R.array.qualification);

        final AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());

        mBuilder.setTitle("Select Job STD");
        mBuilder.setSingleChoiceItems(listStd, selected,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position==1){

                    quali_stream_lay.setVisibility(View.VISIBLE);
                    quali_clg_lay.setVisibility(View.VISIBLE);
                    quali_stream_lay.setHint("Select Stream");
                    quali_clg_lay.setHint("School Name");
                    dialog.dismiss();
                    listStream=getResources().getStringArray(R.array.below_ssc);

                }else if (position==2){
                    quali_stream_lay.setVisibility(View.VISIBLE);
                    quali_clg_lay.setVisibility(View.VISIBLE);
                    quali_stream_lay.setHint("Select Stream");
                    quali_clg_lay.setHint("College Name");
                    dialog.dismiss();
                    listStream=getResources().getStringArray(R.array.ssc_pass);
                }else if (position==3){
                    quali_stream_lay.setVisibility(View.VISIBLE);
                    quali_clg_lay.setVisibility(View.VISIBLE);
                    quali_stream_lay.setHint("Select Stream");
                    quali_clg_lay.setHint("College Name");
                    dialog.dismiss();
                    listStream=getResources().getStringArray(R.array.hhc_pass);

                }else if (position==4){

                    quali_stream_lay.setVisibility(View.VISIBLE);
                    quali_clg_lay.setVisibility(View.VISIBLE);
                    quali_stream_lay.setHint("Select Stream");
                    quali_clg_lay.setHint("University Name");
                    dialog.dismiss();
                    listStream=getResources().getStringArray(R.array.under_Graduate);
                }else if (position==5){
                    quali_stream_lay.setVisibility(View.VISIBLE);
                    quali_clg_lay.setVisibility(View.VISIBLE);
                    quali_stream_lay.setHint("Select Stream");
                    quali_clg_lay.setHint("University Name");
                    dialog.dismiss();
                    listStream=getResources().getStringArray(R.array.GraduateList);
                }else if (position==6){

                    quali_stream_lay.setVisibility(View.VISIBLE);
                    quali_clg_lay.setVisibility(View.VISIBLE);
                    quali_stream_lay.setHint("Select Stream");
                    quali_clg_lay.setHint("University Name");
                    dialog.dismiss();
                    listStream=getResources().getStringArray(R.array.post_graduat);
                }else if (position==7){

                    quali_stream_lay.setVisibility(View.VISIBLE);
                    quali_clg_lay.setVisibility(View.VISIBLE);
                    quali_stream_lay.setHint("Select Stream");
                    dialog.dismiss();
                    quali_clg_lay.setHint("University Name");
                    listStream=getResources().getStringArray(R.array.PHD);
                }else {
                    quali_stream_lay.setVisibility(View.GONE);
                    quali_clg.setVisibility(View.GONE);
                    dialog.dismiss();
                }
                quali_std.setText(listStd[position]);
            }
        });
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private boolean validateStream(){
        String stream=quali_stream_lay.getEditText().getText().toString();
        if (stream.isEmpty()){
            quali_stream_lay.setError("Field is Empty");
            return false;
        }else{
            quali_stream_lay.setError(null);
            return true;
        }

    }
    private boolean validateEndDate(){
        String endDate=endDateEd_dg.getText().toString();
        if (endDateEd_dg.isShown() && endDate.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    private boolean validateStd(){

        String std=quali_std_lay.getEditText().getText().toString();

        if (std.isEmpty()){
            quali_std_lay.setError("Field is Empty");
            return false;
        }else{
            quali_std_lay.setError(null);
            return true;
        }
    }

    private boolean validateCollegeName(){
        String college=quali_clg_lay.getEditText().getText().toString().trim();
        if (college.isEmpty()){
            quali_clg_lay.setError("Field is Empty");
            return false;
        }else{
            quali_clg_lay.setError(null);
            return true;
        }
    }

    private void beforvarification(String qualiFirstStr, String qualiSecontStr, String qualiCollege, String qualiStartDate,
                                   String qualiEndDate) {
        listener.applyTexts(qualiFirstStr,qualiSecontStr,qualiCollege,
                qualiStartDate,qualiEndDate);

    }

    private void arraylistDropdown() {

        below_ssc=new ArrayList<>();
        below_ssc.add("First Class");
        below_ssc.add("Second Class");
        below_ssc.add("Third Class");
        below_ssc.add("Fourth Class");
        below_ssc.add("Fifth Class");
        below_ssc.add("Sixth Class");
        below_ssc.add("Seventh Class");
        below_ssc.add("Eighth Class");
        below_ssc.add("Ninth Class");
        below_ssc.add("Tenth class");

        ssc_pass=new ArrayList<>();
        ssc_pass.add("State Board");
        ssc_pass.add("CBSE Board");
        ssc_pass.add("ICSE Board");

        hhc_pass=new ArrayList<>();
        hhc_pass.add("Science");
        hhc_pass.add("Commerce");
        hhc_pass.add("Arts");

        under_Graduate=new ArrayList<>();
        under_Graduate.add("BCA- Bachelor of Computer Applications");
        under_Graduate.add("B.Sc.- Information Technology");
        under_Graduate.add("BPT- Bachelor of Physiotherapy");
        under_Graduate.add("B.Sc- Applied Geology");
        under_Graduate.add("B.Sc.- Physics");
        under_Graduate.add("B.Sc. Mathematics");
        under_Graduate.add("B.Sc. Chemistry");
        under_Graduate.add("B.Sc- Nursing");
        under_Graduate.add("B.Pharma- Bachelor of Pharmacy");
        under_Graduate.add("B.Com (Hons.)");
        under_Graduate.add("BA (Hons.) in Economics");
        under_Graduate.add("BBA- Bachelor of Business Administration");
        under_Graduate.add("BBA- Bachelor of Business Administration");
        under_Graduate.add("Integrated Law Program- BBA LL.B");

        GraduateList=new ArrayList<>();
        GraduateList.add("BCA- Bachelor of Computer Applications");
        GraduateList.add("B.Sc.- Information Technology");
        GraduateList.add("BPT- Bachelor of Physiotherapy");
        GraduateList.add("B.Sc- Applied Geology");
        GraduateList.add("B.Sc.- Physics");
        GraduateList.add("B.Sc. Mathematics");
        GraduateList.add("B.Sc. Chemistry");
        GraduateList.add("B.Sc- Nursing");
        GraduateList.add("BPharma- Bachelor of Pharmacy");
        GraduateList.add("B.Com (Hons.)");
        GraduateList.add("BA (Hons.) in Economics");
        GraduateList.add("BBA- Bachelor of Business Administration");
        GraduateList.add("BBA- Bachelor of Business Administration");
        GraduateList.add("Integrated Law Program- BBA LL.B");

        post_graduat=new ArrayList<>();
        post_graduat.add("Master of Arts (MA)");
        post_graduat.add("Master of Science (MSc)");
        post_graduat.add("Master of Fine Arts (MFA)");
        post_graduat.add("Master of Letters (MLitt)");
        post_graduat.add("Master of Laws (LLM)");
        post_graduat.add("Master of Engineering (MEng)");
        post_graduat.add("Integrated Masters");
        post_graduat.add("Postgraduate Certificate (PGCert) and Postgraduate Diploma (PGDip)");
        post_graduat.add("Legal Practice Course (LPC)");
        post_graduat.add("Graduate Diploma in Law (GDL)");
        post_graduat.add("Master of Business Administration (MBA)");
        post_graduat.add("Masters in Management (MiM");

        PHD=new ArrayList<>();
        PHD.add("Biochemistry");
        PHD.add("Bioinformatics");
        PHD.add("Biophysics");
        PHD.add("Biotechnology");
        PHD.add("Food Science / Nutrition");
        PHD.add("Chemical Engineering");
        PHD.add("Chemical Toxicology");
        PHD.add("Computational Chemistry");
        PHD.add("Electrochemistry");
        PHD.add("Food Chemistry");
        PHD.add("Astrophysics");
        PHD.add("Atomic Physics");
        PHD.add("Biophysics");
        PHD.add("Metrology");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener= (EducationDialogListener__) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "Must Implement EducationListener");
        }
    }

    public interface EducationDialogListener__{

        void applyTexts(String qualiFirstStr, String qualiSecontStr,
                        String qualiCollege, String qualiStartDate,
                        String qualiEndDate);
    }
}