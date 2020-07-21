package com.app.oooelePartner.Response;

public class ResponseProfileUpload {


    /**
     * status : true
     * message : Successfully Profile Updated
     */

    private boolean status;
    private String message;
    /**
     * data : 96
     */

    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
