package com.app.oooelePartner.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.BeanNewLeads;
import com.app.oooelePartner.Fragment.HomeFragment;
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

public class AdapterNewLeads extends RecyclerView.Adapter<AdapterNewLeads.ViewHolder> {
    Context context;
    String str_expert_id;
    List<BeanNewLeads> banVisits;

    public AdapterNewLeads(Context context, List<BeanNewLeads> banVisits) {
        this.context = context;
        this.banVisits = banVisits;

    }

    @Override
    public AdapterNewLeads.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_new_leads, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterNewLeads.ViewHolder holder, final int position) {
        holder.txt_contact.setText(banVisits.get(position).getContact());
        holder.txt_bookingddate.setText(banVisits.get(position).getBooking_date());
        holder.txt_name.setText(banVisits.get(position).getCustomer());
        holder.txt_visittime.setText(banVisits.get(position).getVisit_time());
        holder.txt_service2.setText(banVisits.get(position).getFault());
        //todo set charges
        holder.txtPrice.setText(banVisits.get(position).getPoint());
        holder.txt_service.setText(banVisits.get(position).getServ() + "/" +
                banVisits.get(position).getSubserv());
        if (banVisits.get(position).isAccepted()==1) {
            holder.cardView.setAlpha(0.2f);
            holder.accept.setVisibility(View.GONE);
            holder.txt_contact.setVisibility(View.GONE);
            holder.txt_name.setVisibility(View.GONE);

        }

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(banVisits.get(position), position);

            }
        });
    }

    public void open(final BeanNewLeads banVisits, final int position) {
        str_expert_id = banVisits.getExpert_id();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(banVisits.getPoint() + " will be deducted from your wallet as a charge of leads");
        alertDialogBuilder.setPositiveButton("OKay",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        acceptLead(banVisits, position);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return banVisits.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_contact, txt_bookingddate, txt_name, txt_visittime, txt_service, txt_service2, txtPrice;
        ImageView img_location, img_qty;
        Button accept;
        CardView cardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            txt_contact = itemView.findViewById(R.id.txt_contact);
            txt_service2 = itemView.findViewById(R.id.txt_service2);
            txt_bookingddate = itemView.findViewById(R.id.txt_bookingddate);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_visittime = itemView.findViewById(R.id.txt_visittime);
            txt_service = itemView.findViewById(R.id.txt_service);
            img_location = itemView.findViewById(R.id.img_location);
            img_qty = itemView.findViewById(R.id.img_qty);
            accept = itemView.findViewById(R.id.accept);
            txtPrice = itemView.findViewById(R.id.txt_price);
            cardView = itemView.findViewById(R.id.card_leads);
            //shortage.setVisibility(View.GONE);
        }
    }


    private void acceptLead(BeanNewLeads banVisits2, final int position) {
        HomeFragment.bar.setVisibility(View.VISIBLE);
        final String id = banVisits2.getId();
        final String serviceId = banVisits2.getServ_id();
        final String points = banVisits2.getPoint();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id", "lead_id", "serv_id", "point"}, new
                String[]{str_expert_id, id, serviceId, points});
        if (CommonUtils.isNetworkAvailable(context)) {
            Call<ResponseAccept> call = service.ApiPartnerAccept(builder.build());


            call.enqueue(new Callback<ResponseAccept>() {
                @Override
                public void onResponse(Call<ResponseAccept> call, Response<ResponseAccept> response) {

                    try {

                        if (response.body().getMessage().equals("Order Successfully Accepted")) {
                            HomeFragment.bar.setVisibility(View.GONE);
                            banVisits.remove(position);
                            Toast.makeText(context, "Lead Booked!!", Toast.LENGTH_LONG).show();
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
                    HomeFragment.bar.setVisibility(View.GONE);

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


}