package com.abc.justjob;

import android.content.Context;
import android.content.SharedPreferences;

import com.abc.justjob.ApiFile.LoginRegisterApi.Users;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedprefretrofit";

    private static final String KEY_LAST_USER_ID = "keylastuserid";
    private static final String KEY_USER_REGISTERED_ID = "keyuserid";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";

    private static final String IS_CANDIDATE="is_candidate";
    private static final String  IS_REGISTERED="Is_register";

    public static final String KEY_ISNIGHTMODE="isNightMode";
    public static final String KEY_ISDIALOG_ACTIVATED="isDialogActivated";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void isDialogActivated(boolean isDialogActivated){
        //IS_CANDIDATE = isCandidate;

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISDIALOG_ACTIVATED, isDialogActivated);
        editor.apply();
    }

    public boolean GetisDialogActivated(Context context){

        SharedPreferences sf;
        boolean isActivated;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        sf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isActivated = sf.getBoolean(KEY_ISDIALOG_ACTIVATED, false);
        return isActivated;
    }

//    public void isDarkMode(boolean isDarkMode){
        //IS_CANDIDATE = isCandidate;

//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean(KEY_ISNIGHTMODE, isDarkMode);
//        editor.apply();
//    }

//    public boolean GetIsDarkMode(Context context){

//        SharedPreferences sf;
//        boolean isDark;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
//        sf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        isDark = sf.getBoolean(KEY_ISNIGHTMODE, false);
//        return isDark;
//    }


    public void isCandidate(String isCandidate){
        //IS_CANDIDATE = isCandidate;

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IS_CANDIDATE, isCandidate);
        editor.apply();
    }

    public String GetIsCandidate(Context context){

        SharedPreferences candidateSf;
        String isCandidateStr;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        candidateSf = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        isCandidateStr = candidateSf.getString(IS_CANDIDATE, null);
        return isCandidateStr;
    }


    public void isRegistered(Context context,boolean isRegistered){
        //IS_REGISTERED = isRegistered;

        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        preferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=preferences.edit();
        editor.putBoolean(IS_REGISTERED,isRegistered);
        editor.apply();
    }
    public boolean getIsRegistered(Context context){
        //return IS_REGISTERED;
        SharedPreferences preferences;
        Boolean isRegisteredStr;

        preferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        isRegisteredStr=preferences.getBoolean(IS_REGISTERED,false);
        return isRegisteredStr;
    }

    public boolean userLogin(String userIdStr) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_REGISTERED_ID, userIdStr);
        editor.apply();
        return true;
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_REGISTERED_ID, null) != null)
            return true;
        else
            return false;
    }

    public void saveUserId(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(KEY_USER_REGISTERED_ID, text); //3
        editor.apply(); //4
    }

    public String getValueOfUserId(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_USER_REGISTERED_ID, null);
        return text;
    }
    public void saveLastId(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(KEY_LAST_USER_ID, text); //3
        editor.apply(); //4
    }

    public String getValueOfLastId(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_LAST_USER_ID, null);
        return text;
    }
    public void saveUserName(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(KEY_USER_NAME, text); //3
        editor.apply(); //4
    }

    public String getUserName(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_USER_NAME, null);
        return text;
    }
    public void saveUserEmail(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(KEY_USER_EMAIL, text); //3
        editor.apply(); //4
    }

    public String getUserEmail(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        text = settings.getString(KEY_USER_EMAIL, null);
        return text;
    }

    public Users getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Users(
                sharedPreferences.getInt(KEY_USER_REGISTERED_ID, 0),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_EMAIL, null)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
}
