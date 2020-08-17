package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.BeanOpenLeads;

import java.util.List;

public class ResponseGetOpenLeads {


    /**
     * status : true
     * data : [{"g_lat":26.93289,"g_lng":26.93289,"id":231,"serv":"AC","subserv":"Split","fault":"Repair","qty":1,"unitRate":200,"discount":null,"booking_date":"2020-05-20","customer":null,"contact":"8107116566","g_address":"7, Khirni Phatak Rd, Om Nagar, Khatipura, Jaipur, Rajasthan 302012, India Jaipur Rajasthan India 302012","email":null,"alt_contact_no":null,"customer_id":8,"visit_time":"8-9 AM","created_at":"2020-05-20 16:53:35"},{"g_lat":26.9516834,"g_lng":75.7089167,"id":69,"serv":"Geyser","subserv":"Electric Geyser","fault":"Repair","qty":1,"unitRate":200,"discount":10,"booking_date":"2020-02-27","customer":null,"contact":"8949449897","g_address":"B1, shop.4, Kardhani Behind Govidam Tower, Kalwar Rd, Kardhani Govindpura, Govindpura, Jaipur, Rajasthan 302012, India","email":null,"alt_contact_no":null,"customer_id":10,"visit_time":"8-9 AM","created_at":"2020-03-18 11:00:26"},{"g_lat":26.9516834,"g_lng":75.7089167,"id":68,"serv":"AC","subserv":"Window","fault":"Water Service","qty":1,"unitRate":400,"discount":20,"booking_date":"2020-02-27","customer":null,"contact":"8949449897","g_address":"B1, shop.4, Kardhani Behind Govidam Tower, Kalwar Rd, Kardhani Govindpura, Govindpura, Jaipur, Rajasthan 302012, India","email":null,"alt_contact_no":null,"customer_id":10,"visit_time":"8-9 AM","created_at":"2020-03-18 11:00:26"}]
     * message : Records Found(s)
     */

    private String status;
    private String message;
    private List<BeanOpenLeads> data;

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

    public List<BeanOpenLeads> getData() {
        return data;
    }

    public void setData(List<BeanOpenLeads> data) {
        this.data = data;
    }

}
