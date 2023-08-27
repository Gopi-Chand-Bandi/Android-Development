package com.abc.justjob.Company.CompanyPostJob;

import android.content.Context;
import android.content.SharedPreferences;

import com.abc.justjob.ApiFile.LoginRegisterApi.Users;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel1;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel2;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel3;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel4;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel5;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel6;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel7;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel8;
import com.abc.justjob.Company.CompanyPostJob.PojoModel.PojoModel9;

public class postJobSharedPref {

    private static postJobSharedPref mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "postjobsharepref";

    private static final String POJO_1_TITLE = "pojo_1_title";
    private static final String POJO_1_ROLE = "pojo_1_role";
    private static final String POJO_1_DESIGNATION = "pojo_1_designation";
    private static final String POJO_1_TYPE = "pojo_1_type";
    private static final String POJO_1_STATE = "pojo_1_state";
    private static final String POJO_1_CITY = "pojo_1_city";

    private static final String POJO_2_DESCRIPTION = "pojo_2_description";
    private static final String POJO_2_SALARY_TIME = "pojo_2_salary_time";
    private static final String POJO_2_MIN_SALARY = "pojo_2_min_salary";
    private static final String POJO_2_MAX_SALARY = "pojo_2_max_salary";
    private static final String POJO_2_MIN_AGE = "pojo_2_min_age";
    private static final String POJO_2_MAX_AGE = "pojo_2_max_age";
    private static final String POJO_2_LOCATION = "pojo_2_location";
    private static final String POJO_2_NUM_LOCATIN = "pojo_2_num_location";


    private static final String POJO_3_FRESHER_CAN = "pojo_3_fresher_can";
    private static final String POJO_3_EXPERIENCE_CAN = "pojo_3_experience_can";
    private static final String POJO_3_MIN_EXP = "pojo_3_min_exp";
    private static final String POJO_3_MAX_EXP = "pojo_3_max_exp";

    private static final String POJO_4_QUALIFICATION = "pojo_4_qualification";
    private static final String POJO_4_MALE_OR_FEMALE = "pojo_4_male_or_female";

    private static final String POJO_5_ENGLISH_KNOW = "pojo_5_english_know";
    private static final String POJO_5_LANGUAGES = "pojo_5_languages";

    private static final String POJO_6_VEHICLE = "pojo_6_vehicle";
    private static final String POJO_6_LAPTOP = "pojo_6_laptop";
    private static final String POJO_6_PHONE = "pojo_6_phone";

    private static final String POJO_7_DOCUMENT = "pojo_7_document";
    private static final String POJO_7_WORKING_DAY = "pojo_7_working_day";

    private static final String POJO_8_START_DAY = "pojo_8_start_day";
    private static final String POJO_8_END_DAY = "pojo_8_end_day";
    private static final String POJO_8_START_NIGHT = "pojo_8_start_night";
    private static final String POJO_8_END_NIGHT = "pojo_8_end_night";
    private static final String POJO_8_START_ROTATION = "pojo_8_start_rotation";
    private static final String POJO_8_END_ROTATION = "pojo_8_end_rotation";

    private static final String POJO_9_REIMBURSEMENT = "pojo_9_reimbursement";
    private static final String POJO_9_INCENTIVE = "pojo_9_incentive";
    private static final String POJO_9_DEPOSITING = "pojo_9_depositing";
    private static final String POJO_9_DEPOSIT_AMOUNT = "pojo_9_deposit_amount";
    private static final String POJO_9_AMOUNT_PURPOSE = "pojo_9_amount_purpose";


    private postJobSharedPref(Context context) {
        mCtx = context;
    }

