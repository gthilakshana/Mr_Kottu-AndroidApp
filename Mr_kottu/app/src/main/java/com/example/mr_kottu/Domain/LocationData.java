package com.example.mr_kottu.Domain;

public class LocationData {

    private String accountId;
    private String accountEmail;
    private String address;
    private String city;

    public LocationData(String accountId, String accountEmail, String address, String city) {
        this.accountId = accountId;
        this.accountEmail = accountEmail;
        this.address = address;
        this.city = city;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

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
}

