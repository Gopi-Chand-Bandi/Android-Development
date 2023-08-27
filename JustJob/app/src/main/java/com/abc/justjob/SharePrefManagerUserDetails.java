package com.abc.justjob;

import android.content.Context;
import android.content.SharedPreferences;

import com.abc.justjob.Candidate.cndResultProfile;

public class SharePrefManagerUserDetails {
    private static SharePrefManagerUserDetails mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedprefretrofit";

    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_EMIAL = "keyuseremail";
    private static final String KEY_CONTACT_NO = "keycontactno";

    private SharePrefManagerUserDetails(Context context) {
        mCtx = context;
    }

    public static synchronized SharePrefManagerUserDetails getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharePrefManagerUserDetails(context);
        }
        return mInstance;
    }

    public void fetchUserProfile(cndResultProfile profile) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, profile.getRegister_login_id());
        editor.putString(KEY_USER_NAME, profile.getCd_full_name());
        editor.putString(KEY_CONTACT_NO, profile.getCd_contact_no());
        editor.apply();
    }

    public cndResultProfile getCndProfile() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new cndResultProfile(
                sharedPreferences.getString(KEY_USER_ID, null),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_CONTACT_NO, null)
        );
    }
}
