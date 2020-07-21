package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.LoginBean;

public class ResponseLogin {


    /**
     * status : true
     * data : {"id":5,"name":null,"gender":null,"dob":null,"mobno":"7597100610","email":null,"cityid":null,"cityname":null,"pincode":null,"exp_year":null,"exp_month":null,"aadhar_file":null,"aadhar_no":null,"address":null,"upload_exp_cert":null,"joining_file":null,"join_date":"2020-07-14","pancard_no":null,"user_profile":null,"signature_file":null,"pancard_file":null,"firm_id":null,"firm_name":null,"expert_type":null,"qualification":null,"qualification_id":null,"expert_type_id":null,"working_radius":null,"created_at":"2020-07-14 10:18:11","updated_at":"2020-07-14 10:18:11","same_as_firm":2,"bank_name":null,"account_no":null,"ifsc":null,"secure_token":"cAj5QvclTfOM7FLNGJz3pd:APA91bHBS2ILNTrkld_xA5wRKLK0GniVxIieiPKfyX1bZMeBg4olqO5PJV44gNpxLEqgb7uVhyDCBqvRRitP7yn191SE-oaK4ffvoQdjyDJF38ibpd8Kqm1XOx2nUahtGgj3zH6IBLku"}
     * message : Successfully User Logged in
     */

    private boolean status;
    private LoginBean data;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LoginBean getData() {
        return data;
    }

    public void setData(LoginBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * id : 5
         * name : null
         * gender : null
         * dob : null
         * mobno : 7597100610
         * email : null
         * cityid : null
         * cityname : null
         * pincode : null
         * exp_year : null
         * exp_month : null
         * aadhar_file : null
         * aadhar_no : null
         * address : null
         * upload_exp_cert : null
         * joining_file : null
         * join_date : 2020-07-14
         * pancard_no : null
         * user_profile : null
         * signature_file : null
         * pancard_file : null
         * firm_id : null
         * firm_name : null
         * expert_type : null
         * qualification : null
         * qualification_id : null
         * expert_type_id : null
         * working_radius : null
         * created_at : 2020-07-14 10:18:11
         * updated_at : 2020-07-14 10:18:11
         * same_as_firm : 2
         * bank_name : null
         * account_no : null
         * ifsc : null
         * secure_token : cAj5QvclTfOM7FLNGJz3pd:APA91bHBS2ILNTrkld_xA5wRKLK0GniVxIieiPKfyX1bZMeBg4olqO5PJV44gNpxLEqgb7uVhyDCBqvRRitP7yn191SE-oaK4ffvoQdjyDJF38ibpd8Kqm1XOx2nUahtGgj3zH6IBLku
         */

        private int id;
        private Object name;
        private Object gender;
        private Object dob;
        private String mobno;
        private Object email;
        private Object cityid;
        private Object cityname;
        private Object pincode;
        private Object exp_year;
        private Object exp_month;
        private Object aadhar_file;
        private Object aadhar_no;
        private Object address;
        private Object upload_exp_cert;
        private Object joining_file;
        private String join_date;
        private Object pancard_no;
        private Object user_profile;
        private Object signature_file;
        private Object pancard_file;
        private Object firm_id;
        private Object firm_name;
        private Object expert_type;
        private Object qualification;
        private Object qualification_id;
        private Object expert_type_id;
        private Object working_radius;
        private String created_at;
        private String updated_at;
        private int same_as_firm;
        private Object bank_name;
        private Object account_no;
        private Object ifsc;
        private String secure_token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public String getMobno() {
            return mobno;
        }

        public void setMobno(String mobno) {
            this.mobno = mobno;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getCityid() {
            return cityid;
        }

        public void setCityid(Object cityid) {
            this.cityid = cityid;
        }

        public Object getCityname() {
            return cityname;
        }

        public void setCityname(Object cityname) {
            this.cityname = cityname;
        }

        public Object getPincode() {
            return pincode;
        }

        public void setPincode(Object pincode) {
            this.pincode = pincode;
        }

        public Object getExp_year() {
            return exp_year;
        }

        public void setExp_year(Object exp_year) {
            this.exp_year = exp_year;
        }

        public Object getExp_month() {
            return exp_month;
        }

        public void setExp_month(Object exp_month) {
            this.exp_month = exp_month;
        }

        public Object getAadhar_file() {
            return aadhar_file;
        }

        public void setAadhar_file(Object aadhar_file) {
            this.aadhar_file = aadhar_file;
        }

        public Object getAadhar_no() {
            return aadhar_no;
        }

        public void setAadhar_no(Object aadhar_no) {
            this.aadhar_no = aadhar_no;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getUpload_exp_cert() {
            return upload_exp_cert;
        }

        public void setUpload_exp_cert(Object upload_exp_cert) {
            this.upload_exp_cert = upload_exp_cert;
        }

        public Object getJoining_file() {
            return joining_file;
        }

        public void setJoining_file(Object joining_file) {
            this.joining_file = joining_file;
        }

        public String getJoin_date() {
            return join_date;
        }

        public void setJoin_date(String join_date) {
            this.join_date = join_date;
        }

        public Object getPancard_no() {
            return pancard_no;
        }

        public void setPancard_no(Object pancard_no) {
            this.pancard_no = pancard_no;
        }

        public Object getUser_profile() {
            return user_profile;
        }

        public void setUser_profile(Object user_profile) {
            this.user_profile = user_profile;
        }

        public Object getSignature_file() {
            return signature_file;
        }

        public void setSignature_file(Object signature_file) {
            this.signature_file = signature_file;
        }

        public Object getPancard_file() {
            return pancard_file;
        }

        public void setPancard_file(Object pancard_file) {
            this.pancard_file = pancard_file;
        }

        public Object getFirm_id() {
            return firm_id;
        }

        public void setFirm_id(Object firm_id) {
            this.firm_id = firm_id;
        }

        public Object getFirm_name() {
            return firm_name;
        }

        public void setFirm_name(Object firm_name) {
            this.firm_name = firm_name;
        }

        public Object getExpert_type() {
            return expert_type;
        }

        public void setExpert_type(Object expert_type) {
            this.expert_type = expert_type;
        }

        public Object getQualification() {
            return qualification;
        }

        public void setQualification(Object qualification) {
            this.qualification = qualification;
        }

        public Object getQualification_id() {
            return qualification_id;
        }

        public void setQualification_id(Object qualification_id) {
            this.qualification_id = qualification_id;
        }

        public Object getExpert_type_id() {
            return expert_type_id;
        }

        public void setExpert_type_id(Object expert_type_id) {
            this.expert_type_id = expert_type_id;
        }

        public Object getWorking_radius() {
            return working_radius;
        }

        public void setWorking_radius(Object working_radius) {
            this.working_radius = working_radius;
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

        public int getSame_as_firm() {
            return same_as_firm;
        }

        public void setSame_as_firm(int same_as_firm) {
            this.same_as_firm = same_as_firm;
        }

        public Object getBank_name() {
            return bank_name;
        }

        public void setBank_name(Object bank_name) {
            this.bank_name = bank_name;
        }

        public Object getAccount_no() {
            return account_no;
        }

        public void setAccount_no(Object account_no) {
            this.account_no = account_no;
        }

        public Object getIfsc() {
            return ifsc;
        }

        public void setIfsc(Object ifsc) {
            this.ifsc = ifsc;
        }

        public String getSecure_token() {
            return secure_token;
        }

        public void setSecure_token(String secure_token) {
            this.secure_token = secure_token;
        }
    }
}
