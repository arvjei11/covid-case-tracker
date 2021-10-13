package com.arvjei.covidtracker.models;

public class LocationStatistics {

    private String country;
    private String state;
    private int totalCases;
    private int delta;

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalCases() {
        return totalCases;
    }

    @Override
    public String toString() {
        return "LocationStatistics{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", totalCases=" + totalCases +
                '}';
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }




}
