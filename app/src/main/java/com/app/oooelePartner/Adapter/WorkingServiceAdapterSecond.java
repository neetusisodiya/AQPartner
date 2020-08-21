package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.SuperServiceBean;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponSelectService;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;

public class WorkingServiceAdapterSecond extends RecyclerView.Adapter<WorkingServiceAdapterSecond.ViewHolder> {
    public Context context;
    Boolean checkBoxState;
    String Cat_ID;
    String User_Id;
    String StrCheckBox;

    ArrayList<SuperServiceBean> restaurantlists;


    public WorkingServiceAdapterSecond(Context context, List<SuperServiceBean> restaurantlists) {
        this.context = context;
        this.restaurantlists = (ArrayList<SuperServiceBean>) restaurantlists;

    }

    @Override
    public int getItemCount() {
        return restaurantlists.size();
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public WorkingServiceAdapterSecond.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_working_service_second,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final SuperServiceBean subserviceBean = restaurantlists.get(position);

        StrCheckBox = subserviceBean.getStatus();

        if (StrCheckBox.equals("yes")) {
            holder.checks.setChecked(true);


        } else {
            holder.checks.setChecked(false);

        }

        holder.checks.setText(subserviceBean.getFault());
        holder.checks.setOnClickListener(v -> {
            Cat_ID = String.valueOf(subserviceBean.getId());
            boolean checked = ((CheckBox) v).isChecked();
            if (checked) {
                holder.checks.setChecked(true);
                SaveWorkingService(User_Id, Cat_ID);


            } else {
                holder.checks.setChecked(false);
                RemoveWorkingService(User_Id, Cat_ID);


            }


        });

    }

    private void RemoveWorkingService(String user_Id, String cat_ID) {
        //    ProgressDialog dialog = new ProgressDialog(context);
        //    dialog.setMessage("Please wait.....");
        //    dialog.show();
        //      ba.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id", "id"},
                new String[]{user_Id, cat_ID});
        Call<ResponSelectService> call = apiInterface.ApiRemoveWorkingService(builder.build());
        //   bar.setVisibility(View.GONE);

        //      dialog.dismiss();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponSelectService resObj = (ResponSelectService) response.body();
                    if (resObj.getMessage().equals("Successfully Removed Working Services")) {

                        // LoginBean loginBean = ((ResponseLogin) response.body()).getData();
                        // AppPreferences.SaveUserdetail(OtpPage.this, loginBean);
                        //  Intent intent = new Intent(OtpPage.this, MainActivity.class);


                        //   startActivity(intent);
                        //    finish();
                    } else {

                        // bar.setVisibility(View.GONE);
                        DynamicToast.makeError(context, "Service Is not Remove").show();
                        // Toast.makeText(LoginNewActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //  bar.setVisibility(View.GONE);
                    DynamicToast.makeError(context, "Error! Please try again! ").show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void SaveWorkingService(String user_Id, String cat_ID) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id", "id"},
                new String[]{user_Id, cat_ID});
        Call<ResponSelectService> call = apiInterface.ApiUpdateWorkingService(builder.build());
        //   bar.setVisibility(View.GONE);

        //    dialog.dismiss();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponSelectService resObj = (ResponSelectService) response.body();
                    if (resObj.getMessage().equals("Successfully Saved Working Service")) {

                        // LoginBean loginBean = ((ResponseLogin) response.body()).getData();
                        // AppPreferences.SaveUserdetail(OtpPage.this, loginBean);
                        //  Intent intent = new Intent(OtpPage.this, MainActivity.class);


                        //   startActivity(intent);
                        //    finish();
                    } else {

                        // bar.setVisibility(View.GONE);
                        DynamicToast.makeError(context, "Successfully Saved Working Service").show();
                        // Toast.makeText(LoginNewActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //  bar.setVisibility(View.GONE);
                    DynamicToast.makeError(context, "Error! Please try again! ").show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checks;


        public ViewHolder(View view) {
            super(view);

            this.checks = itemView.findViewById(R.id.checks);
            checkBoxState = checks.isChecked();
            User_Id = String.valueOf(AppPreferences.getSavedUser(context).getId());


        }

    }

}