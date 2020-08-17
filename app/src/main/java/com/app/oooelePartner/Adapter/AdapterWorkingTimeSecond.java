package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.BeanTimeSlot;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseProfileUpload;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AdapterWorkingTimeSecond extends RecyclerView.Adapter<AdapterWorkingTimeSecond.ViewHolder> {
    public Context context;
    Boolean checkBoxState;
    int check = 1;
    String StrDataID;

    String STrTime;
    String User_Id;
    String StrDiscount;
    String StrCheckBox;
    String StrSID = "";
    String totalquanti;
    ArrayList<BeanTimeSlot> beanTimeSlots;
    String day;
// private ArrayList<Modal_restaurantlist> list;


    public AdapterWorkingTimeSecond(Context context, List<BeanTimeSlot> beanTimeSlots, String day) {
        this.context = context;
        this.day = day;
        this.beanTimeSlots = (ArrayList<BeanTimeSlot>) beanTimeSlots;

    }

    @Override
    public int getItemCount() {
        return beanTimeSlots.size();
    }

    public Object getItem(int position) {
        return context;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public AdapterWorkingTimeSecond.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_working_time_second, viewGroup, false);
        return new AdapterWorkingTimeSecond.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterWorkingTimeSecond.ViewHolder holder, final int position) {
        holder.txttime.setText(beanTimeSlots.get(position).getTimes());
        if (beanTimeSlots.get(position).getStatus().equals("yes")) {
            holder.txttime.setTextColor(ContextCompat.getColor(context, R.color.colorGreen));
        } else {
            holder.txttime.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));

        }
        holder.txttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == 1) {
                    STrTime = beanTimeSlots.get(position).getTimes();
                    holder.txttime.setTextColor(ContextCompat.getColor(context, R.color.colorGreen));
                    ApiSetWorkignArea(position, holder.txttime, day);
                    check = 0;
                } else if (beanTimeSlots.get(position).getStatus().equals("no")) {
                    STrTime = beanTimeSlots.get(position).getTimes();
                    holder.txttime.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));

                    ApiRemoveWorkignArea();
                    check = 1;
                }

            }
        });
    }

    private void ApiSetWorkignArea(final int position, final TextView txttime, String day) {
        // avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id", "day", "work_time"},
                new String[]{User_Id, day, STrTime});
        Call<ResponseProfileUpload> call = apiInterface.ApiSetWorkingTime(builder.build());

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponseProfileUpload resObj = (ResponseProfileUpload) response.body();
                    StrDataID = String.valueOf(resObj.getData());
                    if (resObj.getMessage().equals("Successfully Saved Working Time Slot")) {
                        // txttime.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));


                        //   notifyDataSetChanged();
                        DynamicToast.makeSuccess(context, "Successfully Saved Working Time Slot").show();
                    } else {
                        //    avi.setVisibility(View.GONE);
                        DynamicToast.makeError(context, "Time Is Not Updated").show();
                        // Toast.makeText(LoginNewActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //   avi.setVisibility(View.GONE);
                    DynamicToast.makeError(context, "Error! Please try again! ").show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ApiRemoveWorkignArea() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"id"},
                new String[]{StrDataID});
        Call<ResponseProfileUpload> call = apiInterface.ApiRemoveWorkingTime(builder.build());


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponseProfileUpload resObj = (ResponseProfileUpload) response.body();

                    //String Str= String.valueOf(resObj.getData());
                    // Log.e("STRTS",""+Str);
                    if (resObj.getMessage().equals("Successfully Removed Working Time Slot")) {

                        // txttime.setTextColor(Color.RED);
                        // txttime.setTextColor(Color.BLACK);
                        notifyDataSetChanged();
                        DynamicToast.makeSuccess(context, "Successfully Removed Working Time Slot").show();
                    } else {
                        //    avi.setVisibility(View.GONE);
                        DynamicToast.makeError(context, "Time Is Not Updated").show();
                        // Toast.makeText(LoginNewActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //   avi.setVisibility(View.GONE);
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

        TextView txttime;


        public ViewHolder(View view) {
            super(view);
            this.txttime = itemView.findViewById(R.id.txttime);
            User_Id = String.valueOf(AppPreferences.getSavedUser(context).getId());
        }
    }
}
