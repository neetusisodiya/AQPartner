package com.app.oooelePartner.Response;

public class ResponsePayment {

    /**
     * status : true
     * message : Successfully Added to User Wallet
     */

    private String status;
    private String message;

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
}
