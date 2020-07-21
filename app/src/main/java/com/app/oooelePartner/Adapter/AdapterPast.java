package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.BeanCompleteLead;
import com.app.oooelePartner.R;

import java.util.List;

public class AdapterPast extends RecyclerView.Adapter<AdapterPast.ViewHolder> {
    Context context;

    List<BeanCompleteLead> banVisits;

    public AdapterPast(Context context, List<BeanCompleteLead> banVisits) {
        this.context = context;
        this.banVisits = banVisits;
        Log.e("InsideTheAdaptersingle", "InsideTheAdapter" + banVisits.size());

    }

    @Override
    public AdapterPast.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_open_leads, viewGroup, false);
        return new AdapterPast.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterPast.ViewHolder holder, final int position) {
        Log.e("InsideTheAdaptersing12", "InsideTheAdapter");

        Log.d("iodios",""+banVisits.get(position).getServ());
        holder.txt_serv.setText(banVisits.get(position).getServ());
        holder.txt_booking_date.setText(banVisits.get(position).getBooking_date());
        holder.txt_address.setText(banVisits.get(position).getG_address());
        holder.txt_contactnum.setText(banVisits.get(position).getContact());
        //  holder.txt_qtynum.setText(banVisits.get(position).getQty());
        holder.txt_visitDate.setText(banVisits.get(position).getVisit_time());

holder.buttonCallUser.setVisibility(View.GONE);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_serv, txt_booking_date, txt_address, txt_contactnum, txt_qtynum, txt_visitDate ;
        Button btn_complete,buttonCallUser;
        public ViewHolder(final View itemView) {
            super(itemView);
            txt_serv = itemView.findViewById(R.id.txt_serv);
            txt_booking_date = itemView.findViewById(R.id.txt_booking_date);
            txt_address = itemView.findViewById(R.id.txt_address);
            txt_contactnum = itemView.findViewById(R.id.txt_contactnum);
            txt_qtynum = itemView.findViewById(R.id.txt_qtynum);
            txt_visitDate = itemView.findViewById(R.id.txt_visitDate);
            btn_complete = itemView.findViewById(R.id.btn_complete);
            btn_complete.setVisibility(View.GONE);
            buttonCallUser=itemView.findViewById(R.id.call_user);

            //shortage.setVisibility(View.GONE);
        }
    }
}
