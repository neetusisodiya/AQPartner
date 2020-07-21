package com.app.oooelePartner.Response;

import com.app.oooelePartner.Bean.BeanTimeSlot;

import java.util.List;

public class ResponseTimeSelect {
    /**
     * day : Monday
     * time : [{"time1":"8-9 PM"}]
     */

    private String day;
    private List<BeanTimeSlot> time;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<BeanTimeSlot> getTime() {
        return time;
    }

    public void setTime(List<BeanTimeSlot> time) {
        this.time = time;
    }

 }