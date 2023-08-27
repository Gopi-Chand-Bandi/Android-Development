package com.abc.justjob.Company.FilterOperation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abc.justjob.R;

public class FilterOperationActivity extends AppCompatActivity {

    //flt main lay and primary & secondary
    LinearLayout flt_layout, flt_prim_lay, flt_sec_lay;

    //primary part
    RelativeLayout findByGender_lay, findByRole_lay, findByQuali_lay, findByExp_lay, findByLanguage_lay;
    TextView findByGender, findByRole, findByQuali, findByExp, findByLanguage;

    //secondary part
    //flt click view secondary
    LinearLayout fltGenderView, fltQuliView, fltExpView, fltLangView;

    RelativeLayout CBMaleLayout, CBFemaleLayout, CBBothLayout, fltBlwSSC_Lay, fltSSCPass_Lay, fltHSCPass_Lay,
            fltUnderGraduate_Lay, fltGraduate_Lay, fltPostGraduate_Lay, fltPHD_Lay, fltFresher_lay, fltBlw1Yr_lay,
            flt1_2Yr_lay, flt3_5Yr_lay, flt6_9Yr_lay, flt10PYr_lay, fltHindi_lay, fltEnglish_lay, fltMarathi_lay,
            fltTamil_lay, fltUrdu_lay, fltGujarati_lay;

    CheckBox fltCB_male, fltCB_female, fltCB_both, fltBlwSSC, fltSSCPass, fltHSCPass, fltUnderGraduate,
            fltGraduate, fltPostGraduate, fltPHD, fltFresher, fltBlw1Yr, flt1_2Yr, flt3_5Yr, flt6_9Yr, flt10PYr,
            fltHindi, fltEnglish, fltMarathi, fltTamil, fltUrdu, fltGujarati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operation);

        flt_prim_lay=findViewById(R.id.flt_primary_lay);
        flt_sec_lay=findViewById(R.id.flt_secondary_lay);

        fltGenderView = findViewById(R.id.fltGenderView);
        fltQuliView = findViewById(R.id.fltQuliView);
        fltExpView = findViewById(R.id.fltExpView);
        fltLangView = findViewById(R.id.fltLangView);

        findByGender = findViewById(R.id.findByGender);
        findByRole = findViewById(R.id.findByRole);
        findByQuali = findViewById(R.id.findByQuali);
        findByExp = findViewById(R.id.findByExp);
        findByLanguage = findViewById(R.id.findByLanguage);

        findByGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fltGenderView.setVisibility(View.VISIBLE);
                fltQuliView.setVisibility(View.GONE);
                fltExpView.setVisibility(View.GONE);
                fltLangView.setVisibility(View.GONE);

                findByGender.setBackgroundColor(getResources().getColor(R.color.black));
                findByRole.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByQuali.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByExp.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByLanguage.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                flt_prim_lay.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));

                findByGender.setTextColor(getResources().getColor(R.color.black));
                findByRole.setTextColor(getResources().getColor(R.color.white));
                findByQuali.setTextColor(getResources().getColor(R.color.white));
                findByExp.setTextColor(getResources().getColor(R.color.white));
                findByLanguage.setTextColor(getResources().getColor(R.color.white));
            }
        });

        /*findByRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fltGenderView.setVisibility(View.GONE);
                fltQuliView.setVisibility(View.VISIBLE);
                fltExpView.setVisibility(View.GONE);
                fltLangView.setVisibility(View.GONE);


                findByGender_lay.setBackgroundColor(getResources().getColor(R.color.dark_gray));
                findByQuali_lay.setBackgroundColor(getResources().getColor(R.color.white));
                findByExp_lay.setBackgroundColor(getResources().getColor(R.color.dark_gray));
                findByLanguage_lay.setBackgroundColor(getResources().getColor(R.color.dark_gray));
            }
        });*/

        findByQuali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fltGenderView.setVisibility(View.GONE);
                fltQuliView.setVisibility(View.VISIBLE);
                fltExpView.setVisibility(View.GONE);
                fltLangView.setVisibility(View.GONE);



                findByGender.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByRole.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByQuali.setBackgroundColor(getResources().getColor(R.color.dark_gray));
                findByExp.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByLanguage.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                flt_prim_lay.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));

                findByQuali.setTextColor(getResources().getColor(R.color.black));
                findByRole.setTextColor(getResources().getColor(R.color.white));
                findByQuali.setTextColor(getResources().getColor(R.color.white));
                findByExp.setTextColor(getResources().getColor(R.color.white));
                findByLanguage.setTextColor(getResources().getColor(R.color.white));
            }
        });

        findByExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fltGenderView.setVisibility(View.GONE);
                fltQuliView.setVisibility(View.GONE);
                fltExpView.setVisibility(View.VISIBLE);
                fltLangView.setVisibility(View.GONE);


                findByGender.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByRole.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByQuali.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByExp.setBackgroundColor(getResources().getColor(R.color.dark_gray));
                findByLanguage.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                flt_prim_lay.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));

                findByExp.setTextColor(getResources().getColor(R.color.black));
                findByRole.setTextColor(getResources().getColor(R.color.white));
                findByQuali.setTextColor(getResources().getColor(R.color.white));
                findByExp.setTextColor(getResources().getColor(R.color.white));
                findByLanguage.setTextColor(getResources().getColor(R.color.white));


            }
        });

        findByLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fltGenderView.setVisibility(View.GONE);
                fltQuliView.setVisibility(View.GONE);
                fltExpView.setVisibility(View.GONE);
                fltLangView.setVisibility(View.VISIBLE);

                findByGender.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByRole.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByQuali.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByExp.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                flt_prim_lay.setBackgroundColor(getResources().getColor(R.color.expanded_expandable));
                findByLanguage.setBackgroundColor(getResources().getColor(R.color.dark_gray));

                findByLanguage.setTextColor(getResources().getColor(R.color.black));
                findByRole.setTextColor(getResources().getColor(R.color.white));
                findByQuali.setTextColor(getResources().getColor(R.color.white));
                findByExp.setTextColor(getResources().getColor(R.color.white));
                findByLanguage.setTextColor(getResources().getColor(R.color.white));

            }
        });

    }
}