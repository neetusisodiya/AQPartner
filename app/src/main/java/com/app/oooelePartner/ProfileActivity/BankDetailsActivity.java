package com.app.oooelePartner.ProfileActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.oooelePartner.Bean.GetVendorProfileBean;
import com.app.oooelePartner.Bean.LoginBean;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseBank;
import com.app.oooelePartner.Response.ResponseLogin;
import com.app.oooelePartner.Response.ResponseVendorGetProfile;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.oooelePartner.Prefrence.AppPreferences.Account_Number;
import static com.app.oooelePartner.Prefrence.AppPreferences.DOB;
import static com.app.oooelePartner.Prefrence.AppPreferences.EMAIL;
import static com.app.oooelePartner.Prefrence.AppPreferences.NAME;
import static com.app.oooelePartner.Prefrence.AppPreferences.Name_In_Bank;
import static com.app.oooelePartner.Prefrence.AppPreferences.PAN_CARD;
import static com.app.oooelePartner.Prefrence.AppPreferences.bankIfscCode;

public class BankDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_back;
    EditText edit_bank, edit_ac_num, edt_ifsc;
    Button btn_save;
    String userId;
    AVLoadingIndicatorView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_detail);

        find();




        /* Add_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddBank.class));
            }
        });*/
    }

    public void find() {
        bar = findViewById(R.id.bar);
        edit_bank = findViewById(R.id.edit_bank);
        edit_ac_num = findViewById(R.id.edit_ac_num);
        edt_ifsc = findViewById(R.id.edt_ifsc);
        btn_save = findViewById(R.id.btn_save);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        appPreferences = new AppPreferences(BankDetailsActivity.this);
        userId = String.valueOf(appPreferences.getUserData(AppPreferences.KEY_ID));
        if (appPreferences.checkForValue(Name_In_Bank)) {
            edit_bank.setText(appPreferences.getUserData(Name_In_Bank));
        }
        if (appPreferences.checkForValue(bankIfscCode)) {
            edt_ifsc.setText(appPreferences.getUserData(bankIfscCode));
        }
        if (appPreferences.checkForValue(Account_Number)) {
            edit_ac_num.setText(appPreferences.getUserData(Account_Number));
        }

    }

    String nameInBank, accountNumber, ifscCode;

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            onBackPressed();
        }
        if (v == btn_save) {
            nameInBank = edit_bank.getText().toString().trim();
            accountNumber = edit_ac_num.getText().toString().trim();
            ifscCode = edt_ifsc.getText().toString().trim();
            if (nameInBank.isEmpty()) {
                Toast.makeText(this, "Enter Bank Name", Toast.LENGTH_SHORT).show();
            } else if (accountNumber.isEmpty()) {
                Toast.makeText(this, "Enter Bank Account", Toast.LENGTH_SHORT).show();

            } else if (ifscCode.isEmpty()) {
                Toast.makeText(this, "Enter Bank IFSC", Toast.LENGTH_SHORT).show();

            } else {

                SaveBankAccont();
            }
        }
    }

    AppPreferences appPreferences;

    public void SaveBankAccont() {
        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"bank_name",
                        "account_no", "ifsc", "expert_id"}
                ,
                new String[]{nameInBank
                        , accountNumber
                        , ifscCode, userId});
        Call<ResponseBank> call = apiInterface.ApiBankDetails(builder.build());
        call.enqueue(new Callback<ResponseBank>() {
            @Override
            public void onResponse(Call<ResponseBank> call, Response<ResponseBank> response) {
                bar.setVisibility(View.GONE);


                try {
                    if (response.body().getMessage().
                            equalsIgnoreCase("Bank Detail Successfully Updated")) {
                       appPreferences.setUserData(bankIfscCode,ifscCode);
                        appPreferences.setUserData(Account_Number,accountNumber);
                        appPreferences.setUserData(Name_In_Bank,nameInBank);

                        Toast.makeText(getApplicationContext(), "Bank Detail Successfully Updated", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(getApplicationContext(), "Bank Detail No Update", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<ResponseBank> call, Throwable t) {

            }
        });
    }


}
