package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.BeanOpenLeads;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseAccept;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.app.oooelePartner.Utill.OpenLeadsInterface;
import com.app.oooelePartner.fragment.OpenFragment;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterOpenLead extends RecyclerView.Adapter<AdapterOpenLead.ViewHolder> {
    Context context;
    String User_Id;
    List<BeanOpenLeads> banVisits;
    boolean _isLeadOpen;
    private OpenLeadsInterface callBackListener;

    public AdapterOpenLead(Context context, OpenLeadsInterface callBackListener, List<BeanOpenLeads> banVisits, boolean _isLeadOpen) {
        this.context = context;
        this._isLeadOpen = _isLeadOpen;
        this.banVisits = banVisits;
        this.callBackListener = callBackListener;

    }

    public void setCallBackListner(OpenLeadsInterface callBackListener) {
        this.callBackListener = callBackListener;
    }

    @NonNull
    @Override
    public AdapterOpenLead.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_open_leads, viewGroup, false);
        return new AdapterOpenLead.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterOpenLead.ViewHolder holder, final int position) {
        String bookingDate = "Visiting Date: " + banVisits.get(position).getVisit_date();
        String visitTime = "Visit time: " + banVisits.get(position).getVisit_time();
        String faults = "Faults: " + banVisits.get(position).getFault();
        String price = "Price: " + banVisits.get(position).getUnitRate();
        String services = "Service: " + banVisits.get(position).getServ();
        String quantity = "Quantity: " + banVisits.get(position).getQty();
        String subServices = "Sub service: " + banVisits.get(position).getSubserv();
        if (!_isLeadOpen) {
            holder.btn_complete.setVisibility(View.GONE);
            holder.buttonCallUser.setVisibility(View.GONE);
            holder.mapsButton.setVisibility(View.GONE);
        }
        holder.txt_serv.setText(services);
        holder.tvSubServices.setText(subServices);
        holder.tvFault.setText(faults);
        holder.tvVisitTime.setText(visitTime);
        holder.txt_address.setText("Address: " + banVisits.get(position).getG_address());
        holder.tvVisitDate.setText(bookingDate);
        holder.txtPrice.setText(price);
        holder.txt_qtynum.setText(quantity);
        String points = banVisits.get(position).getPoint();
        if (points.equalsIgnoreCase("0")) {
            holder.txtPoints.setVisibility(View.GONE);

        } else {
            holder.txtPoints.setText(points + " Points");

        }
        if (banVisits.get(position).getStatus().equalsIgnoreCase("3")) {
            holder.btn_complete.setText("Completed");

        }
        holder.buttonCallUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + banVisits.get(position).getContact()));
                context.startActivity(intent);
            }
        });

        holder.mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lng = banVisits.get(position).getG_lng();
                String lat = banVisits.get(position).getG_lat();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + lat.substring(0, 5)
                        + "," + lng.substring(0, 5));
                Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });
        holder.btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.btn_complete.getText().toString().equals("Completed")) {
                    getOrderAccept(banVisits.get(position).getId(), position);
                } else
                    startWorkApi(holder.btn_complete, banVisits.get(position).getId(), position);

            }
        });
    }

    private void startWorkApi(final TextView btn_complete, String id, final int position) {
        OpenFragment.bar.setVisibility(View.VISIBLE);


        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id", "lead_id"}, new
                String[]{User_Id, id});
        if (CommonUtils.isNetworkAvailable(context)) {
            Call<ResponseAccept> call = service.apiForStartWorkApi(builder.build());

            call.enqueue(new Callback<ResponseAccept>() {
                @Override
                public void onResponse(@NonNull Call<ResponseAccept> call, @NonNull Response<ResponseAccept> response) {

                    try {

                        btn_complete.setText("Completed");
                        OpenFragment.bar.setVisibility(View.GONE);
                        notifyDataSetChanged();


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseAccept> call, Throwable t) {
                    OpenFragment.bar.setVisibility(View.GONE);

                }
            });
        } else {


            Toast.makeText(context, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public int getItemCount() {
        return banVisits.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void getOrderAccept(String id, final int position) {

        OpenFragment.bar.setVisibility(View.VISIBLE);


        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id", "lead_id"}, new
                String[]{User_Id, id});
        if (CommonUtils.isNetworkAvailable(context)) {
            Call<ResponseAccept> call = service.ApiPartnerCompleteLead(builder.build());


            call.enqueue(new Callback<ResponseAccept>() {
                @Override
                public void onResponse(Call<ResponseAccept> call, Response<ResponseAccept> response) {

                    try {

                        if (response.body().getMessage().equals("Order Successfully Accepted")) {
                            //bar.setVisibility(View.GONE);
                            OpenFragment.bar.setVisibility(View.GONE);
                            /*   banVisits.remove(position);*/
                            callBackListener.onRowClick();

                            notifyDataSetChanged();
                            banVisits.notify();
                            Toast.makeText(context, "Congratulations on completing this lead", Toast.LENGTH_LONG).show();
                        }


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseAccept> call, Throwable t) {
                    OpenFragment.bar.setVisibility(View.GONE);

                    // bar.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(),"mobile Or Password Wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        } else {

            //progressDialog.dismiss();
            //  scrolls.setVisibility(View.VISIBLE);
            // reli.setVisibility(View.VISIBLE);
            Toast.makeText(context, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_serv, tvSubServices,
                tvFault,
                txt_address, txt_qtynum, txtPrice, txtPoints, tvVisitTime, tvVisitDate;
        TextView btn_complete, buttonCallUser, mapsButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            txt_serv = itemView.findViewById(R.id.txt_service);
            tvVisitDate = itemView.findViewById(R.id.txt_visit_date);
            txt_address = itemView.findViewById(R.id.address);
            txt_qtynum = itemView.findViewById(R.id.quantity);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            mapsButton = itemView.findViewById(R.id.user_maps);
            User_Id = String.valueOf(AppPreferences.getSavedUser(context).getId());
            buttonCallUser = itemView.findViewById(R.id.call_user);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtPoints = itemView.findViewById(R.id.txt_points);
            tvVisitTime = itemView.findViewById(R.id.txt_visit_time);
            tvSubServices = itemView.findViewById(R.id.txt_sub_service);
            tvFault = itemView.findViewById(R.id.txt_fault);
        }
    }

}

