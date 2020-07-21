package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.BeanGetWalletData;

import java.util.List;

public class ResponseGetWalletData {


    /**
     * status : true
     * data : [{"id":2,"amount":200,"member_id":26,"created":"2020-05-22 12:54:41","detail":"Added By Admin For Received Payment"}]
     */

    private String status;
    private String total_amount


;

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    private List<BeanGetWalletData> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BeanGetWalletData> getData() {
        return data;
    }

    public void setData(List<BeanGetWalletData> data) {
        this.data = data;
    }

 }
