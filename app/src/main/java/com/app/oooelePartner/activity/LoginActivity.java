package com.app.oooelePartner.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.oooelePartner.Bean.LoginBean;
import com.app.oooelePartner.Brodcast.SmsBroadcastReceiver;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseLogin;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.AppBaseActivity;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.app.oooelePartner.Prefrence.AppPreferences.ADDRESS;
import static com.app.oooelePartner.Prefrence.AppPreferences.Account_Number;
import static com.app.oooelePartner.Prefrence.AppPreferences.DOB;
import static com.app.oooelePartner.Prefrence.AppPreferences.EMAIL;
import static com.app.oooelePartner.Prefrence.AppPreferences.EXPERIENCE_MONTH;
import static com.app.oooelePartner.Prefrence.AppPreferences.EXPERIENCE_YEAR;
import static com.app.oooelePartner.Prefrence.AppPreferences.EXPERT_IN;
import static com.app.oooelePartner.Prefrence.AppPreferences.NAME;
import static com.app.oooelePartner.Prefrence.AppPreferences.Name_In_Bank;
import static com.app.oooelePartner.Prefrence.AppPreferences.PAN_CARD;
import static com.app.oooelePartner.Prefrence.AppPreferences.PINCODE;
import static com.app.oooelePartner.Prefrence.AppPreferences.Qualifications;
import static com.app.oooelePartner.Prefrence.AppPreferences._isFirstTime;
import static com.app.oooelePartner.Prefrence.AppPreferences.bankIfscCode;
import static maes.tech.intentanim.CustomIntent.customType;

