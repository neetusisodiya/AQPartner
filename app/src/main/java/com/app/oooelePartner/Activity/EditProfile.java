package com.app.oooelePartner.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.oooelePartner.Bean.CityBean;
import com.app.oooelePartner.Bean.ExpertBean;
import com.app.oooelePartner.Bean.GetProfileBean;
import com.app.oooelePartner.Bean.GetVendorProfileBean;
import com.app.oooelePartner.Bean.LoginBean;
import com.app.oooelePartner.Bean.QualificationBean;
import com.app.oooelePartner.Prefrence.AppPreferences;
import com.app.oooelePartner.R;
import com.app.oooelePartner.Response.ResponseExpertType;
import com.app.oooelePartner.Response.ResponseLogin;
import com.app.oooelePartner.Response.ResponseProfileUpload;
import com.app.oooelePartner.Response.ResponseQualification;
import com.app.oooelePartner.Response.ResponsegetCity;
import com.app.oooelePartner.Rest.ApiClient;
import com.app.oooelePartner.Rest.ApiInterface;
import com.app.oooelePartner.Utill.AppBaseActivity;
import com.app.oooelePartner.Utill.CommonUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.oooelePartner.Prefrence.AppPreferences.ADDRESS;
import static com.app.oooelePartner.Prefrence.AppPreferences.DOB;
import static com.app.oooelePartner.Prefrence.AppPreferences.EMAIL;
import static com.app.oooelePartner.Prefrence.AppPreferences.EXPERIENCE_MONTH;
import static com.app.oooelePartner.Prefrence.AppPreferences.EXPERIENCE_YEAR;
import static com.app.oooelePartner.Prefrence.AppPreferences.EXPERT_IN;
import static com.app.oooelePartner.Prefrence.AppPreferences.GENDER;
import static com.app.oooelePartner.Prefrence.AppPreferences.NAME;
import static com.app.oooelePartner.Prefrence.AppPreferences.PHONE_NUMBER;
import static com.app.oooelePartner.Prefrence.AppPreferences.PINCODE;
import static com.app.oooelePartner.Prefrence.AppPreferences.Qualifications;

public class EditProfile extends AppBaseActivity implements MaterialSpinner.OnItemSelectedListener, View.OnClickListener {
    ArrayAdapter Arrayspinner_state, Arrayspinner_qualificatin, Arrayspinner_experttype;
    ArrayList<GetVendorProfileBean> getVendorProfileBeans;
    final Calendar newCalendar = Calendar.getInstance();
    ArrayList<CityBean> cityBeanArrayList;
    ArrayList<ExpertBean> expertBeanArrayList;
    ArrayList<QualificationBean> qualificationBeanArrayList;
    String[] Yearexperance = {"1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10+"};
    String[] Gender = {"Male", "Female", "Other"};
    String[] Month = new String[]{
            "1", "2",
            "3", "4", "5", "6", "7", "8", "9",
            "10", "11", "12"
    };
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<GetProfileBean> getProfileBeanArrayList;

    String Streditfulllname, Streditmob, Streditemail, Streditpincontact, Streditaddress;
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayAdapter<String> spinnergenderAdapter;
    AVLoadingIndicatorView bar;
    String StrSpinCity_ID = "", qualification, StrSpineCity_Name = "", StrSpinQualification_ID = "",
            StrSpinQualification_name = "",
            StrSpinExpert_ID = "", StrSpineExpert_Name = "",
            StrYearexperance = "", StrGender = "", StrMonth = "", expertName;
    int genderPosition, expertTypePosition, qualificationPosition;
    ExpertBean expertBean;
    CityBean cityBean;
    QualificationBean qualificationBean;
    Button btn_submit;
    DateFormat dateFormat;
    TextView txt_dob;
    ImageView img_back;
    EditText editfulllname, editmob, editemail, editpincontact, editaddress;
    MaterialSpinner spinner_gender, spinner_city, spinner_year, spinner_month, spinner_experttype, spinner_qualification;
    int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    int dayOfMonth = 1, month, year = 1959;
    String userId, STRtv_service_DAte = "";
    ImageView img_edit;
    LinearLayout linctext, linc;
    TextView txt_gender, txt_city, Text_year, text_month, txt_experttype, txt_qualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        cityBeanArrayList = new ArrayList<>();
        qualificationBeanArrayList = new ArrayList<>();
        expertBeanArrayList = new ArrayList<>();
        find();

