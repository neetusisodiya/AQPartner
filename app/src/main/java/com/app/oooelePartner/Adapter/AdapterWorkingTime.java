package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseTimeSelect;

import java.util.ArrayList;

public class AdapterWorkingTime extends RecyclerView.Adapter<AdapterWorkingTime.ViewHolder> {
    public Context context;
    ArrayList<ResponseTimeSelect> responseCartCategoryBeans;

    public AdapterWorkingTime(Context context, ArrayList<ResponseTimeSelect> responseCartCategoryBeans) {
        this.context = context;
        this.responseCartCategoryBeans = responseCartCategoryBeans;
    }

    @Override
    public int getItemCount() {
        return responseCartCategoryBeans.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public AdapterWorkingTime.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_working_time, viewGroup, false);
        return new AdapterWorkingTime.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterWorkingTime.ViewHolder holder, final int position) {
        ResponseTimeSelect modal = responseCartCategoryBeans.get(position);
        String str_dishname = modal.getDay();
        holder.txt_day.setText(str_dishname);
        if (modal.getTime().size() > 0) {
            AdapterWorkingTimeSecond adapterSingle = new
                    AdapterWorkingTimeSecond(context, modal.getTime(), modal.getDay());

            holder.recycleTime.setLayoutManager(new
                    StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

            // holder.recycleTime.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            holder.recycleTime.setAdapter(adapterSingle);
            //     adapterSingle.notifyDataSetChanged();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView txt_day;
        protected RecyclerView recycleTime;

        public ViewHolder(View view) {
            super(view);
            txt_day = view.findViewById(R.id.txt_day);
            recycleTime = view.findViewById(R.id.recycleTime);
        }
    }


}

