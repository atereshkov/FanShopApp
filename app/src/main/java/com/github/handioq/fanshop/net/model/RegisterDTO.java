package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class RegisterDTO {

    @SerializedName("mail")
    private String mail;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("street")
    private String street;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("zipcode")
    private long zipcode;

    public RegisterDTO(String mail, String password, String name, String phone, String street, String city, String country, long zipcode) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }
}
