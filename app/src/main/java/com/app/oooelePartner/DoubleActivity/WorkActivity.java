package com.app.oooelePartner.DoubleActivity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.oooelePartner.Adapter.WorkignServiceAdapter;
import com.app.oooelePartner.Bean.SuperServiceBean;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseSuperService;
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

public class WorkActivity extends AppBaseActivity implements View.OnClickListener {
RecyclerView recycleItem;
String User_Id;
AVLoadingIndicatorView bar;
WorkignServiceAdapter workignServiceAdapter;
     ArrayList<ResponseSuperService>  responseSuperServices;
    RecyclerView.LayoutManager layoutManager;
    ImageView back_item;
    private int scrollPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        User_Id = String.valueOf(AppPreferences.getSavedUser(mContext).getId());

        find();
         AllMainData();
     }

    public void find(){
        recycleItem=findViewById(R.id.recycleItem);
        bar=findViewById(R.id.bar);
        back_item=findViewById(R.id.back_item);
        recycleItem.setHasFixedSize(true);
         layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleItem.setLayoutManager(layoutManager);
        recycleItem.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recycleItem.setAdapter(workignServiceAdapter);
        back_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
if (v==back_item){
    onBackPressed();

}
    }

    @Override
    protected void onResume() {
        super.onResume();
    //    recycleItem.setScrollY(scrollPosition);
    }

    public void AllMainData() {
        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id"}
                ,  //new String[]{User_ID,"1",selectedDateStr});
                new String[]{User_Id});
        Call<ResponseBody> call = apiInterface.ApiWorkingService(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Log.d("ddd", response.body().string());
                bar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    try {
                        String resturentMenu = response.body().string();
                        JSONObject jsonObject = new JSONObject(resturentMenu);
                        String status = jsonObject.optString("status");
                         //  String catpath = jsonObject.optString("catpath");
                        //   String SubcatPath = jsonObject.optString("subcatPath");

                        if (status.equalsIgnoreCase(String.valueOf(true))) {
                            responseSuperServices = new ArrayList<>();
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                ResponseSuperService subserviceBean = new ResponseSuperService();
                                subserviceBean.setService(jsonObject1.getString("service"));
                                Log.e("iddddd", jsonObject1.getString("service"));
                                if (jsonObject1.getString("faults").
                                        equalsIgnoreCase("false")) {
                                /*  Restaurent_subCatBean mRestaurent_subCatBean=new Restaurent_subCatBean();
                                  mRestaurent_subCatBean.setSub_cat_id("00");
                                  mRestaurent_subCatBean.setSub_cat_desc("Select Sub Category");
                                  mRestaurent_subCatBean.setSub_cat_photo("Select Sub Category");
                                  mRestaurent_subCatBean.setSub_cat_price("Select Sub Category");
                                  mRestaurent_subCatBean.setSub_cate_name("Select Sub Category");
                                  ArrayList<Restaurent_subCatBean> mListSubCat=new ArrayList<>();
                                  mListSubCat.add(mRestaurent_subCatBean);*/
                                } else {
                                    ArrayList<SuperServiceBean> mListSubCat = new ArrayList<>();
                                    JSONArray jsonArray1 = jsonObject1.getJSONArray("faults");
                                    Log.e("arraysublenth", String.valueOf(jsonArray1.length()));
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                        SuperServiceBean mRestaurent_subCatBean = new SuperServiceBean();
                                      mRestaurent_subCatBean.setId(Integer.parseInt(jsonObject2.getString("id")));
                                        mRestaurent_subCatBean.setFault(jsonObject2.getString("fault"));
                                        mRestaurent_subCatBean.setStatus(jsonObject2.getString("status"));

                                        mListSubCat.add(mRestaurent_subCatBean);
                                    }
                                    subserviceBean.setFaults(mListSubCat);
                                    responseSuperServices.add(subserviceBean);
                                }
                            }
                            if (responseSuperServices.size() > 0) {
                                workignServiceAdapter = new WorkignServiceAdapter(getApplication(), responseSuperServices);
                                recycleItem.setAdapter(workignServiceAdapter);
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
