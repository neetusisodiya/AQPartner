package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.SuperServiceBean;

import java.util.List;

public class ResponseSuperService {

    /**
     * service : AC/Split
     * faults : [{"fault":"Water Service","id":11},{"fault":"New Installation","id":12}]
     */

    private String service;
    private List<SuperServiceBean> faults;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<SuperServiceBean> getFaults() {
        return faults;
    }

    public void setFaults(List<SuperServiceBean> faults) {
        this.faults = faults;
    }

 }