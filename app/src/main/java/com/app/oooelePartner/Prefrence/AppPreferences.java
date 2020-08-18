package com.app.oooelePartner.Prefrence;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.oooelePartner.Bean.LoginBean;
import com.google.gson.Gson;


public class AppPreferences {
    public static final String _isFirstTime = "_isFirstTime";


    public static final String SHARED_PREFERENCE_NAME = "FIREBASE_TOKEN";
    public static final String USER_DETAILS = "USER_DETAILS";
    public static final String USER_DATA = "user_data";
    public static final String KEY_ID = "id";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "name";
    public static final String GENDER = "GENDER";
    public static final String Name_In_Bank = "nameInBank";
    public static final String bankIfscCode = "bankIfscCode";
    public static final String Account_Number = "Account_Number";
    public static final String DOB = "DOB";
    public static final String PAN_CARD = "PAN_CARD";
    public static final String secure_token = "secure_token";
    public static final String ADDRESS = "ADDRESS";
    public static final String PINCODE = "PINCODE";
    public static final String EXPERIENCE_MONTH = "EXPERIENCE_MONTH";

    public static final String EXPERIENCE_YEAR = "EXPERIENCE_YEAR";
    public static final String EXPERT_IN = "EXPERT_IN";
    public static final String Qualifications = "_qualifications";
    public static final String USER_LATITUDE = "LATITUDE";
    public static final String USER_LONGITUDE = "LONGITUDE";
    public static SharedPreferences.Editor editor;
    public SharedPreferences mPrefs;
    private Context context;

    public AppPreferences(Context context) {
        this.context = context;
        mPrefs = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void saveInPref(Context ctx, LoginBean login_model) {
        SharedPreferences prefs = ctx.getSharedPreferences(USER_DETAILS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(login_model);
        editor.putString(USER_DATA, json);
        editor.apply();
    }

    public static LoginBean getSavedUser(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(USER_DETAILS,
                Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(USER_DATA, "");
        return gson.fromJson(json, LoginBean.class);
       /* LoginBean modal = new LoginBean();
        //  modal.setId(Integer.parseInt(prefs.getString(KEY_ID, "-1")));
        modal.setId(Integer.parseInt(prefs.getString(KEY_ID, "-1")));
        modal.setMobno(prefs.getString(PHONE_NUMBER, "-1"));
        modal.setEmail(prefs.getString(EMAIL, "-1"));*/
        //   modal.setEmail(prefs.getString(KEY_EMAIL_ID, "-1"));
        //   modal.setContact_no(prefs.getString(KEY_Contact_No, "-1"));
        //     modal.setAlt_contact_no(prefs.getString(KEY_ALtCONTACT_NO, "-1"));
        //    modal.setCustomer_id(prefs.getString(KEY_CUSTOMER_ID, "-1"));


    }

    public static void clearPrefsdata(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(USER_DETAILS,
                Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear().apply();

    }

    public boolean checkForValue(String value) {
        SharedPreferences prefs = context.getSharedPreferences(USER_DETAILS,
                Context.MODE_PRIVATE);
        return prefs.contains(value);
    }

    public void setUserData(String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(USER_DETAILS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getUserData(String key) {
        SharedPreferences prefs = context.getSharedPreferences(USER_DETAILS,
                Context.MODE_PRIVATE);
        return prefs.getString(key, "");

    }

    public String getAccessToken() {
        return mPrefs.getString(AppPreferences.ACCESS_TOKEN, "");
    }

    public void setAccessToken(String accessToken) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.apply();

    }
    /*public static void saveUserDetail(Context ctx, LoginBean login_model) {
        SharedPreferences prefs = ctx.getSharedPreferences(USER_DETAILS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_ID, String.valueOf(login_model.getId()));
        editor.putString(PHONE_NUMBER, String.valueOf(login_model.getMobno()));
        editor.putString(EMAIL, String.valueOf(login_model.getEmail()));
        editor.putString(NAME, login_model.getName());
        editor.putString(KEY_EMAIL_ID, login_model.getEmail());
       *//* editor.putString(KEY_ALtCONTACT_NO, String.valueOf(login_model.getAlt_contact_no()));
        editor.putString(KEY_CUSTOMER_ID, login_model.getCustomer_id());*//*


        editor.apply();
    }*/
}