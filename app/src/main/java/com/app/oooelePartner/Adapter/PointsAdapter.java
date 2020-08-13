package com.app.oooelePartner.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.oooelePartner.Activity.PaymentActivity;
import com.app.oooelePartner.Bean.PointsData;
import com.app.oooelePartner.R;

import java.util.List;

public class PointsAdapter extends BaseAdapter {
    Activity context;
    List<PointsData> banVisits;
    LayoutInflater inflter;

    public PointsAdapter(Activity context, List<PointsData> banVisits) {
        this.context = context;
        this.banVisits = banVisits;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return banVisits.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        itemView = inflter.inflate(R.layout.adapter_points, null);
        String points;
        TextView text_amount,textPoints;
        text_amount = itemView.findViewById(R.id.rupee_txt);
        textPoints = itemView.findViewById(R.id.points_txt);
        points = banVisits.get(position).getPoint() + " Oooele Points";
        final String rupee = banVisits.get(position).getRupee();
        text_amount.setText("₹"+rupee);
        textPoints.setText(points);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra("TotalAmount", rupee);
                context.startActivity(intent);

            }
        });
        return  itemView;
    }



}

