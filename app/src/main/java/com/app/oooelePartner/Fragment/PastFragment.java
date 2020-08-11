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

import com.app.oooelePartner.Adapter.AdapterPast;
import com.app.oooelePartner.Bean.BeanCompleteLead;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseGetCompletedLeads;
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
    AdapterPast adapterOpenLead;
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
          view= inflater.inflate(R.layout.fragment_past, container, false);
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
            Call<ResponseGetCompletedLeads> call = service.ApiGetCompleteLead(builder.build());
            call.enqueue(new Callback<ResponseGetCompletedLeads>() {
                @Override
                public void onResponse(Call<ResponseGetCompletedLeads> call, Response<ResponseGetCompletedLeads> response) {
                    try {
                        if (response.body().getStatus().equals("true")) {
                            bar.setVisibility(View.GONE);

                            banVisits = new ArrayList<>();
                             for (int i = 0; i < response.body().getData().size(); i++) {
                                beanNewLeads = new BeanCompleteLead();
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

                            adapterOpenLead = new AdapterPast(getActivity(), banVisits);
                            recyclePastleads.setAdapter(adapterOpenLead);
                         } else {
                            text_rel.setVisibility(View.VISIBLE);
                            bar.setVisibility(View.GONE);

                        }
                    } catch (Exception e) {
                    }
                }
                @Override
                public void onFailure(Call<ResponseGetCompletedLeads> call, Throwable t) {
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
