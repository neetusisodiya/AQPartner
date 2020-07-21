package com.app.oooelePartner.Utill;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

 import com.app.oooelePartner.Bean.LoginBean;


public class AppBaseActivity extends AppCompatActivity {
    public Context mContext;
    public ZeiqDb db;
    public LoginBean userPOJO;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = AppBaseActivity.this;
         db = new ZeiqDb(this);
        userPOJO = Constants.getUser(mContext);

        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onResume() {

        super.onResume();
        userPOJO = Constants.getUser(mContext);

    }

    public void backClick(View view) {
        onBackPressed();
    }

}
