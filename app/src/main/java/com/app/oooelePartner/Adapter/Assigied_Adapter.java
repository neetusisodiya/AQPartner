package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.R;
import com.app.oooelePartner.model.assmodel;

import java.util.List;

public class Assigied_Adapter extends RecyclerView.Adapter<Assigied_Adapter.ViewHolder> {

    private List<assmodel> Data;
    private Context context;

    private OnItemClickListener mlistener;

    public Assigied_Adapter(Context context, List<assmodel> Data) {
        this.context = context;
        this.Data = Data;

        //options = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.assimodel, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        /* final item list=caty_item[position];*/
        holder.assname.setText(Data.get(position).getAssname());
        holder.assTime.setText(Data.get(position).getAssTime());
        holder.assPlace.setText(Data.get(position).getAssPlace());
        holder.assPrice.setText(Data.get(position).getAssPrice());
        holder.assPartner.setText(Data.get(position).getAssPartner());

        //Glide.with(context).load(mData.get(position).getCaty_img2()).apply(options).into(holder.img2);

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView assname, assTime, assPlace, assPrice, assPartner;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            assname = itemView.findViewById(R.id.assiname);
            assTime = itemView.findViewById(R.id.assTime);
            assPlace = itemView.findViewById(R.id.assPlace);
            assPrice = itemView.findViewById(R.id.assPrice);
            assPartner = itemView.findViewById(R.id.assPartner);

            assname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }
}
