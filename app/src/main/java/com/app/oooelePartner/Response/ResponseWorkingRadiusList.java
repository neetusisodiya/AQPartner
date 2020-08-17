package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.BeanWorkingRadius;

import java.util.List;

public class ResponseWorkingRadiusList {


    /**
     * status : true
     * data : [{"id":18,"expert_id":1,"lat":26.9324884,"lng":75.7374482,"working_radius":40}]
     * message : Records Found(s)
     */

    private String status;
    private String message;
    private List<BeanWorkingRadius> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BeanWorkingRadius> getData() {
        return data;
    }

    public void setData(List<BeanWorkingRadius> data) {
        this.data = data;
    }

}
