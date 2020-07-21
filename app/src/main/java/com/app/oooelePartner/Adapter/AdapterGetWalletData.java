package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.BeanGetWalletData;
 import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;


import java.util.List;

public class AdapterGetWalletData extends RecyclerView.Adapter<AdapterGetWalletData.ViewHolder> {
    Context context;
    String User_Id;
    List<BeanGetWalletData> banVisits;

    public AdapterGetWalletData(Context context, List<BeanGetWalletData> banVisits) {
        this.context = context;
        this.banVisits = banVisits;

    }

    @Override
    public AdapterGetWalletData.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_all_wallet, viewGroup, false);
        return new AdapterGetWalletData.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterGetWalletData.ViewHolder holder, final int position) {

        holder.text_amount.setText("Amount: "+banVisits.get(position).getAmount());
        holder.text_detail.setText("Details: "+banVisits.get(position).getDetail());
        holder.date.setText("Date: "+banVisits.get(position).getCreated());

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
        TextView text_detail, text_amount,date;
        Button btn_complete;

        public ViewHolder(final View itemView) {
            super(itemView);
            text_amount = itemView.findViewById(R.id.text_amount);
            text_detail = itemView.findViewById(R.id.text_detail);
date=itemView.findViewById(R.id.text_date);

            User_Id = String.valueOf(AppPreferences.getSavedUser(context).getId());

            //shortage.setVisibility(View.GONE);
        }
    }
}
