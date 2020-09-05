package com.app.oooelePartner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Utill.AppBaseActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashActivity extends AppBaseActivity {
    private static final int splash = 2000;
    AppPreferences mAppPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAppPreferences = new AppPreferences(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        if (mAppPreferences.getAccessToken() == null) {
            mAppPreferences.setAccessToken(FirebaseInstanceId.getInstance().getToken());
        }


        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (mAppPreferences.checkForValue(AppPreferences.KEY_ID)) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));

            }
            customType(mContext, "bottom-to-up");
            finish();
        }, splash);
    }
}

