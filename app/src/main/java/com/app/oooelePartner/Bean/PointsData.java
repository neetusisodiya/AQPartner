package com.app.oooelePartner.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointsData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("rupee")
    @Expose
    private String rupee;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getRupee() {
        return rupee;
    }

    public void setRupee(String rupee) {
        this.rupee = rupee;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

