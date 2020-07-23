package com.app.oooelePartner.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Activity.MainActivity;
import com.app.oooelePartner.Adapter.AdapterNewLeads;
import com.app.oooelePartner.Bean.BeanNewLeads;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseGetNewLeads;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import okhttp3.FormBody;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    TextView img_nodata;
    View view;
    ArrayList<BeanNewLeads> banVisits;
    BeanNewLeads beanNewLeads;
    AdapterNewLeads adapterNewLeads;
    RecyclerView newRecycle;
   public static AVLoadingIndicatorView bar;
    String userId;
    RecyclerView.LayoutManager layoutManager;
    MainActivity mainActivity;
    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new, container, false);
        mainActivity= (MainActivity) getActivity();
        findd();
        getNewLead();
        return view;
    }

    AppPreferences appPreferences;

    public void findd() {

        bar = view.findViewById(R.id.bar);
        img_nodata = view.findViewById(R.id.img_nodata);
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

    }

/*
    private void getCurrentLead() {
        bar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            Call<ResponseBody> call = apiInterface.ApiWorkingNewLeads(builder.build());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    bar.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        try {
                            String resturentMenu = response.body().string();
                            JSONObject jsonObject = new JSONObject(resturentMenu);
                            String status = jsonObject.optString("status");
                            Log.d("status", status);
                            Log.d("resturentMenu", resturentMenu);

                            if (status.equalsIgnoreCase(String.valueOf(true))) {
                                banVisits = new ArrayList<>();
                                recordenotfound.setVisibility(View.GONE);

                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    BeanNewLeads banVisit = new BeanNewLeads();
                                    banVisit.setId(Integer.parseInt(jsonObject1.getString("id")));
                                    banVisit.setServ(jsonObject1.getString("serv"));
                                    banVisit.setSubserv(jsonObject1.getString("subserv"));
                                    banVisit.setFault(jsonObject1.getString("fault"));
                                    banVisit.setQty(Integer.parseInt(jsonObject1.getString("qty")));
                                    banVisit.setUnitRate(Integer.parseInt(jsonObject1.getString("unitRate")));
                                    banVisit.setDiscount(Integer.parseInt(jsonObject1.getString("discount")));
                                    banVisit.setBooking_date(jsonObject1.getString("booking_date"));
                                    banVisit.setCustomer(jsonObject1.getString("customer"));
                                    banVisit.setContact(jsonObject1.getString("contact"));
                                    banVisit.setG_address(jsonObject1.getString("g_address"));
                                    banVisit.setG_lat(Double.parseDouble(jsonObject1.getString("g_lat")));
                                    banVisit.setG_lng(Double.parseDouble(jsonObject1.getString("g_lng")));
                                    banVisit.setEmail(jsonObject1.getString("email"));
                                    banVisit.setAlt_contact_no(jsonObject1.getString("alt_contact_no"));
                                    banVisit.setCustomer_id(Integer.parseInt(jsonObject1.getString("customer_id")));
                                    banVisit.setVisit_time(jsonObject1.getString("visit_time"));
                                    banVisit.setCreated_at(jsonObject1.getString("created_at"));


                                    banVisits.add(banVisit);

                                    //   String STRDATE= jsonObject1.getString("date");
                                }
                                adapterNewLeads = new AdapterNewLeads(getActivity(), banVisits);
                                newRecycle.setAdapter(adapterNewLeads);
                            } else {
                                recordenotfound.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });
        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }
*/





    private void getNewLead() {
     bar.setVisibility(View.VISIBLE);
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{userId});
        if (CommonUtils.isNetworkAvailable(getContext())) {
            Call<ResponseGetNewLeads> call = service.ApiWorkingNewLeads(builder.build());


            call.enqueue(new Callback<ResponseGetNewLeads>() {
                @Override
                public void onResponse(Call<ResponseGetNewLeads> call, Response<ResponseGetNewLeads> response) {

                    try {

                        if (response.body().getStatus().equals("true")) {
                            bar.setVisibility(View.GONE);
                            adapterNewLeads = new AdapterNewLeads(getActivity(), response.body().getData());
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

                    bar.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(),"mobile Or Password Wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

}
