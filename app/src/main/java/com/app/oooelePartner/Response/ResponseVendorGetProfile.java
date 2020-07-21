package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.GetVendorProfileBean;

import java.util.List;

public class ResponseVendorGetProfile {
    /**
     * status : true
     * data : [{"id":17,"name":"Pawan prajapati","gender":null,"dob":"1988-10-21","mobno":"7737395108","email":"prajapatipawanphp@gmail.com","cityid":1,"pincode":"302021","exp_year":"7","exp_month":"10","address":"Khirni Phatak, Jaipur","user_profile":null,"expert_type_id":2,"qualification_id":2,"aadhar_file":null,"aadhar_no":null,"upload_exp_cert":null,"pancard_no":null,"pancard_file":null}]
     */

    private String status;
    private List<GetVendorProfileBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetVendorProfileBean> getData() {
        return data;
    }

    public void setData(List<GetVendorProfileBean> data) {
        this.data = data;
    }

}
