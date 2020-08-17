package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.BeanGetWorkingArea;

import java.util.List;

public class ResponseGetWorkingArea {


    /**
     * status : true
     * data : [{"id":4,"expert_id":17,"lat":26,"lng":76,"working_radius":15},{"id":5,"expert_id":17,"lat":26,"lng":76,"working_radius":15},{"id":6,"expert_id":17,"lat":26,"lng":76,"working_radius":15}]
     * message : Records Found(s)
     */

    private boolean status;
    private String message;
    private List<BeanGetWorkingArea> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BeanGetWorkingArea> getData() {
        return data;
    }

    public void setData(List<BeanGetWorkingArea> data) {
        this.data = data;
    }

}
