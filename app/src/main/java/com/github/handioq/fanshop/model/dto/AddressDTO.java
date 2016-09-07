package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

public class AddressDTO {

    @SerializedName("street")
    private String street;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("zipcode")
    private long zipcode;

    public AddressDTO(String street, String city, String country, long zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}
