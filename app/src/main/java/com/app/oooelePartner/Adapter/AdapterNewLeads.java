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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.BeanNewLeads;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseAccept;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.app.oooelePartner.fragment.HomeFragment;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterNewLeads extends RecyclerView.Adapter<AdapterNewLeads.ViewHolder> {
    Context context;
    String str_expert_id;
    List<BeanNewLeads> banVisits;
    int totalPoints;
    int points;
    String pointsValue;

    public AdapterNewLeads(Context context, List<BeanNewLeads> banVisits, int totalPoints, String str_expert_id) {
        this.context = context;
        this.banVisits = banVisits;
        this.totalPoints = totalPoints;
        this.str_expert_id = str_expert_id;
    }

    @NonNull
    @Override
    public AdapterNewLeads.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_new_leads, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterNewLeads.ViewHolder holder, final int position) {
        points = banVisits.get(position).getPoint();
        if (banVisits.get(position).getCharges().equals("0")) {
            holder.tvCharges.setVisibility(View.GONE);
        } else {
            String charges = "Conveyance charges: " + banVisits.get(position).getCharges();
            holder.tvCharges.setText(charges);
        }
        String orderId = "Order id: " + banVisits.get(position).getOrder_id();
        holder.tvOrderId.setText(orderId);
        String bookingDate = "Visiting Date:\n " + banVisits.get(position).getVisit_date();
        holder.txt_bookingddate.setText(bookingDate);
        String visitTime;
        visitTime = "Visit time: " + banVisits.get(position).getVisit_time();
        holder.txt_visittime.setText(visitTime);
        String faults = "Faults: " + banVisits.get(position).getFault();
        holder.txt_service2.setText(faults);
        String price = "Price: " + banVisits.get(position).getUnitRate();
        String quantity = banVisits.get(position).getQty();
        holder.txtPrice.setText(price);
        holder.quantity.setText(quantity);
        pointsValue = String.valueOf(points);
        if (pointsValue.equalsIgnoreCase("0")) {
            holder.txtPoints.setVisibility(View.GONE);

        } else {
            holder.txtPoints.setText(pointsValue + " Points");

        }
        String services = "Service: " + banVisits.get(position).getServ();
        holder.txt_service.setText(services);
        String subServices = "Sub service: " + banVisits.get(position).getSubserv();
        holder.textSubServices.setText(subServices);
        if (banVisits.get(position).isAccepted().equals("1")) {

            if (!banVisits.get(position).getAccept_id().equals(str_expert_id)) {
                holder.cardView.setAlpha(0.2f);
                holder.accept.setVisibility(View.GONE);
            } else {
                holder.cardView.setVisibility(View.GONE);
            }

        }


        holder.accept.setOnClickListener(v -> {


            if (!pointsValue.equalsIgnoreCase("0")) {
                if (totalPoints < points) {
                    if (totalPoints == 0) {
                        lowBalance("You don't have credit in your wallet. Please recharge first");

                    } else
                        lowBalance("Your wallet balance is low. Please recharge first");
                } else {
                    open(banVisits.get(position), position, holder);

                }
            } else {
                acceptLead(banVisits.get(position), position, holder);

            }
        });
    }

    public void open(final BeanNewLeads banVisits, final int position, final ViewHolder holder) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(banVisits.getPoint() + " will be deducted from your wallet as a charge of leads");
        alertDialogBuilder.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        acceptLead(banVisits, position, holder);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void lowBalance(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage
                (message);
        alertDialogBuilder.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
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

    private void acceptLead(BeanNewLeads banVisits2, final int position, final ViewHolder viewHolder) {
        HomeFragment.bar.setVisibility(View.VISIBLE);

        final String id = banVisits2.getId();
        final String serviceId = banVisits2.getServ_id();
        final int points = banVisits2.getPoint();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"expert_id",
                "lead_id", "serv_id", "point"}, new
                String[]{str_expert_id, id, serviceId, String.valueOf(points)});
        if (CommonUtils.isNetworkAvailable(context)) {
            Call<ResponseAccept> call = service.ApiPartnerAccept(builder.build());


            call.enqueue(new Callback<ResponseAccept>() {
                @Override
                public void onResponse(Call<ResponseAccept> call, Response<ResponseAccept> response) {

                    try {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

                        HomeFragment.bar.setVisibility(View.GONE);
                        viewHolder.cardView.setVisibility(View.GONE);
                        viewHolder.itemView.setVisibility(View.GONE);


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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_bookingddate,
                quantity, txt_visittime, tvCharges, txt_service, tvOrderId, txt_service2, textSubServices, txtPrice, txtPoints;
        ImageView img_location, img_qty;
        Button accept;
        CardView cardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtPoints = itemView.findViewById(R.id.txt_points);
            tvOrderId = itemView.findViewById(R.id.order_id);
            txt_service2 = itemView.findViewById(R.id.txt_fault);
            textSubServices = itemView.findViewById(R.id.txt_sub_service);
            txt_bookingddate = itemView.findViewById(R.id.txt_bookingddate);
            txt_visittime = itemView.findViewById(R.id.txt_visittime);
            txt_service = itemView.findViewById(R.id.txt_service);
            img_location = itemView.findViewById(R.id.img_location);
            img_qty = itemView.findViewById(R.id.img_qty);
            accept = itemView.findViewById(R.id.accept);
            txtPrice = itemView.findViewById(R.id.txt_price);
            cardView = itemView.findViewById(R.id.card_leads);
            quantity = itemView.findViewById(R.id.txt_quantity);
            tvCharges = itemView.findViewById(R.id.txt_charges);
        }
    }


}