package com.app.oooelePartner.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class Penalties extends Fragment {
    public AVLoadingIndicatorView bar;
    View view;
    BeanGetWalletData beanNewLeads;
    ArrayList<BeanGetWalletData> banVisits;
    RecyclerView.LayoutManager layoutManager;
    String User_Id;
    AdapterGetWalletData adapterExpenses;
    RecyclerView recycleAllCredits;

    public Penalties() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllExpensesLead();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_penalties, container, false);
        User_Id = String.valueOf(AppPreferences.getSavedUser(getActivity()).getId());

        find();
        //getAllExpensesLead();
        return view;
    }

    public void find() {
        //    rec_not_foundd = view.findViewById(R.id.rec_not_foundd);
        bar = view.findViewById(R.id.bar);
        recycleAllCredits = view.findViewById(R.id.recycleAllCredits);
        recycleAllCredits.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycleAllCredits.setLayoutManager(layoutManager);
        //       recycleAllCredits.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        // recycleAllCredits.setAdapter(adapterOpenLead);


    }

    private void getAllExpensesLead() {
        //    CommonUtils.showProDialog1(getApplicationContext());
///
        // progressDialog.show();

        bar.setVisibility(View.VISIBLE);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}, new
                String[]{User_Id});
        if (CommonUtils.isNetworkAvailable(getContext())) {
            Call<ResponseGetWalletData> call = service.ApiGetWalletData(builder.build());


            call.enqueue(new Callback<ResponseGetWalletData>() {
                @Override
                public void onResponse(Call<ResponseGetWalletData> call, Response<ResponseGetWalletData> response) {

                    try {

                        if (response.body().getStatus().equals("true")) {
                            bar.setVisibility(View.GONE);
                            // relihidedata.setVisibility(View.GONE);
                            if (response.body().getData().size() == 0) {
                                Toast.makeText(getContext(), "No record found", Toast.LENGTH_SHORT).show();
                            }
                            //   String Path = response.body().getPath();
                            //   Path2 = response.body().getPath2();
                            banVisits = new ArrayList<>();
                            //     banVisits.clear();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                beanNewLeads = new BeanGetWalletData();
                                int intAmount = Integer.parseInt(response.body().getData().get(i).getAmount());
                                if (intAmount <= 0) {
                                    beanNewLeads.setId(response.body().getData().get(i).getId());
                                    beanNewLeads.setAmount(response.body().getData().get(i).getAmount());
                                    beanNewLeads.setMember_id(response.body().getData().get(i).getMember_id());
                                    beanNewLeads.setCreated(response.body().getData().get(i).getCreated());
                                    beanNewLeads.setDetail(response.body().getData().get(i).getDetail());
                                    banVisits.add(beanNewLeads);
                                }
                            }
                            adapterExpenses = new AdapterGetWalletData(getActivity(), banVisits);
                            recycleAllCredits.setAdapter(adapterExpenses);
                            //  adapterNewLeads = new AdapterNewLeads(getActivity(), banVisits);
                            //    newRecycle.setAdapter(adapterNewLeads);
                        } else {
                            //      bar.setVisibility(View.GONE);
                            //     relihidedata.setVisibility(View.VISIBLE);
                            //     btn_placeorder.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetWalletData> call, Throwable t) {

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
