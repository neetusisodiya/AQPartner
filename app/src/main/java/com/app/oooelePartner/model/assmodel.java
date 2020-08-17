package com.app.oooelePartner.model;

public class assmodel {


    String assname, assTime, assPlace, assPrice, assPartner;

    public assmodel() {
    }


    public assmodel(String assname, String assTime, String assPlace, String assPrice, String assPartner) {
        this.assname = assname;
        this.assTime = assTime;
        this.assPlace = assPlace;
        this.assPrice = assPrice;
        this.assPartner = assPartner;
    }


    public String getAssname() {
        return assname;
    }

    public void setAssname(String assname) {
        this.assname = assname;
    }

    public String getAssTime() {
        return assTime;
    }

    public void setAssTime(String assTime) {
        this.assTime = assTime;
    }

    public String getAssPlace() {
        return assPlace;
    }

    public void setAssPlace(String assPlace) {
        this.assPlace = assPlace;
    }

    public String getAssPrice() {
        return assPrice;
    }

    public void setAssPrice(String assPrice) {
        this.assPrice = assPrice;
    }

    public String getAssPartner() {
        return assPartner;
    }

    public void setAssPartner(String assPartner) {
        this.assPartner = assPartner;
    }
}