    public static synchronized postJobSharedPref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new postJobSharedPref(context);
        }
        return mInstance;
    }


    public void fragment1SharedPref(String titleStr, String roleStr, String designationStr, String typeStr,
                                    String stateStr, String cityStr) {
        //IS_CANDIDATE = isCandidate;

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_1_TITLE, titleStr);
        editor.putString(POJO_1_ROLE, roleStr);
        editor.putString(POJO_1_DESIGNATION, designationStr);
        editor.putString(POJO_1_TYPE, typeStr);
        editor.putString(POJO_1_STATE, stateStr);
        editor.putString(POJO_1_CITY, cityStr);
        editor.apply();

        new PojoModel1(
                sharedPreferences.getString(POJO_1_TITLE, titleStr),
                sharedPreferences.getString(POJO_1_ROLE, roleStr),
                sharedPreferences.getString(POJO_1_DESIGNATION, designationStr),
                sharedPreferences.getString(POJO_1_TYPE, typeStr),
                sharedPreferences.getString(POJO_1_STATE, stateStr),
                sharedPreferences.getString(POJO_1_CITY, cityStr)
        );
    }

    public PojoModel1 getPojoFragment1() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel1(
                sharedPreferences.getString(POJO_1_TITLE, null),
                sharedPreferences.getString(POJO_1_ROLE, null),
                sharedPreferences.getString(POJO_1_DESIGNATION, null),
                sharedPreferences.getString(POJO_1_TYPE, null),
                sharedPreferences.getString(POJO_1_STATE, null),
                sharedPreferences.getString(POJO_1_CITY, null)
        );
    }

    public void fragment2SharedPref(String descriptionStr, String salaryTimeStr, String minSalaryStr,
                                    String maxSalaryStr, String minAgeStr, String maxAgeStr, String locationStr,
                                    String numberLocationStr) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_2_DESCRIPTION, descriptionStr);
        editor.putString(POJO_2_SALARY_TIME, salaryTimeStr);
        editor.putString(POJO_2_MIN_SALARY, minSalaryStr);
        editor.putString(POJO_2_MAX_SALARY, maxSalaryStr);
        editor.putString(POJO_2_MIN_AGE, minAgeStr);
        editor.putString(POJO_2_MAX_AGE, maxAgeStr);
        editor.putString(POJO_2_LOCATION, locationStr);
        editor.putString(POJO_2_NUM_LOCATIN, numberLocationStr);
        editor.apply();

        new PojoModel2(
                sharedPreferences.getString(POJO_2_DESCRIPTION, descriptionStr),
                sharedPreferences.getString(POJO_2_SALARY_TIME, salaryTimeStr),
                sharedPreferences.getString(POJO_2_MIN_SALARY, minSalaryStr),
                sharedPreferences.getString(POJO_2_MAX_SALARY, maxSalaryStr),
                sharedPreferences.getString(POJO_2_MIN_AGE, minAgeStr),
                sharedPreferences.getString(POJO_2_MAX_AGE, maxAgeStr),
                sharedPreferences.getString(POJO_2_LOCATION, locationStr),
                sharedPreferences.getString(POJO_2_NUM_LOCATIN, numberLocationStr)
        );
    }

    public PojoModel2 getPojoFragment2() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel2(
                sharedPreferences.getString(POJO_2_DESCRIPTION, null),
                sharedPreferences.getString(POJO_2_SALARY_TIME, null),
                sharedPreferences.getString(POJO_2_MIN_SALARY, null),
                sharedPreferences.getString(POJO_2_MAX_SALARY, null),
                sharedPreferences.getString(POJO_2_MIN_AGE, null),
                sharedPreferences.getString(POJO_2_MAX_AGE, null),
                sharedPreferences.getString(POJO_2_LOCATION, null),
                sharedPreferences.getString(POJO_2_NUM_LOCATIN, null)
        );
    }

    public void fragment3SharedPref(String fresherCanStr, String expCanStr, String minExpStr, String maxExpStr) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_3_FRESHER_CAN, fresherCanStr);
        editor.putString(POJO_3_EXPERIENCE_CAN, expCanStr);
        editor.putString(POJO_3_MIN_EXP, minExpStr);
        editor.putString(POJO_3_MAX_EXP, maxExpStr);
        editor.apply();

        new PojoModel3(
                sharedPreferences.getString(POJO_3_FRESHER_CAN, fresherCanStr),
                sharedPreferences.getString(POJO_3_EXPERIENCE_CAN, expCanStr),
                sharedPreferences.getString(POJO_3_MIN_EXP, minExpStr),
                sharedPreferences.getString(POJO_3_MAX_EXP, maxExpStr)
        );
    }


    public PojoModel3 getPojoFragment3() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel3(
                sharedPreferences.getString(POJO_3_FRESHER_CAN, null),
                sharedPreferences.getString(POJO_3_EXPERIENCE_CAN, null),
                sharedPreferences.getString(POJO_3_MIN_EXP, null),
                sharedPreferences.getString(POJO_3_MAX_EXP, null)
        );
    }


    public void fragment4SharedPref(String qualiStr, String maleFemaleStr) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_4_QUALIFICATION, qualiStr);
        editor.putString(POJO_4_MALE_OR_FEMALE, maleFemaleStr);
        editor.apply();

        new PojoModel4(
                sharedPreferences.getString(POJO_4_QUALIFICATION, qualiStr),
                sharedPreferences.getString(POJO_4_MALE_OR_FEMALE, maleFemaleStr)
        );
    }

    public PojoModel4 getPojoFragment4() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel4(
                sharedPreferences.getString(POJO_4_QUALIFICATION, null),
                sharedPreferences.getString(POJO_4_MALE_OR_FEMALE, null)
        );
    }

    public void fragment5SharedPref(String englishKnow, String languageStr) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_5_ENGLISH_KNOW, englishKnow);
        editor.putString(POJO_5_LANGUAGES, languageStr);
        editor.apply();

        new PojoModel5(
                sharedPreferences.getString(POJO_5_ENGLISH_KNOW, englishKnow),
                sharedPreferences.getString(POJO_5_LANGUAGES, languageStr)
        );
    }

    public PojoModel5 getPojoFragment5() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel5(
                sharedPreferences.getString(POJO_5_ENGLISH_KNOW, null),
                sharedPreferences.getString(POJO_5_LANGUAGES, null)
        );
    }

    public void fragment6SharedPref(String vehiNewStr, String laptopNewStr, String phoneNewStr) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_6_VEHICLE, vehiNewStr);
        editor.putString(POJO_6_LAPTOP, laptopNewStr);
        editor.putString(POJO_6_PHONE, phoneNewStr);
        editor.apply();

        new PojoModel6(
                sharedPreferences.getString(POJO_6_VEHICLE, vehiNewStr),
                sharedPreferences.getString(POJO_6_LAPTOP, laptopNewStr),
                sharedPreferences.getString(POJO_6_PHONE, phoneNewStr)
        );
    }

    public PojoModel6 getPojoFragment6() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel6(
                sharedPreferences.getString(POJO_6_VEHICLE, null),
                sharedPreferences.getString(POJO_6_LAPTOP, null),
                sharedPreferences.getString(POJO_6_PHONE, null)
        );
    }

    public void fragment7SharedPref(String documentsStr, String workingDayStr) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_7_DOCUMENT, documentsStr);
        editor.putString(POJO_7_WORKING_DAY, workingDayStr);
        editor.apply();

        new PojoModel7(
                sharedPreferences.getString(POJO_7_DOCUMENT, documentsStr),
                sharedPreferences.getString(POJO_7_WORKING_DAY, workingDayStr)
        );
    }

    public PojoModel7 getPojoFragment7() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel7(
                sharedPreferences.getString(POJO_7_DOCUMENT, null),
                sharedPreferences.getString(POJO_7_WORKING_DAY, null)
        );
    }

    public void fragment8SharedPref(String dayStartStr, String dayEndStr, String nightStartStr, String nightEndStr, String rotateStartStr, String rotateEndStr) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_8_START_DAY, dayStartStr);
        editor.putString(POJO_8_END_DAY, dayEndStr);
        editor.putString(POJO_8_START_NIGHT, nightStartStr);
        editor.putString(POJO_8_END_NIGHT, nightEndStr);
        editor.putString(POJO_8_START_ROTATION, rotateStartStr);
        editor.putString(POJO_8_END_ROTATION, rotateEndStr);
        editor.apply();

        new PojoModel8(
                sharedPreferences.getString(POJO_8_START_DAY, dayStartStr),
                sharedPreferences.getString(POJO_8_END_DAY, dayEndStr),
                sharedPreferences.getString(POJO_8_START_NIGHT, nightStartStr),
                sharedPreferences.getString(POJO_8_END_NIGHT, nightEndStr),
                sharedPreferences.getString(POJO_8_START_ROTATION, rotateStartStr),
                sharedPreferences.getString(POJO_8_END_ROTATION, rotateEndStr)
        );
    }

    public PojoModel8 getPojoFragment8() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel8(
                sharedPreferences.getString(POJO_8_START_DAY, null),
                sharedPreferences.getString(POJO_8_END_DAY, null),
                sharedPreferences.getString(POJO_8_START_NIGHT, null),
                sharedPreferences.getString(POJO_8_END_NIGHT, null),
                sharedPreferences.getString(POJO_8_START_ROTATION, null),
                sharedPreferences.getString(POJO_8_END_ROTATION, null)
        );
    }

    public void fragment9SharedPref(String reimStr, String incetStr, String depositingOrNot, String amountStr, String amountPurposeStr) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POJO_9_REIMBURSEMENT, reimStr);
        editor.putString(POJO_9_INCENTIVE, incetStr);
        editor.putString(POJO_9_DEPOSITING, depositingOrNot);
        editor.putString(POJO_9_DEPOSIT_AMOUNT, amountStr);
        editor.putString(POJO_9_AMOUNT_PURPOSE, amountPurposeStr);
        editor.apply();

        new PojoModel9(
                sharedPreferences.getString(POJO_9_REIMBURSEMENT, reimStr),
                sharedPreferences.getString(POJO_9_INCENTIVE, incetStr),
                sharedPreferences.getString(POJO_9_DEPOSITING, depositingOrNot),
                sharedPreferences.getString(POJO_9_DEPOSIT_AMOUNT, amountStr),
                sharedPreferences.getString(POJO_9_AMOUNT_PURPOSE, amountPurposeStr)
        );
    }

    public PojoModel9 getPojoFragment9() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PojoModel9(
                sharedPreferences.getString(POJO_9_REIMBURSEMENT, null),
                sharedPreferences.getString(POJO_9_INCENTIVE, null),
                sharedPreferences.getString(POJO_9_DEPOSITING, null),
                sharedPreferences.getString(POJO_9_DEPOSIT_AMOUNT, null),
                sharedPreferences.getString(POJO_9_AMOUNT_PURPOSE, null)
        );
    }

    public boolean postJobDataClear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
