package com.app.oooelePartner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.oooelePartner.activity.MainActivity;
import com.app.oooelePartner.Adapter.AdapterNewLeads;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseGetNewLeads;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.wang.avi.AVLoadingIndicatorView;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    public static AVLoadingIndicatorView bar;
    TextView img_nodata, tvTime, aboutTerms;
    View view;
    AdapterNewLeads adapterNewLeads;
    RecyclerView newRecycle;
    String userId;
    RecyclerView.LayoutManager layoutManager;
    MainActivity mainActivity;
    AppPreferences appPreferences;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new, container, false);
        mainActivity = (MainActivity) getActivity();
        findd();
        getNewLead();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewLead();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
        return view;
    }

    public void findd() {
        aboutTerms = view.findViewById(R.id.terms);
        bar = view.findViewById(R.id.bar);
        img_nodata = view.findViewById(R.id.img_nodata);
        mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh_items);
        tvTime = view.findViewById(R.id.time);
        appPreferences = new AppPreferences(getContext());
        userId = appPreferences.getUserData(AppPreferences.KEY_ID);
        //   recordenotfound = view.findViewById(R.id.recordenotfound);
        newRecycle = view.findViewById(R.id.newRecycle);
        newRecycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newRecycle.setLayoutManager(layoutManager);
        newRecycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        newRecycle.setAdapter(adapterNewLeads);

    }

    @Override
    public void onResume() {
        super.onResume();
        CommonUtils.tabChange(getActivity(), mainActivity.iv_home, mainActivity.ivsearch, mainActivity.iv_cart, mainActivity.iv_account, mainActivity.txthome, mainActivity.txtsearch, mainActivity.txtcart, mainActivity.txtaccount);

        userId = appPreferences.getUserData(AppPreferences.KEY_ID);
        getNewLead();
    }


    private void getNewLead() {
        bar.setVisibility(View.VISIBLE);
        aboutTerms.setVisibility(View.GONE);
        tvTime.setVisibility(View.GONE);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{userId});
        if (CommonUtils.isNetworkAvailable(getContext())) {
            Call<ResponseGetNewLeads> call = service.ApiWorkingNewLeads(builder.build());


            call.enqueue(new Callback<ResponseGetNewLeads>() {
                @Override
                public void onResponse(@NonNull Call<ResponseGetNewLeads> call,
                                       @NonNull Response<ResponseGetNewLeads> response) {

                    try {

                        if (response.body().getStatus()) {
                            bar.setVisibility(View.GONE);
                            if (response.body().getData().size() != 0) {
                                aboutTerms.setVisibility(View.VISIBLE);
                                aboutTerms.setText(response.body().getCharge_text());
                                tvTime.setVisibility(View.VISIBLE);
                                tvTime.setText(response.body().getTime_text());
                            }
                            adapterNewLeads = new AdapterNewLeads(getActivity(), response.body().getData(),
                                    response.body().getTotal_point(), userId);
                            newRecycle.setAdapter(adapterNewLeads);
                        } else {
                            img_nodata.setVisibility(View.VISIBLE);
                            bar.setVisibility(View.GONE);
                        }


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseGetNewLeads> call, Throwable t) {
                    // CommonUtils.hideProgressDoalog();
                    img_nodata.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(),"mobile Or Password Wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

}
