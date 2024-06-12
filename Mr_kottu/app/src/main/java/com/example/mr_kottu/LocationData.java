package com.example.mr_kottu;

import android.widget.TextView;

public class LocationData {
    private String address;
    private String city;

    // Required no-argument constructor
    public LocationData() {}

    public LocationData(String address, String city) {
        this.address = address;
        this.city = city;
    }

    public LocationData(TextView address, TextView city) {

    }

    public LocationData(String accountId, String accountEmail, String addressText, String cityText) {

    }

    // Getters and setters for Firebase serialization
    // Ensure you have these methods
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAccountId(String accountId) {
    }

    public void setAccountEmail(String accountEmail) {
    }
}
