package com.app.oooelePartner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.app.oooelePartner.Adapter.PointsAdapter;
import com.app.oooelePartner.Adapter.TabAdapter;
import com.app.oooelePartner.Bean.PointsData;
import com.app.oooelePartner.Bean.PointsResponse;
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

public class CreditHistory extends Fragment {
    List<PointsData> banVisits;
    Button btncredits;
    String User_Id;
    TextView txt_cred;
    GridView recyclerView;
    PointsAdapter adapterExpenses;

    int totalCredit = 0;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_credit_history, container, false);

        User_Id = String.valueOf(AppPreferences.getSavedUser(getActivity()).getId());
        viewPager = view.findViewById(R.id.credit_viewpager);
        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        txt_cred = view.findViewById(R.id.txt_cred);
        btncredits = view.findViewById(R.id.btncredits);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        recyclerView = view.findViewById(R.id.rechargeAmount);
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
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getPointsData() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<PointsResponse> call = service.getPoints();

            call.enqueue(new Callback<PointsResponse>() {
                @Override
                public void onResponse(Call<PointsResponse> call, Response<PointsResponse> response) {
                    adapterExpenses = new PointsAdapter(getActivity(), response.body().getData());
                    recyclerView.setAdapter(adapterExpenses);
                }

                @Override
                public void onFailure(Call<PointsResponse> call, Throwable t) {

                }
            });
        } else {

            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

    private void getAllCreditLead() {

        final ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<ResponseGetWalletData> call = service.ApiGetWalletData(builder.build());


            call.enqueue(new Callback<ResponseGetWalletData>() {
                @Override
                public void onResponse(Call<ResponseGetWalletData> call, Response<ResponseGetWalletData> response) {

                    try {

                        if (response.body().getStatus().equals("true")) {
                            String string = response.body().getTotal_amount();
                            if (!string.isEmpty()) {
                                txt_cred.setText(string + " Oooele Points");
                            }

                        } else {

                            txt_cred.setText(totalCredit + " Oooele Points");


                        }


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseGetWalletData> call, Throwable t) {

                }
            });
        } else {

            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter tabAdapter = new TabAdapter(getActivity().getSupportFragmentManager());
        tabAdapter.addFragment(new AllCreditFragment(), "All");
        tabAdapter.addFragment(new Recharge(), "Recharge");
        tabAdapter.addFragment(new Expenses(), "Expenses & Penalties");
        viewPager.setAdapter(tabAdapter);
    }


}
