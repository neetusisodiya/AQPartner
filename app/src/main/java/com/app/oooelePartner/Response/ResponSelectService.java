package com.app.oooelePartner.Response;

public class ResponSelectService {


    /**
     * status : true
     * message : Successfully Saved Working Service
     */

    private boolean status;
    private String message;

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
}
