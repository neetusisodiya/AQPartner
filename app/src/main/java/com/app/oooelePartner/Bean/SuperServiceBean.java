package com.app.oooelePartner.Bean;

public class SuperServiceBean {
    /**
     * fault : Water Service
     * id : 11
     */

    private String fault;
    private int id;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
