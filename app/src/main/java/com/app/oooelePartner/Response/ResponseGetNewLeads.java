package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.BeanNewLeads;

import java.util.List;

public class ResponseGetNewLeads {


    /**
     * status : true
     * data : [{"id":231,"city":"Jaipur","city_id":1,"serv":"AC","serv_id":5,"subserv":"Split","subserv_id":10,"fault":"Repair","qty":1,"unitRate":200,"discount":null,"booking_date":"2020-05-20","created_at":"2020-05-20 16:53:35","updated_at":"2020-05-20 16:53:35","complaint_id":"JAI2005202","firm_id":0,"firm_name":null,"firm_assign_date":"2020-05-20 17:17:17","part_serial_no":null,"part_image":null,"status":2,"counter":1,"customer":null,"contact":"8107116566","address_type":"Home","address":"7, Khirni Phatak Rd, Om Nagar, Khatipura, Jaipur, Rajasthan 302012, India Jaipur Rajasthan India 302012","comp_city":1,"zipcode":null,"email":null,"alt_contact_no":null,"customer_id":8,"complaint_source":"cust","update_call_status":null,"update_call_status_date":null,"visit_date":"2020-05-20","visit_time":"8-9 AM","cmplnt_close_amnt":null,"cnc_up_date":null,"expert_id":1,"expert_name":"surendra kumar","is_feedback":2,"send_estimate_status":2,"send_estimate_date":null,"feedback_star_id":null,"feedback_stars":null,"feedback_desc":null,"source":"app","g_address":"7, Khirni Phatak Rd, Om Nagar, Khatipura, Jaipur, Rajasthan 302012, India Jaipur Rajasthan India 302012","g_lat":26.93289,"g_lng":26.93289,"prebooking_date":"2020-05-20"},{"id":69,"city":"Jaipur","city_id":1,"serv":"Geyser","serv_id":8,"subserv":"Electric Geyser","subserv_id":17,"fault":"Repair","qty":1,"unitRate":200,"discount":10,"booking_date":"2020-02-27","created_at":"2020-03-18 11:00:26","updated_at":"2020-03-18 11:00:26","complaint_id":"JAI2002276","firm_id":0,"firm_name":null,"firm_assign_date":"2020-04-01 15:38:03","part_serial_no":null,"part_image":null,"status":2,"counter":2,"customer":null,"contact":"8949449897","address_type":"Home","address":"B1, shop.4, Kardhani Behind Govidam Tower, Kalwar Rd, Kardhani Govindpura, Govindpura, Jaipur, Rajasthan 302012, India","comp_city":1,"zipcode":null,"email":null,"alt_contact_no":null,"customer_id":10,"complaint_source":"cust","update_call_status":null,"update_call_status_date":null,"visit_date":"2020-02-27","visit_time":"8-9 AM","cmplnt_close_amnt":null,"cnc_up_date":null,"expert_id":1,"expert_name":"surendra kumar","is_feedback":2,"send_estimate_status":2,"send_estimate_date":null,"feedback_star_id":null,"feedback_stars":null,"feedback_desc":null,"source":"app","g_address":"B1, shop.4, Kardhani Behind Govidam Tower, Kalwar Rd, Kardhani Govindpura, Govindpura, Jaipur, Rajasthan 302012, India","g_lat":26.9516834,"g_lng":75.7089167,"prebooking_date":"2020-03-18"}]
     * message : Records Found(s)
     */

    private String status;
    private String message;

    private List<BeanNewLeads> data;

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

    public List<BeanNewLeads> getData() {
        return data;
    }

