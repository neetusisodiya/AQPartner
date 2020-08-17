package com.app.oooelePartner.Response;

import java.util.List;

public class ResponseTime {


    /**
     * status : true
     * data : [{"day":"Monday","time":[{"time1":"8-9 PM"}]}]
     * message : Records Found(s)
     */

    private boolean status;
    private String message;
    private List<ResponseTimeSelect> day;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseTimeSelect> getDay() {
        return day;
    }

    public void setData(List<ResponseTimeSelect> day) {
        this.day = day;
    }

}
