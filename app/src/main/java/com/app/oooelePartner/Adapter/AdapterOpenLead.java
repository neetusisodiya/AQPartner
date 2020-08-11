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
import com.app.oooelePartner.Fragment.OpenFragment;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseAccept;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterOpenLead extends RecyclerView.Adapter<AdapterOpenLead.ViewHolder> {
    Context context;
    String User_Id;
    List<BeanOpenLeads> banVisits;
    AppPreferences appPreferences;
    public AdapterOpenLead(Context context, List<BeanOpenLeads> banVisits) {
        this.context = context;
        this.banVisits = banVisits;

    }

    @NonNull
    @Override
    public AdapterOpenLead.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_open_leads, viewGroup, false);
        return new AdapterOpenLead.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterOpenLead.ViewHolder holder, final int position) {

        holder.txt_serv.setText(banVisits.get(position).getServ());
        holder.txt_booking_date.setText(banVisits.get(position).getBooking_date());
        holder.txt_address.setText(banVisits.get(position).getG_address());
        holder.txt_visitDate.setText(banVisits.get(position).getVisit_time());
        holder.buttonCallUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + banVisits.get(position).getContact()));
                context.startActivity(intent);
            }
        });
 /*   String uri = "http://maps.google.co.in/maps?q="
                        + banVisits.get(position).getG_lat() + "," + banVisits.get(position).getG_lng();*/


        holder.mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lng = banVisits.get(position).getG_lng();
                String lat = banVisits.get(position).getG_lat();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + lng + "," + lat);
                Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                intent.setPackage("com.google.android.apps.maps");
                context.startActivity(intent);
            }
        });
        //todo replace with start work text
        holder.btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderAccept(banVisits.get(position).getId(), position);
            }
        });
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
                            banVisits.remove(position);
                            Toast.makeText(context,"Congratulations on completing this lead",Toast.LENGTH_LONG).show();
                        } else {
                            //      bar.setVisibility(View.GONE);
                            //     relihidedata.setVisibility(View.VISIBLE);
                            //     btn_placeorder.setVisibility(View.GONE);
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
        TextView txt_serv, txt_booking_date, txt_address, txt_qtynum, txt_visitDate;
        TextView btn_complete, buttonCallUser, mapsButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            txt_serv = itemView.findViewById(R.id.txt_serv);
            txt_booking_date = itemView.findViewById(R.id.txt_booking_date);
            txt_address = itemView.findViewById(R.id.txt_address);
            txt_qtynum = itemView.findViewById(R.id.txt_qtynum);
            txt_visitDate = itemView.findViewById(R.id.txt_visitDate);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            mapsButton = itemView.findViewById(R.id.user_maps);
            User_Id = String.valueOf(AppPreferences.getSavedUser(context).getId());
            buttonCallUser = itemView.findViewById(R.id.call_user);
            //shortage.setVisibility(View.GONE);
        }
    }
}

