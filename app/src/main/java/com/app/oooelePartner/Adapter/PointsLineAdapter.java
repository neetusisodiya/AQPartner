/*
package com.app.oooelePartner.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.PointsData;
import com.app.oooelePartner.R;

import java.util.List;

public class PointsLineAdapter extends RecyclerView.Adapter<PointsLineAdapter.ViewHolder> {
    Activity context;
    List<PointsData> banVisits;

    public PointsLineAdapter(Activity context, List<PointsData> banVisits) {
        this.context = context;
        this.banVisits = banVisits;

    }

    @Override
    public PointsLineAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_point_line, viewGroup, false);
        return new PointsLineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PointsLineAdapter.ViewHolder holder, final int position) {
        holder.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        ad
        holder.recyclerView.setAdapter();

    }


    @Override
    public int getItemCount() {
        return banVisits.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public ViewHolder(final View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.line_recyler_view);
        }
    }

}
*/
