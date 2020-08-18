package com.app.oooelePartner.Response;

public class ResponseAccept {


    /**
     * message : Order Successfully Accepted
     */

    private String message;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
