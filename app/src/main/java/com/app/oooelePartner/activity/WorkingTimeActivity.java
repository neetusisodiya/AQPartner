package com.app.oooelePartner.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Adapter.AdapterWorkingTime;
import com.app.oooelePartner.Bean.BeanTimeSlot;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseTimeSelect;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.AppBaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WorkingTimeActivity extends AppBaseActivity implements View.OnClickListener {
    RecyclerView.LayoutManager layoutManager;
    AdapterWorkingTime adapterWorkingTime;
    String User_Id;
    ImageView back_item;
    ArrayList<ResponseTimeSelect> responseSuperServices;
    RecyclerView recycleTime;
    AVLoadingIndicatorView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workign_time);
        User_Id = String.valueOf(AppPreferences.getSavedUser(mContext).getId());
        find();
        AllMainData();

    }

    public void find() {
        recycleTime = findViewById(R.id.recycleTime);
        back_item = findViewById(R.id.back_item);
        bar = findViewById(R.id.bar);
        recycleTime.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleTime.setLayoutManager(layoutManager);
        recycleTime.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recycleTime.setAdapter(adapterWorkingTime);
        back_item.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == back_item) {
            onBackPressed();
        }
    }

    public void AllMainData() {
        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}
                ,  //new String[]{User_ID,"1",selectedDateStr});
                new String[]{User_Id});
        Call<ResponseBody> call = apiInterface.ApiWorkingTimeSlot(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                bar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    try {
                        String resturentMenu = response.body().string();
                        JSONObject jsonObject = new JSONObject(resturentMenu);
                        String status = jsonObject.optString("status");

                        if (status.equalsIgnoreCase(String.valueOf(true))) {
                            responseSuperServices = new ArrayList<>();
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                ResponseTimeSelect subserviceBean = new ResponseTimeSelect();
                                subserviceBean.setDay(jsonObject1.getString("day"));
                                if (jsonObject1.getString("time").equalsIgnoreCase("false")) {

                                } else {
                                    ArrayList<BeanTimeSlot> mListSubCat = new ArrayList<>();
                                    JSONArray jsonArray1 = jsonObject1.getJSONArray("time");
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                        BeanTimeSlot mRestaurent_subCatBean = new BeanTimeSlot();
                                        mRestaurent_subCatBean.setId(Integer.parseInt(jsonObject2.getString("id")));
                                        mRestaurent_subCatBean.setStatus(jsonObject2.getString("status"));
                                        mRestaurent_subCatBean.setTimes(jsonObject2.getString("times"));
                                        mListSubCat.add(mRestaurent_subCatBean);
                                    }

                                    subserviceBean.setTime(mListSubCat);
                                    responseSuperServices.add(subserviceBean);
                                }
                            }
                            if (responseSuperServices.size() > 0) {
                                adapterWorkingTime = new AdapterWorkingTime(getApplication(), responseSuperServices);
                                recycleTime.setAdapter(adapterWorkingTime);
                                //  adapterWorkingTime.notifyDataSetChanged();

                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onApiFailure(call, t);
                bar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onApiFailure(Call<ResponseBody> call, Throwable t) {
        bar.setVisibility(View.GONE);
    }


}
