package com.app.oooelePartner.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Adapter.AdapterOpenLead;
import com.app.oooelePartner.Bean.BeanOpenLeads;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseGetOpenLeads;
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



public class OpenFragment extends Fragment {
    public static AVLoadingIndicatorView bar;
    GifImageView rec_not_foundd;
    AdapterOpenLead adapterOpenLead;
    View view;
    BeanOpenLeads beanNewLeads;
    ArrayList<BeanOpenLeads> banVisits;
    RecyclerView.LayoutManager layoutManager;
    String User_Id;
    RecyclerView recycleCurrentleads;
    TextView text_rel;

    public OpenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_open, container, false);
        User_Id = String.valueOf(AppPreferences.getSavedUser(getActivity()).getId());
        find();
        getOpenLead();
        //getCurrentLead();
        return view;
    }

    public void find() {
        //    rec_not_foundd = view.findViewById(R.id.rec_not_foundd);
        bar = view.findViewById(R.id.bar);
        text_rel = view.findViewById(R.id.text_rel);
        recycleCurrentleads = view.findViewById(R.id.recycleCurrentleads);
        recycleCurrentleads.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleCurrentleads.setLayoutManager(layoutManager);
        recycleCurrentleads.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recycleCurrentleads.setAdapter(adapterOpenLead);


    }


    @Override
    public void onResume() {
        super.onResume();
        getOpenLead();

        // getCurrentLead();
    }

    private void getOpenLead() {

        bar.setVisibility(View.VISIBLE);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getContext())) {
            Call<ResponseGetOpenLeads> call = service.ApiGetOpenLeads(builder.build());
            call.enqueue(new Callback<ResponseGetOpenLeads>() {
                @Override
                public void onResponse(Call<ResponseGetOpenLeads> call, Response<ResponseGetOpenLeads> response) {
                    try {
                        if (response.body().getStatus().equals("true")) {
                            bar.setVisibility(View.GONE);
                            banVisits = new ArrayList<>();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                beanNewLeads = new BeanOpenLeads();
                                beanNewLeads.setG_lat(response.body().getData().get(i).getG_lat());
                                beanNewLeads.setG_lng(response.body().getData().get(i).getG_lng());
                                beanNewLeads.setId(response.body().getData().get(i).getId());
                                beanNewLeads.setServ(response.body().getData().get(i).getServ());
                                beanNewLeads.setSubserv(response.body().getData().get(i).getSubserv());
                                beanNewLeads.setFault(response.body().getData().get(i).getFault());
                                beanNewLeads.setQty(response.body().getData().get(i).getQty());
                                beanNewLeads.setUnitRate(response.body().getData().get(i).getUnitRate());
                                beanNewLeads.setDiscount(response.body().getData().get(i).getDiscount());
                                beanNewLeads.setBooking_date(response.body().getData().get(i).getBooking_date());
                                beanNewLeads.setCustomer(response.body().getData().get(i).getCustomer());
                                beanNewLeads.setContact(response.body().getData().get(i).getContact());
                                beanNewLeads.setG_address(response.body().getData().get(i).getG_address());
                                beanNewLeads.setEmail(response.body().getData().get(i).getEmail());
                                beanNewLeads.setAlt_contact_no(response.body().getData().get(i).getAlt_contact_no());
                                beanNewLeads.setCustomer_id(response.body().getData().get(i).getCustomer_id());
                                beanNewLeads.setVisit_time(response.body().getData().get(i).getVisit_time());
                                beanNewLeads.setCreated_at(response.body().getData().get(i).getCreated_at());
                                banVisits.add(beanNewLeads);
                            }
                            adapterOpenLead = new AdapterOpenLead(getActivity(), banVisits);
                            recycleCurrentleads.setAdapter(adapterOpenLead);

                        } else {
                            text_rel.setVisibility(View.VISIBLE);
                            bar.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetOpenLeads> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }


}
