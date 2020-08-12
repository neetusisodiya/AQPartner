package com.app.oooelePartner.Bean;

public class BeanNewLeads {

    private String id;
    private int isAccepted;
    private String serv_id;
    private String fault;
    private String visit_time;
    private int point;
    private String status;
    private String address;
    private String expert_id;
    private String subserv;
    private String serv;
    private String qty;
    private String visit_date;
    private String unitRate;

    public String getQty() {
        return qty;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public String getUnitRate() {
        return unitRate;
    }


    public int isAccepted() {
        return isAccepted;
    }


    public String getServ() {
        return serv;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServ_id() {
        return serv_id;
    }


    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getVisit_time() {
        return visit_time;
    }


    public int getPoint() {
        return point;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(String expert_id) {
        this.expert_id = expert_id;
    }


    public String getSubserv() {
        return subserv;
    }

}