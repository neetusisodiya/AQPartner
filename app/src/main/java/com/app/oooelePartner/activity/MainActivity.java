package com.app.oooelePartner.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.oooelePartner.MyService;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Utill.CommonUtils;
import com.app.oooelePartner.fragment.BookFragment;
import com.app.oooelePartner.fragment.CreditHistory;
import com.app.oooelePartner.fragment.HomeFragment;
import com.app.oooelePartner.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.ActivityResult;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.tbuonomo.morphbottomnavigation.MorphBottomNavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public ImageView iv_home, ivsearch, iv_cart, iv_account;
    public TextView txthome, txtsearch, txtcart, txtaccount;
    Fragment fragment;
    RelativeLayout footerHome, footersearch, footerCart, footeraccount;
    MorphBottomNavigationView bottomNavigationView;
    AppPreferences mAppPreferences;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = menuItem -> {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.newfrag:
                //toolbar.setTitle("My Gifts");
                fragment = new HomeFragment();
                loadFragmentmain(fragment);
                return true;
            case R.id.booking:
                fragment = new BookFragment();
                loadFragment(fragment);
                return true;
            case R.id.profile:
                fragment = new ProfileFragment();
                loadFragment(fragment);
                return true;
            case R.id.menu:
                fragment = new CreditHistory();
                loadFragment(fragment);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find();
        mAppPreferences = new AppPreferences(this);

        if (mAppPreferences.getUserData(AppPreferences._isFirstTime).equals("1")) {
            showDialogForTermsCondition();
            mAppPreferences.setUserData(AppPreferences._isFirstTime, "3");
        }
        loadFragmentmain(new HomeFragment());
        startService();
        checkForUpdates();
    }
    private void checkForUpdates() {
        AppUpdateManager
                appUpdateManager = AppUpdateManagerFactory.create(this);

        Task<AppUpdateInfo> appUpdateInfo = appUpdateManager.getAppUpdateInfo();
        appUpdateInfo.addOnCompleteListener(task -> handleImmediateUpdate(appUpdateManager, appUpdateInfo));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_UPDATE == requestCode) {
            if (ActivityResult.RESULT_IN_APP_UPDATE_FAILED == resultCode) {
                finish();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (Activity.RESULT_CANCELED == resultCode) {
            finish();
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    int REQUEST_UPDATE = 100;

    private void handleImmediateUpdate(AppUpdateManager manager, Task<AppUpdateInfo> info) {
        Log.d("LOG_MESSAGE", "handleImmediateUpdate: " +info.getResult().updateAvailability());
        if ((info.getResult().updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE ||

                info.getResult().updateAvailability() ==
                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) &&

                info.getResult().isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

            try {
                manager.startUpdateFlowForResult(info.getResult(), AppUpdateType.IMMEDIATE, this, REQUEST_UPDATE);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }


    }
    private void startService() {

        Intent myIntent = new Intent(this, MyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();

        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }


    }

    private void showDialogForTermsCondition() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(this).inflate
                (R.layout.terms_condition_dialog, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        alertDialog.findViewById(R.id.buttonOk).setOnClickListener(v -> alertDialog.dismiss());
        TextView tvAmount = alertDialog.findViewById(R.id.learn_more);
        tvAmount.setOnClickListener(v -> {
            String url = "https://oooele.com/term-condition";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
    }

    public void find() {
        iv_home = findViewById(R.id.iv_home);
        ivsearch = findViewById(R.id.ivsearch);
        iv_cart = findViewById(R.id.iv_cart);
        iv_account = findViewById(R.id.iv_account);
        txthome = findViewById(R.id.txthome);
        txtsearch = findViewById(R.id.txtsearch);
        txtcart = findViewById(R.id.txtcart);
        txtaccount = findViewById(R.id.txtaccount);
        footerHome = findViewById(R.id.footerHome);
        footersearch = findViewById(R.id.footersearch);
        footerCart = findViewById(R.id.footerCart);
        footeraccount = findViewById(R.id.footeraccount);
        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        footerHome.setOnClickListener(v -> {
            if (fragment instanceof HomeFragment) {
            } else {
                fragment = new HomeFragment();
                loadFragment(fragment);
                CommonUtils.tabChange(MainActivity.this, iv_home, ivsearch, iv_cart, iv_account, txthome, txtsearch, txtcart, txtaccount);
            }
        });
        footersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof BookFragment) {
                } else {
                    fragment = new BookFragment();
                    loadFragment(fragment);
                    CommonUtils.tabChange(MainActivity.this, ivsearch, iv_cart, iv_account, iv_home, txtsearch, txtcart, txtaccount, txthome);
                }
            }
        });
        footerCart.setOnClickListener(v -> {
            if (fragment instanceof ProfileFragment) {
            } else {
                fragment = new ProfileFragment();
                loadFragment(fragment);
                CommonUtils.tabChange(MainActivity.this, iv_cart, iv_account, iv_home, ivsearch, txtcart, txtaccount, txthome, txtsearch);
            }
        });
        footeraccount.setOnClickListener(v -> {
                if (fragment instanceof CreditHistory) {
            } else {
                fragment = new CreditHistory();
                loadFragment(fragment);
                CommonUtils.tabChange(MainActivity.this, iv_account, iv_home, ivsearch, iv_cart, txtaccount, txthome, txtsearch, txtcart);
            }
        });

        //  Utils.tabChange(MainActivity.this,iv_home,ivsearch,iv_cart,iv_account,txthome,txtsearch,txtcart,txtaccount);
    }

    private void loadFragmentmain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.slide_right,R.anim.slideout_left);
        transaction.replace(R.id.container, fragment);
        //transaction.addToBackStack("EXIT");
        //transaction.addToBackStack(String.valueOf(MainActivity.class));
        transaction.commit();
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.slide_right,R.anim.slideout_left);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack("EXIT");
        //transaction.addToBackStack(String.valueOf(MainActivity.class));
        transaction.commit();
    }
}