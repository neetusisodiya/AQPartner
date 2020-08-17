package com.app.oooelePartner.Utill;

import android.content.Context;

import com.app.oooelePartner.Bean.LoginBean;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Constants {
    /*
    * user role list
===============
* type  : 1/2/3

    * */
    public static final String USER_ROLE_FLEET_OWNER = "1";
    public static final String USER_ROLE_COMMISION_AGENT = "2";
    public static final String USER_ROLE_BOOKING_AGENT = "3";
    public static final String USER_ROLE_ADMIN = "25";
    public static final String GEOFENCE_ID_STAN_UNI = "STAN_UNI";
    public static final float GEOFENCE_RADIUS_IN_METERS = 100;
    /**
     * Map for storing information about stanford university in the Stanford.
     */
    public static final HashMap<String, LatLng> AREA_LANDMARKS = new HashMap<String, LatLng>();
    public static final String DB_SELECTED_CATEGORIES = "selectedCategories_seklrewj593285";


    ///////////////////////////
    /*    --------------ForMap Activity*/
    public static final String DB_SELECTED_RADIUS = "selected_radius_sdlkrjew95832";
    public static final String DB_SELECTED_GROUP_CHAT = "selected_group_chat_5r93wksdfsdjkfre";
    public static final String DB_SELECTED_PRIVATE_CHAT = "selected_private_chat_dslfjt2390523";
    public static final String DB_REMEMBER_ME = "remember_me_dsklrjewiro";
    /*    //////////////////EndMapActivity*/
    public static final String USER_ID = "user_id";
    public static final String NOTICE_RELATED_CASE_ID = "id";
    public static final String APPEAL_RELATED_CASE_ID = "id";
    public static final String MOTION_RELATED_CASE_ID = "id";
    /*
    -----------------------------
    SHARED PREFERENCES (TINYDB) KEYS
    -----------------------------
    */
    private static final String DB_USER_DATA = "user_data_sdlkfsjtoweit";
    public static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String TO_MAIL = "mail.abhisharma0@gmail.com";
    public static SimpleDateFormat SET_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String FONT_FILE = "font_regular.ttf";

    static {
        // stanford university.
        AREA_LANDMARKS.put(GEOFENCE_ID_STAN_UNI, new LatLng(37.427476, -122.170262));
    }

    public static void setUser(Context mContext, LoginBean userPOJO) {
        ZeiqDb db = new ZeiqDb(mContext);

        String userStr = new Gson().toJson(userPOJO);
        if (userPOJO == null) {
            db.remove(DB_USER_DATA);
            return;
        }

        db.putString(DB_USER_DATA, userStr);

        AppBaseActivity baseActivity = ((AppBaseActivity) mContext);
        if (baseActivity != null) {
            baseActivity.userPOJO = userPOJO;
        }
    }

    public static LoginBean getUser(Context mContext) {
        ZeiqDb db = new ZeiqDb(mContext);
        String userStr = db.getString(DB_USER_DATA);

        LoginBean user = new Gson().fromJson(userStr, LoginBean.class);

        return user;
    }


}