public class LoginActivity extends AppBaseActivity implements View.OnClickListener {
    private static final int REQ_USER_CONSENT = 200;
    private static FirebaseAnalytics firebaseAnalytics;
    String mobile;
    EditText number, mobile_otp;
    String message;
    Button enter_btn, btnenter_otp;
    TextView txt, tvResend;
    AVLoadingIndicatorView bar;
    SmsBroadcastReceiver smsBroadcastReceiver;
    String str_Token;
    AppPreferences mAppPreferences;
    TextView tvWait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAppPreferences = new AppPreferences(this);
        if (mAppPreferences.getAccessToken() == null) {
            str_Token = FirebaseInstanceId.getInstance().getToken();
        } else {
            str_Token = mAppPreferences.getAccessToken();
        }
        Log.d("LOG_MESSAGE", "onCreate: " + str_Token);
        find();
        startSmsUserConsent();
    }

    public void find() {
        bar = findViewById(R.id.bar);
        txt = findViewById(R.id.txt);
        number = findViewById(R.id.mobile_txt);
        enter_btn = findViewById(R.id.enter_btn);
        mobile_otp = findViewById(R.id.mobile_otp);
        btnenter_otp = findViewById(R.id.enter_otp);
        tvWait = findViewById(R.id.wait_txt);
        tvResend = findViewById(R.id.resend_otp);
        tvWait.setVisibility(View.GONE);
        btnenter_otp.setOnClickListener(this);
        enter_btn.setOnClickListener(this);
        tvResend.setOnClickListener(v -> {
            //getOTP();
            doLogin();
        });
        mobile_otp.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (mobile_otp.getText().toString().equals(otp)) {
                    bar.setVisibility(View.VISIBLE);
                    btnenter_otp.setClickable(false);
                    loginUser();
                    //    doLogin();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == enter_btn) {
            mobile = number.getText().toString();
            if (mobile.length() == 10) {

                //getOTP();
                doLogin();
            } else {
                DynamicToast.makeError(getApplicationContext(), "Enter 10 digit number").show();

            }
        }
        if (v == btnenter_otp) {
            String _otp = ((TextView) findViewById(R.id.mobile_otp)).getText().toString().trim();
            btnenter_otp.setText("Submit");
            if (_otp.equals(otp)) {
                loginUser();
            } else {
                Toast.makeText(this, " please fill valid OTP number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loginUser() {
        AppPreferences.saveInPref(mContext, loginBean);
        AppPreferences appPreferences = new AppPreferences(LoginActivity.this);
        appPreferences.setUserData(AppPreferences.KEY_ID, loginBean.getId());
        appPreferences.setUserData(NAME, loginBean.getName());
        appPreferences.setUserData(AppPreferences.PHONE_NUMBER, loginBean.getMobno());
        appPreferences.setUserData(EMAIL, loginBean.getEmail());
        appPreferences.setUserData(Name_In_Bank, loginBean.getBank_name());
        appPreferences.setUserData(bankIfscCode, loginBean.getIfsc());
        appPreferences.setUserData(Account_Number, loginBean.getAccount_no());
        appPreferences.setUserData(DOB, loginBean.getDob());
        appPreferences.setUserData(PAN_CARD, loginBean.getPancard_no());
        appPreferences.setUserData(AppPreferences.secure_token, loginBean.getSecure_token());
        appPreferences.setUserData(ADDRESS, loginBean.getAddress());
        appPreferences.setUserData(PINCODE, loginBean.getPincode());
        appPreferences.setUserData(_isFirstTime, loginBean.getStatus());
        if (loginBean.getExp_year() != null)
            appPreferences.setUserData(EXPERIENCE_YEAR, loginBean.getExp_year());
        if (loginBean.getExp_month() != null)
            appPreferences.setUserData(EXPERIENCE_MONTH, loginBean.getExp_month());
        if (loginBean.getQualification_id() != null)
            appPreferences.setUserData(Qualifications,
                    String.valueOf(loginBean.getQualification_id()));
        if (loginBean.getExpert_type_id() != null)

            appPreferences.setUserData(EXPERT_IN, String.valueOf(loginBean.getExpert_type_id()));
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
        customType(mContext, "left-to-right");
    }


    public void getOTP() {
        bar.setVisibility(View.VISIBLE);
        tvWait.setVisibility(View.VISIBLE);

        Random otp = new Random();
        int io = otp.nextInt(9999);
        String formatted = String.format("%06d", io);
        String sOTPMessage = "Your OOOELE Service Provider OTP is " +
                formatted + " Please enter to login.";
        Log.d("LOG_MESSAGE", "getOTP: " + formatted);
        fromRetrofit(sOTPMessage);

    }

    private void fromRetrofit(String sOTPMessage) {
        ApiInterface apiInterface = ApiClient.getClientservice(
                "http://msg.msgclub.net/rest/services/sendSMS/").
                create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.getOtp("34c58efe22cfc72e40df3c6a195ce9fa",
                sOTPMessage, "DEMOOS", "8", mobile, "english");
        bar.setVisibility(View.GONE);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    bar.setVisibility(View.GONE);
                    txt.setText("Please Enter 6 digit Otp here");
                    number.setVisibility(View.GONE);
                    enter_btn.setVisibility(View.GONE);
                    mobile_otp.setVisibility(View.VISIBLE);
                    btnenter_otp.setVisibility(View.VISIBLE);
                    tvResend.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
            }
        });
    }

    String otp;
    LoginBean loginBean;

    private void doLogin() {
        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"mobno", "secure_token"},
                new String[]{mobile, str_Token});
        Call<ResponseLogin> call = apiInterface.ApiLogin(builder.build());
        bar.setVisibility(View.GONE);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponseLogin resObj = (ResponseLogin) response.body();
                    if (resObj.isStatus()) {
                        otp = ((ResponseLogin) response.body()).getOtp();
                        Log.d("LOG_MESSAGE", "getOTP: " + otp);
                        bar.setVisibility(View.GONE);
                        tvWait.setVisibility(View.VISIBLE);

                        txt.setText("Please Enter 6 digit Otp here");
                        number.setVisibility(View.GONE);
                        enter_btn.setVisibility(View.GONE);
                        mobile_otp.setVisibility(View.VISIBLE);
                        btnenter_otp.setVisibility(View.VISIBLE);
                        tvResend.setVisibility(View.VISIBLE);
                        loginBean = ((ResponseLogin) response.body()).getData();

                    } else {
                        btnenter_otp.setClickable(true);

                        DynamicToast.makeError(getApplicationContext(),
                                "The username or password is incorrect").show();
                    }
                } else {
                    btnenter_otp.setClickable(true);

                    DynamicToast.makeError(getApplicationContext(),
                            "Error! Please try again! ").show();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                btnenter_otp.setClickable(true);

            }
        });
    }


    private void startSmsUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        //We can add sender phone number or leave it blank
        // I'm adding null here
        client.startSmsUserConsent(null).addOnSuccessListener(aVoid -> {
        }).addOnFailureListener(e -> {
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT) {
            if ((resultCode == RESULT_OK) && (data != null)) {
                //That gives all message to us.
                // We need to get the code from inside with regex
                message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                //    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                // mobile_otp.setText(String.format("%s - %s",  message));
                getOtpFromMessage(message);
            }
        }
    }


    private void registerBroadcastReceiver() {
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        smsBroadcastReceiver.smsBroadcastReceiverListener =
                new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, REQ_USER_CONSENT);
                    }

                    @Override
                    public void onFailure() {
                    }
                };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }

    private void getOtpFromMessage(String message) {
        // This will match any 6 digit number in the message
        Pattern pattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            mobile_otp.setText(matcher.group(0));
        }
    }
}
