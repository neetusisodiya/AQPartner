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
import com.app.oooelePartner.Bean.BeanCompleteLead;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class PastFragment extends Fragment {
    GifImageView rec_not_foundd;
    AdapterOpenLead adapterOpenLead;
    View view;
    BeanCompleteLead beanNewLeads;
    ArrayList<BeanCompleteLead> banVisits;
    RecyclerView.LayoutManager layoutManager;
    String User_Id;
    RecyclerView recyclePastleads;
    AVLoadingIndicatorView bar;
    TextView text_rel;

    public PastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_past, container, false);
        User_Id = String.valueOf(AppPreferences.getSavedUser(getActivity()).getId());

        find();
        getOpenLead();
        return view;
    }

    public void find() {
        // rec_not_foundd = view.findViewById(R.id.rec_not_foundd);
        bar = view.findViewById(R.id.bar);
        text_rel = view.findViewById(R.id.text_rel);
        recyclePastleads = view.findViewById(R.id.recyclePastleads);
        recyclePastleads.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclePastleads.setLayoutManager(layoutManager);
        recyclePastleads.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclePastleads.setAdapter(adapterOpenLead);


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
            Call<ResponseGetOpenLeads> call = service.ApiGetCompleteLead(builder.build());
            call.enqueue(new Callback<ResponseGetOpenLeads>() {
                @Override
                public void onResponse(Call<ResponseGetOpenLeads> call, Response<ResponseGetOpenLeads> response) {
                    try {
                        if (response.body().getStatus().equals("true")) {
                            bar.setVisibility(View.GONE);


                            adapterOpenLead = new AdapterOpenLead(getActivity(), response.body().getData(), false);
                            recyclePastleads.setAdapter(adapterOpenLead);
                        } else {
                            text_rel.setVisibility(View.VISIBLE);
                            bar.setVisibility(View.GONE);

                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetOpenLeads> call, Throwable t) {
                    // bar.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(),"mobile Or Password Wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //progressDialog.dismiss();
            //  scrolls.setVisibility(View.VISIBLE);
            // reli.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

}