        getCityApi();
        getQualificationApi();
        getExpertTypeApi();
    }

    AppPreferences appPreferences;


    public void find() {
        txt_gender = findViewById(R.id.txt_gender);
        txt_city = findViewById(R.id.txt_city);
        Text_year = findViewById(R.id.Text_year);
        text_month = findViewById(R.id.text_month);
        txt_experttype = findViewById(R.id.txt_experttype);
        txt_qualification = findViewById(R.id.txt_qualification);
        img_edit = findViewById(R.id.img_edit);
        linctext = findViewById(R.id.linctext);
        linc = findViewById(R.id.linc);
        btn_submit = findViewById(R.id.btn_submit);
        bar = findViewById(R.id.bar);
        txt_dob = findViewById(R.id.txt_dob);
        img_back = findViewById(R.id.img_back);
        editfulllname = findViewById(R.id.editfulllname);
        editmob = findViewById(R.id.editmob);
        editemail = findViewById(R.id.editemail);
        editpincontact = findViewById(R.id.editpincontact);
        editaddress = findViewById(R.id.editaddress);
        spinner_gender = findViewById(R.id.spinner_gender);
        spinner_city = findViewById(R.id.spinner_city);
        spinner_year = findViewById(R.id.spinner_year);
        spinner_month = findViewById(R.id.spinner_month);
        spinner_experttype = findViewById(R.id.spinner_experttype);
        spinner_qualification = findViewById(R.id.spinner_qualification);
        btn_submit.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        img_back.setOnClickListener(this);
        spinner_gender.setOnItemSelectedListener(this);
        spinner_city.setOnItemSelectedListener(this);
        spinner_year.setOnItemSelectedListener(this);
        spinner_month.setOnItemSelectedListener(this);
        spinner_experttype.setOnItemSelectedListener(this);
        spinner_qualification.setOnItemSelectedListener(this);
        txt_dob.setOnClickListener(this);
        appPreferences = new AppPreferences(this);
        userId = appPreferences.getUserData(AppPreferences.KEY_ID);
        editmob.setText(appPreferences.getUserData(PHONE_NUMBER));
        if (appPreferences.checkForValue(AppPreferences.EMAIL)) {
            editemail.setText(appPreferences.getUserData(EMAIL));
        }
        if (appPreferences.checkForValue(AppPreferences.NAME)) {
            editfulllname.setText(appPreferences.getUserData(AppPreferences.NAME));
        }

        if (appPreferences.checkForValue(DOB)) {
            txt_dob.setText(appPreferences.getUserData(AppPreferences.DOB));
        }
        if (appPreferences.checkForValue(ADDRESS)) {
            editaddress.setText(appPreferences.getUserData(AppPreferences.ADDRESS));
        }
        if (appPreferences.checkForValue(PINCODE)) {
            editpincontact.setText(appPreferences.getUserData(AppPreferences.PINCODE));
        }
        spinnerArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Month);
        spinner_month.setAdapter(spinnerArrayAdapter);
        spinnergenderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Gender);
        spinner_gender.setAdapter(spinnergenderAdapter);
        ArrayAdapter adapteryear = new
                ArrayAdapter(this, android.R.layout.simple_spinner_item, Yearexperance);
        spinner_year.setAdapter(adapteryear);
        if (appPreferences.checkForValue(EXPERIENCE_YEAR)) {
            spinner_year.setSelectedIndex(Integer.parseInt(
                    appPreferences.getUserData(AppPreferences.EXPERIENCE_YEAR)));
        }
        if (appPreferences.checkForValue(GENDER)) {
            spinner_gender.setSelectedIndex(Integer.parseInt(
                    appPreferences.getUserData(AppPreferences.GENDER)));
        }
        if (appPreferences.checkForValue(EXPERIENCE_MONTH)) {
            spinner_month.setSelectedIndex(Integer.parseInt(
                    appPreferences.getUserData(AppPreferences.EXPERIENCE_MONTH)));
        }
    }


    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        if (view == spinner_city) {
            StrSpinCity_ID = "" + cityBeanArrayList.get(position).getId();
            StrSpineCity_Name = "" + cityBeanArrayList.get(position).getCity();
        }
        if (view == spinner_qualification) {
            StrSpinQualification_ID = "" + qualificationBeanArrayList.get(position).getId();
            qualification = qualificationBeanArrayList.get(position).getName();
            StrSpinQualification_name = "" + qualificationBeanArrayList.get(position).getName();
            qualificationPosition = position;
        }
        if (view == spinner_experttype) {
            StrSpinExpert_ID = "" + expertBeanArrayList.get(position).getId();
            StrSpineExpert_Name = "" + expertBeanArrayList.get(position).getValue();
            expertName = expertBeanArrayList.get(position).getValue();
            expertTypePosition = position;

        }
        if (view == spinner_gender) {
            StrGender = "" + Gender[position];
            genderPosition = position;
            //Snackbar.make(view, "Clicked " + position, Snackbar.LENGTH_LONG).show();

        }
        if (view == spinner_year) {
            StrYearexperance = "" + Yearexperance[position];
            //  Snackbar.make(view, "Clicked " + position, Snackbar.LENGTH_LONG).show();

        }
        if (view == spinner_month) {
            StrMonth = "" + Month[position];
            //    Snackbar.make(view, "Clicked " + position, Snackbar.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View v) {
        if (v == txt_dob) {


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            //  STRtv_service_DAte = dayOfMonth + "-" +(monthOfYear + 1) + "-" + year;
                            STRtv_service_DAte = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            txt_dob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();

       /*     DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    STRtv_service_DAte = dayOfMonth + "/" +(month + 1) + "/" + year;

                    Log.d("StrRegistration_DATE", STRtv_service_DAte);


                    //   editjoingdate.setText(new StringBuilder().append(dayOfMonth).append("/").append(month).append("/").append(year));
                    txt_dob.setText(STRtv_service_DAte);
                    Log.d("edit_todate", txt_dob.toString());
                }
            }, year, month, dayOfMonth);
            //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() -1000);
            datePickerDialog.show();*/
        }
        if (v == btn_submit) {
            Streditfulllname = editfulllname.getText().toString();
            Streditemail = editemail.getText().toString();
            Streditpincontact = editpincontact.getText().toString();
            Streditaddress = editaddress.getText().toString();
           /* if (Streditfulllname.equals("") || Streditfulllname.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.blank_name));
            }else*/
            if (Streditemail.equals("") || Streditemail.isEmpty()) {
                CommonUtils.showToast(mContext, getString(R.string.blank_email));
            } else if (!Streditemail.matches(emailPattern)) {
                CommonUtils.showToast(mContext, getString(R.string.invalid_email));

            } else {
                EditUpdate();
            }
        }
        if (v == img_back) {
            onBackPressed();
        }
 /*       if (v == img_edit) {
            editfulllname.setEnabled(true);
            txt_dob.setEnabled(true);
            editmob.setEnabled(true);
            editemail.setEnabled(true);
            editpincontact.setEnabled(true);
            editaddress.setEnabled(true);
            txt_experttype.setVisibility(View.GONE);
            linctext.setVisibility(View.GONE);
            txt_qualification.setVisibility(View.GONE);
            linc.setVisibility(View.VISIBLE);
            txt_city.setVisibility(View.GONE);
            txt_gender.setVisibility(View.GONE);
            btn_submit.setVisibility(View.VISIBLE);
            spinner_gender.setVisibility(View.VISIBLE);
            spinner_city.setVisibility(View.VISIBLE);
            spinner_experttype.setVisibility(View.VISIBLE);
            spinner_qualification.setVisibility(View.VISIBLE);
        }*/
    }


    private void getCityApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {},
                new String[]{});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<ResponsegetCity> call = apiInterface.ApiGetCity(builder.build());
            call.enqueue(new Callback<ResponsegetCity>() {
                @Override
                public void onResponse(Call<ResponsegetCity> call, Response<ResponsegetCity> response) {
                    // avi.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        cityBeanArrayList.clear();
                        try {
                            ArrayList<String> stateNameList = new ArrayList<>();


                            if (response.isSuccessful() && response.body() != null) {


                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    cityBean = new CityBean();
                                    cityBean.setCity(response.body().getData().get(i).getCity());
                                    cityBean.setId(response.body().getData().get(i).getId());
                                    stateNameList.add(String.valueOf(response.body().getData().get(i).getCity()));
                                    cityBeanArrayList.add(cityBean);
                                    Log.e("statelistsize", String.valueOf(stateNameList.size()));
                                }
                                Arrayspinner_state = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, stateNameList);
                                Arrayspinner_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_city.setAdapter(Arrayspinner_state);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponsegetCity> call, Throwable t) {
                    //onApiFailure(call, t);
                    //avi.setVisibility(View.GONE);
                    //Toast.makeText(SignUpActivity.this, "Get states,Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }

    }

    private void EditUpdate() {
        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"name", "gender", "dob",
                        "email", "expert_type_id",
                        "qualification_id", "cityid", "pincode",
                        "address", "exp_year", "exp_month", "id"},
                new String[]{Streditfulllname, StrGender,
                        txt_dob.getText().toString(), Streditemail,
                        StrSpinExpert_ID, StrSpinQualification_ID,
                        StrSpinCity_ID, Streditpincontact,
                        Streditaddress, StrYearexperance, StrMonth, userId});
        Call<ResponseProfileUpload> call = apiInterface.ApiProfileUpload(builder.build());
        bar.setVisibility(View.GONE);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (response.isSuccessful()) {
                    ResponseProfileUpload resObj = (ResponseProfileUpload) response.body();
                    if (resObj.getMessage().equals("Successfully Profile Updated")) {
                        DynamicToast.makeSuccess(getApplicationContext(), "Successfully Profile Updated").show();
                        //getNewLead(userId);
                        AppPreferences appPreferences = new AppPreferences(EditProfile.this);
                        appPreferences.setUserData(NAME, Streditfulllname);
                        if (!StrGender.equals("")) {
                            appPreferences.setUserData(GENDER, String.valueOf(genderPosition));

                        }
                        if (!StrYearexperance.equals("")) {
                            appPreferences.setUserData(EXPERIENCE_YEAR, String.valueOf(Integer.parseInt(StrYearexperance)-1));
                        }
                        if (!StrMonth.equals("")) {
                            appPreferences.setUserData(EXPERIENCE_MONTH, String.valueOf(Integer.parseInt(StrMonth)-1));
                        }
                        appPreferences.setUserData(EMAIL, Streditemail);
                        appPreferences.setUserData(DOB, txt_dob.getText().toString());
                        appPreferences.setUserData(ADDRESS, Streditaddress);
                        appPreferences.setUserData(PINCODE, Streditpincontact);
                        if (!StrSpinQualification_ID.equals("")) {
                            appPreferences.setUserData(Qualifications, String.valueOf(qualificationPosition));
                        }
                        if (!StrSpinExpert_ID.equals("")) {
                            appPreferences.setUserData(EXPERT_IN, String.valueOf(expertTypePosition));
                        }

                    } else {
                        bar.setVisibility(View.GONE);
                        DynamicToast.makeError(getApplicationContext(), "Date Is Not Uploaded").show();
                    }
                } else {
                    bar.setVisibility(View.GONE);
                    DynamicToast.makeError(getApplicationContext(), "Error! Please try again! ").show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNewLead(String str_Owner_Id) {

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"id"}, new
                String[]{str_Owner_Id});
        if (CommonUtils.isNetworkAvailable(EditProfile.this)) {
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
                                AppPreferences.saveInPref(EditProfile.this, loginBean);
                                AppPreferences appPreferences = new AppPreferences(EditProfile.this);

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
            Toast.makeText(EditProfile.this, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();

        }
    }

    private void getExpertTypeApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {},
                new String[]{});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<ResponseExpertType> call = apiInterface.ApiGetExpertType(builder.build());
            call.enqueue(new Callback<ResponseExpertType>() {
                @Override
                public void onResponse(Call<ResponseExpertType> call, Response<ResponseExpertType> response) {
                    if (response.isSuccessful()) {
                        expertBeanArrayList.clear();
                        try {
                            ArrayList<String> expertNameList = new ArrayList<>();


                            if (response.isSuccessful() && response.body() != null) {


                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    expertBean = new ExpertBean();
                                    expertBean.setValue(response.body().getData().get(i).getValue());
                                    expertBean.setId(response.body().getData().get(i).getId());
                                    expertNameList.add(String.valueOf(response.body().getData().get(i).getValue()));
                                    expertBeanArrayList.add(expertBean);
                                    Log.e("expertBeanArrayList", String.valueOf(expertBeanArrayList.size()));
                                }
                                Arrayspinner_experttype = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, expertNameList);
                                Arrayspinner_experttype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_experttype.setAdapter(Arrayspinner_experttype);
                                if (appPreferences.checkForValue(EXPERT_IN)) {
                                    spinner_experttype.setSelectedIndex(Integer.parseInt(
                                            appPreferences.getUserData(AppPreferences.EXPERT_IN)));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseExpertType> call, Throwable t) {
                    //onApiFailure(call, t);
                    //avi.setVisibility(View.GONE);
                    //Toast.makeText(SignUpActivity.this, "Get states,Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }

    }

    private void getQualificationApi() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]
                        {},
                new String[]{});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<ResponseQualification> call = apiInterface.ApiGetQualification(builder.build());
            call.enqueue(new Callback<ResponseQualification>() {
                @Override
                public void onResponse(Call<ResponseQualification> call, Response<ResponseQualification> response) {
                    // avi.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        qualificationBeanArrayList.clear();
                        try {
                            ArrayList<String> qualiNameList = new ArrayList<>();


                            if (response.isSuccessful() && response.body() != null) {


                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    qualificationBean = new QualificationBean();
                                    qualificationBean.setName(response.body().getData().get(i).getName());
                                    qualificationBean.setId(response.body().getData().get(i).getId());
                                    qualiNameList.add(String.valueOf(response.body().getData().get(i).getName()));
                                    qualificationBeanArrayList.add(qualificationBean);
                                    Log.e("qualifBeanArrayList", String.valueOf(qualificationBeanArrayList.size()));
                                }
                                Arrayspinner_qualificatin = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, qualiNameList);
                                Arrayspinner_qualificatin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner_qualification.setAdapter(Arrayspinner_qualificatin);
                                if (appPreferences.checkForValue(Qualifications)) {
                                    spinner_qualification.setSelectedIndex(Integer.parseInt(
                                            appPreferences.getUserData(AppPreferences.Qualifications)));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseQualification> call, Throwable t) {
                    //onApiFailure(call, t);
                    //avi.setVisibility(View.GONE);
                    //Toast.makeText(SignUpActivity.this, "Get states,Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }

    public void singleMain(String str_Owner_Id) {
        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{"id"},
                new String[]{str_Owner_Id});
        Call<ResponseBody> call = apiInterface.ApiProfileGet(builder.build());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Log.d("ddd", response.body().string());
                bar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    try {
                        String resturentMenu = response.body().string();
                        JSONObject jsonObject = new JSONObject(resturentMenu);
                        String status = jsonObject.optString("status");
                        if (status.equalsIgnoreCase(String.valueOf(true))) {
                            getProfileBeanArrayList = new ArrayList<>();
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                GetProfileBean getProfileBean = new GetProfileBean();
                                getProfileBean.setId(Integer.parseInt(jsonObject1.getString("id")));
                                editfulllname.setText(jsonObject1.getString("name"));
                                txt_gender.setText(jsonObject1.getString("gender"));
                                txt_dob.setText(jsonObject1.getString("dob"));
                                editmob.setText(jsonObject1.getString("mobno"));
                                editemail.setText(jsonObject1.getString("email"));
                                txt_city.setText(jsonObject1.getString("cityid"));
                                editpincontact.setText(jsonObject1.getString("pincode"));
                                Text_year.setText(jsonObject1.getString("exp_year"));
                                text_month.setText(jsonObject1.getString("exp_month"));
                                editaddress.setText(jsonObject1.getString("address"));
                                txt_experttype.setText(jsonObject1.getString("expert_type_id"));
                                txt_qualification.setText(jsonObject1.getString("qualification_id"));
                            }
                        } else {
                        }
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onApiFailure(call, t);
                bar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onApiFailure(Call<ResponseBody> call, Throwable t) {
        //Log.e("error", t.toString());
        //avi.setVisibility(View.GONE);
        bar.setVisibility(View.GONE);
    }
}
