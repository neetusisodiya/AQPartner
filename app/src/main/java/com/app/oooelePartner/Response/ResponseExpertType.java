package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.ExpertBean;

import java.util.List;

public class ResponseExpertType {


    /**
     * status : true
     * data : [{"id":1,"value":"Technician"},{"id":2,"value":"Carpenter"},{"id":3,"value":"Plumber"},{"id":4,"value":"Electrician"},{"id":5,"value":"Sweeper"},{"id":6,"value":"Beautician"},{"id":7,"value":"Gardener"},{"id":8,"value":"Car Washer"},{"id":9,"value":"Tank Washer"},{"id":10,"value":"Pest Controller"}]
     */

    private boolean status;
    private List<ExpertBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ExpertBean> getData() {
        return data;
    }

    public void setData(List<ExpertBean> data) {
        this.data = data;
    }

 }
