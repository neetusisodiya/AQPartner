package com.app.oooelePartner.fragment;


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
import com.app.oooelePartner.Utill.OpenLeadsInterface;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OpenFragment extends Fragment implements OpenLeadsInterface {
    OpenLeadsInterface openLeadsInterface;
    public static AVLoadingIndicatorView bar;
    AdapterOpenLead adapterOpenLead;
    View view;
    ArrayList<BeanOpenLeads> banVisits;
    RecyclerView.LayoutManager layoutManager;
    String User_Id;
    RecyclerView recycleCurrentleads;
    TextView text_rel;

    public OpenFragment() {
    }

    public void onRowClick() {
        getOpenLead();
    }

    public void find() {
        bar = view.findViewById(R.id.bar);
        text_rel = view.findViewById(R.id.text_rel);
        recycleCurrentleads = view.findViewById(R.id.recycleCurrentleads);
        recycleCurrentleads.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleCurrentleads.setLayoutManager(layoutManager);
        recycleCurrentleads.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        recycleCurrentleads.setAdapter(adapterOpenLead);


    }


    @Override
    public void onResume() {
        super.onResume();
        getOpenLead();

        // getCurrentLead();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_open, container, false);
        User_Id = String.valueOf(AppPreferences.getSavedUser(getActivity()).getId());
        find();
        openLeadsInterface = this;
        getOpenLead();
        //getCurrentLead();
        return view;
    }

    public void getOpenLead() {

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

                            adapterOpenLead = new AdapterOpenLead(getActivity()
                                    , openLeadsInterface, response.body().getData(), true);
                            recycleCurrentleads.setAdapter(adapterOpenLead);
                            adapterOpenLead.notifyDataSetChanged();
                        } else {
                            text_rel.setVisibility(View.VISIBLE);
                            bar.setVisibility(View.GONE);
                            recycleCurrentleads.setVisibility(View.GONE);
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
