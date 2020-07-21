package com.app.oooelePartner.Bean;

public class BeanNewLeads {

    private String id;
    private  int isAccepted;

    public int isAccepted() {
        return isAccepted;
    }

    public void setAccepted(int accepted) {
        isAccepted = accepted;
    }

    private String serv_id;
    private String fault;
    private String visit_time;
    private String point;
    private String status;
    private String address;
    private String expert_id;
    private  String contact;
    private  String booking_date;
    private   String customer;
    private  String subserv;

    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    private String serv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServ_id() {
        return serv_id;
    }

    public void setServ_id(String serv_id) {
        this.serv_id = serv_id;
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

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public String getPoint() {
        return point+" Points";
    }

    public void setPoint(String point) {
        this.point = point;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSubserv() {
        return subserv;
    }

    public void setSubserv(String subserv) {
        this.subserv = subserv;
    }
}