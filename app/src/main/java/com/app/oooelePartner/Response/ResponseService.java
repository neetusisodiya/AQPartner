package com.app.oooelePartner.Response;

import java.util.List;

public class ResponseService {


    /**
     * status : true
     * data : [{"service":"AC/Split","faults":[{"fault":"Water Service","id":11},{"fault":"New Installation","id":12}]},{"service":"AC/Window","faults":[{"fault":"New installation","id":13},{"fault":"Water Service","id":14}]},{"service":"Car Wash/Hatchback","faults":[{"fault":"Complete Cleaning","id":15}]},{"service":"Carpenter/Repair","faults":[{"fault":"Door Lock Repair","id":16}]}]
     * message : Records Found(s)
     */

    private boolean status;
    private String message;
    private List<ResponseSuperService> data;

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

    public List<ResponseSuperService> getData() {
        return data;
    }

    public void setData(List<ResponseSuperService> data) {
        this.data = data;
    }

 }
