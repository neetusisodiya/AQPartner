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

public class AdapterExpenses extends RecyclerView.Adapter<AdapterExpenses.ViewHolder> {
    Context context;
    String User_Id;
    List<BeanGetWalletData> banVisits;

    public AdapterExpenses(Context context, List<BeanGetWalletData> banVisits) {
        this.context = context;
        this.banVisits = banVisits;
        Log.e("InsideTheAdaptersingle", "InsideTheAdapter" + banVisits.size());

    }

    @Override
    public AdapterExpenses.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_all_wallet, viewGroup, false);
        return new AdapterExpenses.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterExpenses.ViewHolder holder, final int position) {
        int str_Amount = Integer.parseInt(banVisits.get(position).getAmount());
        holder.text_amount.setText("Amount  :" + banVisits.get(position).getAmount());
        holder.text_detail.setText("Details  :" + banVisits.get(position).getDetail());
        /*if (str_Amount <=0) {
            holder.text_amount.setText("Amount  :" + banVisits.get(position).getAmount());
            holder.text_detail.setText("Details  :" + banVisits.get(position).getDetail());
        }*/
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
        TextView text_detail, text_amount;
        Button btn_complete;

        public ViewHolder(final View itemView) {
            super(itemView);
            text_amount = itemView.findViewById(R.id.text_amount);
            text_detail = itemView.findViewById(R.id.text_detail);


            User_Id = String.valueOf(AppPreferences.getSavedUser(context).getId());

            //shortage.setVisibility(View.GONE);
        }
    }
}
