package com.app.oooelePartner.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.app.oooelePartner.Activity.EditProfile;
import com.app.oooelePartner.Activity.LoginActivity;
import com.app.oooelePartner.Activity.MainActivity;
import com.app.oooelePartner.Activity.WorkActivity;
import com.app.oooelePartner.Activity.WorkingTimeActivity;
import com.app.oooelePartner.Bean.GetVendorProfileBean;
import com.app.oooelePartner.Bean.LoginBean;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.ProfileActivity.Award;
import com.app.oooelePartner.ProfileActivity.BankDetailsActivity;
import com.app.oooelePartner.ProfileActivity.IdentityActivity;
import com.app.oooelePartner.ProfileActivity.ServiceLocation;
import com.app.oooelePartner.ProfileActivity.Training;
import com.app.oooelePartner.ProfileActivity.UploadWork;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseLogin;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.CommonUtils;
import com.app.oooelePartner.WorkingArea.MapsActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.oooelePartner.Prefrence.AppPreferences.Account_Number;
import static com.app.oooelePartner.Prefrence.AppPreferences.DOB;
import static com.app.oooelePartner.Prefrence.AppPreferences.EMAIL;
import static com.app.oooelePartner.Prefrence.AppPreferences.NAME;
import static com.app.oooelePartner.Prefrence.AppPreferences.Name_In_Bank;
import static com.app.oooelePartner.Prefrence.AppPreferences.PAN_CARD;
import static com.app.oooelePartner.Prefrence.AppPreferences.bankIfscCode;
import static maes.tech.intentanim.CustomIntent.customType;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    AppPreferences appPreferences;
    ImageView img_edit;
    CircleImageView img_profile;
    TextView txt_name, txt_email, txt_logout, txt_num, txt_identity, txt_traning, txt_bankdetail, txt_upload_work, txt_upload_certi, service_location;
    View view;
    LinearLayout lin_workingarea, lin_workingslot, lin_workingstime;
    ArrayList<GetVendorProfileBean> getVendorProfileBeans;
    String ownerId;
    MainActivity mainActivity;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        mainActivity = (MainActivity) getActivity();
        find();


        return view;
    }

    public void find() {
        lin_workingstime = view.findViewById(R.id.lin_workingstime);
        service_location = view.findViewById(R.id.service_location);
        lin_workingslot = view.findViewById(R.id.lin_workingslot);
        img_edit = view.findViewById(R.id.img_edit);
        img_profile = view.findViewById(R.id.img_profile);
        txt_name = view.findViewById(R.id.txt_name);
        txt_email = view.findViewById(R.id.txt_email);
        txt_num = view.findViewById(R.id.txt_num);
        lin_workingarea = view.findViewById(R.id.lin_workingarea);
        txt_identity = view.findViewById(R.id.txt_identity);
        txt_traning = view.findViewById(R.id.txt_traning);
        txt_logout = view.findViewById(R.id.txt_logout);
        txt_bankdetail = view.findViewById(R.id.txt_bankdetail);
        txt_upload_work = view.findViewById(R.id.txt_upload_work);
        txt_upload_certi = view.findViewById(R.id.txt_upload_certi);
        txt_identity.setOnClickListener(this);
        txt_traning.setOnClickListener(this);
        txt_bankdetail.setOnClickListener(this);
        txt_upload_work.setOnClickListener(this);
        txt_upload_certi.setOnClickListener(this);
        txt_logout.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        lin_workingarea.setOnClickListener(this);
        lin_workingstime.setOnClickListener(this);
        lin_workingslot.setOnClickListener(this);
        txt_num.setOnClickListener(this);
        service_location.setOnClickListener(this);
        appPreferences = new AppPreferences(getContext());
        ownerId = appPreferences.getUserData(AppPreferences.KEY_ID);
        txt_num.setText(appPreferences.getUserData(AppPreferences.PHONE_NUMBER));


    }

    @Override
    public void onClick(View v) {
        if (v == txt_identity) {
            startActivity(new Intent(getContext(), IdentityActivity.class));
            customType(getContext(), "left-to-right");
        }
        if (v == txt_traning) {
            startActivity(new Intent(getContext(), Training.class));
            customType(getContext(), "left-to-right");
        }
        if (v == txt_bankdetail) {
            startActivity(new Intent(getContext(), BankDetailsActivity.class));
            customType(getContext(), "left-to-right");
        }
        if (v == txt_upload_work) {
            startActivity(new Intent(getContext(), UploadWork.class));
            customType(getContext(), "left-to-right");
        }
        if (v == txt_upload_certi) {
            startActivity(new Intent(getContext(), Award.class));
            customType(getContext(), "left-to-right");
        }
        if (v == img_edit) {
            startActivity(new Intent(getContext(), EditProfile.class));
            customType(getContext(), "left-to-right");
        }
        if (v == lin_workingarea) {
            startActivity(new Intent(getContext(), MapsActivity.class));
        }
        if (v == lin_workingslot) {
            startActivity(new Intent(getContext(), WorkActivity.class));
            customType(getContext(), "left-to-right");
        }
        if (v == lin_workingstime) {
            startActivity(new Intent(getContext(), WorkingTimeActivity.class));
            customType(getContext(), "left-to-right");
        }
        if (v == service_location) {
            startActivity(new Intent(getContext(), ServiceLocation.class));
            customType(getContext(), "left-to-right");
        }
        if (v == txt_logout) {
            AppPreferences.clearPrefsdata(getActivity());
            startActivity(new Intent(getActivity(), LoginActivity.class).
                    setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            getActivity().finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
     /*        if (getVendorProfileBeans==null){
            txt_name.setText("Name");
            txt_num.setText("Mobile");
            txt_email.setText("Email");
        } if (getVendorProfileBeans == null) {
            txt_name.setText("Name");
           // txt_num.setText("Mobile");
            txt_email.setText("Email");
        }*/
        if (
                appPreferences.checkForValue(AppPreferences.EMAIL)) {
            txt_email.setText(appPreferences.getUserData(EMAIL));
        }
        if (
                appPreferences.checkForValue(AppPreferences.NAME)) {
            txt_name.setText(appPreferences.getUserData(AppPreferences.NAME));
        }
        txt_num.setText(appPreferences.getUserData(AppPreferences.PHONE_NUMBER));

        CommonUtils.tabChange(getActivity(), mainActivity.iv_cart, mainActivity.iv_account, mainActivity.iv_home, mainActivity.ivsearch, mainActivity.txtcart, mainActivity.txtaccount, mainActivity.txthome, mainActivity.txtsearch);
        //   getNewLead(ownerId);

    }

    private void getNewLead(String str_Owner_Id) {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"id"}, new
                String[]{str_Owner_Id});
        if (CommonUtils.isNetworkAvailable(getContext())) {
            Call<ResponseLogin> call = service.ApiProfileGet1(builder.build());
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                    try {

                        if (response.body() != null) {
                            ResponseLogin resObj = (ResponseLogin) response.body();
                            if (resObj.isStatus()) {
                                getVendorProfileBeans = new ArrayList<>();
                                LoginBean loginBean = ((ResponseLogin) response.body()).getData();
                                AppPreferences.saveInPref(getActivity(), loginBean);
                                AppPreferences appPreferences = new AppPreferences(getActivity());
                                appPreferences.setUserData(AppPreferences.KEY_ID, loginBean.getId());
                                appPreferences.setUserData(NAME, loginBean.getName());
                                appPreferences.setUserData(AppPreferences.PHONE_NUMBER, loginBean.getMobno());
                                appPreferences.setUserData(EMAIL, loginBean.getEmail());
                                appPreferences.setUserData(Name_In_Bank, loginBean.getBank_name());
                                appPreferences.setUserData(bankIfscCode, loginBean.getIfsc());
                                appPreferences.setUserData(Account_Number, loginBean.getAccount_no());
                                appPreferences.setUserData(DOB, loginBean.getDob());
                                appPreferences.setUserData(PAN_CARD, loginBean.getPancard_no());
                                /*for (int i = 0; i < response.body().getData().size(); i++) {
                                    GetVendorProfileBean getVendorProfileBean = new GetVendorProfileBean();
                                    if (!response.body().getData().get(0).getName().equalsIgnoreCase("null")) {
                                        txt_name.setText("Name");
                                    }
                                    if (response.body().getData().get(0).getEmail().equalsIgnoreCase("null")) {
                                        txt_email.setText("Email");

                                    } else {
                                        txt_name.setText(response.body().getData().get(0).getName());
                                        txt_email.setText(response.body().getData().get(0).getEmail());
                                    }
                                    getVendorProfileBean.setId(response.body().getData().get(0).getId());
                                    txt_num.setText(response.body().getData().get(0).getMobno());


                                }*/
                                // bar.setVisibility(View.GONE);
                                //   CommonUtils.hideProgressDoalog();

                                // relihidedata.setVisibility(View.GONE);
                                //   String Path = response.body().getPath();
                                //   Path2 = response.body().getPath2();

                            } else {
                                //       img_nodata.setVisibility(View.VISIBLE);
                                //    recordenotfound.setVisibility(View.VISIBLE);
                                //     bar.setVisibility(View.GONE);
                                //    CommonUtils.hideProgressDoalog();
                                //
                                //      bar.setVisibility(View.GONE);
                                //     relihidedata.setVisibility(View.VISIBLE);
                                //     btn_placeorder.setVisibility(View.GONE);
                            }
                        }


                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    // CommonUtils.hideProgressDoalog();

                    //bar.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(),"mobile Or Password Wrong..", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //  CommonUtils.hideProgressDoalog();

            //progressDialog.dismiss();
            //  scrolls.setVisibility(View.VISIBLE);
            // reli.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

}
