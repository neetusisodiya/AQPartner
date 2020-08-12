package com.app.oooelePartner.Rest;


import com.app.oooelePartner.Bean.PointsResponse;
import com.app.oooelePartner.Response.ResponSelectService;
import com.app.oooelePartner.Response.ResponseAccept;
import com.app.oooelePartner.Response.ResponseBank;
import com.app.oooelePartner.Response.ResponseExpertType;
import com.app.oooelePartner.Response.ResponseGetNewLeads;
import com.app.oooelePartner.Response.ResponseGetOpenLeads;
import com.app.oooelePartner.Response.ResponseGetWalletData;
import com.app.oooelePartner.Response.ResponseLogin;
import com.app.oooelePartner.Response.ResponsePayment;
import com.app.oooelePartner.Response.ResponseProfileUpload;
import com.app.oooelePartner.Response.ResponseQualification;
import com.app.oooelePartner.Response.ResponseWorkingRadiusList;
import com.app.oooelePartner.Response.ResponsegetCity;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {



            @GET("sendGroupSms?")
            Call<ResponseBody> getOtp(@Query("AUTH_KEY") String AUTH_KEY,
                                      @Query("message") String message,
                                      @Query("senderId") String senderId,
                                      @Query("routeId") String routeId,
                                      @Query("mobileNos") String mobileNos,
                                      @Query("smsContentType") String smsContentType);

    @POST("api/partner-login")
    Call<ResponseLogin> ApiLogin(@Body RequestBody requestBody);

    @POST("api-get-city")
    Call<ResponsegetCity> ApiGetCity(@Body RequestBody requestBody);

    @POST("api-get-qualification")
    Call<ResponseQualification> ApiGetQualification(@Body RequestBody requestBody);

    @POST("api-get-expert-type")
    Call<ResponseExpertType> ApiGetExpertType(@Body RequestBody requestBody);
    @POST("api/partner-set-profile-data?")
    Call<ResponseProfileUpload> ApiProfileUpload(@Body RequestBody requestBody);




    @POST("api/partner-get-completed-leads")
    Call<ResponseGetOpenLeads> ApiGetCompleteLead(@Body RequestBody requestBody);


    @POST("api/partner-get-profile-data?")
    Call<ResponseBody> ApiProfileGet(@Body RequestBody requestBody);

    @POST("api/partner-get-profile-data?")
    Call<ResponseLogin> ApiProfileGet1(@Body RequestBody requestBody);

    @POST("api/partner-complete-lead")
    Call<ResponseAccept> ApiPartnerCompleteLead(@Body RequestBody requestBody);

    @POST("api/partner-start-lead")
    Call<ResponseAccept> apiForStartWorkApi(@Body RequestBody requestBody);

    @POST("api/partner-accept-lead")
    Call<ResponseAccept> ApiPartnerAccept(@Body RequestBody requestBody);


    @POST("api/partner-working-services")
    Call<ResponseBody> ApiWorkingService(@Body RequestBody requestBody);


    @POST("api/partner-set-working-services?")
    Call<ResponSelectService>ApiUpdateWorkingService(@Body RequestBody requestBody);


    @POST("api/partner-remove-working-services?")
    Call<ResponSelectService>ApiRemoveWorkingService(@Body RequestBody requestBody);



    @POST("api/partner-working-time-slot")
    Call<ResponseBody>ApiWorkingTimeSlot(@Body RequestBody requestBody);

    @POST("api/partner-update-bank-details")
    Call<ResponseBank>ApiBankDetails(@Body RequestBody requestBody);

    @POST("api/partner-add-wallet-data")
    Call<ResponsePayment>ApiAddWallet(@Body RequestBody requestBody);



    @POST("api/partner-get-new-leads?")
    Call<ResponseGetNewLeads>ApiWorkingNewLeads(@Body RequestBody requestBody);


    @POST("api/partner-get-open-leads")
    Call<ResponseGetOpenLeads>ApiGetOpenLeads(@Body RequestBody requestBody);

    @POST("api/partner-get-wallet-data")
    Call<ResponseGetWalletData>ApiGetWalletData(@Body RequestBody requestBody);
    @GET("api-convertedpoints")
    Call<PointsResponse>getPoints();

    @POST("api/partner-set-working-area?")
    Call<ResponseProfileUpload>ApiPartnerSetWorkignArea(@Body RequestBody requestBody);


    @POST("api/partner-remove-working-area?")
    Call<ResponseProfileUpload>ApiPartnerRemoveWorkignArea(@Body RequestBody requestBody);



    @POST("api/partner-get-working-area?")
    Call<ResponseBody>ApiGetAllWorkingLeads(@Body RequestBody requestBody);
    @POST("api/partner-get-working-area?")
    Call<ResponseWorkingRadiusList>ApiGetdAllWorkingLeads(@Body RequestBody requestBody);

    @POST("api/partner-set-working-time?")
    Call<ResponseProfileUpload>ApiSetWorkingTime(@Body RequestBody requestBody);



    @POST("api/partner-remove-working-time?")
    Call<ResponseProfileUpload>ApiRemoveWorkingTime(@Body RequestBody requestBody);

    /*
      http://webtecnoworld.com/zeiq/api/fleet_addtripadvance.php
      http://webtecnoworld.com/phovio/Api/fleet_getcity.php?state_id=1
     * http://webtecnoworld.com/phovio/Api/fleet_addstate.php?state_name=up&state_code=12
     * http://webtecnoworld.com/phovio/Api/fleet_addcity.php?state_name=up&state_code=12&state_id=1&city_name=Etawa
     * http://webtecnoworld.com/phovio/Api/fleet_getcity.php?state_id=1
     * */
    //


    @Multipart
    @POST("fleet_usersignup.php")
    Call<ResponseBody> Register(@Part("user_type") RequestBody user_type, @Part("business_name") RequestBody business_name,
                                @Part("name") RequestBody name, @Part("mobile") RequestBody mobile,
                                @Part("email") RequestBody email, @Part("address") RequestBody address,
                                @Part("locatlity") RequestBody locatlity, @Part("city") RequestBody city,
                                @Part("state") RequestBody state, @Part("country") RequestBody country,
                                @Part("pincode") RequestBody pincode,
                                @Part("landline") RequestBody landline, @Part("pancard") RequestBody pancard,
                                @Part("password") RequestBody password, @Part("role") RequestBody role,
                                @Part MultipartBody.Part file, @Part MultipartBody.Part file2);

    @Multipart
    @POST("fleet_addtruck.php")
    Call<ResponseBody> AddTruckRegister(@Part("owner_id") RequestBody owner_id, @Part("reg_no") RequestBody reg_no,
                                        @Part("make") RequestBody make, @Part("model") RequestBody model,
                                        @Part("type") RequestBody type, @Part("excel") RequestBody excel,
                                        @Part("reg_date") RequestBody reg_date, @Part("reg_vaild") RequestBody reg_vaild,
                                        @Part("engin_no") RequestBody engin_no, @Part("load_capacity") RequestBody load_capacity,
                                        @Part("road_valid") RequestBody road_valid,
                                        @Part("puv_vaild") RequestBody puv_vaild, @Part("insurance_vaild") RequestBody insurance_vaild,
                                        @Part("permit_valid") RequestBody permit_valid, @Part("fitness_valid") RequestBody fitness_valid,
                                        @Part("tank_capacity") RequestBody tank_capacity, @Part("fuel_in") RequestBody fuel_in,
                                        @Part("avg_fuel") RequestBody avg_fuel, @Part("emai_date") RequestBody emai_date,
                                        @Part("financer_office") RequestBody financer_office, @Part("owner_name") RequestBody owner_name,
                                        @Part("owner_address") RequestBody owner_address, @Part("owner_email") RequestBody owner_email,
                                        @Part("owner_contact") RequestBody owner_contact,
                                        @Part MultipartBody.Part file, @Part MultipartBody.Part file1,
                                        @Part MultipartBody.Part file2, @Part MultipartBody.Part file3);


    @Multipart
    @POST("fleet_adddriver.php?")
    Call<ResponseBody> AddDriverRegister(@Part("owner_id") RequestBody owner_id, @Part("name") RequestBody name,
                                         @Part("last_name") RequestBody last_name, @Part("mobile") RequestBody mobile,
                                         @Part("licence_no") RequestBody licence_no, @Part("licence_valid") RequestBody licence_valid,
                                         @Part("drive_exp") RequestBody drive_exp, @Part("dob") RequestBody dob,
                                         @Part("address") RequestBody address,
                                         @Part("locatlity") RequestBody locatlity,
                                         @Part("city") RequestBody city, @Part("state") RequestBody state,
                                         @Part("pincode") RequestBody pincode, @Part("insurance") RequestBody insurance,
                                         @Part("insurnace_company") RequestBody insurnace_company, @Part("policy_no") RequestBody policy_no,
                                         @Part("emr_contactno") RequestBody emr_contactno, @Part("insurance_vaild") RequestBody insurance_vaild,
                                         @Part("blood_group") RequestBody blood_group, @Part("joining_date") RequestBody joining_date,
                                         @Part("pancard") RequestBody pancard, @Part("bank_name") RequestBody bank_name,
                                         @Part("bank_account") RequestBody bank_account, @Part("bank_ifsc") RequestBody bank_ifsc,
                                         @Part MultipartBody.Part file, @Part MultipartBody.Part file1, @Part MultipartBody.Part file2, @Part MultipartBody.Part file3, @Part MultipartBody.Part file4);


    @Multipart
    @POST("api/partner-update-kyc-details")
    Call<ResponseBody> UploadIdentDoc(@Part("expert_id") RequestBody expert_id,@Part("pancard_no") RequestBody pancard_no, @Part MultipartBody.Part file1);

    @Multipart
    @POST("api/partner-update-kyc-details")
    Call<ResponseBody> UploadIdentDoc1(@Part("expert_id") RequestBody expert_id,@Part("pancard_no") RequestBody pancard_no);



    @Multipart
    @POST(" fleet_addvehiclemaintance.php?")
    Call<ResponseBody> AddVehicleMaintence(@Part("owner_id") RequestBody owner_id, @Part("vendor_id") RequestBody vendor_id,
                                           @Part("vendor_name") RequestBody vendor_name, @Part("vehicle_no") RequestBody vehicle_no,
                                           @Part("vehicle_id") RequestBody vehicle_id, @Part("service_date") RequestBody service_date,
                                           @Part("amount") RequestBody amount, @Part("km") RequestBody km,
                                           @Part("des") RequestBody des,
                                           @Part("next_km") RequestBody next_km,
                                           @Part("next_date") RequestBody next_date,
                                           @Part MultipartBody.Part file, @Part MultipartBody.Part file1);




    ////Driver Reposness////////////////////






    /*@POST("fleet_getcompletetrip.php?")
    Call<ResponseGet_CompleteTrip> Fleet_GetCompleteTrip(@Body RequestBody requestBody);
    @POST("fleet_gettripexpenses.php?")
    Call<TripExpenseListResponse> Fleet_GetTripeExpense(@Body RequestBody requestBody);*/
/*    @POST("fleet_getcountexpansce.php?")
    Call<AddTttripExpwithoutarray> Fleet_GetTripeLoadExp(@Body RequestBody requestBody);*/
}