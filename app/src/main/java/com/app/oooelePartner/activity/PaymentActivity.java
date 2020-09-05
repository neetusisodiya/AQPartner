package com.app.oooelePartner.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponsePayment;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentActivity extends Activity implements PaymentResultListener, View.OnClickListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    double total;
    TextView txt_skip;
    EditText edit_amount;
    String str_expert_id, str_mob_no, str_email;
    private String amount, points, amountForApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymnets);
        str_expert_id = String.valueOf(AppPreferences.getSavedUser(PaymentActivity.this).getId());
        str_mob_no = String.valueOf(AppPreferences.getSavedUser(PaymentActivity.this).getMobno());
        str_email = String.valueOf(AppPreferences.getSavedUser(PaymentActivity.this).getEmail());

        Intent intent = getIntent();
        amount = intent.getStringExtra("TotalAmount");
        amountForApi = intent.getStringExtra("TotalAmount");
        points = intent.getStringExtra("points");
        startPayment();
        Checkout.preload(PaymentActivity.this);
        txt_skip = findViewById(R.id.txt_skip);
        edit_amount = findViewById(R.id.edit_amount);
        txt_skip.setOnClickListener(this);
        Button button = findViewById(R.id.btn_pay);

        button.setOnClickListener(v -> {
            startPayment();
          /*  if (edit_amount.getText().toString().isEmpty()) {
                Toast.makeText(PaymentActivity.this,
                        "Enter Purchase Amount", Toast.LENGTH_SHORT).show();

            } else {
                startPayment();

            }*/
        });

        TextView privacyPolicy = findViewById(R.id.txt_privacy_policy);
        privacyPolicy.setVisibility(View.GONE);
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent httpIntent = new Intent(Intent.ACTION_VIEW);

                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);

            }
        });
    }

    public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();
        co.setImage(R.drawable.logo_service);

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Oooele Service Partner");
            options.put("description", "");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");

            total = Double.parseDouble(amount);
            total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            //    preFill.put("email", str_email);
            preFill.put("contact", str_mob_no);
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: "
                    + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            AddWallet(razorpayPaymentID);
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int code, String response) {
        onBackPressed();
        try {

        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == txt_skip) {

        }
    }

    public void AddWallet(String razorpayPaymentID) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {"transaction_id", "amount", "member_id"}
                ,
                new String[]{razorpayPaymentID, points, str_expert_id});
        Call<ResponsePayment> call = apiInterface.ApiAddWallet(builder.build());
        call.enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                try {
                    if (response.body() != null) {
                        if (response.body().getStatus().
                                equalsIgnoreCase("true")) {
                            Toast.makeText(getApplicationContext(), "Successfully Added to User Wallet", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else {
                            Toast.makeText(getApplicationContext(), "Payment Not Add", Toast.LENGTH_SHORT).show();

                        }
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {

            }
        });
    }

}
