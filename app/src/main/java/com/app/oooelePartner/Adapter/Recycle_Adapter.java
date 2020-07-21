package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app.oooelePartner.R;
import com.app.oooelePartner.model.newmodel;

import java.util.List;

public class Recycle_Adapter extends RecyclerView.Adapter<Recycle_Adapter.ViewHolder> {

    private List<newmodel> mData;
    private Context context;

    private OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener=listener;
    }

    public Recycle_Adapter(Context context, List<newmodel> mData)
    {
    this.context=context;
    this.mData=mData;

    //options = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.newmodel,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
       /* final item list=caty_item[position];*/
        holder.name.setText(mData.get(position).getName());
        holder.skill.setText(mData.get(position).getSkill());
        holder.day.setText(mData.get(position).getDay());
        holder.quotes.setText(mData.get(position).getQuotes());
        holder.credit.setText(mData.get(position).getCredit());

        //Glide.with(context).load(mData.get(position).getCaty_img2()).apply(options).into(holder.img2);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name,skill,day,quotes,credit;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            skill=(TextView)itemView.findViewById(R.id.skill);
            day=(TextView)itemView.findViewById(R.id.day);
            quotes=itemView.findViewById(R.id.quotes);
            credit=itemView.findViewById(R.id.credit);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mlistener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mlistener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }
}
