package com.app.oooelePartner.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.oooelePartner.Bean.BeanWorkingRadius;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseProfileUpload;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AdapterGetWorkingArea extends RecyclerView.Adapter<AdapterGetWorkingArea.ViewHolder> {
    Context context;
    Double StrLat = Math.floor(2.7);
    Double StrLng = Math.floor(2.7);
    List<BeanWorkingRadius> banVisits;

    public AdapterGetWorkingArea(Context context, List<BeanWorkingRadius> banVisits) {
        this.context = context;
        this.banVisits = banVisits;
        Log.e("InsideTheAdaptersingle", "InsideTheAdapter" + banVisits.size());

    }

    @Override
    public AdapterGetWorkingArea.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_get_working_area, viewGroup, false);
        return new AdapterGetWorkingArea.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterGetWorkingArea.ViewHolder holder, final int position) {
        Log.e("InsideTheAdaptersing12", "InsideTheAdapter");
        String StrId = String.valueOf(banVisits.get(position).getId());
/*        StrLng= Double.valueOf(banVisits.get(position).getLat());
        StrLng= Double.valueOf(banVisits.get(position).getLng());
        String StrRadius= String.valueOf(banVisits.get(position).getWorking_radius());*/
        //  holder.txt_id.setText(StrId);
        holder.txt_address.setText("Address  : " + banVisits.get(position).getAddress());
        holder.txt_lat.setText    ("Latitude : " + banVisits.get(position).getLat());
        Log.d("iodios", "" + StrLng);

        holder.txt_lng.setText("Logitude : " + banVisits.get(position).getLng());
        holder.txt_radius.setText ("Radius   : " + banVisits.get(position).getWorking_radius()+"KM");

        holder.img_delte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiSetWorkignArea(position);

            }
        });

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

    private void ApiSetWorkignArea(final int position) {
        // avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"id"},
                new String[]{String.valueOf(banVisits.get(position).getId())});
        Call<ResponseProfileUpload> call = apiInterface.ApiPartnerRemoveWorkignArea(builder.build());
        // avi.setVisibility(View.GONE);


        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponseProfileUpload resObj = (ResponseProfileUpload) response.body();
                    if (resObj.getMessage().equals("Successfully Removed Working Area")) {
                        banVisits.remove(position);
                        notifyDataSetChanged();
                        DynamicToast.makeSuccess(context, "Successfully Remove Working Area").show();
                    } else {
                        //    avi.setVisibility(View.GONE);
                        DynamicToast.makeError(context, "Date Is Not Uploaded").show();
                        // Toast.makeText(LoginNewActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //   avi.setVisibility(View.GONE);
                    DynamicToast.makeError(context, "Error! Please try again! ").show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id, txt_lat, txt_lng, txt_radius, txt_address;
        ImageView img_delte;
        public ViewHolder(final View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_lat = itemView.findViewById(R.id.txt_lat);
            txt_lng = itemView.findViewById(R.id.txt_lng);
            txt_radius = itemView.findViewById(R.id.txt_radius);
            txt_address = itemView.findViewById(R.id.txt_address);
            img_delte = itemView.findViewById(R.id.img_delte);
            //shortage.setVisibility(View.GONE);
        }
    }


}