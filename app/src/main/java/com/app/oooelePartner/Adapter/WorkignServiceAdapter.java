package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseSuperService;

import java.util.ArrayList;

public class WorkignServiceAdapter extends RecyclerView.Adapter<WorkignServiceAdapter.ViewHolder> {
    public Context context;
    ArrayList<ResponseSuperService> responseCartCategoryBeans;

    public WorkignServiceAdapter(Context context, ArrayList<ResponseSuperService> responseCartCategoryBeans) {
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
    public WorkignServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_workingservice, viewGroup, false);
        return new WorkignServiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WorkignServiceAdapter.ViewHolder holder, final int position) {
        ResponseSuperService modal = responseCartCategoryBeans.get(position);
        String str_dishname = modal.getService();
        holder.cateogyname.setText(str_dishname);
        if (modal.getFaults().size() > 0) {
            WorkingServiceAdapterSecond adapterSingle =
                    new WorkingServiceAdapterSecond(context, modal.getFaults());
            holder.recycleHome.setLayoutManager(new
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            holder.recycleHome.setAdapter(adapterSingle);
        }
        holder.cateogyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView cateogyname;
        protected RecyclerView recycleHome;

        public ViewHolder(View view) {
            super(view);
            cateogyname = view.findViewById(R.id.cateogyname);
            recycleHome = view.findViewById(R.id.recycleHome);
        }
    }
}

