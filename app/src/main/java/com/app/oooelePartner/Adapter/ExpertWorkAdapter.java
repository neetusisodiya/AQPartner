package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseSuperService;

import java.util.ArrayList;

public class ExpertWorkAdapter extends RecyclerView.Adapter<ExpertWorkAdapter.ViewHolder> {
    public Context context;
    ArrayList<ResponseSuperService> responseCartCategoryBeans;

    public ExpertWorkAdapter(Context context, ArrayList<ResponseSuperService> responseCartCategoryBeans) {
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


    @NonNull
    @Override
    public ExpertWorkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_workingservice, viewGroup, false);
        return new ExpertWorkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpertWorkAdapter.ViewHolder holder, final int position) {
        ResponseSuperService modal = responseCartCategoryBeans.get(position);
        String str_dishname = modal.getService();
        holder.cateogyname.setText(str_dishname);


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

