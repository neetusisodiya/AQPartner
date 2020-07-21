package com.app.oooelePartner.Bean;

public class CityBean {
    /**
     * id : 1
     * city : Jaipur
     * lat_codes : 26.92207
     * lng_codes : 75.778885
     * working_radius : 17
     */

    private int id;
    private String city;
    private double lat_codes;
    private double lng_codes;
    private int working_radius;

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

    public double getLat_codes() {
        return lat_codes;
    }

    public void setLat_codes(double lat_codes) {
        this.lat_codes = lat_codes;
    }

    public double getLng_codes() {
        return lng_codes;
    }

    public void setLng_codes(double lng_codes) {
        this.lng_codes = lng_codes;
    }

    public int getWorking_radius() {
        return working_radius;
    }

    public void setWorking_radius(int working_radius) {
        this.working_radius = working_radius;
    }
}