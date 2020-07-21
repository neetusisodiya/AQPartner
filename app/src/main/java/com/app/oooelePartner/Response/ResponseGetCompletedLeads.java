package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.BeanCompleteLead;

import java.util.List;

public class ResponseGetCompletedLeads {


    /**
     * status : true
     * data : [{"g_lat":26.9516834,"g_lng":75.7089167,"id":68,"serv":"AC","subserv":"Window","fault":"Water Service","qty":1,"unitRate":400,"discount":20,"booking_date":"2020-02-27","customer":null,"contact":"8949449897","g_address":"B1, shop.4, Kardhani Behind Govidam Tower, Kalwar Rd, Kardhani Govindpura, Govindpura, Jaipur, Rajasthan 302012, India","email":null,"alt_contact_no":null,"customer_id":10,"visit_time":"8-9 AM","created_at":"2020-03-18 11:00:26"}]
     * message : Records Found(s)
     */

    private String status;
    private String message;
    private List<BeanCompleteLead> data;

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

    public List<BeanCompleteLead> getData() {
        return data;
    }

    public void setData(List<BeanCompleteLead> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * g_lat : 26.9516834
         * g_lng : 75.7089167
         * id : 68
         * serv : AC
         * subserv : Window
         * fault : Water Service
         * qty : 1
         * unitRate : 400
         * discount : 20
         * booking_date : 2020-02-27
         * customer : null
         * contact : 8949449897
         * g_address : B1, shop.4, Kardhani Behind Govidam Tower, Kalwar Rd, Kardhani Govindpura, Govindpura, Jaipur, Rajasthan 302012, India
         * email : null
         * alt_contact_no : null
         * customer_id : 10
         * visit_time : 8-9 AM
         * created_at : 2020-03-18 11:00:26
         */

        private double g_lat;
        private double g_lng;
        private int id;
        private String serv;
        private String subserv;
        private String fault;
        private int qty;
        private int unitRate;
        private int discount;
        private String booking_date;
        private Object customer;
        private String contact;
        private String g_address;
        private Object email;
        private Object alt_contact_no;
        private int customer_id;
        private String visit_time;
        private String created_at;

        public double getG_lat() {
            return g_lat;
        }

        public void setG_lat(double g_lat) {
            this.g_lat = g_lat;
        }

        public double getG_lng() {
            return g_lng;
        }

        public void setG_lng(double g_lng) {
            this.g_lng = g_lng;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServ() {
            return serv;
        }

        public void setServ(String serv) {
            this.serv = serv;
        }

        public String getSubserv() {
            return subserv;
        }

        public void setSubserv(String subserv) {
            this.subserv = subserv;
        }

        public String getFault() {
            return fault;
        }

        public void setFault(String fault) {
            this.fault = fault;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public int getUnitRate() {
            return unitRate;
        }

        public void setUnitRate(int unitRate) {
            this.unitRate = unitRate;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getBooking_date() {
            return booking_date;
        }

        public void setBooking_date(String booking_date) {
            this.booking_date = booking_date;
        }

        public Object getCustomer() {
            return customer;
        }

        public void setCustomer(Object customer) {
            this.customer = customer;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getG_address() {
            return g_address;
        }

        public void setG_address(String g_address) {
            this.g_address = g_address;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getAlt_contact_no() {
            return alt_contact_no;
        }

        public void setAlt_contact_no(Object alt_contact_no) {
            this.alt_contact_no = alt_contact_no;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public String getVisit_time() {
            return visit_time;
        }

        public void setVisit_time(String visit_time) {
            this.visit_time = visit_time;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
