package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.CityBean;

import java.util.List;

public class ResponsegetCity {


    /**
     * status : true
     * data : [{"id":1,"city":"Jaipur","lat_codes":26.92207,"lng_codes":75.778885,"working_radius":17}]
     */

    private boolean status;
    private List<CityBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<CityBean> getData() {
        return data;
    }

    public void setData(List<CityBean> data) {
        this.data = data;
    }

}
