package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.QualificationBean;

import java.util.List;

public class ResponseQualification {


    /**
     * status : true
     * data : [{"id":1,"name":"Diploma"},{"id":2,"name":"Graduate"},{"id":3,"name":"Post Graduate"}]
     */

    private boolean status;
    private List<QualificationBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<QualificationBean> getData() {
        return data;
    }

    public void setData(List<QualificationBean> data) {
        this.data = data;
    }

}