    public void setData(List<BeanNewLeads> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 231
         * city : Jaipur
         * city_id : 1
         * serv : AC
         * serv_id : 5
         * subserv : Split
         * subserv_id : 10
         * fault : Repair
         * qty : 1
         * unitRate : 200
         * discount : null
         * booking_date : 2020-05-20
         * created_at : 2020-05-20 16:53:35
         * updated_at : 2020-05-20 16:53:35
         * complaint_id : JAI2005202
         * firm_id : 0
         * firm_name : null
         * firm_assign_date : 2020-05-20 17:17:17
         * part_serial_no : null
         * part_image : null
         * status : 2
         * counter : 1
         * customer : null
         * contact : 8107116566
         * address_type : Home
         * address : 7, Khirni Phatak Rd, Om Nagar, Khatipura, Jaipur, Rajasthan 302012, India Jaipur Rajasthan India 302012
         * comp_city : 1
         * zipcode : null
         * email : null
         * alt_contact_no : null
         * customer_id : 8
         * complaint_source : cust
         * update_call_status : null
         * update_call_status_date : null
         * visit_date : 2020-05-20
         * visit_time : 8-9 AM
         * cmplnt_close_amnt : null
         * cnc_up_date : null
         * expert_id : 1
         * expert_name : surendra kumar
         * is_feedback : 2
         * send_estimate_status : 2
         * send_estimate_date : null
         * feedback_star_id : null
         * feedback_stars : null
         * feedback_desc : null
         * source : app
         * g_address : 7, Khirni Phatak Rd, Om Nagar, Khatipura, Jaipur, Rajasthan 302012, India Jaipur Rajasthan India 302012
         * g_lat : 26.93289
         * g_lng : 26.93289
         * prebooking_date : 2020-05-20
         */

        private int id;
        private String city;
        private int city_id;
        private String serv;
        private int serv_id;
        private String subserv;
        private int subserv_id;
        private String fault;
        private int qty;
        private int unitRate;
        private Object discount;
        private String booking_date;
        private String created_at;
        private String updated_at;
        private String complaint_id;
        private int firm_id;
        private Object firm_name;
        private String firm_assign_date;
        private Object part_serial_no;
        private Object part_image;
        private int status;
        private int counter;
        private Object customer;
        private String contact;
        private String address_type;
        private String address;
        private int comp_city;
        private Object zipcode;
        private Object email;
        private Object alt_contact_no;
        private int customer_id;
        private String complaint_source;
        private Object update_call_status;
        private Object update_call_status_date;
        private String visit_date;
        private String visit_time;
        private Object cmplnt_close_amnt;
        private Object cnc_up_date;
        private int expert_id;
        private String expert_name;
        private int is_feedback;
        private int send_estimate_status;
        private Object send_estimate_date;
        private Object feedback_star_id;
        private Object feedback_stars;
        private Object feedback_desc;
        private String source;
        private String g_address;
        private double g_lat;
        private double g_lng;
        private String prebooking_date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getServ() {
            return serv;
        }

        public void setServ(String serv) {
            this.serv = serv;
        }

        public int getServ_id() {
            return serv_id;
        }

        public void setServ_id(int serv_id) {
            this.serv_id = serv_id;
        }

        public String getSubserv() {
            return subserv;
        }

        public void setSubserv(String subserv) {
            this.subserv = subserv;
        }

        public int getSubserv_id() {
            return subserv_id;
        }

        public void setSubserv_id(int subserv_id) {
            this.subserv_id = subserv_id;
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

        public Object getDiscount() {
            return discount;
        }

        public void setDiscount(Object discount) {
            this.discount = discount;
        }

        public String getBooking_date() {
            return booking_date;
        }

        public void setBooking_date(String booking_date) {
            this.booking_date = booking_date;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getComplaint_id() {
            return complaint_id;
        }

        public void setComplaint_id(String complaint_id) {
            this.complaint_id = complaint_id;
        }

        public int getFirm_id() {
            return firm_id;
        }

        public void setFirm_id(int firm_id) {
            this.firm_id = firm_id;
        }

        public Object getFirm_name() {
            return firm_name;
        }

        public void setFirm_name(Object firm_name) {
            this.firm_name = firm_name;
        }

        public String getFirm_assign_date() {
            return firm_assign_date;
        }

        public void setFirm_assign_date(String firm_assign_date) {
            this.firm_assign_date = firm_assign_date;
        }

        public Object getPart_serial_no() {
            return part_serial_no;
        }

        public void setPart_serial_no(Object part_serial_no) {
            this.part_serial_no = part_serial_no;
        }

        public Object getPart_image() {
            return part_image;
        }

        public void setPart_image(Object part_image) {
            this.part_image = part_image;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCounter() {
            return counter;
        }

        public void setCounter(int counter) {
            this.counter = counter;
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

        public String getAddress_type() {
            return address_type;
        }

        public void setAddress_type(String address_type) {
            this.address_type = address_type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getComp_city() {
            return comp_city;
        }

        public void setComp_city(int comp_city) {
            this.comp_city = comp_city;
        }

        public Object getZipcode() {
            return zipcode;
        }

        public void setZipcode(Object zipcode) {
            this.zipcode = zipcode;
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

        public String getComplaint_source() {
            return complaint_source;
        }

        public void setComplaint_source(String complaint_source) {
            this.complaint_source = complaint_source;
        }

        public Object getUpdate_call_status() {
            return update_call_status;
        }

        public void setUpdate_call_status(Object update_call_status) {
            this.update_call_status = update_call_status;
        }

        public Object getUpdate_call_status_date() {
            return update_call_status_date;
        }

        public void setUpdate_call_status_date(Object update_call_status_date) {
            this.update_call_status_date = update_call_status_date;
        }

        public String getVisit_date() {
            return visit_date;
        }

        public void setVisit_date(String visit_date) {
            this.visit_date = visit_date;
        }

        public String getVisit_time() {
            return visit_time;
        }

        public void setVisit_time(String visit_time) {
            this.visit_time = visit_time;
        }

        public Object getCmplnt_close_amnt() {
            return cmplnt_close_amnt;
        }

        public void setCmplnt_close_amnt(Object cmplnt_close_amnt) {
            this.cmplnt_close_amnt = cmplnt_close_amnt;
        }

        public Object getCnc_up_date() {
            return cnc_up_date;
        }

        public void setCnc_up_date(Object cnc_up_date) {
            this.cnc_up_date = cnc_up_date;
        }

        public int getExpert_id() {
            return expert_id;
        }

        public void setExpert_id(int expert_id) {
            this.expert_id = expert_id;
        }

        public String getExpert_name() {
            return expert_name;
        }

        public void setExpert_name(String expert_name) {
            this.expert_name = expert_name;
        }

        public int getIs_feedback() {
            return is_feedback;
        }

        public void setIs_feedback(int is_feedback) {
            this.is_feedback = is_feedback;
        }

        public int getSend_estimate_status() {
            return send_estimate_status;
        }

        public void setSend_estimate_status(int send_estimate_status) {
            this.send_estimate_status = send_estimate_status;
        }

        public Object getSend_estimate_date() {
            return send_estimate_date;
        }

        public void setSend_estimate_date(Object send_estimate_date) {
            this.send_estimate_date = send_estimate_date;
        }

        public Object getFeedback_star_id() {
            return feedback_star_id;
        }

        public void setFeedback_star_id(Object feedback_star_id) {
            this.feedback_star_id = feedback_star_id;
        }

        public Object getFeedback_stars() {
            return feedback_stars;
        }

        public void setFeedback_stars(Object feedback_stars) {
            this.feedback_stars = feedback_stars;
        }

        public Object getFeedback_desc() {
            return feedback_desc;
        }

        public void setFeedback_desc(Object feedback_desc) {
            this.feedback_desc = feedback_desc;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getG_address() {
            return g_address;
        }

        public void setG_address(String g_address) {
            this.g_address = g_address;
        }

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

        public String getPrebooking_date() {
            return prebooking_date;
        }

        public void setPrebooking_date(String prebooking_date) {
            this.prebooking_date = prebooking_date;
        }
    }
}
