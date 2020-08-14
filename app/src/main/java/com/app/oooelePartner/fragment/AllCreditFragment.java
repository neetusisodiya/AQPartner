package com.app.oooelePartner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Adapter.AdapterGetWalletData;
import com.app.oooelePartner.Bean.BeanGetWalletData;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseGetWalletData;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllCreditFragment extends Fragment {
    public AVLoadingIndicatorView bar;
    View view;
    BeanGetWalletData beanNewLeads;
    ArrayList<BeanGetWalletData> banVisits;
    RecyclerView.LayoutManager layoutManager;
    String User_Id;
    AdapterGetWalletData adapterGetWalletData;
    RecyclerView recycleAllCredits;

    public AllCreditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all__credit, container, false);
        User_Id = String.valueOf(AppPreferences.getSavedUser(getActivity()).getId());
        find();


        return view;

    }

    public void find() {
        bar = view.findViewById(R.id.bar);
        recycleAllCredits = view.findViewById(R.id.recycleAllCredits);
        recycleAllCredits.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleAllCredits.setLayoutManager(layoutManager);


    }

    @Override
    public void onResume() {
        super.onResume();
        getAllCreditLead();
    }

    private void getAllCreditLead() {


        bar.setVisibility(View.VISIBLE);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getContext())) {
            Call<ResponseGetWalletData> call = service.ApiGetWalletData(builder.build());


            call.enqueue(new Callback<ResponseGetWalletData>() {
                @Override
                public void onResponse(Call<ResponseGetWalletData> call, @NonNull Response<ResponseGetWalletData> response) {

                    try {

                        if (response.body().getStatus().equals("true")) {
                            bar.setVisibility(View.GONE);
                            if (response.body().getData().size() == 0) {
                                Toast.makeText(getContext(), "No record found", Toast.LENGTH_SHORT).show();
                            }
                            banVisits = new ArrayList<>();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                beanNewLeads = new BeanGetWalletData();

                                beanNewLeads.setId(response.body().getData().get(i).getId());
                                beanNewLeads.setAmount(response.body().getData().get(i).getAmount());
                                beanNewLeads.setMember_id(response.body().getData().get(i).getMember_id());
                                beanNewLeads.setCreated(response.body().getData().get(i).getCreated());
                                beanNewLeads.setDetail(response.body().getData().get(i).getDetail());
                                banVisits.add(beanNewLeads);
                            }
                            adapterGetWalletData = new AdapterGetWalletData(getActivity(), banVisits);
                            recycleAllCredits.setAdapter(adapterGetWalletData);

                        }


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseGetWalletData> call, Throwable t) {

                }
            });
        } else {

            Toast.makeText(getContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

}
