package com.app.oooelePartner.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.app.oooelePartner.Adapter.PointsAdapter;
import com.app.oooelePartner.Adapter.TabAdapter;
import com.app.oooelePartner.Bean.PointsData;
import com.app.oooelePartner.Bean.PointsResponse;
import com.app.oooelePartner.Fragment.AllCreditFragment;
import com.app.oooelePartner.Fragment.Expenses;
import com.app.oooelePartner.Fragment.Penalties;
import com.app.oooelePartner.Fragment.Recharge;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseGetWalletData;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditHistory extends AppCompatActivity implements View.OnClickListener {
    List<PointsData> banVisits;
    ImageView img_back;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button btncredits;
    String User_Id;
    TextView txt_cred;
    GridView recyclerView;
    PointsAdapter adapterExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_history);
        User_Id = String.valueOf(AppPreferences.getSavedUser(getApplicationContext()).getId());
        viewPager = (ViewPager) findViewById(R.id.credit_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        img_back = findViewById(R.id.img_back);
        txt_cred = findViewById(R.id.txt_cred);
        btncredits = findViewById(R.id.btncredits);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        img_back.setOnClickListener(this);

        recyclerView = findViewById(R.id.rechargeAmount);
        banVisits = new ArrayList<>();

      /*  PointsData pointsData = new PointsData();


        pointsData.setPoints("50 Points");
        pointsData.setRupee(" 500");        banVisits.add(pointsData);

        pointsData.setPoints("100 Points");
        pointsData.setRupee(" 1000");
        banVisits.add(pointsData);

        pointsData = new PointsData();

        pointsData.setPoints("500 Points");
        pointsData.setRupee(" 5000");
        banVisits.add(pointsData);

        pointsData = new PointsData();

        pointsData.setPoints("1000 Points");
        pointsData.setRupee(" 10000");
        banVisits.add(pointsData);
        adapterExpenses.notifyDataSetChanged();
*/
      getAllCreditLead();
          getPointsData();

    }

    private void getPointsData() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        if (CommonUtils.isNetworkAvailable(CreditHistory.this)) {
            Call<PointsResponse> call = service.getPoints();

            call.enqueue(new Callback<PointsResponse>() {
                @Override
                public void onResponse(Call<PointsResponse> call, Response<PointsResponse> response) {
                    adapterExpenses = new PointsAdapter(CreditHistory.this, response.body().getData());
                    recyclerView.setAdapter(adapterExpenses);
                }

                @Override
                public void onFailure(Call<PointsResponse> call, Throwable t) {

                }
            });
        } else {

            Toast.makeText(this, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }String totalCredit="0";
    private void getAllCreditLead() {

        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(this)) {
            Call<ResponseGetWalletData> call = service.ApiGetWalletData(builder.build());


            call.enqueue(new Callback<ResponseGetWalletData>() {
                @Override
                public void onResponse(Call<ResponseGetWalletData> call, Response<ResponseGetWalletData> response) {

                    try {

                        if (response.body().getStatus().equals("true")) {
                            String string=response.body().getTotal_amount();
                            if(!string.isEmpty()){
                                totalCredit=string;

                            }
                            txt_cred.setText(string+" Points");

                        } else {

                            txt_cred.setText("0 Points");


                        }


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseGetWalletData> call, Throwable t) {

                       }
            });
        } else {

            Toast.makeText(this, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new AllCreditFragment(), "All");
        tabAdapter.addFragment(new Recharge(), "Recharge");
        tabAdapter.addFragment(new Expenses(), "Expenses");
        tabAdapter.addFragment(new Penalties(), "Penalties");
        viewPager.setAdapter(tabAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            onBackPressed();
        }
        if (v == btncredits) {
            //startActivity(new Intent(getApplicationContext(), PaymentActivity.class));
          //  finish();
        }
    }
}
